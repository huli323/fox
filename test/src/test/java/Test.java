import com.jcraft.jsch.Channel;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import foxutil.Prime;
import org.apache.commons.lang.StringUtils;
import scala.collection.mutable.HashTable;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

enum T{
    PLUS("+"){double apply(double a, double b){return a + b;}};

    private String symbol;

    T(String symbol) {
        this.symbol = symbol;
    }

    abstract double apply(double a, double b);

    @Override
    public String toString() {
        return symbol;
    }

    private static final Map map = new HashMap();

    static {
        System.out.println("static block");
    }
}

public class Test {
    public static void main(String[] args) throws IOException, IllegalAccessException, ParseException {
//        SInterface stu = new Student("asdb");
//        ProxyFactory p = new ProxyFactory();
//        stu = (SInterface) p.createStudentProxy(stu);
//        stu.print();



    }

    public static void check(long num){
        while (num != 1) {
            long tmp = num >> 1;
            if(tmp << 1 != num){
                System.out.println("error");
                return;
            }
            num = tmp;
        }
    }

    /**
     * 检查DTO中的字段是否为空
     *
     * @param obj
     * @param set
     * @return
     * @throws IllegalAccessException
     */
    public static String check(Object obj, Set set) throws IllegalAccessException {
        Class cls = obj.getClass();
        for (Field field :
                cls.getDeclaredFields()) {
            field.setAccessible(true);
//            System.out.println(field.getName() + " type is " + field.getType().getSimpleName());
            if (field.get(obj) == null && !set.contains(field.getName()))
                return cls.getName() + "->" + field.getName() + "为空";
            else if (field.getType().getSimpleName().equals("Inner")) {
                String tmp = check(field.get(obj), set);
                return tmp == null ? null : cls.getName() + "->" + tmp;
            }
        }
        return null;
    }

    public static int rob(int[] arr) {
        int[] mem = new int[arr.length];
        Arrays.fill(mem, -1);
        return search(arr.length - 1, arr, mem);
    }

    public static int search(int index, int[] arr, int[] mem) {
        if (index < 0)
            return 0;

        return mem[index] != -1 ? mem[index] : Math.max(arr[index] + search(index - 2, arr, mem), search(index - 1, arr, mem));
    }


    /**
     * 将字符串中的整数加一
     */
    public static String addInStr(String str) {
        String tmp = str;
        String pattern = "\\d+";
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(str);
        StringBuffer sb = new StringBuffer("");

        while (m.find()) {
            String group = m.group();
            int len = group.length();
            int start = tmp.indexOf(group);

            sb.append(tmp.substring(0, start));

            String replaceStr = Integer.parseInt(m.group()) + 1 + "";

            sb.append(replaceStr);

            tmp = tmp.substring(start + len);

        }
        sb.append(tmp);

        return sb.toString();
    }

}

/*class T {
    public String add(int asc, Function<Integer, String> function) {
        return function.apply(asc);
    }
}*/

