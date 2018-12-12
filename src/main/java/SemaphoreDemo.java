import java.time.LocalTime;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class SemaphoreDemo {
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(5,true);
        ExecutorService service = Executors.newCachedThreadPool();
        for (int i = 0; i < 100; i++) {
            service.submit(new Player("玩家"+i,semaphore));
        }

        service.shutdown();
        while (!service.isTerminated()){
            System.out.println("当前排队总人数："+semaphore.getQueueLength());
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    static public class Player implements Runnable {
        private String playerName;
        private Semaphore semaphore;
        public Player(String playerName,Semaphore semaphore){
            this.playerName = playerName;
            this.semaphore = semaphore;
        }

        @Override
        public void run() {
            try {
                semaphore.acquire();
                System.out.println(playerName+"进入，时间："+ LocalTime.now());
                Thread.sleep((long)(3000*Math.random()));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            finally {
                System.out.println(playerName+"退出");
                semaphore.release();
            }
        }
    }
}
