package com.soleren.pythonsamples.application;

import android.app.Application;
import android.content.Context;

import com.soleren.pythonsamples.utils.XMLParser;

/**
 * Created by laktionov on 25.08.2017.
 * Класс инициализирует парсер и подготавливает контент для всего приложения;
 */

public class PythonSamples extends Application {

    private static Context appContext;

    @Override
    public void onCreate() {
        super.onCreate();
        appContext = this;
        XMLParser.parse();
    }

    public static Context getAppContext() {
        return appContext;
    }
}
