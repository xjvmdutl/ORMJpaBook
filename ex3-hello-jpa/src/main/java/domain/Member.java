package domain;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;


@Entity
public class Member{

    @Id
    @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long id;

    @Column(name = "USERNAME")
    private String username;
    /*
    //@ManyToOne(fetch = FetchType.LAZY) //프록시객체만 조회한다는 뜻
    @ManyToOne(fetch = FetchType.EAGER) //즉시로딩
    @JoinColumn(name = "TEAM_ID")
    private Team team;
    */
    /*
    //기간 , Period
    @Embedded
    private Period workPeriod;

    //주소 Address
    @Embedded
    private Address homeAddress;

    @Embedded
    @AttributeOverrides({
        @AttributeOverride(
                name = "city",
                column = @Column(name = "WORK_CITY")
        ),@AttributeOverride(
            name = "street",
            column = @Column(name = "WORK_STREET")
        ),@AttributeOverride(
            name = "zipcode",
            column = @Column(name = "WORK_ZIPCODE")
    )})
    private Address workAddress = null;
    */

    @Embedded
    private Address homeAddress;

    @ElementCollection
    @CollectionTable(name = "FAVORITE_FOOD" ,
            joinColumns = @JoinColumn(name = "MEMBER_ID"))
    @Column(name = "FOOD_NAME") //하나라서 가능하다, 예외적으로 가능한 케이스
    private Set<String> favoriteFoods = new HashSet<>();
    /*
    @ElementCollection
    @CollectionTable(name = "ADDRESS" ,
            joinColumns = @JoinColumn(name = "MEMBER_ID"))
    //@OrderColumn(name = "address_history_order") //이것도 위험하다, 중간값이 빠질수 있기때문에
    private List<Address> addressesHistory = new ArrayList<>();
    */

    @OneToMany(cascade = CascadeType.ALL,orphanRemoval = true)
    @JoinColumn(name = "MEMBER_ID")
    private List<AddressEntity> addressesHistory = new ArrayList<>();

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

    public Address getHomeAddress() {
        return homeAddress;
    }

    public void setHomeAddress(Address homeAddress) {
        this.homeAddress = homeAddress;
    }

    public Set<String> getFavoriteFoods() {
        return favoriteFoods;
    }

    public void setFavoriteFoods(Set<String> favoriteFoods) {
        this.favoriteFoods = favoriteFoods;
    }


    public void changeTeam(Team team) {
        //연관관계편의메소드
        //this.team = team;
        team.getMembers().add(this); //나자신인스턴스를 넣어준다.//실수할 일이 줄어든다.
    }

    public List<AddressEntity> getAddressesHistory() {
        return addressesHistory;
    }

    public void setAddressesHistory(List<AddressEntity> addressesHistory) {
        this.addressesHistory = addressesHistory;
    }
}
