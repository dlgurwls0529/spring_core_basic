package hello.core.autowiredduplicate.annotationqualifier;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class BeanA implements Bean {

}
