package com.bobocode;

public class HashTable<K, V> {

    public static final int INITIAL_SIZE = 8;
    public static final double LOAD_FACTOR_LIMIT = 0.75;

    private Entry<K, V>[] buckets;
    private int size;

    private static class Entry<K, V> {
        K key;
        V value;
        Entry<K, V> next;

        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public String toString() {
            return "Entry{" +
                    "key=" + key +
                    ", value=" + value +
                    ", next=" + next +
                    '}';
        }
    }

    public HashTable() {
        buckets = new Entry[INITIAL_SIZE];
    }

    public void put(K key, V value) {
        expandIfNeeded();
        int i = bucketIndex(key);
        Entry<K, V> entry = buckets[i];
        if (entry == null) {
            buckets[i] = new Entry<>(key, value);
            size++;
        } else {
            while (entry.next != null) {
                if (entry.key.equals(key)) {
                    entry.value = value;
                    return;
                }
                entry = entry.next;
            }
            entry.next = new Entry<>(key, value);
            size++;
        }
    }

    private void expandIfNeeded() {
        double load = (double) size / buckets.length;
        System.out.println("load = " + load);
        if (load > LOAD_FACTOR_LIMIT) {
            System.out.println("expansion...");
            size = 0;
            Entry<K, V>[] oldBuckets = buckets;
            buckets = new Entry[buckets.length * 3 / 2];
            for (int i = 0; i < oldBuckets.length; i++) {
                Entry<K, V> entry = oldBuckets[i];
                while (entry != null) {
                    put(entry.key, entry.value);
                    entry = entry.next;
                }
            }
        }
    }

    public void printTable() {
        StringBuilder sb = new StringBuilder("\n");
        for (int i = 0; i < buckets.length; i++) {
            sb.append(i).append(": ");
            Entry<K, V> entry = buckets[i];
            while (entry != null) {
                sb.append(entry.key).append(":").append(entry.value);
                entry = entry.next;
                if (entry != null) {
                    sb.append(" -> ");
                }
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

    private int bucketIndex(K key) {
        return Math.abs(key.hashCode()) % buckets.length;
    }
}
