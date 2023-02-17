package hello.core.autowiredduplicate.qualifier;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("myBean")
public class BeanB implements Bean {

}
