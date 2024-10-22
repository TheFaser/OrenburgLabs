import net.flectone.lab.two.company.Manager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Random;

public class OperatorTest {


    @Test
    public void test() {

        double companyProfit = new Random().nextInt(115000, 140000);

        Manager manager = new Manager("manager", 40000, companyProfit);

        Assertions.assertEquals(40000 + (companyProfit/100) * 5, manager.calculateSalary(5));
    }

}
