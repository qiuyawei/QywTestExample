package exam.qyw.test.myapplication.activitys;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ScrollView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import exam.qyw.test.myapplication.R;
import exam.qyw.test.myapplication.adapter.GoodsAdapter;
import exam.qyw.test.myapplication.adapter.LeftAdapter;
import exam.qyw.test.myapplication.adapter.LetRecAdapter;
import exam.qyw.test.myapplication.base.BaseActivity;
import exam.qyw.test.myapplication.bean.GoodsBean;
import exam.qyw.test.myapplication.bean.InnerBean;
import exam.qyw.test.myapplication.bean.LeftBean;
import exam.qyw.test.myapplication.callback.OnRecycleItemClickListener;
import exam.qyw.test.myapplication.fragments.FragmentOne;
import exam.qyw.test.myapplication.fragments.FragmentTwo;
import exam.qyw.test.myapplication.fragments.ItemFragment;
import exam.qyw.test.myapplication.utils.LogUtil;
import exam.qyw.test.myapplication.utils.ScreenUtil;
import exam.qyw.test.myapplication.view.VerticalViewPage;
import q.rorbin.verticaltablayout.VerticalTabLayout;
import q.rorbin.verticaltablayout.widget.ITabView;
import q.rorbin.verticaltablayout.widget.QTabView;
import q.rorbin.verticaltablayout.widget.TabView;

import static android.support.v7.widget.RecyclerView.SCROLL_STATE_IDLE;

public class LeftTabActivity extends BaseActivity implements OnRecycleItemClickListener {
    @BindView(R.id.recycleView)
    RecyclerView recyclerView;
    @BindView(R.id.vTab)
    VerticalTabLayout vTab;
    private String[] titls = {"title1", "title2", "title3", "title4", "title5", "title6"};
    private List<Fragment> fragmentList = new ArrayList<>();
    private GoodsAdapter goodsAdapter;
    private ArrayList<GoodsBean> goodsBeans = new ArrayList<>();
    private int mPost = 0;
    private boolean isScolling = false;

    @Override
    public int innitLayout() {
        return R.layout.activity_left_tab;
    }

    @Override
    public void innitData() {
        innitToolBar("测试左边tabLayout 右边recycleView 联动");

        for (int i = 0; i < titls.length; i++) {
            QTabView qTabView = new QTabView(getmActivity());
            qTabView.setTitle(new ITabView.TabTitle.Builder()
                    .setTextColor(Color.RED, Color.BLACK).setContent(titls[i]).build());
            vTab.addTab(qTabView);

        }
        for (int i = 0; i < titls.length; i++) {
            GoodsBean goodsBean = new GoodsBean();
            goodsBean.totalTitle = titls[i];
            goodsBean.innerBeans = new ArrayList<>();
            ArrayList<InnerBean> innerBeans = new ArrayList<>();
            for (int j = 0; j < 19; j++) {
                InnerBean innerBean = new InnerBean();
                innerBeans.add(innerBean);
            }
            goodsBean.innerBeans.addAll(innerBeans);
            goodsBeans.add(goodsBean);
        }
        goodsAdapter = new GoodsAdapter(getmActivity(), goodsBeans, this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getmActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerView.addItemDecoration(new DividerItemDecoration(getmActivity(), DividerItemDecoration.VERTICAL));
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(goodsAdapter);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);


                LogUtil.i("newState:" + newState);
                /*if (newState == SCROLL_STATE_IDLE) {
                    LinearLayoutManager manager = (LinearLayoutManager) recyclerView.getLayoutManager();
                    int firstVisPos = manager.findFirstVisibleItemPosition();
                    int lastVisPos = manager.findLastVisibleItemPosition();
                    if (isScolling) {
                        isScolling = false;
                        LogUtil.i("停止滚动");

//                    vTab.setTabSelected(firstVisPos);
                        int n = mPostion - firstVisPos;
                        if (n > 0 && n < recyclerView.getChildCount()) {
                            int top = recyclerView.getChildAt(n).getTop();
                            recyclerView.smoothScrollBy(0, top);
                        }
                    }*/

            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                LinearLayoutManager manager = (LinearLayoutManager) recyclerView.getLayoutManager();
                int firstVisPos = manager.findFirstVisibleItemPosition();
                View firesView = manager.findViewByPosition(firstVisPos);
                int lastVisPos = manager.findLastVisibleItemPosition();
                View view = recyclerView.getChildAt(firstVisPos + 1);
                if (view != null && view.getTop() == recyclerView.getHeight() / 2) {
                    vTab.setTabSelected(firstVisPos + 1);
                }else {
                    if(view==null){LogUtil.i("view=null");}
                    else {
                        LogUtil.i("view.getTop():"+view.getTop());
                        LogUtil.i("view.getTop()--:"+recyclerView.getHeight() / 2);

                    }
                }
            }

        });

        vTab.addOnTabSelectedListener(new VerticalTabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabView tab, int position) {
                LogUtil.i("isEqual:" + position);
//                smothScoller(position);
            }

            @Override
            public void onTabReselected(TabView tab, int position) {

            }
        });
        SnapHelper snapHelper=new LinearSnapHelper();
//        recyclerView.sn
    }

    @Override
    public void onItemClick(int postion) {

    }

    private int mPostion = 0;

    private void smothScoller(int post) {
        LinearLayoutManager manager = (LinearLayoutManager) recyclerView.getLayoutManager();
        int firstVisPos = manager.findFirstVisibleItemPosition();
        int lastVisPos = manager.findLastVisibleItemPosition();
        if (firstVisPos > post) {
            recyclerView.smoothScrollToPosition(post);
        } else if (post < lastVisPos) {
            //已经显示在屏幕上
            int cu = recyclerView.getChildAt(post - firstVisPos).getTop();
            recyclerView.scrollBy(0, cu);
        } else {
            //在最后可见的一个之后
            mPostion = post;
            isScolling = true;
            recyclerView.smoothScrollToPosition(post);
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        LogUtil.i("top_real:" + recyclerView.getHeight() + "==" + recyclerView.getTop() + "=" + recyclerView.getBottom());
    }
}
