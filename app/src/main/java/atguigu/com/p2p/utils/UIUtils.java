package atguigu.com.p2p.utils;

import android.content.Context;
import android.view.View;

import atguigu.com.p2p.common.MyApplication;

/**
 * 作者：李银庆 on 2017/6/20 18:51
 */
public class UIUtils {

    public static View inflate(int id){
        return View.inflate(getContext(),id,null);
    }


    private static Context getContext() {
        return MyApplication.getContext();
    }

    public static String stringFromat(int id,String value){
        String version = String.format(getString(id),value);
        return version;
    }

    private static String getString(int id) {

        String string = getContext().getResources().getString(id);
        return string;
    }

    ;
}
