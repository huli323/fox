package proxy.cglibproxy;

import net.sf.cglib.proxy.Enhancer;

public class Test {
    public static void main(String[] args) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(Student.class);
        enhancer.setCallback(new MyMethodInterceptor());

        Student student = (Student) enhancer.create();
        student.say("hello world");
        System.out.println(student);
    }
}
