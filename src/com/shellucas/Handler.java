import com.shellucas.models.Person;
import com.sleepycat.je.Database;
import com.sleepycat.je.Environment;
import org.jetbrains.annotations.NotNull;

public interface Handler<T> {
  
  T get(int id, @NotNull Environment env, @NotNull Database db);
  T insert(@NotNull Person person, @NotNull Environment env, @NotNull Database db);
  T put(@NotNull Person person, @NotNull Environment env, @NotNull Database db);
  boolean delete(int id, @NotNull Environment env, @NotNull Database db);
  
}
