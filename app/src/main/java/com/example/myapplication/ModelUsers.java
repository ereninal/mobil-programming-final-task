package com.example.myapplication;

import android.os.Parcel;
import android.os.Parcelable;

public class ModelUsers implements Parcelable {

    private int Id;
    private String FullName;
    private String Username;
    private String Email;
    private String Password;
    private String Session;


    protected ModelUsers(Parcel in) {
    }

    public static final Creator<ModelUsers> CREATOR = new Creator<ModelUsers>() {
        @Override
        public ModelUsers createFromParcel(Parcel in) {
            return new ModelUsers(in);
        }

        @Override
        public ModelUsers[] newArray(int size) {
            return new ModelUsers[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }
}
