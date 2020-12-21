package com.company.frame.ui.fragment;

import android.view.View;
import android.widget.TextView;

import com.company.frame.R;
import com.company.frame.action.StatusAction;
import com.company.frame.common.MyFragment;
import com.company.frame.ui.MainActivity;
import com.company.frame.widget.HintLayout;


/**
 * LoveLin
 * <p>
 * Describe 第一个fragment
 */
public class Fragment02 extends MyFragment<MainActivity> implements StatusAction {


    private HintLayout mHintLayout;

    public static Fragment02 newInstance() {
        return new Fragment02();
    }


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_02;
    }

    @Override
    protected void initData() {
//        showError();
        showLoadingBySearch();
    }

    @Override
    protected void initView() {
        TextView tv_02 = findViewById(R.id.tv_02);
        mHintLayout = findViewById(R.id.hl_status_hint);

        tv_02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        tv_02.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {

                return false;
            }
        });
    }


    @Override
    public boolean isStatusBarEnabled() {
        // 使用沉浸式状态栏
        return !super.isStatusBarEnabled();
    }

    @Override
    public HintLayout getHintLayout() {
        return mHintLayout;
    }
}
