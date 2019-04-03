package lesson30.hw;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Department extends IdEntity {

    private long id;
    private DepartmentType type;
    private HashSet<Employee> employees = new HashSet<>();

    public Department(long id, DepartmentType type) {
        this.id = id;
        this.type = type;
    }

    @Override
    public long getId() {
        return id;
    }

    public DepartmentType getType() {
        return type;
    }

    public HashSet<Employee> getEmployees() {
        return employees;
    }

    public Employee addEmployee(EmployeeDAO employeeDAO, Employee employee) {
        if (employeeDAO.getCollection().contains(employee)) {
            employees.add(employee);
            return employee;
        }
        System.out.println("Employee with id " + employee.getId() + " does not exist");
        return null;
    }

    public void deleteEmployee(Employee employee) {
        if (employees.contains(employee)) {
            employees.remove(employee);
            return;
        }
        System.out.println("Employee with id " + employee.getId() + " does not exist");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Department that = (Department) o;
        return type == that.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(type);
    }

    @Override
    public String toString() {
        return "Department{" +
                "type=" + type +
                //", employees=" + employees +
                '}';
    }
}
