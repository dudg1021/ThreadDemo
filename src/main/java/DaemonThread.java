import java.io.*;
import java.util.Scanner;

public class DaemonThread implements Runnable {


    @Override
    public void run() {
        System.out.println("进入守护线程"+Thread.currentThread().getName());


        writeToFile();

        System.out.println("退出守护线程"+Thread.currentThread().getName());
    }

    private void writeToFile() {
        File fileName = new File("D:"+File.separator+"daemon.txt");
        OutputStream os = null;
        try {
            os = new FileOutputStream(fileName,true);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        int count = 0;
        while (count<666){
            try {
                os.write(("\r\nword"+count).getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("守护线程"+Thread.currentThread().getName()+"向文件写入了word"+count++);

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class DaemonThreadDemo{

    public static void main(String[] args) {
        System.out.println("进入主线程"+Thread.currentThread().getName());

        DaemonThread daemonThread = new DaemonThread();

        Thread thread = new Thread(daemonThread);
        thread.setDaemon(true);
        thread.start();

        Scanner sc = new Scanner(System.in);
        sc.next();

        System.out.println("退出主线程"+Thread.currentThread().getName());
    }
}
