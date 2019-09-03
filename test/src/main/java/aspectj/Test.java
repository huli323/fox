package aspectj;

import org.springframework.aop.aspectj.annotation.AspectJProxyFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-bean-config.xml");

        Test test = (Test) context.getBean("test");
        test.show("fox");


    }

    public void show(String name){
        System.out.println("hello " + name);
    }

}


