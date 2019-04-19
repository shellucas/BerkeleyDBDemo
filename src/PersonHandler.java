import com.shellucas.Models.Person;
import com.sleepycat.je.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;


class PersonHandler implements Handler<Person> {
  
  @Nullable
  public Person insert(@NotNull Person person, @NotNull Environment env, @NotNull Database db) {
    Transaction transaction = DatabaseHandler.createTransaction(env);
    DatabaseEntry key = new DatabaseEntry(String.valueOf(person.getId()).getBytes());
    DatabaseEntry data = null;
    try {
      data = new DatabaseEntry(Helpers.objectToBytes(person));
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
        person = (Person) Helpers.bytesToObject(data.getData());
      } catch (Exception e) {
        e.printStackTrace();
      }
      System.out.println(key + "/" + data);
      transaction.commit();
      return person;
    }
  }
  
  @Override
  public Person put(@NotNull Person person, @NotNull Environment env, @NotNull Database db) {
    if (get(person.getId(), env, db) == null) {
      System.out.println("No entry found to update/replace!");
      return null;
    }
  
    Transaction transaction = DatabaseHandler.createTransaction(env);
    DatabaseEntry key = new DatabaseEntry(String.valueOf(person.getId()).getBytes());
    DatabaseEntry data = null;
    try {
      data = new DatabaseEntry(Helpers.objectToBytes(person));
    } catch (Exception e) {
      e.printStackTrace();
    }
  
    if (data == null) {
      return null;
    }
  
    OperationStatus res = db.put(transaction, key, data);
  
    if (res != OperationStatus.SUCCESS) {
      System.out.println("Error: " + res.toString());
      transaction.abort();
      return null;
    } else {
      try {
        person = (Person) Helpers.bytesToObject(data.getData());
      } catch (Exception e) {
        e.printStackTrace();
      }
      System.out.println(key + "/" + data);
      transaction.commit();
      return person;
    }
  }
  
  @Override
  public boolean delete(int id, @NotNull Environment env, @NotNull Database db) {
    Transaction transaction = DatabaseHandler.createTransaction(env);
    DatabaseEntry key = new DatabaseEntry(String.valueOf(id).getBytes());
  
    OperationStatus res = db.delete(transaction, key);
  
    if (res != OperationStatus.SUCCESS) {
      System.out.println("Error: " + res.toString());
      return false;
    } else {
      transaction.commit();
      return true;
    }
  }
  
  @Nullable
  public Person get(int id, @NotNull Environment env, @NotNull Database db) {
    Transaction transaction = DatabaseHandler.createTransaction(env);
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
        person = (Person) Helpers.bytesToObject(data.getData());
      } catch (Exception e) {
        e.printStackTrace();
      }
      return person;
    }
  }
  
}
