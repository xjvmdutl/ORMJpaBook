package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.time.LocalDateTime;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try{
            //객체지향적인 코드가 아니다.
            //참조가 아닌 ID를 저장하므로 이상하다.
            /*
            Team team = new Team();
            team.setName("TeamA");
            em.persist(team);

            Member member = new Member();
            member.setUsername("member1");
            member.setTeamId(team.getId()); //외례키 식별자를 직접 가지고 있다.
            em.persist(member);

            //조회
            Member findMember = em.find(Member.class,member.getId());
            Long findTeamId = findMember.getTeamId();
            Team findTeam = em.find(Team.class,findTeamId);
            */
            /*
            Team team = new Team();
            team.setName("TeamA");
            //team.getMembers().add(member); //연관관계의 주인이 Member 객체의 team임으로 변경해 주어도 아무런 변화가 없다
            em.persist(team);


            Member member = new Member();
            member.setUsername("member1");
            member.changeTeam(team);
            em.persist(member);

            //team.addMember(member);
            //team.getMembers().add(member);//사실 둘다 넣으면 된다, 읽기전용이라 상관 없기 때문

            //넣어주지 않을경우 발생하는 문제
            //1차 케시에서 가지고 올 때,DB에서 조회하는 것이 아니기 때문에 members가 출력이 안된다.

            Team findTeam = em.find(Team.class,team.getId()); //1차케시
            List<Member> members = findTeam.getMembers();

            System.out.println("=======");
            System.out.println("members = " + findTeam);
            System.out.println("=======");
           */
            /*
            //조회
            Member findMember = em.find(Member.class,member.getId());
            List<Member> members = findMember.getTeam().getMembers();

            for(Member m : members){
                System.out.println("m = " + m.getUsername());
            }
            */
            /*
            Member member = new Member();
            member.setUsername("member1");

            em.persist(member);

            Team team = new Team();
            team.setName("teamA");
            team.getMembers().add(member); //Team 테이블에 insert 될게 아니다.
            //member 테이블을 update 해준다, update 쿼리가 한번 더 실행된다.
            //수십개의 테이블이 엮여있는 실무에서 내 엔티티가 아닌 다른테이블에 값이 넣어지면 유지보수가 힘들어진다.
            em.persist(team);
            */
            /*
            Movie movie = new Movie();
            movie.setDirector("aaaa");
            movie.setActor("bbbb");
            movie.setName("바람과함께사라지다");
            movie.setPrice(100000);
            em.persist(movie);

            em.flush();
            em.clear();

            Item item = em.find(Item.class,movie.getId());
            System.out.println("findItem = " + item);

             */
            Member member = new Member();
            member.setUsername("user1");
            member.setCreateBy("kim");
            member.setCreatedDate(LocalDateTime.now());
            em.persist(member);
            tx.commit();
        }catch (Exception e){
            tx.rollback();
        }finally {
            em.close();
        }
        emf.close();
    }
}
