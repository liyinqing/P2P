package atguigu.com.p2p.common;

import android.app.Application;
import android.content.Context;

/**
 * 作者：李银庆 on 2017/6/20 18:53
 */
public class MyApplication extends Application {

    public static Context getContext() {
        return context;
    }

    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;

        //初始化CrashHanlder, 上线的时候在使用，现在都是测试没必要
       // CrashHandler.getInstence().init(this);

    }
}
