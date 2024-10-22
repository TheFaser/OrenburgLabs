import net.flectone.lab.two.company.Operator;
import net.flectone.lab.two.company.TopManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TopManagerTest {

    @Test
    public void test() {

        double companyInMonthly = 10000000;

        TopManager topManager = new TopManager("topManager", 50000, companyInMonthly);

        Assertions.assertEquals(50000, topManager.calculateSalary(150));

        companyInMonthly = 13000000;
        TopManager newTopManager = new TopManager("topManager", 50000, companyInMonthly);
        Assertions.assertEquals(125000, newTopManager.calculateSalary(150));
    }

}
