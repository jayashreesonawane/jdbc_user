package jdbc_user;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;

public class UserCRUD {

	public Connection getConnection() throws Exception {
		FileReader reader = new FileReader("dbconfig.properties");

		Properties properties = new Properties();

		properties.load(reader);

		Class.forName(properties.getProperty("className"));

		Connection connection = DriverManager.getConnection(properties.getProperty("url"),
				properties.getProperty("user"), properties.getProperty("password"));

		return connection;

	}

	public void signUpUser(User user) throws Exception {
		Connection connection = getConnection();

		PreparedStatement preparedStatement = connection
				.prepareStatement("INSERT INTO USER(id,name,phone,email,password) VALUES(?,?,?,?,?)");

		preparedStatement.setInt(1, user.getId());
		preparedStatement.setString(2, user.getName());
		preparedStatement.setLong(3, user.getPhone());
		preparedStatement.setString(4, user.getEmail());
		preparedStatement.setString(5, user.getPassword());

		int result = preparedStatement.executeUpdate();

		if (result != 0) {
			System.out.println("Data Inserted");
		} else {
			System.out.println("Data Not Inserted");
		}

		connection.close();

	}

	public boolean logIn(String email, String password) throws Exception {
		Connection connection = getConnection();

		PreparedStatement preparedStatement = connection.prepareStatement("SELECT PASSWORD FROM USER WHERE EMAIL=?");
		preparedStatement.setString(1, email);

		ResultSet resultSet = preparedStatement.executeQuery();

		String dbPassword = null;

		while (resultSet.next()) {
			dbPassword = resultSet.getString("password");
		}

		connection.close();

		if (password.equals(dbPassword)) {

			return true;

		} else {
			return false;
		}

	}

	public void showPasswords(String email) throws Exception {
		Connection connection = getConnection();

		PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM USER WHERE EMAIL=?");
		preparedStatement.setString(1, email);

		ResultSet resultSet = preparedStatement.executeQuery();

		String dbPassword = null;

		while (resultSet.next()) {
			System.out.println("Saved Passwords Are = ");
			System.out.println("Facebook Password : " + resultSet.getString("facebook"));
			System.out.println("WhatsApp Password : " + resultSet.getString("whatsapp"));
			System.out.println("Instagram Password : " + resultSet.getString("instagram"));
			System.out.println("Snapchat Password : " + resultSet.getString("snapchat"));
			System.out.println("Twitter Password : " + resultSet.getString("twitter"));
			
//			System.out.println("Enter the choice \n1.Update Passwords \n2.LogOut");
//			int choice = scanner.nextInt();
//			
//			switch (choice) {
//			case 1:
//				System.out.println("Enter the password you want to update : ");
//				
//				
//				break;
//
//			default:
//				break;
//			}
//		}

		connection.close();
		}
	}
}

