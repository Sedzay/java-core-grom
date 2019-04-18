package gromecode.lesson30.hw;

import java.util.HashSet;

public class Controller {
    private EmployeeDAO employeeDAO;
    private ProjectDAO projectDAO;
    private CustomerDAO customerDAO;

    public Controller(EmployeeDAO employeeDAO, ProjectDAO projectDAO, CustomerDAO customerDAO) {
        this.employeeDAO = employeeDAO;
        this.projectDAO = projectDAO;
        this.customerDAO = customerDAO;
    }

    public HashSet<Employee> employeesByProject(String projectName) {

        return employeeDAO.employeesByProject(projectName);
    }


    public HashSet<Project> projectsByEmployee(Employee employee) {

        return employee.getProjects();
    }


    public HashSet<Employee> employeesByDepartmentWithoutProject(DepartmentType departmentType) {

        return employeeDAO.employeesByDepartmentWithoutProject(departmentType);
    }


    public HashSet<Employee> employeesWithoutProject() {

        return employeeDAO.employeesWithoutProject();
    }


    public HashSet<Employee> employeesByTeamLead(Employee lead) {

        return employeeDAO.employeesByTeamLead(lead);
    }


    public HashSet<Employee> teamLeadsByEmployee(Employee employee) {

        return employeeDAO.teamLeadsByEmployee(employee);
    }


    public HashSet<Employee> employeesByProjectEmployee(Employee employee) {

        return employeeDAO.employeesByProjectEmployee(employee);
    }


    public HashSet<Project> projectsByCustomer(Customer customer) {

        return projectDAO.projectsByCustomer(customer);
    }


    public HashSet<Employee> employeesByCustomerProjects(Customer customer) {

        return employeeDAO.employeesByCustomerProjects(customer, projectsByCustomer(customer));
    }

}
