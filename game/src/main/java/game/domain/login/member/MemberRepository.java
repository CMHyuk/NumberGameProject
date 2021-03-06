package game.domain.login.member;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.*;

@Slf4j
@Repository
public class MemberRepository {

    private static Map<Long, Member> store = new HashMap<>();

    private static long sequence = 0L;

    public Member save(Member member) {
        member.setId(++sequence);
        log.info("member={}", member);
        store.put(member.getId(), member);
        return member;
    }

    public void edit(Member member, String password) {
        store.get(member.getId()).setPassword(password);
        store.replace(member.getId(), member);
    }

   public Member findById(Long id) {
        return store.get(id);
    }

    public Optional<Member> findByLoginId(String loginId) {
        return findAll().stream()
                .filter(m -> m.getLoginId().equals(loginId))
                .findFirst();
    }

    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    public Member remove(Long id) {
        return store.remove(id);
    }

}
