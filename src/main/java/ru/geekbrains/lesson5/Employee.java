package ru.geekbrains.lesson5;

public class Employee {

    private String fullName;
    private String position;
    private String email;
    private String phoneNumber;
    private int salary;
    private int age;

    public Employee(String fullName, String position, String email, String phoneNumber, int salary, int age) {
        this.fullName = fullName;
        this.position = position;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.salary = salary;
        this.age = age;
    }

    public void print() {
        System.out.printf("%s, position: %s, email: %s, phone: %s, salary: %d, age: %d\n",
                fullName, position, email, phoneNumber, salary, age);
    }

    public static void printEmployeesAboveForty(Employee[] empArr) {
        for (Employee e : empArr) {
            if (e.age > 40) e.print();
        }
    }

    public static void main(String[] args) {

        Employee[] empArr = new Employee[5];
        empArr[0] = new Employee("Чехов Антон Павлович", "разработчик",
                "chap@hornsandhooves.com","555-55-55", 3000, 22);
        empArr[1] = new Employee("Толстой Лев Николаевич", "старший разработчик",
                "tln@hornsandhooves.com","555-55-56", 4000, 27);
        empArr[2] = new Employee("Достоевский Фёдор Михайлович", "начальник отдела",
                "dfm@hornsandhooves.com","555-55-57", 5000, 44);
        empArr[3] = new Employee("Фет Афанасий Афанасьевич", "руководитель проекта",
                "faa@hornsandhooves.com","555-55-58", 5000, 35);
        empArr[4] = new Employee("Пушкин Александр Сергеевич", "вице-президент",
                "puh@hornsandhooves.com","555-55-55", 3000, 41);

        printEmployeesAboveForty(empArr);

    }
}
