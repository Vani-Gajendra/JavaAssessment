package com.generation;

import com.generation.model.*;
import com.generation.service.CourseService;
import com.generation.service.StudentService;
import com.generation.utils.PrinterHelper;

import java.text.ParseException;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args)
            throws ParseException {
        StudentService studentService = new StudentService();
        CourseService courseService = new CourseService();
        Scanner scanner = new Scanner(System.in);
        int option = 0;
        do {
            PrinterHelper.showMainMenu();
            option = scanner.nextInt();
            switch (option) {
                case 1:
                    registerStudent(studentService, scanner);
                    break;
                case 2:
                    findStudent(studentService, scanner);
                    break;
                case 3:
                    gradeStudent(studentService, courseService, scanner);
                    break;
                case 4:
                    enrollStudentToCourse(studentService, courseService, scanner);
                    break;
                case 5:
                    showStudentsSummary(studentService, scanner);
                    break;
                case 6:
                    showCoursesSummary(courseService, scanner);
                    break;
                case 7:
                    getAverageGrade(studentService, courseService, scanner);
                    break;
                case 8:
                    findPassed(studentService, courseService, scanner);
                    break;
            }
        }
        while (option != 9);
    }

    private static void enrollStudentToCourse(StudentService studentService, CourseService courseService,
                                              Scanner scanner) {
        System.out.println("Insert student ID");
        String studentId = scanner.next();
        Student student = studentService.findStudent(studentId);
        if (student == null) {
            System.out.println("Invalid Student ID");
            return;
        }
        System.out.println(student);
        System.out.println("Insert course ID");
        String courseId = scanner.next();
        Course course = courseService.getCourse(courseId);
        if (course == null) {
            System.out.println("Invalid Course ID");
            return;
        }
        System.out.println(course);
        courseService.enrollStudent(courseId, student);
        studentService.enrollToCourse(studentId, course);
        System.out.println("Student with ID: " + studentId + " enrolled successfully to " + courseId);

    }

    private static void showCoursesSummary(CourseService courseService, Scanner scanner) {
        courseService.showSummary();
    }

    private static void showStudentsSummary(StudentService studentService, Scanner scanner) {
        studentService.showSummary();
    }


    private static void gradeStudent(StudentService studentService, CourseService courseService, Scanner scanner) {


        System.out.println("Enter student ID: ");
        String studentId = scanner.next();


        Student student = studentService.findStudent(studentId);

        if (student != null) {
            System.out.println("Enter course ID: ");

            String courseId = scanner.next();
            Course course = courseService.getCourse(courseId);

            if (course != null) {
                if (courseService.isAttendingCourse(courseId, studentId)) {
                    System.out.println("Enter Grade for Student (1 to 6)");
                    String studentGrade = scanner.next();

                    studentService.grade(studentId, courseId, studentGrade);
                    studentService.showSummary();
                    System.out.println("Student ID : " + studentId + " Course ID : " + courseId + " Graded: " + studentGrade);

                } else {
                    System.out.println("Student Not Enrolled in this Course");
                }
            } else {
                System.out.println("Course Not Found");
            }
        }
            else {
                    System.out.println("Student Not Found");
                }
            }


    private static void findStudent(StudentService studentService, Scanner scanner) {
        System.out.println("Enter student ID: ");
        String studentId = scanner.next();
        Student student = studentService.findStudent(studentId);
        if (student != null) {
            System.out.println("Student Found: ");
            System.out.println(student);
        } else {
            System.out.println("Student with Id = " + studentId + " not found");
        }
    }

    private static void registerStudent(StudentService studentService, Scanner scanner)
            throws ParseException {
        Student student = PrinterHelper.createStudentMenu(scanner);
        studentService.subscribeStudent(student);
    }

    private static void getAverageGrade(StudentService studentService, CourseService courseService, Scanner scanner) {
        System.out.println("Enter course ID: ");
        //Course course = courseService.findCourse (courseId);
        String courseId = scanner.next();
        Course course = courseService.getCourse(courseId);
        if (course != null) {
            double avggrades = studentService.getAverage(courseId);
            System.out.println("The average grades for " + courseId + " is " + avggrades);
        }

    }

    private static void findPassed(StudentService studentService, CourseService courseService, Scanner scanner) {
        System.out.println("Enter Student ID: ");
        String studentId = scanner.next();
        Student student = studentService.findStudent(studentId);
        if (student != null) {
            System.out.println("Enter Course ID: ");
            String courseId = scanner.next();
            Course course = courseService.getCourse(courseId);
            if (course != null) {
                if (courseService.isAttendingCourse(courseId, studentId)) {
                    List<Course> result = studentService.findPassedCourses(studentId, courseId);
                } else {
                    System.out.println("Student not enrolled in course");
                }
            }
            else {
                System.out.println("Invalid Course ID");
                }

            }
        else {
            System.out.println("Invalid Student ID");

        }
    }
}