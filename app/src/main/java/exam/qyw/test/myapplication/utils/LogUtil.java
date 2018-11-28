package exam.qyw.test.myapplication.utils;

import android.util.Log;

/**
 * Created by Author:qyw
 * on 2018/11/19.
 * QQ:448739075
 * 描述：
 */
public class LogUtil {
    static  String TAG="QYW";
    static  boolean isOpen=true;
    public static void i(String logContent){
        if(isOpen)
        Log.i(TAG,getContent(logContent,4));
    }
    private static String getNameFromTrace(StackTraceElement[] traceElements, int place) {
        StringBuilder taskName = new StringBuilder();
        if (traceElements != null && traceElements.length > place) {
            StackTraceElement traceElement = traceElements[place];
            taskName.append(traceElement.getMethodName());
            taskName.append("(").append(traceElement.getFileName()).append(":").append(traceElement.getLineNumber()).append(")");
        }
        return taskName.toString();
    }

    private static String getContent(String msg, int place, Object... args) {
        try {
            String sourceLinks = getNameFromTrace(Thread.currentThread().getStackTrace(), place);
            return sourceLinks + String.format(msg, args);
        } catch (Throwable throwable) {
            return msg;
        }
    }
}
