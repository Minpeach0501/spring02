package com.sparta.spring02.mockobject;

import com.sparta.spring02.model.UserInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MockUserRepository {

    private List<UserInfo> users = new ArrayList<>();
    // 상품 테이블 ID: 1부터 시작
    private Long userId = 1L;

    // 회원정보 저장
    public UserInfo save(UserInfo user) {
        // 신규 회원 -> DB 에 저장
        user.setId(userId);
        ++userId;
        users.add(user);
        return user;
    }

    // username 으로 정보 조회
    public Optional<UserInfo> findByUsername(String username) {
        for (UserInfo user : users) {
            if (user.getUsername().equals(username)) {
                return Optional.of(user);
            }
        }

        return Optional.empty();
    }
}