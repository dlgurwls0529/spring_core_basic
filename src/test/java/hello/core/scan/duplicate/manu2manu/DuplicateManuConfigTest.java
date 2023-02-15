package hello.core.scan.duplicate.manu2manu;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class DuplicateManuConfigTest {
    private final ApplicationContext ac = new AnnotationConfigApplicationContext(ManuConfig.class);

    @Test
    @DisplayName("수동-수동 빈 간 중복이 발생되면, 먼저 등록된 빈만 등록된다.(싱글톤에 의함)")
    public void manu2manuDuplicateBeanFind() {
        Object bean = ac.getBean("myBean");
        Assertions.assertInstanceOf(BeanA.class, bean);
    }

    @Configuration
    public static class ManuConfig {
        @Bean("myBean")
        public BeanA beanA() {
            return new BeanA();
        }

        @Bean("myBean")
        public BeanB beanB() {
            return new BeanB();
        }
    }
}
