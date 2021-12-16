import domain.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;
import java.util.Set;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try{
            //domain.Member member = em.find(domain.Member.class,1L);
            //printMemberAndTeam(member);
            //printMember(member);
            /*
            domain.Member member1 = new domain.Member();
            member1.setUsername("hello1");
            em.persist(member1);

            domain.Member member2 = new domain.Member();
            member2.setUsername("hello2");
            em.persist(member2);

            em.flush();
            em.clear();
            */
            /*
            //domain.Member findMember = em.find(domain.Member.class,1L);
            domain.Member findMember = em.getReference(domain.Member.class,member.getId()); //Select쿼리가 안나간다.
            //System.out.println("findMember = " + findMember.getClass()); //프록시 클래스라는걸 찍어준다.
            System.out.println("findMember.id = " + findMember.getId());
            System.out.println("findMember.username = " + findMember.getUsername()); //해당 동작을 해야 Select 쿼리가 나간다.
            System.out.println("findMember.username = " + findMember.getUsername());
            //findMember 가 실제 사용되는 시점에 쿼리가 실행된다.
             */

            //domain.Member m1 = em.find(domain.Member.class,member1.getId());
            //domain.Member m2 = em.getReference(domain.Member.class,member2.getId());
            //logic(m1,m2);
            /*
            domain.Member m1 = em.find(domain.Member.class,member1.getId());

            System.out.println("m1 = "+m1.getClass());

            domain.Member reference = em.getReference(domain.Member.class,member1.getId());
            System.out.println("reference = "+reference.getClass());//domain.Member 클래스가 나온다, 프록시 객체 x
            //1. 이미 영속성 컨텍스트에 있다면 프록시로 얻는 이점이 없기 때문에 Member객체를 반환
            //2. JPA는 같은 트랜젝션안에서의 id가 같다면 항상 같은 객체임을 보장해 주어야 하므로 == 이 성립되어야 하는데, 프록시 객체를 반환하면 ==이 성립이 안되기 떄문에 Member를 반환
            System.out.println("a == a: " + (m1 == reference));
            */
            /*
            domain.Member refMember = em.getReference(domain.Member.class,member1.getId());
            System.out.println("refMember = "+refMember.getClass());

            domain.Member findMember = em.find(domain.Member.class,member1.getId());
            System.out.println("findMember = "+findMember.getClass());
            //findMember 가 프록시가 반환이 된다.
            //em.find를 해도 프록시가 반환이 될 수도 있다는 뜻이다.
            // == 을 맞추기 위해 결국 프록시로 맞춰져서 true가 나온다.
            System.out.println("refMember == findMember: " + (refMember == findMember));
             */
            /*
            domain.Member member1 = new domain.Member();
            member1.setUsername("hello1");
            em.persist(member1);

            em.flush();
            em.clear();

            domain.Member refMember = em.getReference(domain.Member.class,member1.getId());
            System.out.println("refMember = " +refMember.getClass());
            
            //em.detach(refMember);//더이상 영속성컨택스트가 관리 안해줄때
            em.clear();

            refMember.getUsername(); //프록시를 부를때
            */
            /*
            domain.Member member1 = new domain.Member();
            member1.setUsername("hello1");
            em.persist(member1);

            em.flush();
            em.clear();

            domain.Member refMember = em.getReference(domain.Member.class,member1.getId());
            //프록시 클래스 확인 방법
            System.out.println("refMember = " +refMember.getClass());
            refMember.getUsername();
            //프록시 인스턴스 초기화 여부
            System.out.println("isLoaded = " + emf.getPersistenceUnitUtil().isLoaded(refMember));

            Hibernate.initialize(refMember); // 강제 초기화
             */
            /*
            Team team1 = new Team();
            team1.setName("teamA");
            em.persist(team1);

            Team team2 = new Team();
            team2.setName("teamB");
            em.persist(team2);


            Member member1 = new Member();
            member1.setUsername("hello1");
            member1.setTeam(team1);
            em.persist(member1);

            Member member2 = new Member();
            member2.setUsername("hello2");
            member2.setTeam(team2);
            em.persist(member2);

            em.flush();
            em.clear();


             */

            /*
            domain.Member m = em.find(domain.Member.class,member1.getId());
            System.out.println("m = " + m.getTeam().getClass()); //team은 프록시 객체를 반환

            System.out.println("==========");
            System.out.println("teamName = " + m.getTeam().getName());;//이시점에 쿼리 발생
            System.out.println("==========");
            */
            //List<Member> members = em.createQuery("select m from Member m ",Member.class).getResultList();
            //List<Member> members = em.createQuery("select m from Member m join fetch m.team",Member.class).getResultList();
            // jpql이 select쿼리로 실행이 되고, Member를 가지고 온다
            // Member를 가지고 왔더니 Team객체를 즉시로딩으로 호출하므로 N+1 문제가 발생한다.
            /*
            Child child1 = new Child();
            Child child2 = new Child();

            Parent parent = new Parent();
            parent.addChild(child1);
            parent.addChild(child2);

            //부모자식 모두 영속화를 해야 저장이된다.
            //부모를 저장할때 부모, 자식 모두 저장하고 싶다 cascade
            em.persist(parent);
            //em.persist(child1);
            //em.persist(child2);
            em.flush();
            em.clear();

            Parent findParent = em.find(Parent.class,parent.getId());
            //findParent.getChildList().remove(0);
            em.remove(findParent);

             */
            /*
            Member member = new Member();
            member.setUsername("hello");
            member.setHomeAddress(new Address("city","street","zipcode"));
            member.setWorkPeriod(new Period());

            em.persist(member);
             */
            /*
            Address address = new Address("city","street","10000");
            Member member1 = new Member();
            member1.setUsername("member1");
            member1.setHomeAddress(address);
            em.persist(member1);

            Address newAddress = new Address("NewCity",address.getStreet(),address.getZipcode());
            member1.setHomeAddress(newAddress);//값을 통채로 바꿔끼어야된다.

             */
            /*
            Address copyAddress = new Address(address.getCity(),address.getStreet(),address.getZipcode());

            Member member2 = new Member();
            member2.setUsername("member2");
            //member2.setHomeAddress(address); //같은 주소를 쓰고 있다.
            member2.setHomeAddress(copyAddress); //복사를 해서 넣어주어야된다.
            em.persist(member2);
            */
            //member1.getHomeAddress().setCity("newCity"); // 첫번째 맴버의 주소만 newCity로 바꾸고 싶다.
            //Setter를 제거하니 불가능하다
            //만약 같이 공유해서 변경하고 싶다면 Entity를 써야된다.
            /*
            Member member = new Member();
            member.setUsername("member1");
            member.setHomeAddress(new Address("homeCity","street1","10000"));
            member.getFavoriteFoods().add("치킨");
            member.getFavoriteFoods().add("피자");
            member.getFavoriteFoods().add("족발");

            member.getAddressesHistory().add(new AddressEntity("old1","street1","10000"));
            member.getAddressesHistory().add(new AddressEntity("old2","street1","10000"));
            //값타입 컬랙션이기 때문에 생명주기가 Member에 종속적이다.

            em.persist(member);

            em.flush();
            em.clear();

            System.out.println("================Start============");
            Member findMember = em.find(Member.class,member.getId());  //컬렉션들은 기본적으로 지연로딩이다.
            */
            /*
            List<Address> addressHistories = findMember.getAddressesHistory();
            for(Address address : addressHistories){
                System.out.println("address = " + address.getCity());
            }
            Set<String> favoriteFoods = findMember.getFavoriteFoods();
            for(String favoriteFood : favoriteFoods){
                System.out.println("favoriteFood = " + favoriteFood);
            }
            */
            //값타입 같은경우 setter를 이용해서 하면 절대 안된다.//사이드 임펙트 발생된다.
            //통으로 갈아 끼워야된다.
            /*
            Address a = findMember.getHomeAddress();
            findMember.setHomeAddress(new Address("newCity",a.getStreet(),a.getZipcode()));
            //치킨 -> 한식
            findMember.getFavoriteFoods().remove("치킨"); //String 자체를 갈아 끼워야된다.
            findMember.getFavoriteFoods().add("한식");

            findMember.getAddressesHistory().remove(
                    new Address("old1","street1","10000") //equals,hash코드로 값비교를 하기 때문에 잘 구현 해야한다.
            );
            findMember.getAddressesHistory().add(new Address("newCity","street1","10000"));
            */
            Member member = new Member();
            member.setUsername("member1");
            member.setHomeAddress(new Address("homeCity","street1","10000"));
            member.getFavoriteFoods().add("치킨");
            member.getFavoriteFoods().add("피자");
            member.getFavoriteFoods().add("족발");

            member.getAddressesHistory().add(new AddressEntity("old1","street1","10000"));
            member.getAddressesHistory().add(new AddressEntity("old2","street1","10000"));
            //값타입 컬랙션이기 때문에 생명주기가 Member에 종속적이다.

            em.persist(member);

            tx.commit();
        }catch (Exception e){
            tx.rollback();
            e.printStackTrace();
        }finally {
            em.close();
        }
        emf.close();
    }
    
    private static void logic(Member m1,Member m2){
        //System.out.println("m1 == m2 " + (m1.getClass() == m2.getClass()));

        //실제 로직은 매개변수로 프록시객체가 넘어오는지 실제 객체가 넘어오는지 알수 없다.
        //절대 ==을 사용하지 말고, instanceof를 사용하자
        System.out.println("m1 == m2 " + (m1 instanceof Member));
    }
    
    
    
    private static void printMember(Member member) {
        //멤버만 출력하고 싶다.
        //팀은 필요 없음..
        //비지니스 로직마다 필요한것이 다르다.
        //프록시라는 기술로 완벽하게 해결했다.
        System.out.println("member = "+member.getUsername());
    }

    private static void printMemberAndTeam(Member member) {
        //member랑 팀을 조회
        //Member와 팀을 한번에 조회하고 싶다
        String username = member.getUsername();
        System.out.println("username = "+ username);

        //Team team = member.getTeam();
        //System.out.println("team = "+ team.getName());
    }
}
