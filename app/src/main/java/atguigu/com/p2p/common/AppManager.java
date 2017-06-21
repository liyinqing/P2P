package atguigu.com.p2p.common;

import android.app.Activity;

import java.util.Stack;

/**
 * 作者：李银庆 on 2017/6/21 10:02
 */
public class AppManager {

    private AppManager() {
    }

    ;

    private static AppManager appManager = new AppManager();


    private Stack<Activity> stack = new Stack<>();


    //饿汉单例
    public static AppManager getInstance() {
        return appManager;
    }
    //添加activity

    public void addActivity(Activity activity) {
        if (activity != null) {
            stack.add(activity);
        }
    }

    //移除activity，注意移除后集合长度减少，index错误，处理：循环反过来
    public void removeActivity(Activity activity) {
        if (activity != null) {
            for (int i = stack.size() - 1; i >= 0; i--) {
                //取出内存中的每个activity
                Activity currenterActivity = stack.get(i);
                //判断和传进来的相同，移除
                if (currenterActivity == activity) {
                    //关闭页面
                    currenterActivity.finish();
                    stack.remove(i);
                }
            }
        }
    }

    //移除stack里面的所有activity

    public void removeAll() {

        for (int i = 0; i < stack.size(); i++) {

            Activity activity1 = stack.get(i);
            if (activity1 != null) {

                activity1.finish();
                stack.remove(i);
            }

        }
    }

}
