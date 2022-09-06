package com.amazon.alexa.drive.entertainment.view;

import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
/* loaded from: classes7.dex */
public class NowPlayingScreenConstraintLayoutTouchListener implements View.OnTouchListener {
    private static final String TAG = NowPlayingScreenConstraintLayoutTouchListener.class.getSimpleName();
    private GestureDetector mGestureDetector;
    private NowPlayingScreenViewController mNowPlayingScreenViewController;

    /* loaded from: classes7.dex */
    private final class GestureListener extends GestureDetector.SimpleOnGestureListener {
        private static final int SWIPE_DISTANCE_THRESHOLD = 100;
        private static final int SWIPE_VELOCITY_THRESHOLD = 100;

        private GestureListener() {
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
        public boolean onDown(MotionEvent motionEvent) {
            return true;
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
        public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
            float y = motionEvent2.getY() - motionEvent.getY();
            if (Math.abs(y) <= 100.0f || Math.abs(f2) <= 100.0f) {
                return false;
            }
            if (y > 0.0f) {
                Log.i(NowPlayingScreenConstraintLayoutTouchListener.TAG, "onTouch motionEvent swipe down");
                if (NowPlayingScreenConstraintLayoutTouchListener.this.mNowPlayingScreenViewController == null) {
                    return true;
                }
                NowPlayingScreenConstraintLayoutTouchListener.this.mNowPlayingScreenViewController.navigateToPreviousScreen();
                return true;
            }
            Log.i(NowPlayingScreenConstraintLayoutTouchListener.TAG, "onTouch motionEvent swipe up");
            return true;
        }
    }

    public NowPlayingScreenConstraintLayoutTouchListener(NowPlayingScreenViewController nowPlayingScreenViewController) {
        this.mNowPlayingScreenViewController = nowPlayingScreenViewController;
        this.mGestureDetector = new GestureDetector(nowPlayingScreenViewController.getContext(), new GestureListener());
    }

    @Override // android.view.View.OnTouchListener
    public boolean onTouch(View view, MotionEvent motionEvent) {
        return this.mGestureDetector.onTouchEvent(motionEvent);
    }
}
