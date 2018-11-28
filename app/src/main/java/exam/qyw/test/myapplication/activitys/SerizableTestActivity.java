package exam.qyw.test.myapplication.activitys;

import android.os.AsyncTask;
import android.view.View;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import butterknife.OnClick;
import exam.qyw.test.myapplication.R;
import exam.qyw.test.myapplication.base.BaseActivity;
import exam.qyw.test.myapplication.bean.PersonBean;
import exam.qyw.test.myapplication.utils.LogUtil;
import exam.qyw.test.myapplication.utils.OkhttpUtil;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by Author:qyw
 * on 2018/11/21.
 * QQ:448739075
 * 描述：测试序列化
 */
public class SerizableTestActivity extends BaseActivity {
    @Override
    public int innitLayout() {
        return R.layout.activity_serizable_test;
    }

    @Override
    public void innitData() {
        new AsyncTask<Integer, Integer, Integer>() {
            @Override
            protected Integer doInBackground(Integer... integers) {
                getV(100);
                return null;
            }

            @Override
            protected void onPostExecute(Integer integer) {
                super.onPostExecute(integer);
                LogUtil.i("value:");
            }
        }.execute();

    }

    private int getV(int x){
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return x;
    }

}
