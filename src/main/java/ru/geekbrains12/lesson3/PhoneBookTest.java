package ru.geekbrains12.lesson3;

public class PhoneBookTest {
    public static void main(String[] args) {
        PhoneBook pb = new PhoneBook();
        pb.add("+7(495)500-00-00", "Иванов");
        pb.add("+7(495)600-00-00", "Петров");
        pb.add("+7(495)700-00-00", "Сидоров");
        pb.add("+7(495)800-00-00", "Иванов");

        pb.get("Иванов");
    }
}
