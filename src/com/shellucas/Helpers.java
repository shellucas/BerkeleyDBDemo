package com.shellucas;

import org.jetbrains.annotations.NotNull;

import java.io.*;

class Helpers {
  
  static byte[] objectToBytes(@NotNull Object person) throws Exception {
    ByteArrayOutputStream bos = new ByteArrayOutputStream();
    ObjectOutputStream oos = new ObjectOutputStream(bos);
    oos.writeObject(person);
    oos.flush();
    return bos.toByteArray();
  }
  
  static Object bytesToObject(byte[] bytes) throws Exception {
    ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
    Object o;
    try (ObjectInput in = new ObjectInputStream(bis)) {
      o = in.readObject();
    }
    return o;
  }
  
}
