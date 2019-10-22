package com.gz.xhb_zhongtie.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.gz.xhb_zhongtie.Base.XHBBaseAdapter;
import com.gz.xhb_zhongtie.MVP.Model.Entity.PsListInfo;
import com.gz.xhb_zhongtie.MVP.Model.Entity.PsListInfo.DataBean;
import com.gz.xhb_zhongtie.R;

import java.util.List;

/**
 * Created by Administrator on 2016/1/14 0014.
 */
public class PsListAdapter extends XHBBaseAdapter<PsListInfo.DataBean> {
    String typeName;

    public PsListAdapter(Context context, List<PsListInfo.DataBean> list, Class aClass,String typeName) {
        super(context, list, aClass);
        this.typeName = typeName;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_ps_list, null);
            holder = new ViewHolder();
            holder.userName = (TextView) convertView.findViewById(R.id.tv_itemPsList_psName);

            convertView.setTag(holder);
        }
        holder = (ViewHolder) convertView.getTag();
        final PsListInfo.DataBean userInfo = list.get(position);
        holder.userName.setText(userInfo.getPsname());
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (aClass != null) {
                    Intent intent = new Intent(context, aClass);
                    intent.putExtra("psCode", list.get(position).getPscode());
                    intent.putExtra("psName", list.get(position).getPsname());
                    intent.putExtra("typeName", typeName);
                    intent.putExtra("jumpToDataDetail", true);
                    context.startActivity(intent);
                }
            }
        });
        return convertView;
    }

    class ViewHolder {
        TextView userName;
    }
}

