package com.example.bbmarketb.controller;

import com.example.bbmarketb.model.User;
import com.example.bbmarketb.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {
    /*
    /me - 회원정보
    /update - 회원정보수정
    /delete - 회원탈퇴
    */

    @Autowired
    private UserRepository userRepo;

    //localhost:5173/api/user/signup
    @PostMapping("/signup")
    public ResponseEntity<?> Signup(@RequestBody User user) {
        if(userRepo.existsById(user.getID())){
            return ResponseEntity.badRequest().body("가입된 아이디가 이미 있습니다.");
        }
        return ResponseEntity.ok(userRepo.save(user));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {
        String loginUser = userRepo.findById(user.getID());
        if(loginUser.isEmpty()){
            return ResponseEntity.badRequest().body("가입된 아이디가 없습니다.");
        }
        return ResponseEntity.ok(loginUser + "회원님 로그인 되었습니다");
    }

}
