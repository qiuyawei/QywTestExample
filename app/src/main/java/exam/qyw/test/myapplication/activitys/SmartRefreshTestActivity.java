package exam.qyw.test.myapplication.activitys;

import android.support.annotation.NonNull;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;

import butterknife.BindView;
import exam.qyw.test.myapplication.R;
import exam.qyw.test.myapplication.adapter.MyTestAdapter;
import exam.qyw.test.myapplication.base.BaseActivity;

public class SmartRefreshTestActivity extends BaseActivity {
    @BindView(R.id.recycleView)
    RecyclerView recyclerView;
    @BindView(R.id.smartRefresh)
    SmartRefreshLayout smartRefreshLayout;
    private MyTestAdapter adapter;
    private ArrayList<String> data = new ArrayList<>();

    @Override
    public int innitLayout() {
        return R.layout.activity_smart_refresh;
    }

    @Override
    public void innitData() {
//        getData();
        LinearLayoutManager manager = new LinearLayoutManager(getmActivity());
        recyclerView.setLayoutManager(manager);
        DividerItemDecoration itemDecoration = new DividerItemDecoration(getmActivity(), DividerItemDecoration.VERTICAL);
        itemDecoration.setDrawable(getDrawable(R.drawable.shape_divider));
        recyclerView.addItemDecoration(itemDecoration);
        adapter = new MyTestAdapter(R.layout.item_media, data);
        adapter.setEmptyView(LayoutInflater.from(getmActivity()).inflate(R.layout.empty_view,null));
        recyclerView.setAdapter(adapter);
        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                data.clear();
                getData();
                smartRefreshLayout.finishRefresh(2000);
                adapter.notifyDataSetChanged();
            }
        });
        smartRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                getData();
                smartRefreshLayout.finishLoadMore(2000);
                if (data.size() > 30)
                    smartRefreshLayout.finishLoadMore(false);
                adapter.notifyDataSetChanged();
            }
        });

    }

    private void getData() {
        for (int i = 0; i < 10; i++) {
            data.add("name:" + i);
        }
    }
}
