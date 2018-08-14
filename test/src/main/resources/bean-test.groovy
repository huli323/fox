import beantest.Person
import beantest.Spouse
import org.springframework.context.support.ClassPathXmlApplicationContext
import org.springframework.core.io.ClassPathResource

/**
 * 使用Groovy DSL配置Bean信息
 * */
beans {
    person(Person){
        bean ->
            bean.scope = "prototype"
        name = "fox"
        age = 10
        spouse = ref("spouse")
    }

    spouse(Spouse){
        name = "Yan"
    }
}