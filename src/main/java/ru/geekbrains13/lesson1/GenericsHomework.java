package ru.geekbrains13.lesson1;

import java.util.ArrayList;
import java.util.Collections;

public class GenericsHomework {

    //метод, меняющий местами элементы массива
    static <T> void swapObjects (T[] objArray, int index1, int index2) {
        if (index1 < objArray.length && index1 >= 0 && index2 < objArray.length && index2 >= 0) {
            T buf = objArray[index1];
            objArray[index1] = objArray[index2];
            objArray[index2] = buf;
        }
        else throw new IndexOutOfBoundsException("Oops! Check the indexes");
    }

    //метод, преобразующий массив в ArrayList
    static <T> ArrayList<T> toArrayList (T[] objArray) {
        ArrayList<T> result = new ArrayList<>();
        Collections.addAll(result, objArray);
        return result;
    }

    //тестируем методы
    public static void main(String[] args) {

        //меняем местами
        String[] line = new String[] {"you", "must", "swap", "words", "young", "padawan"};
        swapObjects(line, 0, 2);
        swapObjects(line, 1, 3);
        for (String word : line) System.out.print(word + " ");
        System.out.println();

        //преобразуем в ArrayList, проверяем метод листа и содержимое
        System.out.println(toArrayList(line).contains("padawan"));
    }

}
