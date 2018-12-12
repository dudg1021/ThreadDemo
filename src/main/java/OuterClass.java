
public class OuterClass{
    class InnerClass{}

    static class StaticInnerClass{}

    public static void main(String[] args) {
        OuterClass outerClass = new OuterClass();
        InnerClass innerClass = outerClass.new InnerClass();

        Integer integer =1;
        Float fl = 4f;
        StaticInnerClass staticInnerClass = new StaticInnerClass();
    }
}