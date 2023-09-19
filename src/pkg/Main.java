package pkg;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        try {
            Tasks.t3();
            Tasks.t4();
            Tasks.t5();
            Tasks.t6();
            Tasks.t7();
            Tasks.t8();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
