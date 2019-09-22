package com.gz.xhb.Base;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * Created by Administrator on 2016/1/14 0014.
 */
public abstract class XHBBaseAdapter<T> extends BaseAdapter {

    protected Context context;
    protected List<T> list;
    protected LayoutInflater inflater;
    protected  Class<?> aClass;


    public XHBBaseAdapter(Context context, List<T> list,Class aClass) {
        this.context=context;
        this.list=list;
        inflater= LayoutInflater.from(context);
        this.aClass = aClass;
    }

    public void onDateChange(List<T> list) {
        this.list = list;
        this.notifyDataSetChanged();
    }


    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

//    @Override
//    public View getView(int position, View convertView, ViewGroup parent) {
//        ViewHolder holder=null;
//        if(convertView==null){
//            convertView=inflater.inflate(R.layout.item_ps_list, null);
//            holder=new ViewHolder();
//            holder.userName = (TextView) convertView.findViewById(R.id.tv_itemPsList_psName);
//            convertView.setTag(holder);
//        }
//        holder=(ViewHolder)convertView.getTag();
//        final T userInfo=list.get(position);
//        holder.userName.setText(userInfo.getPsname());
//        return convertView;
//    }
//
//
//
//    class ViewHolder{
//        TextView userName;
//    }
}

