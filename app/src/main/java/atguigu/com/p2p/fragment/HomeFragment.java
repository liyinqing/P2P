package atguigu.com.p2p.fragment;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.squareup.picasso.Picasso;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import atguigu.com.p2p.R;
import atguigu.com.p2p.base.BaseFragment;
import atguigu.com.p2p.bean.IndexBean;
import atguigu.com.p2p.common.AppNetConfig;
import atguigu.com.p2p.utils.HttpUtils;
import butterknife.Bind;

/**
 * 作者：李银庆 on 2017/6/20 16:51
 */
public class HomeFragment extends BaseFragment {

    @Bind(R.id.base_title)
    TextView baseTitle;
    @Bind(R.id.base_back)
    ImageView baseBack;
    @Bind(R.id.base_setting)
    ImageView baseSetting;
    @Bind(R.id.banner)
    Banner banner;
    @Bind(R.id.tv_home_product)
    TextView tvHomeProduct;
    @Bind(R.id.tv_home_yearrate)
    TextView tvHomeYearrate;


    @Override
    protected void initTitle() {

    }




    public void initData() {
        //联网获取数据
        loginData();
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_home;
    }

    private void loginData() {
//        AsyncHttpClient httpClient = new AsyncHttpClient();
//
//        httpClient.get(AppNetConfig.INDEX,new AsyncHttpResponseHandler(){
//            @Override
//            public void onSuccess(String content) {
//                super.onSuccess(content);
//                //解析数据
//                parseJson(content);
//            }
//
//            @Override
//            public void onFailure(Throwable error, String content) {
//                super.onFailure(error, content);
//            }
//        });

        HttpUtils.getInstance().getNetData(AppNetConfig.INDEX, new HttpUtils.OnHttpClientListener() {
            @Override
            public void onSuccess(String content) {
                //解析数据
               parseJson(content);
            }

            @Override
            public void onFailure(String content) {

            }
        });


    }

    private void parseJson(String json) {

        //手动解析
       // manualParseJson(json);
        //框架解析
        IndexBean indexBean = JSON.parseObject(json, IndexBean.class);
        initBanner(indexBean);
    }

    private void initBanner(IndexBean indexBean) {

        List<IndexBean.ImageArrBean> imageArr = indexBean.getImageArr();
        List<String> images = new ArrayList<>();
        for(int i = 0; i < imageArr.size(); i++) {
            String imaurl = imageArr.get(i).getIMAURL();
            images.add(AppNetConfig.BASE_URL + imaurl);
        }

        //设置图片加载器
        banner.setImageLoader(new GlideImageLoader());
        //设置图片集合

        banner.setImages(images);
        //banner设置方法全部调用完毕时最后调用
        banner.start();

    }

    private void manualParseJson(String json) {
        IndexBean indexBean = new IndexBean();
        List<IndexBean.ImageArrBean> imageArr1 = new ArrayList<>();


        try {
            JSONObject jsonObject = new JSONObject(json);

            JSONArray imageArr = jsonObject.getJSONArray("imageArr");
            for(int i = 0; i < imageArr.length(); i++) {

                IndexBean.ImageArrBean imageArrayBean = new IndexBean.ImageArrBean();
                JSONObject jsonObject1 = imageArr.getJSONObject(i);
                String id = jsonObject1.getString("ID");
                imageArrayBean.setID(id);
                String imapaurl = jsonObject1.getString("IMAPAURL");
                imageArrayBean.setIMAPAURL(imapaurl);
                String imaurl = jsonObject1.getString("IMAURL");
                imageArrayBean.setIMAURL(imaurl);

                imageArr1.add(imageArrayBean);
            }

            indexBean.setImageArr(imageArr1);

            JSONObject proInfo = jsonObject.getJSONObject("proInfo");
            String id = proInfo.getString("id");
            String name = proInfo.getString("name");


        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    class  GlideImageLoader extends ImageLoader{

        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            //用picasso加载图片
            Picasso.with(context).load((String) path).into(imageView);
        }
    }


}
