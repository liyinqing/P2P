package atguigu.com.p2p.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import atguigu.com.p2p.utils.UIUtils;
import butterknife.ButterKnife;

/**
 * 作者：李银庆 on 2017/6/21 16:53
 */
public abstract class BaseFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //判断没有传入布局
        if(getLayoutId() == 0){
            TextView view = new TextView(getContext());
            view.setText("你是小白啊");
            return view;
        }
        View view = UIUtils.inflate(getLayoutId());

        ButterKnife.bind(this,view);
        initView();
        initTitle();
        initData();
        initListener();
        return view;
    }

    private void initView() {

    }

    protected abstract void initTitle();

    protected abstract void initData();

    private void initListener() {

    }

    public abstract int getLayoutId();

    @Override
    public void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }
}
