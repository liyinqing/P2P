package atguigu.com.p2p.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

import atguigu.com.p2p.R;
import atguigu.com.p2p.fragment.HomeFragment;
import atguigu.com.p2p.fragment.InvestFragment;
import atguigu.com.p2p.fragment.MoreFragment;
import atguigu.com.p2p.fragment.PropertyFragment;
import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.fl_main)
    FrameLayout flMain;
    @Bind(R.id.rb_home)
    RadioButton rbHome;
    @Bind(R.id.rb_invest)
    RadioButton rbInvest;
    @Bind(R.id.rb_property)
    RadioButton rbProperty;
    @Bind(R.id.rb_more)
    RadioButton rbMore;
    @Bind(R.id.rg_main)
    RadioGroup rgMain;


    private HomeFragment homeFragment;
    private InvestFragment investFragment;
    private PropertyFragment propertyFragment;
    private MoreFragment moreFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        initView();

        initData();

        initListener();
    }

    //实例化控件方法
    public <T> T instance(int id) {

        return (T) findViewById(id);
    }

    private void initListener() {

        rgMain.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switchFragment(checkedId);
            }
        });
    }

    private void switchFragment(int checkedId) {
        //开启事物
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        hideder(ft);
        switch (checkedId) {
            case R.id.rb_home:
                if (homeFragment == null) {
                    homeFragment = new HomeFragment();
                    ft.add(R.id.fl_main, homeFragment);
                } else {
                    ft.show(homeFragment);
                }
                break;
            case R.id.rb_invest:

                if (investFragment == null) {
                    investFragment = new InvestFragment();

                    ft.add(R.id.fl_main, investFragment);
                } else {
                    ft.show(investFragment);
                }
                break;
            case R.id.rb_property:
                if (propertyFragment == null) {
                    propertyFragment = new PropertyFragment();

                    ft.add(R.id.fl_main, propertyFragment);
                } else {
                    ft.show(propertyFragment);
                }
                break;
            case R.id.rb_more:
                if (moreFragment == null) {
                    moreFragment = new MoreFragment();

                    ft.add(R.id.fl_main, moreFragment);
                } else {
                    ft.show(moreFragment);
                }
                break;
        }
        ft.commit();
    }

    private void hideder(FragmentTransaction ft) {

        if (homeFragment != null) {
            ft.hide(homeFragment);
        }
        if (investFragment != null) {
            ft.hide(investFragment);
        }
        if (propertyFragment != null) {
            ft.hide(propertyFragment);
        }
        if (moreFragment != null) {
            ft.hide(moreFragment);
        }

    }

    private void initData() {

    }

    private void initView() {
        switchFragment(R.id.rb_home);
    }

    /**
     * 按两次退出
     */
    private boolean isExit = false;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK) {

            if (isExit) {
                finish();
            }
            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    isExit = false;
                }
            }, 2000);

            isExit = true;
            Toast.makeText(MainActivity.this, "再点一次退出", Toast.LENGTH_SHORT).show();

            return true;

        }
        return super.onKeyDown(keyCode, event);
    }
}
