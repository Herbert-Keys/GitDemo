package com.herbert.server;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Herbert on 2019/2/16.
 */

public class Book implements Parcelable{
    private String name;
    private String price;

    public Book(){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    protected Book(Parcel in) {
        name = in.readString();
        price = in.readString();
    }

    public static final Creator<Book> CREATOR = new Creator<Book>() {
        @Override
        public Book createFromParcel(Parcel in) {
            return new Book(in);
        }

        @Override
        public Book[] newArray(int size) {
            return new Book[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(price);
    }
}
