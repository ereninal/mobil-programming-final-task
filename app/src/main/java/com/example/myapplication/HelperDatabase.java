package com.example.myapplication;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.AsyncTask;
import android.util.Log;




public class HelperDatabase extends SQLiteOpenHelper {

    public  HelperDatabase(Context c){
        super(c,"HotelDB",null,1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table users(" +
                "Id integer primary key autoincrement, " +
                "Fullname text not null," +
                "Username text not null," +
                "Email text not null,"+
                "Password text not null," +
                "Image blob )");
        db.execSQL("create table propert(" +
                "Id integer primary key autoincrement," +
                "Title text not null," +
                "Date text," +
                "Type text," +
                "RoomCount text," +
                "BuildingAge text," +
                "FloorLocation text," +
                "Heating text," +
                "Country text," +
                "District text," +
                "Adress text," +
                "Fee text," +
                "Images blob," +
                "UserId integer," +
                "foreign key(UserId) references users(Id))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
