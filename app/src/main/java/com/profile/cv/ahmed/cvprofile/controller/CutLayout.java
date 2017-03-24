package com.profile.cv.ahmed.cvprofile.controller;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Xfermode;
import android.os.Build;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.RelativeLayout;

/**
 * Created by ahmed on 1/4/2017.
 */
public class CutLayout extends RelativeLayout {
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Xfermode pdMode = new PorterDuffXfermode(PorterDuff.Mode.CLEAR);
    private Path path = new Path();

    public CutLayout(Context context) {
        super(context);
    }

    public CutLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CutLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public CutLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        int saveCount = canvas.saveLayer(0, 0, getWidth(), getHeight(), null, Canvas.ALL_SAVE_FLAG);
        super.dispatchDraw(canvas);
        paint.setXfermode(pdMode);
        path.reset();
//        path.moveTo(0, getHeight() - TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 50, getResources().getDisplayMetrics()));
        path.moveTo(0, 0);
        path.lineTo(getWidth(), getHeight());
        path.lineTo(getWidth(), getHeight() );

//  change value (50) to change the the slope.50 means 50dp.
//        path.lineTo(getWidth()/2, getHeight());


//        path.moveTo(0,0);                               //  (0,0)
//        path.lineTo(getPx(50),0);                       //  (50,0)
//        path.lineTo(0,getPx(50));                       //  (0,50)
//
//        path.lineTo(0,getHeight()-getPx(50));           //  (0,H-50)
//        path.lineTo(getPx(50),getHeight());             //  (50,H)
//        path.lineTo(0,getHeight());                     //  (0,H)
//
//        path.lineTo(0,0);                               //  (0,0)
//        path.lineTo(getWidth()-getPx(50),0);            //  (W-50,0)
//        path.lineTo(getWidth(),getPx(50));              //  (W,50)
//        path.lineTo(getWidth(),0);                      //  (W,0)
//
//        path.lineTo(getWidth(),getHeight()-getPx(50));  //  (W,H-50)
//        path.lineTo(getWidth()-getPx(50),getHeight());  //  (W-50,H)
//        path.lineTo(getWidth(),getHeight());            //  (W,H)
//        path.lineTo(getWidth(),0);                      //  (W,0)


        path.close();
        canvas.drawPath(path, paint);
        canvas.restoreToCount(saveCount);
        paint.setXfermode(null);
    }

    private float getPx(int i) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, i, getResources().getDisplayMetrics());
    }

}

