package com.gz.xhb_zhongtie.util;

import android.support.annotation.ColorInt;
import android.support.v4.app.FragmentActivity;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.gz.xhb_zhongtie.R;
import com.jzxiang.pickerview.TimePickerDialog;
import com.jzxiang.pickerview.data.Type;
import com.jzxiang.pickerview.listener.OnDateSetListener;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * Created by zdj on 2018/7/1.
 */
public class TimePickerDialogUtil {
    private TimePickerDialog mDialogAll;
    //    static OnDateSetListener onDateSetListener;
    private long thiryYears = 30L * 365 * 1000 * 60 * 60 * 24L;
    private final long dayMillseconds = 24L * 60 * 60 * 1000L;
    private View.OnClickListener onClickListener;
    private SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    private long sectionDayMax = -1L;

    public TimePickerDialogUtil() {
    }

    public static TimePickerDialogUtil newInstence() {
        return new TimePickerDialogUtil();
    }

    public void setTextViewToTimeView(FragmentActivity appCompatActivity, final TextView textView, OnDateSetListener onDateSetListener, String title, final Type type) {
        if (textView instanceof EditText) {
            textView.setInputType(InputType.TYPE_NULL);
        }
        if (onDateSetListener == null) {
            onDateSetListener = new OnDateSetListener() {
                @Override
                public void onDateSet(TimePickerDialog timePickerView, long millseconds) {
                    textView.setText(getDateToString(millseconds, getDateFormat(type)));
                }
            };
        }
        setOnClickListener(appCompatActivity, onDateSetListener, title, textView, type);
        textView.setOnClickListener(onClickListener);


    }

    public void setBeginTime(final FragmentActivity appCompatActivity, final TextView begin, final TextView end, final Type type, final String time) {
        setTextViewToTimeView(appCompatActivity, begin, new OnDateSetListener() {
            @Override
            public void onDateSet(TimePickerDialog timePickerView, long millseconds) {
                long endTimemillseconds = (getStringToDate(end.getText().toString(), getDateFormat(type))).getTime();
                if (time == null || time.equals("")) {
                    begin.setText(getDateToString(millseconds, getDateFormat(type)));
                } else {
                    begin.setText(time);
                }
                if (end.getText() != null && !end.getText().equals("")) {
                    if (endTimemillseconds < millseconds) {
                        end.setText(begin.getText());
                    } else if (sectionDayMax != -1L && (millseconds < (endTimemillseconds - sectionDayMax * dayMillseconds))) {
//                        ArmsUtils.makeText(appCompatActivity, "目前仅支持跨度" + sectionDayMax + "天的查询");
                        ToastTool.ShowLongToast(appCompatActivity,"目前仅支持跨度" + sectionDayMax + "天的查询");
                        end.setText(DateUtils.getDateToString(millseconds + sectionDayMax * dayMillseconds, getDateFormat(type)));
                    }
                }
                if (onTimePickedListener != null)
                    onTimePickedListener.onTimePicked();
            }
        }, "开始时间", type);
    }

    public void setEndTime(final FragmentActivity appCompatActivity, final TextView begin, final TextView end, final Type type, final String time) {
        setTextViewToTimeView(appCompatActivity, end, new OnDateSetListener() {
            @Override
            public void onDateSet(TimePickerDialog timePickerView, long millseconds) {
                long beginTimemillseconds = (getStringToDate(begin.getText().toString(), getDateFormat(type))).getTime();
                if (time == null || time.equals("")) {
                    end.setText(getDateToString(millseconds, getDateFormat(type)));
                } else {
                    end.setText(time);
                }
                if (begin.getText() != null && !begin.getText().equals("")) {
                    if (beginTimemillseconds > millseconds) {
                        begin.setText(end.getText());
                    } else if (sectionDayMax != -1L && (millseconds > (beginTimemillseconds + sectionDayMax * dayMillseconds))) {
//                        ArmsUtils.makeText(appCompatActivity, "目前仅支持跨度" + sectionDayMax + "天的查询");
                        ToastTool.ShowLongToast(appCompatActivity,"目前仅支持跨度" + sectionDayMax + "天的查询");
                        begin.setText(DateUtils.getDateToString(millseconds - sectionDayMax * dayMillseconds, getDateFormat(type)));
                    }
                }
                if (onTimePickedListener != null)
                    onTimePickedListener.onTimePicked();
            }
        }, "截止时间", type);
    }

