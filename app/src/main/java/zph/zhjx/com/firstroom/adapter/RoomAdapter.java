package zph.zhjx.com.firstroom.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import zph.zhjx.com.firstroom.R;
import zph.zhjx.com.firstroom.bean.Message01;


public class RoomAdapter extends BaseAdapter{
    private Context mContext;
    private List<Message01> mList;

    public RoomAdapter(Context mContext, List<Message01> mList) {
        this.mContext = mContext;
        this.mList = mList;
    }

    @Override
    public int getCount() {
        return mList!=null?mList.size():0;
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder=null;
        if (convertView==null){
            viewHolder=new ViewHolder();
            convertView= LayoutInflater.from(mContext).inflate(R.layout.item_room01,parent,false);
            viewHolder.room= (TextView) convertView.findViewById(R.id.room_item_room);
            viewHolder.time= (TextView) convertView.findViewById(R.id.room_item_time);
            viewHolder.title= (TextView) convertView.findViewById(R.id.room_item_title);
            viewHolder.value= (TextView) convertView.findViewById(R.id.room_item_value);
            convertView.setTag(viewHolder);
        }else {
            viewHolder= (ViewHolder) convertView.getTag();
        }
        Message01 message01=mList.get(position);
        viewHolder.room.setText("户型:"+message01.getRoom());
        viewHolder.title.setText(message01.getTitle());
        viewHolder.time.setText("时间:"+message01.getData());
        viewHolder.value.setText("价格:"+message01.getValue());
        return convertView;
    }
    class ViewHolder{
        private TextView title;
        private TextView room;
        private TextView time;
        private TextView value;
    }
    public void setData(List<Message01> list){
        this.mList=list;
        notifyDataSetChanged();
    }
}
