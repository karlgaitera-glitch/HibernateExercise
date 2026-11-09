package com.example.spring.hibernate_demo;

import com.example.spring.hibernate_demo.dao.StudentDao;
import com.example.spring.hibernate_demo.entity.Student;
import com.example.spring.hibernate_demo.dao.AppDao;
import com.example.spring.hibernate_demo.entity.Instructor;
import com.example.spring.hibernate_demo.entity.InstructorDetail;
import com.example.spring.hibernate_demo.entity.Review;
import com.example.spring.hibernate_demo.entity.Course;

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
	/*
	 * @Bean
	 * CommandLineRunner commandLineRunner(StudentDao studentDao) {
	 * return runner -> {
	 * // createStudent(studentDao);
	 * // readStudent(studentDao);
	 * // queryStudent(studentDao);
	 * // queryStudentByLastName(studentDao);
	 * // updateStudent(studentDao);
	 * // deleteStudent(studentDao);
	 * // deleteAllStudents(studentDao);
	 * };
	 * }
	 */

	@Bean
	CommandLineRunner commandLineRunner(AppDao appDao) {
		return runner -> {
			//createInstructor(appDao);
			//findInstructorById(appDao);
			//deleteInstructorById(appDao);
			//findInstructorDetailById(appDao);
			//deleteInstructorDetailById(appDao);
			//createInstructorWithCourses(appDao);
			//findInstructorWithCourses(appDao);
			//findCoursesByInstructorId(appDao);
			//findInstructorWithCoursesJoinFetch(appDao);
			//updateInstructor(appDao);		
			//deleteInstructorOnly(appDao);	
			//createCourseAndReviews(appDao);
			//retrieveCourseAndReviews(appDao);
			deleteCourseAndReviews(appDao);
		};
	}

	private void createInstructor(AppDao appDao) {
		Instructor ins = new Instructor("Warl",
				"Waitera",
				"Warl.Waitera@gmail.com");
		InstructorDetail deets = new InstructorDetail("w2Maths",
				"w3Solving math problems");
		ins.setInstructorDetail(deets);
		System.out.println("Saving instructor: " + ins);
		appDao.save(ins);
	}
	
	private void findInstructorById(AppDao appDao) {
		int id = 1;
		System.out.println("Finding instructor with id: " + id);

		Instructor ins = appDao.findInstructorById(id);

		System.out.println("Found instructor: " + ins);
		System.out.println("Instructor details: " + ins.getInstructorDetail());
	}

	private void deleteInstructorById(AppDao appDao) {
		int id = 3;
		System.out.println("Deleting instructor with id: " + id);

		appDao.deleteInstructorById(id);
		System.out.println("Deleted successfully!");
	}

	private void findInstructorDetailById(AppDao appDao) {
		int id = 4;
		System.out.println("Finding instructor detail with id: " + id);

		InstructorDetail deets = appDao.findInstructorDetailById(id);

		System.out.println("Found instructor detail: " + deets);
		System.out.println("Associated instructor: " + deets.getInstructor());
	}

	private void deleteInstructorDetailById(AppDao appDao) {
		int id = 5;
		System.out.println("Deleting instructor detail with id: " + id);

		appDao.deleteInstructorDetailById(id);
		System.out.println("Deleted successfully!");
	}

	private void createInstructorWithCourses(AppDao appDao) {
		Instructor ins = new Instructor("Damn", "Damn", 
			"damn@gmail.com");
		InstructorDetail deets = new InstructorDetail("English",
				"Solving english problems");
		ins.setInstructorDetail(deets);

		Course course1 = new Course("Java Programming");
		Course course2 = new Course("Python Programming");

		ins.addCourse(course1);
		ins.addCourse(course2);

		System.out.println("Saving instructor: " + ins);
		System.out.println("Associated courses: " + ins.getCourses());
		appDao.save(ins);
	}

	private void findInstructorWithCourses(AppDao appDao) {
		int id = 5;
		System.out.println("Finding instructor with courses for id: " + id);

		Instructor ins = appDao.findInstructorById(id);

		System.out.println("Found instructor: " + ins);
		System.out.println("Associated courses: " + ins.getCourses());
	}

	private void findCoursesByInstructorId(AppDao appDao) {
		int id = 7;
		System.out.println("Finding courses for instructor id: " + id);

		Instructor ins = appDao.findInstructorById(id);
		System.out.println("Found instructor: " + ins);

		System.out.println("Finding courses for instructor id: " + id);
		List<Course> courses = appDao.findCoursesByInstructorId(id);

		ins.setCourses(courses);
		System.out.println("Associated courses: " + ins.getCourses());
	}

	private void findInstructorWithCoursesJoinFetch(AppDao appDao) {
		int id = 8;
		System.out.println("Finding instructor with courses for id: " + id);

		Instructor ins = appDao.findInstructorByIdJoinFetch(id);

		System.out.println("Found instructor: " + ins);
		System.out.println("Associated courses: " + ins.getCourses());
	}

	private void updateInstructor(AppDao appDao) {
		int id = 7;
		System.out.println("Finding instructor with id: " + id);

		Instructor ins = appDao.findInstructorById(id);
		System.out.println("Found instructor: " + ins);

		ins.setLastName("UpdatedLastName");

		System.out.println("Updated!");
		appDao.updateInstructor(ins);
	}

	private void deleteInstructorOnly(AppDao appDao){
		int id = 9;
		System.out.println("Delete instructor: " + id);
		
		appDao.deleteInstructorById(id);
		System.out.println("Deleted successfully!");
	}

	private void createCourseAndReviews(AppDao appDao){
		Course course = new Course("How to Speed Run Spring in 1 Day");
		course.addReview(new Review(
			"Great speed 2x...loved it!!!!"));
		course.addReview(new Review(
			"I wanted speed 0.75:(((((("));
		course.addReview(new Review(
			"Hoping to do a speed of 3x :D. Anyways, nice."));

		System.out.println("Course saved!");
		appDao.save(course);
	}

	private void retrieveCourseAndReviews(AppDao appDao){
		int id = 9;

		Course course = appDao.findCourseAndReviewsByCourseId(id);
		System.out.println(course);
		System.out.println(course.getReview());
	}

	private void deleteCourseAndReviews(AppDao appDao){
		int id = 6;
		System.out.println("Delete course: " + id);

		appDao.deleteCourseById(id);
		System.out.println("Deleted successfully!");
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