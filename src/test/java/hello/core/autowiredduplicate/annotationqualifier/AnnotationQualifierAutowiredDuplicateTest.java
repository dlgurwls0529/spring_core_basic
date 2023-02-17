package hello.core.autowiredduplicate.annotationqualifier;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

public class AnnotationQualifierAutowiredDuplicateTest {
    private final ApplicationContext ac = new AnnotationConfigApplicationContext(
            AnnotationQualifierAutowiredDuplicatedAppConfig.class
    );

    // 퀄리파이어는 문자열이라서 오타나면 컴파일 타임에 못잡는다.
    // 어노테이션으로 만들면 컴파일 타임에 잡을 수 있다.
    @Test
    public void annotationQualifierAutowiredDuplicateFind() {
        Injectee bean = (Injectee) ac.getBean("injectee");
        Assertions.assertInstanceOf(BeanB.class, bean.getBean());
    }

    @Configuration
    @ComponentScan
    public static class AnnotationQualifierAutowiredDuplicatedAppConfig {

    }
}
