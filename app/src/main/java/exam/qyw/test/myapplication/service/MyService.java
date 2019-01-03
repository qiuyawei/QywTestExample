package exam.qyw.test.myapplication.service;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Binder;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.support.v4.app.INotificationSideChannel;
import android.widget.RemoteViews;

import java.util.logging.Logger;

import exam.qyw.test.myapplication.IMyAidlInterface;
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

    private MyBinder binder = new MyBinder();
    private static final int NOTIFICATION_FLAG = 1;
    public static class MyBinder extends Binder{
        public void startDownload(){

            //下载任务
            new Thread(new Runnable() {
                @Override
                public void run() {
                    LogUtil.i("数据下载中。。。。。。。");
                }
            }).start();
        }
    }


    @Override
    public void onCreate() {
        super.onCreate();
        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        Notification myNotify = new Notification();
        myNotify.icon = R.mipmap.ic_launcher;
        myNotify.tickerText = "TickerText:您有新短消息，请注意查收！";
        myNotify.when = System.currentTimeMillis();
        myNotify.flags = Notification.FLAG_NO_CLEAR;// 不能够自动清除
        RemoteViews rv = new RemoteViews(getPackageName(),
                R.layout.my_notification);
        rv.setTextViewText(R.id.text_content, "hello wrold!");
        myNotify.contentView = rv;
        Intent intent = new Intent(Intent.ACTION_MAIN);
        PendingIntent contentIntent = PendingIntent.getActivity(this, 1,
                intent, 1);
        myNotify.contentIntent = contentIntent;
        manager.notify(NOTIFICATION_FLAG, myNotify);
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mStub;
    }
    /**AIDL的使用*/
    IMyAidlInterface.Stub mStub = new IMyAidlInterface.Stub() {
        @Override
        public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {

        }

        @Override
        public int plus(int a, int b) throws RemoteException {
            return a + b;
        }
    };
}

