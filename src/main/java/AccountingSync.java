public class AccountingSync implements Runnable {

    private static int i = 0;

    /**
     * synchrionzed 修饰实例方法
     */
    public synchronized void increase(){
        i++;
    }

    @Override
    public void run() {
        synchronized(this) {
            for (int j = 0; j < 5; j++) {

                try {
                    System.out.println(Thread.currentThread().getName() + ":" + (i++));
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        //test-01
        AccountingSync as1 = new AccountingSync();
        AccountingSync as2 = new AccountingSync();
        Thread t1 = new Thread(as1);
        Thread t2 = new Thread(as2);

        //test-02
//        AccountingSync as = new AccountingSync();
//        Thread t1 = new Thread(as);
//        Thread t2 = new Thread(as);

        t1.start();
        t2.start();

//        //join含义：当前线程A等待thread线程终止后才能从thread.join()返回
//        t1.join();
//        t2.join();
    }
}
