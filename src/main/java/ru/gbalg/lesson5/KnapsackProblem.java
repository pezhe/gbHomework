package ru.gbalg.lesson5;

public class KnapsackProblem {

    static class Item {

        final int value;
        final int weight;

        public Item(int value, int weight) {
            this.value = value;
            this.weight = weight;
        }

    }

    static class Knapsack {

        final int capacity;

        Knapsack(int capacity) {
            this.capacity = capacity;
        }

    }

    public static int findMaxValue(Knapsack sack, Item... items) {
        return findMaxValue(items, items.length - 1, sack.capacity);
    }

    private static int findMaxValue(Item[] items, int pos, int weight) {
        if (pos < 0) return 0;
        if (items[pos].weight > weight) return findMaxValue(items, pos - 1, weight);
        return Math.max(findMaxValue(items, pos - 1, weight),
                        findMaxValue(items, pos - 1,weight - items[pos].weight)
                                + items[pos].value);
    }

    // Тест
    public static void main(String[] args) {
        Knapsack sack = new Knapsack(13);
        int maxValue = findMaxValue(sack,
                    new Item(1, 3),
                    new Item(6, 4),
                    new Item(4, 5),
                    new Item(7, 8),
                    new Item(6, 9));
        System.out.println(maxValue);
    }

}
