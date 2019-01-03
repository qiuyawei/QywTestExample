/**
 * <p>Project: sgm_netmusic</p>
 * <p>Package: com.sgm.netmusic.commons</p>
 * <p>File: NetConnectUtil.java</p>
 * <p>Date: 2018/5/21/13:53.</p>
 */
package exam.qyw.test.myapplication.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * <p>Class: com.sgm.netmusic.commons.NetConnectUtil</p>
 * <p>Description: </p>
 * <pre>
 *
 * </pre>
 *
 * @author hechucai
 * @date 2018/5/21/13:53
 */


public class NetConnectUtil {

    private static final String TAG = NetConnectUtil.class.getSimpleName();

    public static boolean checkNet(Context context) {
        ConnectivityManager manager = null;
        if (context != null) {
            manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            if (manager != null) {
                NetworkInfo networkInfo = manager.getActiveNetworkInfo();
                LogUtil.i("checkNet:" + (networkInfo != null && networkInfo.isConnected()));
                return (networkInfo != null && networkInfo.isConnected());
            }
        }

        LogUtil.i("checkNet:false context:" + context + "manager:" + manager);
        return false;
    }
}
