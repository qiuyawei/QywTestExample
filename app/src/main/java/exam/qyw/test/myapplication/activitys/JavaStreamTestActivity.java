package exam.qyw.test.myapplication.activitys;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;
import android.os.Parcel;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.EditText;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;

import butterknife.BindView;
import exam.qyw.test.myapplication.R;
import exam.qyw.test.myapplication.base.BaseActivity;
import exam.qyw.test.myapplication.constans.Constant;
import exam.qyw.test.myapplication.utils.LogUtil;

/**
 * Created by Author:qyw
 * on 2018/11/27.
 * QQ:448739075
 * 描述：测试各种java 流的转化
 */
public class JavaStreamTestActivity extends BaseActivity {
    @BindView(R.id.et_showContent)
    EditText et_show;

    PrintWriter printWriter;
    OutputStream outputStream;

    @Override
    public int innitLayout() {
        return R.layout.activity_java_stream_test;
    }

    @Override
    public void innitData() {
        LogUtil.i("存储卡目录：" + Environment.getExternalStorageDirectory().getAbsolutePath());
    }

    //转化流
    public void onChange(View view) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                printData();
            } else {
                requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, Constant.PERMISON_RRQUEST_CODE);
            }
        } else
            printData();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode==Constant.PERMISON_RRQUEST_CODE){
            if(grantResults[0]==PackageManager.PERMISSION_GRANTED){
                printData();
            }
        }
    }

    private void printData() {
        String filePath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/qiuyawei/a.txt";
        LogUtil.i("filePath:" + filePath);
        File file = new File(filePath);
        if (!file.exists()) {
            //先创建目录，如果存在了也没关系
            boolean dirCreateReulst = file.getParentFile().mkdirs();
            LogUtil.i("dirCreateReulst:" + dirCreateReulst);

            try {
                boolean fileCreateRel = file.createNewFile();
                LogUtil.i("create file result:" + fileCreateRel);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            try {
                outputStream = new FileOutputStream(filePath, true);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            try {
                printWriter = new PrintWriter(new BufferedWriter(new OutputStreamWriter(outputStream, "utf-8")));
                printWriter.append("ni hao");
                printWriter.append("/n");

//                printWriter.print(100);
                printWriter.flush();
                printWriter.close();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
    }
}
