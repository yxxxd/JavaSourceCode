package learn.java.util;

import java.util.concurrent.atomic.LongAdder;

public class Main {
    public static void main(String[] args) {
        LongAdder adder = new LongAdder();
        adder.add(1);
        adder.add(2);

        System.out.println(adder.sum());
    }
}
