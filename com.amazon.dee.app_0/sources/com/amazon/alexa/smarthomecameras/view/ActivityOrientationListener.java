package com.amazon.alexa.smarthomecameras.view;

import android.app.Activity;
import android.util.Log;
import android.view.OrientationEventListener;
import androidx.annotation.VisibleForTesting;
import com.google.common.base.Preconditions;
/* loaded from: classes10.dex */
public class ActivityOrientationListener extends OrientationEventListener {
    private static final String TAG = ActivityOrientationListener.class.getSimpleName();
    private final Activity activity;
    private boolean didRotateToLandscape;
    @VisibleForTesting
    int downPortrait;
    @VisibleForTesting
    int epsilon;
    @VisibleForTesting
    int leftLandscape;
    @VisibleForTesting
    int rightLandscape;
    @VisibleForTesting
    int upPortrait;

    public ActivityOrientationListener(Activity activity) {
        super(activity, 3);
        this.epsilon = 10;
        this.leftLandscape = 90;
        this.rightLandscape = 270;
        this.upPortrait = 0;
        this.downPortrait = 180;
        Preconditions.checkNotNull(activity, "Activity cannot be null");
        this.activity = activity;
    }

    private boolean epsilonCheck(int i, int i2, int i3) {
        return i > i2 - i3 && i < i2 + i3;
    }

    public void initialize() {
        if (canDetectOrientation()) {
            enable();
        } else {
            Log.w(TAG, "Cannot detect orientation");
        }
    }

    @Override // android.view.OrientationEventListener
    public void onOrientationChanged(int i) {
        if ((epsilonCheck(i, this.upPortrait, this.epsilon) || epsilonCheck(i, this.downPortrait, this.epsilon)) && this.didRotateToLandscape) {
            Log.i(TAG, "Changing to portrait orientation");
            this.activity.setRequestedOrientation(4);
        } else if (!epsilonCheck(i, this.leftLandscape, this.epsilon) && !epsilonCheck(i, this.rightLandscape, this.epsilon)) {
        } else {
            Log.i(TAG, "Changing to landscape orientation");
            this.didRotateToLandscape = true;
        }
    }
}
