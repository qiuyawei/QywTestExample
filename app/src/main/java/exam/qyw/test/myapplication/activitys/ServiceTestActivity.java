package exam.qyw.test.myapplication.activitys;

import android.app.ActivityManager;
import android.app.Notification;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.graphics.drawable.BitmapDrawable;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.List;

import butterknife.OnClick;
import exam.qyw.test.myapplication.R;
import exam.qyw.test.myapplication.base.BaseActivity;
import exam.qyw.test.myapplication.constans.Constant;
import exam.qyw.test.myapplication.service.MyService;
import exam.qyw.test.myapplication.utils.ClickUtil;
import exam.qyw.test.myapplication.utils.LogUtil;

public class ServiceTestActivity extends BaseActivity {
    private Intent intent;
    private MyService myService;
    @Override
    public int innitLayout() {
        return R.layout.activity_service_test;
    }

    @Override
    public void innitData() {
        innitToolBar("测试 SERVICE");
        intent = new Intent();

        IntentFilter intentFilter=new IntentFilter();
        intentFilter.addAction(Constant.BROADCAST_ACTION);
        registerReceiver(myBroadcastReceiver,intentFilter);
    }

    @OnClick({R.id.bt_startService, R.id.bt_bindService,R.id.bt_stopService})
    public void onClick(View view) {
        if (ClickUtil.isFastDoubleClick(view.getId())) {
            return;
        }
        switch (view.getId()) {
            case R.id.bt_startService:
//                通过Intent 启动Service
                intent.setClass(getmActivity(), MyService.class);
                intent.putExtra("value", "启动了Service");
                startService(intent);
                break;
            case R.id.bt_bindService:
//                bindService
                intent.setClass(getmActivity(), MyService.class);
                bindService(intent, serviceConnection, BIND_AUTO_CREATE);
                break;
            case R.id.bt_stopService:
//                stopService
//                intent.setClass(getmActivity(),MyService.class);
//                intent.setAction("com.qyw.test.myapplication");
//                终止service要和启动service的Intent是同一个对象
//                Intent mIntent=new Intent(this,MyService.class);
                boolean b = stopService(intent);
                LogUtil.i("b:"+b);
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        unbindService(serviceConnection);
        unregisterReceiver(myBroadcastReceiver);
    }

    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            LogUtil.i("onServiceConnected");
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            LogUtil.i("onServiceDisconnected");
        }
    };
    private BroadcastReceiver myBroadcastReceiver=new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if(intent.getAction().equals(Constant.BROADCAST_ACTION)){
                String value=intent.getStringExtra("value");
//                Toast.makeText(this,value,Toast.LENGTH_SHORT).show();
                Toast.makeText(getmActivity(),value,Toast.LENGTH_SHORT).show();
            }
        }
    };


    @Override
    protected void onResume() {
        super.onResume();
        ActivityManager activityManager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> list = activityManager.getRunningTasks(3);
        for (ActivityManager.RunningTaskInfo taskInfo:list) {
            LogUtil.i("taskInfo.numActivities:" + taskInfo.numActivities);
            LogUtil.i("taskInfo.numRunning:" + taskInfo.numRunning);
        }

    }
}
