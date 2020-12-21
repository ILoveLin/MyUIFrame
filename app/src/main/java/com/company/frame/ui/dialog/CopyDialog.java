package com.company.frame.ui.dialog;

import android.content.Context;
import android.view.Gravity;

import com.company.frame.R;
import com.hjq.base.BaseDialog;


/**
 *    author : Android 轮子哥
 *    github : https://github.com/getActivity/AndroidProject
 *    time   : 2018/10/18
 *    desc   : 可进行拷贝的副本
 */
public final class CopyDialog {

    public static final class Builder
            extends BaseDialog.Builder<Builder> {

        public Builder(Context context) {
            super(context);

            setContentView(R.layout.copy_dialog);
            setAnimStyle(BaseDialog.ANIM_BOTTOM);
            setGravity(Gravity.BOTTOM);
        }
    }
}