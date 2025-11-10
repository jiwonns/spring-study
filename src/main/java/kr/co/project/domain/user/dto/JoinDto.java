package kr.co.project.domain.user.dto;

import kr.co.project.global.jwt.dto.Authority;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JoinDto {
    private String loginId;
    private String password;
    private Authority authority;
}
