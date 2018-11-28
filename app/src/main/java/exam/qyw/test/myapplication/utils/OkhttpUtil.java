package exam.qyw.test.myapplication.utils;

import android.content.Context;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * Created by Author:qyw
 * on 2018/11/22.
 * QQ:448739075
 * 描述：
 */
public class OkhttpUtil {
    private static final String BASE_URL="";
    private static final String IMAGE_BASE_URL="";
    private static final long TIME_OUT=8000;//超时时间
    public static void innitOkhttpGet(Context context, String currentRequestUrl, HashMap<String,String>parmas, Callback callback){
        FormBody.Builder fBuilder=new FormBody.Builder();
//        Iterator<Map<String,String>> iterator=parmas.keySet();
        for(String item:parmas.keySet()){
            fBuilder.add(item,parmas.get(item));
        }
        Request.Builder builder=new Request.Builder();
        builder.url(BASE_URL+currentRequestUrl)
                .put(fBuilder.build())
                .get();
        Request request=builder.build();

        innitGet().newCall(request).enqueue(callback);
    }

    private static OkHttpClient innitGet(){
        OkHttpClient.Builder builder=new OkHttpClient.Builder();
        builder.callTimeout(TIME_OUT,TimeUnit.SECONDS);
        return builder.build();
    }
}
