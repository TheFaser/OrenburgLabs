package net.flectone.lab.two.company;

import net.flectone.lab.two.employee.Employee;

public class Operator extends Employee {

    public Operator(String name, double fixPrice) {
        super(name, _ -> fixPrice);
    }
}
