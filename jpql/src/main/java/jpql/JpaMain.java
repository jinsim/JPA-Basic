package jpql;

import javax.persistence.*;
import java.util.List;

public class JpaMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {

            Team team = new Team();
            team.setName("teamA");
            em.persist(team);

            Member member = new Member();
            member.setUsername("member1");
            member.setAge(10);
            member.changeTeam(team);

            em.persist(member);

            em.flush();
            em.clear();

            String query1 = "select m from Member m join m.team t";
            List<Member> result1 = em.createQuery(query1, Member.class)
                    .getResultList();
            String query2 = "select m from Member m left join m.team t";
            List<Member> result2 = em.createQuery(query2, Member.class)
                    .getResultList();
            String query3 = "select m from Member m, Team t where m.username = t.name";
            List<Member> result3 = em.createQuery(query3, Member.class)
                    .getResultList();



            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }

        emf.close();
    }

}