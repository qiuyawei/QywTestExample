package exam.qyw.test.myapplication.activitys;

import android.annotation.SuppressLint;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ajguan.library.EasyRefreshLayout;
import com.ajguan.library.LoadModel;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.callback.ItemDragAndSwipeCallback;
import com.chad.library.adapter.base.listener.OnItemDragListener;
import com.chad.library.adapter.base.listener.OnItemSwipeListener;
import com.chad.library.adapter.base.loadmore.LoadMoreView;

import java.util.ArrayList;

import butterknife.BindView;
import exam.qyw.test.myapplication.R;
import exam.qyw.test.myapplication.adapter.MaxRecyAdapter;
import exam.qyw.test.myapplication.adapter.MyTestAdapter;
import exam.qyw.test.myapplication.base.BaseActivity;
import exam.qyw.test.myapplication.utils.LogUtil;
import exam.qyw.test.myapplication.utils.ToastUtil;
import exam.qyw.test.myapplication.view.CustomLoadView;
import exam.qyw.test.myapplication.view.MyRecycleView;
import exam.qyw.test.myapplication.view.load_mor_bottom;

/**
 * @Author qiuyawei
 * @CreateTime 2018/12/26 15:08
 * @Description 测试ScllerView 嵌套 recycleView 解决滑动冲突
 */
public class ScllerAndRecycleActivity extends BaseActivity {
    @BindView(R.id.easyRefresh)
    EasyRefreshLayout easyRefreshLayout;
    @BindView(R.id.recycleView)
    MyRecycleView recyclerView;
    private ArrayList<String> data = new ArrayList<>();
    private MyTestAdapter adapter;
    private View HeadView, bottomView, loadView, emptyView;
    private ItemTouchHelper mItemTouchHelper;
    private ItemDragAndSwipeCallback mItemDragAndSwipeCallback;
    private load_mor_bottom bottom;

    @Override
    public int innitLayout() {
        return R.layout.activity_recy_and_scllor;
    }

    @Override
    public void innitData() {
        innitRecycleView();

        LogUtil.i("getPackageResourcePath:"+ getPackageResourcePath());
        LogUtil.i("getPackageCodePath:"+ getPackageCodePath());
        LogUtil.i("getPackageCodePath:"+ getApplication().getFilesDir().getAbsolutePath());

    }

