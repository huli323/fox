import java.io.IOException;
import java.text.ParseException;

public class Test {
    public static void main(String[] args) throws IOException, IllegalAccessException, ParseException, InterruptedException {

        System.out.println("1|1|1||".split("\\|", 5).length);

        for (String s :
                "1|1|1||".split("\\|", 5)) {
            System.out.println(s);
        }

    }

}

