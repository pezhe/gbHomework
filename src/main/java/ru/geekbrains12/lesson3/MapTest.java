package ru.geekbrains12.lesson3;

import java.util.HashMap;
import java.util.Map;

public class MapTest {

    public static void main(String[] args) {
        String[] arr = {"Иван", "Василий", "Сергей", "Анатолий", "Иван", "Николай", "Афанасий",
                "Василий", "Виктор", "Виталий", "Юрий", "Василий"};
        showOccurrences(arr);
    }

    static void showOccurrences (String[] arr) {
        HashMap<String, Integer> map = new HashMap<>();
        int maxSize = 6; // Максимальная ширина первого столбца для красивого вывода
        for (String s : arr) {
            if (s.length() > maxSize) maxSize = s.length();
            if (map.containsKey(s)) map.replace(s, map.get(s) + 1);
            else map.put(s, 1);
        }
        String format = "%-" + maxSize + "s | %s%n";
        System.out.printf(format, "Запись", "Количество");
        System.out.println();
        for (Map.Entry<String, Integer> me : map.entrySet()) {
            System.out.printf(format, me.getKey(), me.getValue().toString());
        }
    }
}
