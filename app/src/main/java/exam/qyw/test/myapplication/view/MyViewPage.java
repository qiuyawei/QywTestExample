package exam.qyw.test.myapplication.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

public class MyViewPage extends ViewPager {
    private float downX,downY,endX,endY,moveDistanceX,moveDistanceY;
    public MyViewPage(@NonNull Context context) {
        super(context,null);
    }

    public MyViewPage(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return super.onTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        switch (ev.getAction()){
            case MotionEvent.ACTION_DOWN:
                downX=ev.getX();
                downY=ev.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                endX=ev.getX();
                endY=ev.getY();
                //判断是上下移动距离大还是左右移动距离大
                moveDistanceX=Math.abs(endX-downX);
                moveDistanceY=Math.abs(endY-downY);
                if(moveDistanceY<=moveDistanceX){
                    return true;
                }
                break;
        }
        return super.onInterceptTouchEvent(ev);
    }
}
