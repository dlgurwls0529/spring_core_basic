package hello.core.autowiredduplicate.primary;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

public class PrimaryAutowiredDuplicateTest {
    private final ApplicationContext ac = new AnnotationConfigApplicationContext(
            PrimaryAutowiredDuplicateAppConfig.class
    );

    // 퀄리파이어 없음 -> 오토와이어드 타입 중복 -> 그 중 프라이머리 선택
    // 프라이머리는 꽤 많이 쓴다.
    // 가령, 메인디비저장소(90프로 정도 씀)랑 서브디비저장소(10프로..)의 빈이 있다고 하자
    // 같은 타입(인터페이스)인데, 둘 다 쓰긴 해야 하니까 빈으로 등록은 해야하고
    // 각자 다른 것으로 하기는 DI 쓰는 의미가 없고(=구체 타입 검색)
    // 그래서 일단 메인디비를 Primary 를 걸어놓고, 서브디비를 퀄리파이어 걸어놓고 필요할 때마다
    // 퀄리파이어를 적용하는 식으로 사용한다.
    // 그러면, 메인디비 바꿀라 치면 새로운 클래스 만들고, 컴포넌트 어노테이션 걸고, 프라이머리 걸면
    // 디비 사용 측 코드는 하나도 안바뀐다.
    // 서브디비도 마찬가지
    // 누차 말하지만 핵심은 사용 측 코드가 안바뀌는거
    @Test
    @DisplayName("오토와이어드 타입 중복 발생했으니, 프라이머리 주입")
    public void primaryAutowiredDuplicateFind() {
        Injectee bean = (Injectee)ac.getBean("injectee");
        Assertions.assertInstanceOf(BeanA.class, bean.getBean());
    }

    @ComponentScan
    @Configuration
    public static class PrimaryAutowiredDuplicateAppConfig {

    }
}
