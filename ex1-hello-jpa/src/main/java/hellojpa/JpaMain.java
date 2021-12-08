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
        }catch (Exception e){
            tx.rollback();
        }finally {
            em.close(); //자원을 꼭 닫아주는것이 중요하다.
        }
        emf.close();
    }
}
