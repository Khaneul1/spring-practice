package hello.hi_servlet.domain.member;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MemberRepository {

    /**
     * 동시성 문제가 고려되어 있지 않음!
     * 실무에서는 ConcurrentHashMap, AtomicLong 사용 고려
     */

    //데이터 저장
    //key == id, value == Member
    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L; //아이디가 1개씩 증가하는 시퀀스로 사용할 것

    //싱글톤으로 만들 것!!
    private static final MemberRepository instance = new MemberRepository();

    public static MemberRepository getInstance(){
        return instance;
    }

    //싱글톤을 만들 때는 private으로 생성자를 막아야 함
    //아무나 생성하지 못하게!!
    private MemberRepository(){
    }

    //save
    public Member save(Member member){
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    //찾는 것
    public Member findById(Long id) {
        return store.get(id); //id로 회원 찾기
    }

    //java.util로 임포트 할 것
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
        //store에 있는 모든 값을 꺼내서 새로운 arraylist에 담아서 넣어 줌
        //new arrayList에 값을 넣거나 외부에서 조작해도
        //store에 있는 value list를 건들고 싶지 않아서 설정하는 것!

        //스토어 자체를 보호하기 위함
    }

    //test용 store 전부 날려버리기
    public void clearStore() {
        store.clear();
    }
}
