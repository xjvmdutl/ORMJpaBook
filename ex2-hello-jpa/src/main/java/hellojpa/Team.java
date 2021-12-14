package hellojpa;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Team extends BaseEntity{
    @Id
    @GeneratedValue
    @Column(name = "TEAM_ID")
    private Long id;

    private String name;

    /*
    @OneToMany(mappedBy = "team") //mappedBy로 어떤것과 연관되어 있는지를 적어주어야 된다.
    @JoinColumn(name = "TEAM_ID")
    private List<Member> members = new ArrayList<>();//관례로 생성시 만들어 놓는다.
    */

    @OneToMany
    @JoinColumn(name = "TEAM_ID")
    private List<Member> members = new ArrayList<>();//관례로 생성시 만들어 놓는다.




    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Member> getMembers() {
        return members;
    }

    public void setMembers(List<Member> members) {
        this.members = members;
    }
    /*
    연관관계편의 메소드는 한쪽에 있어야 한다.
    public void addMember(Member member) {
        member.setTeam(this);
        members.add(member);
    }

     */

    @Override
    public String toString() {
        return "Team{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", members=" + members +
                '}';
    }
}
