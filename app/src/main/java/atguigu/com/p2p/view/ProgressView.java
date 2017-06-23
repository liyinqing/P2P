package atguigu.com.p2p.view;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import atguigu.com.p2p.R;
import atguigu.com.p2p.utils.UIUtils;


/**
 * 作者：李银庆 on 2017/6/23 09:58
 */
public class ProgressView extends View{

    private Paint paint ;
    private int paintColor= Color.BLACK;

    private int width;
    private int height;

    private int strokeWidth = UIUtils.dip2px(10) ;
    private int swepAngle = 0;

    private int textColor;

    public ProgressView(Context context, AttributeSet attrs) {
        super(context, attrs);
        //初始化画笔
        init();
        /**
         * 自定义属性散步
         * 1、创建attrs文件
         * 2、在自定义控件的构造方法中，实例化arrts对象，并获取对应的属性值
         * 3、在布局文件中使用自定义属性
         * 4、释放资源
         */
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.ProgressView);
        int textColor = typedArray.getColor(R.styleable.ProgressView_textColor, Color.GRAY);
        this.textColor = textColor;

        int paintColor = typedArray.getColor(R.styleable.ProgressView_paintColor, Color.DKGRAY);
        this.paintColor = paintColor;
        //释放资源
        typedArray.recycle();



    }

    private void init() {
        paint = new Paint();

        //设置抗锯齿
        paint.setAntiAlias(true);

        //设置填充样式三种样式
        //1、描边 stroke 2、填充 fill 3、填充又描边 stroke and fill
        paint.setStyle(Paint.Style.STROKE);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //得到控件的高、宽
        height = getHeight();
        width = getWidth();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        /**
         * 1、画圆
         */

        //设置圆环厚度
        paint.setStrokeWidth(strokeWidth);
        //设置画笔颜色
        paint.setColor(paintColor);
        //圆的中心点坐标
        int cx = width/2;
        int cy = height/2;
        int radius  = cx-strokeWidth/2;
        canvas.drawCircle(cx,cy,radius,paint);

        /**
         * 2、画弧(arc)
         * 画弧的坐标是圆环内圆的矩形坐标
         *
//         */
        paint.setColor(textColor);
        RectF rectF = new RectF();
        rectF.set(strokeWidth/2,strokeWidth/2,width-strokeWidth/2,width-strokeWidth/2);
        canvas.drawArc(rectF,0,swepAngle,false,paint);

        /**
         * 3、画文字
         *
         * x、y字体矩形右下角的坐标
         * 1、获取字体控件的宽高
         */

        //设置画笔的厚度
        paint.setColor(textColor);
        paint.setStrokeWidth(0);
        String str = swepAngle +"%";
        paint.setTextSize(30);
        Rect rect = new Rect();
        paint.getTextBounds(str,0,str.length(),rect);
        int textWidth = rect.width();
        int textHeight = rect.height();
        float x = width/2 -textWidth/2 ;
        float y = height/2 + textHeight/2;
        canvas.drawText(str,x,y,paint);
    }

    /*
    * 面试题：
    * invalidate和postInvalidate的区别是什么
    * invalidate是主线程进行强制重绘
    * postInvalidate是分线程进行强制重绘
    * 设置文字百度比，动态
    * 和圆弧
    * 用属性动画 ValueAnimantion
    * */
    public void setSweepAngle(int sweepangle){

        //运用属性动画的监听实现进度条动画的效果
        ValueAnimator animator = ValueAnimator.ofInt(0,sweepangle);
        animator.setDuration(200);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {

                int a  = (int) animation.getAnimatedValue();

                ProgressView.this.swepAngle = a;
                invalidate();
            }
        });

        animator.start();
    }

}
