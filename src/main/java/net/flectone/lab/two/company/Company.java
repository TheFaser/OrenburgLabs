package net.flectone.lab.two.company;

import lombok.Getter;
import lombok.Setter;
import net.flectone.lab.two.employee.Employee;
import net.flectone.lab.two.employee.EmployeeHour;
import net.flectone.lab.two.employee.EmployeePercent;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

// Создайте класс компания Company, содержащей сотрудников и реализующей методы:
// •	найм одного сотрудника — hire(),
// •	найм списка сотрудников – hireAll(),
// •	увольнение сотрудника – fire(),
// •	получение значения дохода компании – getIncome().
// Аргументы и возвращаемое значение методов выберите на основании логики работы вашего приложения.
// Создайте два метода, возвращающие список указанной длины (count). Они должны содержать сотрудников, отсортированных по убыванию и возрастанию заработной платы:
// •	List<Employee> getTopSalaryStaff(int count),
// •	List<Employee> getLowestSalaryStaff(int count).
// Создайте классы сотрудников с информацией о зарплатах и условиями начисления зарплаты:
// •	Manager — зарплата складывается из фиксированной части и бонуса в виде 5% от заработанных для компании денег. Количество заработанных денег для компании генерируйте случайным образом от 115 000 до 140 000 рублей.
// •	TopManager — зарплата складывается из фиксированной части и бонуса в виде 150% от заработной платы, если доход компании более 10 млн рублей.
// •	Operator — зарплата складывается только из фиксированной части.
// Каждый класс сотрудника должен реализовывать метод, возвращающий зарплату сотрудника:
// •	getMonthSalary()
// Аргументы и возвращаемое значение метода выберите в соответствии с логикой начисления зарплат.
// Для каждого класса провести модульное тестирование основных методов класса.

// p.s.
// использовал уже созданный в прошлом задании класс Employee
// и getMonthSalary() реализовал как calculateSalary(double valueToCalculateSalary)

@Getter
public class Company {

    private final String name;
    private final List<Employee> employees = new ArrayList<>();

    @Setter
    private double moneyMonthly;

    public Company(String name, double moneyMonthly) {
        this.name = name;
        this.moneyMonthly = moneyMonthly;
    }

    public void hire(Employee employee) {
        this.employees.add(employee);
    }

    public void hires(List<Employee> employees) {
        this.employees.addAll(employees);
    }

    public void fire(Employee employee) {
        this.employees.remove(employee);
    }

    public void fires(List<Employee> employees) {
        this.employees.removeAll(employees);
    }

    public List<Employee> getTopSalaryStaff(int count) {
        return employees.stream().limit(count)
                .sorted(Comparator.comparing(this::currentEmployee))
                .limit(count)
                .toList();
    }

    public List<Employee> getLowestSalaryStaff(int count) {
        return employees.stream().limit(count)
                .sorted(Comparator.comparing(this::currentEmployee))
                .toList()
                .reversed()
                .stream()
                .limit(count)
                .toList();
    }

    private double currentEmployee(Employee employee) {
        double valueToCalculateSalary = 0;

        if (employee instanceof EmployeeHour) {
            // допустим каждый сотрудник поработал 1 час каждый день т.е. 30 часов
            valueToCalculateSalary = 30;
        } else if (employee instanceof EmployeePercent) {
            // допустим сотрудник получает 10% от зарплаты ему назначенной
            valueToCalculateSalary = 10;
        } else if (employee instanceof Manager) {
            // бонус в виде 5%
            valueToCalculateSalary = 5;
        } else if (employee instanceof TopManager) {
            // бонус в виде 150%, если доход компании более 10 млн рублей
            valueToCalculateSalary = 150;
        }

        return employee.calculateSalary(valueToCalculateSalary);
    }

    // получаем прибыль компании, вычитая зарплаты сотрудников
    public double getIncome() {

        double income = moneyMonthly;

        for (Employee employee : employees) {
            income -= currentEmployee(employee);
        }

        return income;
    }
}
