package jpql;


import javax.persistence.*;
import java.util.Collection;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try{
            /*
            Member member = new Member();
            member.setUsername("member1");
            member.setAge(10);
            em.persist(member);
            em.flush();
            em.clear();
            */
            /*
            //타입 정보가 명확하므로 Member를 받을수 있지만 타입정보가 명확하지 않을 때가 있다.
            TypedQuery<Member> query1 = em.createQuery("select m from Member m",Member.class);//2번쨰 파라미터로 타입정보를 줄수 있다.
            TypedQuery<String> query2 = em.createQuery("select m.username from Member m",String.class);
            //반환 타입이 명확하지 않으므로 Query 사용
            Query query3 = em.createQuery("select m.username, m.age from Member m");
            */
            /*
            TypedQuery<Member> query = em.createQuery("select m from Member m",Member.class);
            Member result = query.getSingleResult();
            //Spring Data JPA -> 값이 없을떄 에러가 나지 않게 한다.
            System.out.println("result = " + result);
             */
            /*
            Member singleResult = em.createQuery("select m from Member m where m.username = :username",Member.class)
                    .setParameter("username","member1")
                    .getSingleResult();
            System.out.println("singleResult = " + singleResult.getUsername());
            */
            //조회된 데이터는 영속성 컨택스트에 관리된다
            /*
            List<MemberDto> result = em.createQuery("select new jpql.MemberDto(m.username,m.age) from Member m"
                    ,MemberDto.class)
                    .getResultList();
            MemberDto memberDto = result.get(0);
            System.out.println("memberDto = " + memberDto.getUsername());
            System.out.println("memberDto = " + memberDto.getAge());
            //값타입 이기 때문에 이건 불가능
            //em.createQuery("select a from Address a",Address.class).getResultList();
            */
            /*
            for(int i=0;i<100;++i){
                Member member = new Member();
                member.setUsername("member" + i);
                member.setAge(i);
                em.persist(member);
            }
            List<Member> resultList = em.createQuery("select m from Member m order by m.age desc", Member.class)
                    .setFirstResult(1)
                    .setMaxResults(10)
                    .getResultList();
            System.out.println("resultList = " + resultList.size());
            for(Member member1 : resultList){
                System.out.println("member1 = " + member1);
            }
             */
            /*
            Team team = new Team();
            team.setName("teamA");
            em.persist(team);

            Member member = new Member();
            member.setUsername("관리자");
            member.setAge(10);
            member.setMemberType(MemberType.ADMIN);
            member.changeTeam(team);

            em.persist(member);

             */
            /*
            //inner,outer는 생략가능
            List<Member> resultList = em.createQuery("select m from Member m left join Team t on m.username = t.name", Member.class)
                    .getResultList();
            System.out.println("result = "+ resultList.size());
             */
            /*
            String query = "select (select avg(m1.age) from Member m1) as avg from Member m join Team t on m.username = t.name";
            List<Member> resultList = em.createQuery("select m from Member m left join Team t on m.username = t.name", Member.class)
                    .getResultList();
             */
            /*
            em.createQuery("select i from Item i where type(i) = 'book'")
                    .getResultList();

            List<Object[]> resultList = em.createQuery("select m.username,'Hello',TRUE from Member m where m.memberType = :userType")
                    .setParameter("userType",MemberType.ADMIN)
                    .getResultList();
            for(Object[] objects : resultList){
                System.out.println("object[0] = " + objects[0]);
                System.out.println("object[1] = " + objects[1]);
                System.out.println("object[2] = " + objects[2]);
            }
             */
           /*
           String query = "select "
                         + "       case when m.age <= 10 then '학생요금'"
                         + "            when m.age >= 60 then '경로요금'"
                         + "            else '일반요금' "
                         + " end "
                         + "from Member m";
             */
            /*
            String query = "select nullif(m.username,'관리자') "
                    +" from Member m";
            List<String> result = em.createQuery(query,String.class)
                            .getResultList();
            for(String s : result) {
                System.out.println(s);
            }
             */
            /*
            String query = "select function('group_concat',m.username) "
                    +" from Member m";
            List<String> result = em.createQuery(query,String.class)
                    .getResultList();
            for(String s : result) {
                System.out.println(s);
            }
             */
            /*
            String query = "select m.team "
                    +" from Member m"; //묵시적 내부조인이 발생
             */
            /*
            String query = "select m.user " // 1대다 관계
                    +" from Team t join t.members m"; //명시적 조인으로 별칭을 주어 다시 사용할 수 있다.
            List<Collection> result = em.createQuery(query, Collection.class)
                    .getResultList();
            System.out.println(result);
             */


            Team teamA = new Team();
            teamA.setName("teamA");
            em.persist(teamA);

            Team teamB = new Team();
            teamB.setName("teamB");
            em.persist(teamB);

            Member member1 = new Member();
            member1.setUsername("회원1");
            member1.changeTeam(teamA);
            em.persist(member1);

            Member member2 = new Member();
            member2.setUsername("회원2");
            member2.changeTeam(teamA);
            em.persist(member2);

            Member member3 = new Member();
            member3.setUsername("회원3");
            member3.changeTeam(teamB);
            em.persist(member3);

            em.flush();
            em.clear();
            /*
            String query = "select m "
                    +" from Member m join fetch m.team";
            List<Member> result = em.createQuery(query, Member.class)
                    .getResultList();
            for(Member member : result) {
                System.out.println(member.getUsername() + ", " + member.getTeam().getName());
                //회원1, 팀A(SQL)
                //회원2, 팀A(1차캐시)
                //회원3, 팀B(SQL)
                //회원 100명 -> N+1 
                //fetch join 으로 해결할수 밖에 없다
            }
             */
            /*
            String query = "select t "
                    +" from Team t join fetch t.members";
            List<Team> result = em.createQuery(query, Team.class)
                    .getResultList();
            for(Team team : result){
                System.out.println(team.getName() + " | " + team.getMembers().size());
                //데이터가 뻥튀기 된다.
                for(Member member : team.getMembers()){
                    System.out.println("->member :" + member);
                }
            }
             */
            /*
            String query = "select distinct t "
                    +" from Team t join fetch t.members";
            List<Team> result = em.createQuery(query, Team.class)
                    .getResultList();
            for(Team team : result){
                System.out.println(team.getName() + " | " + team.getMembers().size());
                for(Member member : team.getMembers()){
                    System.out.println("->member :" + member);
                }
            }
             */


            //alias 는 주면 안된다. //연관된 데이터를 모두 가지고 올때 사용하기 때문이다.
            //여러개를 join fetch 할때만 쓴다
            /*
            String query = "select t "
                    +" from Team t";
            List<Team> result = em.createQuery(query, Team.class)
                    .setFirstResult(0)
                    .setMaxResults(2)
                    .getResultList();
            for(Team team : result){
                System.out.println(team.getName() + " | " + team.getMembers().size());
                for(Member member : team.getMembers()){
                    System.out.println("->member :" + member);
                }
            }
             */
            /*
            String query = "select m "
                    +" from Member m where m = :member";
             */
            /*
            String query = "select m "
                    +" from Member m where m.team = :team";

            List<Member> result = em.createQuery(query, Member.class)
                    .setParameter("team",teamA)
                    .getResultList();
            for(Member member : result) {
                System.out.println(result);
            }
             */
            /*
            List<Member> result = em.createNamedQuery("Member.findByUsername")
                    .setParameter("username","회원1")
                    .getResultList();
            for(Member member : result){
                System.out.println(member);
            }
            */

            //FLUSH 자동 호출
            //WHY? flush가 Auto모드 일때, Query,commit 시점에
            int resultCount = em.createQuery("update Member m set m.age = 20")
                    .executeUpdate();
            em.clear(); //clear로 영속성 컨텍스트를 비워준뒤 다시 가지고 와야된다.
            Member findMember = em.find(Member.class,member1.getId());
            System.out.println(findMember.getAge());//데이터 적합성이 안맞기 떄문에 다시 맞춰줘야된다.

            System.out.println(resultCount);
            tx.commit();

        }catch (Exception e){
            tx.rollback();
            e.printStackTrace();
        }finally {
            em.close();
        }
        emf.close();
    }

}
