package com.gli.navigationcontroller;

import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.KeyEvent;
import android.view.MotionEvent;

public class MainActivity extends AppCompatActivity {
private static final String TAG = MainActivity.class.getSimpleName();
    private GestureDetectorCompat mDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate: ");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mDetector = new GestureDetectorCompat(this, new MyGestureListener());
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.d(TAG, "onTouchEvent: ");
        this.mDetector.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        Log.d(TAG, "onKeyUp: keycode="+keyCode+" event="+event);
        return super.onKeyUp(keyCode, event);
    }

    class MyGestureListener extends GestureDetector.SimpleOnGestureListener {

        private static final String TAG = "MyGestureListener";
        float x1, x2, y1, y2, dx, dy;
        String direction;

        @Override
        public boolean onDown(MotionEvent e) {
            //Log.d(TAG, "onDown: e"+e.toString());
            x1 = e.getX();
            y1 = e.getY();
            return true;
        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            //Log.d(TAG, "onFling: e"+e1.toString()+" e2"+e2.toString()+"velocity="+velocityX+" "+velocityY);
            x2 = e2.getX();
            y2 = e2.getY();
            dx = x2-x1;
            dy = y2-y1;

            // Use dx and dy to determine the direction
            if(Math.abs(dx) > Math.abs(dy)) {
                if(dx>0)
                    direction = "right";
                else
                    direction = "left";
            } else {
                if(dy>0)
                    direction = "down";
                else
                    direction = "up";
            }
            Log.d(TAG, "onFling: Direction "+direction);
            return true;
        }

        @Override
        public boolean onDoubleTap(MotionEvent e) {
            Log.d(TAG, "onDoubleTap: ");
            return super.onDoubleTap(e);
        }

        @Override
        public boolean onSingleTapConfirmed(MotionEvent e) {
            Log.d(TAG, "onSingleTapConfirmed: ");
            return super.onSingleTapConfirmed(e);
        }
    }

}
