package com.soleren.pythonsamples.utils;

import com.soleren.pythonsamples.model.Item;
import com.soleren.pythonsamples.model.Title;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Класс формируем список из {@link Item} для {@link com.soleren.pythonsamples.adapters.MainAdapter}
 */

public class CategoryFactory {

    private static Map<String, Item> mContentList;
    private static String mCurrentMenuKey;
    private static String mCurrentTitleKey;

    static {
        mContentList = new HashMap<>();
    }

    static void fetchContent(Item item) {
        if (item.getMenu() != null && !mContentList.containsKey(item.getMenu())) {
            generateNewMainMenuItem(item);
        } else if (item.getMenu() != null) {
            fetchItem(item);
        }
    }

    private static void generateNewMainMenuItem(Item item) {
        String key = item.getMenu();
        item.setSubMenuToSubMenuList(item.getSubmenu());
        item.setTitleToTitlesList(new Title(item.getTitle(), item.getContent(), item.getPrint()));
        mContentList.put(key, item);
    }

    private static void fetchItem(Item item) {
        String key = item.getMenu();
        if (!mContentList.get(key).getSubmenu().equals(item.getSubmenu())) {
            mContentList.get(key).setSubmenu(item.getSubmenu());
            mContentList.get(key).setSubMenuToSubMenuList(item.getSubmenu());
        }
        mContentList.get(key).setTitleToTitlesList(new Title(item.getTitle(), item.getContent(), item.getPrint()));
    }

    public static List<String> getMenuList() {
        if (mContentList.size() != 0) {
            return new ArrayList<>(mContentList.keySet());
        }
        return new ArrayList<>();
    }

    public static List<String> getSubMenuList() {
        return mContentList.get(mCurrentMenuKey).getSubMenuTitlesList();
    }

    public static List<String> geTitlesList() {
        ArrayList<String> titles = new ArrayList<>(10);
        for (Title title : mContentList.get(mCurrentMenuKey).getTitlesList()) {
            titles.add(title.getTitle());
        }
        return titles;
    }

    public static Title getCurrentTitleData() {
        for (Title title : mContentList.get(mCurrentMenuKey).getTitlesList()) {
            if (title.getTitle().equals(mCurrentTitleKey)) {
                return title;
            }
        }
        return new Title("", "", "");
    }

    public static void setCurrentKey(String currentKey) {
        CategoryFactory.mCurrentMenuKey = currentKey;
    }

    public static void setCurrentTitleKey(String currentTitleKey) {
        CategoryFactory.mCurrentTitleKey = currentTitleKey;
    }
}
