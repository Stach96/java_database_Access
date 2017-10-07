import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.hsqldb.rights.User;

public class DataBase {
	private Connection conn;
	private Statement statement;
	private ResultSet result;
	private String dbPath;

	public DataBase(String dbPath) {
		this.dbPath = dbPath;
		this.statement = null;
		this.conn = null;
		this.result = null;
	}

	/**
	 * Public method to initialize connection
	 */
	public void initializeConn() {
		try {
			this.conn = DriverManager.getConnection("jdbc:ucanaccess://" + this.dbPath);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Method that return student name
	 * 
	 * @return array of student name
	 */
	private List<String> getStudentNameSurname() {
		// declare and initialize list
		List<String> listaStudenti = new ArrayList<String>();
		try {
			// create statement
			this.statement = this.conn.createStatement();
			// get result of query
			this.result = this.statement
					.executeQuery("SELECT Studenti.nome,Studenti.cognome FROM Studenti Order by Studenti.cognome");
			// take all the results and put in list
			while (this.result.next()) {
				// get string work with columns not raws
				listaStudenti.add(this.result.getString(1) + " " + this.result.getString(2));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listaStudenti;
	}

	/**
	 * This method return the student data only if there is an allowed user. The
	 * authorization mechanism is implemented with a match of username/password
	 * insert by user to username/password saved in database.
	 * 
	 * @param userName
	 * @param password
	 * @return student data
	 */
	public List<String> returnStudentData(String userName, String password) {
		// declare and initialize list
		List<String> studentData = new ArrayList<String>();
		try {
			// create statement
			this.statement = this.conn.createStatement();
			// get result of query
			this.result = this.statement.executeQuery("SELECT * FROM Studenti\n" + "WHERE Studenti.userName ='" + userName
					+ "' and Studenti.password='" + password+"'");
			// take all the results and put in list
			while (this.result.next()) {
				// get string work with columns not raws
				studentData.add(this.result.getString(2));
				studentData.add(this.result.getString(3));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		//if list is empty user doesn't exist
		if (studentData.size() == 0) {
			throw new IllegalArgumentException("User inesistente");
		} else
			return studentData;
	}
	
	public void nome() {
		try {
			// create statement
			this.statement = this.conn.createStatement();
			// get result of query
			this.result = this.statement.executeQuery("SELECT * FROM Studenti WHERE Studenti.nome = ");
			// take all the results and put in list
			while (this.result.next()) {
				// get string work with columns not raws
				System.out.println(this.result.getString(2) + " " + this.result.getString(3));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
