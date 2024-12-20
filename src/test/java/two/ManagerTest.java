package two;

import net.flectone.lab.two.company.Operator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ManagerTest {

    @Test
    public void test() {

        Operator operator = new Operator("operator", 35000);
        Assertions.assertEquals(35000, operator.calculateSalary(0));
    }
}
