package hello.core.autowiredduplicate.annotationqualifier;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@MyBean
public class BeanB implements Bean {

}
