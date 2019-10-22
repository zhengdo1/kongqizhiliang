package com.gz.xhb_zhongtie.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gz.xhb_zhongtie.Activity.PortListActivity;
import com.gz.xhb_zhongtie.Base.XHBBaseAdapter;
import com.gz.xhb_zhongtie.MVP.Model.Entity.PortInfoData;
import com.gz.xhb_zhongtie.R;

import java.util.List;

/**
 * Created by Administrator on 2016/1/14 0014.
 */
public class PortListAdatper extends XHBBaseAdapter<PortInfoData> {


    public PortListAdatper(Context context, List<PortInfoData> list, Class aClass) {
        super(context, list, aClass);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder=null;
        if(convertView==null){
            convertView=inflater.inflate(R.layout.item_port_list, null);
            holder=new ViewHolder();

            ((TextView)convertView.findViewById(R.id.layout_itemPortList_outputcode).findViewById(R.id.tv_left_question)).setText("监控点编号");
            ((TextView)convertView.findViewById(R.id.layout_itemPortList_outputname).findViewById(R.id.tv_left_question)).setText("监控点名称");
            ((TextView)convertView.findViewById(R.id.layout_itemPortList_mn).findViewById(R.id.tv_left_question)).setText("mn号");
            ((TextView)convertView.findViewById(R.id.layout_itemPortList_outputtype).findViewById(R.id.tv_left_question)).setText("监控点类型");
            ((TextView)convertView.findViewById(R.id.layout_itemPortList_outputpointtype).findViewById(R.id.tv_left_question)).setText("位置类型");
            ((TextView)convertView.findViewById(R.id.layout_itemPortList_ifsintering).findViewById(R.id.tv_left_question)).setText("是否烧结");
            ((TextView)convertView.findViewById(R.id.layout_itemPortList_position).findViewById(R.id.tv_left_question)).setText("监测点位置");

            holder.outputcode = (TextView) convertView.findViewById(R.id.layout_itemPortList_outputcode).findViewById(R.id.tv_right_answer);
            holder.outputname = (TextView) convertView.findViewById(R.id.layout_itemPortList_outputname).findViewById(R.id.tv_right_answer);
            holder.mn = (TextView) convertView.findViewById(R.id.layout_itemPortList_mn).findViewById(R.id.tv_right_answer);
            holder.outputtype = (TextView) convertView.findViewById(R.id.layout_itemPortList_outputtype).findViewById(R.id.tv_right_answer);
            holder.outputpointtype = (TextView) convertView.findViewById(R.id.layout_itemPortList_outputpointtype).findViewById(R.id.tv_right_answer);
            holder.ifsintering = (TextView) convertView.findViewById(R.id.layout_itemPortList_ifsintering).findViewById(R.id.tv_right_answer);
            holder.position = (TextView) convertView.findViewById(R.id.layout_itemPortList_position).findViewById(R.id.tv_right_answer);
            convertView.setTag(holder);
        }
        holder=(ViewHolder)convertView.getTag();
        final PortInfoData portInfo=list.get(position);
        holder.outputcode.setText(portInfo.getOutputcode());
        holder.outputname.setText(portInfo.getOutputname());
        holder.mn.setText(portInfo.getMN());
        holder.outputtype.setText(portInfo.getOutputtype());
        holder.outputpointtype.setText(portInfo.getOutputpointtype());
        holder.ifsintering.setText(portInfo.getIfsintering());
        holder.position.setText(portInfo.getAirposition());
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(aClass!=null){
                    Intent intent= new Intent(context,aClass);
                    intent.putExtra("outputCode",list.get(position).getOutputcode());
                    intent.putExtra("typeName", PortListActivity.typeName);
                    intent.putExtra("outputname", list.get(position).getOutputname());
                    context.startActivity(intent);
                }
            }
        });

        return convertView;
    }

    class ViewHolder{
        TextView outputcode;
        TextView outputname;
        TextView mn;
        TextView outputtype;
        TextView outputpointtype;
        TextView ifsintering;
        TextView position;
    }
}

