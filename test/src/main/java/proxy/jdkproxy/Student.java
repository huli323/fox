package proxy.jdkproxy;

public class Student implements SInterface {
    private String name;

    public Student(String name) {
        this.name = name;
    }

    public Student() {

    }

    @Override
    public void print() {
        System.out.println("hello " + name);
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {

        return name;
    }

    @Override
    public String toString() {
        return "toString";
    }
}
