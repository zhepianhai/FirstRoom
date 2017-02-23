package zph.zhjx.com.firstroom;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import java.util.ArrayList;
import java.util.List;

import zph.zhjx.com.firstroom.Fragment.ALlFragment;
import zph.zhjx.com.firstroom.Fragment.HuiLongGuanFragment;
import zph.zhjx.com.firstroom.view.TopIndicator;

public class MenuActivity extends BaseActivity {
    private TopIndicator topSelete;
    private ViewPager vp;
    private List<Fragment> lists=new ArrayList<Fragment>();
    Context context;
    MyNewsPagerAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        setstatusbackground();
        initView();
        initViewPager();
        initTitleDate();
        setLists();
    }
    private void initTitleDate() {
        topSelete.setTopText(this,"全北京","回龙观");
    }

    private void initViewPager() {

        adapter=new MyNewsPagerAdapter(getSupportFragmentManager(),lists);
        vp.setAdapter(adapter);
        topSelete.setOnTopIndicatorListener(new TopIndicator.OnTopIndicatorListener() {
            @Override
            public void onIndicatorSelected(int index) {
                vp.setCurrentItem(index);
            }
        });
        vp.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                topSelete.setTabsDisplay(context, position);
            }
            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
            }
            @Override
            public void onPageScrollStateChanged(int position) {

            }
        });
    }

    private void initView() {
        topSelete= (TopIndicator) findViewById(R.id.topindicator_all);
        vp= (ViewPager) findViewById(R.id.viewpager_all);
    }

    class MyNewsPagerAdapter extends FragmentPagerAdapter {
        List<Fragment> lists;
        public MyNewsPagerAdapter(FragmentManager fm, List<Fragment> lists) {
            super(fm);
            this.lists=lists;
        }
        @Override
        public Fragment getItem(int position) {
            return lists.get(position);
        }
        @Override
        public int getCount() {
            return lists.size();
        }

    }


    /***
     * 添加fragment的,如果放在OnResume中的话，就会在第二次进入的时候出现重复添加的错误
     * */
    private void setLists() {
        lists.add(new ALlFragment());
        lists.add(new HuiLongGuanFragment());
        adapter.notifyDataSetChanged();
    }


}
