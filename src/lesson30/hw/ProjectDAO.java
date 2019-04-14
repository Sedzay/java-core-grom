package lesson30.hw;

import java.util.HashSet;

public class ProjectDAO {
    private HashSet<Project> collection = new HashSet<>();

    public HashSet<Project> getCollection() {
        return collection;
    }

    public String toString() {
        return "ProjectDAO{" +
                "projects=" + collection +
                '}';
    }

    HashSet<Project> projectsByCustomer(Customer customer) {

        HashSet<Project> allProjectByCustomer = new HashSet<>();

        for (Project project : collection) {
            if (project != null && project.getCustomer().equals(customer)) {
                allProjectByCustomer.add(project);
            }
        }
        return allProjectByCustomer;
    }

}
