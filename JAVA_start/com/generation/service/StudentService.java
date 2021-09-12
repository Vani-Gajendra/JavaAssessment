package com.generation.service;

import com.generation.model.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StudentService {
    private final Map<String, Student> students = new HashMap<>();
    private final Map<String, Course> courses = new HashMap<>();

    private final Map<String, Map<String, String>> grades = new HashMap<>();

    private final Map<String, List> avgGrades = new HashMap<>();


    public void subscribeStudent(Student student) {
        students.put(student.getId(), student);
    }

    public Student findStudent(String studentId) {
        if (students.containsKey(studentId)) {
            return students.get(studentId);
        }
        return null;

    }

    public void showSummary() {
        //TODO implement

        System.out.println("Student Details:");
        for (String key : students.keySet()) {
            Student student = students.get(key);

            System.out.println(student);

        }
    }

    public void enrollToCourse(String studentId, Course course) {
        if (students.containsKey(studentId)) {
            students.get(studentId).enrollToCourse(course);
        }
    }

    public void grade(String studentId, String courseId, String graded) {



                if (!grades.containsKey(studentId))
                    grades.put(studentId, new HashMap<>());

                grades.get(studentId).put(courseId, graded);
                System.out.println("Graded(2): " +graded);
                if (!avgGrades.containsKey(courseId))
                   avgGrades.put(courseId, new ArrayList());

                avgGrades.get(courseId).add(graded);
            }





    public List<Course> findPassedCourses(String studentId, String courseId) {
        //TODO implement this method
        if (grades.containsKey(studentId)) {

            int grade = Integer.parseInt(grades.get(studentId).get(courseId));
            if (grade <= 3) {
                System.out.println("Fail");

            } else if (grade >= 4) {
                System.out.println("Pass");
            }


        }
        return null;
    }

    public double getAverage(String courseId) {
         //System.out.println(courseId);
         //System.out.println(avgGrades);
        if (avgGrades.containsKey(courseId)) {
            List gradeList = avgGrades.get(courseId);
            //System.out.println("lIST: " + gradeList.toString());
            double sum = 0.0;
            for (int i = 0; i < gradeList.size(); i++) {

                sum += Integer.parseInt(gradeList.get(i).toString());

            }
            double avg = sum / gradeList.size();

            return avg;
        }
        return 0.0;
    }

}