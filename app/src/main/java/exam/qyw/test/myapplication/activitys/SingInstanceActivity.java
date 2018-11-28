package exam.qyw.test.myapplication.activitys;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import java.util.List;

import exam.qyw.test.myapplication.R;
import exam.qyw.test.myapplication.base.BaseActivity;
import exam.qyw.test.myapplication.utils.LogUtil;

/**
 * Created by Author:qyw
 * on 2018/11/21.
 * QQ:448739075
 * 描述：
 */
public class SingInstanceActivity extends BaseActivity {
    @Override
    public int innitLayout() {
        return R.layout.activity_sing_instance_test;
    }

    @Override
    public void innitData() {
        innitToolBar("测试启动模式SingleInstance");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LogUtil.i("onCreate_SingInstanceActivity");

    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        LogUtil.i("onNewIntent_SingInstanceActivity");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        LogUtil.i("onDestroy_SingInstanceActivity");

    }

    @Override
    protected void onResume() {
        super.onResume();
        ActivityManager activityManager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> list = activityManager.getRunningTasks(3);
        for (ActivityManager.RunningTaskInfo taskInfo:list) {
            LogUtil.i("taskInfo.numActivities:" + taskInfo.numActivities);
            LogUtil.i("taskInfo.numRunning:" + taskInfo.numRunning);
        }
//        ActivityManager.RunningTaskInfo runningTaskInfo=activityManager.getRunningTasks(1).get(0);
//        String runName=runningTaskInfo.topActivity.getClassName();
//        String packgName=runningTaskInfo.topActivity.getPackageName();
//        LogUtil.i("runName:"+runName);
//        LogUtil.i("packgName:"+packgName);

    }
}
