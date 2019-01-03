package exam.qyw.test.myapplication.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import exam.qyw.test.myapplication.R;

public class MaxRecyAdapter extends RecyclerView.Adapter<MaxRecyAdapter.ViewHolder> {
    private ArrayList<String> mData;
    private Context mContext;


    public MaxRecyAdapter(Context context,ArrayList<String> data){
        this.mData=data;
        this.mContext=context;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View holdeView=LayoutInflater.from(mContext).inflate(R.layout.item_spinner,null);
        ViewHolder viewHolder=new ViewHolder(holdeView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int postion) {
        String name=mData.get(postion);
        if(!TextUtils.isEmpty(name)) {
            viewHolder.tvName.setText(name);
        }
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.tv_mediaName)
        TextView tvName;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
