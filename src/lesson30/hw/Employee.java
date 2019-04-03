package lesson30.hw;

import java.util.*;

public class Employee extends IdEntity{

    private long id;
    private String firstName;
    private String lastName;
    private Date dateHired;
    private Position position;
    private Department department;
    private HashSet<Project> projects = new HashSet<>();

    public Employee(String firstName, String lastName, Date dateHired, Position position, Department department) {

        Random random = new Random();
        this.id = random.nextInt();

        this.firstName = firstName;
        this.lastName = lastName;
        this.dateHired = dateHired;
        this.position = position;
        this.department = department;

    }

    public Project addProject(ProjectDAO projectDAO, Project project) {
        if (projectDAO.getCollection().contains(project)) {
            projects.add(project);
            return project;
        }
        System.out.println("Project with id " + project.getId() + " does not exist");
        return null;
    }

    public void deleteProject(Project project) {
        if (projects.contains(project)) {
            projects.remove(project);
            return;
        }
        System.out.println("Project with id " + project.getId() + " does not exist");
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public void setProjects(HashSet<Project> projects) {
        this.projects = projects;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Position getPosition() {
        return position;
    }

    public Department getDepartment() {
        return department;
    }

    public HashSet<Project> getProjects() {
        return projects;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return firstName.equals(employee.firstName) &&
                lastName.equals(employee.lastName) &&
                position == employee.position;
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, position);
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", dateHired=" + dateHired +
                ", position=" + position +
                //", department=" + department +
                ", projects=" + projects +
                '}';
    }
}
