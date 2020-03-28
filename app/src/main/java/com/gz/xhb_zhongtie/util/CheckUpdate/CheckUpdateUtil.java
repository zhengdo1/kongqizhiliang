//package com.gz.xhb_zhongtie.util.CheckUpdate;
//
//import android.app.Activity;
//import android.support.v7.app.AlertDialog;
//import android.text.method.ScrollingMovementMethod;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.widget.TextView;
//
//import com.google.gson.Gson;
//import com.google.gson.reflect.TypeToken;
//import com.gz.xhb_zhongtie.R;
//import com.gz.xhb_zhongtie.util.Utils;
//import com.pgyersdk.update.PgyUpdateManager;
//import com.pgyersdk.update.UpdateManagerListener;
//
//import java.lang.reflect.Type;
//
///**
// * Created by zdj on 2019/10/22.
// */
//public class CheckUpdateUtil {
//
//    public static void checkUpdateFromPgyer(Activity activity, boolean isManual) {
//        PgyUpdateManager.setIsForced(false); //设置是否强制更新。true为强制更新；false为不强制更新（默认值）。
//        PgyUpdateManager.register(activity,
//                new UpdateManagerListener() {
//
//                    @Override
//                    public void onUpdateAvailable(final String result) {
//                        try {
//                            Gson gson = new Gson();
//                            Type type = new TypeToken<PgyerUpdateVersionInfo>() {
//                            }.getType();
//                            PgyerUpdateVersionInfo pgyerUpdateVersionInfo = gson.fromJson(result, type);
//                            PgyerUpdateVersionInfo.DataBean updateData = pgyerUpdateVersionInfo.getData();
//                            String versionName = "";
//                            try {
//                                versionName = VersionUtil.getVersionName(activity);
//                            } catch (Exception e) {
//                                e.printStackTrace();
//                            }
//                            if (!versionName.equals(updateData.getVersionName())) {
//                                boolean isForce = updateData.getVersionName().endsWith("0");
//                                {
////                                    String content = String.format("最新版本：%1$s\n\n更新内容\n%3$s", updateData.getVersionName(), size, info.updateContent);
//                                    String content = String.format("最新版本：%s\n\n更新内容\n%s", updateData.getVersionName(), updateData.getReleaseNote());
//                                    LayoutInflater inflater = LayoutInflater.from(activity);
//                                    View view = inflater.inflate(R.layout.common_business_dialog_version_update, null);
//                                    final AlertDialog dialog = new AlertDialog.Builder(activity).create();
//
//                                    TextView tvTitle = view.findViewById(R.id.tv_dialog_title);
//                                    TextView tvContent = view.findViewById(R.id.tv_change);
//                                    TextView btnUpdate = view.findViewById(R.id.btn_update);
//                                    TextView btnCancel = view.findViewById(R.id.btn_cancel);
//                                    TextView btnIgnore = view.findViewById(R.id.btn_ignore);
//                                    tvTitle.setText("应用更新");
//                                    tvContent.setText(content);
//
//
//                                    float density = activity.getResources().getDisplayMetrics().density;
////                            TextView tv = new TextView(context);
//                                    tvContent.setMovementMethod(new ScrollingMovementMethod());
//                                    tvContent.setVerticalScrollBarEnabled(true);
//                                    tvContent.setTextSize(14);
//                                    tvContent.setMaxHeight((int) (250 * density));
//
////                            dialog.setView(tv, (int) (25 * density), (int) (15 * density), (int) (25 * density), 0);
//
//                                    TextView.OnClickListener onClickListener = new View.OnClickListener() {
//                                        @Override
//                                        public void onClick(View v) {
//                                            int i = v.getId();
//                                            if (i == R.id.btn_update) {
//                                                startDownloadTask(
//                                                        activity,
//                                                        updateData.getDownloadURL());
//                                            } else if (i == R.id.btn_cancel) {
////                                            nextTime = now + 1 * 60 * 60 * 1000;
//                                            }
//                                            dialog.dismiss();
//                                        }
//                                    };
//                                    btnUpdate.setOnClickListener(onClickListener);
//                                    btnCancel.setOnClickListener(onClickListener);
//                                    btnIgnore.setOnClickListener(onClickListener);
//
//                                    if (isForce) {
//                                        tvContent.setText("您需要更新应用才能继续使用\n\n" + content);
//                                        btnCancel.setVisibility(View.GONE);
//                                    } else {
//                                        tvContent.setText(content);
//                                    }
//                                    dialog.setView(view);
//                                    dialog.setCancelable(false);
//                                    dialog.setCanceledOnTouchOutside(false);
//                                    dialog.show();
//                                }
//                            } else if (isManual) {
//                                Utils.makeText(activity, "当前已经是最新版本");
//                            }
//                        } catch (Exception e) {
//                            e.printStackTrace();
//                        }
//
//
//                    }
//
//                    @Override
//                    public void onNoUpdateAvailable() {
//                        if (isManual) {
//                            Utils.makeText(activity, "当前已经是最新版本");
//                        }
//                    }
//                });
//    }
//
//}
