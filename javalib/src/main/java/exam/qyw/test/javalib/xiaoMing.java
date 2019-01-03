package exam.qyw.test.javalib;

/**
 * Created by Author:qyw
 * on 2018/11/30.
 * QQ:448739075
 * 描述：
 */
public class xiaoMing extends Student {
    private static MyCallListener myCallListener;
    private String mName;
    public xiaoMing(String name){
        this.mName=name;
    }
    public void canStudy(String objectName){
        System.out.print(mName);
    }
    public void showCall(){
    }
}
