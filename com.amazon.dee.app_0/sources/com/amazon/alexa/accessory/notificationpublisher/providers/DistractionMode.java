package com.amazon.alexa.accessory.notificationpublisher.providers;

import com.amazon.alexa.accessory.notificationpublisher.utils.Log;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.BitSet;
/* loaded from: classes.dex */
public class DistractionMode {
    private static final int LOW_DISTRACTION_BIT = 0;
    public static final int LOW_DISTRACTION_MODE = 2;
    public static final int NORMAL_DISTRACTION_MODE = 1;
    private static final int NO_DISTRACTION_BIT = 1;
    public static final int NO_DISTRACTION_MODE = 4;
    private static final int SILENT_DISTRACTION_BIT = 2;
    public static final int SILENT_DISTRACTION_MODE = 3;
    private static final String TAG = "DistractionMode";
    private final BitSet currentDistractionMode = new BitSet(3);

    public DistractionMode(boolean z, boolean z2) {
        setLowDistractionMode(z);
        setSilentDistractionMode(z2);
    }

    public int getCurrentDistractionMode() {
        if (this.currentDistractionMode.get(1)) {
            Log.i(TAG, "getCurrentDistractionMode - No distraction mode");
            return 4;
        } else if (this.currentDistractionMode.get(2)) {
            Log.i(TAG, "getCurrentDistractionMode - Silent distraction mode");
            return 3;
        } else if (!this.currentDistractionMode.get(0)) {
            return 1;
        } else {
            Log.i(TAG, "getCurrentDistractionMode - Low distraction mode");
            return 2;
        }
    }

    public void setLowDistractionMode(boolean z) {
        String str = TAG;
        Log.i(str, "setLowDistractionMode - enabled = " + z);
        this.currentDistractionMode.set(0, z);
        String str2 = TAG;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("setLowDistractionMode - Low distraction bit = ");
        outline107.append(this.currentDistractionMode.get(0));
        Log.d(str2, outline107.toString());
    }

    public void setNoDistractionMode(boolean z) {
        String str = TAG;
        Log.i(str, "setNoDistractionMode - enabled = " + z);
        this.currentDistractionMode.set(1, z);
        String str2 = TAG;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("setNoDistractionMode - No distraction bit = ");
        outline107.append(this.currentDistractionMode.get(1));
        Log.d(str2, outline107.toString());
    }

    public void setSilentDistractionMode(boolean z) {
        String str = TAG;
        Log.i(str, "setSilentDistractionMode - enabled = " + z);
        this.currentDistractionMode.set(2, z);
        String str2 = TAG;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("setSilentDistractionMode - Silent distraction bit = ");
        outline107.append(this.currentDistractionMode.get(2));
        Log.d(str2, outline107.toString());
    }
}
