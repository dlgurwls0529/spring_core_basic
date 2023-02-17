package hello.core.autowired;

import hello.core.member.domain.Member;
import jakarta.annotation.Nullable;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Optional;

public class AutowiredTest {

    @Test
    public void autowiredOption() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestBean.class);
    }

    public static class TestBean {
        // 2, 3 은 호출 안되는데, 1은 아예 안 호출된다.

        // true 로 하면 빈 없어서 예외 터짐
        @Autowired(required = false)
        public void setNoBean1(Member noBean1) {
            System.out.println("noBean1 = " + noBean1);
        }

        @Autowired
        public void setNoBean2(@Nullable Member noBean2) {
            System.out.println("noBean2 = " + noBean2);
        }

        @Autowired
        public void setNoBean3(Optional<Member> noBean3) {
            System.out.println("noBean3 = " + noBean3);
        }

        // nullable 과 optional 은 생성자 주입에서도 사용할 수 있다.
        // 가령 굳이 이거는 없어도 생성을 하고 싶어~ 이런 경우에
        // 그 필드만 nullable 이나 optional 을 넣고 돌리면 된다.
    }
}
