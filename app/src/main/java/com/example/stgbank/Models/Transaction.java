package com.example.stgbank.Models;

import android.os.Parcel;
import android.os.Parcelable;

public class Transaction implements Parcelable
{
    int id;
    Double amount;
    String status;
    String sname;
    String rname;

    public Transaction() {
    }

    public Transaction(Double amount, String status, String sname, String rname) {
        this.amount = amount;
        this.status = status;
        this.sname = sname;
        this.rname = rname;
    }

    public Transaction(int id, Double amount, String status, String sname, String rname) {
        this.id = id;
        this.amount = amount;
        this.status = status;
        this.sname = sname;
        this.rname = rname;
    }

    public int getId() {
        return id;
    }

    public Double getAmount() {
        return amount;
    }

    public String getStatus() {
        return status;
    }

    public String getSname() {
        return sname;
    }

    public String getRname() {
        return rname;
    }

    protected Transaction(Parcel in) {
        id = in.readInt();
        if (in.readByte() == 0) {
            amount = null;
        } else {
            amount = in.readDouble();
        }
        status = in.readString();
        sname = in.readString();
        rname = in.readString();
    }

    public static final Creator<Transaction> CREATOR = new Creator<Transaction>() {
        @Override
        public Transaction createFromParcel(Parcel in) {
            return new Transaction(in);
        }

        @Override
        public Transaction[] newArray(int size) {
            return new Transaction[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        if (amount == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeDouble(amount);
        }
        parcel.writeString(status);
        parcel.writeString(sname);
        parcel.writeString(rname);
    }
}
