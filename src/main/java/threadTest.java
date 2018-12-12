import java.util.*;
import java.util.concurrent.*;


public class threadTest extends Thread {

    public static void main(String[] args) {
        simpleImpl simple = new simpleImpl();

        System.out.println("simple.getName() :"+simple.getName());
        Integer x = 123; //new Integer(123);
        Integer y = 123; // new Integer(123);
        System.out.println(x==y);
        Integer a = Integer.valueOf(123);
        Integer b = Integer.valueOf(123);
        System.out.println(x == a);
        System.out.println(a == b);
        System.out.println(Objects.deepEquals(y,b));

        String str = "123";
        String str2 = "5657";
        System.out.println(str2.intern());


        List<String> list = new CopyOnWriteArrayList<>();
        LinkedList<String> linkedList = new LinkedList<>();
    }

    @Override
    public void run() {
        System.out.println(getName());
    }
}