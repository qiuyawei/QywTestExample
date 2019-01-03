package exam.qyw.test.myapplication.activitys;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import exam.qyw.test.myapplication.R;
import exam.qyw.test.myapplication.adapter.MyFragmentPageAdapter;
import exam.qyw.test.myapplication.base.BaseActivity;
import exam.qyw.test.myapplication.bean.TabBean;
import exam.qyw.test.myapplication.fragments.FragmentOne;
import exam.qyw.test.myapplication.fragments.FragmentTwo;
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
    @BindView(R.id.container)
    FrameLayout frameLayout;
    @BindView(R.id.tabLayout)
    TabLayout tableLayout;
    private ArrayList<Fragment> fragments = new ArrayList<>();
    private ArrayList<TabBean> tabBeans = new ArrayList<>();
    private int[] normalPics = {R.mipmap.icon_tab_def, R.mipmap.icon_tab_three_def};
    private int[] selectPics = {R.mipmap.icon_tab_sel, R.mipmap.icon_tab_three_sel};
    private String[] titls = {"Index", "Second"};
    private ContextWrapper mContext;
    private MyFragmentPageAdapter myFragmentPageAdapter;
    private FragmentManager fragmentManager;
    FragmentTransaction transaction;
    @Override
    public int innitLayout() {
        return R.layout.activity_fragment_ac;
    }

    @Override
    public void innitData(){
        fragmentManager=getSupportFragmentManager();
        fragments.add(new FragmentOne());
        fragments.add(new FragmentTwo());
        for(int i=0;i<fragments.size();i++){
            TabLayout.Tab tab=tableLayout.newTab();
            tab.setText(titls[i]);
            tab.setIcon(normalPics[i]);
            tableLayout.addTab(tab);
        }

        fragmentManager.beginTransaction().add(R.id.container,fragments.get(0),"FragmentOne").commit();
        fragmentManager.beginTransaction().add(R.id.container,fragments.get(1),"ItemFragment").commit();
//        hideFragments();
        showFragment(0);
        tableLayout.addOnTabSelectedListener(new TabLayout.BaseOnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int postion=tableLayout.getSelectedTabPosition();
                LogUtil.i("postion:"+postion);
                showFragment(tableLayout.getSelectedTabPosition());
                LogUtil.i("postion:isVis:"+fragments.get(postion).isHidden());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }


    private void hideFragments(){
        for(Fragment fragment:fragments){
            fragmentManager.beginTransaction().hide(fragment).commit();
        }
    }

    private void showFragment(int pos){
        hideFragments();
        fragmentManager.beginTransaction().show(fragments.get(pos)).commit();
    }

}
