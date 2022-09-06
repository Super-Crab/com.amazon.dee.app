package com.amazon.alexa.device;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import com.amazon.comms.ringservice.webrtc.FrostVideoEffectController;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes.dex */
final class DefaultScreenInformation {
    private static final double MAX_PHONE_SIZE_IN_INCHES = 6.5d;
    private static final String TAG = "DefaultScreenInformation";
    private Context context;
    private Double deviceScreenSize;
    private Boolean phoneFormFactor;

    /* JADX INFO: Access modifiers changed from: package-private */
    public DefaultScreenInformation(Context context) {
        this.context = context;
    }

    private double calculateScreenSize() {
        DisplayMetrics displayMetrics;
        int i = getDisplayMetrics().densityDpi;
        double d = displayMetrics.widthPixels / i;
        double d2 = displayMetrics.heightPixels / i;
        if (Double.isNaN(d) || Double.isNaN(d2) || Double.isInfinite(d) || Double.isInfinite(d2)) {
            return FrostVideoEffectController.VIDEO_STRENGTH_CLEAR;
        }
        double sqrt = Math.sqrt(Math.pow(d2, 2.0d) + Math.pow(d, 2.0d));
        String str = "Screen size is: " + sqrt;
        return sqrt;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public DisplayMetrics getDisplayMetrics() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((WindowManager) this.context.getSystemService("window")).getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public double getScreenSize() {
        if (this.deviceScreenSize == null) {
            this.deviceScreenSize = Double.valueOf(calculateScreenSize());
        }
        return this.deviceScreenSize.doubleValue();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean isPhoneFormFactor() {
        if (this.phoneFormFactor == null) {
            this.phoneFormFactor = Boolean.valueOf(6.5d > getScreenSize());
        }
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("isPhoneFormFactor: ");
        outline107.append(this.phoneFormFactor);
        outline107.toString();
        return this.phoneFormFactor.booleanValue();
    }
}
