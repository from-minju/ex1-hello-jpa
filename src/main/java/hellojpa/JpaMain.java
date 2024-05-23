package hellojpa;

import jakarta.persistence.*;

public class JpaMain {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {

            Team team = new Team();
            team.setName("TeamA");
            em.persist(team); //영속 -> 영속상태가 되면 무조건 PK값이 세팅됨. team의 id값

            Member member = new Member();
            member.setUsername("member1");
            member.setTeamId(team.getId()); //외래키 식별자를 직접 다루고 있음.
            em.persist(member);

            //찾아온 멤버가 어느 팀 소속인지 알고싶을 때
            Member findMember = em.find(Member.class, member.getId());
            Long findTeamId = findMember.getTeamId();
            Team findTeam = em.find(Team.class, findTeamId);

            tx.commit();
        }catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}
