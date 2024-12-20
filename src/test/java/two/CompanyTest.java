package two;

import net.flectone.lab.two.company.Company;
import net.flectone.lab.two.company.Manager;
import net.flectone.lab.two.company.Operator;
import net.flectone.lab.two.company.TopManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Random;

public class CompanyTest {

    @Test
    public void test() {

        double companyInMonthly = 1200000;

        Company company = new Company("NewCompany", companyInMonthly);
        Assertions.assertEquals(companyInMonthly, company.getMoneyMonthly());

        Manager manager = new Manager("manager", 40000, new Random().nextInt(115000, 140000));
        company.hire(manager);

        double managerSalary = manager.calculateSalary(5);
        Assertions.assertEquals(companyInMonthly - managerSalary, company.getIncome());

        company.fire(manager);
        Assertions.assertTrue(company.getEmployees().isEmpty());

        TopManager topManager = new TopManager("topManager", 50000, companyInMonthly);
        company.hire(topManager);

        double topManagerSalary = topManager.calculateSalary(150);
        Assertions.assertEquals(companyInMonthly - topManagerSalary, company.getIncome());

        company.fire(topManager);

        Operator operator = new Operator("operator", 35000);
        company.hire(operator);

        double operatorSalary = operator.calculateSalary(0);
        Assertions.assertEquals(companyInMonthly - operatorSalary, company.getIncome());

        company.fire(operator);

        company.hires(List.of(manager, topManager, operator));
        Assertions.assertEquals(3, company.getEmployees().size());
        Assertions.assertEquals(companyInMonthly - managerSalary - topManagerSalary - operatorSalary, company.getIncome());

        company.fires(List.of(manager, topManager));
        Assertions.assertEquals(1, company.getEmployees().size());

        company.fire(operator);
        Assertions.assertTrue(company.getEmployees().isEmpty());
    }

}
