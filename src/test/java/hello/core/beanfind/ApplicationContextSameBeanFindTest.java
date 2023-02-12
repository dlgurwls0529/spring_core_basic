package hello.core.beanfind;

import hello.core.AppConfig;
import hello.core.member.repository.MemberRepository;
import hello.core.member.repository.MemoryMemberRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

public class ApplicationContextSameBeanFindTest {
    // 인자로 Config 안넘기면 빈 등록 안되니까 오류 뜨겠지
    ApplicationContext ac = new AnnotationConfigApplicationContext(SameBeanConfig.class);

    @Test
    @DisplayName("타입으로 조회 시 같은 타입이 둘 있으면, 중복 오류가 발생한다.")
    void findBeanByTypeDuplicate() {
        Assertions.assertThrows(NoUniqueBeanDefinitionException.class,
                new Executable() {
                    @Override
                    public void execute() throws Throwable {
                        MemberRepository bean = ac.getBean(MemberRepository.class);
                    }
                });
    }

    @Test
    @DisplayName("타입으로 조회 시 같은 타입이 둘 있으면, 빈 이름을 지정하면 된다.")
    public void findBeanByName() {
        MemberRepository memberRepository = ac.getBean("memberRepository1", MemberRepository.class);
        Assertions.assertInstanceOf(MemberRepository.class, memberRepository);
    }

    @Test
    @DisplayName("특정 타입을 모두 조회하기")
    public void findAllBeanByType() {
        Map<String, MemberRepository> map = ac.getBeansOfType(MemberRepository.class);
        for (String s : map.keySet()) {
            System.out.println("key=" + s + " value=" + map.get(s));
        }
        System.out.println("beanOfType=" + map);
        Assertions.assertEquals(2, map.size());
    }

    @Configuration
    static class SameBeanConfig {
        // 빈 이름 다른데 리턴 인스턴스 타입이 둘 다 같을 수도 있다.
        // 가령 Repository 용량이 다른 두 MemoryMemberRepository
        // 를 만들어야 하는 상황을 예로 들 수 있다.
        // 이러면 두 인스턴스를 다른 빈으로 넣어야 하겠지?

        @Bean
        public MemberRepository memberRepository1() {
            return new MemoryMemberRepository();
        }

        @Bean
        public MemberRepository memberRepository2() {
            return new MemoryMemberRepository();
        }
    }
}
