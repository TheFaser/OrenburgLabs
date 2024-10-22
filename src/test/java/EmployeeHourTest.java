import net.flectone.lab.two.employee.EmployeeHour;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class EmployeeHourTest {

    @Test
    public void testSalary() {
        EmployeeHour employeeHour = new EmployeeHour("test", 20);

        double salary = employeeHour.calculateSalary(10);

        Assertions.assertEquals(200.0, salary);
    }

    @Test
    public void testAddSalary() {
        EmployeeHour employeeHour = new EmployeeHour("test", 20);

        Assertions.assertEquals(0.0, employeeHour.getAllSalary());

        employeeHour.addSalary(10);

        Assertions.assertEquals(200.0, employeeHour.getAllSalary());
    }
}
