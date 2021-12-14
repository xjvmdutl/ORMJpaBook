package jpabook.jpashop;

import jpabook.jpashop.domain.Book;
import jpabook.jpashop.domain.Member;
import jpabook.jpashop.domain.Order;
import jpabook.jpashop.domain.OrderItem;

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
            /*
            Order order = em.find(Order.class,1L);
            Long memberId = order.getMemberId();
            em.find(Member.class,memberId); // FK값을 저장했기 떄문에 해당 키로 재조회 해야한다.
            //객체지향스럽지 않다.
            Member member = order.getMember();
            //이 방식이 훨씬 객체지향스럽다.
             */
            //Order order = new Order();
            //order.addOrderItem(new OrderItem()); //연관관계편의메서드

            //단방향 연관관계만 해도 개발을 진행하는데 전혀 문제가 없다.
            //이렇게 하면 양방향 관계가 전혀 필요가 없다.
            /*
            Order order = new Order();
            em.persist(order);

            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order);
            em.persist(orderItem);
             */

            Book book = new Book();
            book.setName("JPA");
            book.setAuthor("김영한");
            em.persist(book);
            tx.commit();
        }catch (Exception e){
            tx.rollback();
        }finally {
            em.close();
        }
        emf.close();
    }
}
