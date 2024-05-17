package ru.practicum.dinner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class DinnerConstructor {
    HashMap<String, ArrayList<String>> menu;
    Random random;
    ArrayList<String> dishes;

    DinnerConstructor() {

        menu = new HashMap<>();
        random=new Random();
        // Это для теста
        dishes=new ArrayList<>(List.of("Борщ", "Суп гречневый", "Куриный бульон","Суп с фрикадельками","Окрошка"));
        menu.put("Первое", dishes);

        dishes=new ArrayList<>(List.of("Плов", "Мясо по-капитански", "Пельмени","Ленивые голубцы","Макароны по-флотски"));
        menu.put("Второе", dishes);

        dishes=new ArrayList<>(List.of("Гречка", "Рис", "Макароны","Жареный картофель","Тушеная капуста"));
        menu.put("Гарнир", dishes);

        dishes=new ArrayList<>(List.of("Оливье", "Винегрет", "Крабовый салат","Овощной салат","Летний салат"));
        menu.put("Салат", dishes);

        dishes=new ArrayList<>(List.of("Морс клюквенный", "Чай", "Минеральная вода","Квас","Молочный коктель"));
        menu.put("Напиток", dishes);
    }

    public void putTypeDish(String newTypeOfDish) { //Добавляем новый Тип блюд
        if (!checkType(newTypeOfDish)) {
            ArrayList<String> newList = new ArrayList<>();
            menu.put(newTypeOfDish, newList);
        } else System.out.println("Такой тип блюд уже существует!");
    }

    public void putDish(String TypeOfDish, String newDish) { //Добавляет новое блюдо
        if (!chekDish(TypeOfDish, newDish)) menu.get(TypeOfDish).add(newDish);
        else System.out.println("Блюдо уже в списке.");
    }

    public String getDish(String type, int number){ // Возвращает блюдо типа - type с индексом number в списке
        return menu.get(type).get(number); }

    boolean checkType(String type) { //Метод должен проверять существует ли такой тип блюд?
        if (menu.containsKey(type)) return true;
        else return false;
    }

    boolean chekDish(String type, String dish) { //Метод должен проверять присутствие блюда в списке
        // Название блюд разных типов могут совпадать
        if (menu.get(type).contains(dish)) return true;
        else return false;
    }

    public void printAllMenu() { //Метод печатает таблицу тип блюда - названия блюд. Написан для тестированияю
        for (String type : menu.keySet()) {
            System.out.println("\t"+type);
            for (String dish : menu.get(type)) {
                System.out.print(dish+"\t ");
            }
        }
    }

    int randomNumber(String type){ //Рандомайзер
        //double r=Math.random(); //случайное число от 0 до 1 в формате double
        //return (int) (r*menu.get(type).size()); //случайное число * длину списка и сразу приводим к целому числу
        return random.nextInt(menu.get(type).size()); //Так тоже интересно
    }
}
