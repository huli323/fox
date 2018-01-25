import java.io.IOException;
import java.text.ParseException;

public class Test {
    public static void main(String[] args) throws IOException, IllegalAccessException, ParseException, InterruptedException {


    }

}

class A{
    A(){
        System.out.println("class A init");
    }
    static {
        System.out.println("class A static block");
    }
    {
        System.out.println("class A block");
    }
}
class B extends A{
    B(){
        System.out.println("class B init");

    }
    static {
        System.out.println("class B static block");
    }
    {
        System.out.println("class B block");
    }
}


