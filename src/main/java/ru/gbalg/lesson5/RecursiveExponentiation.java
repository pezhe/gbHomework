package ru.gbalg.lesson5;

public class RecursiveExponentiation {

    public static double raiseToPower(double base, int power) {
        if (power == 0) return 1;
        if (power < 0) return 1 / base * raiseToPower(base, ++power);
        return base * raiseToPower(base, --power);
    }

    // Тест
    public static void main(String[] args) {
        System.out.println(raiseToPower(2, -2));
        System.out.println(raiseToPower(2, 10));
    }

}
