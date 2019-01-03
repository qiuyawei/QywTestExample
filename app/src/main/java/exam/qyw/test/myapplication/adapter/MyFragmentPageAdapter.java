package exam.qyw.test.myapplication.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

public class MyFragmentPageAdapter extends FragmentPagerAdapter {
    private List<Fragment> fragmentList;
    private String[]mTitls;

    public MyFragmentPageAdapter(FragmentManager fragmentManager, List<Fragment> fragments, String[]titles){
        super(fragmentManager);
        this.fragmentList=fragments;
    }
    @Override
    public Fragment getItem(int i) {
        return fragmentList.get(i);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }
}
