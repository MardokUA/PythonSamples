package com.soleren.pythonsamples.utils;

import android.text.TextUtils;

import com.soleren.pythonsamples.R;
import com.soleren.pythonsamples.application.PythonSamples;
import com.soleren.pythonsamples.model.Item;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

/**
 * Created by den on 2017-05-22.
 * Класс парсит XML и генерирует объекты Item для класса {@link ContentFactory}
 */

public class XMLContentParser {

    private XMLContentParser() {
    }

    private static XmlPullParser prepare(int id) {
        return PythonSamples.getAppContext().getResources().getXml(id);
    }

    public static void parse() {
        XmlPullParser xpp = prepare(R.xml.main);
        String tag = "";
        Item item = new Item();
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
                            ContentFactory.fetchContent(item);
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
        } catch (XmlPullParserException | IOException e) {
            e.printStackTrace();
        }
    }
}
