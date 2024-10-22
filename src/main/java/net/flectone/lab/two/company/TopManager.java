package net.flectone.lab.two.company;

import net.flectone.lab.two.employee.Employee;

public class TopManager extends Employee {

    public TopManager(String name, double fixSalary, double companyIncome) {
        super(name, x -> fixSalary + (companyIncome > 10000000 ? (fixSalary/100) * x : 0));
    }
}
