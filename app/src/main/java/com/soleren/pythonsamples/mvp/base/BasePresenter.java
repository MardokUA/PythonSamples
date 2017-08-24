package com.soleren.pythonsamples.mvp.base;

import android.os.Bundle;

public interface BasePresenter {

    void selectTitle(int res);

    void selectTitle(Bundle bundle);

    void selectFragment(String fName, int position);

    void selectFragment(int fNumber);

    void destroy();
}
