package exam.qyw.test.myapplication.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import exam.qyw.test.myapplication.R;
import exam.qyw.test.myapplication.bean.RightIndexBean;

/**
 * Created by Author:qyw
 * on 2018/11/28.
 * QQ:448739075
 * 描述：
 */
public class RightAdapter extends RecyclerView.Adapter<RightAdapter.ViewHolder> {
    private Context mContext;
    private ArrayList<RightIndexBean> mData;
    private OnItemClickListener mOnItemClickListener;
    public RightAdapter(Context context, ArrayList<RightIndexBean> data,OnItemClickListener onItemClickListener) {
        this.mContext = context;
        this.mData = data;
        this.mOnItemClickListener=onItemClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_right_view, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        RightIndexBean bean = mData.get(i);
        if (bean != null) {
            viewHolder.tv_rightIndex.setText(bean.getFirstChar() + "");
        }
        viewHolder.tv_rightIndex.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               mOnItemClickListener.onItemOnClick(v,i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_rightIndex)
        TextView tv_rightIndex;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public interface OnItemClickListener {
        public void onItemOnClick(View view, int postion);
    }
}
