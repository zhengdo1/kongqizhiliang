package com.gz.xhb_zhongtie.Adapter;

import android.content.Context;
import android.util.DebugUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gz.xhb_zhongtie.R;
import com.gz.xhb_zhongtie.util.UIUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import vb.shlv.MyHScrollView;

/**
 * Created by Vieboo on 2016/5/12.
 */
public class ListAdapter extends BaseAdapter {

    Context mContext;
    List<Map<String, String>> dataList;
    View headView;
    List<String> titleList = new ArrayList<>();

    MyHScrollView head_scroll;
    private int touchPosition = -1; //点击事件标志位
//    private List<Integer> withList;
    LinearLayout head_linearLayout;

    public ListAdapter(Context context, List<Map<String, String>> list, View head) {
        this.mContext = context;
        this.dataList = list;
        this.headView = head;
        head_scroll = (MyHScrollView) headView.findViewById(R.id.head_scroll);
        head_linearLayout = head_scroll.findViewById(R.id.ll_onlineData_addLayout);
        setTitle();
    }

    public void notifyDataChanged(List<Map<String, String>> list) {
        this.dataList = list;
        setTitle();
        this.notifyDataSetChanged();
    }

    private void setTitle() {
        head_linearLayout.removeAllViews();
        if (dataList != null && dataList.size() != 0) {
            Map<String, String> map = dataList.get(0);
            for (Map.Entry<String, String> entry : map.entrySet()) {
                if (!entry.getKey().equals("监测时间")) {
                    String title = formatStringTitle(entry.getKey());
//                    String title = entry.getKey();
                    TextView textView = getTextView(title);
                    textView.setTextColor(mContext.getResources().getColor(R.color.white));
                    head_linearLayout.addView(textView);
//                    addView(linearLayout,entry.getKey());
                }
            }
        }
    }


    private TextView getTextView(String str) {
        TextView textView = new TextView(mContext);
        int padding = UIUtil.dp2px(mContext, 10);
        textView.setPadding(padding, 0, padding, 0);
//        textView.setLayoutParams(new ViewGroup.LayoutParams(UIUtil.dp2px(mContext,120), ViewGroup.LayoutParams.WRAP_CONTENT));
        textView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        textView.setGravity(Gravity.CENTER);
        textView.setText(str);
        return textView;
    }
    private String formatStringTitle(String str){
        StringBuilder sb = new StringBuilder(str);
        if(str.contains("实测")){
            int index = str.indexOf("实测");
            if(index>2){
                str = str.substring(0,index)+"\n"+str.substring(index,str.length());
                sb.insert(index, "\n");
            }
        }
        if(str.contains("折算")){
            int index = str.indexOf("折算");
            if(index>2){
                str = str.substring(0,index)+"\n"+str.substring(index,str.length());
                sb.insert(index, "\n");
            }
        }
        if(str.contains("(")){
            int index = str.indexOf("(");
            sb.insert(index, "\n");
        }
        return sb.toString();
    }



    public int getTouchPosition() {
        return touchPosition;
    }

    public void setTouchPosition(int touchPosition) {
        this.touchPosition = touchPosition;
    }

    @Override
    public int getCount() {
        return dataList.size();
    }

    @Override
    public Object getItem(int position) {
        return dataList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
         ViewHolder holder;
        if (null == convertView) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.layout_list_item, null, false);
            holder.list_scroll =  convertView.findViewById(R.id.list_scroll);
            holder.tv_timeTitle = convertView.findViewById(R.id.tv_itemData_time);
            //把ViewHolder的ScrollerView里LinearLayout的item先添加上
            for(int j=0;j<head_linearLayout.getChildCount();j++){
                //获取每一项标题的View
                TextView textViewHead = (TextView)head_linearLayout.getChildAt(j);
                //获取标题列LinearLayout的每一项的TextView的文本作为map的key查询出来value，即为数据值
                TextView textViewAdd = getTextView("");
                //设置数据项View与对应的标题View同等的宽度
                int with = textViewHead.getMeasuredWidth();
                textViewAdd.setLayoutParams(new ViewGroup.LayoutParams(with, ViewGroup.LayoutParams.WRAP_CONTENT));
                //添加到layout中
                LinearLayout linearLayout = holder.list_scroll.findViewById(R.id.ll_itemData_data);
                linearLayout.addView(textViewAdd);
            }
            head_scroll.AddOnScrollChangedListener(new OnScrollChangedListenerImp(holder.list_scroll));
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        String time = dataList.get(position).get("监测时间");
//        if(time.equals("2018-07-19 00:00")){
//            Log.i("XHB","");
//        }
        if (time.startsWith("20")) {
            time = time.substring(5);
        }
        holder.tv_timeTitle.setText(time);
//        int i = 0;
        for(int j=0;j<head_linearLayout.getChildCount();j++){
            //获取每一项标题的View
            TextView textViewHead = (TextView)head_linearLayout.getChildAt(j);
            //获取标题列LinearLayout的每一项的TextView的文本作为map的key查询出来value，即为数据值
            LinearLayout linearLayout = holder.list_scroll.findViewById(R.id.ll_itemData_data);
            ((TextView)linearLayout.getChildAt(j)).setText(dataList.get(position).get(textViewHead.getText().toString().trim().replaceAll("\n","")));
        }

        if (position % 2 == 0) {
            holder.tv_timeTitle.setBackgroundColor(mContext.getResources().getColor(R.color.white));
            holder.list_scroll.setBackgroundColor(mContext.getResources().getColor(R.color.white));
        } else {
            holder.tv_timeTitle.setBackgroundColor(mContext.getResources().getColor(R.color.colorDataOnline));
            holder.list_scroll.setBackgroundColor(mContext.getResources().getColor(R.color.colorDataOnline));
        }

        convertView.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                setTouchPosition(position);
                return false;
            }
        });

        return convertView;
    }

    /**
     * 加ViewHolder防止数据多了滑动列表位置错乱
     */
    private class ViewHolder {
        MyHScrollView list_scroll;
        TextView tv_timeTitle;
    }

    class OnScrollChangedListenerImp implements MyHScrollView.OnScrollChangedListener {
        MyHScrollView mScrollViewArg;

        public OnScrollChangedListenerImp(MyHScrollView scrollViewar) {
            mScrollViewArg = scrollViewar;
        }

        public void onScrollChanged(int l, int t, int oldl, int oldt) {
            mScrollViewArg.smoothScrollTo(l, t);
        }
    }
}
