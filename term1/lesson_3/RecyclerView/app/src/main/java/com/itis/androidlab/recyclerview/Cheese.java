package com.itis.androidlab.recyclerview;

import android.os.Parcel;
import android.os.Parcelable;

public class Cheese implements Parcelable {

    private int mImage;
    private String mTitle;

    public Cheese(int image, String title) {
        mImage = image;
        mTitle = title;
    }

    protected Cheese(Parcel in) {
        mImage = in.readInt();
        mTitle = in.readString();
    }

    public static final Creator<Cheese> CREATOR = new Creator<Cheese>() {
        @Override
        public Cheese createFromParcel(Parcel in) {
            return new Cheese(in);
        }

        @Override
        public Cheese[] newArray(int size) {
            return new Cheese[size];
        }
    };

    public int getImage() {
        return mImage;
    }

    public void setImage(int image) {
        mImage = image;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(mImage);
        dest.writeString(mTitle);
    }
}
