import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Public class Database which implements all methods to get information from
 * database.
 * 
 * @author Lorenzo Stacchio
 *
 */
public class DataBase {
	// connection
	private Connection conn;
	// statement
	private Statement statement;
	// resul set
	private ResultSet result;
	// dbpath
	private String dbPath;
	// boolean that tell if user is logged
	private boolean logged;

	/**
	 * Constructor that give a regulare db path to initialize connection.
	 * 
	 * @param dbPath
	 *            path of database
	 */
	public DataBase(String dbPath) {
		this.dbPath = dbPath;
		this.statement = null;
		this.conn = null;
		this.result = null;
		this.logged = false;
	}

	/**
	 * Public method to initialize connection
	 */
	public void initializeConn() {
		try {
			// inizialize conn
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
			this.result = this.statement.executeQuery("SELECT * FROM Studenti\n" + "WHERE Studenti.userName ='"
					+ userName + "' and Studenti.password='" + password + "'");
			// take all the results and put in list
			while (this.result.next()) {
				// get string work with columns not raws
				studentData.add(this.result.getString(1));
				studentData.add(this.result.getString(2));
				studentData.add(this.result.getString(3));
			}
		} catch (SQLException e) {
			// print exception
			e.printStackTrace();
		}
		// if list is empty user doesn't exist
		if (studentData.size() == 0) {
			throw new IllegalArgumentException("User inesistente");
		} else {
			// if i'm here, log action was succesfull
			this.logged = true;
			return studentData;
		}
	}

	/**
	 * Method that return students marks
	 * 
	 * @return list of student's marks
	 */
	public List<String> returnStudentMarks(int idStudente) {
		//control if user is logged
		if(this.logged==false) {
			throw new IllegalArgumentException("Utente non loggato!");
		}
		// declare and initialize list
		List<String> studentMarks = new ArrayList<String>();
		try {
			// create statement
			//this.statement = this.conn.createStatement();
			// get result of query
			this.result = this.statement.executeQuery("SELECT "
					+ "Materia.nome_materia, Materia.Cfu, EsamePassato.dataEsame, EsamePassato.Voto "
					+ "FROM Studenti, Materia, EsamePassato "
					+ "WHERE EsamePassato.id_studente=" + idStudente +" and EsamePassato.id_studente=Studenti.id and "
							+ "Materia.id_Materia=EsamePassato.id_materia");
			// take all the results and put in list
			while (this.result.next()) {
				// get string work with columns not raws
				studentMarks.add(this.result.getString(1));
				studentMarks.add(this.result.getString(2));
				studentMarks.add(this.result.getString(3));
				studentMarks.add(this.result.getString(4));

			}
		} catch (SQLException e) {
			// print exception
			e.printStackTrace();
		}
		// if list is empty user doesn't exist
		if (studentMarks.size() == 0) {
			throw new IllegalArgumentException("Lo user non ha nessun voto");
		} else {
			return studentMarks;
		}
	}

}
