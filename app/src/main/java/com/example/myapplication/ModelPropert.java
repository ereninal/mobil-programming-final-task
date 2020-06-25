package com.example.myapplication;

import android.os.Parcel;
import android.os.Parcelable;

public class ModelPropert implements Parcelable {

    private int Id;
    private String Title;
    private String Date;
    private String Type;
    private String RoomCount;
    private String BuildingAge;
    private String FloorLocation;
    private String Heating;
    private String Country;
    private String District;
    private String Address;
    private String Fee;
    private byte[] Images;
    private int UserId;


    protected ModelPropert(Parcel in) {
        Id = in.readInt();
        Title = in.readString();
        Type = in.readString();
        RoomCount = in.readString();
        BuildingAge = in.readString();
        FloorLocation = in.readString();
        Heating = in.readString();
        Country = in.readString();
        District = in.readString();
        Address = in.readString();
        Fee = in.readString();
        UserId = in.readInt();
        Images = new byte[in.readInt()];
        in.readByteArray(Images);
    }
    public ModelPropert(int id,String title,String fee,String type,String date,byte[] img){
        Id=id;
        Title=title;
        Fee = fee;
        Type = type;
        Images = img;
        Date=date;

    }
    public ModelPropert(){

    }

    public static final Creator<ModelPropert> CREATOR = new Creator<ModelPropert>() {
        @Override
        public ModelPropert createFromParcel(Parcel in) {
            return new ModelPropert(in);
        }

        @Override
        public ModelPropert[] newArray(int size) {
            return new ModelPropert[size];
        }
    };

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public String getRoomCount() {
        return RoomCount;
    }

    public void setRoomCount(String roomCount) {
        RoomCount = roomCount;
    }

    public String getBuildingAge() {
        return BuildingAge;
    }

    public void setBuildingAge(String buildingAge) {
        BuildingAge = buildingAge;
    }

    public String getFloorLocation() {
        return FloorLocation;
    }

    public void setFloorLocation(String floorLocation) {
        FloorLocation = floorLocation;
    }

    public String getHeating() {
        return Heating;
    }

    public void setHeating(String heating) {
        Heating = heating;
    }

    public String getCountry() {
        return Country;
    }

    public void setCountry(String country) {
        Country = country;
    }

    public String getDistrict() {
        return District;
    }

    public void setDistrict(String district) {
        District = district;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getFee() {
        return Fee;
    }

    public void setFee(String fee) {
        Fee = fee;
    }

    public byte[] getImages() {
        return Images;
    }

    public void setImages(byte[] images) {
        Images = images;
    }

    public int getUserId() {
        return UserId;
    }

    public void setUserId(int userId) {
        UserId = userId;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(Id);
        dest.writeString(Title);
        dest.writeString(Type);
        dest.writeString(RoomCount);
        dest.writeString(BuildingAge);
        dest.writeString(FloorLocation);
        dest.writeString(Heating);
        dest.writeString(Country);
        dest.writeString(District);
        dest.writeString(Address);
        dest.writeString(Fee);
        dest.writeInt(UserId);
    }
}
