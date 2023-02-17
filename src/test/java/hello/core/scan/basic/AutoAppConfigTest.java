package hello.core.scan.basic;

import hello.core.AutoAppConfig;
import hello.core.member.service.MemberService;
import hello.core.member.service.MemberServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AutoAppConfigTest {

    @Test
    public void basicScan() {
        ApplicationContext ac =
                new AnnotationConfigApplicationContext(AutoAppConfig.class);

        MemberService bean = ac.getBean(MemberService.class);
        Assertions.assertInstanceOf(MemberServiceImpl.class, bean);

    }
}
