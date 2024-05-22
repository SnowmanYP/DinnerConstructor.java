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
        random = new Random();
        // Это для теста
        dishes = new ArrayList<>(List.of("Борщ", "Суп гречневый", "Куриный бульон", "Суп с фрикадельками", "Окрошка"));
        menu.put("Первое", dishes);
        dishes = new ArrayList<>(List.of("Плов", "Мясо по-капитански", "Пельмени", "Ленивые голубцы", "Макароны по-флотски"));
        menu.put("Второе", dishes);
        dishes = new ArrayList<>(List.of("Гречка", "Рис", "Макароны", "Жареный картофель", "Тушеная капуста"));
        menu.put("Гарнир", dishes);
        dishes = new ArrayList<>(List.of("Оливье", "Винегрет", "Крабовый салат", "Овощной салат", "Летний салат"));
        menu.put("Салат", dishes);
        dishes = new ArrayList<>(List.of("Морс клюквенный", "Чай", "Минеральная вода", "Квас", "Молочный коктель"));
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

    public String getDish(String type, int number) { // Возвращает блюдо типа - type с индексом number в списке
        return menu.get(type).get(number);
    }

    boolean checkType(String type) { //Метод должен проверять существует ли такой тип блюд?
        return menu.containsKey(type);
    }

    boolean chekDish(String type, String dish) { //Метод должен проверять присутствие блюда в списке
        // Название блюд разных типов могут совпадать
        return menu.get(type).contains(dish);
    }

    int randomNumber(String type) { //Рандомайзер
        return random.nextInt(menu.get(type).size());
    }
}
