package hellojpa;

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
            /**
             * JPA를 활용한 CRUD
             */
            // 저장
//            Member member = new Member();
//            member.setId(1L); // Long 값이라 L 붙인 것.
//            member.setName("HelloA");
//            em.persist(member); // member 저장

            // 조회
//            Member findMember = em.find(Member.class, 1L);
//            System.out.println("findMember.id = " + findMember.getId());
//            System.out.println("findMember.name = " + findMember.getName());

            // 삭제
//            Member findMember = em.find(Member.class, 1L);
//            em.remove(findMember)

            // 수정
//            Member findMember = em.find(Member.class, 1L);
//            findMember.setName("HelloJPA");
            // 저장 안하고 그냥 끝내도 된다. 마치 자바 컬렉션을 다루듯이 사용하면 된다.

            /**
             * JPQL
             */
            // 전체 회원 조회
            List<Member> result = em.createQuery("select m from Member as m", Member.class)
                    .getResultList(); // JPA는 테이블을 대상으로 코드를 짜지 않는다. Member객체에 대한 코드이다. 대상이 테이블이 아닌 객체임.
            for (Member member : result) {
                System.out.println("member.name = " + member.getName());
            }

            // 페이지네이션 가능
            List<Member> result1 = em.createQuery("select m from Member as m", Member.class)
                    .setFirstResult(5)
                    .setMaxResults(8)
                    .getResultList();

            /**
             * 영속 컨텍스트
             */

            // 비영속
            Member member = new Member();
            member.setId(100L);
            member.setName("HelloJPA");

            // 영속
            em.persist(member);

            // 준영속
            em.detach(member);

            // 삭제
            em.remove(member);

            /**
             * 1차 캐시
             */
            Member member1 = new Member();
            member.setId("member";
            member.setName("회원");

            // 영속 컨텍스트 내부의 1차 캐시에 저장됨.
            em.persist(member1);

            // 1차 캐시에서 조회
            Member findMember1 = em.find(Member.class, "member1");

            /**
             * 엔티티 등록.
             * 트랜잭션을 지원하는 쓰기 지연
             */



            tx.commit(); // 트랜젝션 커밋
        } catch (Exception e) {
            tx.rollback(); // 문제가 생기면 트랜젝션 롤백
        } finally {
            em.close(); // entityManager가 내부적으로 DB 커넥션을 물고 동작하므로 사용을 하고난 후에는 꼭 닫아 주어야 한다.
        }

        emf.close(); // 전체 애플리케이션이 끝나면 entityManagerFactory를 닫아준다.
    }
}
