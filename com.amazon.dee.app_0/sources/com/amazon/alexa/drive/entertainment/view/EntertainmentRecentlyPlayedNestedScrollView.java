package com.amazon.alexa.drive.entertainment.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.widget.NestedScrollView;
import com.amazon.alexa.drive.userstudy.DriverDistractionLog;
import com.amazon.alexa.drive.userstudy.LogConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes7.dex */
public class EntertainmentRecentlyPlayedNestedScrollView extends NestedScrollView {
    private static final String TAG = EntertainmentRecentlyPlayedNestedScrollView.class.getSimpleName();
    private GestureDetector mGestureDetector;

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
                return true;
            }
            if (y > 0.0f) {
                Log.i(EntertainmentRecentlyPlayedNestedScrollView.TAG, "onTouch motionEvent swipe down");
                DriverDistractionLog.logTouch(EntertainmentRecentlyPlayedNestedScrollView.TAG, LogConstants.ACTION_SWIPE_DOWN);
                return true;
            }
            Log.i(EntertainmentRecentlyPlayedNestedScrollView.TAG, "onTouch motionEvent swipe up");
            DriverDistractionLog.logTouch(EntertainmentRecentlyPlayedNestedScrollView.TAG, LogConstants.ACTION_SWIPE_UP);
            return true;
        }
    }

    public EntertainmentRecentlyPlayedNestedScrollView(@NonNull Context context) {
        super(context);
        this.mGestureDetector = new GestureDetector(context, new GestureListener());
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        boolean dispatchTouchEvent = super.dispatchTouchEvent(motionEvent) | this.mGestureDetector.onTouchEvent(motionEvent);
        GeneratedOutlineSupport1.outline172("dispatchTouchEvent ", dispatchTouchEvent);
        return dispatchTouchEvent;
    }

    public EntertainmentRecentlyPlayedNestedScrollView(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mGestureDetector = new GestureDetector(context, new GestureListener());
    }

    public EntertainmentRecentlyPlayedNestedScrollView(@NonNull Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mGestureDetector = new GestureDetector(context, new GestureListener());
    }
}
