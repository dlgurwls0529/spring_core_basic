package hello.core.beandefinition;

import hello.core.AppConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLOutput;

public class BeanDefinitionFindTest {
    // BeanDefinition 이란 등록된 빈의 메타데이터이다
    // 가령 빈의 범위(싱글턴, 아니면 매번 생성 ..)
    // 아니면 lazy 냐 eager 초기화냐
    // 등등 이 있다.

    // private final ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
    // 위에처럼 하면 BeanDefinition 을 불러올 수 없다.
    // 할려면 BeanDef 관련한 인터페이스를 상속받아야 한다.
    // AppContext 는 상속 안받지만 그 구현체들은
    // BeanDef 관련한 인터페이스를 상속받기 때문에 getBeanDef 를 호출할 수 있다.
    // 이렇게 하는 이유는 굳이 개발할 때 BeanDef 를 건드릴 일이 없기 때문이다.

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    @DisplayName("빈 설정 메타정보 확인")
    public void findBeanDefinition() {
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            BeanDefinition definition = ac.getBeanDefinition(beanDefinitionName);
            if (definition.getRole() == BeanDefinition.ROLE_APPLICATION) {
                System.out.println(
                        "def-name = " + beanDefinitionName +
                        "   " +
                        "def = " + definition
                );
            }
        }
    }
}
