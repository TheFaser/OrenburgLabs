package net.flectone.lab.three;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.NonNull;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * Class for working with Person objects
 */
public class PersonManager {

    private final Logger LOGGER = Logger.getLogger(PersonManager.class.getName());
    private final Gson GSON = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd")
            .setPrettyPrinting()
            .create();

    private final Pattern EMAIL_PATTERN = Pattern.compile("^\\s*(.+)@(.+)\\.(.+)\\s*$");

    public PersonManager() {
        FileHandler fileHandler;
        try {
            fileHandler = new FileHandler("logs.txt");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        SimpleFormatter simpleFormatter = new SimpleFormatter();
        fileHandler.setFormatter(simpleFormatter);

        LOGGER.addHandler(fileHandler);
    }

    /**
     * Completing task
     */
    public void stepByStep() {
        // 1.
        // Создайте список объектов класса Person с полями name и дата рождения, email.
        LOGGER.info("Creating persons...");
        createPersons()
                .forEach(person -> LOGGER.info("Created person: " + person.toString()));

        // 2.
        // Информацию о Person считаваем из json-файла. Логируйте результаты о прочтении
        List<Person> persons = new ArrayList<>();

        LOGGER.info("Loading persons...");
        try {
            List<Person> loadedPersons = loadPersons("input.json");
            loadedPersons.forEach(person -> LOGGER.info("Loaded person: " + person));

            persons.addAll(loadedPersons);
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
        }

        // 3.
        // Используя Stream API, отфильтруйте людей старше 18 лет и соберите их имена и email в новый список.
        // Отфильтрованные данные записывайте также в JSON-файл. Логируйте имена пользователей, которые прошли фильтр.
        List<Person> filteredPersons = persons.stream()
                .filter(person -> person.getAge() > 18)
                .toList();

        filteredPersons.forEach(person -> LOGGER.info("Person older than 18 year " + person));

        Map<String, String> personEmailMap = filteredPersons.stream()
                .collect(Collectors.toMap(Person::getName, Person::getEmail));

        LOGGER.info("Saving email person map...");
        try {
            save(personEmailMap, "personEmailMap.json");
            LOGGER.info("Saved successfully.");
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
        }

        // 4.
        // Определить средний возраст людей.
        LOGGER.info("Average age: " + averageAge(persons));

        // 5.
        // Выведите список людей, которые родились в высокосный год.
        persons.stream()
                .filter(person -> person.getBirthYear() % 4 == 0 && person.getBirthYear() % 100 != 0
                        || person.getBirthYear() % 400 == 0)
                .forEach(person -> LOGGER.info("Person in leap year: " + person));

        // 6.
        // Используя Stream API, сгруппируйте людей по возрастным группам (ребенок, молодежь, пожилой).
        List<Person> children = persons.stream()
                .filter(person -> person.getAge() < 18)
                .toList();

        children.forEach(person -> LOGGER.info("Children: " + person));

        List<Person> youngPersons = persons.stream()
                .filter(person -> person.getAge() >= 18 && person.getAge() < 55)
                .toList();

        youngPersons.forEach(person -> LOGGER.info("Young: " + person));

        List<Person> olderPersons = persons.stream()
                .filter(person -> person.getAge() >= 55)
                .toList();

        olderPersons.forEach(person -> LOGGER.info("Older: " + person));

        // 7.
        // Используйте Predicate, Function и Consumer для работы со списком сотрудников.

        Predicate<Person> isOlder = person -> person.getBirthDate().getYear() < 65;
        Function<Person, String> getName = Person::getName;
        Consumer<Person> printName = person -> LOGGER.info("Name: " + getName.apply(person));

        LOGGER.info("Printing older persons names...");
        persons.stream()
                .filter(isOlder)
                .forEach(printName);

        // 8.
        // Информацию о возникающих исключениях также записывайте в лог
        // ok

        // 9.
        // Реализуйте метод, который выполняет длительную операцию (например, сортировку большого массива).
        // Измерьте время выполнения и залогируйте его с помощью логгера.

        long start = System.currentTimeMillis();

        veryLongOperation();

        long end = System.currentTimeMillis();

        LOGGER.info("Time: " + (end - start) + "ms");
    }

    /**
     * Create person object
     * @param name person name
     * @param email person email
     * @param birthDate person birth date
     * @return Person object
     */
    public Person create(@NonNull String name, @NonNull String email, @NonNull Date birthDate) {
        if (birthDate.getYear() > new Date().getYear()) {
            throw new IllegalArgumentException("Date of birth must be less than or equal to the year");
        }

        if (!isEmail(email)) {
            throw new IllegalArgumentException("Email address is invalid");
        }

        return new Person(name, email, birthDate);
    }

    /**
     * Create 3 persons
     * @return List of persons
     */
    public List<Person> createPersons() {
        List<Person> persons = List.of(
                create("John", "test@gmail.com", new Date()),
                create("Jane", "test2@gmail.com", new Date()),
                create("Jim", "test3@gmail.com", new Date())
        );

        return persons;
    }

    /**
     * Load persons from file
     * @param filePath file path
     * @return List of persons
     * @throws IOException if file not found
     */
    public List<Person> loadPersons(String filePath) throws IOException{

        List<Person> persons = new ArrayList<>();

        try (Reader reader = Files.newBufferedReader(Paths.get(filePath))) {
            persons.addAll(List.of(GSON.fromJson(reader, Person[].class)));
        }

        return persons;
    }

    /**
     * Save persons to file
     * @param object object to save
     * @param filePath file path
     * @throws IOException if file error
     */
    public void save(Object object, String filePath) throws IOException {
        try (Writer writer = Files.newBufferedWriter(Paths.get(filePath))) {
            GSON.toJson(object, writer);
        }
    }

    private void veryLongOperation() {
        double sum = 0;

        for (int i = 0; i < 1000; i++) {
            for (int j = 0; j < 1000; j++) {
                for (int k = 0; k < 1000; k++) {
                    sum++;
                }
            }
        }
    }

    /**
     * Calculate average age
     * @param persons list of persons
     * @return average age
     */
    public double averageAge(List<Person> persons) {
        return persons.stream()
                .mapToInt(Person::getAge)
                .average()
                .orElse(0);
    }

    /**
     * Check email
     * @param email String email
     * @return true if email is valid
     */
    public boolean isEmail(String email) {
        return EMAIL_PATTERN.matcher(email).find();
    }
}
