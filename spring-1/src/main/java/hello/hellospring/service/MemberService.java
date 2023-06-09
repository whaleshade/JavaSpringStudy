package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

//@Service
public class MemberService {
    private final MemberRepository memberRepository;
//    @Autowired
    public MemberService(MemberRepository memberRepository) {

        this.memberRepository = memberRepository; // 의존성 주입
    }

    /* 회원 가입 */
    public Long join(Member member) {
        validateDuplicateMember(member);

        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {

        Optional<Member> result = memberRepository.findByName(member.getName());
        result.ifPresent(m -> {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        });
    }

    /* 전체 회원 조회 */
    public List<Member> findMembers() {

        return memberRepository.findAll();
    }

    /* 회원 가입 여부 */
    public Optional<Member> findOne(Long memberId) {

        return memberRepository.findById(memberId);
    }
}
