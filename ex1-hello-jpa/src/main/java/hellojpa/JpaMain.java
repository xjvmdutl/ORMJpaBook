package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        //Persistence Unit의 이름을 넣는다.

        EntityManager em = emf.createEntityManager();
        //code
        EntityTransaction tx = em.getTransaction();
        tx.begin(); // DB는 트렌젝션이 매우 중요하기때문에 하나의 트렌젝션으로 묶어 주어야 한다.
        try{
            /*
            Member member = new Member();
            member.setId(2L);
            member.setName("helloB");
            */
            //Member findMember = em.find(Member.class,1L);
            //findMember.setName("helloJPA");//해당 멤버를 변경만 하면 값이 자동으로 변화된다,
            //java 객체에서 값만 변경했는데 Update를 해준다.
            //영속성 컨택스트가 관리하는 엔티티는 commit 시점에 더티체크를 하여 바뀐부분을 찾아 update쿼리를 날려준다.

            //em.remove(findMember);//삭제
            /*
            List<Member> result
                    = em.createQuery("select m from Member m",Member.class)
                    .setFirstResult(1) //페이지
                    .setMaxResults(3)
                    .getResultList();
            //JPA 쿼리를 작성할때 테이블이 대상이 아니라 객체를 대상으로 실행해 준다.
            //각 DB에 맞게 페이징을 알아서 변경해서 실행시켜준다.
            for(Member member : result){
                System.out.println(member.getName());
            }
            tx.commit();
             */
            //비영속 상태
            /*
            Member member = new Member();
            member.setId(101L);
            member.setName("HelloJPA");
            */
            //영속상태
            /*
            Member findMember1 = em.find(Member.class,101L);
            Member findMember2 = em.find(Member.class,101L);
            System.out.println("result = " + (findMember1 == findMember2));
            //em.detach(member);
             */
            /*
            Member member1 = new Member(150L,"A");
            Member member2 = new Member(160L,"B");

            em.persist(member1);
            em.persist(member2);

            System.out.println("===============");
            //해당 라인 이후로 쿼리가 발생한다.
            //버퍼기능을 사용할 수가 있다.
             */
            /*
            Member member = em.find(Member.class,150L);
            //찾아온뒤, 영속화 할 필요 없다(이미 영속화 되있기 때문)
            member.setName("ZZZZZ");//값만 변경해 주어도 업데이트 쿼리가 실행된다.
            //JAVA Collection 을 다루듯이 사용할수 있다는 장점을 확인할수 있다.


             */
            /*
            Member member = new Member(200L,"member200");
            em.persist(member);
            em.flush();//강제로 영속성컨택스트를 flush하기 떄문에 쿼리가 실행된다.

            System.out.println("=================");
            */
            Member member1 = em.find(Member.class,150L); //영속 상태
            member1.setName("AAAAA");
            //em.detach(member);//JPA가 더이상 관리하지 않는다
            em.clear();//모든 영속성 컨택스트 비워줌
            Member member2 = em.find(Member.class,150L); 
            //쿼리가 2번 실행해줌 /영속성 컨택스트를 비워줫기 떄문
            tx.commit();
        }catch (Exception e){
            tx.rollback();
        }finally {
            em.close(); //자원을 꼭 닫아주는것이 중요하다.
        }
        emf.close();
    }
}
