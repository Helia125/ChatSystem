CREATE DATABASE project;
--CREATE TABLES
	--delete from temp;
	--drop table temp;
	create table Temp
	(
		UserId VARCHAR(20),
		Fname VARCHAR(20),
		Lname VARCHAR(20),
		UserType VARCHAR(20)
	);
	SELECT * FROM temp;

	--delete from admins;
	--drop table admins;
	Create table Admins
	(
		UserId VARCHAR(20) Primary Key,
		Password Varchar(20),
		UserType VARCHAR(20),
		Fname VARCHAR(20),
		Lname VARCHAR(20)

	);
	SELECT * FROM admins;
	
	--delete from teachers;
	--Drop table teachers;
	Create table Teachers
	(
		UserId VARCHAR(20) Primary Key,
		Password Varchar(20),
		UserType VARCHAR(20),
		Fname VARCHAR(20),
		Lname VARCHAR(20),
		Email VARCHAR(40),
		Gender VARCHAR(5),
		Birth DATE,
		Phone BIGINT,
		Subject VARCHAR(20)
	);
	SELECT * FROM teachers;
	
	--delete from Students;
	--drop table Students;
	Create table Students
	(
		UserId VARCHAR(20),
		UserType VARCHAR(20),
		Fname VARCHAR(20),
		Lname VARCHAR(20),
		Email VARCHAR(40),
		Gender VARCHAR(5),
		Birth DATE,
		Phone BIGINT,
	);
	SELECT * FROM students;
	
	--delete from postnotice;
	--drop table PostNotice;
	create table PostNotice
	(
		ID VARCHAR(20),
		Description VARCHAR(500),
	);
	SELECT * FROM PostNotice;
	
	--delete from messages;
	--drop table Messages;
	create table Messages
	(
		Inbox VARCHAR(20),
		message VARCHAR(200),
		Outbox VARCHAR(20)
	);
	SELECT * FROM Messages;
	
	--delete from attendance;
	--drop table Attendance;
	create table Attendance
	(
		UserId VARCHAR(20),
		Name VARCHAR(40),
		Date DATE,
		Attended VARCHAR(20),
		Count INT
	);
	SELECT * FROM Attendance;
	
	--delete from attendance2;
	--drop table Attendance2;
	create table Attendance2
	(
		UserId VARCHAR(20),
		Name VARCHAR(40),
		Date DATE,
		Attended VARCHAR(20),
		Count INT
	);
	SELECT * FROM Attendance2;
	
	--delete from SCIENCE;
	delete from SCIENCE
	--drop table SCIENCE;	--CHANGE TO OTHER SUBJECTS 
	Create table SCIENCE	--ENGLISH, GEOGRAPHY, HISTORY, IT, MATH, SCIENCE ...
	(
	Userid varchar(20),
	Name varchar(20),
	Sem1 Float,
	Sem2 Float,
	Average Float
	);
--END OF CREATE TABLES


--TRIGGERS
	--trigger to add students to subjects
	drop trigger trgStudent;
	Create trigger trgStudent
	on students
	for insert
	as
	declare 
	@UserId VARCHAR(20),
	@SName VARCHAR(40)
	begin
	select @UserId=UserID from inserted;
	select @SName=FName+' '+LName from inserted;
	insert into english values(@UserId, @SName, 0, 0, 0);
	insert into Math values(@UserId, @SName, 0, 0, 0);
	insert into Science values(@UserId, @SName, 0, 0, 0);
	insert into IT values(@UserId, @SName, 0, 0, 0);
	insert into History values(@UserId, @SName, 0, 0, 0);
	insert into Geography values(@UserId, @SName, 0, 0, 0);
	end

	--trigger to add students to Attendance
	drop trigger trgStudent2;
	Create trigger trgStudent2
	on students
	for insert
	as
	declare 
	@UserId2 VARCHAR(20),
	@SName2 VARCHAR(40)
	begin
	select @UserId2=UserID from inserted;
	select @SName2=FName+' '+LName from inserted;
	insert into Attendance values(@UserId2, @SName2, null, null, 0);
	end

	--trigger to delete students in Attendance
	drop trigger trgStudent3;
	Create trigger trgStudent3
	on students
	for delete
	as
	declare 
	@UserId3 VARCHAR(20),
	@SName3 VARCHAR(40)
	begin
	select @UserId3=UserID from inserted;
	delete from Attendance where UserId = @UserId3;
	end
--END OF TRIGGERS

delete from Attendance where UserId = 's#1002';
--INSERT VALUES

	--ADMIN
	Insert into Admins Values('samir','samir','Admin', 'Samir', 'Nasser');
	Insert into Admins Values('salman','salman','Admin', 'Salman', 'Fazal');
	SELECT * FROM Admins;

	--TEACHERS
	Insert into teachers Values('salman1', 'salman','teacher', 'Salman', 'Fazal', 'salman.fazal@live.com', 'M', '1996-08-01', 255684530650, 'English');
	SELECT * FROM Teachers;

	--STUDENTS
	Insert into students Values('salman','teacher', 'Salman', 'Fazal', 'salman.fazal@live.com', 'M', '08-01-1996', 255684530650);
	SELECT * FROM Students;

	--POST NOTICE	
	insert into PostNotice values ('salman', 'samdjsfsda');
	SELECT * FROM PostNotice;

	--MESSAGES	
	insert into messages values('salman', 'test message', 'salman');
	SELECT * FROM Messages;

	--ATTENDANCE
	INSERT INTO attendance VALUES('123', 'test', 'test2', 1+1);
	SELECT * FROM Attendance;

	--SUBJECTS
	INSERT INTO English VALUES('1234', 'Salman Fazal', 20, 40 ,30);
	INSERT INTO English VALUES('123rdf4', 'fdg Fafgdfgzal', 90, 40 , );
	SELECT * FROM English;

--END OF INSERT VALUES


--OTHER CODES
	SP_TABLES;

	SELECT * FROM students UNION SELECT * FROM Teachers WHERE password='salman';
	UPDATE admin SET Password='salman' WHERE Password='salman';
	select * from Users order by UserType;
	DELETE FROM teachers WHERE userId LIKE 'sall';
	SELECT userid, password FROM admin WHERE password = 'salman' UNION SELECT userid, password FROM teachers WHERE password = 'salman';

	SELECT Userid, Name, Sem1, Sem2, (ISNULL(Sem1,3)+ISNULL(Sem2,4))/3.0 AS Average FROM ENGLISH;

	UPDATE Attendance SET Count=(Count+1), Date=(GETDATE()), Attended=('Present')  where UserId = 's#1001';
	select * from Attendance where UserId='s#1001';
	insert into Attendance values('S#1003', 'Test Student', GETDATE(), null, 1);
	delete from Attendance where UserId='';

	SELECT  Userid, Name, Date, Count FROM Attendance order by UserId;
	
--END OF OTHER CODES