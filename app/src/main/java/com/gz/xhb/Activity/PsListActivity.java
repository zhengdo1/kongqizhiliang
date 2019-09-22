package com.gz.xhb.Activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.gz.xhb.Adapter.PsListAdapter;
import com.gz.xhb.Base.BaseListActivity;
import com.gz.xhb.Base.XHBBaseAdapter;
import com.gz.xhb.MVP.Model.Entity.PsListInfo;
import com.gz.xhb.MVP.Presenter.PsListPresenter;
import com.gz.xhb.MVP.View.PsListView;
import com.gz.xhb.R;
import com.gz.xhb.util.ToastTool;
import com.gz.xhb.util.ToolBarUtil;
import com.miguelcatalan.materialsearchview.MaterialSearchView;

import java.util.ArrayList;
import java.util.List;

import cn.bingoogolapple.refreshlayout.BGANormalRefreshViewHolder;
import cn.bingoogolapple.refreshlayout.BGARefreshLayout;
import cn.bingoogolapple.refreshlayout.BGARefreshViewHolder;

/**
 * Created by xjj on 2018/6/8.
 */

public class PsListActivity extends BaseListActivity<ListView, PsListAdapter> implements BGARefreshLayout.BGARefreshLayoutDelegate, PsListView {
    //    int i;
//    List<PsListInfo.DataBean> mList = new ArrayList<>();
    BGARefreshLayout mRefreshLayout;
    //    PsListAdapter psListAdapter;
//    private MaterialSearchView searchView = findViewById(R.id.search_view);
    private MaterialSearchView searchView;
    PsListPresenter psListPresenter = new PsListPresenter(this);
    private String typeName;
    private Class[] classes = new Class[]{PsBaseInfoActivity.class, PortListActivity.class};
    private Class aClass;
    private String keyword = "";
    private String pstype = "0";


    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_ps_list;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);
        mRefreshLayout = findViewById(R.id.rl_psList_refresh);
        initSearchView();
        initRefreshLayout(mRefreshLayout);
    }


    @Override
    protected void initData() {
        getIntentData();
        switch (typeName) {
            case "污水":
                pstype = "1";
                break;
            case "废气":
                pstype = "2";
                break;
            case "VOC":
                pstype = "3";
                break;
        }
        psListPresenter.getList(keyword,pstype);
    }


    @Override
    public String setTitle() {
        return "企业列表";
    }

    @Override
    public ListView setListView() {
        return findViewById(R.id.lv_psList_content);
    }

    @Override
    public void loadDataSync() {

    }

    void initSearchView() {
        searchView = (MaterialSearchView) findViewById(R.id.search_view);
        searchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                //Do some magic
                psListPresenter.refresh(query,pstype);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                //Do some magic
                keyword = newText;
                psListPresenter.refresh(keyword,pstype);
                return true;
            }
        });

        searchView.setOnSearchViewListener(new MaterialSearchView.SearchViewListener() {
            @Override
            public void onSearchViewShown() {
                //Do some magic
            }

            @Override
            public void onSearchViewClosed() {
                //Do some magic
            }
        });
    }

    @Override
    public void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout mRefreshLayout) {

//        new AsyncTask<Void, Void, Void>() {
//
//            @Override
//            protected Void doInBackground(Void... params) {
//                try {
//                    Thread.sleep(1000);
//                    mList = new ArrayList<>();
//                    i = 0;
////                    initData();
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                return null;
//            }
//
//            @Override
//            protected void onPostExecute(Void aVoid) {
//                // 加载完毕后在 UI 线程结束加载更多
//                psListPresenter.refresh();
//                mRefreshLayout.endRefreshing();
//
//            }
//        }.execute();
        psListPresenter.refresh(keyword,pstype);

    }


    @Override
    public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout mRefreshLayout) {
//        if (true) {
//            // 如果网络可用，则异步加载网络数据，并返回 true，显示正在加载更多
//            new AsyncTask<Void, Void, Void>() {
//
//                @Override
//                protected Void doInBackground(Void... params) {
//                    try {
//                        Thread.sleep(1000);
////                        initData();
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                    return null;
//                }
//
//                @Override
//                protected void onPostExecute(Void aVoid) {
//                    // 加载完毕后在 UI 线程结束加载更多
//                    psListAdapter.onDateChange(mList);
//                }
//            }.execute();
//            return true;
//        } else {
//            // 网络不可用，返回 false，不显示正在加载更多
//            Toast.makeText(this, "网络不可用", Toast.LENGTH_SHORT).show();
        return false;
//        }
    }

    private void initRefreshLayout(BGARefreshLayout mRefreshLayout) {
        // 为BGARefreshLayout 设置代理
        mRefreshLayout.setDelegate(this);
        // 设置下拉刷新和上拉加载更多的风格     参数1：应用程序上下文，参数2：是否具有上拉加载更多功能
        BGARefreshViewHolder refreshViewHolder = new BGANormalRefreshViewHolder(this, true);
        // 设置下拉刷新和上拉加载更多的风格
        mRefreshLayout.setRefreshViewHolder(refreshViewHolder);


        // 为了增加下拉刷新头部和加载更多的通用性，提供了以下可选配置选项  -------------START
        // 设置正在加载更多时不显示加载更多控件
        // mRefreshLayout.setIsShowLoadingMoreView(false);
        // 设置正在加载更多时的文本
        refreshViewHolder.setLoadingMoreText("加载更多");
        // 设置整个加载更多控件的背景颜色资源 id
        refreshViewHolder.setLoadMoreBackgroundColorRes(R.color.white);
        // 设置整个加载更多控件的背景 drawable 资源 id
//        refreshViewHolder.setLoadMoreBackgroundDrawableRes(loadMoreBackgroundDrawableRes);
        // 设置下拉刷新控件的背景颜色资源 id
//        refreshViewHolder.setRefreshViewBackgroundColorRes(refreshViewBackgroundColorRes);
        // 设置下拉刷新控件的背景 drawable 资源 id
        refreshViewHolder.setRefreshViewBackgroundDrawableRes(R.color.white);
        // 设置自定义头部视图（也可以不用设置）     参数1：自定义头部视图（例如广告位）， 参数2：上拉加载更多是否可用
//        mRefreshLayout.setCustomHeaderView(mBanner, false);
        // 可选配置  -------------END
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_ps_list, menu);

        MenuItem item = menu.findItem(R.id.action_search);
        searchView.setMenuItem(item);

        return true;

    }


    @Override
    public void showList(PsListInfo psListInfo) {
        if (adapter == null) {
            setAdapter(new PsListAdapter(this, psListInfo.getData(), aClass, typeName));
            listView.setAdapter(adapter);
        } else {
            adapter.onDateChange(psListInfo.getData());
        }
    }

    @Override
    public void showError() {
        ToastTool.ShowShortToast(this, getResources().getString(R.string.internet_error));
    }

    @Override
    public void updateListView(PsListInfo psListInfo) {
        showList(psListInfo);
    }

    @Override
    public void showSuccess() {

    }

    @Override
    public void endRefresh() {
        mRefreshLayout.endRefreshing();
    }

    @Override
    public void endLoadMore() {

    }

    @Override
    public void showEmpty() {

    }

    public void getIntentData() {
        typeName = getIntent().getStringExtra("typeName");
        switch (typeName) {
            case "基础信息":
                aClass = classes[0];
                break;
            default:
                aClass = classes[1];
        }
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

    }
}
