package zph.zhjx.com.firstroom.bean;

import java.io.Serializable;

/**
 * Created by adminZPH on 2017/2/22.
 */

public class Message01 implements Serializable{
    private int id;
    private String url;
    private String title;
    private String room;
    private String value;
    private String data;

    @Override
    public String toString() {
        return "Message01{" +
                "id=" + id +
                ", url='" + url + '\'' +
                ", title='" + title + '\'' +
                ", room='" + room + '\'' +
                ", value='" + value + '\'' +
                ", data='" + data + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
