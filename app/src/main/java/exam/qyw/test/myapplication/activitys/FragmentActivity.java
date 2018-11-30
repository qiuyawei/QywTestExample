package exam.qyw.test.myapplication.activitys;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import exam.qyw.test.myapplication.R;
import exam.qyw.test.myapplication.base.BaseActivity;
import exam.qyw.test.myapplication.bean.TabBean;
import exam.qyw.test.myapplication.fragments.FragmentOne;
import exam.qyw.test.myapplication.fragments.ItemFragment;
import exam.qyw.test.myapplication.utils.LogUtil;

/**
 * Created by Author:qyw
 * on 2018/11/29.
 * QQ:448739075
 * 描述：
 */
public class FragmentActivity extends BaseActivity {
    private int selectPost=0;
    @BindView(R.id.viewPage)
    ViewPager viewPager;
    @BindView(R.id.tabLayout)
    TabLayout tableLayout;
    private ArrayList<Fragment> fragments = new ArrayList<>();
    private ArrayList<TabBean> tabBeans = new ArrayList<>();
    private int[] normalPics = {R.mipmap.icon_tab_def, R.mipmap.icon_tab_three_def};
    private int[] selectPics = {R.mipmap.icon_tab_sel, R.mipmap.icon_tab_three_sel};
    private String[] titls = {"Index", "Second"};
    private ContextWrapper mContext;
    @Override
    public int innitLayout() {
        return R.layout.activity_fragment_ac;
    }

    @Override
    public void innitData(){
        innitFragments();
        innitTabLayouts();
        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int i) {
                LogUtil.i("getItem:" + i);
//                setIconWithPos(i);

                return fragments.get(i);
            }

            @Override
            public int getCount() {
                return fragments.size();
            }
        });
        tableLayout.addOnTabSelectedListener(new TabLayout.BaseOnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                LogUtil.i("select-pos:"+tableLayout.getSelectedTabPosition());
                LogUtil.i("child-count:"+tableLayout.getChildCount());

//                setIconWithPos(tableLayout.getSelectedTabPosition());
                if (tab.getCustomView() != null) {
                    TextView textView=tab.getCustomView().findViewById(R.id.tv_tab_title);
                    ImageView icon=tab.getCustomView().findViewById(R.id.iv_icon);

                    textView.setTextColor(Color.RED);
                    icon.setImageResource(selectPics[tableLayout.getSelectedTabPosition()]);
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                if (tab.getCustomView() != null) {
                    TextView textView=tab.getCustomView().findViewById(R.id.tv_tab_title);
                    ImageView icon=tab.getCustomView().findViewById(R.id.iv_icon);

                    textView.setTextColor(Color.BLACK);
                    icon.setImageResource(normalPics[tableLayout.getSelectedTabPosition()>0?tableLayout.getSelectedTabPosition()-1:fragments.size()-1]);

                }
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        tableLayout.setupWithViewPager(viewPager);
        setCustomView();
    }

    private void innitFragments() {
        FragmentOne fragmentOne = FragmentOne.newInstance("参数1", "参数2");
        ItemFragment itemFragment = ItemFragment.newInstance(4);
        fragments.add(fragmentOne);
        fragments.add(itemFragment);
    }

    private void innitTabLayouts() {
        for (int i = 0; i < fragments.size(); i++) {
            TabLayout.Tab tab=tableLayout.newTab();
            tab.setCustomView(R.layout.item_red_dot);
            TextView textView=tab.getCustomView().findViewById(R.id.tv_tab_title);
            ImageView icon=tab.getCustomView().findViewById(R.id.iv_icon);
            textView.setText("Index"+i);
            tab.setText("Index"+i);
            tableLayout.addTab(tab);
        }
        tableLayout.setTabTextColors(Color.BLACK, Color.RED);
        tableLayout.setSelectedTabIndicatorColor(Color.TRANSPARENT);
    }

    private void setCustomView(){
        LogUtil.i("setCustomView:"+tableLayout.getChildCount());
        for (int i = 0; i < fragments.size(); i++) {
            TabLayout.Tab tab=tableLayout.getTabAt(i);
            tab.setCustomView(R.layout.item_red_dot);
            TextView textView=tab.getCustomView().findViewById(R.id.tv_tab_title);
            ImageView icon=tab.getCustomView().findViewById(R.id.iv_icon);

            textView.setText("Index"+i);
            tab.setText("Index"+i);
            //设置默认选项字体颜色
            if(i==0){
                textView.setTextColor(Color.RED);
                icon.setImageResource(selectPics[0]);
            }else {
                icon.setImageResource(normalPics[1]);
            }
        }
    }

    private int getPostion(TabLayout.Tab tab){
        for(int i=0;i<tableLayout.getChildCount();i++){
            if(tableLayout.getTabAt(i)==tab){
                return i;
            }
        }
        return 0;
    }

}
