package hello.core.autowiredduplicate.primary;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Primary
@Component
public class BeanA implements Bean {

}
