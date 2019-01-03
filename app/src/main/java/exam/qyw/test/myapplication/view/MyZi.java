package exam.qyw.test.myapplication.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import exam.qyw.test.myapplication.R;

public class MyZi extends LinearLayout {
    public MyZi(Context context) {
        super(context);
        innit(context);
    }

    public MyZi(Context context,  AttributeSet attrs) {
        super(context, attrs);
        innit(context);

    }

    public MyZi(Context context,  AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        innit(context);

    }

    public MyZi(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        innit(context);

    }

    private void innit(Context context){
        setOrientation(LinearLayout.VERTICAL);
        addView(LayoutInflater.from(context).inflate(R.layout.zi_dingyi,null));
    }
}
