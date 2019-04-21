package com.shellucas;

import com.sleepycat.je.*;
import com.sleepycat.je.rep.CommitPointConsistencyPolicy;
import com.sleepycat.je.rep.utilint.RepUtils;

import java.io.File;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

class DatabaseHandler {
  
  static Database createDB(Environment env, String dbName) {
    Database db;
    final DatabaseConfig dbConfig = new DatabaseConfig();
    dbConfig.setTransactional(true);
    dbConfig.setAllowCreate(true);
    db = env.openDatabase(null, dbName, dbConfig);
    return db;
  }
  
  static Environment createENV(String dbLocation) {
    Environment env;
    Path path = null;
    try {
      path = Paths.get(Main.class.getProtectionDomain().getCodeSource().getLocation().toURI()).getParent();
    } catch (URISyntaxException e) {
      e.printStackTrace();
    }
    assert path != null;
    final File jarFile = path.toFile();
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
    CommitToken token =  new CommitToken(UUID.randomUUID(), 1);
    ReplicaConsistencyPolicy policy = new CommitPointConsistencyPolicy(token, 2, TimeUnit.MINUTES);
    
    transactionConfig.setDurability(Durability.COMMIT_SYNC);
    transactionConfig.setConsistencyPolicy(policy);
    transactionConfig.setLocalWrite(true);
    transactionConfig.setReadCommitted(true);
    return env.beginTransaction(null, transactionConfig);
  }
  
}
