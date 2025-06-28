package com.example.bbmarketb.controller;

import com.example.bbmarketb.model.User;
import com.example.bbmarketb.repository.UserRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

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
        if (userRepo.existsByUserId(user.getUserId())) {
            return ResponseEntity.badRequest().body("가입된 ID가 이미 있습니다");
        }
            return ResponseEntity.ok(userRepo.save(user));
    }
    //localhost:5173/api/user/login
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody User user){ //RequestBody - DB에 들어가는 경로를 만들어 놓고 / DB에 들어가서 해야할 일을 여기서 선언 (ID 찾기)
        Optional<User> loginUser = userRepository.findByUserId(user.getUserId()); //DB에서 가져오기
        Optional<User> loginP = userRepository.findByUserId(user.getPassword());

        /*
        if(loginUser.isPresent() && loginP.isPresent()){
           if(!loginUser.equals(user.getUserId())) {
               return ResponseEntity.badRequest().body("가입된 ID가 없습니다");
           }
        }
        */
        // 2. 아이디 존재 여부 확인
        if (loginUser.isEmpty()) {
            return ResponseEntity.badRequest().body("가입된 ID가 없습니다");
        }

        // 3. 비밀번호 비교
        if (!loginUser.get().getPassword().equals(user.getPassword())) {
            return ResponseEntity.badRequest().body("비밀번호가 일치하지 않습니다");
        }

        // 4. 로그인 성공
        return ResponseEntity.ok(user.getUserId() + "님 로그인 되었습니다");


        //return ResponseEntity.ok( "로그인 되었습니다");
    }

}
