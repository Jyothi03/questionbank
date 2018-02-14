delete from teaches;
delete from person;
delete from answer;
delete from question;
delete from course;
delete from department;
 
INSERT INTO Person (first_name, last_name, user_name, password, admin) VALUES 
	('Question','Bank','qbank','11111',1),
	('Testy', 'McTeacher', 'teach', '11111', 0);
	
INSERT INTO department (name, abbreviation) VALUES 
	('Computer Science','CSCI'),
	('Mathematics','MATH'),
	('Geology','GEOL'),
	('Psychology','PSYC'),
	('Foreign Languages','FORL'),
	('English','ENGL'),
	('Biology','BIO');
	
INSERT INTO course (course_name, course_number, department_id, credit) VALUES 
	('Test Course 1 Title', '1000', (select id from department where abbreviation = 'GEOL'), 3),
	('Test Course 2 Title', '2003', (select id from department where abbreviation = 'GEOL'), 3),
	('Test Course 3 Title', '3500', (select id from department where abbreviation = 'GEOL'), 1),
	('Intro to Computer Science', '1000', (select id from department where abbreviation = 'CSCI'), 3),
	('Theory & Computer Applications for Pattern Recognition', '6635', (select id from department where abbreviation = 'CSCI'), 3),
	('Data Structures and Applications', '2025', (select id from department where abbreviation = 'CSCI'), 3),
	('Calculus 1', '2114', (select id from department where abbreviation = 'MATH'), 3),
	('Calculus 2', '2124', (select id from department where abbreviation = 'MATH'), 3);
	
INSERT INTO teaches (person_id, course_id) VALUES
	((select id from person where user_name = 'teach'), (select id from course where course_name = 'Test Course 1 Title')),
	((select id from person where user_name = 'teach'), (select id from course where course_name = 'Test Course 2 Title')),
	((select id from person where user_name = 'teach'), (select id from course where course_name = 'Test Course 3 Title')),
	((select id from person where user_name = 'teach'), (select id from course where course_name = 'Intro to Computer Science')),
	((select id from person where user_name = 'teach'), (select id from course where course_name = 'Theory & Computer Applications for Pattern Recognition')),
	((select id from person where user_name = 'teach'), (select id from course where course_name = 'Data Structures and Applications')),
	((select id from person where user_name = 'teach'), (select id from course where course_name = 'Calculus 1')),
	((select id from person where user_name = 'teach'), (select id from course where course_name = 'Calculus 2'));

INSERT INTO question (question, chapter, course_id) VALUES 
	('What is a limit?', '1', (select id from course where course_name = 'Calculus 1')),
	('Who was L''hopital?', '1', (select id from course where course_name = 'Calculus 1')),
	('What is a derivative?', '2', (select id from course where course_name = 'Calculus 1')),
	('What is an integral?', '3', (select id from course where course_name = 'Calculus 1')),
	('Describe a polar vector.', '4', (select id from course where course_name = 'Calculus 1')),
	('What is calculus?', '5', (select id from course where course_name = 'Calculus 1')),
	('What is a mouse?', '1', (select id from course where course_name = 'Intro to Computer Science')),
	('Where is the monitor?', '1', (select id from course where course_name = 'Intro to Computer Science')),
	('How do you turn on the computer?', '2', (select id from course where course_name = 'Intro to Computer Science')),
	('How do you turn off the computer?', '2', (select id from course where course_name = 'Intro to Computer Science')),
	('What does RAM mean?', '3', (select id from course where course_name = 'Intro to Computer Science')),
	('What does ROM mean?', '3', (select id from course where course_name = 'Intro to Computer Science')),
	('Who you gonna call?', '1', (select id from course where course_name = 'Test Course 1 Title')),
	('Who''s the baddest?', '1', (select id from course where course_name = 'Test Course 1 Title')),
	('Where in the world is Carmen Sandiego?', '1', (select id from course where course_name = 'Test Course 1 Title')),
	('Have you ever header the wolf cry to the blue corn moon?', '1', (select id from course where course_name = 'Test Course 1 Title'));
	
INSERT INTO answer (answer_text, question_id) VALUES 
	('The value a function or sequence approaches as the input or index approaches some value.',(select id from question where question = 'What is a limit?')), 
	('French guy that invented L''hopital''s rule.',(select id from question where question = 'Who was L''hopital?')), 
	('The slope of the tangent line at some point of a graphed function',(select id from question where question = 'What is a derivative?')), 
	('A mathematical object that can be interpreted as an area',(select id from question where question = 'What is an integral?')), 
	('The representation of a vector as a vector magnitude and angle.',(select id from question where question = 'Describe a polar vector.')), 
	('What we are learning.',(select id from question where question = 'What is calculus?')), 
	('A small rodent',(select id from question where question = 'What is a mouse?')), 
	('In front of you.',(select id from question where question = 'Where is the monitor?')), 
	('Press the power button while the computer is off.',(select id from question where question = 'How do you turn on the computer?')), 
	('Press the power button while the computer is on.',(select id from question where question = 'How do you turn off the computer?')), 
	('Random Access Memory.',(select id from question where question = 'What does RAM mean?')), 
	('Read Only Memory.',(select id from question where question = 'What does ROM mean?')), 
	('Ghostbusters!',(select id from question where question = 'Who you gonna call?')), 
	('Sho Nuff!',(select id from question where question = 'Who''s the baddest?')), 
	('She is lost.',(select id from question where question = 'Where in the world is Carmen Sandiego?')), 
	('Sure.',(select id from question where question = 'Have you ever header the wolf cry to the blue corn moon?'));	