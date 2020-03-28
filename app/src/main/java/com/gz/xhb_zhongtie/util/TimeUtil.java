//package com.gz.xhb_zhongtie.util;
//
//
//import android.support.v7.app.AppCompatActivity;
//import android.view.View;
//import android.widget.TextView;
//
//import com.gz.xhb_zhongtie.MyApplication.MyApplication;
//import com.gz.xhb_zhongtie.R;
//import com.jzxiang.pickerview.TimePickerDialog;
//import com.jzxiang.pickerview.data.Type;
//import com.jzxiang.pickerview.listener.OnDateSetListener;
//
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//
///**
// * Created by zdj on 2018/7/1.
// */
//public class TimeUtil {
//    static TimePickerDialog mDialogAll;
//    //    static OnDateSetListener onDateSetListener;
//    static long thiryYears = 30L * 365 * 1000 * 60 * 60 * 24L;
//    static View.OnClickListener onClickListener;
//
//
//    public static void setTextViewToTimeView(AppCompatActivity appCompatActivity, TextView textView, OnDateSetListener onDateSetListener, String title) {
//        if (onDateSetListener == null) {
//            onDateSetListener = new OnDateSetListener() {
//                @Override
//                public void onDateSet(TimePickerDialog timePickerView, long millseconds) {
//                    textView.setText(getDateToString(millseconds));
//                }
//            };
//        }
//        setOnClickListener(appCompatActivity, onDateSetListener, title, textView);
//        textView.setOnClickListener(onClickListener);
//    }
//
//
//    public static void setBeginToEndTime(AppCompatActivity appCompatActivity, TextView begin, TextView end) {
//        setTextViewToTimeView(appCompatActivity, begin, new OnDateSetListener() {
//            @Override
//            public void onDateSet(TimePickerDialog timePickerView, long millseconds) {
//                begin.setText(getDateToString(millseconds));
//                if (end.getText() != null && end.getText() != "") {
//                    if (getStringToDate(end.getText().toString()).getTime() < millseconds) {
//                        end.setText(begin.getText());
//                    }
//                }
//            }
//        }, "开始时间");
//        setTextViewToTimeView(appCompatActivity, end, new OnDateSetListener() {
//            @Override
//            public void onDateSet(TimePickerDialog timePickerView, long millseconds) {
//                end.setText(getDateToString(millseconds));
//                if (begin.getText() != null && begin.getText() != "") {
//                    if (getStringToDate(begin.getText().toString()).getTime() > millseconds) {
//                        begin.setText(end.getText());
//                    }
//                }
//            }
//        }, "截止时间");
//    }
//
//    public static void setOnClickListener(AppCompatActivity appCompatActivity, OnDateSetListener onDateSetListener, String title, TextView textView) {
//        onClickListener = new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                mDialogAll = new TimePickerDialog.Builder()
//                        .setCallBack(onDateSetListener)
//                        .setCancelStringId("取消")
//                        .setSureStringId("确定")
//                        .setTitleStringId(title)
//                        .setYearText("年")
//                        .setMonthText("月")
//                        .setDayText("日")
//                        .setHourText("时")
//                        .setMinuteText("分")
//                        .setCyclic(false)
//                        .setMinMillseconds(System.currentTimeMillis() - thiryYears)
//                        .setMaxMillseconds(System.currentTimeMillis())
////                        .setCurrentMillseconds(System.currentTimeMillis())
//                        .setCurrentMillseconds(stringToLong(textView.getText().toString()))
//                        .setThemeColor(MyApplication.getContext().getResources().getColor(R.color.timepicker_dialog_bg))
//                        .setType(Type.ALL)
//                        .setWheelItemTextNormalColor(MyApplication.getContext().getResources().getColor(R.color.timetimepicker_default_text_color))
//                        .setWheelItemTextSelectorColor(MyApplication.getContext().getResources().getColor(R.color.timepicker_toolbar_bg))
//                        .setWheelItemTextSize(12)
//                        .build();
//                mDialogAll.show(appCompatActivity.getSupportFragmentManager(), "all");
//            }
//
//        };
//    }
//
//
//    public static String getDateToString(long time) {
//        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
//        Date d = new Date(time);
//        return sf.format(d);
//    }
//
//    public static String getTodayDateStr(Date date) {
//        SimpleDateFormat todaySdf = new SimpleDateFormat("yyyy-MM-dd");
//        String todayStr = todaySdf.format(date);
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
//        try {
//            todayStr = todayStr + " 00:00";
//            Date todayDate = sdf.parse(todayStr);
//            todayStr = sdf.format(todayDate);
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        return todayStr;
//    }
//
//
//    public static Date getStringToDate(String str) {
//        Date date = null;
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
//        try {
//            date = sdf.parse(str);
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        return date;
//    }
//
////    public static long getStringToLong(String str) {
////        long dateLong = 0l;
////        Date date = null;
////        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
////        try {
////            date = sdf.parse(str);
////            dateLong = date.getTime();
////        } catch (ParseException e) {
////            e.printStackTrace();
////        }
////        return dateLong;
////    }
//
//    public static long stringToLong(String str){
//        Date date = null;
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
//        try {
//            date = sdf.parse(str);
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        if (date == null) {
//            return 0;
//        } else {
//            long currentTime = date.getTime(); // date类型转成long类型
//            return currentTime;
//        }
//    }
//
//
//}
