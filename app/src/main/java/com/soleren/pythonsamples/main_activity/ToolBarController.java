package com.soleren.pythonsamples.main_activity;

import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;

import com.soleren.pythonsamples.R;

import java.util.EmptyStackException;
import java.util.Stack;

/**
 * Created by laktionov on 25.08.2017.
 * Этот класс инициализируется в MainActivity и занимается управлением состояний тулбара в нем.
 * Его задача - хранить весь стэк заголовков, которые геренинуются при переходе из активити во
 * фрагменты и обратно.
 */

class ToolBarController {

    private Toolbar mMainActivityToolbar;
    private ActionBar mMainActivityActionBar;
    private Stack<ToolBarState> mToolBarTitleStack;

    private ToolBarController() {
        // запрещаем создавать контроллер через new, что бы обязать инициировать билдер
    }

    {
        mToolBarTitleStack = new Stack<>();
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

    void addToolBarTitle(ToolBarState currentState) {
        if (currentState != null) {
            mToolBarTitleStack.add(currentState);
            changeToolBarState(currentState);
            changeArrowButtonState();
        }
    }

    void restoreToolBarState() {
        if (mToolBarTitleStack.size() != 0) {
            addToolBarTitle(mToolBarTitleStack.pop());
        }
    }

    void popToolBarState() {
        mToolBarTitleStack.pop();
        try {
            changeToolBarState(mToolBarTitleStack.peek());
        } catch (EmptyStackException exp) {
            changeToolBarState(null);
            changeArrowButtonState();
        }
    }

    Stack<ToolBarState> getToolBarStack() {
        return mToolBarTitleStack;
    }

    void setToolBarStack(Stack<ToolBarState> mToolBarTitleStack) {
        this.mToolBarTitleStack = mToolBarTitleStack;
        restoreToolBarState();
    }

    private void changeToolBarState(ToolBarState currentState) {
        if (mMainActivityActionBar != null) {
            if (currentState != null) {
                if (currentState.getTitle() != null) {
                    mMainActivityToolbar.setTitle(currentState.getTitle());
                }
                if (currentState.getSubtitle() != null) {
                    mMainActivityToolbar.setSubtitle(currentState.getSubtitle());
                }
            } else {
                mMainActivityToolbar.setTitle(R.string.app_name);
            }
        }
    }

    private void changeArrowButtonState() {
        if (mMainActivityActionBar != null) {
            mMainActivityActionBar.setDisplayHomeAsUpEnabled(isToolBarStackEmpty());
        }
    }

    private boolean isToolBarStackEmpty() {
        return mToolBarTitleStack.size() > 0;
    }

    class Builder {

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

        /**
         * @param actionBar из MainActivity
         * @return возвращает Builder, что бы продолжить цепочку инициации;
         */
        Builder withActionBar(ActionBar actionBar) {
            ToolBarController.this.mMainActivityActionBar = actionBar;
            return this;
        }

        /**
         * Этим методом заканчивается инициализация ToolBarController'a
         * и создается пустой стэк стейтов тулбара
         *
         * @return new ToolBarController
         */
        ToolBarController build() {
            return ToolBarController.this;
        }
    }
}
