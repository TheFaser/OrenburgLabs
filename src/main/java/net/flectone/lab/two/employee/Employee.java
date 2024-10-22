package net.flectone.lab.two.employee;

import lombok.Getter;

// Создать базовый класс – работник, и производные классы – служащий с почасовой оплатой,
// служащий в штате и служащий с процентной ставкой.
// Определить функцию начисления зарплаты.
// Для каждого класса провести модульное тестирование основных методов класса.

@Getter
public abstract class Employee {

    private final String name;
    private final InterfaceSalary interfaceSalary;

    private double allSalary;

    public Employee(String name, InterfaceSalary interfaceSalary) {
        this.name = name;
        this.interfaceSalary = interfaceSalary;
    }

    public void addSalary(double value) {
        allSalary += calculateSalary(value);
    }

    public double calculateSalary(double value) {
        return interfaceSalary.calculateSalary(value);
    }

    @FunctionalInterface
    public interface InterfaceSalary {
        double calculateSalary(double x);
    }
}
