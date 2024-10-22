package net.flectone.lab.two.company;

import net.flectone.lab.two.employee.Employee;

public class Manager extends Employee {

    public Manager(String name, double fixSalary, double companyIncome) {
        super(name, x -> fixSalary + (companyIncome / 100) * x);
    }
}
