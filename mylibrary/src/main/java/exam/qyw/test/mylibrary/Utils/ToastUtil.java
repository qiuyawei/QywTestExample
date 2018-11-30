package exam.qyw.test.mylibrary.Utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by Author:qyw
 * on 2018/11/29.
 * QQ:448739075
 * 描述：
 */
public class ToastUtil {
    /**
     *
     * @param context
     * @param message
     */
    public static void showMyToast(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }
}
