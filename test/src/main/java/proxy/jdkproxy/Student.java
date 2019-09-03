package proxy.jdkproxy;

public class Student implements SInterface {
    private String name;
    @Override
    public void print(String name) {
        System.out.println("hello " + name);
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                '}';
    }
}
