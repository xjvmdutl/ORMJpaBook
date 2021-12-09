package hellojpa;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Member")//테이블 명을 적어줄수 있다
public class Member {
    @Id
    private Long id;

    @Column(name = "name")
    private String name;

    public Member(){//JPA는 기본 생성자가 있어야한다.
    }
    public Member(Long id,String name){
        this.id = id;
        this.name = name;
    }
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
}
