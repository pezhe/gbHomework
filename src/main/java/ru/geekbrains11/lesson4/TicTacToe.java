package ru.geekbrains11.lesson4;

import java.util.InputMismatchException;
import java.util.Scanner;

public class TicTacToe {

    static final char EMPTY_CELL_SYMBOL = '-';
    static final char PLAYER_SIGN = 'X';
    static final char BOT_SIGN = 'O';
    static final int fieldSize = 3;
    static final int maxDepth = 3;

    public static void start() {

        char[][] field = createField();

        drawField(field);

        try {
            do {
                doPlayerMove(field);
                drawField(field);
                if (isWin(field, PLAYER_SIGN)) {
                    System.out.println("Congratulations!!! You are winner.");
                    break;
                }
                if (isDraw(field)) {
                    System.out.println("This is draw!!!");
                    break;
                }

                doBotMove(field);
                drawField(field);
                if (isWin(field, BOT_SIGN)) {
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
        int maxValue = -1;
        int bestMoveV = 0;
        int bestMoveH = 0;
        //Iterate and evaluate possible moves
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field.length; j++) {
                if (isEmptyCell(field, i, j)) {
                    int value = evaluateMove(field, i, j, true, 0);
                    if (moveIsBetter(value, maxValue)) {
                        maxValue = value;
                        bestMoveV = i;
                        bestMoveH = j;
                    }
                }
            }
        }
        field[bestMoveV][bestMoveH] = BOT_SIGN;
    }

    static int evaluateMove(char[][] field, int v, int h, boolean isBot, int depth) {
        if (++depth > maxDepth) return 0;
        field[v][h] = isBot ? BOT_SIGN : PLAYER_SIGN;
        if (isDraw(field)) {
            field[v][h] = EMPTY_CELL_SYMBOL;
            return 0;
        }
        if (isWin(field, isBot ? BOT_SIGN : PLAYER_SIGN)) {
            field[v][h] = EMPTY_CELL_SYMBOL;
            return 1;
        }

        //Best opposite move
        int maxValueB = -1;
        int bestMoveV = 0;
        int bestMoveH = 0;
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field.length; j++) {
                if (isEmptyCell(field, i, j)) {
                    int value = evaluateMove(field, i, j, !isBot, depth);
                    if (moveIsBetter(value, maxValueB)) {
                        maxValueB = value;
                        bestMoveV = i;
                        bestMoveH = j;
                    }
                }
            }
        }
        field[bestMoveV][bestMoveH] = !isBot ? BOT_SIGN : PLAYER_SIGN;
        //Best opposite move

        if (isWin(field, !isBot ? BOT_SIGN : PLAYER_SIGN)) {
            field[v][h] = EMPTY_CELL_SYMBOL;
            field[bestMoveV][bestMoveH] = EMPTY_CELL_SYMBOL;
            return -1;
        }
        int maxValue = -1;
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field.length; j++) {
                if (isEmptyCell(field, i, j)) {
                    int value = evaluateMove(field, i, j, isBot, depth);
                    if (moveIsBetter(value, maxValue)) maxValue = value;
                }
            }
        }
        if (maxValue > 0) {
            field[v][h] = EMPTY_CELL_SYMBOL;
            field[bestMoveV][bestMoveH] = EMPTY_CELL_SYMBOL;
            return maxValue + 1;
        }
        if (maxValue < 0) {
            field[v][h] = EMPTY_CELL_SYMBOL;
            field[bestMoveV][bestMoveH] = EMPTY_CELL_SYMBOL;
            return maxValue - 1;
        }
        field[v][h] = EMPTY_CELL_SYMBOL;
        field[bestMoveV][bestMoveH] = EMPTY_CELL_SYMBOL;
        return 0;
    }

    //Compare moves by value (value here is a tricky thing so simple mathematical comparison is not working)
    static boolean moveIsBetter(int value, int maxValue) {
        if ((value > 0 && maxValue > 0) || (value < 0 && maxValue < 0)) return value <= maxValue;
        return value > maxValue;
    }

    static void doPlayerMove(char[][] field) {
        int v, h;
        do {
            v = getCoordinate(field, 'v');
            h = getCoordinate(field, 'h');
        } while (isNotEmptyCell(field, v, h));

        field[v][h] = PLAYER_SIGN;
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
        char[][] field = new char[fieldSize][fieldSize];
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field.length; j++) {
                field[i][j] = EMPTY_CELL_SYMBOL;
            }
        }
        return field;
    }

    static void drawField(char[][] field) {
        for (char[] chars : field) {
            for (int j = 0; j < field.length; j++) {
                System.out.print(chars[j]);
                System.out.print(" ");
            }
            System.out.println();
        }

        System.out.println();
    }

}