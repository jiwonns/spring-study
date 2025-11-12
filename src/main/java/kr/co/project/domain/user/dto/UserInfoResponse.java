package kr.co.project.domain.user.dto;

import kr.co.project.global.jwt.dto.Authority;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserInfoResponse {
    private Long id;
    private String loginId;
    private Authority authority;
}
