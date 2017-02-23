package zph.zhjx.com.firstroom;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

import zph.zhjx.com.firstroom.adapter.RoomAdapter;
import zph.zhjx.com.firstroom.bean.Message01;

public class MainActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {
    SwipeRefreshLayout swipeRefreshLayout;
    List<Message01> listbean;
    ListView listView;
    RoomAdapter adapter;
    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 1:
                    adapter=new RoomAdapter(MainActivity.this,listbean);
                    listView.setAdapter(adapter);
                    break;
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView= (ListView) findViewById(R.id.listview);
        swipeRefreshLayout= (SwipeRefreshLayout) findViewById(R.id.swipeRefreshLayout);
        setSwipeRefreshLayout();
        LoadingDate();

    }

    private void setSwipeRefreshLayout() {
        swipeRefreshLayout.setOnRefreshListener(this);
        // 设置下拉圆圈上的颜色，蓝色、绿色、橙色、红色
        swipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_bright, android.R.color.holo_green_light,
                android.R.color.holo_orange_light, android.R.color.holo_red_light);
        swipeRefreshLayout.setDistanceToTriggerSync(200);// 设置手指在屏幕下拉多少距离会触发下拉刷新
        swipeRefreshLayout.setProgressBackgroundColor(R.color.colorbasse); // 设定下拉圆圈的背景
        swipeRefreshLayout.setSize(SwipeRefreshLayout.DEFAULT); // 设置圆圈的大小
    }

    private void LoadingDate(){


        new Thread() {
            @Override
            public void run() {
                super.run();
                try {

                    Document doc = Jsoup.connect("http://bj.01fy.cn/sale/").get();
                    Element list = doc.getElementById("list");
                    Elements links = list.getElementsByTag("li");
                    Log.i("TAG","list的个数是："+links.size());
                    listbean=new ArrayList<Message01>();
                    for(int i=0;i<links.size();++i){

                        Elements div01 = links.get(i).getElementsByClass("div01");
                        String url=div01.get(0).getElementsByTag("a").attr("href")+"";
                        String title=div01.get(0).text().toString()+"";

                        Elements div02 = links.get(i).getElementsByClass("div02");
                        String room=div02.get(0).text().toString()+"";

                        Elements div03 = links.get(i).getElementsByClass("div03");
                        String value=div03.get(0).text().toString()+"";

                        Elements div04 = links.get(i).getElementsByClass("div04");
                        String time=div04.get(0).text().toString()+"";
                        Message01 message01=new Message01();
                        message01.setId(i);
                        message01.setData(time);
                        message01.setRoom(room);
                        message01.setTitle(title);
                        message01.setUrl(url);
                        message01.setValue(value);
                        listbean.add(message01);
                    }
                    handler.sendEmptyMessage(1);
                }catch (Exception e){e.printStackTrace();
                }
            }
        }.start();
    }

    @Override
    public void onRefresh() {

        LoadingDate();
    }
}
