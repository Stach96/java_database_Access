
public class UniversitySystem {

	public static void main(String[] argS){
		DataBase db = new DataBase("src/Java_school.accdb");
		//initialize conn
		db.initializeConn();
		Student student = new Student(db, "lorenzo.stacchio","password");
		System.out.println(student.getData());
		System.out.println("Esami superati:\n"+student.getStudentMarks());
	}
}