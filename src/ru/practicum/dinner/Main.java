package ru.practicum.dinner;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    static DinnerConstructor dc;
    static Scanner scanner;

    public static void main(String[] args) {
        dc = new DinnerConstructor();
        scanner = new Scanner(System.in);
        while (true) {
            printMenu();
            String command = scanner.nextLine();
            switch (command) {
                case "1":
                    addNewDish();
                    break;
                case "2":
                    generateDishCombo();
                    break;
                case "3":
                    return;
            }
        }
    }

    private static void printMenu() {
        System.out.println("Выберите команду:");
        System.out.println("1 - Добавить новое блюдо");
        System.out.println("2 - Сгенерировать комбинации блюд");
        System.out.println("3 - Выход");
    }

    private static void addNewDish() {
        System.out.println("Введите тип блюда:");
        String dishType = scanner.nextLine();
        dc.putTypeDish(dishType);
        System.out.println("Введите название блюда:");
        String dishName = scanner.nextLine();
        dc.putDish(dishType, dishName);
    }

    private static void generateDishCombo() {
        System.out.println("Начинаем конструировать обед...");
        System.out.println("Введите количество наборов, которые нужно сгенерировать:");
        int numberOfCombos;// = scanner.nextInt(); //Обработка ошибок
        // Exception in thread "main" java.util.InputMismatchException
        while (true) {  //Этот блок обрабатывает ошибки, если пользователь ввел не число.
            // Нет возможности вынестив в отдельный метод т.к. запрещено передавать объект Scanner.
            try {
                numberOfCombos = scanner.nextInt();
                break;
            } catch (InputMismatchException e) {
                System.out.println("Вы ввели не число, введите число");
                scanner.nextLine();
            }
        }
        if (numberOfCombos < 1) {
            System.out.println("Количество наборов не может быть меньше 1, количество наборов=1");
            numberOfCombos = 1;
        }
        scanner.nextLine();
        System.out.println("Вводите типы блюда, разделяя символом переноса строки (enter). Для завершения ввода введите пустую строку");
        String nextItem = " "; //scanner.nextLine(); - если оставить - значения первых блюд теряются
        //реализуйте ввод типов блюд
        ArrayList<String>[] group = new ArrayList[numberOfCombos]; // Создаем массив списков.
        for (int i = 0; i < numberOfCombos; i++) { //и сразу создаем объекты, это позволит обращаться к методам...
            // ...сразу из ячеек
            group[i] = new ArrayList<>();
        }
        while (!nextItem.isEmpty()) {
            nextItem = scanner.nextLine();
            if (dc.checkType(nextItem)) {
                int rnd = dc.randomNumber(nextItem);  // случайное число от 0 до размера списка
                for (int i = 0; i < group.length; i++) {
                    group[i].add(dc.getDish(nextItem, rnd)); // array[i].add.... - можно сразу обратится к методу
                    rnd = dc.randomNumber(nextItem);  //Перезагружаем рандомайзер
                }
            }
            if (!dc.checkType(nextItem) && !nextItem.isEmpty()) {
                System.out.println("Такого типа блюд нет в меню, повторите попытку.");
            }
            // сгенерируйте комбинации блюд и выведите на экран
        }
        for (int i = 0; i < group.length; i++) {
            System.out.println("Комбо "+(i+1));
            System.out.println(group[i]);
        }
    }
}
