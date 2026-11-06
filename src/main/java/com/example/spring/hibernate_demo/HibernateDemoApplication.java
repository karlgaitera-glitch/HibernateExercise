package com.example.spring.hibernate_demo;

import com.example.spring.hibernate_demo.dao.StudentDao;
import com.example.spring.hibernate_demo.entity.Student;

import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.CommandLineRunner;


@SpringBootApplication
public class HibernateDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(HibernateDemoApplication.class, args);
	}

	@Bean 
	CommandLineRunner commandLineRunner(StudentDao studentDao) {
		return runner -> {
			createStudent(studentDao);
			//readStudent(studentDao);
			//queryStudent(studentDao);
			//queryStudentByLastName(studentDao);
			//updateStudent(studentDao);
			//deleteStudent(studentDao);
			//deleteAllStudents(studentDao);
		};
	}

	private void createStudent(StudentDao studentDao) {
		System.out.println("Creating student information...");
		Student student = new Student("Gray", "Magayi", "gray.magayi@example.com");

		System.out.println("Saving student information...");
		studentDao.save(student);

		System.out.println("Saved! Generated id	: " + student.getId());
	}

	private void readStudent(StudentDao studentDao) {
		System.out.println("Retrieving student id 2...");
		Student student = studentDao.findById(2);

		System.out.println("Retrieved student: " + student);
	}

	private void queryStudent(StudentDao studentDao) {
		List<Student> students = studentDao.findAll();

		for (Student student : students) {
			System.out.println(student);
		}
	}

	private void queryStudentByLastName(StudentDao studentDao) {
		List<Student> students = studentDao.findByLastName("Magayi");

		for (Student student : students) {
			System.out.println(student);
		}
	}

	private void updateStudent(StudentDao studentDao) {
		int id = 2;
		System.out.println("Retrieving student id 2...");
		Student student = studentDao.findById(id);

		System.out.println("Updating student name...");
		student.setFirstName("Gray");
		
		studentDao.update(student);

		System.out.println("Updated student information: " + student);
	}

	private void deleteStudent(StudentDao studentDao) {
		int id = 4;
		System.out.println("Deleting student id 4...");
		studentDao.delete(id);

		System.out.println("Deleted student with id: " + id);
	}

	private void deleteAllStudents(StudentDao studentDao) {
		System.out.println("Deleting all student information...");
		int numRowsDeleted = studentDao.deleteAll();

		System.out.println("Deleted " + numRowsDeleted + " students.");
	}
}

