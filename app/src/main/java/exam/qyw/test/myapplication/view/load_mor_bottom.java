package exam.qyw.test.myapplication.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ajguan.library.ILoadMoreView;
import com.github.ybq.android.spinkit.SpinKitView;

import java.util.logging.Handler;

import exam.qyw.test.myapplication.R;
import exam.qyw.test.myapplication.utils.LogUtil;
import exam.qyw.test.myapplication.utils.ScreenUtil;

public class load_mor_bottom extends FrameLayout implements ILoadMoreView {
    private TextView tvHitText;
    private SpinKitView spinKitView;
    private View view;

    public load_mor_bottom(Context context) {
        this(context, (AttributeSet)null);
    }

    public load_mor_bottom(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.view = inflate(context,R.layout.default_load_more, this);
        this.tvHitText = (TextView)this.view.findViewById(com.ajguan.library.R.id.tv_hit_content);
        this.spinKitView = (SpinKitView)this.view.findViewById(com.ajguan.library.R.id.spin_kit);
    }

    public void reset() {
        setVisibility(View.VISIBLE);
        this.spinKitView.setVisibility(4);
        this.tvHitText.setVisibility(4);
        this.tvHitText.setText("正在加载...");
    }

    public void loading() {
        setVisibility(View.VISIBLE);
        this.spinKitView.setVisibility(0);
        this.tvHitText.setVisibility(0);
        this.tvHitText.setText("正在加载...");
    }

    public void loadComplete() {
        this.spinKitView.setVisibility(4);
        this.tvHitText.setVisibility(0);
        this.tvHitText.setText("加载完成");
    }

    public void loadFail() {
        setVisibility(View.VISIBLE);
        this.spinKitView.setVisibility(4);
        this.tvHitText.setVisibility(0);
        this.tvHitText.setText("加载失败,点击重新加载");
    }

    public void loadNothing() {
        setVisibility(View.VISIBLE);
        this.spinKitView.setVisibility(4);
        this.tvHitText.setVisibility(View.VISIBLE);
        this.tvHitText.setText("没有更多可以加载");
        new android.os.Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                setVisibility(View.GONE);
            }
        },1000);
    }

    public View getCanClickFailView() {
        return this.view;
    }
}
