package hello.core.scan.duplicate.auto2manu;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class DuplicateAuto2ManuConfigTest {
    private final ApplicationContext ac = new AnnotationConfigApplicationContext(Auto2ManuConfig.class);

    /*
        다음은 ADI 와 MDI 를 비교한 것입니다.
        복잡성: ADI 는 종속성을 자동으로 검색하고 해결하여 코드를 단순화할 수 있습니다. 개발자는 종속성을 매개변수로 지정하기만 하면 됩니다. 반대로 MDI 는 개발자가 종속성을 수동으로 만들고 관리해야 하므로 더 복잡하고 오류가 발생하기 쉽습니다.
        테스트 용이성: ADI 는 개별 구성 요소를 쉽게 분리하고 테스트할 수 있도록 하여 테스트 가능성을 향상시킬 수 있습니다. 종속성이 매개변수로 전달되기 때문에 개발자는 테스트 목적으로 쉽게 모방하거나 대체할 수 있습니다. MDI 를 사용하면 하드 코딩된 종속성이 있는 구성 요소를 격리하고 테스트하기가 더 어려울 수 있습니다.
        유연성: MDI 는 개발자가 종속성의 생성 및 관리를 완전히 제어할 수 있으므로 더 많은 유연성을 제공합니다. 이는 종속성이 더 복잡하거나 특정 방식으로 구성해야 하는 상황에서 유용할 수 있습니다.
        성능: 프레임워크가 해결된 종속성을 캐시하고 재사용할 수 있으므로 ADI 가 MDI 보다 빠를 수 있습니다. MDI 를 사용하면 개발자는 필요할 때마다 종속성을 만들고 관리해야 하므로 속도가 느려질 수 있습니다.
        요약하면 ADI 는 코드를 단순화하고 테스트 가능성을 향상시킬 수 있는 반면 MDI 는 종속성에 대해 더 많은 유연성과 제어를 제공합니다. ADI 와 MDI 사이의 선택은 프로젝트의 특정 요구 사항과 제약 조건에 따라 달라집니다.
     */

    @Test
    @DisplayName("자동-수동 간 빈 이름 중복이 있다면, 수동을 우선하되, 설정으로 '스프링 부트를 통한' 예외를 터트릴 수 있다.")
    public void auto2manuDuplicateBeanFind() {
        // 예외 뜨면 거기에 끄고 키는거 나와있고, 예외 터트리는 게 디폴트
        Object bean = ac.getBean("myBean");
        Assertions.assertInstanceOf(BeanA.class, bean);
    }

    @Configuration
    public static class Auto2ManuConfig {
        // 방금 알았는데, 빈 별칭은 외부에 붙이는 거랜다.
        // 그래서 내부에서는 결국 메소드 이름으로 관계를 정해야 한다.
        @Bean("myBean")
        public BeanA beanA() {
            return new BeanA();
        }

        @Bean
        public BeanC beanC() {
            return new BeanC(beanA());
        }
    }
}
