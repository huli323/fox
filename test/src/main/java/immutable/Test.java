package immutable;

public class Test {
    public static void main(String[] args) throws CloneNotSupportedException {
        String[] arr = new String[]{"hello", "world"};
        MutableBook book = new MutableBook("Java", "servlet", arr);
            ImmutableReader reader = new ImmutableReader(book, 20);

        System.out.println(reader);
        book.setBookName("C");
        System.out.println(reader);

        arr[1] = "no";
        System.out.println(reader);

        System.out.println("==========================");

        B b = new B("aa");
        A a = new A(b);
        A a1 = (A) a.clone();
        System.out.println(a.getB() == a1.getB());


        String s1 = new String("abc").intern();
        String s2 = "abc";
        System.out.println(s1 == s2);


    }
}
