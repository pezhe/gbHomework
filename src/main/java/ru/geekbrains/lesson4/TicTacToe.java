package ru.geekbrains.lesson4;

import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class TicTacToe {

    static final char EMPTY_CELL_SYMBOL = '-';
    static final int fieldSize = 3;

    public static void start() {

        char[][] field = createField();

        drawField(field);

        try {
            do {
                doPlayerMove(field);
                drawField(field);
                if (isWin(field, 'X')) {
                    System.out.println("Congratulations!!! You are winner.");
                    break;
                }
                if (isDraw(field)) {
                    System.out.println("This is draw!!!");
                    break;
                }

                doBotMove(field);
                drawField(field);
                if (isWin(field, 'O')) {
                    System.out.println("Sorry!!! You are loser. :(");
                    break;
                }
                if (isDraw(field)) {
                    System.out.println("This is draw!!!");
                    break;
                }
            } while (true);
        } catch (InputMismatchException e) {
            System.out.println("exit");
        }

    }

    static boolean isDraw(char[][] field) {
        for (int v = 0; v < field.length; v++) {
            for (int h = 0; h < field.length; h++) {
                if (isEmptyCell(field, v, h)) {
                    return false;
                }
            }
        }
        return true;
    }

    static boolean isEmptyCell(char[][] field, int v, int h) {
        return field[v][h] == EMPTY_CELL_SYMBOL;
    }

    static boolean isNotEmptyCell(char[][] field, int v, int h) {
        return !isEmptyCell(field, v, h);
    }

    static boolean isWin(char[][] field, char sign) {
        //Horizontal
        for (char[] chars : field) {
            for (int j = 0; j < field.length; j++) {
                if (chars[j] != sign) break;
                if (j == field.length - 1) return true;
            }
        }
        //Vertical
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field.length; j++) {
                if (field[j][i] != sign) break;
                if (j == field.length - 1) return true;
            }
        }
        //Main Diagonal
        for (int i = 0; i < field.length; i++) {
            if (field[i][i] != sign) break;
            if (i == field.length - 1) return true;
        }
        //Side Diagonal
        for (int i = 0; i < field.length; i++) {
            if (field[i][field.length - i - 1] != sign) break;
            if (i == field.length - 1) return true;
        }
        return false;
    }

    static void doBotMove(char[][] field) {
        Random random = new Random();

        int v, h;

        do {
            v = random.nextInt(fieldSize);
            h = random.nextInt(fieldSize);
        } while (isNotEmptyCell(field, v, h));

        field[v][h] = 'O';
    }

    static void doPlayerMove(char[][] field) {
        int v, h;
        do {
            v = getCoordinate(field, 'v');
            h = getCoordinate(field, 'h');
        } while (isNotEmptyCell(field, v, h));

        field[v][h] = 'X';
    }

    static int getCoordinate(char[][] field, char coordinateName) {
        Scanner scanner = new Scanner(System.in);

        int coordinate;
        do {
            System.out.printf("Please enter %s-coordinate [1..%d] ...%n", coordinateName, fieldSize);
            coordinate = scanner.nextInt() - 1;
        } while (coordinate < 0 || coordinate >= field.length);

        return coordinate;
    }

    static char[][] createField() {
        //Scanner scanner = new Scanner(System.in);
        //System.out.print("Please enter field size...");
        //fieldSize = scanner.nextInt();
        char[][] field = new char[fieldSize][fieldSize];
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field.length; j++) {
                field[i][j] = EMPTY_CELL_SYMBOL;
            }
        }
        return field;
    }

    static void drawField(char[][] field) {
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field.length; j++) {
                System.out.print(field[i][j]);
                System.out.print(" ");
            }
            System.out.println();
        }

        System.out.println();
    }

}