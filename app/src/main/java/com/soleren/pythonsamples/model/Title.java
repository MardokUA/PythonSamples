package com.soleren.pythonsamples.model;

/**
 * Created by laktionov on 25.08.2017.
 * Класс инкапсулирует в себе связть заголовка последнего уровня вложенности и его содержимого
 */

public class Title {

    private String mTitle;
    private String mTitleContent;
    private String mTitlePrint;

    public Title(String mTitle, String mTitleContent, String mTitlePrint) {
        this.mTitle = mTitle;
        this.mTitleContent = mTitleContent;
        this.mTitlePrint = mTitlePrint;
    }

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
}
