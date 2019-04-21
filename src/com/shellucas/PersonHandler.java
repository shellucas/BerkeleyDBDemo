package com.shellucas;

import com.shellucas.models.Person;
import com.sleepycat.bind.EntryBinding;
import com.sleepycat.bind.serial.SerialBinding;
import com.sleepycat.bind.serial.StoredClassCatalog;
import com.sleepycat.bind.tuple.IntegerBinding;
import com.sleepycat.je.*;
import com.sleepycat.persist.EntityStore;
import com.sleepycat.persist.StoreConfig;
import com.sleepycat.persist.impl.Store;
import com.sun.xml.internal.ws.api.databinding.Databinding;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;


public class PersonHandler implements Handler<Person> {
  
  @Nullable
  public Person insert(@NotNull Person person, @NotNull Environment env, @NotNull Database db) {
    Transaction transaction = DatabaseHandler.createTransaction(env);
    DatabaseEntry key = new DatabaseEntry();
    IntegerBinding.intToEntry(person.getId(), key);
    
    DatabaseEntry data = new DatabaseEntry();
    EntryBinding<Person> dataBinding = bind(db, person, data);
    
    OperationStatus res = db.putNoOverwrite(transaction, key, data);
    
    if (res != OperationStatus.SUCCESS) {
      System.out.println("Error: " + res.toString());
      transaction.abort();
      return null;
    } else {
      try {
        person = dataBinding.entryToObject(data);
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
    DatabaseEntry key = new DatabaseEntry();
    IntegerBinding.intToEntry(person.getId(), key);
    DatabaseEntry data = new DatabaseEntry();
  
    EntryBinding<Person> dataBinding = bind(db, person, data);
    
    OperationStatus res = db.put(transaction, key, data);
  
    if (res != OperationStatus.SUCCESS) {
      System.out.println("Error: " + res.toString());
      transaction.abort();
      return null;
    } else {
      try {
        person = dataBinding.entryToObject(data);
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
    DatabaseEntry key = new DatabaseEntry();
    IntegerBinding.intToEntry(id, key);
  
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
    DatabaseEntry key = new DatabaseEntry();
    IntegerBinding.intToEntry(id, key);
  
    StoredClassCatalog catalog = new StoredClassCatalog(db);
    EntryBinding<Person> dataBinding = new SerialBinding<>(catalog, Person.class);
    
    OperationStatus res = db.get(transaction, key, data, null);
    if (res != OperationStatus.SUCCESS) {
      System.out.println("Error: " + res.toString());
      return null;
    } else {
      System.out.println(key + "/" + data);
      transaction.commit();
      Person person = null;
      try {
        person = dataBinding.entryToObject(data);
      } catch (Exception e) {
        e.printStackTrace();
      }
      return person;
    }
  }
  
  public EntryBinding<Person> bind(Database db, Person person, DatabaseEntry data) {
    StoredClassCatalog catalog = new StoredClassCatalog(db);
    EntryBinding<Person> dataBinding = new SerialBinding<>(catalog, Person.class);
    dataBinding.objectToEntry(person, data);
    return dataBinding;
  }
  
}
