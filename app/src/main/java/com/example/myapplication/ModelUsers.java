package com.example.myapplication;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class ModelUsers implements Parcelable {

    private int Id;
    private String FullName;
    private String Username;
    private String Email;
    private String Password;
    private String Session;
    private byte[] Image;

    protected ModelUsers(Parcel in) {
        Id = in.readInt();
        FullName = in.readString();
        Username = in.readString();
        Email = in.readString();
        Password = in.readString();
        Session = in.readString();
        Image = in.createByteArray();
        Users = in.createTypedArrayList(ModelUsers.CREATOR);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(Id);
        dest.writeString(FullName);
        dest.writeString(Username);
        dest.writeString(Email);
        dest.writeString(Password);
        dest.writeString(Session);
        dest.writeByteArray(Image);
        dest.writeTypedList(Users);
    }

    @Override
    public int describeContents() {
        return 0;
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

    public byte[] getImage() {
        return Image;
    }

    public void setImage(byte[] image) {
        Image = image;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getFullName() {
        return FullName;
    }

    public void setFullName(String fullName) {
        FullName = fullName;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getSession() {
        return Session;
    }

    public void setSession(String session) {
        Session = session;
    }

    public List<ModelUsers> getUsers() {
        return Users;
    }

    public void setUsers(ModelUsers users) {
        Users.clear();
        Users.add(users);
    }

    public List<ModelUsers> Users = new ArrayList<>();

    public ModelUsers() {

    }
}



