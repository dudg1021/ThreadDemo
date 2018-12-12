

public class JoinLongTest {
    public static void main(String[] args) {
        try {
            MyThread threadTest = new MyThread();
            threadTest.setName("线程1");

            MyThread threadTest2 = new MyThread();
            threadTest2.setName("线程2");

            MyThread threadTest3 = new MyThread();
            threadTest3.setName("线程3");

            //threadTest.join(2000);// 只等2秒
            threadTest.start();
            threadTest.join();
            threadTest2.start();
            threadTest2.join();
            threadTest3.start();
            //Thread.sleep(2000);

            System.out.println("  end timer=" + System.currentTimeMillis());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    static public class MyThread extends Thread {
        @Override
        public void run() {
            try {
                System.out.println(Thread.currentThread().getName()+ "begin Timer=" + System.currentTimeMillis());
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}