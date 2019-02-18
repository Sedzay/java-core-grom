package lesson8.HomeWork.arithmetickAdder.courseStudent;

import java.util.Date;

public class Demo {
    Student[]students = null;
    Course[] courses = {
            new Course(new Date(),"Math", 12,"Kluben", students),
            new Course(new Date(),"Lit", 10,"Djoba", students),
            new Course(new Date(),"Eng", 100,"Jonson", students)
    };
    Student createHighestParent() {

        return new Student("Ivan", "Ivanov", 1,courses);
    }

    SpecialStudent createLowestChild() {
        return new SpecialStudent("Petya", "Petrov", 2, courses, 125,"email");
    }
}
