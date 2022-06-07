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
        EntityManager em = emf.createEntityManager(); // EntityManager 생성

        EntityTransaction tx = em.getTransaction(); // 트랜젝션 가져오기
        tx.begin(); // 트랜젝션 시작

        try {

            List<Member> result = em.createQuery(
                    "select m From Member m where m.username like '%kim%'",
                    Member.class
            ).getResultList();

            for (Member member : result) {
                System.out.println("member = " + member);
            }

// old1 -> new1
//findMember.getAddressHistory().remove(new AddressEntity("old1", "street", "zipcode"));
// 보통 remove는 내부에서 equals를 사용해서 맞는 객체를 지워주므로, 삭제를 원한다면 똑같은 객체를 만들어서 넣으면 된다.
//findMember.getAddressHistory().add(new AddressEntity("new1", "street", "zipcode"));


/*  객체의 참조와 테이블의 외래키를 매핑
            // 팀 저장
            Team team = new Team();
            team.setName("TeamA");
            em.persist(team); // 이때 team의 id가 정해지면서 team이 저장되었음.

            // 회원 저장
            Member member = new Member();
            member.setUsername("member1");
            member.setTeamId(team.getId()); // 객체지향스럽지 않다. setTeam 해야할 거 같다.
            em.persist(member);

            // 조회
            Member findMember = em.find(Member.class, member.getId());
            // 연관관계 없어서 또 find 해야함.
            Team findTeam = em.find(Team.class, team.getId());
*/




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

//            // 전체 회원 조회
//            List<Member> result = em.createQuery("select m from Member as m", Member.class)
//                    .getResultList(); // JPA는 테이블을 대상으로 코드를 짜지 않는다. Member객체에 대한 코드이다. 대상이 테이블이 아닌 객체임.
//            for (Member member : result) {
//                System.out.println("member.name = " + member.getName());
//            }
//
//            // 페이지네이션 가능
//            List<Member> result1 = em.createQuery("select m from Member as m", Member.class)
//                    .setFirstResult(5)
//                    .setMaxResults(8)
//                    .getResultList();

            /**
             * 영속 컨텍스트
             */

//            // 비영속
//            Member member = new Member();
//            member.setId(100L);
//            member.setName("HelloJPA");
//
//            // 영속
//            em.persist(member);
//
//            // 준영속
//            em.detach(member);
//
//            // 삭제
//            em.remove(member);

            /**
             * 1차 캐시
             */
//            Member member1 = new Member();
//            member1.setId(10L);
//            member1.setName("회원");
//
//            // 영속 컨텍스트 내부의 1차 캐시에 저장됨.
//            em.persist(member1);
//
//            // 1차 캐시에서 조회
//            Member findMember1 = em.find(Member.class, 10L);

            /**
             * 엔티티 등록.
             * 트랜잭션을 지원하는 쓰기 지연
             */
//            Member a = new Member(150L, "A");
//            Member b = new Member(160L, "B");
//
//            em.persist(a);
//            em.persist(b);
//            // 이때까지는 DB에 저장되는 게 아니라, 영속 컨텍스트에 차곡차곡 엔티티와 쿼리가 쌓인다.
//            // 커밋을 하는 시점에 DB에 쿼리가 날아간다.

            /**
             * 엔티티 수정
             * 변경 감지
             */
//            Member member2 = em.find(Member.class, 150L);
//            member2.setName("ZZZZ");


            /**
             * 플러시
             */
//            Member member200 = new Member(200L, "member200");
//            em.persist(member200); // member를 영속 컨텍스트에 저장
//
//            // 트랜잭션이 커밋되기 전까지 해당 쿼리를 볼 수 없다.
//            // 쿼리를 미리 보고싶거나 DB에 미리 반영하고 싶으면 플러시 강제 호출이 가능하다.
//            em.flush();
//
//            /**
//             * 준영속
//             */
//            Member member3 = em.find(Member.class, 150L); // DB 에서 엔티티 가져와서
//            member3.setName("AAA"); // 수정
//
//            em.detach(member3); // 변경사항 반영 안됨.



            tx.commit(); // 트랜젝션 커밋
        } catch (Exception e) {
            tx.rollback(); // 문제가 생기면 트랜젝션 롤백
        } finally {
            em.close(); // entityManager가 내부적으로 DB 커넥션을 물고 동작하므로 사용을 하고난 후에는 꼭 닫아 주어야 한다.
        }

        emf.close(); // 전체 애플리케이션이 끝나면 entityManagerFactory를 닫아준다.
    }

}
