import java.util.ArrayList;

// Abstraction
abstract class Employee{
    // Access modifiers
    private String name;
    private int id;

    // Constructor(Parameterize)
    public Employee(String name, int id){
        // 'this' keyword
        this.name = name;
        this.id = id;
    }

    // Encapsulation
    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public int getId(){
        return id;
    }

    public abstract double calculateSalary();

}

// Interface
interface Bonus{
    double calculateBonus();
}

// Inheritance
class FullTimeEmployee extends Employee implements Bonus{
    private double salary;

    // Constructor Chaining
    public FullTimeEmployee(String name, int id){
        this(name, id, 50000);
    }

    public FullTimeEmployee(String name, int id, double salary){
        // 'super' keyword
        super(name, id);
        this.salary = salary;
    }

    // Polymorphism
    @Override
    public double calculateSalary(){
        return salary;
    }

    @Override
    public double calculateBonus() {
        return salary * 0.1;
    }

    @Override
    public String toString(){
        return "[Full-Time Employee Name: " + getName() + " ID: " + getId() + " Salary: " + calculateSalary()+ " Bonus: " + calculateBonus() + "]";
    }

}

class PartTimeEmployee extends Employee implements Bonus{
    private int hoursWorked;
    private double hourlyRate;

    public PartTimeEmployee(String name, int id, int hoursWorked, double hourlyRate){
        super(name, id);
        this.hoursWorked = hoursWorked;
        this.hourlyRate = hourlyRate;
    }

    @Override
    public double calculateSalary(){
        return hoursWorked * hourlyRate;
    }

    @Override
    public double calculateBonus() {
        if(hoursWorked > 30){
            return (hoursWorked - 30) * 550;
        } else {
            return 0;
        }
    }

    @Override
    public String toString(){
        return "[Part-Time Employee Name: " + getName() + " ID: " + getId() + " Salary: " + calculateSalary()+ " Bonus: " + calculateBonus() + "]";
    }

}

class PayrollSystem{
    // Data structure (ArrayList(Dynamic))
    private ArrayList<Employee> employeeList;

    public PayrollSystem(){
        employeeList = new ArrayList<>();
    }

    public void addEmployee(Employee employee){
        employeeList.add(employee);
    }

    public void removeEmployee(int id){
        Employee employeeToRemove = null;

        for(Employee emp : employeeList){
            if(emp.getId() == id){
                employeeToRemove = emp;
                break;
            }
        }

        if(employeeToRemove != null){
            employeeList.remove(employeeToRemove);
        } else {
            System.out.println("Employee not found");
        }
    }

    public void displayEmployees(){
        for(Employee emp : employeeList){
            System.out.println(emp);
        }
    }

}

public class Main {
    public static void main(String[] args) {
        System.out.println("Employee Payroll System");

        PayrollSystem ps = new PayrollSystem();

        FullTimeEmployee emp1 = new FullTimeEmployee("Sujal", 1, 86000);
        PartTimeEmployee emp2 = new PartTimeEmployee("Don", 2, 40, 450);
        FullTimeEmployee emp3 = new FullTimeEmployee("Ram", 3);

        ps.addEmployee(emp1);
        ps.addEmployee(emp2);

        System.out.println("Initial Employee Details: ");
        ps.displayEmployees();

        System.out.println("Removing Employee");
        ps.removeEmployee(2);

        System.out.println("After removing Employee Details: ");
        ps.displayEmployees();

    }
}