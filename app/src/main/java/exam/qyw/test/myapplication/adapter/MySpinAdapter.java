package exam.qyw.test.myapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import exam.qyw.test.myapplication.R;

public class MySpinAdapter extends BaseAdapter {
    ArrayList<String> titls;
    Context mContext;
    public MySpinAdapter(Context context,ArrayList<String> data){
        this.mContext=context;
        this.titls=data;
    }
    @Override
    public int getCount() {
        return titls.size();
    }

    @Override
    public Object getItem(int position) {
        return titls.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView=LayoutInflater.from(mContext).inflate(R.layout.item_spinner,null);
        TextView title=convertView.findViewById(R.id.tv_mediaName);
        title.setText(titls.get(position));
        return convertView;
    }
}
