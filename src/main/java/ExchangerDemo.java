import java.util.concurrent.Exchanger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExchangerDemo {

    private static final Exchanger exchanger = new Exchanger();
    private static ExecutorService service = Executors.newFixedThreadPool(2);
    public static void main(String[] args) {
        service.submit(new ExchangerRunnable("1","A",exchanger));
        service.submit(new ExchangerRunnable("2","B",exchanger));

        service.shutdown();
    }

    static public class ExchangerRunnable implements Runnable{
        private Object data;
        private String name;
        private Exchanger exchanger;
        public ExchangerRunnable(Object data, String name, Exchanger exchanger) {
            this.data = data;
            this.name = name;
            this.exchanger = exchanger;
        }

        @Override
        public void run() {
            try {
                Object previous = this.data;
                this.data = this.exchanger.exchange(previous);
                System.out.println("名称:" + name + " 之前数据：" + previous + " ,交换之后数据：" + this.data);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}
