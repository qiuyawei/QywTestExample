package exam.qyw.test.myapplication.utils;

import android.content.Context;
import android.content.Intent;

import exam.qyw.test.myapplication.constans.Constant;

/**
 * Created by Author:qyw
 * on 2018/11/19.
 * QQ:448739075
 * 描述：
 */
public class CalculateUtil {
    private  CalculateCallBack mCalculateCallBack;
    private Context mContext;
    public CalculateUtil(Context context,CalculateCallBack calculateCallBack){
        this.mContext=context;
        this.mCalculateCallBack=calculateCallBack;
    }
    //计算是耗时操作，所以需要开启线程
    public  void getSum(final int a, final int b){
        new Thread(new Runnable() {
            @Override
            public void run() {
                int sum=a+b;
                mCalculateCallBack.getSum(sum);
            }
        },"caculateThread").start();

    }


}
