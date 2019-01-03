package exam.qyw.test.javalib;

public class ZhangSan extends Student {
    @Override
    public void run() {
        super.run();
        System.out.print("重载了父类方法");
    }
}
