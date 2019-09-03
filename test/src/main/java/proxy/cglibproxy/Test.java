package proxy.cglibproxy;

public class Test {
    public static void main(String[] args) {
        CGLibProxy cgLibProxy = new CGLibProxy();

        Student student = (Student) cgLibProxy.newProxy(new Student());
        student.say("hello world");
        System.out.println(student);
    }
}
