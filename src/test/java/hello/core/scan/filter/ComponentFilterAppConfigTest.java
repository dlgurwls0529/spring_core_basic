package hello.core.scan.filter;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.*;

public class ComponentFilterAppConfigTest {

    private final ApplicationContext ac = new AnnotationConfigApplicationContext(ComponentFilterAppConfig.class);

    @Test
    public void filterScanSuccess() {
        Object bean = ac.getBean(BeanA.class);
        Assertions.assertInstanceOf(BeanA.class, bean);
    }

    @Test
    public void filterScanFail() {
        // Object bean = ac.getBean(BeanB.class);
        // 이렇게 하면 not visible constructor 뭐시기 예외 뜨는데,
        // constructor 가 없다. = 생성 제어를 안넘겨받았다. = 빈 등록이 안되었다.
        // 즉, BeanB 가 필터링되어 빈으로 등록되지 않은 듯?

        // 인 줄알았는데, 그냥 설정 클래스를 private static class 로 해서
        // 설정 클래스의 visible 한 생성자. 즉, invisible(private) 생성자밖에 없어서
        // 설정 정보를 못 사용한다던가 그런 듯
        // 특히, 언핸서 쓸 때 예외 터진거 보니까 설정 내부 빈이 없어서 그런게 아니라 그냥
        // 설정을 못 읽었다 그런 이야기 인 듯.

        // public static class 로 바꾸면, 다시 빈 못찾음 예외 던진다.

        /*Assertions.assertThrows(IllegalArgumentException.class,
                new Executable() {
                    @Override
                    public void execute() throws Throwable {
                        Object bean = ac.getBean(BeanB.class);
                    }
                });*/

        Assertions.assertThrows(NoSuchBeanDefinitionException.class,
                new Executable() {
                    @Override
                    public void execute() throws Throwable {
                        Object bean = ac.getBean(BeanB.class);
                    }
                });
    }

    @Configuration
    @ComponentScan(
            includeFilters = @ComponentScan.Filter(
                    type = FilterType.ANNOTATION,
                    classes = MyIncludeComponent.class
            ),
            excludeFilters = @ComponentScan.Filter(
                    type = FilterType.ANNOTATION,
                    // 어노테이션을 인식해서 사용한다.
                    // 기본값이라 안 넣어도 되긴 한다.
                    classes = MyExcludeComponent.class
            )

            // 인클루드 필터 거의 안쓰고, 익스클루드 필터만 가끔 쓴다.
    )
    public static class ComponentFilterAppConfig {

    }
}
