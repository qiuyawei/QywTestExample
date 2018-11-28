package exam.qyw.test.myapplication.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.content.FileProvider;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.io.File;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import exam.qyw.test.myapplication.BuildConfig;
import exam.qyw.test.myapplication.R;
import exam.qyw.test.myapplication.bean.MediaBean;
import exam.qyw.test.myapplication.utils.ItemOnClickListener;
import exam.qyw.test.myapplication.utils.LogUtil;

/**
 * Created by Author:qyw
 * on 2018/11/20.
 * QQ:448739075
 * 描述：
 */
public class MediaAdapter extends RecyclerView.Adapter<MediaAdapter.ViewHolder> {
    private Context mContext;
    private List<MediaBean> mdata;
    private ItemOnClickListener itemOnClickListener;

    public MediaAdapter(Context context, List<MediaBean> data, ItemOnClickListener onClickListener) {
        this.mContext = context;
        this.mdata = data;
        this.itemOnClickListener = onClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, final int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_media, viewGroup, false);
//        View view=View.inflate(mContext,R.layout.item_media,viewGroup);
        ViewHolder viewHolder = new ViewHolder(view);
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemOnClickListener.onItemOnClick(v);
            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        LogUtil.i("onBindViewHolder:" + i);
        final MediaBean bean = mdata.get(i);
        if (bean.getBitmap() != null) {

            Glide.with(mContext).load(bean.getBitmap()).into(viewHolder.imageView);
        } else {
            Glide.with(mContext).load(R.mipmap.music).into(viewHolder.imageView);
        }
        viewHolder.mediaName.setText(bean.getName());
    }

    @Override
    public int getItemCount() {
        return mdata.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_mediaImage)
        ImageView imageView;
        @BindView(R.id.tv_mediaName)
        TextView mediaName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
