import com.shellucas.Models.Person;
import com.sleepycat.je.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;


class PersonHandler {
  
  @Nullable
  static Person insertPerson(@NotNull Person person, @NotNull Environment env, @NotNull Database db) {
    Transaction transaction = env.beginTransaction(null, null);
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
  
  @Nullable
  static Person getPerson(int id, @NotNull Environment env, @NotNull Database db) {
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
        person = (Person) Helpers.bytesToObject(data.getData());
      } catch (Exception e) {
        e.printStackTrace();
      }
      return person;
    }
  }
  
}
