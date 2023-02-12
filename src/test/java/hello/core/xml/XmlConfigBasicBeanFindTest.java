package hello.core.xml;

import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class XmlConfigBasicBeanFindTest {
    // 빈 저장하고 찾는거는 빈팩토리가 다하는데,
    // 어플리케이션 컨텍스트가 이걸 확장한다.
    // DI 말고도 이벤트 드리븐, 환경 변수, 글로벌 뭐시기, 외부 파일 처리 등 부가 기능이 더 필요해서
    // 빈 팩토리랑 저것들을 다 구현하는 게 어플리케이션 컨텍스트
    private final ApplicationContext applicationContext = new GenericXmlApplicationContext("AppConfig.xml");

    @Test
    @DisplayName("xml 에서 서비스 파일 찾기")
    public void findBeanByXmlConfig() {
        Object bean = applicationContext.getBean("orderService", OrderService.class);
        Assertions.assertInstanceOf(OrderServiceImpl.class, bean);
        // 아래 꺼도 됨.
        // Assertions.assertInstanceOf(OrderService.class, bean);
        // Assertions.assertInstanceOf(Object.class, bean);
    }

}
