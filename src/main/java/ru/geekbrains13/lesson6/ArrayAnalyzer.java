package ru.geekbrains13.lesson6;

public final class ArrayAnalyzer {

    public static int[] getTailAfterFour(int[] array) {
        for (int i = array.length - 1; i >= 0; i--) {
            if (array[i] == 4) {
                if (i == array.length - 1) return new int[0];
                int[] result = new int[array.length - i - 1];
                System.arraycopy(array, i + 1, result, 0, array.length - i - 1);
                return result;
            }
        }
        throw new RuntimeException("There's no 4s in this array!");
    }

    public static boolean consistsOfFoursAndOnes(int[] array) {
        boolean foursAreIn = false;
        boolean onesAreIn = false;
        for (int i : array) {
            onesAreIn |= (i == 1);
            foursAreIn |= (i == 4);
            if (i != 4 && i != 1) return false;
        }
        return onesAreIn && foursAreIn;
    }

}
