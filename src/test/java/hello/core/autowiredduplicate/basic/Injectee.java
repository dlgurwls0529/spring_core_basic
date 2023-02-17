package hello.core.autowiredduplicate.basic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Injectee {
    private final Bean beanA;

    // 같은 타입 먼저 하고, 여러개니까 beanA 로 찾는다.
    @Autowired
    public Injectee(Bean beanA) {
        this.beanA = beanA;
    }

    public Bean getBeanA() {
        return beanA;
    }
}
