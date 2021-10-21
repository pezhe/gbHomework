package ru.geekbrains13.lesson7;

import java.util.Locale;

public final class Tester {

    private static String line;

    @BeforeSuite
    public static void preparingData() {
        System.out.println("Preparing test data");
        line = "Test Data!";
    }

    @Test (priority = 6)
    public static void capitalPrintTest() {
        System.out.println(line.toUpperCase(Locale.ROOT));
    }

    @Test (priority = 9)
    public static void reversePrintTest() {
        StringBuilder sb = new StringBuilder(line);
        System.out.println(sb.reverse());
    }

    @AfterSuite
    public static void cleaningData() {
        System.out.println("Cleaning test data");
        line = null;
    }

}