    public void setBeginToEndTime(FragmentActivity appCompatActivity, final TextView begin, final TextView end, final Type type) {
        setBeginToEndTime(appCompatActivity, begin, end, type, null);
    }

    public void setBeginToEndTime(FragmentActivity appCompatActivity, final TextView begin, final TextView end, final Type type, long sectionDayMax) {
        setBeginToEndTime(appCompatActivity, begin, end, type, null,-1L);
    }

    public void setBeginToEndTime(FragmentActivity appCompatActivity, final TextView begin, final TextView end, final Type type, OnTimePickedListener onTimePickedListener) {
        setBeginTime(appCompatActivity, begin, end, type, "");
        setEndTime(appCompatActivity, begin, end, type, "");
        this.onTimePickedListener = onTimePickedListener;
    }

    public void setBeginToEndTime(FragmentActivity appCompatActivity, final TextView begin, final TextView end, final Type type, OnTimePickedListener onTimePickedListener, long sectionDayMax) {
        setBeginToEndTime(appCompatActivity, begin, end, type, onTimePickedListener);
        this.sectionDayMax = sectionDayMax;
    }

    OnTimePickedListener onTimePickedListener;

    public interface OnTimePickedListener {
        void onTimePicked();
    }

    private static SimpleDateFormat getDateFormat(Type type) {
        if (type.equals(Type.ALL)) {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm");
        } else if (type.equals(Type.YEAR_MONTH_DAY)) {
            return new SimpleDateFormat("yyyy-MM-dd");
        } else if (type.equals(Type.YEAR_MONTH)) {
            return new SimpleDateFormat("yyyy-MM");
        } else if (type.equals(Type.YEAR)) {
            return new SimpleDateFormat("yyyy");
        } else if (type.equals(Type.MONTH_DAY_HOUR_MIN)) {
            return new SimpleDateFormat("MM-dd HH:mm");
        }
        return null;
    }

    public void setOnClickListener(final FragmentActivity appCompatActivity, final OnDateSetListener onDateSetListener, final String title, final TextView textView, final Type type) {
//        @ColorInt final int colorPrimary = Utils.getThemeColorPramary(appCompatActivity);
        onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDialogAll = new TimePickerDialog.Builder()
                        .setCallBack(onDateSetListener)
                        .setCancelStringId("取消")
                        .setSureStringId("确定")
                        .setTitleStringId(title)
                        .setYearText("年")
                        .setMonthText("月")
                        .setDayText("日")
                        .setHourText("时")
                        .setMinuteText("分")
                        .setCyclic(false)
                        .setMinMillseconds(System.currentTimeMillis() - thiryYears)
                        .setMaxMillseconds(System.currentTimeMillis())
//                        .setCurrentMillseconds(System.currentTimeMillis())
                        .setCurrentMillseconds(stringToLong(textView.getText().toString(), getDateFormat(type)))
//                        .setThemeColor(colorPrimary)
                        .setThemeColor(appCompatActivity.getResources().getColor(R.color.colorPrimary))
                        .setType(type)
                        .setWheelItemTextNormalColor(appCompatActivity.getResources().getColor(R.color.black))
                        .setWheelItemTextSelectorColor(appCompatActivity.getResources().getColor(R.color.colorPrimary))
                        .setWheelItemTextSize(16)
                        .build();
                mDialogAll.show(appCompatActivity.getSupportFragmentManager(), "all");
            }

        };
    }

    public static String getDateToString(long time, SimpleDateFormat sf) {
        Date d = new Date(time);
        return sf.format(d);
    }

    public static String getTodayDateStr(Date date, SimpleDateFormat sdf) {
        SimpleDateFormat todaySdf = new SimpleDateFormat("yyyy-MM-dd");
        String todayStr = todaySdf.format(date);
        try {
            todayStr = todayStr + " 00:00";
            Date todayDate = sdf.parse(todayStr);
            todayStr = sdf.format(todayDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return todayStr;
    }


    public static Date getStringToDate(String str, SimpleDateFormat sdf) {
        Date date = null;
        try {
            date = sdf.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    public static long stringToLong(String str, SimpleDateFormat sdf) {
        Date date = null;
        try {
            date = sdf.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (date == null) {
            return 0;
        } else {
            long currentTime = date.getTime(); // date类型转成long类型
            return currentTime;
        }
    }


}
