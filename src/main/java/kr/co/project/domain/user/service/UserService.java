package kr.co.project.domain.user.service;


import jakarta.transaction.Transactional;
import kr.co.project.domain.user.dto.JoinDto;
import kr.co.project.domain.user.dto.LoginRequestDto;
import kr.co.project.domain.user.entity.User;
import kr.co.project.domain.user.repository.UserRepository;
import kr.co.project.global.enums.statuscode.ErrorStatus;
import kr.co.project.global.exception.GeneralException;
import kr.co.project.global.jwt.dto.Authority;
import kr.co.project.global.jwt.util.JwtUtil;
import kr.co.project.global.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final BCryptPasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    // 로그인
    // TODO 수정 필요
    @Transactional
    public ResponseEntity<?> login(LoginRequestDto dto) {
        String loginId = dto.getLoginId();
        User user = userRepository.findUserByPhoneNum(loginId)
                .orElseThrow(() -> new GeneralException(ErrorStatus._MEMBER_NOT_FOUND));

//        // 비밀번호 검증
//        if(!passwordEncoder.matches(password, user.getPassword())) {
//            throw new GeneralException(ErrorStatus._PASSWORD_NOT_CORRECT);
//        }

        String accessToken = jwtUtil.createJwt(user);
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + accessToken); // JWT 발급 성공시 Header에 삽입하여 반환

        return ResponseEntity.ok().headers(headers)
                .body(ApiResponse.onSuccess("Bearer " + accessToken));
    }

    public ResponseEntity<?> join(JoinDto joinDTO) {
        // 동일 username 사용자 생성 방지
//        if (userRepository.existsUserByLoginId(joinDTO.getLoginId())) {
//            return ResponseEntity.status(HttpStatus.FORBIDDEN)
//                    .body(ApiResponse.onFailure(ErrorStatus._MEMBER_IS_EXISTS, "회원가입에 실패하였습니다."));
//        }

        User user = User.builder()
//                .loginId(joinDTO.getLoginId())
//                .password(passwordEncoder.encode(joinDTO.getPassword())) // 암호화 후 저장
                .authority(Authority.ROLE_USER)
                .build();
        userRepository.save(user);

        String accessToken = jwtUtil.createJwt(user);
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + accessToken);

        return ResponseEntity.ok().headers(headers)
                .body(ApiResponse.onSuccess("Bearer " + accessToken));
    }
}
