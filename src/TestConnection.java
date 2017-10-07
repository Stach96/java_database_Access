import java.sql.*;

public class TestConnection {

	public static void main(String[] argS) throws SQLException {
		DataBase db = new DataBase("src/Java_school.accdb");
		//initialize conn
		db.initializeConn();
		Student student = new Student(db, "lorenzo.stacchio","password");
		System.out.println(student.getData());
		
	}
}