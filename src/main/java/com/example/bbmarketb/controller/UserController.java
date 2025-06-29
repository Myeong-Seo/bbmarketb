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

        if(loginUser.isEmpty()){
            return ResponseEntity.ok("가입된 아이디가 없습니다.");
        }
        else if((loginUser.isPresent())&&(!loginUser.get().getPassword().equals(user.getPassword()))){
                return ResponseEntity.ok("비밀번호가 틀렸습니다.");
        }
        else {     // 4. 로그인 성공
            return ResponseEntity.ok(loginUser.get().getUserId() + "님 로그인 되었습니다");
        }
    }
//로그인 - 메인페이지 이동 - 회원정보 버튼 누르면 db에서 회원정보 가져오기
    /*
    @GetMapping("/me")
    public ResponseEntity<?> getUserInfo(@RequestParam String userId){
        Optional<User> user = userRepository.findByUserId(userId);

        if(user.isEmpty()){
            return ResponseEntity.badRequest().body("사용자 정보를 찾을 수 없습니다");
        }
        return ResponseEntity.ok(user.get());
    }
*/

    @PostMapping("/me")
    public ResponseEntity<?> updateUserInfo(@RequestBody User updateUser){
        Optional<User> userinfo = userRepository.findByUserId(updateUser.getUserId());

        if(userinfo.isEmpty()){
            return ResponseEntity.badRequest().body("사용자가 존재하지 않습니다");
        }

        User editinfo = userinfo.get();
        editinfo.setPassword(updateUser.getPassword());
        editinfo.setAddress(updateUser.getAddress());
        editinfo.setPhoneNumber(updateUser.getPhoneNumber());

        userRepository.save(editinfo);
        System.out.println(editinfo);
        return ResponseEntity.ok("회원 정보가 수정되었습니다.");
    }
}
