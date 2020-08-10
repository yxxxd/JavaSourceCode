package learn.java.util;

public class MyHashMap<K, V> {
    private final static int DEFAULT_INITIAL_CAPACITY = 16;
    private final static float DEFAULT_LOAD_FACTORY = .75f;


    private float loadFactory;
    private Node<K, V>[] table;
    private int threshHold;
    private int size;

    public MyHashMap() {
        this.loadFactory = DEFAULT_LOAD_FACTORY;
    }

    class Node<K, V> {
        K key;
        V value;
        Node<K, V> next;
        int hash;

        public Node(int hash, K key, V value, Node<K, V> next) {
            this.hash = hash;
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }


    public V put(K key, V value) {
        // 初始化
        if (table == null || table.length == 0) {
            resize();
        }
        int hash = hash(key);
        Node<K, V> p;int i;
        if ((p = table[i = (hash & table.length-1)])==null) {
            table[i] = new Node<>(hash, key, value, null);
        } else {
            Node<K, V> e;
            K k;
            if (p.hash == hash && (key == p.key || (key != null && key.equals(p.key)))) {
                e = p;
            }
            while(true) {
                if ((e = p.next) == null) {
                    p.next = new Node<>(hash, key, value, null);
                    break;
                }
                if (e.hash == hash && (e.key == key || (key != null && key.equals(e.key)))) {
                    break;
                }
                p = e;
            }
            if (e != null) {
                V oldValue = e.value;
                e.value = value;
                return oldValue;
            }
        }
        if (++size >= threshHold) {
            resize();
        }
        return null;
    }

    public V get(K key) {
        int hash = hash(key);
        Node<K, V> p = table[hash & table.length-1];
        while(p != null) {
            if (p.hash == hash && (p.key == key || key != null && key.equals(p.key))) {
                return p.value;
            }
            p = p.next;
        }

        return null;
    }

    private int hash(K key) {
        return key == null ? 0 : key.hashCode();
    }

    private void resize(){
        Node<K, V>[] oldTable = table;
        int oldCap = oldTable == null ? 0 : oldTable.length;
        int oldThr = threshHold;
        int newCap, newThr = 0;
        if (oldCap > 0) {
            // 扩容
            newCap = oldCap << 1;
            newThr = oldThr << 1;
        } else if (oldThr > 0) {
            // 带参的初始化
            newCap = oldThr;
        } else {
            // 无参初始化
            newCap = DEFAULT_INITIAL_CAPACITY;
            newThr = (int) (DEFAULT_INITIAL_CAPACITY * DEFAULT_LOAD_FACTORY);
        }

        if (newThr == 0) {
            newThr = (int) (newCap * loadFactory);
        }

        threshHold = newThr;
        Node<K, V>[] newTable = new Node[newCap];
        table = newTable;

        if (oldTable != null) {
            Node<K, V> e;

            for (int i = 0; i < oldCap; i++) {
                if ((e = oldTable[i]) != null) {
                    if (e.next == null) {
                        newTable[e.hash&newCap-1] = e;
                    } else {
                        Node<K, V> loHead = null, loTail = null;
                        Node<K, V> hiHead = null, hiTail = null;
                        do {
                            if ((e.hash & oldCap) == 0) {
                                if (loTail == null) {
                                    loHead = e;
                                    loTail = loHead;
                                } else {
                                    loTail.next = e;
                                    loTail = e;
                                }
                            } else {
                                if (hiTail == null) {
                                    hiHead = e;
                                    hiTail = loHead;
                                } else {
                                    hiTail.next = e;
                                    hiTail = e;
                                }
                            }
                        } while ((e=e.next) != null);
                        if (loTail != null) {
                            loTail.next = null;
                            newTable[i] = loHead;
                        }
                        if (hiTail != null) {
                            hiTail.next = null;
                            newTable[i+oldCap] = hiHead;
                        }
                    }
                }
            }
        }
    }

    private int capacity() {
        return (table != null) ? table.length : DEFAULT_INITIAL_CAPACITY;
    }

    public static void main(String[] args) {
        MyHashMap<String, String> map = new MyHashMap<>();
        for (int i = 0; i < 17; i++) {
            map.put("k"+i, "v"+i);
        }

        System.out.println(map.get("k16"));
    }
}
