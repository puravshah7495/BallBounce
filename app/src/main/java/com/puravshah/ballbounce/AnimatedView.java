package com.puravshah.ballbounce;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Handler;
import android.widget.ImageView;

/**
 * Created by Purav Shah on 7/17/2014.
 */


public class AnimatedView extends ImageView implements SensorEventListener{
    private Context mContext;
    int x = -1;
    int y = -1;
    private double xVelocity = 0;
    private double yVelocity = 0;
    private double xAcc, yAcc;
    private Handler h;
    private final int FRAME_RATE = 30;

    public AnimatedView(Context context)  {
        super(context);
        mContext = context;
        h = new Handler();
    }
    private Runnable r = new Runnable() {
        @Override
        public void run() {
            invalidate();
        }
    };

    protected void onDraw(Canvas c) {
        BitmapDrawable ball = (BitmapDrawable) mContext.getResources().getDrawable(R.drawable.magic_ball);
        long time = System.currentTimeMillis();

        if (x<0 && y <0) {
            x = this.getWidth()/2;
            y = this.getHeight()/2;
        } else {
            x += xVelocity;
            y += yVelocity;
            if ((x > this.getWidth() - ball.getBitmap().getWidth()) || (x < 0)) {
                xVelocity *= -1;
            }
            if ((y > this.getHeight() - ball.getBitmap().getHeight()) || (y < 0)) {
                yVelocity *= -1;
            }
        }
        c.drawBitmap(ball.getBitmap(),x,y,null);
        h.postDelayed(r,FRAME_RATE);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    public void addXVelocity(double x) {
        xVelocity += -x;
    }

    public void addYVelocty(double y) {
        yVelocity += y;
    }
}
