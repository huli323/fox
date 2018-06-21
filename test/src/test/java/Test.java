import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Test {
    public static void main(String[] args) throws Exception {
        List<Map<String, String>> list = new ;
        try {
            throw new Exception("aaa");
        } catch (Exception e) {
            e.printStackTrace();
        }

        for (Map<String, String> map : list) {
            System.out.println(map.get("a"));
        }
    }

}