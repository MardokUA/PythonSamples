package com.soleren.pythonsamples.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by den on 2017-05-22.
 */
//implements Parcelable
public class Item {
    private String id;
    private String menu;
    private String submenu;
    private String title;
    private String content;
    private String print;

    public Item() {
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setMenu(String menu) {
        this.menu = menu;
    }

    public void setSubmenu(String submenu) {
        this.submenu = submenu;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setPrint(String print) {
        this.print = print;
    }


    public String getId() {
        return id;
    }

    public String getMenu() {
        return menu;
    }

    public String getSubmenu() {
        return submenu;
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

}
