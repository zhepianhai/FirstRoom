package zph.zhjx.com.firstroom.Fragment;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

import zph.zhjx.com.firstroom.Contact.Contact;
import zph.zhjx.com.firstroom.DetailActivity;
import zph.zhjx.com.firstroom.R;
import zph.zhjx.com.firstroom.adapter.RoomAdapter;
import zph.zhjx.com.firstroom.bean.Message01;

/**
 * A simple {@link Fragment} subclass.
 */
public class HuiLongGuanFragment extends Fragment implements AdapterView.OnItemClickListener {
    private static final String TAG="HuiLongGuanFragment";
    private View view;
    private PullToRefreshListView pullToRefreshListView;
//    private ListView listView;
    private RoomAdapter adapter;
    private List<Message01> listbean;

    public HuiLongGuanFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view=inflater.inflate(R.layout.fragment_hui_long_guan, container, false);
        initview();
        setPullToRefreshListView();
        LoadingDate();
        setListView();
        return view;
    }

    private void setListView() {
        pullToRefreshListView.setOnItemClickListener(this);
    }

    private void initview() {
        pullToRefreshListView= (PullToRefreshListView) view.findViewById(R.id.huilong_listview);
    }


    private void setPullToRefreshListView() {
        pullToRefreshListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
            @Override
            public void onRefresh(PullToRefreshBase<ListView> refreshView) {
                String label = DateUtils.formatDateTime(getActivity(), System.currentTimeMillis(),
                        DateUtils.FORMAT_SHOW_TIME | DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_ABBREV_ALL);
                // Update the LastUpdatedLabel
                refreshView.getLoadingLayoutProxy().setLastUpdatedLabel(label);
                // Do work to refresh the list here.
                LoadingDate();
            }
        });
    }



    private void LoadingDate(){


        new Thread() {
            @Override
            public void run() {
                super.run();
                try {

                    Document doc = Jsoup.connect(Contact.Url_HuiLong).get();
                    Element list = doc.getElementById("list");
                    Elements links = list.getElementsByTag("li");
                    Log.i("TAG","list的个数是："+links.size());
                    listbean=new ArrayList<Message01>();
                    for(int i=0;i<links.size();++i){

                        Elements div01 = links.get(i).getElementsByClass("div01");
                        String url=Contact.Url_Main+div01.get(0).getElementsByTag("a").attr("href")+"";
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

    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 1:
                    adapter=new RoomAdapter(getActivity(),listbean);
                    pullToRefreshListView.setAdapter(adapter);
                    break;
            }
        }
    };

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Message01 message01= (Message01) adapter.getItem(i);
        Intent intent=new Intent(getActivity(), DetailActivity.class);
        intent.putExtra("detail",message01);
        getActivity().startActivity(intent);
    }
}



