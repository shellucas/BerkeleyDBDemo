import com.shellucas.Models.Student;
import com.sleepycat.je.*;

public class Main {

	private static final String dbLocation = "/data/je";
	private static final String dbName = "DemoDatabase";

	public static void main(String[] args) {
		Environment env = DatabaseHandler.createENV(dbLocation);
		Database db = DatabaseHandler.createDB(env, dbName);
		
		Student s1 = new Student(0, "Shelby", "Hendrikcx", 24);
		Student studentInserted = (Student) PersonHandler.insertPerson(s1, env, db);

		if (studentInserted != null) {
			System.out.printf("Student inserted: \n%s\n", studentInserted);
		}
		
		Student p1 = (Student) PersonHandler.getPerson(s1.getId(), env, db);

		if (p1 != null) {
			System.out.println(p1);
		}
	}
}
