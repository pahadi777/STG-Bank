package com.example.stgbank.Models;

import android.os.Parcel;
import android.os.Parcelable;

public class User implements Parcelable
{
    private String name;
    private String phone;
    private String mail;
    private Double balance;
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User(String name, String phone, String mail, Double balance , int id) {
        this.name = name;
        this.phone = phone;
        this.mail = mail;
        this.balance = balance;
        this.id = id;
    }

    protected User(Parcel in) {
        name = in.readString();
        phone = in.readString();
        mail = in.readString();
        if (in.readByte() == 0) {
            balance = null;
        } else {
            balance = in.readDouble();
        }
        id = in.readInt();
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getMail() {
        return mail;
    }

    public Double getBalance() {
        return balance;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(phone);
        parcel.writeString(mail);
        if (balance == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeDouble(balance);
        }
        parcel.writeInt(id);
    }
}
