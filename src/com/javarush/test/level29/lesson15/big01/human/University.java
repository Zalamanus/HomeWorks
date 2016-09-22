package com.javarush.test.level29.lesson15.big01.human;

import java.util.List;

public class University {
    private List<Student> students = StudentsDataBase.students;
    private String name;
    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public University(String name, int age) {
        this.name = name; this.age = age;
    }

    public Student getStudentWithAverageGrade(double averageGrade) {
        for (Student student : students) {
            if (student.getAverageGrade()==averageGrade) return student;
        }
        return null;
    }

    public Student getStudentWithMaxAverageGrade() {
        Student student = null;
        for (Student student1 : students) {
            if (student == null) student = student1;
            if (student1.getAverageGrade() > student.getAverageGrade()) student = student1;
        }
        return student;
    }

    public Student getStudentWithMinAverageGrade() {
        Student student = null;
        for (Student student1 : students) {
            if (student == null) student = student1;
            if (student1.getAverageGrade() < student.getAverageGrade()) student = student1;
        }
        return student;
    }
    public void expel(Student student) {
        students.remove(student);
    }
}
