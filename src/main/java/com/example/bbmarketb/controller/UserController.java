package com.example.bbmarketb.controller;

import com.example.bbmarketb.model.User;
import com.example.bbmarketb.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")//main에서 api에 들어가서 user에서 할일
public class UserController {
    //me - 회원정보
    //update - 회원정보수정
    //delete - 회원탈퇴

    @Autowired
    private UserRepository userRepo;
    @Autowired
    private UserRepository userRepository;

    //localhost:5173/api/user/signup
    @PostMapping("/signup")
    public ResponseEntity<?>  Signup(@RequestBody User user) {
        if (userRepo.existsById(user.getId())) {
            return ResponseEntity.badRequest().body("가입된 ID가 이미 있습니다");
        }
            return ResponseEntity.ok(userRepo.save(user));
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody User user){ //RequestBody - DB에 들어가는 경로를 만들어 놓고 / DB에 들어가서 해야할 일을 여기서 선언 (ID 찾기)
        String loginUser = userRepository.findById(user.getId()); //DB에서 가져오기
        if(loginUser.isEmpty()){ //Null값인지
            return ResponseEntity.badRequest().body("가입된 아이디가 없습니다");
        }
            //내 입력과 DB 정보가 맞는지
            //틀린경우
        //user.getID() / user.getPassword()
        //UserRepository에 ID 찾는 메소드

        return ResponseEntity.ok(loginUser + "로그인 되었습니다");
    }

}
