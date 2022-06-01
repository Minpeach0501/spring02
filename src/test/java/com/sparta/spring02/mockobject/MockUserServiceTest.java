package com.sparta.spring02.mockobject;

import com.sparta.spring02.dto.SignupRequestDto;
import org.junit.jupiter.api.*;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;

public class MockUserServiceTest {

    PasswordEncoder passwordEncoder = new PasswordEncoder() {
        @Override
        public String encode(CharSequence rawPassword) {
            return null;
        }

        @Override
        public boolean matches(CharSequence rawPassword, String encodedPassword) {
            return false;
        }
    };

    @Nested
    @DisplayName("정상 케이스")
    class SuccessCases {
        @Test
        @DisplayName("정상 회원가입 케이스1")
        void User() {

            SignupRequestDto requestDto = new SignupRequestDto(
                    "username",
                    "password",
                    "password"
            );

            MockUserService mockUserService = new MockUserService(passwordEncoder);
            // when
            String user = mockUserService.registerUser(requestDto);
            // then
            assertEquals("회원가입 완료", user);
        }

        @Test
        @DisplayName("정상 회원가입 케이스2")
        void User2() {

            SignupRequestDto requestDto = new SignupRequestDto(
                    "name",
                    "word",
                    "word"
            );

            MockUserService mockUserService = new MockUserService(passwordEncoder);
            // when
            String user = mockUserService.registerUser(requestDto);
            // then
            assertEquals("회원가입 완료", user);
        }
    }

    @Nested
    @DisplayName("실패 케이스")
    class FailCases {

        @Nested
        @DisplayName("닉네임 길이조건")
        class UserName_length {

            @Test
            @DisplayName("1")
            void username_length1() {

                SignupRequestDto requestDto = new SignupRequestDto(
                        "ms",
                        "password",
                        "password"
                );

                MockUserService mockUserService = new MockUserService(passwordEncoder);
                // when
                String user = mockUserService.registerUser(requestDto);
                // then
                assertEquals("아이디는 영문대소문자 및 숫자만으로 3글자이상으로 입력해야합니다.", user);
            }
            @Test
            @DisplayName("2")
            void username_length2() {

                SignupRequestDto requestDto = new SignupRequestDto(
                        "js",
                        "password",
                        "password"
                );

                MockUserService mockUserService = new MockUserService(passwordEncoder);
                // when
                String user = mockUserService.registerUser(requestDto);
                // then
                assertEquals("아이디는 영문대소문자 및 숫자만으로 3글자이상으로 입력해야합니다.", user);
            }
        }

        @Nested
        @DisplayName("닉네임 특수문자 조건")
        class UserName_sp {


            @Test
            @DisplayName("1")
            void username_sp1() {

                SignupRequestDto requestDto = new SignupRequestDto(
                        "ms!!",
                        "password",
                        "password"
                );

                MockUserService mockUserService = new MockUserService(passwordEncoder);
                // when
                String user = mockUserService.registerUser(requestDto);
                // then
                assertEquals("아이디는 영문대소문자 및 숫자만으로 3글자이상으로 입력해야합니다.", user);
            }

            @Test
            @DisplayName("2")
            void username_sp2() {

                SignupRequestDto requestDto = new SignupRequestDto(
                        "js.@",
                        "password",
                        "password"
                );

                MockUserService mockUserService = new MockUserService(passwordEncoder);
                // when
                String user = mockUserService.registerUser(requestDto);
                // then
                assertEquals("아이디는 영문대소문자 및 숫자만으로 3글자이상으로 입력해야합니다.", user);
            }

        }

        @Nested
        @DisplayName("비밀번호 길이 조건")
        class PassWord_length {


            @Test
            @DisplayName("1")
            void password_length1() {

                SignupRequestDto requestDto = new SignupRequestDto(
                        "username",
                        "ms",
                        "ms"
                );

                MockUserService mockUserService = new MockUserService(passwordEncoder);
                // when
                String user = mockUserService.registerUser(requestDto);
                // then
                assertEquals("비밀번호는 4글자 이상으로 입력해야 합니다.", user);
            }

            @Test
            @DisplayName("2")
            void password_length2() {

                SignupRequestDto requestDto = new SignupRequestDto(
                        "username",
                        "ps",
                        "ps"
                );

                MockUserService mockUserService = new MockUserService(passwordEncoder);
                // when
                String user = mockUserService.registerUser(requestDto);
                // then
                assertEquals("비밀번호는 4글자 이상으로 입력해야 합니다.", user);
            }

        }

        @Nested
        @DisplayName("비밀번호에 닉네임포함 조건")
        class PassWord_username {


            @Test
            @DisplayName("1")
            void password_username1() {

                SignupRequestDto requestDto = new SignupRequestDto(
                        "name",
                        "namepassword",
                        "namepassword"
                );

                MockUserService mockUserService = new MockUserService(passwordEncoder);
                // when
                String user = mockUserService.registerUser(requestDto);
                // then
                assertEquals("비밀번호에 아이디가 포함될 수 없습니다.", user);
            }

            @Test
            @DisplayName("2")
            void password_username2() {

                SignupRequestDto requestDto = new SignupRequestDto(
                        "name",
                        "username",
                        "username"
                );

                MockUserService mockUserService = new MockUserService(passwordEncoder);
                // when
                String user = mockUserService.registerUser(requestDto);
                // then
                assertEquals("비밀번호에 아이디가 포함될 수 없습니다.", user);
            }

        }

        @Nested
        @DisplayName("중복 닉네임 조건")
        class UserName_overlap {


            @Test
            @DisplayName("1")
            void username_overlap1() {

                SignupRequestDto requestDto = new SignupRequestDto(
                        "name",
                        "password",
                        "password"
                );

                MockUserService mockUserService = new MockUserService(passwordEncoder);
                // when
                mockUserService.registerUser(requestDto);
                String user = mockUserService.registerUser(requestDto);
                // then
                assertEquals("이미 사용중인 아이디입니다.", user);
            }

            @Test
            @DisplayName("2")
            void username_overlap2() {

                SignupRequestDto requestDto = new SignupRequestDto(
                        "minsu",
                        "password",
                        "password"
                );

                MockUserService mockUserService = new MockUserService(passwordEncoder);
                // when
                mockUserService.registerUser(requestDto);
                String user = mockUserService.registerUser(requestDto);
                // then
                assertEquals("이미 사용중인 아이디입니다.", user);
            }

        }
    }
}
