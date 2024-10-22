package net.flectone.lab.two.employee;

public class EmployeePercent extends Employee {

    public EmployeePercent(String name, double salary) {
        super(name, x -> (salary / 100) * x);
    }
}
