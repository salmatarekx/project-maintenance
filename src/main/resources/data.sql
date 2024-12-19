-- -- Insert statements for User
INSERT INTO Users ( User_Name, Password, role, Email)
VALUES ( 'admin', 'admin123', 'ADMIN', 'admin@example.com'),
       ( 'instructor1', 'instr123', 'INSTRUCTOR', 'instr1@example.com'),
       ( 'student1', 'stud123', 'STUDENT', 'student1@example.com'),
       ( 'student2', 'stud2123', 'STUDENT', 'student2@example.com'),
       ( 'student3', 'stud3123', 'STUDENT', 'student3@example.com');
--
-- Insert statements for Course
INSERT INTO Course (title, description, duration, instructor_id)
VALUES ( 'Java Basics', 'Introduction to Java programming', 30, 2),
       ( 'Spring Boot Advanced', 'Deep dive into Spring Boot framework', 45, 2);
--
-- Insert statements for Lesson
INSERT INTO lesson ( topic, course_id)
VALUES ( 'Introduction to Java', 1),
       ( 'OOP Basics', 1),
       ( 'Spring Boot Setup', 2),
       ( 'REST API Development', 2);
--
-- Insert statements for Quiz
INSERT INTO quiz ( title, course_id )
VALUES ( 'Java Basics Quiz', 1 ),
       ( 'Java Advanced Quiz', 1 ),
       ( 'Spring Boot Quiz', 2 );
--
--
-- Insert statements for Assignment
INSERT INTO assignment ( title, course_id)
VALUES ('Java Project 1', 1),
       ( 'Spring Boot Project', 2);

-- Insert statements for Assignment
INSERT INTO assignment_grades ( student_id , assignment_id , grade, feedback)
VALUES (3, 1,  'A+', 'Excellent'),
       (4, 2,  'B', 'Good'),
       (4, 2,  'B+', 'Good job!'),
       ( 5, 1, 'A', 'Well done!');
--



-- -- Insert statements for Attendance
INSERT INTO attendance ( student_id, lesson_id, attend)
VALUES ( 3, 1, true),
       ( 3, 2, false),
       ( 3, 3, true),
       ( 4, 3, true),
       ( 4, 3, true),
       ( 5, 4, true);


-- Insert statements for QuizScore
INSERT INTO quiz_grades ( quiz_id, student_id, grade , feedback)
VALUES ( 1, 3, 'B' , 'Good Job'),
       ( 2, 3, 'A+' , 'Excellent'),
       ( 2, 4, 'A+' , 'Excellent'),
       ( 3, 5, 'A' , 'Well done');
---- Insert statements for Assignment_Task
INSERT INTO ASSIGNMENT_TASK (ID, ASSIGNMENT_ID, TASK_DESCRIPTION, EXPECTED_OUTPUT, POINTS, TASK_TYPE, ADDITIONAL_RESOURCES)
VALUES
(1, 1, 'Create a Java class named "Calculator" with methods for basic arithmetic operations (add, subtract, multiply, divide).',
    'Calculator class should have four public methods: add(double a, double b), subtract(double a, double b), multiply(double a, double b), and divide(double a, double b)',
    20,
    'CODE',
    'https://docs.oracle.com/javase/tutorial/java/concepts/class.html'),

(2, 1, 'Implement a method that checks if a given string is a palindrome.',
    'Method should return true for inputs like "radar" and "A man a plan a canal Panama"',
    15,
    'CODE',
    'https://www.geeksforgeeks.org/java-program-to-check-whether-a-string-is-a-palindrome/'),

(3, 1, 'Write a detailed explanation of how garbage collection works in Java.',
    'Cover topics including: Mark and Sweep algorithm, Generational Garbage Collection, and Memory Leaks',
    25,
    'WRITING',
    'https://www.oracle.com/webfolder/technetwork/tutorials/obe/java/gc01/index.html');

INSERT INTO ASSIGNMENT_TASK (ID, ASSIGNMENT_ID, TASK_DESCRIPTION, EXPECTED_OUTPUT, POINTS, TASK_TYPE, ADDITIONAL_RESOURCES)
VALUES
(4, 2, 'Create a REST API endpoint that handles CRUD operations for a "Product" entity.',
    'Implement GET, POST, PUT, and DELETE endpoints with proper error handling and status codes',
    30,
    'CODE',
    'https://spring.io/guides/tutorials/rest/'),

(5, 2, 'Implement proper validation for the Product entity using Spring Validation.',
    'Add validation for: name (not empty, max 100 chars), price (positive number), category (from enum list)',
    25,
    'CODE',
    'https://www.baeldung.com/spring-boot-bean-validation'),

(6, 2, 'Write unit tests for the Product controller using MockMvc.',
    'Achieve at least 80% test coverage for the ProductController class',
    20,
    'CODE',
    'https://spring.io/guides/gs/testing-web/');

-- Insert statements for Question
INSERT INTO QUESTION ( QUIZ_ID, QUESTION_TEXT, OPTIONA, OPTIONB, OPTIONC, OPTIOND, CORRECT_ANSWER, POINTS)
VALUES
( 1, 'What is the correct way to declare a variable in Java?',
    'var x;',
    'int x;',
    'variable x;',
    'declare x;',
    'B',
    5),
( 1, 'Which method is the entry point of a Java program?',
    'start()',
    'run()',
    'main()',
    'execute()',
    'C',
    5),
( 1, 'What is the size of int data type in Java?',
    '16 bit',
    '32 bit',
    '64 bit',
    '8 bit',
    'B',
    5);
INSERT INTO QUESTION ( QUIZ_ID, QUESTION_TEXT, OPTIONA, OPTIONB, OPTIONC, OPTIOND, CORRECT_ANSWER, POINTS)
VALUES
( 2, 'Which annotation is used to create a REST controller in Spring?',
    '@Controller',
    '@RestController',
    '@Service',
    '@Repository',
    'B',
    10),
( 2, 'What is the default scope of a bean in Spring?',
    'Prototype',
    'Singleton',
    'Request',
    'Session',
    'B',
    10),
( 2, 'Which dependency is required for Spring Boot Web applications?',
    'spring-boot-starter-data-jpa',
    'spring-boot-starter-test',
    'spring-boot-starter-web',
    'spring-boot-starter-security',
    'C',
    10);


