package lesson30.hw;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Controller {
    private EmployeeDAO employeeDAO;
    private DepartmentDAO departmentDAO;
    private ProjectDAO projectDAO;
    private CustomerDAO customerDAO;

    public Controller(EmployeeDAO employeeDAO, DepartmentDAO departmentDAO, ProjectDAO projectDAO, CustomerDAO customerDAO) {
        this.employeeDAO = employeeDAO;
        this.departmentDAO = departmentDAO;
        this.projectDAO = projectDAO;
        this.customerDAO = customerDAO;
    }

    public HashSet<Employee> employeesByProject(String projectName) {

        HashSet<Employee> employees = new HashSet<>();

        HashSet<Project> projects = projectDAO.getCollection();
        Project project = null;

        for (Project project1 : projects) {
            if (project1 != null && project1.getName().equals(projectName)) {
                project = project1;
                break;
            }
        }

        if (project == null) {
            return null;
        }

        HashSet<Employee> empl = employeeDAO.getCollection();

        for (Employee employee : empl) {
            if (employee != null && employee.getProjects().contains(project)) {
                employees.add(employee);
            }
        }

        return employees;
    }


    public HashSet<Project> projectsByEmployee(Employee employee) {

        HashSet<Project> projects = new HashSet<>();

        HashSet<Employee> employees = employeeDAO.getCollection();
        if (employees.contains(employee)) {
            projects.addAll(employee.getProjects());
        }
        return projects;
    }


    public HashSet<Employee> employeesByDepartmentWithoutProject(DepartmentType departmentType) {

        HashSet<Department> departments = departmentDAO.getCollection();

        HashSet<Employee> employees = new HashSet<>();

        for (Department department : departments) {
            if (department != null && department.getType() == departmentType) {
                employees = department.getEmployees();
                break;
            }
        }

        return getAllEmployeesWithoutProject(employees);
    }


    public HashSet<Employee> employeesWithoutProject() {

        return getAllEmployeesWithoutProject(employeeDAO.getCollection());

    }


    public HashSet<Employee> employeesByTeamLead(Employee lead) {

        //узнать какие проекты есть у лидера
        HashSet<Employee> employees = employeeDAO.getCollection();

        HashSet<Project> projects = new HashSet<>();

        for (Employee employee : employees) {
            if (employee != null && employee.equals(lead)) {
                projects = employee.getProjects();
                break;
            }
        }

        //найти всех работников, у кого такие же проекты как у лидера
        //лидера не учитывать
        HashSet<Employee> newEmploees = new HashSet<>();

        for (Project project : projects) {
            for (Employee employee : employees) {
                if (employee != null && employee.getProjects().contains(project) && employee.getPosition() != lead.getPosition()) {
                    newEmploees.add(employee);
                    continue;
                }
            }
        }

        return newEmploees;
    }


    public HashSet<Employee> teamLeadsByEmployee(Employee employee) {

        HashSet<Project> projects = employee.getProjects();

        //проверить во всех проектах сотрудника всех лидеров
        //сохранить в новый список
        HashSet<Employee>leads = new HashSet<>();
        HashSet<Employee>employees = employeeDAO.getCollection();

        for (Project project : projects) {
            for (Employee empl : employees) {
                if (empl != null && empl.getProjects().contains(project) && empl.getPosition() == Position.TEAM_LEAD) {
                    leads.add(empl);
                }
            }
        }
        return leads;
    }


    public HashSet<Employee> employeesByProjectEmployee(Employee employee) {
        HashSet<Project> projects = employee.getProjects();

        //проверить во всех проектах сотрудника всех остальных сотрудников кроме лидеров
        //сохранить в новый список
        HashSet<Employee>otherEmployees = new HashSet<>();
        HashSet<Employee>employees = employeeDAO.getCollection();

        for (Project project : projects) {
            for (Employee empl : employees) {
                if (empl != null && empl.getProjects().contains(project) && empl.getPosition() != Position.TEAM_LEAD && !empl.equals(employee)) {
                    otherEmployees.add(empl);
                }
            }
        }
        return otherEmployees;
    }


    public HashSet<Project> projectsByCustomer(Customer customer) {
        HashSet<Project> projects = projectDAO.getCollection();

        HashSet<Project> allProjectByCustomer = new HashSet<>();

        for (Project project : projects) {
            if (project != null && project.getCustomer().equals(customer)) {
                allProjectByCustomer.add(project);
            }
        }
        return allProjectByCustomer;
    }


    public HashSet<Employee> employeesByCustomerProjects(Customer customer) {
        //найти все проекты заказчика
        HashSet<Project> projects = projectsByCustomer(customer);

        //отобрать всех сотрудников по отобранным проектам с учетом лидеров
        HashSet<Employee> employees = employeeDAO.getCollection();
        HashSet<Employee> allEmployeesByProjects = new HashSet<>();

        for (Project project : projects) {
            for (Employee employee : employees) {
                if (employee != null && employee.getProjects().contains(project)) {
                    allEmployeesByProjects.add(employee);
                }
            }
        }
        return allEmployeesByProjects;

    }




    private HashSet<Employee> getAllEmployeesWithoutProject(HashSet<Employee> employees) {

        HashSet<Employee> newEmploees = new HashSet<>();

        for (Employee employee : employees) {
            if (employee != null && employee.getProjects().size() == 0) {
                newEmploees.add(employee);
            }
        }

        return newEmploees;
    }



}
