package com.example.myapplication;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class ModelUsers implements Parcelable {

    private int Id;
    private String FullName;
    private String Username;
    private String Email;
    private String Password;
    private String Session;
    public List<ModelUsers> Users;

    public ModelUsers(){

    }
    protected ModelUsers(Parcel in) {
        Id = in.readInt();
        FullName = in.readString();
        Username = in.readString();
        Email = in.readString();
        Password = in.readString();
        Session = in.readString();
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
        dest.writeInt(Id);
        dest.writeString(FullName);
        dest.writeString(Username);
        dest.writeString(Email);
        dest.writeString(Password);
        dest.writeString(Session);
    }
}
