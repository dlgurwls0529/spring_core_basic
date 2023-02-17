package hello.core;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

// 이거 붙은거에 싱글턴 코드 삽입
@Configuration
// 컴포넌트 스캔 방식으로 빈 등록
@ComponentScan(
        excludeFilters = @ComponentScan.Filter(
                type = FilterType.ANNOTATION,
                classes = Configuration.class
        ),

        // 이거 안하면 테스트 빈 다 읽어서 막 충돌처리했던것도 읽어서 터진다.
        basePackages = {
                "hello.core.discount",
                "hello.core.member",
                "hello.core.order"
        }
        // Configuration 도 상위에 Component 가 있어서
        // 이거 안하면 설정 정보인 AppConfig 와 그 빈들이 다 등록된다.
        // 이러면 충돌 나니까 여기에서는 AppConfig 안읽을라고
        // Configuration 은 필터링하는 것이다.

        //, basePackages = "main.java.hello.core"
        // 이거는 탐색하는 패키지의 시작 위치
        // 이 패키지를 포함해서 모든 하위 패키지를 다 뒤진다.

        // , basePackageClasses = hello.core.AutoAppConfig.class
        // 이거는 이 클래스가 속한 패키지를 시작 위치로 하는 것

        // 만약 저것들로 지정 안하면, 베이스 패키지에 설정 정보를 넣어서 수행한다.
        // 그니까 설정 정보가 속한 패키지부터 다 뒤진다는 것이다.
        // 이거 기준으로는 AppConfig, CoreApplication, discount ...
        // 이렇게 지정하지 않는 것을 추천한다고 한다.

        // 생성자 주입이 가장 좋다.
        // 예를 들면 공연을 할 때 배역을 다 정하고 공연을 해야지
        // 하다가 막 배역이 바뀔 수 있게 하면 안되지 않느냐
        // 보통 어플리케이션 개발에서 빈 간 의존관계가 동적으로
        // 바뀔 상황이 없을 뿐더러, 위에 예시가 말하듯이,
        // 그러한 설계는 바람직하지 않다고 한다.(실수로 변경 가능성 높다, 가변적)

        // 애초에 DI 자체가 '필드 주입' 이 아니라 '생성' 제어를 위임하는 것이니
        // 당연히 생성자가 적절하다. 그리고 빌더 패턴의 배경처럼
        // 완전히 사용자가 이 객체 정보를 모르는 경우 결함이 있는 객체가 만들어질 수 있다.

        // 위에서 말하긴 했는데, 생성자로 하면 final 키워드 쓸 수 있어서 불변 객체를 만들 수 있다.

        // setter 주입은 setter 로 필드를 개방할 수 있고, 필드 꼭 정하지 않아도 생성할 수 있다.
        // 생성자랑 달리 내부에서, 일단 객체 만들고 setter 로 조입한다고 한다.
        // 근데 만약 생성되고 주입할 대상이 검색되지 않으면, 오류가 뜨는데
        // @Autowired(required=false) 옵션으로 주입할 대상 없어도 오류 안뜨게 할 수 있다.
        // 근데 이거는 public 으로 필드를 열어놓아야 해서 막 필드가 의도치 않게 수정될 수 있다.
        // 생성자(불변), setter(가변)

        // 필드 주입은 개발자가 DI 컨테이너 없이 개발자가 값을 넣을 수 없다.
        // 이러면 스프링 테스트 말고 간단히 실험할 때나 그런게 안된다.
        // 순수 자바 코드로 테스트하는 경우가 꽤 많다고 한다.
        // 그래서 생성자나 세터를 만들어야 한다.
        // 사실 그럴 바에는 세터랑 생성자에 autowired 주면 되지 않나

        // 다만, 주입할 필드에 달리 접근할 일이 없으면, 써도 괜찮다.


)
public class AutoAppConfig {

}
