package exam.qyw.test.myapplication.activitys;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AbsListView;
import android.widget.TextView;

import com.github.promeg.pinyinhelper.Pinyin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;
import java.util.logging.Handler;

import butterknife.BindView;
import exam.qyw.test.myapplication.R;
import exam.qyw.test.myapplication.adapter.LeftAdapter;
import exam.qyw.test.myapplication.adapter.RightAdapter;
import exam.qyw.test.myapplication.base.BaseActivity;
import exam.qyw.test.myapplication.bean.ContactBean;
import exam.qyw.test.myapplication.bean.RightIndexBean;
import exam.qyw.test.myapplication.utils.LogUtil;

/**
 * Created by Author:qyw
 * on 2018/11/28.
 * QQ:448739075
 * 描述：
 */
public class SeachActivity extends BaseActivity implements RightAdapter.OnItemClickListener {
    @BindView(R.id.lefttRecycleView)
    RecyclerView lefttRecycleView;
    @BindView(R.id.rightRecycleView)
    RecyclerView rightRecycleView;
    @BindView(R.id.tv_bigSize)
    TextView tv_bigSize;

    private ArrayList<ContactBean> dataLeft = new ArrayList<>();
    private ArrayList<RightIndexBean> dataRight = new ArrayList<>();
    String[] rightData = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S"
            , "T", "U", "V", "W", "X", "Y", "Z"};

    String[] names = {"欧阳烟", "大神", "波波", "阿飞", "张三", "李四", "王五", "小月", "张强", "李世民", "王达"};
    String[] phones = {"18516034778", "13789904000", "18516034778", "13789904000", "18516034778", "13789904000", "13888888888", "19899999999", "19899999999", "19899999999", "19899999999"};

    private LeftAdapter leftAdapter;
    private RightAdapter rightAdapter;

    @Override
    public int innitLayout() {
        return R.layout.activity_search;
    }

    @Override
    public void innitData() {
        innitToolBar("根据字母快速定位查找");


        innitLeftData();
        innitRightData();
        lefttRecycleView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (mShouldScroll && newState == RecyclerView.SCROLL_STATE_IDLE) {
                    mShouldScroll = false;
                    smoothMoveToPosition(lefttRecycleView, mToPosition);
                }
            }
        });
    }

    private void innitLeftData() {
        //这个必须加上否则不显示，亲测
        lefttRecycleView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        lefttRecycleView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        leftAdapter = new LeftAdapter(getmActivity(), dataLeft);
        lefttRecycleView.setAdapter(leftAdapter);
        nameSortWithFirstCha();
        dataLeft.clear();
        for (int i = 0; i < names.length; i++) {
            ContactBean contactBean = new ContactBean();
            contactBean.setName(names[i]);
            contactBean.setNameFirstCha(getStringFirstCha(names[i]));
            contactBean.setPhone(phones[i]);
            dataLeft.add(contactBean);
        }
        LogUtil.i("size:" + dataLeft.size());
        leftAdapter.notifyDataSetChanged();
    }

    private void innitRightData() {
        rightAdapter = new RightAdapter(getmActivity(), dataRight, this);
        rightRecycleView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        rightRecycleView.addItemDecoration(new DividerItemDecoration(getmActivity(), DividerItemDecoration.VERTICAL));
        rightRecycleView.setAdapter(rightAdapter);
        dataRight.clear();
        for (int i = 0; i < rightData.length; i++) {
            RightIndexBean bean = new RightIndexBean();
            bean.setFirstChar(rightData[i].charAt(0));
            dataRight.add(bean);
        }
        rightAdapter.notifyDataSetChanged();
    }

    private void nameSortWithFirstCha() {
        Arrays.sort(names, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                String pin1 = Pinyin.toPinyin(o1.charAt(0));
                String pin2 = Pinyin.toPinyin(o2.charAt(0));

                char a1 = pin1.charAt(0);
                char a2 = pin2.charAt(0);
                int t = 0;
                if (a1 > a2) {
                    t = 1;
                } else if (a1 < a2) {
                    t = -1;
                } else {
                    t = 0;
                }

                return t;
            }
        });
    }

    private char getStringFirstCha(String name) {
        if (name.length() > 1) {
            name = name.substring(0, 1);
        }
        String pinyin = Pinyin.toPinyin(name.charAt(0));
        return pinyin.charAt(0);
    }

    //rightRecycleView item onClick
    private boolean mShouldScroll = false;

    @Override
    public void onItemOnClick(View view, int postion) {
        int index = rightRecycleView.getChildLayoutPosition(view);
        LogUtil.i("index:" + index);
        LogUtil.i("postion:" + postion);
        LogUtil.i("content:" + dataRight.get(postion).getFirstChar());
        int scllPos = getFirstChaPos(dataRight.get(postion).getFirstChar());
        if (scllPos >= 0) {
            tv_bigSize.setVisibility(View.VISIBLE);
            tv_bigSize.setText(dataRight.get(postion).getFirstChar() + "");
            smoothMoveToPosition(lefttRecycleView, scllPos);
        }

    }

    private int getFirstChaPos(char mch) {
        int index = -1;
        for (int i = 0; i < dataLeft.size(); i++) {
            if (mch == dataLeft.get(i).getNameFirstCha()) {
                index = i;
                break;
            }
        }
        LogUtil.i("pos：" + index);
        return index;
    }

    /**
     * 滑动到指定位置
     */
    private int mToPosition = 0;

    private void smoothMoveToPosition(RecyclerView mRecyclerView, final int position) {
        // 第一个可见位置
        int firstItem = mRecyclerView.getChildLayoutPosition(mRecyclerView.getChildAt(0));
        // 最后一个可见位置
        int lastItem = mRecyclerView.getChildLayoutPosition(mRecyclerView.getChildAt(mRecyclerView.getChildCount() - 1));

        LogUtil.i("postion:" + position);
        LogUtil.i("firstItem:" + firstItem);
        LogUtil.i("lastItem:" + lastItem);

        if (position < firstItem) {
            // 第一种可能:跳转位置在第一个可见位置之前，使用smoothScrollToPosition
            mRecyclerView.smoothScrollToPosition(position);
        } else if (position <= lastItem) {
            // 第二种可能:跳转位置在第一个可见位置之后，最后一个可见项之前
            int movePosition = position - firstItem;
            if (movePosition >= 0 && movePosition < mRecyclerView.getChildCount()) {
                int top = mRecyclerView.getChildAt(movePosition).getTop();
                // smoothScrollToPosition 不会有效果，此时调用smoothScrollBy来滑动到指定位置
                mRecyclerView.smoothScrollBy(0, top);
            }
        } else {
            // 第三种可能:跳转位置在最后可见项之后，则先调用smoothScrollToPosition将要跳转的位置滚动到可见位置
            // 再通过onScrollStateChanged控制再次调用smoothMoveToPosition，执行上一个判断中的方法
            mRecyclerView.smoothScrollToPosition(position);
            mToPosition = position;
            mShouldScroll = true;
        }
        new android.os.Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                tv_bigSize.setVisibility(View.GONE);
            }
        }, 500);
    }

}