    private void innitRecycleView() {
        HeadView = LayoutInflater.from(getmActivity()).inflate(R.layout.head_view, (ViewGroup) recyclerView.getParent(), false);
        bottomView = LayoutInflater.from(getmActivity()).inflate(R.layout.bottom_view, (ViewGroup) recyclerView.getParent(), false);
        loadView = LayoutInflater.from(getmActivity()).inflate(R.layout.loading_more, (ViewGroup) recyclerView.getParent(), false);
        emptyView = LayoutInflater.from(getmActivity()).inflate(R.layout.empty_view, (ViewGroup) recyclerView.getParent(), false);


        LinearLayoutManager manager = new LinearLayoutManager(getmActivity());
        recyclerView.setLayoutManager(manager);
        DividerItemDecoration dividerItemDecoration=new DividerItemDecoration(getmActivity(),DividerItemDecoration.VERTICAL);
        dividerItemDecoration.setDrawable(getDrawable(R.drawable.shape_divider));
        recyclerView.addItemDecoration(dividerItemDecoration);
//        adapter=new MaxRecyAdapter(getmActivity(),data);
//        recyclerView.setAdapter(adapter);
        adapter = new MyTestAdapter(R.layout.item_media, data);

        recyclerView.setAdapter(adapter);

//        adapter.addHeaderView(HeadView);
        adapter.addFooterView(bottomView);
        adapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_BOTTOM);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {

            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                ToastUtil.toastShort(getmActivity(), "item_click:" + position);

            }
        });
        adapter.setPreLoadNumber(10);
        adapter.setEmptyView(emptyView);
        emptyView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addData();
            }
        });
        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                ToastUtil.toastShort(getmActivity(), "item_child_click:" + position);
            }
        });

        adapter.setLoadMoreView(new CustomLoadView());

        adapter.setOnItemDragListener(new OnItemDragListener() {
            @Override
            public void onItemDragStart(RecyclerView.ViewHolder viewHolder, int pos) {

            }

            @Override
            public void onItemDragMoving(RecyclerView.ViewHolder source, int from, RecyclerView.ViewHolder target, int to) {

            }

            @Override
            public void onItemDragEnd(RecyclerView.ViewHolder viewHolder, int pos) {

            }
        });

        mItemDragAndSwipeCallback = new ItemDragAndSwipeCallback(adapter);
        mItemTouchHelper = new ItemTouchHelper(mItemDragAndSwipeCallback);
        mItemTouchHelper.attachToRecyclerView(recyclerView);
        OnItemDragListener listener = new OnItemDragListener() {
            @Override
            public void onItemDragStart(RecyclerView.ViewHolder viewHolder, int pos) {
//                Log.d(TAG, "drag start");
                BaseViewHolder holder = ((BaseViewHolder) viewHolder);
//                holder.setTextColor(R.id.tv, Color.WHITE);
            }

            @Override
            public void onItemDragMoving(RecyclerView.ViewHolder source, int from, RecyclerView.ViewHolder target, int to) {
//                Log.d(TAG, "move from: " + source.getAdapterPosition() + " to: " + target.getAdapterPosition());
            }

            @Override
            public void onItemDragEnd(RecyclerView.ViewHolder viewHolder, int pos) {
//                Log.d(TAG, "drag end");
                BaseViewHolder holder = ((BaseViewHolder) viewHolder);
//                holder.setTextColor(R.id.tv, Color.BLACK);
            }
        };
        final Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setTextSize(20);
        paint.setColor(Color.BLACK);
        OnItemSwipeListener onItemSwipeListener = new OnItemSwipeListener() {
            @Override
            public void onItemSwipeStart(RecyclerView.ViewHolder viewHolder, int pos) {
                BaseViewHolder holder = ((BaseViewHolder) viewHolder);
//                holder.setTextColor(R.id.tv, Color.WHITE);
            }

            @Override
            public void clearView(RecyclerView.ViewHolder viewHolder, int pos) {
                BaseViewHolder holder = ((BaseViewHolder) viewHolder);
//                holder.setTextColor(R.id.tv, Color.BLACK);
            }

            @Override
            public void onItemSwiped(RecyclerView.ViewHolder viewHolder, int pos) {
            }

            @Override
            public void onItemSwipeMoving(Canvas canvas, RecyclerView.ViewHolder viewHolder, float dX, float dY, boolean isCurrentlyActive) {
//                canvas.drawColor(ContextCompat.getColor(getmActivity(), R.color.colorPrimary));
                canvas.drawText("Just some text", 0, 40, paint);
                LogUtil.i("dx:" + dX);
                LogUtil.i("dy:" + dY);


            }
        };
        //mItemDragAndSwipeCallback.setDragMoveFlags(ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT | ItemTouchHelper.UP | ItemTouchHelper.DOWN);
        mItemDragAndSwipeCallback.setSwipeMoveFlags(ItemTouchHelper.START | ItemTouchHelper.END);
        adapter.enableSwipeItem();
        adapter.setOnItemSwipeListener(onItemSwipeListener);
        adapter.enableDragItem(mItemTouchHelper);
        adapter.setOnItemDragListener(listener);
        easyRefreshLayout.addEasyEvent(new EasyRefreshLayout.EasyEvent() {
            @Override
            public void onLoadMore() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        addData();
                        if(data.size()>30){
                            easyRefreshLayout.loadNothing();
                            adapter.getFooterLayout().setVisibility(View.VISIBLE);
                            adapter.notifyDataSetChanged();
                        }else {
                            easyRefreshLayout.loadMoreComplete();
                        }
                    }
                }, 2000);
            }

            @Override
            public void onRefreshing() {

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        data.clear();
                        addData();
                        easyRefreshLayout.refreshComplete();
                    }
                }, 2000);
            }
        });
//        easyRefreshLayout.setLoadMoreView(loadView);
        bottom = new load_mor_bottom(getmActivity());
        easyRefreshLayout.setLoadMoreView(bottom);

    }

    private void addData() {
        if (data.size() > 30) {
//            bottom.loadNothing();
//            adapter.notifyDataSetChanged();
            easyRefreshLayout.loadNothing();
            return;
        }
        for (int i = 0; i < 15; i++) {
            data.add("name:" + i);
        }
        adapter.getFooterLayout().setVisibility(View.GONE);
        adapter.notifyDataSetChanged();
    }
}
