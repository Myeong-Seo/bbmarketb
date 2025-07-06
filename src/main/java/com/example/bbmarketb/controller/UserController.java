package com.example.bbmarketb.controller;

import com.example.bbmarketb.model.User;
import com.example.bbmarketb.repository.UserRepository;
import com.example.bbmarketb.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Map;
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

    @Autowired
    private JwtUtil jwtUtil;

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
        Optional<User> loginUser = userRepo.findByUserId(user.getUserId());

        if (loginUser.isEmpty()) {
            return ResponseEntity.badRequest().body("가입된 아이디가 없습니다.");
        }

        if (!loginUser.get().getPassword().equals(user.getPassword())) {
            return ResponseEntity.badRequest().body("비밀번호가 틀렸습니다.");
        }

        String token = jwtUtil.generateToken(loginUser.get().getUserId());
        return ResponseEntity.ok().body(token); // 토큰 반환
    }

    @GetMapping("/me")
    public ResponseEntity<?> getUserInfo(@RequestHeader("Authorization") String authHeader) {
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return ResponseEntity.badRequest().body("토큰이 없습니다.");
        }

        String token = authHeader.substring(7); // "Bearer " 이후 토큰만 추출

        if (!jwtUtil.validateToken(token)) {
            return ResponseEntity.status(401).body("유효하지 않은 토큰입니다.");
        }

        String userId = jwtUtil.getUserIdFromToken(token);
        Optional<User> user = userRepo.findByUserId(userId);

        if (user.isEmpty()) {
            return ResponseEntity.badRequest().body("사용자 정보를 찾을 수 없습니다.");
        }

        return ResponseEntity.ok(user.get());
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateUserInfo(@RequestBody User updateUser, @RequestHeader("Authorization") String authHeader) {
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return ResponseEntity.status(401).body("토큰이 없습니다.");
        }

        String token = authHeader.substring(7);
        if (!jwtUtil.validateToken(token)) {
            return ResponseEntity.status(401).body("유효하지 않은 토큰입니다.");
        }

        String userId = jwtUtil.getUserIdFromToken(token);
        Optional<User> userOptional = userRepo.findByUserId(userId);

        if (userOptional.isEmpty()) {
            return ResponseEntity.status(404).body("사용자를 찾을 수 없습니다.");
        }

        User user = userOptional.get();
        user.setPassword(updateUser.getPassword());
        user.setPhoneNumber(updateUser.getPhoneNumber());
        user.setAddress(updateUser.getAddress());
        user.setLatest_at(LocalDateTime.now());

        userRepo.save(user);
        return ResponseEntity.ok("회원정보 수정 완료");
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteUser(@RequestHeader("Authorization") String authHeader, @RequestBody Map<String, String> request){
        if(authHeader == null || !authHeader.startsWith("Bearer")){
                return  ResponseEntity.status(401).body("토큰이 없습니다");
        }

        String token = authHeader.substring(7);
        if(!jwtUtil.validateToken(token)){
            return ResponseEntity.status(401).body("유효하지 않은 토큰입니다.");
        }

        String userId = jwtUtil.getUserIdFromToken(token);
        Optional<User> userOptional =userRepo.findByUserId(userId);

        String password = request.get("password");
        User user = userOptional.get();

        if (!user.getPassword().equals(password)) {
            return ResponseEntity.status(403).body("비밀번호가 일치하지 않습니다.");
        }

        userRepo.delete(user);
        return ResponseEntity.ok("회원 탈퇴가 완료되었습니다");
    }

    @PutMapping("/change-password")
    public ResponseEntity<?> changePassword(@RequestHeader("Authorization") String authHeader, @RequestBody Map<String,String> passwordMap){
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return ResponseEntity.status(401).body("토큰이 없습니다.");
        }
        String token = authHeader.substring(7);

        if (!jwtUtil.validateToken(token)) {
            return ResponseEntity.status(401).body("유효하지 않은 토큰입니다.");
        }

        String userId = jwtUtil.getUserIdFromToken(token);
        Optional<User> userOptional = userRepo.findByUserId(userId);

        if (userOptional.isEmpty()) {
            return ResponseEntity.status(404).body("사용자를 찾을 수 없습니다.");
        }

        User user = userOptional.get();
        String currentPassword = passwordMap.get("currentPassword");
        String newPassword = passwordMap.get("newPassword");

        if (!user.getPassword().equals(currentPassword)) {
            return ResponseEntity.status(403).body("현재 비밀번호가 일치하지 않습니다.");
        }

        user.setPassword(newPassword);
        user.setLatest_at(LocalDateTime.now());
        userRepo.save(user);

        return ResponseEntity.ok("비밀번호가 변경되었습니다.");
    }

}
