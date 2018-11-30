package exam.qyw.test.javalib;

/**
 * Created by Author:qyw
 * on 2018/11/30.
 * QQ:448739075
 * 描述：
 */
public class Student {
    private String name;
    private int id;
    private int age;

    public void canStudy(){
        System.out.println("Student canStudy");
    }

    //方法的重载，同一个类中 方法名字相同，参数不相同
    public void run(){

    }
    public void run(int speed){

    }
}
