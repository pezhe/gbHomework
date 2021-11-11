package ru.gbalg.lesson6;

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
        explore(root);
        return true;
    }

    private void explore(Node rootNode) {
        if (rootNode != null) {
            explore(rootNode.leftChild);
            rootNode.display();
            explore(rootNode.rightChild);
        }
    }

}
