package exam.qyw.test.myapplication.utils;

import android.content.Context;
import android.widget.Toast;

public class ToastUtil {
    public static void toastShort(Context context,String message){
        Toast.makeText(context,message,Toast.LENGTH_SHORT).show();
    }
}
