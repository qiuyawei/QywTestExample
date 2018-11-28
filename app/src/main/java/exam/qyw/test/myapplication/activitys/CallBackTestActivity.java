package exam.qyw.test.myapplication.activitys;

import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.OnClick;
import exam.qyw.test.myapplication.MainActivity;
import exam.qyw.test.myapplication.R;
import exam.qyw.test.myapplication.base.BaseActivity;
import exam.qyw.test.myapplication.utils.CalculateCallBack;
import exam.qyw.test.myapplication.utils.CalculateUtil;
import exam.qyw.test.myapplication.utils.ClickUtil;
import exam.qyw.test.myapplication.utils.LogUtil;


/**
 * Created by Author:qyw
 * on 2018/11/19.
 * QQ:448739075
 * 描述：
 */
public class CallBackTestActivity extends BaseActivity implements CalculateCallBack {
    @BindView(R.id.et_number1)
    EditText etOne;
    @BindView(R.id.et_number2)
    EditText etTwo;
    @BindView(R.id.tv_result)
    TextView sumResult;

    private CalculateUtil calculateUtil;
    private Handler mHandler=new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
             sumResult.setText(String.valueOf(msg.what));
            return false;
        }
    });
    @Override
    public int innitLayout() {
        return R.layout.activity_test_callback;
    }

    @Override
    public void innitData() {
        innitToolBar("测试回调");
        calculateUtil = new CalculateUtil(getmActivity(),this);
    }

    @OnClick({R.id.bt_getSum,R.id.bt_goMainAc})
    public void onClick(View view) {
        if (ClickUtil.isFastDoubleClick(view.getId())) {
            return;
        }
        switch (view.getId()) {
            case R.id.bt_getSum:
                calculate();
                break;
            case R.id.bt_goMainAc:
                Intent intent=new Intent(getmActivity(),MainActivity.class);
                startActivity(intent);
                break;
        }
    }

    private void calculate() {
        String value1 = etOne.getText().toString().trim();
        String value2 = etTwo.getText().toString().trim();
        if (value1.isEmpty() || value2.isEmpty()) {
            return;
        } else {
            int a = Integer.parseInt(value1);
            int b = Integer.parseInt(value2);
            calculateUtil.getSum(a, b);
        }
    }

    @Override
    public void getSum(final int sum) {
        if(Looper.myLooper()==Looper.getMainLooper())
        sumResult.setText(String.valueOf(sum));
        else {
            LogUtil.i("当前不再主线程："+Thread.currentThread().getName());
            Message message=new Message();
            message.what=sum;
            mHandler.sendMessage(message);
        }
    }
}
