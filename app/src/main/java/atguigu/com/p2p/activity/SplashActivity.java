package atguigu.com.p2p.activity;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import atguigu.com.p2p.R;
import atguigu.com.p2p.utils.UIUtils;
import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 作者：李银庆 on 2017/6/20 19:02
 */
public class SplashActivity extends AppCompatActivity {

    @Bind(R.id.iv_welcome_icon)
    ImageView ivWelcomeIcon;
    @Bind(R.id.splash_tv_version)
    TextView splashTvVersion;
    @Bind(R.id.activity_splash)
    RelativeLayout activitySplash;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);

        initView();
        initData();
        initListener();
    }

    private void initListener() {

    }

    private void initData() {
        //设置动画
        AlphaAnimation animation = new AlphaAnimation(0, 1);
        animation.setDuration(2000);

        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                //判断账号是否登录

                if(getLogin()){
                    //已登录进入主页面

                    startActivity(new Intent(SplashActivity.this,MainActivity.class));
                }else {
                    //进入登录页面
                    startActivity(new Intent(SplashActivity.this,LoginActivity.class));
                }
                //关闭动画、页面
                ivWelcomeIcon.clearAnimation();
                finish();
            }


            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        ivWelcomeIcon.startAnimation(animation);
    }

    //判断是否登录过
    private boolean getLogin() {
        return true;
    }


    private void initView() {
       // 在布局中写中文1
//        String s = splashTvVersion.getText().toString();
//        String format = String.format(s, getVersion());
//        splashTvVersion.setText(format);


        //在strings中标注2
//        String string = getResources().getString(R.string.main_name);
//        String format = String.format(string, getVersion());
//        splashTvVersion.setText(format);

        //封装进UIUtils3
        String s = UIUtils.stringFromat(R.string.main_name, getVersion());
        splashTvVersion.setText(s);
    }


    /**
     * 得当当前版本号
     * @return
     */
    private String getVersion() {
        try {
            PackageInfo packageInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
            int versionCode = packageInfo.versionCode;
            String versionName = packageInfo.versionName;
            return versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return "10";
    }
}
