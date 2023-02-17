package hello.core;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class HelloLombok {
    private String name;
    private int age;

    public static void main(String[] args) {
       HelloLombok helloLombok = new HelloLombok();
       helloLombok.setName("testName");
       System.out.println(helloLombok.getName());
    }
}
