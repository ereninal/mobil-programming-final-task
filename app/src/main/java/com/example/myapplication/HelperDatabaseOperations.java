package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

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
    /****************************User Operations**********************************/
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
        values.clear();
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

    /********************************Propert Operations*****************************************/

    public void InsertPropert(ModelPropert propert){
        DatabaseOpen();
        values.clear();
        values.put("Title",propert.getTitle());
        values.put("Date",propert.getTitle());
        values.put("Type",propert.getTitle());
        values.put("Title",propert.getTitle());
        values.put("RoomCount",propert.getTitle());
        values.put("BuildingAge",propert.getTitle());
        values.put("FloorLocation",propert.getTitle());
        values.put("Heating",propert.getTitle());
        values.put("Country",propert.getTitle());
        values.put("District",propert.getTitle());
        values.put("Address",propert.getTitle());
        values.put("Fee",propert.getTitle());
        values.put("Images",propert.getTitle());
        db.insert("propert",null,values);
        DatabaseClose();
    }
    /*public Cursor GetUserAllPropert(){

    }*/


}
