package exam.qyw.test.myapplication.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.QuickContactBadge;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import exam.qyw.test.myapplication.R;
import exam.qyw.test.myapplication.bean.GoodsBean;
import exam.qyw.test.myapplication.callback.OnRecycleItemClickListener;

public class GoodsAdapter extends RecyclerView.Adapter<GoodsAdapter.ViewHolder> {
    private Context mContext;
    private ArrayList<GoodsBean> goodsBeans;
    private OnRecycleItemClickListener onRecycleItemClickListener;
    public GoodsAdapter(Context context,ArrayList<GoodsBean> beans,OnRecycleItemClickListener listener) {
        this.mContext=context;
        this.goodsBeans=beans;
        this.onRecycleItemClickListener=listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=LayoutInflater.from(mContext).inflate( R.layout.item_goods,null);
        ViewHolder holder=new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        viewHolder.title.setText(goodsBeans.get(i).totalTitle);
        InnerAdapter adapter=new InnerAdapter(mContext,goodsBeans.get(i).innerBeans);
        GridLayoutManager manager=new GridLayoutManager(mContext,3);
        viewHolder.reGride.setLayoutManager(manager);
        viewHolder.reGride.setAdapter(adapter);

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onRecycleItemClickListener.onItemClick(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return goodsBeans.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.reGride)
        RecyclerView reGride;
        @BindView(R.id.title)
        TextView title;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }
    }
}
