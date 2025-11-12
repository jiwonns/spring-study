package kr.co.project.global.jwt.service;


import kr.co.project.domain.user.entity.User;
import kr.co.project.domain.user.repository.UserRepository;
import kr.co.project.global.enums.statuscode.ErrorStatus;
import kr.co.project.global.exception.GeneralException;
import kr.co.project.global.jwt.dto.AuthUser;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

// userDetails를 생성하여 반환
@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String phoneNum)
            throws UsernameNotFoundException {
        // UserDetails 객체 생성 -> JWT 검증시 사용
        User user = userRepository.findUserByPhoneNum(phoneNum)
                .orElseThrow(() -> new GeneralException(ErrorStatus._MEMBER_NOT_FOUND));
        return new AuthUser(user);
    }
}
