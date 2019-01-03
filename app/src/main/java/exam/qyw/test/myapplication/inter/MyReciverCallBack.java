package exam.qyw.test.myapplication.inter;

import android.content.Intent;
import android.os.Bundle;

public interface MyReciverCallBack {
    /**
     * 收到广播后处理一些逻辑
     * @param intent 广播传过来的对象
     */
    void afterReciver(Intent intent);
}
