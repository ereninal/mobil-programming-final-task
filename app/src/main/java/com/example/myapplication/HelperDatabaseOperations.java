package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;
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
    public List<ModelPropert> GetAllPropertUserId(int userId){
        List<ModelPropert> p = new ArrayList<>();
        ModelPropert propert;
        DatabaseOpen();
        String columns[] ={"Id","Title","Date","Type","RoomCount","BuildingAge","FloorLocation","Heating","Country","District","Adress","Fee","Images","UserId"};
        Cursor cursor = db.query("users",columns,
                null,null,null,null,null);
        cursor.moveToFirst();
        /*String query = "select Id,Title,Fee,Type,Date,Images from propert";
        Cursor cursor = db.rawQuery(query,null);
        cursor.moveToFirst();*/
        while(!cursor.isAfterLast()){
            propert = new ModelPropert();
            propert.setUserId(cursor.getInt(0));
            propert.setTitle(cursor.getString(1));
            propert.setDate( cursor.getString(2));
            propert.setType(cursor.getString(3));
            propert.setRoomCount(cursor.getString(4));
            propert.setBuildingAge(cursor.getString(5));
            propert.setFloorLocation(cursor.getString(6));
            propert.setHeating(cursor.getString(7));
            propert.setCountry(cursor.getString(8));
            propert.setDistrict(cursor.getString(9));
            propert.setAddress(cursor.getString(10));
            propert.setFee(cursor.getString(11));
            propert.setImages(cursor.getBlob(12));
            propert.setRoomCount(cursor.getString(13));
            propert.setUserId(cursor.getInt(14));
            //properts.add(new ModelPropert(id,title,fee,type,date,img));
            p.add(propert);
            cursor.moveToNext();

        }
        DatabaseClose();
        return p;
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
        values.put("Adress",propert.getTitle());
        values.put("Fee",propert.getTitle());
        values.put("Images",propert.getTitle());
        db.insert("propert",null,values);
        DatabaseClose();
    }

    /*public Cursor GetUserAllPropert(int userId){
        DatabaseOpen();
        String query = "select Id,Title,Fee,Type,Date,Images from propert where UserId="+userId;
        return db.rawQuery(query,null);

    }*/
    public byte[] ImageViewToByte (ImageView iimage){
        Bitmap bitmap =((BitmapDrawable)iimage.getDrawable()).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,100,stream);
        byte[] bytes = stream.toByteArray();
        return bytes;
    }

}
