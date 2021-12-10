package hellojpa;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;


//@Table(name = "Member")//테이블 명을 적어줄수 있다
/*
@TableGenerator(
        name = "MEMBER_SEQ_GENERATOR",
        table = "MY_SEQUENCES",
        pkColumnValue = "MEMBER_SEQ",
        allocationSize = 1
)
*/
@SequenceGenerator(
        name = "MEMBER_SEQ_GENERATOR",
        sequenceName = "MEMBER_SEQ", //매핑할 데이터베이스 시퀀스 이름
        initialValue = 1,
        allocationSize = 50)
@Entity
public class Member {

    //@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "member_seq_generator")//기본값 = auto(DB 방언에 맞춰 알아서 생성)
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    // @GeneratedValue(strategy = GenerationType.TABLE, generator = "MEMBER_SEQ_GENERATOR")
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE
                    ,generator = "MEMBER_SEQ_GENERATOR")
    private Long id;

    @Column(name = "name",nullable = true,length = 10)
    private String username;

    private Integer age;

    @Enumerated(EnumType.STRING) //DB에는 ENUM 타입이 없기 때문에 Enumerated 어노테이션을 사용한다.
    private RoleType roleType;

    @Temporal(TemporalType.TIMESTAMP) //날짜와 관련된 메핑
    private Date createdDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModifiedDate;

    //hibernate 최신버젼을 쓰게되면 LocalData,LocalDateTime,Date 을 사용하면 된다.
    private LocalDateTime testLocalDateTime;

    private LocalDate testLocalDate;

    @Lob  //varchar를 넘어서는 큰 컨텐츠를 넣을때 사용한다.
    private String description;

    //DB랑 신경쓰지 않고 싶은 데이터를 가지고 싶을때
    @Transient
    private int temp;

    public Member(){
    }

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

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public RoleType getRoleType() {
        return roleType;
    }

    public void setRoleType(RoleType roleType) {
        this.roleType = roleType;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
