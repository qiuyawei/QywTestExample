package exam.qyw.test.javalib;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MyClass {
    private static final int PORT = 8882;
    private List<Socket> mClientList = new ArrayList<Socket>();
    private ServerSocket server = null;
    private ExecutorService mExecutors = null; // �̳߳ض���

    public static void main(String[] args) {
        new MyClass();
    }

    /**
     * ���췽�����������������������ȴ��ͻ�������
     */
    public MyClass() {
        try {
            server = new ServerSocket(PORT);
            mExecutors = Executors.newCachedThreadPool(); // �����̳߳�
            System.out.println("���������������ȴ��ͻ�������...");
            Socket client = null;
            /*
             * ����ѭ���ȴ�����ͻ��˵����ӣ�����һ��������һ���߳̽��й���
             */
            while (true) {
                client = server.accept();
                // �ѿͻ��˷��뼯����
                mClientList.add(client);
                mExecutors.execute(new Service(client)); // ����һ���̣߳������غ�ӿͻ��˷�������Ϣ
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    class Service implements Runnable {
        private Socket socket;
        private BufferedReader in = null;
        private String message = "";

        public Service(Socket socket) {
            this.socket = socket;
            try {
                in = new BufferedReader(new InputStreamReader(
                        socket.getInputStream()));// �������������
                // �ͻ���ֻҪһ�������������㷢�����ӳɹ�����Ϣ
                message = "��������ַ��" + this.socket.getInetAddress();
                this.sendMessage(message);
                message = "��ǰ��������:" + mClientList.size();
                this.sendMessage(message);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        @Override
        public void run() {
            try {
                while (true) {
                    if ((message = in.readLine()) != null) {
                        // ���ͻ��˷��͵���ϢΪ��exitʱ���ر�����
                        if (message.equals("exit")) {
                            closeSocket();
                            break;
                        } else {
                            // ���տͻ��˷���������Ϣmessage��Ȼ��ת�����ͻ��ˡ�
                            message = socket.getInetAddress() + ":" + message;
                            this.sendMessage(message);
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        /**
         * �رտͻ���
         *
         * @throws IOException
         */
        public void closeSocket() throws IOException {
            mClientList.remove(socket);
            in.close();
            message = "����:" + socket.getInetAddress() + "�ر�����\nĿǰ����:"
                    + mClientList.size();
            socket.close();
            this.sendMessage(message);
        }

        /**
         * �����յ���Ϣת����ÿһ���ͻ���
         *
         * @param msg
         */

        public void sendMessage(String msg) {
            System.out.println(msg);// ���ڿ���̨���
            int count = mClientList.size();
            // �����ͻ��˼���
            for (int i = 0; i < count; i++) {
                Socket mSocket = mClientList.get(i);
                PrintWriter out = null;
                try {
                    out = new PrintWriter(new BufferedWriter(
                            new OutputStreamWriter(mSocket.getOutputStream())),
                            true);// �������������
                    out.println(msg);// ת��
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
