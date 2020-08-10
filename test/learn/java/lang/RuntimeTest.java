package learn.java.lang;

import java.io.IOException;

public class RuntimeTest {
    public static void main(String[] args) throws IOException {
        Runtime.getRuntime().exec(new String[]{ "cmd", "/c"});
    }
}
