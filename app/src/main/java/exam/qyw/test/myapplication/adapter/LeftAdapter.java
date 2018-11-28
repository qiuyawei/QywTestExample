package exam.qyw.test.myapplication.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import exam.qyw.test.myapplication.R;
import exam.qyw.test.myapplication.bean.ContactBean;

/**
 * Created by Author:qyw
 * on 2018/11/28.
 * QQ:448739075
 * 描述：
 */
public class LeftAdapter extends RecyclerView.Adapter<LeftAdapter.ViewHolder> {
    private Context mContext;
    private ArrayList<ContactBean> mData;

    public LeftAdapter(Context context, ArrayList<ContactBean> data) {
        this.mContext = context;
        this.mData = data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_left_contacts, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int postion) {
        ContactBean bean = mData.get(postion);
        viewHolder.tv_firstLetter.setText(bean.getNameFirstCha()+"");
        //判断是否已经显示了标题
        if(postion>=1){
            if(mData.get(postion-1).getNameFirstCha()==bean.getNameFirstCha()){
                viewHolder.tv_firstLetter.setVisibility(View.GONE);
            }
        }
        viewHolder.tv_name.setText(bean.getName());
        viewHolder.tv_phone.setText(bean.getPhone());
        if(postion==0){
            viewHolder.tv_firstLetter.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_firstLetter)
        TextView tv_firstLetter;
        @BindView(R.id.tv_name)
        TextView tv_name;
        @BindView(R.id.tv_phone)
        TextView tv_phone;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
