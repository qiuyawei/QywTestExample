package exam.qyw.test.myapplication.activitys;

import android.animation.ObjectAnimator;
import android.graphics.BitmapFactory;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Properties;
import java.util.Random;

import butterknife.BindView;
import butterknife.OnClick;
import exam.qyw.test.myapplication.R;
import exam.qyw.test.myapplication.base.BaseActivity;
import exam.qyw.test.myapplication.utils.ClickUtil;
import exam.qyw.test.myapplication.utils.ImageFilter;
import exam.qyw.test.myapplication.utils.LogUtil;
import exam.qyw.test.myapplication.utils.ScreenUtil;

/**
 * Created by Author:qyw
 * on 2018/11/20.
 * QQ:448739075
 * 描述：
 */
public class AnimationTestActivity extends BaseActivity {
    @BindView(R.id.image1)
    ImageView imageView1;
    @BindView(R.id.image2)
    ImageView imageView2;
    @BindView(R.id.image)
    ImageView imageView;
    @BindView(R.id.tv_rote)
    TextView tv_rote;

    private ImageFilter imageFilter;
    @Override
    public int innitLayout() {
        return R.layout.activity_animation;
    }

    @Override
    public void innitData() {
        LogUtil.i("action_bar_height:"+ScreenUtil.px2dp(getmActivity(),ScreenUtil.actionBarHeight(getmActivity())));
//        Animation animation1 = AnimationUtils.loadAnimation(this, R.anim.translate_animation);
//        imageView.setAnimation(animation1);
//        imageView.startAnimation(animation1);
        imageView1.setImageBitmap(ImageFilter.blurBitmap(
                AnimationTestActivity.this,BitmapFactory.decodeResource(getResources(),R.mipmap.music),3));
        imageView2.setImageBitmap(ImageFilter.blurBitmap(
                AnimationTestActivity.this,BitmapFactory.decodeResource(getResources(),R.mipmap.music),6));
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
                Animation animation2 = AnimationUtils.loadAnimation(this, R.anim.rotate_animation);
                tv_rote.setAnimation(animation2);
                tv_rote.startAnimation(animation2);
//                ObjectAnimator.ofFloat(imageView, "rotationY", 0.0f, 110.0f).setDuration(1000).start();
//                RotateAnimation animation=new RotateAnimation(0,360,) ;

                break;
            case R.id.image:
                Toast.makeText(this,"被点击了",Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
