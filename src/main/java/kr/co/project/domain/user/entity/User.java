package kr.co.project.domain.user.entity;

import jakarta.persistence.*;
import kr.co.project.global.entity.BaseEntity;
import kr.co.project.global.jwt.dto.Authority;
import lombok.*;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "tb_user")
public class User extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;
    private String name;
    private String phoneNum; // 전화번호
    private String nickName;
    private Integer profileNum;
    private Integer ticketCnt; // 티켓 수(감정 포인트 확인용)
    private Integer attendancePoint;

    @Enumerated(EnumType.STRING)
    private Authority authority;
}
