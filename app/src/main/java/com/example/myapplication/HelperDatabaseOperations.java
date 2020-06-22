package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.util.List;

public class HelperDatabaseOperations {
    private static SQLiteDatabase db;
    private static HelperDatabase helperDatabase;
    private static ModelUsers users;
    private static ContentValues values = new ContentValues();
    public HelperDatabaseOperations(Context c){
        helperDatabase = new HelperDatabase(c);
    }
    private static void DatabaseOpen(){
        db = helperDatabase.getWritableDatabase();
    }
    private static void DatabaseClose(){
        helperDatabase.close();
    }
    public void FirstUserInsert(){//bir sefer kullanıcak
        DatabaseOpen();
        users = new ModelUsers();
        users.setUsername("ereninal");
        users.setFullName("Eren İNAL");
        users.setEmail("ereninal10@gmail.com");
        users.setPassword("123456");
        users.setSession("False");
        users.setUsers(users);
        values.put("Fullname",users.getFullName());
        values.put("Username", users.getUsername());
        values.put("Email", users.getEmail());
        values.put("Password", users.getPassword());
        values.put("Session", users.getSession());
        db.insert("users",null,values);
        DatabaseClose();

    }
    public void InsertUser(ModelUsers modelUsers){
        DatabaseOpen();
        users.setUsers(modelUsers);
        values.put("Fullname",modelUsers.getFullName());
        values.put("Username", modelUsers.getUsername());
        values.put("Email", modelUsers.getEmail());
        values.put("Password", modelUsers.getPassword());
        values.put("Session", modelUsers.getSession());
        db.insert("users",null,values);
        DatabaseClose();
        //TODO: Yeni Kullanıcı oluşturma işlemi yapılacak
    }
    public List<ModelUsers> GetUserAll(){
        //TODO: Tüm kullanıcılar Listelenecek
        return null;
    }
    public List<ModelUsers> GetUser(String username){
        //TODO: gelen kullanıcı adı tüm kullanıcarda aranacak varsa list dönecek yoksa null
        return null;
    }

}
