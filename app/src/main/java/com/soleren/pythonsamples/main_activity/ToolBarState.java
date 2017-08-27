package com.soleren.pythonsamples.main_activity;

/**
 * Created by laktionov on 25.08.2017.]
 * Класс, экземпляр которого создается для {@link ToolBarState}
 */

class ToolBarState {

    private String mTitle;
    private String mSubtitle;

    /**
     * Конструктор создания текста для ToolBar
     * @param mTitle заголовок для ToolBar;
     */
    ToolBarState(String mTitle) {
        this.mTitle = mTitle;
    }

    /**
     * Перегруженный конструктор на будущее. Мало ли
     *
     * @param mTitle    заголовок для ToolBar
     * @param mSubtitle подзаголовок для ToolBar
     */
    public ToolBarState(String mTitle, String mSubtitle) {
        this.mTitle = mTitle;
        this.mSubtitle = mSubtitle;
    }

    String getTitle() {
        return mTitle;
    }

    String getSubtitle() {
        return mSubtitle;
    }
}
