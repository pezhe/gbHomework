package ru.gbalg.lesson8;

import java.util.LinkedList;
import java.util.Objects;

public class ChainingHashMap<K, V> {
    private final int capacity;
    private int size;
    private final LinkedList<Node>[] st;

    @SuppressWarnings("unchecked")
    // Type safety provided inside 'put' method. Only 'Node' instances are inserted to list
    public ChainingHashMap(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException();
        }
        this.capacity = capacity;
        st = new LinkedList[capacity];
        for (int i = 0; i < st.length; i++) {
            st[i] = new LinkedList<>();
        }
    }

    private class Node {
        K key;
        V value;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    private int getHash(K key) {
        return (key.hashCode() & 0x7fffffff) % capacity;
    }

    public boolean contains(K key) {
        return get(key) != null;
    }

    public void put(K key, V value) {
        int hash = getHash(Objects.requireNonNull(key));
        for (Node node : st[hash]) {
            if (key.equals(node.key)) {
                node.value = value;
                return;
            }
        }
        st[hash].addLast(new Node(key, value));
        size++;
    }

    public V get(K key) {
        int hash = getHash(Objects.requireNonNull(key));
        for (Node node : st[hash]) {
            if (key.equals(node.key)) {
                return node.value;
            }
        }
        return null;
    }

    public V remove(K key) {
        int hash = getHash(Objects.requireNonNull(key));
        for (int i = 0; i < st[hash].size(); i++) {
            if (key.equals(st[hash].get(i).key)) {
                size--;
                return st[hash].remove(i).value;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < capacity; i++) {
            for (Node node : st[i]) {
                sb.append(node.key).append(" = ").append(node.value).append(" ");
            }
            sb.append(System.lineSeparator());
        }
        return sb.toString();
    }
}
