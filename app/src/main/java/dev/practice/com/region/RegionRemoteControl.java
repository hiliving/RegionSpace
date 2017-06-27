package dev.practice.com.region;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Region;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by HY on 2017/6/27.
 */

public class RegionRemoteControl extends View {
    Path up_p,down_p,left_p,right_p,center_p;
    Region up,down,left,right,center;
    Matrix mMatrix = null;
    int CENTER = 0;
    int UP = 1;
    int RIGHT = 2;
    int DOWN = 3;
    int LEFT = 4;
    int touchFlag = -1;
    int currentFlag = -1;
    int mDefaultColor = Color.GRAY;
    private Paint mDefaultPaint;
    public RegionRemoteControl(Context context, AttributeSet attrs) {
        super(context, attrs);
        up_p = new Path();
        down_p = new Path();
        left_p = new Path();
        right_p = new Path();
        center_p = new Path();

        up = new Region();
        down = new Region();
        left = new Region();
        right = new Region();

        mDefaultPaint = new Paint();
        mDefaultPaint.setColor(mDefaultColor);
        mDefaultPaint.setAntiAlias(true);

        mMatrix = new Matrix();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mMatrix.reset();
        //注意这个区域的大小
        Region globalRegion = new Region(-w,-h,w,h);
        int minWidth = w>h?h:w;
        minWidth*=0.8;
        int br = minWidth /2;
        RectF bigCircle = new RectF(-br,-br,br,br);
    }

    public RegionRemoteControl(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    private MenuListener listener;
    public void setLisener(MenuListener lisener){
        this.listener = lisener;
    }
    /**
     * 点击事件监听
     */
    public interface MenuListener{
        void onCenterClick();
        void onUpClick();
        void onRightClick();
        void onDownClick();
        void onLeftClick();
    }
}
