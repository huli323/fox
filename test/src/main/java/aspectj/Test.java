package aspectj;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {
    public static void main(String[] args) {

     /*   Test target = new Test();
        AspectJProxyFactory factory = new AspectJProxyFactory();
        factory.setTarget(target);
        factory.addAspect(AspectJService.class);

        Test test = factory.getProxy();
        test.show();*/



        ApplicationContext context = new ClassPathXmlApplicationContext("spring-bean-config.xml");

        Test test = (Test) context.getBean("test");
        test.show();


    }

    public void show(){
        System.out.println("hello world");
    }

}


