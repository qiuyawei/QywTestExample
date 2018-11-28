package exam.qyw.test.myapplication.activitys;

import android.view.View;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;

import butterknife.OnClick;
import exam.qyw.test.myapplication.R;
import exam.qyw.test.myapplication.base.BaseActivity;
import exam.qyw.test.myapplication.utils.LogUtil;
import exam.qyw.test.myapplication.utils.OkhttpUtil;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Author:qyw
 * on 2018/11/21.
 * QQ:448739075
 * 描述：
 */
public class OkttpTestActivity extends BaseActivity {
    @Override
    public int innitLayout() {
        return R.layout.activity_okhttp_test;
    }

    @Override
    public void innitData() {

    }

    @OnClick({R.id.bt_get})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.bt_get:
                testGet();
                break;
        }
    }
    private void testGet(){
        HashMap<String,String> map=new HashMap<>();
        map.put("name","张三");
        map.put("password","123456");
        OkhttpUtil.innitOkhttpGet(getmActivity(), "http://www.sina.cn", map, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String result=response.body().string();
                try {
                    JSONObject jsonObject=new JSONObject(result);
                    LogUtil.i("result:"+jsonObject.toString());

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
