package exam.qyw.test.myapplication.bean;

/**
 * Created by Author:qyw
 * on 2018/11/28.
 * QQ:448739075
 * 描述：
 */
public class ContactBean {
    private String name;
    private char nameFirstCha;//姓名的第一个首字母
    private String phone;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public char getNameFirstCha() {
        return nameFirstCha;
    }

    public void setNameFirstCha(char nameFirstCha) {
        this.nameFirstCha = nameFirstCha;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
