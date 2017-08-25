package com.soleren.pythonsamples.utils;

import android.content.Context;
import android.text.TextUtils;

import com.soleren.pythonsamples.model.Item;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by den on 2017-05-22.
 */

public class XMLParser {
    private static XMLParser xmlParser = new XMLParser();
    private static Context context;
    private static int resourceId;
    private ArrayList<Item> items;

    private XMLParser() {
    }

    public static XMLParser getXmlParser(Context c, int res) {
        context = c;
        resourceId = res;
        return xmlParser;
    }


    public ArrayList<Item> parse() {
        XmlPullParser xpp = prepare(resourceId);
        items = new ArrayList<>();
        Item item = new Item();
        String tag = "";
        try {
            while (xpp.getEventType() != XmlPullParser.END_DOCUMENT) {
                switch (xpp.getEventType()) {
                    case XmlPullParser.START_DOCUMENT:
//                        Log.d("!!!", "Start");
                        break;

                    case XmlPullParser.START_TAG:
                        switch (xpp.getName()) {
                            case "id":
                                tag = "id";
                                break;
                            case "menu":
                                tag = "menu";
                                break;
                            case "submenu":
                                tag = "submenu";
                                break;
                            case "title":
                                tag = "title";
                                break;
                            case "content":
                                tag = "content";
                                break;
                            case "print":
                                tag = "print";
                                break;

                        }
                        break;

                    case XmlPullParser.TEXT:
                        if (!TextUtils.isEmpty(tag)) {
                            switch (tag) {
                                case "id":
                                    tag = "id";
                                    item.setId(xpp.getText());
                                    break;
                                case "menu":
                                    tag = "menu";
                                    item.setMenu(xpp.getText());
                                    break;
                                case "submenu":
                                    tag = "submenu";
                                    item.setSubmenu(xpp.getText());
                                    break;
                                case "title":
                                    item.setTitle(xpp.getText());
                                    break;
                                case "content":
                                    item.setContent(xpp.getText());
                                    break;
                                case "print":
                                    item.setPrint(xpp.getText());
                                    break;
                                default:break;
                            }
                        }
                        break;
                    case XmlPullParser.END_TAG:
                        if (tag.equals("print")) {
                            items.add(item);
                            item = new Item();
                        }
                        if (!TextUtils.isEmpty(tag)) {
                            tag = "";
                        }
                        break;
                    default:
                        break;
                }
                xpp.next();
            }
//            for (Item item1 : items) {
//                Log.d("!!!",item1.getId() + " " + item1.getTitle_ru() + " " + item1.getTitle_en() + " " + item1.getContent_ru() +" " +item1.getContent_en() + " " + item1.getPrint());
//            }
            return items;

//            Log.d("!!!", "END_DOCUMENT");
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

     private XmlPullParser prepare(int id){
         return context.getResources().getXml(id);
     }
}
