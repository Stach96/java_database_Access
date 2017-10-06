import java.sql.*;

public class TestConnection {

	public static void main(String[] argS) throws SQLException {
		DataBase db = new DataBase("C:/Users/Chiqu/workspace/DataBaseProject/src/Java_school.accdb");
		//initialize conn
		db.initializeConn();
		System.out.println(db.getStudentNameSurname());
	}
}