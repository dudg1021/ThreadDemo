import java.util.Date;

public class InheritableThreadLocalDemo {
    static public class Tools{
        public static InheritableThreadLocal t1 = new InheritableThreadLocal(){
            @Override
            protected Object initialValue() {
                return new Date().getTime();
            }

            @Override
            protected Object childValue(Object parentValue) {
                return parentValue+"我在子线程添加的！";
            }
        };
    }

    static public class ThreadA extends Thread{
        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                System.out.println("在ThreadA线程中取值="+ Tools.t1.get());
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
                System.out.println("   在Main线程中取值="+ Tools.t1.get());
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
