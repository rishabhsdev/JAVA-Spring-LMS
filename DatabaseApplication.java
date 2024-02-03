package com.example.learn.database;

import java.util.List;
import java.util.Scanner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.learn.database.dao.AppDAO;
import com.example.learn.database.entity.*;

@SpringBootApplication
public class DatabaseApplication {

	public static void main(String[] args) {
		SpringApplication.run(DatabaseApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(AppDAO appDAO) {
		return runner -> {
			selection(appDAO);
		};
	}

	private void selection(AppDAO appDAO) {
		Scanner input = new Scanner(System.in);

		outerloop: while (true) {
			System.out
					.println(
							"Make a Selection:\n 1. Add Instructor\n 2. Add course\n 3. Add review\n 4. Add student\n 5. Exit");
			int selection = Integer.parseInt(input.nextLine());

			switch (selection) {
				case 1:
					createInstructor(appDAO);
					break;

				case 2:
					createCourse(appDAO);
					break;

				case 3:
					createReview(appDAO);
					break;

				case 4:
					createStudent(appDAO);
					break;

				case 5:
					break outerloop;

				default:
					System.out.println("Invalid input");
					break;
			}
		}
		input.close();
	}

	private void createStudent(AppDAO appDAO) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'createStudent'");
	}

	private void createReview(AppDAO appDAO) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'createReview'");
	}

	private void createCourse(AppDAO appDAO) {
		Scanner input = new Scanner(System.in);
		Instructor instructor;

		outerloop: while (true) {
			System.out.println("Enter 1 to use an existing instructor, 2. to create one: ");
			int choice = Integer.parseInt(input.nextLine());

			switch (choice) {
				case 1:
					instructor = findInstructor(appDAO);
					System.out.println("Instructor found: " + instructor.getFirst_name() + instructor.getLast_name());
					break outerloop;

				case 2:
					instructor = createInstructor(appDAO);
					System.out.println("Instructor found: " + instructor.getFirst_name() + instructor.getLast_name());
					break outerloop;

				default:
					System.out.println("Invalid input, try again");
					break;
			}
		}

		System.out.println("Enter Course details:\nCourse Title: ");
		String title = input.nextLine();

		instructor.addCourse(new Course(title));
		appDAO.save(instructor);

		input.close();

	}

	// private void createInstructorWithCourse(AppDAO appDAO) {
	// Instructor instructor = new Instructor("Morgan", "B", "james@mail.com");

	// instructor.setInstructorDetails(new InstructorDetails("yt.com/james",
	// "guitar"));

	// instructor.addCourse(new Course("Finance lessons"));
	// instructor.addCourse(new Course("Learn stock"));

	// appDAO.save(instructor);
	// }

	private void updateInstructor(AppDAO appDAO) {
		Scanner input = new Scanner(System.in);

		System.out.println("Enter the id of instructor you want to update: ");
		int id = Integer.parseInt(input.nextLine());

		Instructor instructor = appDAO.findInstructorById(id);

		System.out.println("Instructor found: " + instructor);
		System.out.println("Enter new hobby: ");
		String hobby = input.nextLine();

		// System.out.println(hobby);

		instructor.getInstructorDetails().setHobby(hobby);

		appDAO.save(instructor);

		input.close();
	}

	private void findInstructorDetails(AppDAO appDAO) {
		// Scanner input = new Scanner(System.in);

		// System.out.println("Enter the id of instructor details you want to fetch: ");
		// int id = input.nextInt();

		InstructorDetails details = appDAO.findDetailsById(1);
		System.out.println("Instructor: " + details);
		System.out.println("Instructor: " + details.geInstructor());

		// input.close();
	}

	private Instructor findInstructor(AppDAO appDAO) {

		Scanner input = new Scanner(System.in);

		System.out.println("Enter the email of instructor you want to fetch: ");
		String email = input.nextLine();
		input.close();

		return appDAO.findInstructor(email);
	}

	public Instructor createInstructor(AppDAO appDAO) {

		Scanner input = new Scanner(System.in);

		Instructor instructor = null;

		while (true) {
			System.out.println("Enter 1 for adding a instructor, 2 for exit: ");
			String choice = input.nextLine();

			if (!choice.equals("1")) {
				System.out.println(choice);
				break;

			}

			System.out.println("Enter Instructor's First name: ");
			String firstName = input.nextLine();
			// System.out.println(firstName);

			System.out.println("Enter Instructor's Last name: ");
			String lastName = input.nextLine();

			System.out.println("Enter email: ");
			String email = input.nextLine();

			System.out.println("Enter Instructor's Youtube channel: ");
			String yt = input.nextLine();

			System.out.println("Enter Instructor's hobby: ");
			String hobby = input.nextLine();

			instructor = new Instructor(firstName, lastName, email);

			instructor.setInstructorDetails(new InstructorDetails(yt, hobby));

			appDAO.save(instructor);

			// fetch newely saved instructor from the database to avoid unique identifier
			// (id) mismatch
			instructor = appDAO.findInstructor(email);

		}

		// input.close();

		return instructor;

	}

	// public String getInput() {
	// Scanner input = new Scanner(System.in);

	// input.nextLine();

	// String value = input.nextLine();
	// input.close();

	// return value;
	// }

}
