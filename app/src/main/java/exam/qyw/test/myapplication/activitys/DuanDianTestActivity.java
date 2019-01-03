package exam.qyw.test.myapplication.activitys;

import android.animation.ObjectAnimator;
import android.graphics.drawable.AnimationDrawable;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.widget.ImageView;

import butterknife.BindView;
import exam.qyw.test.myapplication.R;
import exam.qyw.test.myapplication.base.BaseActivity;
import exam.qyw.test.myapplication.utils.ToastUtil;

/**
 * @Author qiuyawei
 * @CreateTime 2018/12/20 17:19
 * @Description 断点调试
 */
public class DuanDianTestActivity extends BaseActivity {
    private String[] names = {"ab", "cd"};
    @BindView(R.id.image)
    ImageView image;
    AnimationDrawable animationDrawable;

    @Override
    public int innitLayout() {
        return R.layout.activty_duan_dian;
    }

    @Override
    public void innitData() {
        animationDrawable = (AnimationDrawable) image.getBackground();
    }

    public void startDuanDian(View view) {

    }

    boolean isShow=false;
    public void anmiaTest(View view) {
        if(isShow){
            animationDrawable.stop();
            isShow=false;
        }else {
            animationDrawable.start();
            isShow=true;
        }
    }
}
