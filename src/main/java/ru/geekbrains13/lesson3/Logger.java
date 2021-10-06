package ru.geekbrains13.lesson3;

import java.io.*;

public class Logger {

    private final File file;

    public Logger(String path) {
        file = new File(path);
    }

    public String readLog(int lines) throws IOException {
        StringBuilder sb = new StringBuilder();
        try (RandomAccessFile raf = new RandomAccessFile(file, "r")) {
                int count = 0;
                for (long i = raf.length() - 1; i >= 0; i--) {
                    raf.seek(i);
                    int b = raf.readByte();
                    if (b == '\n') {
                        count++;
                        if (count == lines + 1) break;
                    }
                    sb.append((char)b);
                }
                return sb.reverse().toString();
            }
    }

    public void writeLog(String message) {
        try {
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(file, true))) {
                bw.write(message);
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Failed to write to log");
        }
    }

    public static void main(String[] args) throws IOException { // Тест логирования
        Logger logger = new Logger("D:/test_log.txt");
        logger.writeLog("line 1");
        logger.writeLog("line 2");
        logger.writeLog("line 3");
        logger.writeLog("line 4");
        String getThree = logger.readLog(3);
        System.out.println(getThree);
        String getFive = logger.readLog(5);
        System.out.println(getFive);
    }

}