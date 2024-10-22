package net.flectone.lab.two.employee;

public class EmployeeHour extends Employee {

    public EmployeeHour(String name, double salaryInHour) {
        super(name, x -> x * salaryInHour);
    }
}
