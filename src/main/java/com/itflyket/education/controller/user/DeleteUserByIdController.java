package com.itflyket.education.controller.user;

import com.itflyket.education.service.DeleteUserByIdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class DeleteUserByIdController {

    @Autowired
    private DeleteUserByIdService deleteUserByIdService;

    @DeleteMapping("/{id}")
    @CrossOrigin
    public ResponseEntity<Void> deleteUserById(@PathVariable Long id){
        this.deleteUserByIdService.deleteUserById(id);
        return ResponseEntity.noContent().build();  //返回204， NO Content
    }
}
