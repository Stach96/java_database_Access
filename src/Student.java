import java.util.ArrayList;
import java.util.Dictionary;
import java.util.List;

/**
 * Class born to describe the normal student
 * 
 * @author Lorenzo Stacchio
 *
 */
public class Student {
	// id
	private int id;
	// name
	private String name;
	// surname
	private String surname;
	// db
	private DataBase db;
	// dictionary for exams
	private Dictionary<String, Integer> Esami;

	/**
	 * Empty Constructor, it is the only constructor for student because for the
	 * initialize i will use database functions.
	 * 
	 * @param db
	 *            database to recruit information
	 * @param userName
	 *            userName of Log
	 * @param password
	 *            password of Log
	 */
	public Student(DataBase db, String userName, String password) {
		this.db = db;
		// initialize if student exist
		List<String> studentData = this.db.returnStudentData(userName, password);
		this.id = Integer.parseInt(studentData.get(0));
		this.name = studentData.get(1);
		this.surname = studentData.get(2);
	}

	/**
	 * Simple method that return data of a student
	 * 
	 * @return student data
	 */
	public String getData() {
		return "Salve " + this.name + " " + this.surname + " il tuo id è: " + this.id;
	}

	/**
	 * Method that return student marks
	 * 
	 * @return student marks
	 */
	public String getStudentMarks() {
		// at this point student exist
		List<String> studentMarks = new ArrayList<String>();
		try {
			studentMarks = db.returnStudentMarks(this.id);
		} catch (NullPointerException e) {
			System.out.println("student mark is null");
		}
		// string to return
		String prova = "";
		for (String x : studentMarks) {
			prova += x +"\n";
		}
		return prova;
	}

}