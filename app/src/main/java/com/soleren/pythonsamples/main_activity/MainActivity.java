package com.soleren.pythonsamples.main_activity;

import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.FragmentManager;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.soleren.pythonsamples.R;
import com.soleren.pythonsamples.data.Const;
import com.soleren.pythonsamples.fragments.ContentFragment;
import com.soleren.pythonsamples.fragments.HierarchyFragment;
import com.soleren.pythonsamples.fragments.MenuFragment;
import com.soleren.pythonsamples.fragments.SubMenuFragment;
import com.soleren.pythonsamples.fragments.TitleFragment;
import com.soleren.pythonsamples.search_activity.SearchActivity;

import java.util.EmptyStackException;
import java.util.Stack;

public class MainActivity extends AppCompatActivity implements HierarchyFragment.FragmentChangeListener {

    private Stack<String> mTitleStack;
    private FragmentManager mFragmentManager;
    private Toolbar mToolBar;
    private ActionBar mActionBar;

    private String mLastTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initData();
        initViews();
        initToolbar();
        if (savedInstanceState == null) {
            initTopFragment();
        } else {
            restoreLastTitle(savedInstanceState);
            restoreLastActiveFragment(savedInstanceState);
        }
    }

    private void initData() {
        mFragmentManager = getSupportFragmentManager();
        mTitleStack = new Stack<>();
        mLastTitle = getResources().getString(R.string.app_name);
    }

    private void initViews() {
        mToolBar = (Toolbar) findViewById(R.id.main_activity_toolbar);
    }

    private void initToolbar() {
        setSupportActionBar(mToolBar);
        mActionBar = getSupportActionBar();
    }

    /**
     * Инициализирует меню приложения {@link MenuFragment}
     */
    private void initTopFragment() {
        MenuFragment menuFragment = new MenuFragment();
        menuFragment.setFragmentChangeListener(this);
        setActiveFragment(menuFragment);
    }

    /**
     * Устанавливает первый фрагмент {@link MenuFragment} в активити в случае первого запуска приложения.
     *
     * @param fragment меню приложения.
     */
    public void setActiveFragment(MenuFragment fragment) {
        mFragmentManager
                .beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .addToBackStack(MenuFragment.class.getName())
                .commit();
    }

    @Override
    public void changeCurrentVisibleFragment(String categoryTitle, int nextFragment) {
        addToolBarTitle(categoryTitle);
        MainActivity.this.changeCurrentVisibleFragment(nextFragment);
    }

    /**
     * Метод управляет логикой смены фрагментов при переходах "Вперед" по иерархии приложения.
     *
     * @param nextFragment передается из слушателя во вермя смены фрагментов.
     */
    private void changeCurrentVisibleFragment(int nextFragment) {
        switch (nextFragment) {
            case Const.SUB_MENU_TITLE_ID:
                SubMenuFragment subMenuFragment = new SubMenuFragment();
                mFragmentManager
                        .beginTransaction()
                        .setCustomAnimations(R.anim.fragment_enter, R.anim.fragment_exit, R.anim.fragment_pop_enter, R.anim.fragment_pop_exit)
                        .replace(R.id.fragment_container, subMenuFragment)
                        .addToBackStack(SubMenuFragment.class.getName())
                        .commit();
                break;
            case Const.TITLE_ID:
                TitleFragment titleFragment = new TitleFragment();
                mFragmentManager
                        .beginTransaction()
                        .setCustomAnimations(R.anim.fragment_enter, R.anim.fragment_exit, R.anim.fragment_pop_enter, R.anim.fragment_pop_exit)
                        .replace(R.id.fragment_container, titleFragment)
                        .addToBackStack(TitleFragment.class.getName())
                        .commit();
                break;
            case Const.CONTENT_ID:
                ContentFragment contentFragment = new ContentFragment();
                mFragmentManager
                        .beginTransaction()
                        .setCustomAnimations(R.anim.fragment_enter, R.anim.fragment_exit, R.anim.fragment_pop_enter, R.anim.fragment_pop_exit)
                        .replace(R.id.fragment_container, contentFragment)
                        .addToBackStack(ContentFragment.class.getName())
                        .commit();
                break;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if (mFragmentManager.getBackStackEntryCount() > 1) {
            mFragmentManager.popBackStack();
            popToolBarState();
        } else {
            finish();
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putSerializable(Const.KEY_STACK, mTitleStack);
        outState.putString(Const.KEY_TITLE, mLastTitle);
        mFragmentManager.putFragment(outState, Const.KEY_FRAGMENT, mFragmentManager.findFragmentById(R.id.fragment_container));
        super.onSaveInstanceState(outState);
    }

    /*
    * Методы управление состояниями тулбара, котроые меняются во время смены фрагметов , а так же восстановление этих состояний после
    * вызова метода жизненного цикла активити onSavedInstanceState()
    * */

    /**
     * Основной метод изменения состояния тулбара во время навигации "Вперед". Наполняет стэк для
     * организации переходов назад.
     *
     * @param currentTitle передается из слушателя во вермя смены фрагментов.
     */
    private void addToolBarTitle(String currentTitle) {
        mLastTitle = currentTitle;
        mTitleStack.add(currentTitle);
        mActionBar.setDisplayHomeAsUpEnabled(true);
        mActionBar.setTitle(currentTitle);
    }

    /**
     * Основной метод изменения состояния тулбара во время навигации "Назад". Вызывает исключение,
     * если в стэке нету больше названий. Это происходит при возвращении в {@link MenuFragment}.
     */
    private void popToolBarState() {
        try {
            mTitleStack.pop();
            mActionBar.setTitle(mTitleStack.peek());
        } catch (EmptyStackException e) {
            setDefaultState();
        }
    }

    /**
     * Метод возвращает в активити последний видимый фрагмент. Этор реализуется путем помещения данных {@link FragmentManager}
     * в Bundle в методе onSavedInstanceState().
     *
     * @param savedInstanceState передается из метода onCrate() активити.
     */
    private void restoreLastActiveFragment(Bundle savedInstanceState) {
        HierarchyFragment fragment = (HierarchyFragment) mFragmentManager.getFragment(savedInstanceState, Const.KEY_FRAGMENT);
        fragment.setFragmentChangeListener(this);
        mFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commit();
    }

    /**
     * Метод восстанавливает состояние тулбара после изменения конфигурации телефона. Нарпимер, после поворота экрана
     *
     * @param savedInstanceState передается из метода onCrate() активити.
     */
    private void restoreLastTitle(Bundle savedInstanceState) {
        mTitleStack = (Stack<String>) savedInstanceState.getSerializable(Const.KEY_STACK);
        mLastTitle = savedInstanceState.getString(Const.KEY_TITLE);
        try {
            if (mFragmentManager.getBackStackEntryCount() > 1) {
                mActionBar.setDisplayHomeAsUpEnabled(true);
            }
            mActionBar.setTitle(mLastTitle);
        } catch (EmptyStackException e) {
            setDefaultState();
        }
    }

    /**
     * Метод устанавливает состояние тулбара, которое было во время первого открытия приложения.
     * Обрабатывает исключение {@link EmptyStackException}.
     */
    private void setDefaultState() {
        mActionBar.setDisplayHomeAsUpEnabled(false);
        mActionBar.setTitle(R.string.app_name);
        mLastTitle = getResources().getString(R.string.app_name);
    }

    public void onSearchClick(View view) {
        Intent intent = new Intent(this, SearchActivity.class);
        startActivity(intent);
    }
}
