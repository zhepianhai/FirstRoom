package zph.zhjx.com.firstroom;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import zph.zhjx.com.firstroom.bean.Message01;

public class DetailActivity extends AppCompatActivity {
    private static final String TAG="DetailActivity";
    Message01 message01;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        message01= (Message01) getIntent().getSerializableExtra("detail");
        Log.i(TAG,"message01:"+message01.toString());
    }
}
