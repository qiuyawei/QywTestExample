package exam.qyw.test.myapplication.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;

import butterknife.ButterKnife;
import exam.qyw.test.myapplication.R;
import me.ele.uetool.UETool;

public abstract class BaseActivity extends AppCompatActivity {
    private Activity mActivity;
    private ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(innitLayout());
        mActivity = this;
        ButterKnife.bind(this);
        innitData();
//        UETool.showUETMenu();

    }

    /**
     * 当前activity 布局文件
     *
     * @return
     */
    public abstract int innitLayout();

    /**
     * 当前activity 初始化数据
     *
     * @return
     */
    public abstract void innitData();

    public Activity getmActivity() {
        return mActivity;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void innitToolBar(String title) {
        actionBar = getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle(title);
    }

}
