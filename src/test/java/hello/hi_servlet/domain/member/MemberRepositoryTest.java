package hello.hi_servlet.domain.member;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class MemberRepositoryTest {

    //싱글톤 사용했기 때문에 new 사용 X
    MemberRepository memberRepository = MemberRepository.getInstance();

    @AfterEach
    void afterEach() {
        memberRepository.clearStore();
        //테스트가 끝날 때마다 초기화
    }

    @Test
    void save() {
        //저장

        //given
        //이걸 실행했을 때 되는 결과가 이거여야 해!
        Member member = new Member("hello", 20);

        //when
        Member savedMember = memberRepository.save(member);

        //then
        Member findMember = memberRepository.findById(savedMember.getId());
        assertThat(findMember).isEqualTo(savedMember);
        //찾아온 멤버가 저장된 멤버와 같아야 된다는 의미
    }

    //모든 걸 조회
    @Test
    void findAll(){
        //given
        Member member1 = new Member("member1", 20);
        Member member2 = new Member("member2", 30);

        //미리 저장
        memberRepository.save(member1);
        memberRepository.save(member1);

        //when
        List<Member> result = memberRepository.findAll();

        //then
        assertThat(result.size()).isEqualTo(2);
        assertThat(result).contains(member1, member2); //result 안에 member1과 member2 객체가 들어있는가
    }
}

