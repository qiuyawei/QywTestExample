package exam.qyw.test.myapplication.activitys;

import android.os.AsyncTask;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ProgressBar;

import java.util.ArrayList;

import butterknife.BindView;
import exam.qyw.test.myapplication.R;
import exam.qyw.test.myapplication.adapter.MyPageAdapter;
import exam.qyw.test.myapplication.base.BaseActivity;
import exam.qyw.test.myapplication.utils.LogUtil;
import exam.qyw.test.myapplication.utils.ScaleTransformer;
import exam.qyw.test.myapplication.view.MyViewPage;

public class BannerActivity extends BaseActivity {
    private String[] imagePaths = {"http://pic1a.nipic.com/2009-01-07/20091713417344_2.jpg",
            "http://pic19.nipic.com/20120210/7827303_221233267358_2.jpg"
            , "http://pic33.photophoto.cn/20141022/0019032438899352_b.jpg"};
    private ArrayList<String> pics = new ArrayList<>();
    private MyPageAdapter myPageAdapter;
    @BindView(R.id.viewPage)
    MyViewPage viewPager;
    @Override
    public int innitLayout() {
        return R.layout.activity_banner;
    }

    @Override
    public void innitData() {
        for (String picPath : imagePaths) {
            pics.add(picPath);
        }
        myPageAdapter = new MyPageAdapter(BannerActivity.this, pics);
        viewPager.setAdapter(myPageAdapter);
        viewPager.setPageMargin(50);
        viewPager.setOffscreenPageLimit(2);
//        viewPager.setPageTransformer(true,new ScaleTransformer());
        LogUtil.i("viewpage_height:"+viewPager.getHeight());
        viewPager.post(new Runnable() {
            @Override
            public void run() {
                LogUtil.i("viewpage_height after post:"+viewPager.getHeight());

            }
        });
    }

}

