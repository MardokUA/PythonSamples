package com.soleren.pythonsamples.model;

import com.soleren.pythonsamples.data.Const;
import com.soleren.pythonsamples.utils.ContentFactory;
import com.soleren.pythonsamples.utils.XMLContentParser;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    // флаг "конец парсинга" для XMLContentParser
    private String print;

    private List<String> mSubmenuTitles;
    private Map<String, List<Title>> mTitlesMap;

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
     * Добавляет текущий subtitle, который устанавливает {@link XMLContentParser},
     * к списку всех subtitles, которые относятся к этому айтему
     * Этот метод использует {@link ContentFactory}
     *
     * @param subTitle
     */
    public void setSubMenuToSubMenuList(String subTitle) {
        this.mSubmenuTitles.add(subTitle);
    }

    /**
     * Возвращает список для адаптера
     *
     * @return список titles;
     */
    public Map<String, List<Title>> getTitlesMap() {
        return mTitlesMap;
    }

    /**
     * Добавляет текущий title, который устанавливает {@link XMLContentParser},
     * к списку всех subtitles, которые относятся к этому айтему
     * Этот метод использует {@link ContentFactory}
     *
     * @param title       значение
     * @param titleMapKey ключ, равный submenu
     */
    public void setTitleToTitlesMap(String titleMapKey, Title title) {
        mTitlesMap.get(titleMapKey).add(title);

    }

    public List<String> getTitlesList(String key) {
        List<String> categoryTitles = new ArrayList<>(Const.MAX_TITLE_CAPACITY);
        for (Title title : mTitlesMap.get(key)) {
            categoryTitles.add(title.getTitle());
        }
        return categoryTitles;
    }

    public void initEmptyCollections() {
        mSubmenuTitles = new ArrayList<>(Const.MAX_SUBMENU_CAPACITY);
        mTitlesMap = new HashMap<>();
    }
}
