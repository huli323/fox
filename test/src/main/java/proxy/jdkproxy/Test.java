package proxy.jdkproxy;

public class Test {
    public static void main(String[] args) {
        ProxyFactory factory = new ProxyFactory();
        SInterface student = new Student("aasd");
        student = (SInterface) factory.createStudentProxy(student);

        student.print();
    }
}
