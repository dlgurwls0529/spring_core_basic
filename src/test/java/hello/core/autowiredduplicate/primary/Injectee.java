package hello.core.autowiredduplicate.primary;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Injectee {
    private final Bean bean;

    // BeanA 가 프라이머리니까 이게 들어오겟지
    // 프라이머리는 오토와이어드 타입 검색 중복 시 추가로 수행된다.
    // Bean 타입이 여러 개 있으니까, 프라이어머리 붙은 걸로 간다.
    @Autowired
    public Injectee(Bean bean) {
        this.bean = bean;
    }

    public Bean getBean() {
        return bean;
    }
}
