package exam.qyw.test.myapplication.activitys;

import android.animation.ObjectAnimator;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.Properties;
import java.util.Random;

import butterknife.BindView;
import butterknife.OnClick;
import exam.qyw.test.myapplication.R;
import exam.qyw.test.myapplication.base.BaseActivity;
import exam.qyw.test.myapplication.utils.ClickUtil;

/**
 * Created by Author:qyw
 * on 2018/11/20.
 * QQ:448739075
 * 描述：
 */
public class AnimationTestActivity extends BaseActivity {
    @BindView(R.id.image)
    ImageView imageView;

    @Override
    public int innitLayout() {
        return R.layout.activity_animation;
    }

    @Override
    public void innitData() {
//        Animation animation1 = AnimationUtils.loadAnimation(this, R.anim.translate_animation);
//        imageView.setAnimation(animation1);
//        imageView.startAnimation(animation1);
    }

    @OnClick({R.id.bt_alpha, R.id.bt_weiYi, R.id.bt_rote,R.id.image})
    public void onClick(View view) {
        if (ClickUtil.isFastDoubleClick(view.getId())) {
            return;
        }
        switch (view.getId()) {
            case R.id.bt_alpha:
//                Animation animation = AnimationUtils.loadAnimation(this, R.anim.fade_in_animation);
//                imageView.setAnimation(animation);
//                imageView.startAnimation(animation);
                ObjectAnimator.ofArgb(imageView,"alpha",10,255).setDuration(2000).start();
                break;
            case R.id.bt_weiYi:
//                Animation animation1 = AnimationUtils.loadAnimation(this, R.anim.translate_animation);
//                imageView.setAnimation(animation1);
//                imageView.startAnimation(animation1);
//                float[][]values={{0f,0f},{100f,100f}};
//                ObjectAnimator.ofMultiFloat(imageView,"translate",values).start();
//                ObjectAnimator.ofMultiFloat(imageView,"rotationY",values).start();
                float va=(float) (new Random().nextInt(3));
                imageView.animate().
                        scaleX(va)
                        .scaleY(va)
                        .rotation(90*va)
                        .start();
                break;
            case R.id.bt_rote:
//                Animation animation2 = AnimationUtils.loadAnimation(this, R.anim.rotate_animation);
//                imageView.setAnimation(animation2);
//                imageView.startAnimation(animation2);
//                ObjectAnimator.ofFloat(imageView, "rotationY", 0.0f, 110.0f).setDuration(1000).start();
//                RotateAnimation animation=new RotateAnimation(0,360,) ;
                break;
            case R.id.image:
                Toast.makeText(this,"被点击了",Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
