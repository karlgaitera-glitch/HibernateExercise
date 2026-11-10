package com.example.spring.hibernate_demo;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.spring.hibernate_demo.dao.AppDao;
import com.example.spring.hibernate_demo.dao.StudentDao;
import com.example.spring.hibernate_demo.entity.Course;
import com.example.spring.hibernate_demo.entity.Instructor;
import com.example.spring.hibernate_demo.entity.InstructorDetail;
import com.example.spring.hibernate_demo.entity.Review;
import com.example.spring.hibernate_demo.entity.Student;

/**
 * Represents the main entry point for the Spring Boot application.
 * 
 * Executes various Hibernate DAO demonstration operations upon startup.
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

	/**
	 * Creates a {@link CommandLineRunner} bean to execute Hibernate DAO
	 * operations upon application startup.
	 *
	 * @param appDao the DAO used to perform main application entity operations
	 * @return the startup task runner
	 */
	@Bean
	CommandLineRunner commandLineRunner(AppDao appDao) {
		return runner -> {
			// createInstructor(appDao);
			// findInstructorById(appDao);
			// deleteInstructorById(appDao);
			// findInstructorDetailById(appDao);
			// deleteInstructorDetailById(appDao);
			// createInstructorWithCourses(appDao);
			// findInstructorWithCourses(appDao);
			// findCoursesByInstructorId(appDao);
			// findInstructorWithCoursesJoinFetch(appDao);
			// updateInstructor(appDao);
			// deleteInstructorOnly(appDao);
			// createCourseAndReviews(appDao);
			// retrieveCourseAndReviews(appDao);
			// deleteCourseAndReviews(appDao);
			// createCourseAndStudents(appDao);
			// findCourseAndStudents(appDao);
			// findStudentAndCourses(appDao);
			// addMoreCoursesForStudent(appDao);
			// deleteCourseById(appDao);
			// deleteStudent(appDao);
		};
	}

	/**
	 * Creates a new instructor along with their detailed information 
	 * and persists them to the database.
	 *
	 * @param appDao the DAO used to persist the instructor
	 */
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

	/**
	 * Finds and prints an instructor and their associated details by ID.
	 *
	 * @param appDao the DAO used to retrieve the instructor
	 */
	private void findInstructorById(AppDao appDao) {
		int id = 1;
		System.out.println("Finding instructor with id: " + id);

		Instructor ins = appDao.findInstructorById(id);

		System.out.println("Found instructor: " + ins);
		System.out.println("Instructor details: " + ins.getInstructorDetail());
	}

	/**
	 * Deletes an instructor by their ID.
	 *
	 * @param appDao the DAO used to remove the instructor
	 */
	private void deleteInstructorById(AppDao appDao) {
		int id = 3;
		System.out.println("Deleting instructor with id: " + id);

		appDao.deleteInstructorById(id);
		System.out.println("Deleted successfully!");
	}

	/**
	 * Finds and prints instructor details and the associated 
	 * instructor by detail	ID.
	 *
	 * @param appDao the DAO used to retrieve the instructor detail
	 */
	private void findInstructorDetailById(AppDao appDao) {
		int id = 4;
		System.out.println("Finding instructor detail with id: " + id);

		InstructorDetail deets = appDao.findInstructorDetailById(id);

		System.out.println("Found instructor detail: " + deets);
		System.out.println("Associated instructor: " + deets.getInstructor());
	}

	/**
	 * Deletes an instructor detail record by ID.
	 *
	 * @param appDao the DAO used to delete the instructor detail
	 */
	private void deleteInstructorDetailById(AppDao appDao) {
		int id = 5;
		System.out.println("Deleting instructor detail with id: " + id);

		appDao.deleteInstructorDetailById(id);
		System.out.println("Deleted successfully!");
	}

	/**
	 * Creates an instructor along with multiple assigned courses 
	 * and persists them.
	 *
	 * @param appDao the DAO used to persist the instructor and courses
	 */
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

	/**
	 * Finds an instructor by ID and retrieves their associated courses.
	 *
	 * @param appDao the DAO used to retrieve the instructor
	 */
	private void findInstructorWithCourses(AppDao appDao) {
		int id = 5;
		System.out.println("Finding instructor with courses for id: " + id);

		Instructor ins = appDao.findInstructorById(id);

		System.out.println("Found instructor: " + ins);
		System.out.println("Associated courses: " + ins.getCourses());
	}

	/**
	 * Retrieves courses belonging to a specific instructor ID and associates 
	 * them	manually.
	 *
	 * @param appDao the DAO used to query courses by instructor ID
	 */
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

	/**
	 * Eagerly fetches an instructor and their associated courses in a single
	 * query using JOIN FETCH.
	 *
	 * @param appDao the DAO used to execute the JOIN FETCH query
	 */
	private void findInstructorWithCoursesJoinFetch(AppDao appDao) {
		int id = 8;
		System.out.println("Finding instructor with courses for id: " + id);

		Instructor ins = appDao.findInstructorByIdJoinFetch(id);

		System.out.println("Found instructor: " + ins);
		System.out.println("Associated courses: " + ins.getCourses());
	}

	/**
	 * Fetches an instructor, updates their last name, and merges changes 
	 * back into the database.
	 *
	 * @param appDao the DAO used to retrieve and update the instructor
	 */
	private void updateInstructor(AppDao appDao) {
		int id = 7;
		System.out.println("Finding instructor with id: " + id);

		Instructor ins = appDao.findInstructorById(id);
		System.out.println("Found instructor: " + ins);

		ins.setLastName("UpdatedLastName");

		System.out.println("Updated!");
		appDao.updateInstructor(ins);
	}

	/**
	 * Deletes an instructor record without deleting cascading entities like
	 * associated courses.
	 *
	 * @param appDao the DAO used to delete the instructor
	 */
	private void deleteInstructorOnly(AppDao appDao) {
		int id = 9;
		System.out.println("Delete instructor: " + id);

		appDao.deleteInstructorById(id);
		System.out.println("Deleted successfully!");
	}

	/**
	 * Creates a new course along with attached student reviews 
	 * and persists it.
	 *
	 * @param appDao the DAO used to persist the course and reviews
	 */
	private void createCourseAndReviews(AppDao appDao) {
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

	/**
	 * Retrieves a course and its associated reviews by course ID.
	 *
	 * @param appDao the DAO used to fetch the course and reviews
	 */
	private void retrieveCourseAndReviews(AppDao appDao) {
		int id = 9;

		Course course = appDao.findCourseAndReviewsByCourseId(id);
		System.out.println(course);
		System.out.println(course.getReview());
	}

	/**
	 * Deletes a course along with its associated reviews by ID.
	 *
	 * @param appDao the DAO used to delete the course
	 */
	private void deleteCourseAndReviews(AppDao appDao) {
		int id = 6;
		System.out.println("Delete course: " + id);

		appDao.deleteCourseById(id);
		System.out.println("Deleted successfully!");
	}

	/**
	 * Creates a new course, creates multiple students, enrolls them, 
	 * and persists	the entity tree.
	 *
	 * @param appDao the DAO used to save the course and enrolled students
	 */
	private void createCourseAndStudents(AppDao appDao) {
		Course course = new Course("How to be a Millionare");

		Student student1 = new Student(
				"Red", "Blue", "redblue@gmail.com");
		Student student2 = new Student(
				"White", "Black", "whiteblack@gmail.com");

		course.addStudent(student1);
		course.addStudent(student2);

		appDao.save(course);
		System.out.println("Successfully enrolled!");
	}

	/**
	 * Retrieves a course along with its list of enrolled 
	 * students by course ID.
	 *
	 * @param appDao the DAO used to retrieve the course and students
	 */
	private void findCourseAndStudents(AppDao appDao) {
		int id = 17;
		Course course = appDao.findCourseAndStudentByCourseId(id);

		System.out.println("Course: " + course);
		System.out.println("Students: " + course.getStudents());
	}

	/**
	 * Retrieves a student along with all courses they are enrolled 
	 * in by student ID.
	 *
	 * @param appDao the DAO used to fetch the student and courses
	 */
	private void findStudentAndCourses(AppDao appDao) {
		int id = 4;
		Student student = appDao.findStudentAndCoursesByStudentId(id);

		System.out.println("Course: " + student);
		System.out.println("Students: " + student.getCourses());
	}

	/**
	 * Retrieves a student by ID, enrolls them in additional courses, 
	 * and updates the record.
	 *
	 * @param appDao the DAO used to fetch and update student course 
	 * enrollments
	 */
	private void addMoreCoursesForStudent(AppDao appDao) {
		int id = 4;
		Student student = appDao.findStudentAndCoursesByStudentId(id);

		Course course1 = new Course("How to Test without Testing");
		Course course2 = new Course("How to Study without Studying");
		Course course3 = new Course(
				"10 Lifehacks to Remember (no. 4 will shock you)");

		student.addCourse(course1);
		student.addCourse(course2);
		student.addCourse(course3);

		System.out.println("Succesfully enrolled to new courses!");
		appDao.update(student);
	}

	/**
	 * Deletes a specific course by ID.
	 *
	 * @param appDao the DAO used to delete the course
	 */
	private void deleteCourseById(AppDao appDao) {
		int id = 20;
		System.out.println("Delete course " + id);

		appDao.deleteCourseById(id);
		System.out.println("Deleted successfully!");
	}

	/**
	 * Deletes a student by ID using the {@link AppDao}.
	 *
	 * @param appDao the DAO used to delete the student entity
	 */
	private void deleteStudent(AppDao appDao) {
		int id = 4;
		System.out.println("Delete student: " + id);

		appDao.deleteStudentById(id);
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