package com.soleren.pythonsamples.utils;

import android.text.TextUtils;

import com.soleren.pythonsamples.application.PythonSamples;
import com.soleren.pythonsamples.model.Item;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by den on 2017-05-22.
 */

public class XMLParser {
    private static XMLParser xmlParser = new XMLParser();
    private static int resourceId;
    private ArrayList<Item> items;

    private XMLParser() {
    }

    public static XMLParser getXmlParser(int res) {
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
                                default:
                                    break;
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
            return items;

        } catch (XmlPullParserException | IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private XmlPullParser prepare(int id) {
        return PythonSamples.getAppContext().getResources().getXml(id);
    }
}
