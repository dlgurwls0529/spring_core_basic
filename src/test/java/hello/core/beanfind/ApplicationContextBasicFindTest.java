package hello.core.beanfind;

import hello.core.AppConfig;
import hello.core.member.service.MemberService;
import hello.core.member.service.MemberServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ApplicationContextBasicFindTest {
    ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    @DisplayName("빈 이름으로 조회하기")
    public void findBeanByName() throws ClassNotFoundException {
        Object bean = ac.getBean("memberService", MemberService.class);

        Class<?> c = Class.forName("hello.core.member.service.MemberServiceImpl");

        // 밑에 껄로 하면 안됨. 경로까지 쳐줘야 찾을 수 있다!
        // c = Class.forName("MemberServiceImpl");

        Assertions.assertInstanceOf(c, bean);
    }

    @Test
    @DisplayName("빈 타입으로 조회")
    public void findBeanByType() {
        MemberService bean = ac.getBean(MemberService.class);
        // actual 에는 인스턴스가 와야 한다.
        // 클래스 메타데이터 넣으면 안됨

        // 같은 타입 여러개일 수 있으니 이름 타입 같이 하는 게 좋다.
        Assertions.assertInstanceOf(MemberServiceImpl.class, bean);
    }

    @Test
    @DisplayName("빈 이름으로 조회X")
    public void findBeanNameX() {
        Executable executable = new Executable() {
            @Override
            public void execute() throws Throwable {
                ac.getBean("xxxx", MemberService.class);
            }
        };

        Assertions.assertThrows(
                NoSuchBeanDefinitionException.class, executable
        );
    }
}