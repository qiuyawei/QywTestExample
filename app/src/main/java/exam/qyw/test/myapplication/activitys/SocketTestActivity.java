package exam.qyw.test.myapplication.activitys;

import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.OnClick;
import exam.qyw.test.myapplication.R;
import exam.qyw.test.myapplication.base.BaseActivity;
import exam.qyw.test.myapplication.utils.LogUtil;
import exam.qyw.test.myapplication.utils.OkhttpUtil;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by Author:qyw
 * on 2018/11/21.
 * QQ:448739075
 * 描述：
 */
public class SocketTestActivity extends BaseActivity {
    private static final String HOST = "192.168.191.1";//服务器地址
    private static final int PORT = 8882;//连接端口号
    private Socket socket = null;
    private BufferedReader in = null;
    private PrintWriter out = null;
    @BindView(R.id.et_content)
    EditText et_content;
    //接收线程发送过来信息，并用TextView追加显示
    public Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            LogUtil.i("serverMessage:" + (CharSequence) msg.obj);
//            tv_msg.append((CharSequence) msg.obj);
        }
    };

    @Override
    public int innitLayout() {
        return R.layout.activity_socket_test;
    }

    @OnClick({R.id.bt_closeSocket, R.id.bt_sendMessage})
    public void onClick(View view) {
        if (view.getId() == R.id.bt_closeSocket) {
            if (socket != null) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        DataOutputStream writer = null;
                        try {
                            writer = new DataOutputStream(socket.getOutputStream());
                            writer.writeUTF("exit");  // 写一个UTF-8的信息
                            writer.flush();
                            writer.close();
//                            socket.shutdownOutput();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();

            }
        } else {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    if (socket != null)
                        LogUtil.i("socket is ClosedAndConnect:" + socket.isClosed()+"/"+socket.isConnected());
                    String msg = et_content.getText().toString().trim();
                    if (socket != null && !socket.isClosed() && socket.isConnected()) {
                        DataOutputStream writer = null;
                        try {
                            writer = new DataOutputStream(socket.getOutputStream());
                            writer.writeUTF(msg);  // 写一个UTF-8的信息
                            writer.flush();
                            writer.close();
                            socket.shutdownOutput();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }).start();
        }
    }

    @Override
    public void innitData() {
        new AsyncTask<Integer, Integer, Integer>() {
            @Override
            protected Integer doInBackground(Integer... integers) {
                connection();
                return null;
            }


            @Override
            protected void onPostExecute(Integer integer) {
                super.onPostExecute(integer);
                if (socket != null) {
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            goConnection();

                        }
                    }).start();
                } else {
                    LogUtil.i("socket=null");
                }
            }
        }.execute();


    }


    /**
     * 连接服务器
     */
    private void connection() {
        try {
            socket = new Socket(HOST, PORT);//连接服务器
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            in = new BufferedReader(new InputStreamReader(socket
                    .getInputStream()));//接收消息的流对象
            out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(
                    socket.getOutputStream())), true);//发送消息的流对象
        } catch (final IOException ex) {
            ex.printStackTrace();
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    ShowDialog("连接服务器失败：" + ex.getMessage());
                }
            });
        }
    }

    /**
     * 如果连接出现异常，弹出AlertDialog！
     */
    public void ShowDialog(String msg) {
        new AlertDialog.Builder(this).setTitle("通知").setMessage(msg)
                .setPositiveButton("ok", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).show();
    }

    /**
     * 读取服务器发来的信息，并通过Handler发给UI线程
     */
    public void goConnection() {
        try {
            while (true) {//死循环守护，监控服务器发来的消息
                if (!socket.isClosed()) {//如果服务器没有关闭
                    if (socket.isConnected()) {//连接正常
                        if (!socket.isInputShutdown()) {//如果输入流没有断开
                            String getLine;
                            if ((getLine = in.readLine()) != null) {//读取接收的信息
                                getLine += "\n";
                                Message message = new Message();
                                message.obj = getLine;
                                mHandler.sendMessage(message);//通知UI更新
                            } else {

                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (socket != null && socket.isConnected()) {
            try {
                socket.close();
                LogUtil.i("socket closed success");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
