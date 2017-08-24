package com.soleren.pythonsamples.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by den on 2017-05-22.
 */

public class Item implements Parcelable{
//    private String id;
    private String title;
//    private String title_ru;
//    private String title_en;
    private String content;
//    private String content_ru;
//    private String content_en;
    private String print;

    public Item() {
    }

    public Item(Parcel in) {
//        id = in.readString();
        title = in.readString();
//        title_ru = in.readString();
//        title_en = in.readString();
        content = in.readString();
//        content_ru = in.readString();
//        content_en = in.readString();
        print = in.readString();
    }

    public static final Creator<Item> CREATOR = new Creator<Item>() {

        @Override
        public Item createFromParcel(Parcel in) {
            return new Item(in);
        }

        @Override
        public Item[] newArray(int size) {
            return new Item[size];
        }

    };

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setPrint(String print) {
        this.print = print;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getPrint() {
        return print;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(content);
        dest.writeString(print);
    }
}
