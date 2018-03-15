import java.util.Random;

public class Test {
    private static boolean stopRequested;
    public static void main(String[] args) throws InterruptedException {
        Random random = new Random(5);
        System.out.println(random.nextInt(10));
        System.out.println(random.nextInt(10));
        System.out.println(random.nextInt(10));
        System.out.println(random.nextInt(10));

    }


}
//
//class A implements Runnable {
//    private String name;
//
//    public A(String name){
//        this.name = name;
//    }
//
//    @Override
//    public void run() {
//        Thread.currentThread().setName(name);
//        throw new RuntimeException();
//    }
//}
//
//class MyThreadFactory implements ThreadFactory {
//    @Override
//    public Thread newThread(Runnable r) {
//        Thread t = new Thread(r);
//        System.out.println("created a threadtest: " + t.getName());
//        t.setUncaughtExceptionHandler(new MyExceptionHandler());
//        return t;
//    }
//}
//
//class MyExceptionHandler implements Thread.UncaughtExceptionHandler {
//    @Override
//    public void uncaughtException(Thread t, Throwable e) {
//        System.out.println(t.getName() + " catched exception: " + e);
//    }
//}
//
//
//class LinkedStack<T> {
//    private Node<T> node = new Node();
//
//    private class Node<U> {
//        U val;
//        Node<U> next;
//
//        Node() {
//        }
//
//        Node(U val, Node<U> next) {
//            this.val = val;
//            this.next = next;
//        }
//
//        boolean isEnd() {
//            return val == null && next == null;
//        }
//
//        @Override
//        public String toString() {
//            return next + "->" + val;
//        }
//    }
//
//    public void push(T t) {
//        if (node.isEnd()) {
//            node.val = t;
//        } else {
//            node = new Node(t, node);
//        }
//    }
//
//    public T pop() {
//        T t = node.val;
//        return t == null ? null : t;
//    }
//
//    public boolean isEmpty() {
//        return node.isEnd();
//    }
//
//    @Override
//    public String toString() {
//        return node.toString();
//    }
//}
//
//
