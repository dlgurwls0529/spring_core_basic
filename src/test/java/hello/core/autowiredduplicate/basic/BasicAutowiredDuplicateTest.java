package hello.core.autowiredduplicate.basic;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

public class BasicAutowiredDuplicateTest {
    private final ApplicationContext ac = new AnnotationConfigApplicationContext(
            BasicAutowiredDuplicateAppConfig.class
    );

    @Test
    @DisplayName("같은 타입 먼저 하고, 여러개니까 매개변수 이름 beanA 로 해서 찾는다.")
    public void basicAutowiredDuplicateFind() {
        Injectee bean = (Injectee) ac.getBean("injectee");
        Assertions.assertInstanceOf(BeanA.class, bean.getBeanA());
    }

    @ComponentScan
    @Configuration
    public static class BasicAutowiredDuplicateAppConfig {

    }
}
