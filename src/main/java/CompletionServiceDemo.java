import java.util.Random;
import java.util.concurrent.*;

public class CompletionServiceDemo {

    public static void main(String[] args) {

        CompletionServiceDemo csd = new CompletionServiceDemo();
        //csd.testByQueue();
        csd.testByCompletion();
    }

    class MyThread implements Callable<String> {
        private String name;
        public MyThread(String name) {
            this.name = name;
        }

        @Override
        public String call() throws Exception {
            int sleepTime = new Random().nextInt(1000);
            try {
                Thread.sleep(sleepTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            //返回给调用者的值
            String str = name + " sleep time;"+ sleepTime;
            System.out.println(name+" finished...");
            return str;
        }
    }

    private final int POOL_SIZE = 5;
    private final int TOTAL_TASK = 20;
    //方法一 自己写集合来实现获取线程池中任务的返回结果
    public  void testByQueue() {
        ExecutorService pool = Executors.newFixedThreadPool(POOL_SIZE);
        BlockingQueue<Future<String>> queue = new LinkedBlockingDeque<Future<String>>();

        for (int i = 0; i < TOTAL_TASK; i++) {
            queue.add(pool.submit(new MyThread("Thread"+i)));
        }
        //检查线程池任务执行结果
        for (int i = 0; i < TOTAL_TASK; i++) {
            try {
                System.out.println("method1:"+queue.take().get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
        //关闭线程池
        pool.shutdown();
    }

    public void testByCompletion(){
        ExecutorService pool = Executors.newFixedThreadPool(POOL_SIZE);
        CompletionService<String> completionService = new ExecutorCompletionService<String>(pool);

        for (int i = 0; i < TOTAL_TASK; i++) {
            completionService.submit(new MyThread("Thread"+i));
        }
        //检查线程池任务执行结果
        for (int i = 0; i < TOTAL_TASK; i++) {
            Future<String> future = null;
            try {
                future = completionService.take();
                System.out.println("method2:"+future.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
        //关闭线程池
        pool.shutdown();
    }
}
