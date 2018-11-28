package exam.qyw.test.myapplication.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;

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
public class EventBusSendActivity extends BaseActivity {

    @Override
    public int innitLayout() {
        return R.layout.activity_eventbus_send;
    }

    @Override
    public void innitData() {
    }

    private StringBuilder stringBuilder=new StringBuilder();
    public void onSend(View view){
        String content="你好，我是EventBus传过来的消息\n";
        for(int i=0;i<10;i++){
            stringBuilder.append(content);
        }
        EventTestBean bean=new EventTestBean();
        bean.setName(stringBuilder.toString());
        EventBus.getDefault().post(bean);
        finish();
    }

}
