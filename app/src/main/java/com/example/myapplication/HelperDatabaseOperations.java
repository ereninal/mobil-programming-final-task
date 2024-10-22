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

    public void InsertUser(ModelUsers modelUsers){
        DatabaseOpen();
        users = new ModelUsers();
        users.setUsers(modelUsers);
        values.clear();
        values.put("Fullname",modelUsers.getFullName());
        values.put("Username", modelUsers.getUsername());
        values.put("Email", modelUsers.getEmail());
        values.put("Password", modelUsers.getPassword());

        db.insert("users",null,values);
        DatabaseClose();

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
        String columns[] ={"Id","Fullname","Username","Password","Email","Image"};
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
                users.setImage(cursor.getBlob(5));
                u.add(users);
                return u;
            }
            cursor.moveToNext();
        }
        return null;
    }
    public boolean GetUserCheck(String username){
        List<ModelUsers> u = new ArrayList<>();
        DatabaseOpen();
        String columns[] ={"Id","Fullname","Username","Password","Email"};
        Cursor cursor = db.query("users",columns,
                null,null,null,null,null);
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            //cursor.getString(2).equalsIgnoreCase(username)
            if(cursor.getString(2).equals(username)){
                return true;
            }
            cursor.moveToNext();
        }
        return false;
    }
    public void UserUpdate(int id,String name,String uname,String mail,String pass, byte[] img){
        DatabaseOpen();
        String query ="update users set Password='"+pass+"',Fullname='"+name+"',Username='"+uname+"',Email='"+mail+"',Image='"+img+"' where Id='"+id+"'";
        try{
            //db.update("users",values,"Username="+username,null);
            db.execSQL(query);
        }catch (Exception e){
            e.printStackTrace();
        }
        DatabaseClose();
    }
    public void UserPasswordUptade(String username,String password){
        DatabaseOpen();
        //Log.d("d",password);
        String query ="update users set Password='"+password+"' where Username='"+username+"'";
        try{
            db.execSQL(query);
        }catch (Exception e){
            e.printStackTrace();
        }
        DatabaseClose();



    }


    /********************************Propert Operations*****************************************/

    public void InsertPropert(ModelPropert propert,int UserId){
        DatabaseOpen();
        values.clear();
        values.put("Title",propert.getTitle());
        values.put("Date",propert.getDate());
        values.put("Type",propert.getType());
        values.put("RoomCount",propert.getRoomCount());
        values.put("BuildingAge",propert.getBuildingAge());
        values.put("FloorLocation",propert.getFloorLocation());
        values.put("Heating",propert.getHeating());
        values.put("Country",propert.getCountry());
        values.put("District",propert.getDistrict());
        values.put("Adress",propert.getAddress());
        values.put("Fee",propert.getFee());
        values.put("Images",propert.getImages());
        values.put("UserId",UserId);
        db.insert("propert",null,values);
        DatabaseClose();
    }
    public List<ModelPropert> GetAllPropertUserId(int userId) {
        List<ModelPropert> p = new ArrayList<>();
        ModelPropert propert;
        DatabaseOpen();
        String columns[] = {"Id", "Title", "Date", "Type", "RoomCount", "BuildingAge",
                "FloorLocation", "Heating", "Country", "District", "Adress",
                "Fee", "Images", "UserId"};
        Cursor cursor = db.query("propert", columns,
                null, null, null, null, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            if(cursor.getString(13).equals(userId)){
                propert = new ModelPropert();
                propert.setUserId(cursor.getInt(0));
                propert.setTitle(cursor.getString(1));
                propert.setDate(cursor.getString(2));
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
                propert.setUserId(cursor.getInt(13));
                //properts.add(new ModelPropert(id,title,fee,type,date,img));
                p.add(propert);
                cursor.moveToNext();
            }
        }
        DatabaseClose();
        return p;
    }
    public void DeletePropert(int userId){
        DatabaseOpen();
        String id = String.valueOf(userId);
        String query ="delete from propert where UserId='"+id+"'";
        try{
            db.execSQL(query);
        }catch (Exception e){
            e.printStackTrace();
        }
        DatabaseClose();

    }
    public List<ModelPropert> GetAllPropert(){
        List<ModelPropert> p = new ArrayList<>();
        ModelPropert propert;
        DatabaseOpen();
        String columns[] = {"Id", "Title", "Date", "Type", "RoomCount", "BuildingAge",
                "FloorLocation", "Heating", "Country", "District", "Adress", "Fee",
                "Images", "UserId"};
        Cursor cursor = db.query("propert", columns,
                null, null, null, null, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            propert = new ModelPropert();
            propert.setUserId(cursor.getInt(0));
            propert.setTitle(cursor.getString(1));
            propert.setDate(cursor.getString(2));
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
            propert.setUserId(cursor.getInt(13));
            //properts.add(new ModelPropert(id,title,fee,type,date,img));
            p.add(propert);
            cursor.moveToNext();
        }
        DatabaseClose();
        return p;
    }
    public byte[] ImageViewToByte (ImageView image){
        Bitmap bitmap =((BitmapDrawable)image.getDrawable()).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,stream);
        byte[] bytes = stream.toByteArray();
        return bytes;
    }

}
