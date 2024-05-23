package hellojpa;

import jakarta.persistence.*;

import java.util.Date;

@Entity //JPA가 관리하는 테이블
public class Member {
    @Id @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long id;

    @Column(name = "USERNAME")
    private String username;

    @Column(name = "TEAM_ID")
    private Long teamId;

}
