package exam.qyw.test.myapplication.activitys;

import android.Manifest;
import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaMetadataRetriever;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import exam.qyw.test.myapplication.BuildConfig;
import exam.qyw.test.myapplication.R;
import exam.qyw.test.myapplication.adapter.MediaAdapter;
import exam.qyw.test.myapplication.base.BaseActivity;
import exam.qyw.test.myapplication.bean.MediaBean;
import exam.qyw.test.myapplication.constans.Constant;
import exam.qyw.test.myapplication.utils.ItemOnClickListener;
import exam.qyw.test.myapplication.utils.LogUtil;
import exam.qyw.test.mylibrary.Utils.ToastUtil;

/**
 * Created by Author:qyw
 * on 2018/11/20.
 * QQ:448739075
 * 描述：
 */
public class MediaTestActivity extends BaseActivity implements ItemOnClickListener{
    @BindView(R.id.recycleView)
    RecyclerView recyclerView;
    private MediaAdapter adapter;
    private List<MediaBean> mData = new ArrayList<>();

    @Override
    public int innitLayout() {
        return R.layout.activity_media_test;
    }

    @Override
    public void innitData() {
        innitToolBar("RecycleView 显示 Media");
        adapter = new MediaAdapter(getmActivity(), mData,this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        DividerItemDecoration itemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        itemDecoration.setDrawable(getDrawable(R.drawable.shape_divider));
        recyclerView.addItemDecoration(itemDecoration);
        recyclerView.setAdapter(adapter);
        /**
         * 既然是动画，就会有时间，我们把动画执行时间变大一点来看一看效果
         */
        DefaultItemAnimator defaultItemAnimator = new DefaultItemAnimator();
        defaultItemAnimator.setAddDuration(3000);
        defaultItemAnimator.setRemoveDuration(3000);
        recyclerView.setItemAnimator(defaultItemAnimator);
        //检查权限
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            int value = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE);
            if (value == PackageManager.PERMISSION_GRANTED) {
                getData();
            } else {
                requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, Constant.PERMISON_RRQUEST_CODE);
            }
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == Constant.PERMISON_RRQUEST_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                LogUtil.i("onRequestPermissionsResult_MediaTestAC");
                getData();
            }
        }
    }

    private void getData() {
        getLoaderManager().initLoader(0, null, mLoaderCallbacks);
    }


    private LoaderManager.LoaderCallbacks<Cursor> mLoaderCallbacks = new LoaderManager.LoaderCallbacks<Cursor>() {
        @NonNull
        @Override
        public Loader<Cursor> onCreateLoader(int i, @Nullable Bundle bundle) {
            CursorLoader cursorLoader = new CursorLoader(getmActivity(), MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                    null, null, null, null);
            return cursorLoader;
        }

        @Override
        public void onLoadFinished(@NonNull Loader<Cursor> loader, Cursor data) {
            if (data != null) {
                LogUtil.i("volum_size:" + data.getCount());
                data.moveToFirst();
                do {
//                    LogUtil.i("Audio.Media.DATA:"+
//                            data.getString(data.getColumnIndexOrThrow(MediaStore.Audio.Media.DATA)));
//                    LogUtil.i("Audio.Media.DISPLAY_NAME:"+
//                            data.getString(data.getColumnIndexOrThrow(MediaStore.Audio.Media.DISPLAY_NAME)));
//                    LogUtil.i("Audio.Media.DURATION:"+
//                            data.getString(data.getColumnIndexOrThrow(MediaStore.Audio.Media.DURATION)));
//                    LogUtil.i("Audio.Media.ALBUM_ID:"+
//                            data.getString(data.getColumnIndexOrThrow(MediaStore.Audio.Media.ALBUM_ID)));
//                    LogUtil.i("Audio.Media.ARTIST:"+
//                            data.getString(data.getColumnIndexOrThrow(MediaStore.Audio.Media.ARTIST)));
//
//                    LogUtil.i("Audio.Media.Cover:"+
//                            getAlbumArt(data.getString(data.getColumnIndexOrThrow(MediaStore.Audio.Media.ALBUM_ID))));

                    String imageUrl = data.getString(data.getColumnIndexOrThrow(MediaStore.Audio.Media.DATA));
                    String name = data.getString(data.getColumnIndexOrThrow(MediaStore.Audio.Media.DISPLAY_NAME));
                    long duration = data.getLong(data.getColumnIndexOrThrow(MediaStore.Audio.Media.DURATION));
//                    long size=data.getLong(data.getColumnIndexOrThrow(MediaStore.Audio.Media.SIZE));
                    if(imageUrl.endsWith(".mp3")) {
                        MediaBean bean = new MediaBean();
                        bean.setName(name);
                        bean.setCoverPath("");
                        bean.setDuration(duration);
                        bean.setImageUrl(imageUrl);
                        bean.setBitmap(getAlbumArt(imageUrl));

                        mData.add(bean);
                    }

                } while (data.moveToNext());
            }
            adapter.notifyDataSetChanged();
        }

        @Override
        public void onLoaderReset(@NonNull Loader<Cursor> loader) {

        }
    };

    /**
     * 根据专辑ID获取专辑封面图
     *
     * @param mediaUri 专辑ID
     * @return
     */
    private Bitmap getAlbumArt(String mediaUri) {
        MediaMetadataRetriever mediaMetadataRetriever = new MediaMetadataRetriever();
        mediaMetadataRetriever.setDataSource(mediaUri);
        byte[] picture = mediaMetadataRetriever.getEmbeddedPicture();
        if(picture==null||picture.length<0){
            return null;
        }
        LogUtil.i("imageUrl:"+mediaUri);
        Bitmap bitmap = BitmapFactory.decodeByteArray(picture, 0, picture.length);
        return bitmap;
    }

    public void onItemOnClick(View view) {
//        LogUtil.i("onItemOnClick:"+recyclerView.getChildAdapterPosition(view));
//        LogUtil.i("onItemOnClick:"+postion);
        int postion=recyclerView.getChildAdapterPosition(view);
        MediaBean bean = mData.get(postion);
        Intent intent = new Intent(Intent.ACTION_VIEW);
        File file = new File(bean.getImageUrl());
        LogUtil.i("file is exists:" + file.exists());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            Uri contentUri = FileProvider.getUriForFile(this, BuildConfig.APPLICATION_ID + ".fileProvider", file);
            intent.setDataAndType(contentUri, "audio/mp3");
            LogUtil.i("contentUri:" + contentUri.getPath());
        } else {
            Uri uri = Uri.fromFile(file);
            intent.setDataAndType(uri, "audio/mp3");
        }
        startActivity(intent);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.action_add:
                break;
            case R.id.action_remove:
                mData.remove(0);
                adapter.notifyDataSetChanged();
                break;
        }
        return true;
    }


}
