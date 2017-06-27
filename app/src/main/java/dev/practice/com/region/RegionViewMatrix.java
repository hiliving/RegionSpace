package dev.practice.com.region;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.CornerPathEffect;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.gcssloop.view.utils.CanvasAidUtils;

/**
 * Created by HY on 2017/6/27.
 */

public class RegionViewMatrix extends View {
    float down_x = -1;
    float down_y = -1;
    private int mWidth ;
    private int mHeight;
    Paint mPaint;
    public RegionViewMatrix(Context context, AttributeSet attrs) {
        super(context, attrs);
        mPaint =new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.GRAY);
        mWidth = this.getWidth();
        mHeight = this.getHeight();

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:
                down_x = event.getRawX();
                down_y = event.getRawY();
                invalidate();
                break;
            case MotionEvent.ACTION_CANCEL:
            case MotionEvent.ACTION_UP:
                down_x=down_y=-1;
                invalidate();
                break;
        }
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        float[] pts = {down_x,down_y};
        //绘制触摸坐标系 灰色
        drawTouchCordinateSpace(canvas);
        //注意画布平移
        canvas.translate(getWidth()/2,getHeight()/2);
        //绘制平移后的坐标系 红色
        drawTranslateCoordinateSpace(canvas);
        //如果没有就返回
        if (pts[0]==-1&&pts[1]==-1) return;
        //获取当前矩阵的逆矩阵
        Matrix invertMatrix = new Matrix();
        canvas.getMatrix().invert(invertMatrix);

        //使用mapPoints 将触摸坐标转化为画布坐标
        invertMatrix.mapPoints(pts);

        //在触摸位置绘制一个小圆
        canvas.drawCircle(pts[0],pts[1],20,mPaint);
    }

    private void drawTouchCordinateSpace(Canvas canvas) {
        canvas.save();
        canvas.translate(10,10);
        CanvasAidUtils.set2DAxisLength(1000,0,1400,0);
        CanvasAidUtils.setLineColor(Color.GRAY);
        CanvasAidUtils.draw2DCoordinateSpace(canvas);
        canvas.restore();
    }

    private void drawTranslateCoordinateSpace(Canvas canvas) {
        CanvasAidUtils.set2DAxisLength(500,500,700,700);
        CanvasAidUtils.setLineColor(Color.RED);
        CanvasAidUtils.draw2DCoordinateSpace(canvas);
    }


}
