package zph.zhjx.com.firstroom;

import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.readystatesoftware.systembartint.SystemBarTintManager;

import zph.zhjx.com.firstroom.Contact.Contact;


public class BaseActivity extends FragmentActivity {
    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
    }

    public void setHeaderTitle(View headerView, String title, Contact.Position position) {
        TextView tv = (TextView) headerView.findViewById(R.id.headview_center);
        if (title == null) {
            tv.setText("TITLE");
        } else {
            tv.setText(title);
        }

        switch (position) {
            case LEFT:
                tv.setGravity(Gravity.LEFT);
                break;

            default:
                tv.setGravity(Gravity.CENTER);
                break;
        }

    }

    public void setHeaderTitle(View headerView, String title) {
        setHeaderTitle(headerView, title, Contact.Position.CENTER);
    }


    public void setHeaderImage(View headerView,  Contact.Position position, OnClickListener listener) {
        ImageView iv = null;
        switch (position) {
            case LEFT:
                iv = (ImageView) headerView.findViewById(R.id.headview_left);
                break;

            default:
                iv = (ImageView) headerView.findViewById(R.id.headview_left);
                break;
        }

//        iv.setImageResource(resId);

        if (listener != null) {
            iv.setOnClickListener(listener);
        }
    }

    public boolean isEmpty(EditText... editTexts) {
        for (EditText et : editTexts) {
            if (TextUtils.isEmpty(et.getText().toString())) {
                et.setText("不能为空哦！");
                return true;
            }
        }
        return false;
    }

    public void setstatusbackground() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            //透明导航栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
    }

    public void setstatusbarcolor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            //透明状态栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            //透明导航栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            SystemBarTintManager tintManager = new SystemBarTintManager(this);
            // 激活状态栏
            tintManager.setStatusBarTintEnabled(true);
            // enable navigation bar tint 激活导航栏
            tintManager.setNavigationBarTintEnabled(true);
            //设置系统栏设置颜色
            tintManager.setTintColor(R.color.colorhome_red);
            //给状态栏设置颜色
            tintManager.setStatusBarTintResource(R.color.colorhome_red);
            //给导航栏设置资源
            tintManager.setNavigationBarTintResource(R.color.colorhome_red_red);
        }
    }

}
