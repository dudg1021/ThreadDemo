import java.util.Date;

public class ThreadLocalDemo2 {
    static public class Tools{
        public static ThreadLocal t1 = new ThreadLocal(){
            @Override
            protected Object initialValue() {
                return new Date().getTime();
            }
        };
    }

    static public class ThreadA extends Thread{
        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                System.out.println("在ThreadA线程中取值="+Tools.t1.get());
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        try {
            for (int i = 0; i < 10; i++) {
                System.out.println("   在Main线程中取值="+Tools.t1.get());
                Thread.sleep(100);
            }
            Thread.sleep(5000);
            ThreadA a = new ThreadA();
            a.start();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
