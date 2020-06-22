package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
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

        db.insert("users",null,values);
        DatabaseClose();
        //TODO: Yeni Kullanıcı oluşturma işlemi yapılacak
    }
    public List<ModelUsers> GetUserAll(){//tüm kullanıcı veri tabanını listeler
        List<ModelUsers> u = new ArrayList<>();

        DatabaseOpen();
        String columns[] ={"Id","Fullname","Username","Password","Email"};
        Cursor cursor = db.query("users",columns,
                null,null,null,null,null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            users = new ModelUsers();
            users.setId(cursor.getInt(0));
            users.setFullName(cursor.getString(1));
            users.setUsername(cursor.getString(2));
            users.setEmail(cursor.getString(3));
            users.setPassword(cursor.getString(4));
            u.add(users);
            cursor.moveToNext();
        }
        DatabaseClose();
        return u;
    }
    public List<ModelUsers> GetUser(String username){
        List<ModelUsers> u = new ArrayList<>();
        DatabaseOpen();
        String columns[] ={"Id","Fullname","Username","Password","Email"};
        Cursor cursor = db.query("users",columns,
                null,null,null,null,null);
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            //cursor.getString(2).equalsIgnoreCase(username)
            if(cursor.getString(2).equals(username)){
                users = new ModelUsers();
                users.setId(cursor.getInt(0));
                users.setFullName(cursor.getString(1));
                users.setUsername(cursor.getString(2));
                users.setPassword(cursor.getString(3));
                users.setEmail(cursor.getString(4));

                u.add(users);
                return u;
            }
            cursor.moveToNext();
        }
        return null;
    }

}
