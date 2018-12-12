import jdk.nashorn.internal.codegen.CompilerConstants;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.*;

public class FutureDemo {
    private static SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public static void main(String[] args) {

        ExecutorService executor = Executors.newCachedThreadPool();
        Task task = new Task();
        //Future+Callable方式
        //Future<Integer> result = executor.submit(task);
        //第一种方式
        FutureTask<Integer> futureTask = new FutureTask<Integer>(task);
        executor.submit(futureTask);
        executor.shutdown();

        //第二种方式 注意这种方式和第一种方式效果类似，一个是ExecutorService,一个Thread
        /*FutureTask<Integer> futureTask = new FutureTask<Integer>(task);
        Thread thread = new Thread(futureTask);
        thread.start();*/

        System.out.println("主线程继续执行任务，开始获取运行结果："+format.format(new Date()));
        try {
            System.out.println("futureTask.get()获取运行结果"+futureTask.get());
            System.out.println("主线程继续执行任务，结束获取运行结果："+format.format(new Date()));
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        System.out.println("所有任务执行完毕");
    }

    static public class Task implements Callable<Integer>{
        @Override
        public Integer call() throws Exception {
            System.out.println("子线程开始进行计算："+format.format(new Date()));
            Thread.sleep(5000);
            int sum = 0;
            for (int i = 0; i < 100; i++) {
                sum += i;
            }
            System.out.println("子线程结束计算："+format.format(new Date()));
            return sum;
        }
    }
}
