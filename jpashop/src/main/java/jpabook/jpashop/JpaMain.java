package jpabook.jpashop;
import jpabook.jpashop.domain.Member;
import jpabook.jpashop.domain.Order;
import jpabook.jpashop.domain.OrderItem;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager(); // EntityManager 생성

        EntityTransaction tx = em.getTransaction(); // 트랜젝션 가져오기
        tx.begin(); // 트랜젝션 시작

        try {

            Order order = new Order();
            order.addOrderItem(new OrderItem())

            tx.commit(); // 트랜젝션 커밋
        } catch (Exception e) {
            tx.rollback(); // 문제가 생기면 트랜젝션 롤백
        } finally {
            em.close(); // entityManager가 내부적으로 DB 커넥션을 물고 동작하므로 사용을 하고난 후에는 꼭 닫아 주어야 한다.
        }

        emf.close(); // 전체 애플리케이션이 끝나면 entityManagerFactory를 닫아준다.
    }
}
