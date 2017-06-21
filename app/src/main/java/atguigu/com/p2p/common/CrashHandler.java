package atguigu.com.p2p.common;

import android.content.Context;
import android.os.Build;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;

/**
 * 作者：李银庆 on 2017/6/21 12:19
 */
public class CrashHandler implements Thread.UncaughtExceptionHandler {

    //设置单例
    private CrashHandler() {
    }

    ;

    private static CrashHandler crashHandler = new CrashHandler();

    public static CrashHandler getInstence() {
        return crashHandler;
    }


    private Context context;

    public void init(Context context) {
        this.context = context;
        //告诉系统，崩溃操作，有我执行
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    @Override
    public void uncaughtException(Thread t, Throwable e) {

        Log.d("crash", "uncaughtException: " + e.getMessage());

        //告诉用户，当前线程为子线程，要切换到主线程

        new Thread() {
            public void run() {
                //两个方法里面是主线程
                Looper.prepare();
                Toast.makeText(context, "请再启动", Toast.LENGTH_SHORT).show();
                Looper.loop();
            }
        }.start();

        //收集异常信息
        collection(e);
        //杀死当前进程
        android.os.Process.killProcess(android.os.Process.myPid());
        //退出虚拟机，参数，除了0意外都是非正常退出
        System.exit(0);

    }

    //手机异常信息的时候，注意手机版本号，系统类型等等信息 用的Builde类
    private void collection(Throwable e) {
        String version = Build.BOARD;
        //手机信息发送给服务器
    }
}
