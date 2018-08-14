package beantest;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.GenericGroovyApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

public class Test {
    public static void main(String[] args) {
        ApplicationContext context = null;
        context = new AnnotationConfigApplicationContext(Beans.class);
        context = new GenericGroovyApplicationContext("bean-test.groovy");
//        context = new ClassPathXmlApplicationContext("spring-bean-config.xml");

        Person p1 = context.getBean("person", Person.class);
        System.out.println(p1);
        Person p2 = context.getBean("person", Person.class);
        System.out.println(p1 == p2);

    }
}
