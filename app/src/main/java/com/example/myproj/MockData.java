package com.example.myproj;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MockData {

    public static ArrayList<Course> getCourses() {
        ArrayList<Course> courses = new ArrayList<>();
        courses.add(new Course("1", "Java", "", "5h 30m", "Beginner", "Learn the fundamentals of Java programming.", getJavaLessons(), true));
        courses.add(new Course("2", "Photoshop", "", "3h 15m", "Beginner", "Master the basics of Adobe Photoshop.", new ArrayList<>(), true));
        courses.add(new Course("3", "Digital Marketing", "", "7h 45m", "Intermediate", "Learn how to market products online.", new ArrayList<>(), false));
        courses.add(new Course("4", "Python", "", "6h 0m", "Beginner", "Get started with Python programming.", getPythonLessons(), true));
        courses.add(new Course("5", "UI/UX Design", "", "4h 30m", "Intermediate", "Learn the principles of UI/UX design.", new ArrayList<>(), false));
        courses.add(new Course("6", "Data Science", "", "10h 0m", "Advanced", "An in-depth look at data science.", new ArrayList<>(), true));
        return courses;
    }

    private static List<Lesson> getJavaLessons() {
        List<Lesson> lessons = new ArrayList<>();
        lessons.add(new Lesson("1", "Introduction to Java", "30m"));
        lessons.add(new Lesson("2", "Variables and Data Types", "45m"));
        lessons.add(new Lesson("3", "Control Flow", "1h"));
        lessons.add(new Lesson("4", "Methods", "1h 15m"));
        lessons.add(new Lesson("5", "Object-Oriented Programming", "2h"));
        return lessons;
    }

    private static List<Lesson> getPythonLessons() {
        List<Lesson> lessons = new ArrayList<>();
        lessons.add(new Lesson("1", "Introduction to Python", "30m"));
        lessons.add(new Lesson("2", "Data Structures", "1h"));
        lessons.add(new Lesson("3", "Functions and Modules", "1h 30m"));
        lessons.add(new Lesson("4", "File I/O", "1h"));
        lessons.add(new Lesson("5", "Object-Oriented Programming in Python", "2h"));
        return lessons;
    }

    public static ArrayList<Quiz> getQuizzes() {
        ArrayList<Quiz> quizzes = new ArrayList<>();

        // Java Quiz
        ArrayList<QuizQuestion> javaQuestions = new ArrayList<>();
        javaQuestions.add(new QuizQuestion("1", "What is the main purpose of the JVM?", new ArrayList<>(Arrays.asList("To compile Java code", "To run Java code", "To debug Java code")), 1));
        javaQuestions.add(new QuizQuestion("2", "Which of the following is not a primitive data type in Java?", new ArrayList<>(Arrays.asList("int", "float", "String")), 2));
        javaQuestions.add(new QuizQuestion("3", "What is the difference between an interface and an abstract class in Java?", new ArrayList<>(Arrays.asList("An interface can have method implementations", "An abstract class can have method implementations", "There is no difference")), 1));
        javaQuestions.add(new QuizQuestion("4", "What keyword is used to create an object in Java?", new ArrayList<>(Arrays.asList("new", "create", "object")), 0));
        javaQuestions.add(new QuizQuestion("5", "Which of these is a looping statement in Java?", new ArrayList<>(Arrays.asList("if", "switch", "for")), 2));
        quizzes.add(new Quiz("1", javaQuestions));

        // Python Quiz
        ArrayList<QuizQuestion> pythonQuestions = new ArrayList<>();
        pythonQuestions.add(new QuizQuestion("1", "What is the correct way to create a list in Python?", new ArrayList<>(Arrays.asList("list = []", "list = ()", "list = {}")), 0));
        pythonQuestions.add(new QuizQuestion("2", "Which of the following is a mutable data type in Python?", new ArrayList<>(Arrays.asList("tuple", "list", "string")), 1));
        pythonQuestions.add(new QuizQuestion("3", "How do you define a function in Python?", new ArrayList<>(Arrays.asList("def function_name():", "function function_name():", "function_name():")), 0));
        quizzes.add(new Quiz("4", pythonQuestions));

        return quizzes;
    }
}
