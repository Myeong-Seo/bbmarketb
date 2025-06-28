package com.example.bbmarketb.controller;

import com.example.bbmarketb.model.User;
import com.example.bbmarketb.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Optional;

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

    LocalDateTime now = LocalDateTime.now();


    //localhost:5173/api/user/signup
    @PostMapping("/signup")
    public ResponseEntity<?> Signup(@RequestBody User user) {
        try {
            if (userRepo.existsByUserId(user.getUserId())) {
                return ResponseEntity.badRequest().body("가입된 아이디가 이미 있습니다.");
            }
            User saveUser = new User();
            saveUser.setUserId(user.getUserId());
            saveUser.setPassword(user.getPassword());
            saveUser.setName(user.getName());
            saveUser.setPhoneNumber(user.getPhoneNumber());
            saveUser.setAddress(user.getAddress());
            saveUser.setCreate_at(now);
            saveUser.setLatest_at(now);
            saveUser.setRole("일반회원");
            saveUser.setStatus("Active");
            System.out.println("UserId: " + user.getUserId());
            System.out.println("Password: " + user.getPassword());
            userRepo.save(saveUser);
            return ResponseEntity.ok("회원가입이 완료되었습니다.");
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(500).body("에러 발생");
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {
        User loginUserId = userRepo.findByUserId(user.getUserId()).orElseThrow();
        User loginUserPassword = userRepo.findByPassword(user.getPassword()).orElseThrow();

        Optional<User> loginU =  userRepo.findByUserId(loginUserId.getUserId());
        Optional<User> loginP =  userRepo.findByPassword(loginUserId.getPassword());
        if(loginU.isPresent() && loginP.isPresent()) {

            return ResponseEntity.badRequest().body("ID ---");

        }
        else{

        }
        if(!loginUserId.getUserId().equals(user.getUserId()) && !loginUserPassword.getPassword().equals(user.getPassword())){
            return ResponseEntity.badRequest().body("가입된 아이디가 없습니다.");
        }

        return ResponseEntity.ok("회원님 로그인 되었습니다");
    }

}
