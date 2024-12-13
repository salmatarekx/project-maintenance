-- -- Insert statements for User
INSERT INTO Users ( User_Name, Password, role, Email)
VALUES ( 'admin', 'admin123', 'ADMIN', 'admin@example.com'),
       ( 'instructor1', 'instr123', 'INSTRUCTOR', 'instr1@example.com'),
       ( 'student1', 'stud123', 'STUDENT', 'student1@example.com');
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
VALUES (2, 1,  'B+', 'Good job!'),
       ( 3, 1, 'A', 'Well done!');
--



-- -- Insert statements for Attendance
INSERT INTO attendance ( student_id, lesson_id, attend)
VALUES ( 3, 1, true),
       ( 3, 2, false),
       ( 3, 3, true),
       ( 3, 4, true);


-- Insert statements for QuizScore
INSERT INTO quiz_grades ( quiz_id, student_id, grades , feedback)
VALUES ( 1, 3, 'B' , 'Good Job'),
       ( 2, 3, 'A+' , 'Excellent'),
       ( 3, 2, 'A' , 'Well done');



