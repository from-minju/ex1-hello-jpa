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
            member.setTeam(team);
            em.persist(member);

            //1차캐시에서 가져오기때문에 select문 없음 -> db쿼리 직접 보고싶을땐 어떻게 하지??
            //-> flush강제호출 - 영속성 컨텍스트에 있는걸 db쿼리 다 날려버리고 clear로 영속성컨텍스트 초기화
            em.flush();
            em.clear();

            //찾아온 멤버가 어느 팀 소속인지 알고싶을 때
            Member findMember = em.find(Member.class, member.getId());
            Team findTeam = findMember.getTeam();
            System.out.println("findTeam.getName() = " + findTeam.getName());

            tx.commit();
        }catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}
