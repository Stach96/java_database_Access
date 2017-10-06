import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DataBase {
	private Connection conn;
	private Statement statement;
	private ResultSet result;
	private String dbPath;

	public DataBase(String dbPath) {
		this.dbPath = dbPath;
		this.statement = null;
		this.conn=null;
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
	public List<String> getStudentNameSurname() {
		//declare and initialize list
		List<String> listaStudenti = new ArrayList<String>();
		try {
			// create statement
			this.statement = this.conn.createStatement();
			// get result of query
			this.result = this.statement.executeQuery("SELECT Studenti.nome,Studenti.cognome FROM Studenti Order by Studenti.cognome");
			//take all the results and put in list
			while (this.result.next()) {
				//get string work with columns not raws
				listaStudenti.add(this.result.getString(1)+" "+ this.result.getString(2));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listaStudenti;
	}
	
}
