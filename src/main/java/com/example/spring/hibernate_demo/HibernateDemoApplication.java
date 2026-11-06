package com.example.spring.hibernate_demo;

import com.example.spring.hibernate_demo.dao.StudentDao;
import com.example.spring.hibernate_demo.entity.Student;

import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.CommandLineRunner;

/**
 * Spring Boot application that runs student DAO operations at startup.
 */
@SpringBootApplication
public class HibernateDemoApplication {

	/**
	 * Starts the Spring Boot application.
	 *
	 * @param args command-line arguments passed to the application
	 */
	public static void main(String[] args) {
		SpringApplication.run(HibernateDemoApplication.class, args);
	}

	/**
	 * Creates the command-line task that runs a student operation when the
	 * application starts.
	 *
	 * @param studentDao the DAO used to perform student operations
	 * @return the startup task
	 */
	@Bean
	CommandLineRunner commandLineRunner(StudentDao studentDao) {
		return runner -> {
			createStudent(studentDao);
			// readStudent(studentDao);
			// queryStudent(studentDao);
			// queryStudentByLastName(studentDao);
			// updateStudent(studentDao);
			// deleteStudent(studentDao);
			// deleteAllStudents(studentDao);
		};
	}

	/**
	 * Creates a new student and saves the student information.
	 *
	 * @param studentDao the DAO used to persist the student
	 */
	private void createStudent(StudentDao studentDao) {
		System.out.println("Creating student information...");
		Student student = new Student(
				"Gray", "Magayi", "gray.magayi@example.com");

		System.out.println("Saving student information...");
		studentDao.save(student);

		System.out.println("Saved! Generated id	: " + student.getId());
	}

	/**
	 * Retrieves and prints the student with ID 2.
	 *
	 * @param studentDao the DAO used to retrieve the student
	 */
	private void readStudent(StudentDao studentDao) {
		System.out.println("Retrieving student id 2...");
		Student student = studentDao.findById(2);

		System.out.println("Retrieved student: " + student);
	}

	/**
	 * Queries and prints all students.
	 *
	 * @param studentDao the DAO used to query the students
	 */
	private void queryStudent(StudentDao studentDao) {
		List<Student> students = studentDao.findAll();

		for (Student student : students) {
			System.out.println(student);
		}
	}

	/**
	 * Retrieves and prints students whose last name is {@code Magayi}.
	 *
	 * @param studentDao the DAO used to find the students
	 */
	private void queryStudentByLastName(StudentDao studentDao) {
		List<Student> students = studentDao.findByLastName("Magayi");

		for (Student student : students) {
			System.out.println(student);
		}
	}

	/**
	 * Updates the information of a student with ID 2.
	 *
	 * @param studentDao the DAO used to update the student
	 */
	private void updateStudent(StudentDao studentDao) {
		int id = 2;
		System.out.println("Retrieving student id 2...");
		Student student = studentDao.findById(id);

		System.out.println("Updating student name...");
		student.setFirstName("Gray");

		studentDao.update(student);

		System.out.println("Updated student information: " + student);
	}

	/**
	 * Deletes the student with ID 4.
	 *
	 * @param studentDao the DAO used to delete the student
	 */
	private void deleteStudent(StudentDao studentDao) {
		int id = 4;
		System.out.println("Deleting student id 4...");
		studentDao.delete(id);

		System.out.println("Deleted student with id: " + id);
	}

	/**
	 * Deletes all students and prints the number of deleted records.
	 *
	 * @param studentDao the DAO used to delete the students
	 */
	private void deleteAllStudents(StudentDao studentDao) {
		System.out.println("Deleting all student information...");
		int numRowsDeleted = studentDao.deleteAll();

		System.out.println("Deleted " + numRowsDeleted + " students.");
	}
}