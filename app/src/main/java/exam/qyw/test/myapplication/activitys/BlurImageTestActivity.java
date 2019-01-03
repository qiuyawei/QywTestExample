package exam.qyw.test.myapplication.activitys;

import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.MultiTransformation;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

import butterknife.BindView;
import exam.qyw.test.myapplication.R;
import exam.qyw.test.myapplication.base.BaseActivity;
import exam.qyw.test.myapplication.utils.ImageFilter;
import exam.qyw.test.myapplication.utils.ScreenUtil;
import jp.wasabeef.glide.transformations.BlurTransformation;
import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

/**
 * @Author qiuyawei
 * @CreateTime 2018/12/24 14:41
 * @Description 高斯模糊测试
 */
public class BlurImageTestActivity extends BaseActivity {
    @BindView(R.id.image1)
    ImageView image1;
    @BindView(R.id.image2)
    ImageView image2;
    @BindView(R.id.image3)
    ImageView image3;

    @Override
    public int innitLayout() {
        return R.layout.activity_blur_test;
    }

    @Override
    public void innitData() {
//        Glide.with(getmActivity()).
//                load(ImageFilter.blurBitmap(getmActivity(), BitmapFactory.decodeResource(getResources(), R.mipmap.b), 10))

//                .into(image1);
       image1.setImageBitmap(ImageFilter.blurBitmap(getmActivity(), BitmapFactory.decodeResource(getResources(), R.mipmap.b), 1));
       image2.setImageResource(R.mipmap.b);
      /*  Glide.with(getmActivity()).load(R.mipmap.b).
                apply(RequestOptions.bitmapTransform(new BlurTransformation(10,1)))
                .into(image3);
        Glide.with(getmActivity()).load(R.mipmap.b).
                apply(RequestOptions.bitmapTransform(new BlurTransformation(10,3)))
                .into(image4);*/
      int width=ScreenUtil.dip2px(getmActivity(),100);
      Bitmap bitmap=BitmapFactory.decodeResource(getResources(),R.mipmap.b);
      image3.setImageBitmap(bitmap);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ImageFilter.recycleBitmaps();
    }
}
