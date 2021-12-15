package domain;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Entity
public class Member{

    @Id
    @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long id;

    @Column(name = "USERNAME")
    private String username;

    //@ManyToOne(fetch = FetchType.LAZY) //프록시객체만 조회한다는 뜻
    @ManyToOne(fetch = FetchType.EAGER) //즉시로딩
    @JoinColumn(name = "TEAM_ID")
    private Team team;

    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public void changeTeam(Team team) {
        //연관관계편의메소드
        //this.team = team;
        team.getMembers().add(this); //나자신인스턴스를 넣어준다.//실수할 일이 줄어든다.
    }


}
