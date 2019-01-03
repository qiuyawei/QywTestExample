package exam.qyw.test.myapplication.activitys;

import android.app.Dialog;
import android.view.View;

import exam.qyw.test.myapplication.R;
import exam.qyw.test.myapplication.base.BaseActivity;

public class DialogActivity extends BaseActivity {
    private Dialog mDialog;
    @Override
    public int innitLayout() {
        return R.layout.activity_dialog_test;
    }

    @Override
    public void innitData() {
        innitDialog();
    }

    public void showPopu(View view){
        if(mDialog!=null){
            if(mDialog.isShowing()){
                mDialog.dismiss();
            }else {
                mDialog.show();
            }
        }
    }
    private void innitDialog(){
        mDialog=new Dialog(this,R.style.my_dialog);
        mDialog.setContentView(R.layout.view_popu);
    }
}
