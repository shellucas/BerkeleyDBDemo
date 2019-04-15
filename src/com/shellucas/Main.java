package com.shellucas;


import com.sleepycat.je.Database;
import com.sleepycat.je.DatabaseConfig;
import com.sleepycat.je.DatabaseEntry;
import com.sleepycat.je.DatabaseException;
import com.sleepycat.je.Environment;
import com.sleepycat.je.EnvironmentConfig;
import com.sleepycat.je.OperationStatus;
import com.sleepycat.je.Transaction;

import java.io.File;

public class Main {

    public static void main(String[] args) {
	// write your code here
        Database db = null;
        Environment env = null;
        try {
            final File envDir = new File("data/je");
            envDir.mkdirs();
            final EnvironmentConfig envConfig = new EnvironmentConfig();
            envConfig.setTransactional(true);
            envConfig.setAllowCreate(true);
            env = new Environment(envDir, envConfig);
            final DatabaseConfig dbConfig = new DatabaseConfig();
            dbConfig.setTransactional(true);
            dbConfig.setAllowCreate(true);
            dbConfig.setSortedDuplicates(true);
            db = env.openDatabase(null, "exampledb", dbConfig);
        } catch(Exception e) {
            System.out.println(e);
        }

        if (db == null) {
            return;
        }
        System.out.println("Created db" + db.getDatabaseName());
        Transaction transaction = env.beginTransaction(null, null);
        DatabaseEntry key = new DatabaseEntry("key2".getBytes());
        DatabaseEntry data = new DatabaseEntry("data1".getBytes());
        String result = null;
//        OperationStatus res = db.put(transaction, key, data);
//        if (res != OperationStatus.SUCCESS) {
//            result = "Error: " + res.toString();
//        } else {
//            result = key + "/" + data;
//        }
//        transaction.commit();

        System.out.println("JE " + "did put of: " + result);
        transaction = env.beginTransaction(null, null);
        data = new DatabaseEntry();

        OperationStatus res;
        res = db.get(transaction, key, data, null);
        if (res != OperationStatus.SUCCESS) {
            result = "Error: " + res.toString();
        } else {
            result = key + "/" + data;
        }
        transaction.commit();
        System.out.println("JE " + "did get of: " + result);
    }

}
