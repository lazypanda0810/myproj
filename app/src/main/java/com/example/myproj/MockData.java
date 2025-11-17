package com.example.myproj;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MockData {

    public static ArrayList<Course> getCourses() {
        ArrayList<Course> courses = new ArrayList<>();
        courses.add(new Course("1", "Java", "", "5h 30m", "Beginner", "Learn the fundamentals of Java programming.", getJavaLessons(), true));
        courses.add(new Course("4", "Python", "", "6h 0m", "Beginner", "Get started with Python programming.", getPythonLessons(), true));
        return courses;
    }

    private static List<Lesson> getJavaLessons() {
        List<Lesson> lessons = new ArrayList<>();
        lessons.add(new Lesson("1", "Introduction to Java (Detailed)", "30m",
                "- Object-Oriented – everything revolves around objects & classes.\n- Platform Independent – uses JVM → Write Once, Run Anywhere.\n- Simple & Easy – syntax similar to C/C++.\n- Secure – no direct memory access, strong exception handling.\n- Robust – handles errors effectively with try-catch.\n- Multithreaded – allows simultaneous execution of tasks.\n- Portable – compiled Java bytecode runs on any system with JVM.",
                "Java is a high-level, class-based, object-oriented programming language.\nIt was developed by James Gosling at Sun Microsystems in 1995.",
                "public class Demo {\n    public static void main(String[] args) {\n        System.out.println(\"Welcome to Java!\");\n    }\n}\n\nclass → Blueprint for creating objects\nmain() → Entry point of every Java program\nSystem.out.println() → Prints output"));
        lessons.add(new Lesson("2", "Variables and Data Types (Detailed)", "45m",
                "Local Variable – inside methods.\nInstance Variable – inside class, outside method.\nStatic Variable – belongs to class, shared among objects.",
                "A variable is a container that stores data values in memory.\n\nTypes of Variables in Java\n\nData Types in Java\nA. Primitive Data Types\nType\tSize\tExample\nbyte\t1 byte\tbyte age = 20;\nshort\t2 bytes\tshort s = 150;\nint\t4 bytes\tint num = 500;\nlong\t8 bytes\tlong pop = 999999L;\nfloat\t4 bytes\tfloat price = 99.5f;\ndouble\t8 bytes\tdouble rate = 45.67;\nchar\t2 bytes\tchar grade = 'A';\nboolean\t1 bit\tboolean flag = true;\nB. Non-Primitive Data Types\n\nString\n\nArrays\n\nClasses\n\nInterfaces",
                "String name = \"Raj\";"));
        lessons.add(new Lesson("3", "Control Flow (Detailed)", "1h",
                "1. Conditional Statements (if-else, else-if, switch)\n2. Loops (for, while, do-while)",
                "Control flow decides how instructions are executed.\n\n1. Conditional Statements - Used to make decisions.\n\n2. Loops - Used for repeating tasks.",
                "// if-else\nif (marks >= 40) {\n    System.out.println(\"Pass\");\n} else {\n    System.out.println(\"Fail\");\n}\n\n// else-if ladder\nif (marks >= 90) System.out.println(\"A\");\nelse if (marks >= 75) System.out.println(\"B\");\nelse System.out.println(\"C\");\n\n// switch\nswitch(day){\n    case 1: System.out.println(\"Monday\"); break;\n    case 2: System.out.println(\"Tuesday\"); break;\n    default: System.out.println(\"Invalid\");\n}\n\n// for loop\nfor(int i = 1; i <= 5; i++){\n    System.out.println(i);\n}\n\n// while loop\nint i = 1;\nwhile(i <= 5){\n    System.out.println(i);\n    i++;\n}\n\n// do-while\nint i = 1;\ndo{\n    System.out.println(i);\n    i++;\n}while(i <= 5);"));
        return lessons;
    }

    private static List<Lesson> getPythonLessons() {
        List<Lesson> lessons = new ArrayList<>();
        lessons.add(new Lesson("1", "Introduction to Python (Detailed)", "30m",
                "Simple & beginner-friendly syntax\n\nHuge number of libraries (NumPy, Pandas, TensorFlow, Flask)\n\nUsed in AI/ML, data science, automation, web development, scripting",
                "Python is a general-purpose, high-level, interpreted programming language.\nCreated by Guido van Rossum in 1991.",
                "print(\"Hello World\")\n\n\nNo semi-colon\n\nNo main function required for simple programs\n\nDynamic typing → types decided at runtime"));
        lessons.add(new Lesson("2", "Data Structures (Detailed)", "1h",
                "1. List (Ordered, Mutable, Allows duplicates)\n2. Tuple (Ordered, Immutable)\n3. Dictionary (Key-value pairs, Fast lookup)\n4. Set (Unordered, No duplicates)",
                "Python has powerful built-in data structures.",
                "// List\nfruits = [\"apple\", \"banana\", \"mango\"]\nfruits.append(\"orange\")\n\n// Tuple\npoint = (5, 10)\n\n// Dictionary\nstudent = {\"name\": \"Raj\", \"age\": 20, \"course\": \"BCA\"}\n\n// Set\nnumbers = {1, 2, 3, 3}  # output {1, 2, 3}"));
        lessons.add(new Lesson("3", "Functions and Modules (Detailed)", "1h 30m",
                "Functions (Built-in, User-defined, Arguments)\nModules (Importing, Creating)",
                "Functions are a block of reusable code.\nModules are a Python file with functions/classes.",
                "// Functions\ndef greet(name):\n    return \"Hello \" + name\n\n// Types of Functions\n// Built-in functions → len(), print(), type()\n// User-defined functions → created using def\n\n// Arguments\ndef add(a, b):\n    return a + b\n\n// Modules\n// A module is a Python file with functions/classes.\n\n// Importing a module\nimport math\nprint(math.sqrt(25))\n\n// Creating your own module\n// File: mymath.py\n// def square(n):\n//     return n*n\n\n// Using it:\n// import mymath\n// print(mymath.square(4))"));
        return lessons;
    }

    public static ArrayList<Quiz> getQuizzes() {
        ArrayList<Quiz> quizzes = new ArrayList<>();

        // Java Quiz - Comprehensive Test (15 questions)
        ArrayList<QuizQuestion> javaQuestions = new ArrayList<>();

        // Introduction to Java
        javaQuestions.add(new QuizQuestion("1", "What is the main purpose of the JVM?", new ArrayList<>(Arrays.asList("To compile Java code", "To run Java code", "To debug Java code")), 1));
        javaQuestions.add(new QuizQuestion("2", "Who developed Java?", new ArrayList<>(Arrays.asList("Dennis Ritchie", "James Gosling", "Bjarne Stroustrup")), 1));
        javaQuestions.add(new QuizQuestion("3", "What does JVM stand for?", new ArrayList<>(Arrays.asList("Java Virtual Machine", "Java Variable Method", "Java Version Manager")), 0));
        javaQuestions.add(new QuizQuestion("4", "Which principle makes Java platform-independent?", new ArrayList<>(Arrays.asList("Inheritance", "Write Once, Run Anywhere", "Polymorphism")), 1));

        // Variables and Data Types
        javaQuestions.add(new QuizQuestion("5", "Which of the following is not a primitive data type in Java?", new ArrayList<>(Arrays.asList("int", "float", "String")), 2));
        javaQuestions.add(new QuizQuestion("6", "What is the size of an int in Java?", new ArrayList<>(Arrays.asList("2 bytes", "4 bytes", "8 bytes")), 1));
        javaQuestions.add(new QuizQuestion("7", "Which keyword is used to create an object in Java?", new ArrayList<>(Arrays.asList("new", "create", "object")), 0));
        javaQuestions.add(new QuizQuestion("8", "What is a variable declared inside a method called?", new ArrayList<>(Arrays.asList("Instance variable", "Local variable", "Static variable")), 1));

        // Control Flow
        javaQuestions.add(new QuizQuestion("9", "Which of these is a looping statement in Java?", new ArrayList<>(Arrays.asList("if", "switch", "for")), 2));
        javaQuestions.add(new QuizQuestion("10", "What is the difference between while and do-while loops?", new ArrayList<>(Arrays.asList("No difference", "do-while executes at least once", "while executes at least once")), 1));
        javaQuestions.add(new QuizQuestion("11", "Which statement is used to exit a loop immediately?", new ArrayList<>(Arrays.asList("exit", "break", "stop")), 1));

        // OOP Concepts
        javaQuestions.add(new QuizQuestion("12", "What is the difference between an interface and an abstract class in Java?", new ArrayList<>(Arrays.asList("An interface can have method implementations", "An abstract class can have method implementations", "There is no difference")), 1));
        javaQuestions.add(new QuizQuestion("13", "Which keyword is used for inheritance in Java?", new ArrayList<>(Arrays.asList("extends", "implements", "inherits")), 0));
        javaQuestions.add(new QuizQuestion("14", "What is the entry point of a Java program?", new ArrayList<>(Arrays.asList("start()", "main()", "run()")), 1));
        javaQuestions.add(new QuizQuestion("15", "Which of the following is true about Java?", new ArrayList<>(Arrays.asList("Java is procedural", "Java is object-oriented", "Java is functional")), 1));

        quizzes.add(new Quiz("1", javaQuestions));

        // Python Quiz - Comprehensive Test (15 questions)
        ArrayList<QuizQuestion> pythonQuestions = new ArrayList<>();

        // Introduction to Python
        pythonQuestions.add(new QuizQuestion("1", "Who created Python?", new ArrayList<>(Arrays.asList("James Gosling", "Guido van Rossum", "Dennis Ritchie")), 1));
        pythonQuestions.add(new QuizQuestion("2", "What type of language is Python?", new ArrayList<>(Arrays.asList("Compiled", "Interpreted", "Assembly")), 1));
        pythonQuestions.add(new QuizQuestion("3", "Which statement is used to print output in Python?", new ArrayList<>(Arrays.asList("System.out.println()", "print()", "console.log()")), 1));

        // Data Structures
        pythonQuestions.add(new QuizQuestion("4", "What is the correct way to create a list in Python?", new ArrayList<>(Arrays.asList("list = []", "list = ()", "list = {}")), 0));
        pythonQuestions.add(new QuizQuestion("5", "Which of the following is a mutable data type in Python?", new ArrayList<>(Arrays.asList("tuple", "list", "string")), 1));
        pythonQuestions.add(new QuizQuestion("6", "What is the correct syntax for a dictionary in Python?", new ArrayList<>(Arrays.asList("{\"name\": \"Raj\"}", "[\"name\", \"Raj\"]", "(\"name\", \"Raj\")")), 0));
        pythonQuestions.add(new QuizQuestion("7", "Which data structure does NOT allow duplicate values?", new ArrayList<>(Arrays.asList("List", "Tuple", "Set")), 2));
        pythonQuestions.add(new QuizQuestion("8", "What is the output of: len([1, 2, 3, 4])?", new ArrayList<>(Arrays.asList("3", "4", "5")), 1));

        // Functions and Modules
        pythonQuestions.add(new QuizQuestion("9", "How do you define a function in Python?", new ArrayList<>(Arrays.asList("def function_name():", "function function_name():", "function_name():")), 0));
        pythonQuestions.add(new QuizQuestion("10", "What keyword is used to return a value from a function?", new ArrayList<>(Arrays.asList("return", "yield", "send")), 0));
        pythonQuestions.add(new QuizQuestion("11", "How do you import the math module in Python?", new ArrayList<>(Arrays.asList("include math", "import math", "using math")), 1));
        pythonQuestions.add(new QuizQuestion("12", "Which of these is a built-in function in Python?", new ArrayList<>(Arrays.asList("len()", "square()", "append()")), 0));

        // General Python Concepts
        pythonQuestions.add(new QuizQuestion("13", "What is the output of: print(type([1, 2, 3]))?", new ArrayList<>(Arrays.asList("<class 'list'>", "<class 'tuple'>", "<class 'dict'>")), 0));
        pythonQuestions.add(new QuizQuestion("14", "Does Python require semicolons at the end of statements?", new ArrayList<>(Arrays.asList("Yes, always", "No", "Only in functions")), 1));
        pythonQuestions.add(new QuizQuestion("15", "Which library is commonly used for Machine Learning in Python?", new ArrayList<>(Arrays.asList("Flask", "TensorFlow", "Django")), 1));

        quizzes.add(new Quiz("4", pythonQuestions));

        return quizzes;
    }
}
