package threadtest;

public class Test {
    public static void main(String[] args) throws InterruptedException {
        Runnable runnable = () -> {
            for (int i = 0; i < 10000; i++) {
                System.out.println(i);

            }
        };

        Thread t = new Thread(runnable);
        t.run();
//        Thread.sleep(100);
        t.interrupt();
    }
}
