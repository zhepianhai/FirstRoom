package zph.zhjx.com.firstroom.bean;

import java.util.Arrays;

/**
 * Created by adminZPH on 2017/2/23.
 */

public class Message02 {
    private String title;//标题
    private String publictime;//发布时间
    private String size;//大小
    private String value;//价格
    private String roomtype;//类型
    private String addname;//小区名字
    private String address;//小区地址
    private String username;//联系人
    private String phone;//联系电话
    private String miaoshu;//描述
    private String[] image;//图片描述


    @Override
    public String toString() {
        return "Message02{" +
                "title='" + title + '\'' +
                ", publictime='" + publictime + '\'' +
                ", size='" + size + '\'' +
                ", value='" + value + '\'' +
                ", roomtype='" + roomtype + '\'' +
                ", addname='" + addname + '\'' +
                ", address='" + address + '\'' +
                ", username='" + username + '\'' +
                ", phone='" + phone + '\'' +
                ", miaoshu='" + miaoshu + '\'' +
                ", image=" + Arrays.toString(image) +
                '}';
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPublictime() {
        return publictime;
    }

    public void setPublictime(String publictime) {
        this.publictime = publictime;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getRoomtype() {
        return roomtype;
    }

    public void setRoomtype(String roomtype) {
        this.roomtype = roomtype;
    }

    public String getAddname() {
        return addname;
    }

    public void setAddname(String addname) {
        this.addname = addname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMiaoshu() {
        return miaoshu;
    }

    public void setMiaoshu(String miaoshu) {
        this.miaoshu = miaoshu;
    }

    public String[] getImage() {
        return image;
    }

    public void setImage(String[] image) {
        this.image = image;
    }
}
