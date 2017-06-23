package atguigu.com.p2p.view;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ScrollView;

/**
 * 作者：李银庆 on 2017/6/23 14:58
 */
public class MyScrollView extends ScrollView {


    private View childAtView;

    public MyScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        if(getChildCount()>0){
            childAtView = getChildAt(0);

        }
    }

    private int lastY;

    private Rect rect = new Rect();

    private boolean isAnStart = false;

    @Override
    public boolean onTouchEvent(MotionEvent ev) {

        if(isAnStart && getChildCount() == 0){
            return super.onTouchEvent(ev);
        }

        int startY = (int) ev.getY();

        switch (ev.getAction()){
            case MotionEvent.ACTION_DOWN:
                lastY = startY;
                break;
            case MotionEvent.ACTION_MOVE:

                if(rect.isEmpty()) {
                    rect.set(childAtView.getLeft(),
                            childAtView.getTop(),
                            childAtView.getRight(),
                            childAtView.getBottom());
                }

                int dy = startY - lastY;

                childAtView.layout(childAtView.getLeft()
                        ,childAtView.getTop()+dy
                        ,childAtView.getRight()
                        ,childAtView.getBottom()+dy);
                lastY = startY;
                break;


            case MotionEvent.ACTION_UP:
                //设置动画平移的偏移量
                int transD = childAtView.getTop() -rect.top;
                /**
                 * 设置平移动画，后面四个参数：前两个是X轴，从哪里到哪里
                 *
                 * 后面是Y轴，从哪里到哪里
                 */
                TranslateAnimation animation = new TranslateAnimation(0,0,0,-transD);
                //设置动画播放时间
                animation.setDuration(200);
                //监听动画
                animation.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {
                        isAnStart = true;
                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {

                        //动画是视图动画，移动后属性没有移动，这里把属性也移动过来
                        childAtView.layout(rect.left,rect.top,rect.right,rect.bottom);
                        //释放资源
                        rect.setEmpty();

                        isAnStart = false;
                        //动画结束后一定要移除动画，否则画面会出现异常
                        childAtView.clearAnimation();
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
                //开启动画
                childAtView.startAnimation(animation);

                break;
        }

        return true;

    }
}
