package hello.core.scan.duplicate.auto2auto;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.springframework.beans.factory.BeanDefinitionStoreException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

public class DuplicateAutoConfigTest {

    @Test
    @DisplayName("자동 빈 간 중복된 이름이 등록되면 컨테이너 띄울 때, 예외 터진다.")
    public void auto2autoDuplicateBeanFind() {
        Assertions.assertThrows(
                BeanDefinitionStoreException.class,
                new Executable() {
                    @Override
                    public void execute() throws Throwable {
                        ApplicationContext ac = new AnnotationConfigApplicationContext(
                                DupAutoConfig.class
                        );
                    }
                });
    }

    @Configuration
    @ComponentScan
    public static class DupAutoConfig {

    }
}
