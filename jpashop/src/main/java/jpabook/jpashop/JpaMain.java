package jpabook.jpashop;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.domain.Order;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try{
            Order order = em.find(Order.class,1L);
            Long memberId = order.getMemberId();
            em.find(Member.class,memberId); // FK값을 저장했기 떄문에 해당 키로 재조회 해야한다.
            //객체지향스럽지 않다.
            Member member = order.getMember();
            //이 방식이 훨씬 객체지향스럽다.
            tx.commit();
        }catch (Exception e){
            tx.rollback();
        }finally {
            em.close();
        }
        emf.close();
    }
}
