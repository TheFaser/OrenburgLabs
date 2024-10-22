import net.flectone.lab.two.employee.EmployeePercent;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class EmployeePercentTest {

    @Test
    public void testSalary() {
        EmployeePercent employeePercent = new EmployeePercent("test", 1000);

        double salary = employeePercent.calculateSalary(50);

        Assertions.assertEquals(500, salary);
    }

    @Test
    public void testAddSalary() {
        EmployeePercent employeePercent = new EmployeePercent("test", 20);

        Assertions.assertEquals(0.0, employeePercent.getAllSalary());

        employeePercent.addSalary(10);

        Assertions.assertEquals(2.0, employeePercent.getAllSalary());
    }
}
