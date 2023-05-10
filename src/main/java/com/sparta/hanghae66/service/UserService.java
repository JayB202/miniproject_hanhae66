package com.sparta.hanghae66.service;

import com.sparta.hanghae66.dto.ResponseDto;
import com.sparta.hanghae66.dto.TokenDto;
import com.sparta.hanghae66.dto.UserRequestDto;
import com.sparta.hanghae66.entity.RefreshToken;
import com.sparta.hanghae66.entity.User;
import com.sparta.hanghae66.entity.UserRole;
import com.sparta.hanghae66.repository.RefreshTokenRepository;
import com.sparta.hanghae66.repository.UserRepository;
import com.sparta.hanghae66.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static com.sparta.hanghae66.util.JwtUtil.ACCESS_KEY;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final RefreshTokenRepository refreshTokenRepository;
    private static final String ADMIN_TOKEN = "AAABnvxRVklrnYxKZ0aHgTBcXukeZygoC";
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public ResponseDto signup(UserRequestDto requestDto) {
        String userId = requestDto.getUserId();
        String userName = requestDto.getUserName();
        String userPassword = passwordEncoder.encode(requestDto.getUserPassword());
        String userSkill = requestDto.getUserSkill();
        Long userYear = requestDto.getUserYear();
        String userRole = requestDto.getUserRole();

        Optional<User> found = userRepository.findByUserId(userId);

        if (found.isPresent()) {
            return new ResponseDto("아이디 중복", HttpStatus.BAD_REQUEST);
        }

        UserRole role;

        if (userRole.equals("admin")) {
            if (!ADMIN_TOKEN.equals(requestDto.getAdminToken())) {
                return new ResponseDto("토큰값이 일치하지 않습니다.", HttpStatus.BAD_REQUEST);
            }
            role =  UserRole.ADMIN;
        }else{
            role =  UserRole.USER;
        }

        User user = new User(userId, userName, userPassword, userYear, userSkill, role);

        userRepository.save(user);

        return new ResponseDto("회원가입이 성공했습니다.", HttpStatus.OK);
    }


    @Transactional
    public ResponseDto login(UserRequestDto requestDto, jakarta.servlet.http.HttpServletResponse response) {

        String userId = requestDto.getUserId();
        String userPassword = requestDto.getUserPassword();

        try {
            User user = userRepository.findByUserId(userId).orElseThrow(
                    () -> new IllegalArgumentException("없는 ID 입니다.")
            );

            // 비밀번호 확인
            if(!passwordEncoder.matches(userPassword, user.getUserPassword())){
                return new ResponseDto("비밀번호를 확인해주세요!!", HttpStatus.BAD_REQUEST);
            }

            //username (ID) 정보로 Token 생성
            TokenDto tokenDto = jwtUtil.createAllToken(requestDto.getUserId(), user.getRole());

            //Refresh 토큰 있는지 확인
            Optional<RefreshToken> refreshToken = refreshTokenRepository.findByUserId(requestDto.getUserId());

            //Refresh 토큰이 있다면 새로 발급 후 업데이트
            //없다면 새로 만들고 DB에 저장
            if (refreshToken.isPresent()) {
                RefreshToken savedRefreshToken = refreshToken.get();
                RefreshToken updateToken = savedRefreshToken.updateToken(tokenDto.getRefreshToken().substring(7));
                refreshTokenRepository.save(updateToken);
            } else {
                RefreshToken newToken = new RefreshToken(tokenDto.getRefreshToken().substring(7), userId);
                refreshTokenRepository.save(newToken);
            }

            //응답 헤더에 토큰 추가
            setHeader(response, tokenDto);
            return new ResponseDto("성공", HttpStatus.OK);

        } catch (IllegalArgumentException e) {
            return new ResponseDto(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    private void setHeader(jakarta.servlet.http.HttpServletResponse response, TokenDto tokenDto) {
        response.addHeader(ACCESS_KEY, tokenDto.getAccessToken());
    }

    @Transactional(readOnly = true)
    public ResponseDto userCheck(String userId) {
        Optional<User> found = userRepository.findByUserId(userId);
        String namePattern = "^[a-z0-9]+$";
        int chk = userId.length();

        if (found.isPresent()) {
            return new ResponseDto("아이디 중복", HttpStatus.BAD_REQUEST);
        }
        else {
            if (!userId.matches(namePattern)) {
                return new ResponseDto("소문자와 숫자만 입력 가능합니다.", HttpStatus.BAD_REQUEST);
            }
            else if(chk < 4) {
                return new ResponseDto("id 크기는 4 이상, 10 이하만 가능합니다.", HttpStatus.BAD_REQUEST);
            }
            else if(chk > 10) {
                return new ResponseDto("id 크기는 4 이상, 10 이하만 가능합니다.", HttpStatus.BAD_REQUEST);
            }
            else {
                return new ResponseDto("사용가능한 아이디 입니다.", HttpStatus.OK);
            }
        }
    }

    @Transactional
    public ResponseDto logOut(UserRequestDto requestDto) {
        RefreshToken refreshToken = refreshTokenRepository.findByUserId(requestDto.getUserId())
                .orElseThrow( () -> new IllegalArgumentException("리프레시 토큰 없습니다~"));

        refreshTokenRepository.delete(refreshToken);

        return new ResponseDto("로그아웃 성공", HttpStatus.OK);
    }
}
