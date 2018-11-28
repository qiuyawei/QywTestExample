package exam.qyw.test.myapplication.service;

import android.app.Notification;
import android.app.Service;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Binder;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.support.v4.app.INotificationSideChannel;

import exam.qyw.test.myapplication.R;
import exam.qyw.test.myapplication.constans.Constant;
import exam.qyw.test.myapplication.utils.LogUtil;

/**
 * Created by Author:qyw
 * on 2018/11/19.
 * QQ:448739075
 * 描述：
 */
public class MyService extends Service {
    private Binder mBinder=new INotificationSideChannel.Stub() {
        @Override
        public void notify(String s, int i, String s1, Notification notification) throws RemoteException {
            LogUtil.i("notify-MyService");

        }

        @Override
        public void cancel(String s, int i, String s1) throws RemoteException {
            LogUtil.i("cancel-MyService");

        }

        @Override
        public void cancelAll(String s) throws RemoteException {
            LogUtil.i("cancelAll-MyService");

        }
    };

    @Override
    public void onCreate() {
        super.onCreate();
        LogUtil.i("onCreate-MyService");
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        LogUtil.i("onStartCommand-MyService");
        if(intent!=null){
            String value=intent.getStringExtra("value");
            LogUtil.i("onStartCommand-MyService:"+value);

        }
        return super.onStartCommand(intent, flags, startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        LogUtil.i("onBind:MyService");
        //设置为前台服务
        Notification.Builder builder = new Notification.Builder(this);
        builder.setContentTitle("播放音乐中..").setContentText("Diamonds")
                .setWhen(System.currentTimeMillis()).setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher));
        Notification notifation = builder.getNotification();
        startForeground(2, notifation);

        return mBinder;

    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        LogUtil.i("onDestroy-MyService");
    }
}
