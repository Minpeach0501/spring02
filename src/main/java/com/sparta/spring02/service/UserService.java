package com.sparta.spring02.service;

import com.sparta.spring02.dto.SignupRequestDto;
import com.sparta.spring02.model.UserInfo;
import com.sparta.spring02.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }
    public String registerUser(SignupRequestDto requestDto) {
        // 회원 ID 중복 확인
        String username = requestDto.getUsername();
        Optional<UserInfo> found = userRepository.findByUsername(username);
        if (found.isPresent()) {
            System.out.println("중복ID");
            return "중복된 닉네임입니다.";
        }
        if()
        if (requestDto.getPassword().equals(requestDto.getPasswordCheck()) ){
            // 패스워드 암호화
            String password = passwordEncoder.encode(requestDto.getPassword());
            UserInfo user = new UserInfo(username, password);
            userRepository.save(user);
            return "회원가입 완료";
        }
        else {
            return "비밀번호가 일치하지 않습니다.";
        }

    }
}