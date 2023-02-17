package hello.core.autowiredduplicate.qualifier;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

public class QualifierAutowiredDuplicateTest {
    private final ApplicationContext ac = new AnnotationConfigApplicationContext(
            QualifierAutowiredDuplicatedAppConfig.class
    );

    // 우선 순위는 퀄리파이어 -> 프라이머리 -> 베이직 오토와이어드
    // 퀄리파이어는 퀄리파이어끼리 된다.
    // 무슨 말이냐면, 빈에 퀄리파이어 걸었다고 막 이름 바껴서 그걸로 검색하면 되는 것이 아니라
    // 이름이 바뀌지는 않기에, 퀄리파이어 등록은 퀄리파이어로 검색해야만 의미가 있다.
    // 아무튼 그래서 BeanB 가 뜬다.
    @Test
    @DisplayName("퀄리파이어 있으니까 일단 퀄리파이어 타고 간다.")
    public void qualifierAutowiredDuplicateFind() {
        Injectee bean = (Injectee) ac.getBean("injectee");
        Assertions.assertInstanceOf(BeanB.class, bean.getBean());
    }

    @Configuration
    @ComponentScan
    public static class QualifierAutowiredDuplicatedAppConfig {

    }
}
