package lesson30.hw;

import java.util.Date;

public class Demo {
    public static void main(String[] args) {

        //add department
        Department department1 = new Department(1, DepartmentType.ANALISTS);

        Employee employee1 = new Employee("Vasya", "Pupkin", new Date(), Position.ANALYST, department1);
        Employee employee2 = new Employee("Koya", "Ivanov", new Date(), Position.ANALYST, department1);
        Employee employee3 = new Employee("Sonya", "Petrova", new Date(), Position.DESIGNER, department1);

        //add Employee
        EmployeeDAO employeeDAO = new EmployeeDAO();
        employeeDAO.getCollection().add(employee1);
        employeeDAO.getCollection().add(employee2);
        employeeDAO.getCollection().add(employee3);

        System.out.println(employeeDAO);

        department1.addEmployee(employeeDAO, employee1);
        department1.addEmployee(employeeDAO, employee2);

        //update Employee

//        employee3.setId(employee2.getId());
//
//        employeeDAO.update(employee3);
//        System.out.println(employeeDAO);

        System.out.println();

        //add customer
        Customer customer1 = new Customer("KievHleb", "Ukraina", 1000);
        Customer customer2 = new Customer("ATB", "Ukraina", 2000);

        CustomerDAO customerDAO = new CustomerDAO();
        customerDAO.getCollection().add(customer1);
        customerDAO.getCollection().add(customer2);

        System.out.println(customerDAO);

        System.out.println();

        //add project
        Project project1 = new Project(1001, "pr1", customer1);
        Project project2 = new Project(1002, "pr2", customer1);
        Project project3 = new Project(1003, "pr3", customer1);

        ProjectDAO projectDAO = new ProjectDAO();
        projectDAO.getCollection().add(project1);
        projectDAO.getCollection().add(project2);
        projectDAO.getCollection().add(project3);

        System.out.println(projectDAO);

        employee1.addProject(projectDAO, project1);
        employee1.addProject(projectDAO, project2);

        employee2.addProject(projectDAO, project1);
        employee2.addProject(projectDAO, project3);

        //employee3.addProject(projectDAO, project1);

        System.out.println(employee1.getProjects());
        System.out.println(employee2.getProjects());

        System.out.println();

        Controller controller = new Controller(employeeDAO, projectDAO, customerDAO);

        //test employeesByProject
        System.out.println(controller.employeesByProject("pr1"));

        //test projectsByEmployee
        System.out.println();
        System.out.println(controller.projectsByEmployee(employee2));

        //test employeesByDepartmentWithoutProject
        System.out.println();
        System.out.println(controller.employeesByDepartmentWithoutProject(DepartmentType.ANALISTS));

        //test employeesWithoutProject
        System.out.println();
        System.out.println(controller.employeesWithoutProject());

        //employeeDAO.getCollection().add(employee3);
        //test employeesByTeamLead
        System.out.println();
        Employee employeeLead = new Employee("Potap", "Dudikov", new Date(), Position.TEAM_LEAD, department1);

        //у лидера нет ни одного проекта
        System.out.println(controller.employeesByTeamLead(employeeLead));
        //у лидера есть одинпроект, в нем два подчиненных
        employeeLead.addProject(projectDAO, project1);
 //       System.out.println(employeeLead.getProjects());
        employeeDAO.getCollection().add(employeeLead);

        System.out.println(controller.employeesByTeamLead(employeeLead));
        System.out.println();
        //у лидера проект без подчиненных
        Project project4 = new Project(1004, "pr4", customer1);
        projectDAO.getCollection().add(project4);

        employeeLead.deleteProject(project1);
        employeeLead.addProject(projectDAO, project4);
        System.out.println(controller.employeesByTeamLead(employeeLead));

        //один проект один подчиненный
        employeeLead.deleteProject(project4);
        employeeLead.addProject(projectDAO, project2);
        System.out.println(controller.employeesByTeamLead(employeeLead));


        //test teamLeadsByEmployee(Employee employee)
        System.out.println(controller.teamLeadsByEmployee(employee1));

        //добавитьеще одного руководителя первого проекта, у сотрудника два проекта
        Employee employeeLead2 = new Employee("Scot", "Yar", new Date(), Position.TEAM_LEAD, department1);
        employeeDAO.getCollection().add(employeeLead2);
        employeeLead2.addProject(projectDAO, project1);

        System.out.println(controller.teamLeadsByEmployee(employee1));

        //test employeesByProjectEmployee(Employee employee)
        System.out.println();
        System.out.println(controller.employeesByProjectEmployee(employee1));

        //test projectsByCustomer(Customer customer)
        System.out.println();
        System.out.println(controller.projectsByCustomer(customer1));
        System.out.println(controller.projectsByCustomer(customer2));

        //test employeesByCustomerProjects(Customer customer)
        System.out.println(controller.employeesByCustomerProjects(customer2));
        System.out.println(controller.employeesByCustomerProjects(customer1));
    }
}
