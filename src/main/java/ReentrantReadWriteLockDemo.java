import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReentrantReadWriteLockDemo {
    public static void main(String[] args) {
        Service service = new Service();

        ThreadA a = new ThreadA(service);
        a.setName("线程A");
        a.start();
        ThreadB b = new ThreadB(service);
        b.setName("线程B");
        b.start();
    }

    static public class Service{
        private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
        public void read(){
           try {
               try {
                   lock.readLock().lock();
                   System.out.println("获得读锁" + Thread.currentThread().getName() + " " + System.currentTimeMillis());
                   Thread.sleep(10000);
               }
               finally {
                   lock.readLock().unlock();
               }
           }
           catch (InterruptedException e) {
               e.printStackTrace();
           }
        }

        public void write() {
            try {
                try {
                    lock.writeLock().lock();
                    System.out.println("获得写锁" + Thread.currentThread().getName() + " " + System.currentTimeMillis());
                    Thread.sleep(10000);
                } finally {
                    lock.writeLock().unlock();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    static public class ThreadA extends Thread{
        private Service service;
        public ThreadA(Service service){
            this.service = service;
        }

        @Override
        public void run() {
            service.read();
        }
    }

    static public class ThreadB extends Thread{
        private Service service;
        public ThreadB(Service service){
            this.service = service;
        }

        @Override
        public void run() {
            service.write();
        }
    }
}
