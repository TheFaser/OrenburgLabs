package three;

import net.flectone.lab.three.Person;
import net.flectone.lab.three.PersonManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PersonManagerTest {

    @Test
    public void testCreatePerson() {

        PersonManager personManager = new PersonManager();

        try {
            // test incorrect email
            personManager.create("Test", "123@gmail345dcom", new Date());
            Assertions.fail();

        } catch (IllegalArgumentException ignored) {}

        try {
            // test incorrect birth year
            personManager.create("Test", "123@gmail.com", new Date(System.currentTimeMillis()*2));
            Assertions.fail();

        } catch (IllegalArgumentException ignored) {}

        Person person = personManager.create("Test", "123@gmail.com", new Date());

        // test name
        Assertions.assertEquals("Test", person.getName());
        // test email
        Assertions.assertEquals("123@gmail.com", person.getEmail());
        // test birth year
        Assertions.assertEquals(new Date().getYear() + 1900, person.getBirthYear());
        // test age
        Assertions.assertEquals(new Date().getYear() + 1900 - person.getBirthYear(), person.getAge());
    }

    @Test
    public void testCreatePersons() {
        PersonManager personManager = new PersonManager();
        List<Person> persons = personManager.createPersons();

        Assertions.assertNotNull(persons);
        Assertions.assertEquals(3, persons.size());
    }

    @Test
    public void testAverageAge() {
        PersonManager personManager = new PersonManager();

        List<Person> persons = new ArrayList<>();
        persons.add(personManager.create("Test", "123@gmail.com", new Date()));

        Assertions.assertEquals(0.0, personManager.averageAge(persons));

        Date date = new Date();
        date.setYear(date.getYear() - 1);
        persons.add(personManager.create("Test", "123@gmail.com", date));

        Assertions.assertEquals(0.5, personManager.averageAge(persons));
    }
}
