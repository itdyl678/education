package com.itflyket.education.bigmodel;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import okhttp3.*;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.*;

public class OptionalAPI extends WebSocketListener {

    // 常量定义
    public static final String hostUrl = "https://sparkcube-api.xf-yun.com/v1/customize";
    public static final String domain = "4.0Ultra:";
    public static final String APPID = "ca4ec749";
    public static final String APISECRET = "ZDBkNGY3MmQxNTg1NmY1MDkyYzRlNmM5";
    public static final String APIKEY = "531ab87447e9f5bb7d376c2b10b15050";

    // 全局存储回答
    private static String totalAnswer = "";
    // 处理 JSON
    private static final Gson gson = new Gson();

    // 用于标记 WebSocket 连接是否关闭
    private boolean wsCloseFlag = false;

    // 用于同步等待回答
    private final Object lock = new Object();

    /**
     * 通过这个方法对外提供调用：接收问题字符串，返回回答内容
     *
     * @param question 前端传入的问题
     * @return AI回答
     */
    public String askAi(String question) throws Exception {
        // 清空全局回答
        totalAnswer = "";
        // 1. 构建鉴权URL
        String authUrl = MyThread.getAuthUrl(hostUrl, APIKEY, APISECRET);
        String wsUrl = authUrl.replace("http://", "ws://").replace("https://", "wss://");

        // 2. 创建 OkHttp WebSocket
        OkHttpClient client = new OkHttpClient.Builder().build();
        Request request = new Request.Builder().url(wsUrl).build();
        WebSocket webSocket = client.newWebSocket(request, this);

        // 3. 构造并发送请求（在一个新线程里）
        MyThread sendThread = new MyThread(webSocket, question);
        sendThread.start();

        // 4. 阻塞等待回答完成
        synchronized (lock) {
            while (!wsCloseFlag) {
                lock.wait(1000); // 每秒检查一次，防止过度阻塞
            }
        }
        return totalAnswer;
    }

    /**
     * WebSocket连接成功后，会调用onOpen
     */
    @Override
    public void onOpen(WebSocket webSocket, Response response) {
        super.onOpen(webSocket, response);
        System.out.println("WebSocket 连接已建立，开始发送请求...");
    }

    /**
     * 接收到服务端的消息
     */
    @Override
    public void onMessage(WebSocket webSocket, String text) {
        MyThread.JsonParse myJsonParse = gson.fromJson(text, MyThread.JsonParse.class);
        if (myJsonParse.header.code != 0) {
            System.err.println("发生错误，错误码：" + myJsonParse.header.code);
            System.err.println("sid：" + myJsonParse.header.sid);
            webSocket.close(1000, "");
        }
        List<MyThread.Text> textList = myJsonParse.payload.choices.text;
        for (MyThread.Text temp : textList) {
            System.out.print(temp.content); // 打印
            totalAnswer += temp.content;    // 累加
        }
        // 如果返回结束标志
        if (myJsonParse.header.status == 2) {
            System.out.println("\n************** 回答结束 **************");
            // 标记关闭并唤醒等待线程
            wsCloseFlag = true;
            synchronized (lock) {
                lock.notifyAll();
            }
            webSocket.close(1000, "");
        }
    }

    /**
     * WebSocket连接失败
     */
    @Override
    public void onFailure(WebSocket webSocket, Throwable t, Response response) {
        super.onFailure(webSocket, t, response);
        if (response != null) {
            try {
                int code = response.code();
                System.err.println("onFailure code:" + code);
                System.err.println("onFailure body:" + response.body().string());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        wsCloseFlag = true;
        synchronized (lock) {
            lock.notifyAll();
        }
    }

    /**
     * 发送请求的线程
     */
    class MyThread extends Thread {
        private WebSocket webSocket;
        private String question;

        public MyThread(WebSocket webSocket, String question) {
            this.webSocket = webSocket;
            this.question = question;
        }

        @Override
        public void run() {
            try {
                JSONObject requestJson = new JSONObject();

                // header
                JSONObject header = new JSONObject();
                header.put("app_id", APPID);
                header.put("uid", UUID.randomUUID().toString().substring(0, 10));

                // parameter
                JSONObject parameter = new JSONObject();
                JSONObject chat = new JSONObject();
                chat.put("domain", domain);
                chat.put("temperature", 0.5);
                chat.put("max_tokens", 4096);
                parameter.put("chat", chat);

                // payload
                JSONObject payload = new JSONObject();
                JSONObject message = new JSONObject();
                JSONArray text = new JSONArray();

                RoleContent roleContent = new RoleContent();
                roleContent.role = "user";
                ArrayList<Object> content = new ArrayList<>();

                TextType textType = new TextType();
                textType.type = "text";
                textType.text = question; // 使用传入的 question
                content.add(textType);
                roleContent.content = content;
                text.add(JSON.toJSON(roleContent));
                message.put("text", text);
                payload.put("message", message);

                requestJson.put("header", header);
                requestJson.put("parameter", parameter);
                requestJson.put("payload", payload);

                // 发送
                webSocket.send(requestJson.toString());

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        // 鉴权方法
        public static String getAuthUrl(String hostUrl, String apiKey, String apiSecret) throws Exception {
            URL url = new URL(hostUrl);
            // 时间
            SimpleDateFormat format = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z", Locale.US);
            format.setTimeZone(TimeZone.getTimeZone("GMT"));
            String date = format.format(new Date());
            // 拼接
            String preStr = "host: " + url.getHost() + "\n" +
                    "date: " + date + "\n" +
                    "GET " + url.getPath() + " HTTP/1.1";
            // System.err.println(preStr);
            // SHA256加密
            Mac mac = Mac.getInstance("hmacsha256");
            SecretKeySpec spec = new SecretKeySpec(apiSecret.getBytes(StandardCharsets.UTF_8), "hmacsha256");
            mac.init(spec);

            byte[] hexDigits = mac.doFinal(preStr.getBytes(StandardCharsets.UTF_8));
            // Base64加密
            String sha = Base64.getEncoder().encodeToString(hexDigits);
            // System.err.println(sha);
            // 拼接
            String authorization = String.format("api_key=\"%s\", algorithm=\"%s\", headers=\"%s\", signature=\"%s\"", apiKey, "hmac-sha256", "host date request-line", sha);
            // 拼接地址
            HttpUrl httpUrl = Objects.requireNonNull(HttpUrl.parse("https://" + url.getHost() + url.getPath())).newBuilder().//
                    addQueryParameter("authorization", Base64.getEncoder().encodeToString(authorization.getBytes(StandardCharsets.UTF_8))).//
                    addQueryParameter("date", date).//
                    addQueryParameter("host", url.getHost()).//
                    build();

            // System.err.println(httpUrl.toString());
            return httpUrl.toString();
        }

        //返回的json结果拆解
        class JsonParse {
            Header header;
            Payload payload;
        }

        class Header {
            int code;
            int status;
            String sid;

            String message;
        }

        class Payload {
            Choices choices;
        }

        class Choices {
            List<Text> text;
        }

        class Text {
            String role;
            String content;
        }

        class RoleContent {
            public String role;
            public List content;

        }

        class FileType {
            public String type;
            public List file;
        }

        class TextType {
            public String type;
            public String text;
        }
    }
}