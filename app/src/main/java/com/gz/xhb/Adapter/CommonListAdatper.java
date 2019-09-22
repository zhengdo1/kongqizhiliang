package com.gz.xhb.Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gz.xhb.Base.XHBBaseAdapter;
import com.gz.xhb.R;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Administrator on 2016/1/14 0014.
 */
public class CommonListAdatper extends XHBBaseAdapter<JSONObject> {


    JSONObject jsonObject;

    public CommonListAdatper(Context context, List<JSONObject> list) {
        super(context, list,null);
        jsonObject = list.get(0);
    }

    public void notifyDataChanged(List<JSONObject> list) {
        this.list = list;
        super.notifyDataSetChanged();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder=null;
        if(convertView==null){
            convertView=inflater.inflate(R.layout.item_common_list, null);
            holder=new ViewHolder();

            holder.linearLayouts = new ArrayList<>();
            holder.leftQs = new ArrayList<>();
            holder.rightAs = new ArrayList<>();


//            ((TextView)convertView.findViewById(R.id.layout_itemPortList_outputcode).findViewById(R.id.tv_left_question)).setText("监控点编号");

            LinearLayout linearLayout = convertView.findViewById(R.id.ll_itemCommonList_content);

            if(jsonObject!=null){
                Iterator<String> it = jsonObject.keys();
                while(it.hasNext()) {
                    String key = it.next();
                    LinearLayout itemLinearLayout = (LinearLayout) inflater.inflate(R.layout.layout_left_question_right_answer,null);
                    holder.linearLayouts.add(itemLinearLayout);
                    holder.leftQs.add(itemLinearLayout.findViewById(R.id.tv_left_question));
                    holder.rightAs.add(itemLinearLayout.findViewById(R.id.tv_right_answer));
                    linearLayout.addView(itemLinearLayout);
                }
            }
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }
        final JSONObject commonInfo=list.get(position);
        if(jsonObject!=null) {
            Iterator<String> it = list.get(position).keys();
            int i=0;
            while (it.hasNext()) {
                String key = it.next();
                try {
                    holder.leftQs.get(i).setText(key);
                    holder.rightAs.get(i).setText(jsonObject.getString(key));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                i++;
            }
        }
        return convertView;
    }

    class ViewHolder{
        List<LinearLayout> linearLayouts;
        List<TextView> leftQs;
        List<TextView> rightAs;
    }
}

