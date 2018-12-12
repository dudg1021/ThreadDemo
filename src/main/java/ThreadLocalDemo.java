public class ThreadLocalDemo {

    private static NumberList numberList = new NumberList();
    private static ThreadLocal<NumberList> local = new ThreadLocal<NumberList>(){
        @Override
        protected NumberList initialValue() {
            //return numberList;
            return  new NumberList();
        }
    };

    public static void main(String[] args) {
        Thread[] threads = new Thread[3];
        for (int i = 0; i < 3; i++) {
            threads[i] = new Thread(new Runnable() {
                @Override
                public void run() {
                    NumberList list = local.get();
                    for (int j = 0; j < 1000; j++) {
                        list.init();
                    }
                    local.set(list);
                    System.out.println(Thread.currentThread().getName()+"==="+local.get().num);
                }
            });
        }
        for (Thread t:threads){
            t.start();
        }

    }

    static class NumberList{
        int num;
        public  void init(){
            num++;
        }
    }
}

