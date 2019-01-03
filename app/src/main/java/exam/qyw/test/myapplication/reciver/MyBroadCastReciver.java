package exam.qyw.test.myapplication.reciver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import exam.qyw.test.myapplication.inter.Gloable;
import exam.qyw.test.myapplication.inter.MyReciverCallBack;
import exam.qyw.test.myapplication.utils.LogUtil;
import exam.qyw.test.myapplication.utils.NetConnectUtil;

public class MyBroadCastReciver extends BroadcastReceiver {
    private MyReciverCallBack callBack;

    public MyBroadCastReciver() {
    }

    public MyBroadCastReciver(MyReciverCallBack myReciverCallBack) {
        this.callBack = myReciverCallBack;
    }

    @Override

    public void onReceive(Context context, Intent intent) {
        callBack.afterReciver(intent);
        NetConnectUtil.checkNet(context);
    }
}
