import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Employee_Management {
	public static String DB_Url ="jdbc:mysql://localhost:3306/database";
	public static String User ="root";	
	public static String Password ="root";
	public static String Query ="SELECT * FROM credentials";		
	static Scanner sc=new Scanner(System.in);


	public static void ManageEmployeeModule(Connection conn) throws SQLException {
		Statement stmt = conn.createStatement();
		System.out.println("Enter 1 for Add, 2 for Edit & 3 for View");
		int Choice=sc.nextInt();
		switch (Choice) {
		case 1:{
			System.out.println("Enter Employee Id");
			int id=sc.nextInt();
			System.out.println("Enter Employee Name");
			String name=sc.next();
			System.out.println("Enter Employee Designation");
			String des=sc.next();
			System.out.println("Enter Employee Salary");
			int sal=sc.nextInt();
			String Query="INSERT INTO emp_table(Emp_Id,Emp_Name,Emp_Designation,Emp_Salary)Values("+id+",\""+name+"\",\""+des+"\","+sal+")";
			int rs = stmt.executeUpdate(Query);
			break;
		}
		case 2:{
			System.out.println("Enter a Employee Id in which you want to edit a data");
			int Id=sc.nextInt();
			System.out.println("Enter 1 for Edit Name, 2 for Edit Designation & 3 for Edit Salary");
			int Option=sc.nextInt();
			switch (Option) {
			case 1:{
				System.out.println("Enter Employee Name");
				String name=sc.next();
				String Query="UPDATE emp_table SET emp_Name = \""+name+"\" WHERE emp_Id="+Id;
				int rs = stmt.executeUpdate(Query);
				break;
			}
			case 2:{
				System.out.println("Enter Employee Designation");
				String designation=sc.next();
				String Query="UPDATE emp_table SET emp_Designation = \""+designation+"\" WHERE emp_Id="+Id;
				int rs = stmt.executeUpdate(Query);
				break;
			}
			case 3:{
				System.out.println("Enter Employee Salary");
				String salary=sc.next();
				String Query="UPDATE emp_table SET emp_Salary = \""+salary+"\" WHERE emp_Id="+Id;
				int rs = stmt.executeUpdate(Query);
				break;
			}
			}
			break;
		}
		case 3:{
			String Query="SELECT * FROM emp_table";
			ResultSet rs = stmt.executeQuery(Query);
			while(rs.next()) {
				System.out.println(rs.getInt("Emp_Id"));
				System.out.println(rs.getString("Emp_Name"));
				System.out.println(rs.getString("Emp_Designation"));
				System.out.println(rs.getInt("Emp_Salary"));
			}
			break;
		}
		default:{
			System.out.println("Enter a valid choice");	
		}
		}
	}


	public static void SearchModule(Connection conn) throws SQLException {
		String query="SELECT * FROM emp_table";
		Statement stmt = conn.createStatement();
		ResultSet rs1=stmt.executeQuery(query);
		System.out.println("Enter a Employee Id");
		int id=sc.nextInt();
		while(rs1.next()) {
			if(rs1.getInt("Emp_Id")==id) {
				System.out.println(rs1.getInt("Emp_Id"));
				System.out.println(rs1.getString("Emp_Name"));
				System.out.println(rs1.getString("Emp_Designation"));
				System.out.println(rs1.getInt("Emp_Salary"));
			}
		}
	}


	public static void DeleteModule(Connection conn) throws SQLException {
		System.out.println("Enter a Employee Id");
		int id=sc.nextInt();
		Statement stmt = conn.createStatement();
		String query="DELETE FROM emp_table WHERE Emp_Id="+id;
		int rs=stmt.executeUpdate(query);
		String query2="SELECT * FROM emp_table";
		ResultSet rs1=stmt.executeQuery(query2);
		while(rs1.next()) {
			System.out.println(rs1.getInt("Emp_Id"));
			System.out.println(rs1.getString("Emp_Name"));
			System.out.println(rs1.getString("Emp_Designation"));
			System.out.println(rs1.getInt("Emp_Salary"));
		}
	}


	public static void main(String[] args) throws ClassNotFoundException {

		Class.forName("com.mysql.cj.jdbc.Driver");

		System.out.println("Enter username ");
		String username=sc.next();
		System.out.println("Enter password ");
		String pass=sc.next();

		try(Connection conn=DriverManager.getConnection(DB_Url,User,Password);

				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(Query);){

			while(rs.next()) {
				if((rs.getString("Username")).equals(username) && (rs.getString("Password")).equals(pass)) {
					System.out.println("Press 1 for Manage Employee Module,Press 2 for Delete Module & Press 3 for Search Module");
					int ch=sc.nextInt();
					switch (ch){
					case 1:
					{
						ManageEmployeeModule(conn);
						break;
					}
					case 2:
					{
						DeleteModule(conn);
						break;
					}
					case 3:
					{
						SearchModule(conn);
						break;
					}
					default:{

						System.out.println("Enter the valid Choice");
					}
					}
				}
				else 
				{
					conn.close();
					break;
				}
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}
}
