import java.util.Dictionary;
import java.util.List;

/**
 * Class born to describe the normal student
 * 
 * @author Lorenzo Stacchio
 *
 */
public class Student {
	// name
	private String name;
	//surname
	private String surname;
	//db
	private DataBase db;
	//dictionary for exams
	private Dictionary<String, Integer> Esami;

	/**
	 * Empty Constructor, it is the only constructor for student because for the initialize i will use database functions.
	 * @param db
	 * 	database to recruit information
	 * @param userName
	 * userName of Log	
	 * @param password
	 * password of Log
	 */
	public Student(DataBase db,String userName, String password) {
		//initialize if student exist
		List<String> studentData = db.returnStudentData(userName, password);
		this.name = studentData.get(0);
		this.surname =studentData.get(1);
	}
	
	/**
	 * Simple method that return data of a student
	 * @return student data
	 */
	public String getData() {
		return "Salve "+this.name+" "+ this.surname;
	}
	
	
}