package learn.java.util;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.TimeUnit;

public class HashMapTest {
    public static void main(String[] args) throws InterruptedException {
//        failFast();
        failSafe();
//        list();
    }

    private static void failFast() {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        Iterator it = list.iterator();
        while (it.hasNext()) {
            list.add(4);
            System.out.println(it.next());
        }
    }

    private static void failSafe() {
//        List<Integer> list = new CopyOnWriteArrayList<>();
//        list.add(1);
//        list.add(2);
//        list.add(3);
//        Iterator<Integer> it = list.iterator();
//        while(it.hasNext()) {
//            list.add(4);
//            System.out.println(it.next());
//        }
        ConcurrentHashMap<String, String> map = new ConcurrentHashMap<>();
        map.put("k1","v1");
        map.put("k2","v2");
        map.put("k3","v3");
        Iterator it = map.entrySet().iterator();
        while (it.hasNext()) {

            System.out.println(it.next());
            map.put("a4", "v4");
        }
    }

    private static void list() {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        Iterator<Integer> iterator = list.iterator();
        while (iterator.hasNext()) {
            list.add(5);
            System.out.println(iterator.next());
        }
    }

}
