package gromecode.lesson30.hw;

import java.util.HashSet;

public class EmployeeDAO {

    private HashSet<Employee> collection = new HashSet<>();

    public HashSet<Employee> getCollection() {
        return collection;
    }

    public String toString() {

        return "EmployeeDAO{" +
                "employee=" + collection +
                '}';
    }

    HashSet<Employee> employeesByProject(String projectName) {
        HashSet<Employee> employees = new HashSet<>();

        for (Employee employee : collection) {
            if (employee != null && isEmployeeHaveProject(employee, projectName)) {
                employees.add(employee);
            }
        }
        return employees;
    }

    HashSet<Employee> employeesByDepartmentWithoutProject(DepartmentType departmentType) {

        HashSet<Employee> employees = new HashSet<>();

        for (Employee employee : collection) {
            if (employee != null && employee.getDepartment().getType() == departmentType && employee.getProjects().isEmpty()) {
                employees.add(employee);
            }
        }
        return employees;
    }

    HashSet<Employee> employeesWithoutProject() {

        HashSet<Employee> emploees = new HashSet<>();

        for (Employee employee : collection) {
            if (employee != null && employee.getProjects().isEmpty()) {
                emploees.add(employee);
            }
        }
        return emploees;
    }

    HashSet<Employee> employeesByTeamLead(Employee lead) {

        //узнать какие проекты есть у лидера

        HashSet<Project> projects = new HashSet<>();

        for (Employee employee : collection) {
            if (employee != null && employee.equals(lead)) {
                projects = employee.getProjects();
                break;
            }
        }

        //найти всех работников, у кого такие же проекты как у лидера
        //лидера не учитывать
        HashSet<Employee> newEmploees = new HashSet<>();

        for (Project project : projects) {
            for (Employee employee : collection) {
                if (employee != null && employee.getProjects().contains(project) && employee.getPosition() != lead.getPosition()) {
                    newEmploees.add(employee);
                }
            }
        }

        return newEmploees;
    }

    HashSet<Employee> teamLeadsByEmployee(Employee employee) {

        HashSet<Project> projects = employee.getProjects();

        //проверить во всех проектах сотрудника всех лидеров
        //сохранить в новый список
        HashSet<Employee> leads = new HashSet<>();

        for (Project project : projects) {
            for (Employee empl : collection) {
                if (empl != null && empl.getProjects().contains(project) && empl.getPosition() == Position.TEAM_LEAD) {
                    leads.add(empl);
                }
            }
        }
        return leads;
    }

    HashSet<Employee> employeesByProjectEmployee(Employee employee) {
        HashSet<Project> projects = employee.getProjects();

        //проверить во всех проектах сотрудника всех остальных сотрудников кроме лидеров
        //сохранить в новый список
        HashSet<Employee> otherEmployees = new HashSet<>();

        for (Project project : projects) {
            for (Employee empl : collection) {
                if (empl != null && empl.getProjects().contains(project) && empl.getPosition() != Position.TEAM_LEAD && !empl.equals(employee)) {
                    otherEmployees.add(empl);
                }
            }
        }
        return otherEmployees;
    }


    HashSet<Employee> employeesByCustomerProjects(Customer customer, HashSet<Project> projects) {

        //отобрать всех сотрудников по отобранным проектам с учетом лидеров
        HashSet<Employee> allEmployeesByProjects = new HashSet<>();

        for (Project project : projects) {
            for (Employee employee : collection) {
                if (employee != null && employee.getProjects().contains(project)) {
                    allEmployeesByProjects.add(employee);
                }
            }
        }
        return allEmployeesByProjects;

    }

    private boolean isEmployeeHaveProject(Employee employee, String projectName) {
        for (Project project : employee.getProjects()) {
            if (project.getName().equals(projectName)) {
                return true;
            }
        }
        return false;
    }

}
