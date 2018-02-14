delete from course;

INSERT INTO course (course_name, course_number, department_id, credit) 
VALUES ('Test Course 1 Title', '1000', (select id from department where abbreviation = 'GEOL'), 3),
	   ('Test Course 2 Title', '2003', (select id from department where abbreviation = 'GEOL'), 3),
	   ('Test Course 3 Title', '3500', (select id from department where abbreviation = 'GEOL'), 1),
	   ('Intro to Computer Science', '1000', (select id from department where abbreviation = 'CSCI'), 3),
	   ('Theory & Computer Applications for Pattern Recognition', '6635', (select id from department where abbreviation = 'CSCI'), 3),
	   ('Data Structures and Applications', '2025', (select id from department where abbreviation = 'CSCI'), 3),
	   ('Calculus 1', '2114', (select id from department where abbreviation = 'MATH'), 3),
	   ('Calculus 2', '2124', (select id from department where abbreviation = 'MATH'), 3);