import com.shellucas.Models.Person;
import com.shellucas.Models.Student;
import com.sleepycat.je.*;

import java.io.*;
import java.util.Objects;

public class Main {

	private static final String dbLocation = "/data/je";
	private static final String dbName = "DemoDatabase";

	public static void main(String[] args) {
		Environment env = createENV();
		Database db = createDB(env);
		
		Student s1 = new Student(0, "Shelby", "Hendrikcx", 24);
		Student studentInserted = (Student) insertPerson(s1, env, db);

		if (studentInserted != null) {
			System.out.printf("Student inserted: \n%s\n", studentInserted);
		}
		
		Student p1 = (Student) getPerson(s1.getId(), env, db);

		if (p1 != null) {
			System.out.println(p1);
		}
	}

	private static Database createDB(Environment env) {
		Database db;
		final DatabaseConfig dbConfig = new DatabaseConfig();
		dbConfig.setTransactional(true);
		dbConfig.setAllowCreate(true);
		dbConfig.setSortedDuplicates(true);
		db = env.openDatabase(null, dbName, dbConfig);
		return db;
	}

	private static Environment createENV() {
		Environment env;
		final File jarFile = new File(Objects.requireNonNull(ClassLoader.getSystemClassLoader().getResource(".")).getPath());
		final File envDir = new File(jarFile.getPath() + dbLocation);
		boolean dirCreated = envDir.mkdirs();

		if (dirCreated) {
			System.out.println("A new location for the environment has been created");
		}

		final EnvironmentConfig envConfig = new EnvironmentConfig();
		envConfig.setTransactional(true);
		envConfig.setAllowCreate(true);
		env = new Environment(envDir, envConfig);

		return env;
	}
	
	private static Person insertPerson(Person person, Environment env, Database db) {
		Transaction transaction = env.beginTransaction(null, null);
		DatabaseEntry key = new DatabaseEntry(String.valueOf(person.getId()).getBytes());
		DatabaseEntry data = null;
		try {
			data = new DatabaseEntry(objectToBytes(person));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if (data == null) {
			return null;
		}
		
		OperationStatus res = db.putNoOverwrite(transaction, key, data);
		
		if (res != OperationStatus.SUCCESS) {
			System.out.println("Error: " + res.toString());
			transaction.abort();
			return null;
		} else {
			try {
				person = (Person) bytesToObject(data.getData());
			} catch (Exception e) {
				e.printStackTrace();
			}
			System.out.println(key + "/" + data);
			transaction.commit();
			return person;
		}
	}
	
	private static Person getPerson(int id, Environment env, Database db) {
		Transaction transaction = env.beginTransaction(null, null);
		DatabaseEntry data = new DatabaseEntry();
		DatabaseEntry key = new DatabaseEntry(String.valueOf(id).getBytes());
		
		OperationStatus res = db.get(transaction, key, data, null);
		if (res != OperationStatus.SUCCESS) {
			System.out.println("Error: " + res.toString());
			return null;
		} else {
			System.out.println(key + "/" + data);
			transaction.commit();
			Person person = null;
			try {
				person = (Person) bytesToObject(data.getData());
			} catch (Exception e) {
				e.printStackTrace();
			}
			return person;
		}
	}
	
	private static byte[] objectToBytes(Object person) throws Exception {
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		ObjectOutputStream oos = new ObjectOutputStream(bos);
		oos.writeObject(person);
		oos.flush();
		return bos.toByteArray();
	}
	
	private static Object bytesToObject(byte[] bytes) throws Exception {
		ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
		Object o;
		try (ObjectInput in = new ObjectInputStream(bis)) {
			o = in.readObject();
		}
		return o;
	}

}
