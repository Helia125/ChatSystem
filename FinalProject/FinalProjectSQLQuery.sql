CREATE DATABASE Project;
--to Create table in Project database
use Project
go
--Create table temp
	create table Temp
	(
		UserId VARCHAR(20),
		Fname VARCHAR(20),
		Lname VARCHAR(20),
		UserType VARCHAR(20)
	);
	SELECT * FROM Temp;
--Create table admins
	Create table Admins
	(
		UserId VARCHAR(20) Primary Key,
		Password Varchar(20),
		UserType VARCHAR(20),
		Fname VARCHAR(20),
		Lname VARCHAR(20)

	);
	SELECT * FROM Admins;
--Create table teachers
	Create table Teachers
	(
		UserId VARCHAR(20) Primary Key,
		Password Varchar(20),
		UserType VARCHAR(20),
		Fname VARCHAR(20),
		Lname VARCHAR(20),
		Email VARCHAR(40),
		Gender VARCHAR(5),
		Subject VARCHAR(20)
	);
	SELECT * FROM Teachers;
--Create table students
	Create table Students
	(
		UserId VARCHAR(20),
		UserType VARCHAR(20),
		Fname VARCHAR(20),
		Lname VARCHAR(20),
		Email VARCHAR(40),
		Gender VARCHAR(5),
	
	);
	SELECT * FROM Students;
--Create table postnotice
	create table PostNotice
	(
		ID VARCHAR(20),
		Description VARCHAR(500),
	);
	SELECT * FROM PostNotice;
	
	--Create table messages
	create table Messages
	(
		Inbox VARCHAR(20),
		message VARCHAR(200),
		Outbox VARCHAR(20)
	);
	SELECT * FROM Messages;
	--Create table java
	Create table JAVA	
	(
	Userid varchar(20),
	Name varchar(20),
	Midterm Float,
	Final Float,
	Total Float
	);
	--Create table datastructure
	Create table DataStructure
	(
	Userid varchar(20),
	Name varchar(20),
	Midterm Float,
	Final Float,
	Total Float
	);
	--Create table math
     Create table MATH
	 (
	Userid varchar(20),
	Name varchar(20),
	Midterm Float,
	Final Float,
	Total Float
	);
	--Create table python
	Create table Python
	(
	Userid varchar(20),
	Name varchar(20),
	Midterm Float,
	Final Float,
	Total Float
	);
	--Create table dsp
	Create table DSP
	(
	Userid varchar(20),
	Name varchar(20),
	Midterm Float,
	Final Float,
	Total Float
	);
--END OF CREATE TABLES