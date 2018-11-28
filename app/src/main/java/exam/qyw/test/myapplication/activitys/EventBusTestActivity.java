package exam.qyw.test.myapplication.activitys;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import exam.qyw.test.myapplication.R;
import exam.qyw.test.myapplication.base.BaseActivity;
import exam.qyw.test.myapplication.bean.EventTestBean;
import exam.qyw.test.myapplication.utils.LogUtil;

/**
 * Created by Author:qyw
 * on 2018/11/27.
 * QQ:448739075
 * 描述：
 */
public class EventBusTestActivity extends BaseActivity {
    @BindView(R.id.tv_value)
    TextView tv_value;

    @Override
    public int innitLayout() {
        return R.layout.activity_eventbus_test;
    }

    @Override
    public void innitData() {
        EventBus.getDefault().register(this);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    private StringBuilder stringBuilder = new StringBuilder();

    public void onJump(View view) {
        Intent intent = new Intent(getmActivity(), EventBusSendActivity.class);
        startActivity(intent);
    }

    //回调接收
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(EventTestBean testBean) {
        if (testBean != null) {
            tv_value.setText(testBean.getName());
        }
    }
}
