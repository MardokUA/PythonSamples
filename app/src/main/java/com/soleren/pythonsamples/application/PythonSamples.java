package com.soleren.pythonsamples.application;

import android.app.Application;
import android.content.Context;

/**
 * Created by laktionov on 25.08.2017.
 */

public class PythonSamples extends Application {

    private static Context appContext;

    @Override
    public void onCreate() {
        super.onCreate();
        appContext = this;
    }

    public static Context getAppContext() {
        return appContext;
    }
}
