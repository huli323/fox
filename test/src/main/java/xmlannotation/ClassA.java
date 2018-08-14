package xmlannotation;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @XmlRootElement 用在class类的注解，对应XML的根元素，常与@XmlAccessorType 一起使用
 *
 * @XmlAccessorType(XmlAccessType.PUBLIC_MEMBER)
 * XmlAccessType枚举类成员：
 *      FIELD 将类中每个非静态、非瞬态属性自动绑定到XML
 *      NONE 所有属性都不能绑定到XML，除非使用JAXB注释对他们进行注释
 *      PROPERTY 将类中setter/getter 方法自动绑定到XML
 *      PULBIC_MEMBER 将每个公共getter/setter和公共的属性自动绑定到XML，除非由@XmlTransient注释
 *
 * @XmlTransient 映射到XML时，忽略注解标记的元素
 *
 * @XmlElement 将元素映射为XML节点
 *
 * @XmlAttribute 将元素映射为XML节点的属性，可通过name指定属性别名
 * */

//@XmlRootElement(name = "classA")
@XmlAccessorType(XmlAccessType.FIELD)
public class ClassA {
    @XmlElement(name="Name")
    private String name;

    @XmlElement(name="Age")
    private int age;

    @XmlElement(name="Gender")
    public String gender;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public ClassA() {
    }

    public ClassA(String name, int age, String gender) {
        this.name = name;
        this.age = age;
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "ClassA{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", gender='" + gender + '\'' +
                '}';
    }
}
