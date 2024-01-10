package jdbc_user;

import java.util.Scanner;

public class UserController {

	public static void main(String[] args) throws Exception {

		User user = new User();

		UserCRUD crud = new UserCRUD();

		Scanner scanner = new Scanner(System.in);

		System.out.println("Enter the choice \n1.SignUp \n2.LogIn");

		int choice = scanner.nextInt();

		switch (choice) {

		case 1: {
			System.out.println("Enter the id : ");
			int id = scanner.nextInt();

			System.out.println("Enter the name : ");
			String name = scanner.next();

			System.out.println("Enter the phone : ");
			long phone = scanner.nextLong();

			System.out.println("Enter the email : ");
			String email = scanner.next();

			System.out.println("Enter the password : ");
			String password = scanner.next();

			user.setId(id);
			user.setName(name);
			user.setPhone(phone);
			user.setEmail(email);
			user.setPassword(password);

			crud.signUpUser(user);

			break;
		}

		case 2: {
			System.out.println("Enter the email : ");
			String email = scanner.next();

			System.out.println("Enter the password : ");
			String password = scanner.next();

			boolean result = crud.logIn(email, password);

			if (result) {
				System.out.println("Login Successful");
				crud.showPasswords(email);
			} else {
				System.out.println("Login Fail");
			}
			
//			updatePasswords()

			break;
		}

		}
		scanner.close();
	}

}






//update user set facebook=?,whatsapp=?,instagram=?,snapchat=?,twitter=? where email=?