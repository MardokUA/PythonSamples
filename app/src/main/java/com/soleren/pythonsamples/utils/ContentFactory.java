package com.soleren.pythonsamples.utils;

import com.soleren.pythonsamples.data.Const;
import com.soleren.pythonsamples.model.Item;
import com.soleren.pythonsamples.model.Title;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Класс формируем список из {@link Item} для {@link com.soleren.pythonsamples.adapters.MainAdapter}
 */

public class ContentFactory {

    private static Map<String, Item> mContentList;
    private static List<Title> mTitleList;

    private static String mCurrentMenuKey;
    private static String mCurrentSubMenuKey;
    private static String mCurrentTitleKey;

    static {
        mContentList = new HashMap<>();
        mTitleList = new ArrayList<>(500);
    }

    static void fetchContent(Item item) {
        if (item != null) {
            String itemKey = item.getMenu() == null ? "" : item.getMenu();
            if (!itemKey.isEmpty() && mContentList.containsKey(itemKey)) {
                fetchItem(item, itemKey);
            } else if (!itemKey.isEmpty()) {
                generateNewMainMenuItem(item, itemKey);
            }
            fetchTitleToItem(item, itemKey);
        }
    }

    private static void generateNewMainMenuItem(Item item, String itemKey) {
        item.initEmptyCollections();
        item.setSubMenuToSubMenuList(item.getSubmenu());
        mContentList.put(itemKey, item);
    }

    private static void fetchItem(Item item, String itemKey) {
        if (!mContentList.get(itemKey).getSubmenu().equals(item.getSubmenu())) {
            mContentList.get(itemKey).setSubmenu(item.getSubmenu());
            mContentList.get(itemKey).setSubMenuToSubMenuList(item.getSubmenu());
        }
    }

    private static void fetchTitleToItem(Item item, String itemKey) {
        String titleMapKey = item.getSubmenu();
        if (item.getTitle() != null) {
            Title title = new Title(item.getTitle(), item.getContent(), item.getPrint());
            Item itemInMap = mContentList.get(itemKey);
            if (itemInMap != null) {
                if (itemInMap.getTitlesMap().containsKey(titleMapKey)) {
                    mContentList.get(item.getMenu()).setTitleToTitlesMap(titleMapKey, title);
                } else {
                    List<Title> newTitleList = new ArrayList<>(Const.MAX_TITLE_CAPACITY);
                    newTitleList.add(title);
                    itemInMap.getTitlesMap().put(titleMapKey, newTitleList);
                }
            }
            mTitleList.add(title);
        }
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
        return mContentList.get(mCurrentMenuKey).getTitlesList(mCurrentSubMenuKey);
    }

    public static Title getCurrentTitleData() {
        List<Title> currentTitlesList = mContentList.get(mCurrentMenuKey).getTitlesMap().get(mCurrentSubMenuKey);
        for (Title title : currentTitlesList) {
            if (title.getTitle().equals(mCurrentTitleKey)) {
                return title;
            }
        }
        return new Title("ERROR", "Data doesn't found", "");
    }

    public static void setCurrentMenuKey(String currentKey) {
        ContentFactory.mCurrentMenuKey = currentKey;
    }

    public static void setCurrentSubMenuKey(String mCurrentSubMenuKey) {
        ContentFactory.mCurrentSubMenuKey = mCurrentSubMenuKey;
    }

    public static boolean isSubMenuNeedExtendViewType() {
        return mCurrentSubMenuKey.equals(Const.NOTES) || mCurrentSubMenuKey.equals(Const.TIPS) || mCurrentSubMenuKey.equals(Const.DEFINITIONS);
    }

    public static void setCurrentTitleKey(String mCurrentTitleKey) {
        ContentFactory.mCurrentTitleKey = mCurrentTitleKey;
    }

    public static String getCurrentSubMenuKey() {
        return mCurrentSubMenuKey;
    }

    public static List<Title> getTitleList() {
        return mTitleList;
    }
}
