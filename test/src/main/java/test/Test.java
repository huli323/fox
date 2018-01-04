package test;

import java.util.HashMap;

public class Test {
    int outer = 1;
    public static void main(String[] args) {
        int a = 1;
//        TestIn test = () -> { int i = servlet;};

//        TestIn test = new TestIn(){
//            int i = servlet;
//            @Override
//            public void add() {
//                int i = servlet;
//            }
//        };

//        servlet = 2;


//        Out out = new Out();
//        out.show().add();
//        String str1 = "servlet";
//        String str2 = "b";
//        String str3 = str1 + str2;
////        System.out.println(str3);
//        StringBuffer buffer = new StringBuffer();
//        buffer.append("servlet");
//        buffer.append("servlet");
//        buffer.append('b');
////        buffer.setLength(0);
//        System.out.println(buffer.indexOf('b'+""));
//
//        System.out.println(lengthOfLongestSubstring("cabeacd"));




        Request request = new Request();
        request.setName("servlet");

        PayParam payParam = new PayParam();
        payParam.setId("11");
        request.setParam(payParam);
        request.setBiz(payParam);
        PayParam p = (PayParam) request.getBiz();

        PayParam param = (PayParam) request.getParam();
        System.out.println(param.getId());


    }

    public static int lengthOfLongestSubstring(String s) {
        if(s.length() == 0) return 0;
        HashMap<Character, Integer> map = new HashMap();
        char[] arr = s.toCharArray();
        int max = 1;
        for(int i = 0, j = -1, len = arr.length; i < len; i++){
            if(map.containsKey(arr[i])){
                j = Math.max(j, map.get(arr[i]));
            }
            map.put(arr[i], i);
            max = Math.max(max, i - j);
        }
        return max;
    }

}


class Out{
    public TestIn show(){
        int a = 1;
        TestIn test = new TestIn(){
            @Override
            public void add() {
                System.out.println(a);
            }
        };
        return test;
    }
}

enum E{
    H("servlet", "b");

    private String value;
    private String lavel;

    E(String a, String b) {
        this.value = a;
        this.lavel = b;
    }

    public String getValue(){
        return value;
    }

    public String getLavel(){
        return lavel;
    }
}

class Request<T extends Out>{
    private String name;
    private T biz;
    private Param param;

    public void setParam(Param param) {
        this.param = param;
    }

    public Param getParam() {

        return param;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBiz(T biz) {
        this.biz = biz;
    }

    public String getName() {

        return name;
    }

    public T getBiz() {
        return biz;
    }

    @Override
    public String toString() {
        return "Request{" +
                "name='" + name + '\'' +
                ", biz=" + biz +
                ", param=" + param +
                '}';
    }
}
class Param extends Out{

}
class PayParam extends Param{
    private String id;

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {

        return id;
    }

    @Override
    public String toString() {
        return "PayParam{" +
                "id='" + id + '\'' +
                '}';
    }
}
