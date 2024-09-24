package net.flectone.lab.one;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class CollectionLab {

    private final static Path FILE_PATH = Paths.get("test.txt");
    private final static Path STRINGS_FILE_PATH = Paths.get("strings.txt");

    private static final Map<String, String> TELEPHONE_HUMAN = new HashMap<>(Map.of(
            "+79228440145", "Артём",
            "+79228440146", "Артём",
            "+79228440147", "Артём",
            "+79228440148", "Артём"
    ));

    private static final Map<String, List<String>> HUMAN_TELEPHONES = new HashMap<>(Map.of(
            "Артём", List.of(
                    "+79228440145",
                    "+79228440146",
                    "+79228440147",
                    "+79228440148"
            )
    ));

    private static final List<String> EMPLOYEES = List.of(
            "Иван Иванов",
            "Светлана Петрова",
            "Кристина Белова",
            "Анна Мусина",
            "Анна Крутова",
            "Иван Юрин",
            "Петр Лыков",
            "Павел Чернов",
            "Петр Чернышов",
            "Мария Федорова",
            "Марина Светлова",
            "Мария Савина",
            "Мария Рыкова",
            "Марина Лугова",
            "Анна Владимирова",
            "Иван Мечников",
            "Петр Петин",
            "Иван Ежов"
    );

    private static List<String> sortFileStringsByLength(Path filePath) throws IOException {
        List<String> strings = Files.readAllLines(filePath);
        strings.sort(Comparator.comparing(String::length));

        return strings;
    }

    private static Map<String, Integer> countEmployeeNames(List<String> employees) {
        Map<String, Integer> nameCount = new HashMap<>();

        employees.forEach(student -> {

            String studentName = student.split(" ")[0];
            int k = nameCount.getOrDefault(studentName, 0) + 1;

            nameCount.put(studentName, k);

        });

        return nameCount;
    }

    private static void printSortedByValue(Map<String, Integer> nameCount) {
        nameCount.entrySet()
                .stream()
                .filter(entry -> entry.getValue() > 1)
                .sorted(Map.Entry.comparingByValue())
                .toList()
                .reversed()
                .forEach(entry -> System.out.println(entry.getKey() + " " + entry.getValue()));
    }

    public static void main(String[] args) throws IOException {
        // Задание:
        // Реализуйте структуру телефонной книги с помощью HashMap,
        // учитывая, что 1 человек может иметь несколько телефонов.

        // Вариант реализации, если ключом должен быть телефон
        // и человек может иметь несколько телефонов
        System.out.println(TELEPHONE_HUMAN);

        // Вариант реализации, если ключом должен быть человек
        // и человек может иметь несколько телефонов
        System.out.println(HUMAN_TELEPHONES);

        // Задание:
        // Пусть дан список сотрудников
        // Написать программу, которая найдет и выведет повторяющиеся имена с количеством повторений.
        // Отсортировать по убыванию популярности.
        Map<String, Integer> nameCount = countEmployeeNames(EMPLOYEES);
        printSortedByValue(nameCount);

        // Задание:
        // Ввести строки из файла, записать в список ArrayList.
        // Выполнить сортировку строк по длине.
        System.out.println(sortFileStringsByLength(FILE_PATH));
        System.out.println(sortFileStringsByLength(STRINGS_FILE_PATH));
    }
}
