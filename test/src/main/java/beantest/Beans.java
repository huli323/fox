package beantest;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/**
 * 以带注解的Java类提供配置信息
 */
@Configuration
public class Beans {

    @Scope(value = "prototype")
    @Bean(name = "person")
    public Person build(){
        Person person = new Person();
        person.setAge(10);
        person.setName("Fox");
        return person;
    }

}
