package exam.qyw.test.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.io.File;
import java.util.ArrayList;

import butterknife.OnClick;
import exam.qyw.test.myapplication.R;
import exam.qyw.test.myapplication.activitys.AnimationTestActivity;
import exam.qyw.test.myapplication.activitys.CallBackTestActivity;
import exam.qyw.test.myapplication.activitys.EventBusTestActivity;
import exam.qyw.test.myapplication.activitys.JavaStreamTestActivity;
import exam.qyw.test.myapplication.activitys.MediaTestActivity;
import exam.qyw.test.myapplication.activitys.OkttpTestActivity;
import exam.qyw.test.myapplication.activitys.SeachActivity;
import exam.qyw.test.myapplication.activitys.SerizableTestActivity;
import exam.qyw.test.myapplication.activitys.ServiceTestActivity;
import exam.qyw.test.myapplication.activitys.SingInstanceActivity;
import exam.qyw.test.myapplication.activitys.SocketTestActivity;
import exam.qyw.test.myapplication.activitys.WebViewActivity;
import exam.qyw.test.myapplication.base.BaseActivity;
import exam.qyw.test.myapplication.bean.PersonBean;
import exam.qyw.test.myapplication.utils.ClickUtil;
import exam.qyw.test.myapplication.utils.LogUtil;

public class MainActivity extends BaseActivity {

    private Intent mIntent;
    @Override
    public int innitLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void innitData() {
        mIntent=new Intent();
    }

    @OnClick({R.id.bt_testCallBack, R.id.bt_testService, R.id.bt_mediaTest, R.id.bt_Animation, R.id.bt_webView
            , R.id.bt_testSingleInstance, R.id.bt_testOkhttp, R.id.bt_testSocket, R.id.bt_testSerizable,R.id.bt_testEventBus
            ,R.id.bt_testPrinterWriter,R.id.bt_testSearchWithPinYin})
    public void onClick(View view) {
        if (ClickUtil.isFastDoubleClick(view.getId())) {
            return;
        }

        switch (view.getId()) {
            case R.id.bt_testCallBack:
                Intent intent = new Intent(getmActivity(), CallBackTestActivity.class);
                startActivity(intent);
                break;
            case R.id.bt_testService:
                Intent intent1 = new Intent(getmActivity(), ServiceTestActivity.class);
                startActivity(intent1);
                break;
            case R.id.bt_mediaTest:
                Intent intent2 = new Intent(getmActivity(), MediaTestActivity.class);
                startActivity(intent2);
                break;
            case R.id.bt_Animation:
                Intent intent3 = new Intent(getmActivity(), AnimationTestActivity.class);
                startActivity(intent3);
                break;
            case R.id.bt_webView:
                Intent intent4 = new Intent(getmActivity(), WebViewActivity.class);
                startActivity(intent4);
                break;
            case R.id.bt_testSingleInstance:
                Intent intent5 = new Intent(getmActivity(), SingInstanceActivity.class);
                startActivity(intent5);
                break;
            case R.id.bt_testOkhttp:
                Intent intent6 = new Intent(getmActivity(), OkttpTestActivity.class);
                startActivity(intent6);
                break;
            case R.id.bt_testSocket:
                Intent intent7 = new Intent(getmActivity(), SocketTestActivity.class);
                startActivity(intent7);
                break;
            case R.id.bt_testSerizable:
                Intent intent8 = new Intent(getmActivity(), SerizableTestActivity.class);
                intent8.putExtra("bean",getPersonBean());
                startActivity(intent8);
                break;
            case R.id.bt_testEventBus:
                mIntent.setClass(getmActivity(), EventBusTestActivity.class);
                startActivity(mIntent);
                break;
            case R.id.bt_testPrinterWriter:
                mIntent.setClass(getmActivity(), JavaStreamTestActivity.class);
                startActivity(mIntent);
                break;
            case R.id.bt_testSearchWithPinYin:
                mIntent.setClass(getmActivity(), SeachActivity.class);
                startActivity(mIntent);
                break;
        }
    }


    private ArrayList<PersonBean> getPersonBean() {
        ArrayList<PersonBean> personBeans = new ArrayList<>();
//        for (int i = 0; i < 300; i++) {
//            PersonBean bean = new PersonBean();
//            bean.setName("bean" + i);
//            bean.setHomeAddress("河南省商丘梁园区双八镇朱楼大队小吴庄村27号。。。。");
//            personBeans.add(bean);
//        }
        return personBeans;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LogUtil.i("onCreate-MainActivity");

    }

    @Override
    protected void onStart() {
        super.onStart();
        LogUtil.i("onStart-MainActivity");
    }

    @Override
    protected void onResume() {
        super.onResume();
        LogUtil.i("onResume-MainActivity");

    }

    @Override
    protected void onPause() {
        super.onPause();
        LogUtil.i("onPause-MainActivity");

    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        LogUtil.i("onNewIntent-MainActivity");
    }
}
