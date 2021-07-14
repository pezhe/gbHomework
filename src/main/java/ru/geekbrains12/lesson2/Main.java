package ru.geekbrains12.lesson2;

public class Main {

    public static void main(String[] args) {
        String[][] arr = {{"1","1","1","1"}, {"1","1","1","1"}, {"1","1","1","1"}, {"1","1","1","1"}};
        try {
            System.out.println("Starting the \"try\" block");
            System.out.println("Sum = " + sumArray(arr));
        } catch (MyArraySizeException | MyArrayDataException e) {
            System.out.println(e.getMessage());
            System.out.println("The cause is:  " + e.getCause());
            System.out.println("Calculation has aborted. Program terminates");
        }

    }

    static int sumArray (String[][] arr) throws MyArraySizeException, MyArrayDataException {
        int sum = 0;
        int linesNumber = arr.length;
        if (linesNumber == 4) {
            for (int i = 0; i < 4; i++) {
                int membersNumber = arr[i].length;
                if (membersNumber != 4) throw new MyArraySizeException("Wrong array size. Expected size is 4x4. " +
                        "Line " + (i + 1) + " contains " + membersNumber + " members.");
                for (int j = 0; j < 4; j++) {
                    try {
                        sum += Integer.parseInt(arr[i][j]);
                    } catch (NumberFormatException e) {
                        throw new MyArrayDataException("Inappropriate type of array member. Cell address [" +
                                (i + 1) + ":" + (j + 1) + "].", e);
                    }
                }
            }
        } else throw new MyArraySizeException("Wrong array size. Expected size is 4x4. Array contains " +
                linesNumber + " lines");
        return sum;
    }

}
