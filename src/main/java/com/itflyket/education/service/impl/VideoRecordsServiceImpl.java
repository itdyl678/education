package com.itflyket.education.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itflyket.education.entity.VideoRecords;
import com.itflyket.education.service.VideoRecordsService;
import com.itflyket.education.mapper.VideoRecordsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
* @author 铯日光岩fgr
* @description 针对表【video_records】的数据库操作Service实现
* @createDate 2025-01-08 15:15:28
*/
@Service
public class VideoRecordsServiceImpl extends ServiceImpl<VideoRecordsMapper, VideoRecords>
implements VideoRecordsService{

    @Autowired
    private VideoRecordsMapper videoRecordsMapper;

}
