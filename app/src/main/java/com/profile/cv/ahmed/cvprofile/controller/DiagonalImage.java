package com.profile.cv.ahmed.cvprofile.controller;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Xfermode;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * Created by ahmed on 1/4/2017.
 */
public class DiagonalImage extends ImageView {
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Xfermode pdMode = new PorterDuffXfermode(PorterDuff.Mode.CLEAR);
    private Path path = new Path();
    public DiagonalImage(Context context) {
        super(context);
        paint=new Paint();
        paint.setColor(Color.BLACK);
    }
    public DiagonalImage(Context context, AttributeSet attrs) {
        super(context, attrs);
     }

    public DiagonalImage(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
     }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Path p=new Path();
        //start from bottom left corner
        p.moveTo(360,canvas.getHeight());
        //move to the offset
        p.lineTo(canvas.getWidth(),200);
        //move to bottom right corner
        p.lineTo(canvas.getWidth(),canvas.getHeight());
        //return to bottom left corner
        p.lineTo(canvas.getHeight(),360);
        //clsoe the path
        p.close();
        canvas.drawPath(p,paint);
    }
}
