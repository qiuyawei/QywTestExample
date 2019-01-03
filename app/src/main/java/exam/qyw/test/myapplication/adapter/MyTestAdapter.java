package exam.qyw.test.myapplication.adapter;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseItemDraggableAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import exam.qyw.test.myapplication.R;
import exam.qyw.test.myapplication.utils.ToastUtil;

public class MyTestAdapter extends BaseItemDraggableAdapter<String,BaseViewHolder> {
    public MyTestAdapter(int layoutResId, @Nullable List<String> data) {
        super(layoutResId, data);
    }


    @Override
    protected void convert(BaseViewHolder helper, String item) {
        helper.setText(R.id.tv_mediaName,item);
        helper.addOnClickListener(R.id.tv_mediaName);
    }

}
