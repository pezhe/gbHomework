package ru.gbalg.lesson6;

import java.util.concurrent.ThreadLocalRandom;

public class TreeApp {

    public static void main(String[] args) {

        MyBinaryTree[] treeArray = new MyBinaryTree[20];
        for (int n = 0; n < 20; n++) {
            treeArray[n] = new MyBinaryTree(6);
            while(treeArray[n].insert(ThreadLocalRandom.current().nextInt(-100, 101))) {}
        }

        int balancedAmount = 0;
        for (MyBinaryTree mbt : treeArray) {
            boolean balanced = mbt.isBalanced();
            System.out.println("Balanced: " + balanced);
            if (balanced) balancedAmount++;
        }
        System.out.println(balancedAmount / 20 * 100 + "% are balanced");

    }

}
