package com.soleren.pythonsamples.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by laktionov on 25.08.2017.
 * Класс инкапсулирует в себе связть заголовка последнего уровня вложенности и его содержимого
 */

public class Title implements Parcelable {

    private String mTitle;
    private String mTitleContent;
    private String mTitlePrint;

    public Title(String mTitle, String mTitleContent, String mTitlePrint) {
        this.mTitle = mTitle;
        this.mTitleContent = mTitleContent;
        this.mTitlePrint = mTitlePrint;
    }

    public static final Creator<Title> CREATOR = new Creator<Title>() {
        @Override
        public Title createFromParcel(Parcel in) {
            return new Title(in);
        }

        @Override
        public Title[] newArray(int size) {
            return new Title[size];
        }
    };

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public String getTitleContent() {
        return mTitleContent;
    }

    public void setTitleContent(String mTitleContent) {
        this.mTitleContent = mTitleContent;
    }

    public String getmTitlePrint() {
        return mTitlePrint;
    }

    public void setmTitlePrint(String mTitlePrint) {
        this.mTitlePrint = mTitlePrint;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(mTitle);
        parcel.writeString(mTitleContent);
        parcel.writeString(mTitlePrint);
    }

    private Title(Parcel in) {
        mTitle = in.readString();
        mTitleContent = in.readString();
        mTitlePrint = in.readString();
    }
}
