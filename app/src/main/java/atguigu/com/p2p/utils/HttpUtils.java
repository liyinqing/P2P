package atguigu.com.p2p.utils;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

/**
 * 作者：李银庆 on 2017/6/21 16:27
 */
public class HttpUtils {
    private AsyncHttpClient asyncHttpClient;
    private static HttpUtils httpUtils = new HttpUtils();

    private HttpUtils(){
        asyncHttpClient = new AsyncHttpClient();
    };

    public static HttpUtils getInstance(){
        return httpUtils;
    }

    private OnHttpClientListener onHttpClientListener;


    public void getNetData(String url, final OnHttpClientListener onHttpClientListener){
        this.onHttpClientListener = onHttpClientListener;
        asyncHttpClient.get(url,handler);
    };

    private AsyncHttpResponseHandler handler = new AsyncHttpResponseHandler(){
        @Override
        public void onSuccess(String content) {
            super.onSuccess(content);

            if (onHttpClientListener != null) {
                onHttpClientListener.onSuccess(content);
            }
        }

        @Override
        public void onFailure(Throwable error, String content) {
            super.onFailure(error, content);

            if (onHttpClientListener != null) {
                onHttpClientListener.onFailure(content);
            }
        }

    };




    //写个接口
    public interface OnHttpClientListener{
        void onSuccess(String content);
        void onFailure(String content);
    }
}
