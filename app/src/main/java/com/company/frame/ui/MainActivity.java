package com.company.frame.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;

import com.company.frame.R;
import com.company.frame.common.MyActivity;
import com.company.frame.common.MyFragment;
import com.company.frame.helper.ActivityStackManager;
import com.company.frame.helper.DoubleClickHelper;
import com.company.frame.other.KeyboardWatcher;
import com.company.frame.ui.fragment.Fragment01;
import com.company.frame.ui.fragment.Fragment02;
import com.company.frame.ui.fragment.Fragment03;
import com.company.frame.ui.fragment.Fragment04;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.hjq.base.BaseFragmentAdapter;

/**
 * AppConfig  各种配置,报名,版本号等等
 */
public class MainActivity extends MyActivity implements KeyboardWatcher.SoftKeyboardStateListener, BottomNavigationView.OnNavigationItemSelectedListener {

    private ViewPager mViewPager;
    private BottomNavigationView mBottomNavigationView;
    private BaseFragmentAdapter<MyFragment> mPagerAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        mViewPager = findViewById(R.id.vp_home_pager);
        mBottomNavigationView = findViewById(R.id.bv_home_navigation);
        // 不使用图标默认变色
        mBottomNavigationView.setItemIconTintList(null);
        mBottomNavigationView.setOnNavigationItemSelectedListener(this);
        //取消底部tab动画
//        mBottomNavigationView.setItemTextAppearanceActive(R.style.bottom_selected_text);
//        mBottomNavigationView.setItemTextAppearanceInactive(R.style.bottom_normal_text);
        KeyboardWatcher.with(this)
                .setListener(this);
    }

    @Override
    protected void initData() {
        mPagerAdapter = new BaseFragmentAdapter<>(this);
        mPagerAdapter.addFragment(Fragment01.newInstance());
        mPagerAdapter.addFragment(Fragment02.newInstance());
        mPagerAdapter.addFragment(Fragment03.newInstance());
        mPagerAdapter.addFragment(Fragment04.newInstance());
        // 设置成懒加载模式
        mPagerAdapter.setLazyMode(true);
        mViewPager.setAdapter(mPagerAdapter);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_home:
                mViewPager.setCurrentItem(0, false);
                return true;
            case R.id.home_found:
                mViewPager.setCurrentItem(1, false);
                return true;
            case R.id.home_message:
                mViewPager.setCurrentItem(2, false);
                return true;
            case R.id.home_me:
                mViewPager.setCurrentItem(3, false);
                return true;
            default:
                break;
        }
        return false;
    }

    @Override
    public void onBackPressed() {
        if (DoubleClickHelper.isOnDoubleClick()) {
            // 移动到上一个任务栈，避免侧滑引起的不良反应
            moveTaskToBack(false);
            postDelayed(() -> {

                // 进行内存优化，销毁掉所有的界面
                ActivityStackManager.getInstance().finishAllActivities();
                // 销毁进程（注意：调用此 API 可能导致当前 Activity onDestroy 方法无法正常回调）
                // System.exit(0);

            }, 300);
        } else {
            toast(R.string.home_exit_hint);
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // 回调当前 Fragment 的 onKeyDown 方法
        if (mPagerAdapter.getShowFragment().onKeyDown(keyCode, event)) {
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    /**
     * 软键盘的监听方法
     *
     * @param keyboardHeight 软键盘高度
     */
    @Override
    public void onSoftKeyboardOpened(int keyboardHeight) {
        mBottomNavigationView.setVisibility(View.GONE);
    }

    @Override
    public void onSoftKeyboardClosed() {
        mBottomNavigationView.setVisibility(View.VISIBLE);
    }

    @Override
    protected void onDestroy() {
        mViewPager.setAdapter(null);
        mBottomNavigationView.setOnNavigationItemSelectedListener(null);
        super.onDestroy();
    }

    @Override
    public boolean isSwipeEnable() {
        return false;
    }

}