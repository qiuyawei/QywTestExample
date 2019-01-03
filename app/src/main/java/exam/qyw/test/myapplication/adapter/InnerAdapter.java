package exam.qyw.test.myapplication.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import exam.qyw.test.myapplication.R;
import exam.qyw.test.myapplication.bean.InnerBean;

public class InnerAdapter extends RecyclerView.Adapter<InnerAdapter.ViewHolder> {
    private Context mContext;
    private ArrayList<InnerBean> data;
    public InnerAdapter(Context context,ArrayList<InnerBean> datas){
        this.mContext=context;
        this.data=datas;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=LayoutInflater.from(mContext).inflate(R.layout.item_inner_gride,null);
        ViewHolder holder=new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
