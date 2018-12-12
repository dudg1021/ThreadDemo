public interface simpleInterface {

    String getName();

    default int getAge() {
        return 18;
    }
}
