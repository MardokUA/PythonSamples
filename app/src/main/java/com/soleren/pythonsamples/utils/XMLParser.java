package com.soleren.pythonsamples.utils;

import android.content.Context;
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
//                        Log.d("!!!", "Start");
                        break;

                    case XmlPullParser.START_TAG:
                        switch (xpp.getName()) {
//                            case "id":
//                                tag = "id";
//                                break;
                            case "title":
                                tag = "title";
                                break;
                            case "content":
                                tag = "content";
                                break;
//                            case "title_ru":
//                                tag = "title_ru";
//                                break;
//                            case "title_en":
//                                tag = "title_en";
//                                break;
//                            case "content_ru":
//                                tag = "content_ru";
//                                break;
//                            case "content_en":
//                                tag = "content_en";
//                                break;
                            case "print":
                                tag = "print";
                                break;

                        }
                        break;

                    case XmlPullParser.TEXT:
                        if (!TextUtils.isEmpty(tag)) {
                            switch (tag) {
//                                case "id":
//                                    item.setId(xpp.getText());
//                                    break;
                                case "title":
                                    item.setTitle(xpp.getText());
                                    break;
                                case "content":
                                    item.setContent(xpp.getText());
                                    break;
//                                case "title_ru":
//                                    item.setTitle_ru(xpp.getText());
//                                    break;
//                                case "title_en":
//                                    item.setTitle_en(xpp.getText());
//                                    break;
//                                case "content_ru":
//                                    item.setContent_ru(xpp.getText());
//                                    break;
//                                case "content_en":
//                                    item.setContent_en(xpp.getText());
//                                    break;
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
                        if (!TextUtils.isEmpty(tag))
                            tag = "";

                        break;
                    default:
                        break;
                }
                xpp.next();
            }
            return items;

//            Log.d("!!!", "END_DOCUMENT");
        } catch (XmlPullParserException | IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private XmlPullParser prepare(int id) {
        return PythonSamples.getAppContext().getResources().getXml(id);
    }
}
