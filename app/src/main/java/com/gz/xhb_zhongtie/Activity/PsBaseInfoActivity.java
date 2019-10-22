package com.gz.xhb_zhongtie.Activity;


import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.gz.xhb_zhongtie.MVP.Model.Entity.PsBaseInfo;
import com.gz.xhb_zhongtie.MVP.Model.Entity.PsBaseInfoData;
import com.gz.xhb_zhongtie.MVP.Presenter.PsBaseInfoPresenter;
import com.gz.xhb_zhongtie.R;
import com.gz.xhb_zhongtie.databinding.ActivityPsBaseInfoBinding;
import com.gz.xhb_zhongtie.util.ToolBarUtil;
import com.gz.xhb_zhongtie.MVP.View.PsBaseInfoView;

/**
 * Created by xjj on 2018/6/6.
 */

public class PsBaseInfoActivity extends XHBBaseActivity implements PsBaseInfoView {

    PsBaseInfoData dataBean = new PsBaseInfoData();
    ActivityPsBaseInfoBinding binding;
    String psCode = "",psName;
    
    PsBaseInfoPresenter psBaseInfoPresenter = new PsBaseInfoPresenter(this);

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_ps_base_info;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        ToolBarUtil.setToolBar(this,"企业基本信息");
        binding = DataBindingUtil.bind(getBindingLayout());
        binding.setPsmPsBaseInfo(dataBean);
        psName = getIntent().getStringExtra("psName");
        psCode = getIntent().getStringExtra("psCode");
        psBaseInfoPresenter.getPsBaseInfo(psCode);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_ps_base_info, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            case R.id.action_psBaseInfo_portInfo:
                Intent intent = new Intent(this, PortListActivity.class);
                intent.putExtra("psName",psName);
                intent.putExtra("psCode",psCode);
                intent.putExtra("typeName","");
                intent.putExtra("jumpToDataDetail",false);
                startActivity(intent);
                return true;

            case R.id.action_psBaseInfo_equipment:
                intent = new Intent(this, TestActivity.class);
                startActivityForResult(intent,2);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    @Override
    public void showError() {

    }

    @Override
    public void showSuccess() {

    }

    @Override
    public void showPsBaseInfo(PsBaseInfoData dataBean) {
        this.dataBean = dataBean;
        binding.setPsmPsBaseInfo(this.dataBean);
    }
}
