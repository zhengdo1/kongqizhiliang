package com.gz.xhb.util;

import android.app.Activity;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.gz.xhb.R;


/**
 * 快速设置ToolBar的工具类
 * 需要提前在布局页面中添加ToolBar
 *
 */
public class ToolBarUtil {
    /**
     * 设置ToolBar
     * @param res 设置ToolBar的Id
     *
     *
     */
    public static void setToolBarWithId(final AppCompatActivity activity, int res, String title) {
        Toolbar toolbar = (Toolbar) activity.findViewById(res);
        activity.setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.mipmap.ic_launcher);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.finish();
            }
        });
        toolbar.setTitle(title);
        activity.getSupportActionBar().setTitle(title);
        toolbar.setTitleTextColor(Color.WHITE);
    }

    public static void setToolBar(final AppCompatActivity activity, String title) {
//        Toolbar toolbar = (Toolbar) activity.findViewById(R.id.toolbar);
//        activity.setSupportActionBar(toolbar);
//        toolbar.setNavigationIcon(R.mipmap.ic_back);
//
////        TextView mCenterTitle = new TextView(activity);
////        mCenterTitle.setGravity(Gravity.CENTER);
////        mCenterTitle.setSingleLine();
////        mCenterTitle.setEllipsize(TextUtils.TruncateAt.END);
////        mCenterTitle.setTextColor(Color.WHITE);
////        mCenterTitle.setText(title);
////        mCenterTitle.setTextSize(20);
////        //构建子view想要显示的参数
////        FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT
////                ,ViewGroup.LayoutParams.WRAP_CONTENT);
//////        lp.alignWithParent=true;
//////        lp.addRule(RelativeLayout.CENTER_HORIZONTAL);
////        lp.gravity = GravityCompat.START | Gravity.CENTER_HORIZONTAL;
////        //textView in center
////        toolbar.addView(mCenterTitle, lp);
//
//        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    activity.finish();
//                }
//            });
//
//        toolbar.setTitle("");
//        activity.getSupportActionBar().setTitle("");
//        toolbar.setTitleTextColor(Color.WHITE);


        TitleBar mTitleBar = (TitleBar) activity.findViewById(R.id.toolbar);
        activity.setSupportActionBar(mTitleBar);
        activity.getSupportActionBar().setDisplayShowTitleEnabled(false);

        mTitleBar.setCenterTitle(title);
        mTitleBar.setBackgroundColor(activity.getResources().getColor(R.color.colorPrimary));
//        mTitleBar.setNavigationIcon(R.mipmap.ic_launcher);
        mTitleBar.setNavigationIcon(R.mipmap.ic_back);
        mTitleBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.finish();
            }
        });
    }
    public  static Toolbar getToolBar(AppCompatActivity activity){
        return (Toolbar) activity.findViewById(R.id.toolbar);
    }
}