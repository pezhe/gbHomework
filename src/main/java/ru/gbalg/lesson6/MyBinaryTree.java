package ru.gbalg.lesson6;

import ru.gbalg.lesson3.MyStack;

public class MyBinaryTree {

    static class Node {
        private int value;
        private Node leftChild;
        private Node rightChild;

        private void display() {
            System.out.print(value + " ");
        }
    }

    private Node root;
    private final int maxDepth;

    public MyBinaryTree (int maxDepth) {
        this.maxDepth = maxDepth;
    }

    public boolean insert(int value) {
        Node node = new Node();
        node.value = value;
        if (root == null) {
            root = node;
            return true;
        }
        else {
            Node current = root;
            Node parent;
            int currentDepth = 0;
            while (true) {
                currentDepth++;
                if (currentDepth > maxDepth) return false;
                parent = current;
                if (value < current.value) {
                    current = current.leftChild;
                    if (current == null) {
                        parent.leftChild = node;
                        return true;
                    }
                } else {
                    current = current.rightChild;
                    if (current == null) {
                        parent.rightChild = node;
                        return true;
                    }
                }
            }
        }
    }

    public boolean isBalanced() {
        MyStack<Integer> depths = new MyStack<>();
        explore(root, 0, depths);
        int max = 0;
        int min = maxDepth;
        while (!depths.isEmpty()) {
            int depth = depths.pop();
            max = Math.max(depth, max);
            min = Math.min(depth, min);
        }
        System.out.print("(" + min + "," + max + ") ");
        return (max - min) <= 1;
    }

    private void explore(Node rootNode, int depth, MyStack<Integer> depths) {
        if (rootNode != null) {
            if (rootNode.leftChild == null || rootNode.rightChild == null)
                depths.push(depth);
            depth++;
            explore(rootNode.leftChild, depth, depths);
            rootNode.display();
            explore(rootNode.rightChild, depth, depths);
        }
    }

}
