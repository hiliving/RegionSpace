package dev.practice.com.region;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Region;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

/**
 * Created by HY on 2017/6/26.
 */

public class RegionView extends View {
    Region mCircleRegion;//圆的范围
    Path circlePath;//圆路径
    Paint mDefualtPaint;//普通画笔

    public RegionView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initview();
    }

    private void initview() {
        mDefualtPaint = new Paint();
        mDefualtPaint.setColor(Color.GRAY);
        circlePath = new Path();
        mCircleRegion = new Region();
    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        //在屏幕中间画一个圆 Path.Direction.CW顺时针方向，CWW是逆时针方向，结果是一样的
        circlePath.addCircle(w/2,h/2,200, Path.Direction.CW);
        //将剪裁边界设置为视图大小
        Region globalRegion = new Region(-w,-h,w,h);
        //将Path添加到Region中
        mCircleRegion.setPath(circlePath,globalRegion);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                int x = (int) event.getX();
                int y = (int) event.getY();
                //点击区域判断
                if (mCircleRegion.contains(x,y)){
                    Toast.makeText(this.getContext(),"点击了",Toast.LENGTH_SHORT).show();
                }
                break;
        }
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Path circle= circlePath;
        //绘制圆
        canvas.drawPath(circle,mDefualtPaint);
    }
}
