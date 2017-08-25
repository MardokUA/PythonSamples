package com.soleren.pythonsamples.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by den on 2017-05-22.
 * Основная модель приложения
 */

public class Item {

    // порядковйы номер
    private String id;

    private String menu;
    private String submenu;
    private String title;
    private String content;

    // флаг "конец парсинга" для XMLParser
    private String print;

    private List<String> mSubmenuTitles;
    private List<Title> mTitlesList;

    public Item() {
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setMenu(String menu) {
        this.menu = menu;
    }

    public String getMenu() {
        return menu;
    }

    public void setSubmenu(String submenu) {
        this.submenu = submenu;
    }

    public String getSubmenu() {
        return submenu;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContent() {
        if (content == null) {
            return "";
        }
        return content;
    }

    public void setPrint(String print) {
        this.print = print;
    }

    public String getPrint() {
        if (print == null) {
            return "";
        }
        return print;
    }

    /**
     * Возвращает список для адаптера
     *
     * @return список subtitles;
     */
    public List<String> getSubMenuTitlesList() {
        return mSubmenuTitles;
    }

    /**
     * Добавляет текущий subtitle, который устанавливает {@link com.soleren.pythonsamples.utils.XMLParser},
     * к списку всех subtitles, которые относятся к этому айтему
     * Этот метод использует {@link com.soleren.pythonsamples.utils.CategoryFactory}
     *
     * @param subTitle
     */
    public void setSubMenuToSubMenuList(String subTitle) {
        if (mSubmenuTitles == null) {
            mSubmenuTitles = new ArrayList<>(10);
        }
        this.mSubmenuTitles.add(subTitle);
    }

    /**
     * Возвращает список для адаптера
     *
     * @return список titles;
     */
    public List<Title> getTitlesList() {
        return mTitlesList;
    }

    /**
     * Добавляет текущий title, который устанавливает {@link com.soleren.pythonsamples.utils.XMLParser},
     * к списку всех subtitles, которые относятся к этому айтему
     * Этот метод использует {@link com.soleren.pythonsamples.utils.CategoryFactory}
     *
     * @param title
     */
    public void setTitleToTitlesList(Title title) {
        if (mTitlesList == null) {
            mTitlesList = new ArrayList<>(10);
        }
        mTitlesList.add(title);
    }
}
