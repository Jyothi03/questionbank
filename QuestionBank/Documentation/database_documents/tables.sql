drop table if exists `answer`; 
drop table if exists `question`;
drop table if exists `teaches`;
drop table if exists `person`;
drop table if exists `course`;
drop table if exists `department`;

CREATE TABLE `person`(
  `id` MEDIUMINT NOT NULL AUTO_INCREMENT,
  `first_name` varchar(50),
  `last_name` varchar(50),
  `user_name` varchar(20),
  `password` varchar(20),
  `admin` boolean not null default 0,
  primary key (`id`)
) ENGINE=InnoDB;

CREATE TABLE `department`(
  `id` MEDIUMINT NOT NULL AUTO_INCREMENT,
  `name` varchar(50),
  `abbreviation` varchar(10),
  primary key (`id`)
) ENGINE=InnoDB;

create table `course`(
	`id`   MEDIUMINT NOT NULL AUTO_INCREMENT,
	`course_name`     varchar(64),
	`course_number`	  varchar(12),
	`department_id` MEDIUMINT,
	`credit`          int,
	primary key (`id`),
	constraint `fk_course_department_id` foreign key (`department_id`) 
		references `department`(`id`)
) ENGINE=InnoDB;

create table `teaches`(
	`person_id` MEDIUMINT,
	`course_id` MEDIUMINT,
	primary key (`person_id`, `course_id`),
	constraint `fk_teaches_person_id` foreign key (`person_id`) 
		references `person`(`id`),
	constraint `fk_teaches_course_id` foreign key (`course_id`) 
		references `course`(`id`)
) ENGINE=InnoDB;


create table `question`(
	`id` MEDIUMINT NOT NULL AUTO_INCREMENT,
	`course_id` MEDIUMINT,
	`question` varchar(256),
	`chapter` varchar(7),
	primary key (`id`),
	constraint `fk_question_course_id` foreign key (`course_id`) 
		references `course`(`id`)
) ENGINE=InnoDB;

create table `answer`(
	`id` MEDIUMINT NOT NULL AUTO_INCREMENT,
	`question_id` MEDIUMINT,
	`answer_text` varchar(256),
	primary key (id),
	constraint `fk_answer_question_id` foreign key (`question_id`) 
		references `question`(`id`)
) ENGINE=InnoDB;

