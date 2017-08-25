package com.soleren.pythonsamples.main_activity;

import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;

import java.util.Stack;

/**
 * Created by laktionov on 25.08.2017.
 * Этот класс инициализируется в MainActivity и занимается управлением состояний тулбара в нем.
 * Его задача - хранить весь стэк заголовков, которые геренинуются при переходе из активити во
 * фрагменты и обратно.
 */

class ToolBarController {

    private Toolbar mMainActivityToolbar;
    private Stack<ToolBarController.State> mToolBarTitleStack;
    private ActionBarDrawerToggle mArrowToogle;

    private ToolBarController() {
        // запрещаем создавать контроллер через new, что бы обязать инициировать билдер
    }

    /**
     * Статичный метод, используется вместо контсруттора. Заставляет инициализировать внутренний класс
     * Builder, который инстанциирует ToolBarController.
     *
     * @return new ToolBarController();
     */
    static Builder createBuilder() {
        return new ToolBarController().new Builder();
    }

    public void addToolBarTitle(ToolBarController.State currentState) {
        if (currentState != null) {
            mToolBarTitleStack.add(currentState);
            changeActualTitle(currentState);
        }
    }

    private void changeActualTitle(ToolBarController.State currentState) {
        if (mMainActivityToolbar != null) {
            if (currentState.getTitle() != null) {
                mMainActivityToolbar.setTitle(currentState.getTitle());
            }
            if (currentState.getSubtitle() != null) {
                mMainActivityToolbar.setSubtitle(currentState.getSubtitle());
            }
        }
    }

    public class Builder {

        private Builder() {
        }

        /**
         * @param toolBar из MainActivity
         * @return возвращает Builder, что бы продолжить цепочку инициации;
         */
        Builder withToolBar(Toolbar toolBar) {
            ToolBarController.this.mMainActivityToolbar = toolBar;
            return this;
        }

        Builder withActionBarDrawerToggle(ActionBarDrawerToggle arrowToggle) {
            ToolBarController.this.mArrowToogle = arrowToggle;
            return this;
        }

        /**
         * Этим методом заканчивается инициализация ToolBarController'a
         * и создается пустой стэк стейтов тулбара
         *
         * @return new ToolBarController
         */
        ToolBarController build() {
            ToolBarController.this.mToolBarTitleStack = new Stack<>();
            return new ToolBarController();
        }
    }

    private class State {

        private String mTitle;
        private String mSubtitle;

        public State(String mTitle) {
            this.mTitle = mTitle;
        }

        /**
         * Перегруженный конструктор на будущее. Мало ли
         *
         * @param mTitle    заголовок для ToolBar
         * @param mSubtitle подзаголовок для ToolBar
         */
        public State(String mTitle, String mSubtitle) {
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
}
