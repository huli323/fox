package proxy.jdkproxy;

public class Test {
    public static void main(String[] args) {
        SInterface student = new Student();
        JDKProxy factory = new JDKProxy();
        student = (SInterface) factory.newProxy(student);

        student.print("fox");
    }
}
