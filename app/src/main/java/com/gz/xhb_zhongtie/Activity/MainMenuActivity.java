package com.gz.xhb_zhongtie.Activity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.gz.xhb_zhongtie.App.Jpush.JPushUtil;
import com.gz.xhb_zhongtie.MVP.Presenter.MainMenuPresenter;
import com.gz.xhb_zhongtie.MVP.View.MainMenuView;
import com.gz.xhb_zhongtie.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by xjj on 2018/6/5.
 */

public class MainMenuActivity extends XHBBaseActivity implements MainMenuView {
    @BindView(R.id.MyGridView)
    GridView mGridView;
    @BindView(R.id.tv_main_title)
    TextView tvMainTitle;

    //定义图标数组
//    private int[] imageRes = {R.mipmap.ic_map, R.mipmap.ic_psdetail, R.mipmap.ic_water, R.mipmap.ic_gas,
//            R.mipmap.ic_vocs, R.mipmap.ic_alarm, R.mipmap.ic_warning};
    private int[] imageRes = {R.mipmap.ic_map, R.mipmap.ic_psdetail,  R.mipmap.ic_gas,
             R.mipmap.ic_alarm, R.mipmap.ic_warning};
    //定义标题数组
//    private String[] itemName = {"电子地图", "基础信息", "污水数据", "废气数据", "VOCs数据", "报警数据", "预警数据"};
//    private String[] typeName = {"电子地图", "基础信息", "污水", "废气", "VOC", "报警数据", "预警数据"};
    private String[] itemName = {"电子地图", "基础信息",  "空气质量",  "报警数据", "预警数据"};
    private String[] typeName = {"电子地图", "基础信息", "空气质量",  "报警数据", "预警数据"};

//    private int[] toClassType = {-1,0,1,1,1,1,1};

    private Class[] classes = {AMapActivity.class
            , PsListActivity.class
            , PsListActivity.class,
            AlarmDataListActivity.class,
            AlarmDataListActivity.class,};

    private List<HashMap<String, Object>> data = new ArrayList<>();

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_main;
    }

    MainMenuPresenter mainMenuPresenter = new MainMenuPresenter();


    @Override
    protected void initView(Bundle savedInstanceState) {

        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/hwkt.ttf");
        tvMainTitle.setTypeface(typeface);
        int length = itemName.length;
        for (int i = 0; i < length; i++) {
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("ItemImageView", imageRes[i]);
            map.put("ItemTextView", itemName[i]);
            data.add(map);
        }
        SimpleAdapter simpleAdapter = new SimpleAdapter(this,
                data, R.layout.main_menu_item, new String[]{"ItemImageView", "ItemTextView"},
                new int[]{R.id.ItemImageView, R.id.ItemTextView});
        mGridView.setAdapter(simpleAdapter);

        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                toActivity(position);
            }
        });
    }


    @Override
    public void showError() {

    }

    @Override
    public void showSuccess() {

    }

    @Override
    protected void setStatusColor() {
        fullScreen(this);
    }

    @Override
    protected void setSystemInvadeBlack() {
    }

    @Override
    public void toActivity(int position) {
        Intent intent = new Intent(this, classes[position]);
        intent.putExtra("typeName", typeName[position]);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
