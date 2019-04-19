import com.sleepycat.je.*;

import java.io.File;
import java.util.Objects;

class DatabaseHandler {
  
  static Database createDB(Environment env, String dbName) {
    Database db;
    final DatabaseConfig dbConfig = new DatabaseConfig();
    dbConfig.setTransactional(true);
    dbConfig.setAllowCreate(true);
    dbConfig.setSortedDuplicates(true);
    db = env.openDatabase(null, dbName, dbConfig);
    return db;
  }
  
  static Environment createENV(String dbLocation) {
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
  
  static Transaction createTransaction(Environment env) {
    TransactionConfig transactionConfig = new TransactionConfig();
    transactionConfig.setDurability(Durability.COMMIT_SYNC);
    return env.beginTransaction(null, transactionConfig);
  }
  
}
