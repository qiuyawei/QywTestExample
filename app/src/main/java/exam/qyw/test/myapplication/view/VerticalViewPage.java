package exam.qyw.test.myapplication.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import javax.xml.transform.Transformer;

public class VerticalViewPage extends ViewPager {
    public VerticalViewPage(@NonNull Context context) {
        this(context,null);
    }

    public VerticalViewPage(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setPageTransformer(true,new DefaultTransformaer());
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return super.onTouchEvent(swapEvent(ev));
    }

    private MotionEvent swapEvent(MotionEvent event){
        float width=getWidth();
        float height=getHeight();
        float swapX=event.getY()/height*width;
        float swapY=event.getX()/width*height;
        event.setLocation(swapX,swapY);
        return event;
    }

    public class DefaultTransformaer implements PageTransformer {

        @Override
        public void transformPage(@NonNull View view, float postion) {
            float alpha=0;
            if(0<=postion&&postion<=1){
                alpha=1-postion;
            }else if(-1<postion&&postion<0){
                alpha=postion+1;
            }
            view.setAlpha(alpha);
            float transX=view.getWidth()*-postion;
            float transY=view.getHeight()*postion;
            view.setTranslationX(transX);
            view.setTranslationY(transY);
        }

    }
}
