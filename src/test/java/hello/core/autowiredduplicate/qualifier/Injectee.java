package hello.core.autowiredduplicate.qualifier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class Injectee {
    private final Bean bean;

    // 퀄리파이어 있으니 일단 그걸로 주입한다. 그리고 끝
    @Autowired
    public Injectee(@Qualifier("myBean") Bean bean) {
        this.bean = bean;
    }

    public Bean getBean() {
        return this.bean;
    }
}
