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
        // Configuration 도 상위에 Component 가 있어서
        // 이거 안하면 설정 정보인 AppConfig 와 그 빈들이 다 등록된다.
        // 이러면 충돌 나니까 여기에서는 AppConfig 안읽을라고
        // Configuration 은 필터링하는 것이다.

        basePackages = "hello.core.member",
        // 이거는 탐색하는 패키지의 시작 위치
        // 이 패키지를 포함해서 모든 하위 패키지를 다 뒤진다.

        basePackageClasses = AutoAppConfig.class
        // 이거는 이 클래스가 속한 패키지를 시작 위치로 하는 것

        // 만약 저것들로 지정 안하면, 베이스 패키지에 설정 정보를 넣어서 수행한다.
        // 그니까 설정 정보가 속한 패키지부터 다 뒤진다는 것이다.
        // 이거 기준으로는 AppConfig, CoreApplication, discount ...
        // 이렇게 지정하지 않는 것을 추천한다고 한다.
        //

)
public class AutoAppConfig {

}
