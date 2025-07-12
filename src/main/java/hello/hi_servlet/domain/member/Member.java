package hello.hi_servlet.domain.member;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Member {

    //회원 저장소(리파지토리) 만들면 거기서 id 발행...
    //domain/member/MemberRepository
    private Long id; //식별자 id값
    private String username;
    private int age;

    //기본 생성자
    public Member() {
        
    }
    
    //username과 age를 가지는 생성자
    public Member(String username, int age) {
        this.username = username;
        this.age = age;
    }
}
