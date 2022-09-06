package org.webrtc;

import android.graphics.RectF;
import android.util.Log;
import java.util.Locale;
/* loaded from: classes5.dex */
public class FollowMeUtils {
    private static final String TAG = "FollowMeUtils";
    static final float maxDelta = 1.0f;
    static final float panInterval = 20.0f;
    static final float zoomInterval = 20.0f;

    public static void updateCurrentROI(RectF rectF, RectF rectF2, int i, int i2) {
        if (i > 0 && i2 > 0) {
            float f = rectF.left;
            if (f >= 0.0f) {
                float f2 = rectF.top;
                if (f2 >= 0.0f) {
                    float f3 = rectF.right;
                    if (f3 > f) {
                        float f4 = rectF.bottom;
                        if (f4 > f2) {
                            float f5 = (f + f3) / 2.0f;
                            float f6 = (f2 + f4) / 2.0f;
                            float f7 = f3 - f;
                            float f8 = rectF2.left;
                            float f9 = rectF2.right;
                            float f10 = ((f8 + f9) / 2.0f) - f5;
                            float f11 = ((rectF2.top + rectF2.bottom) / 2.0f) - f6;
                            float f12 = (f9 - f8) - f7;
                            float abs = Math.abs(f10);
                            float abs2 = Math.abs(f11);
                            float abs3 = Math.abs(f12);
                            float f13 = i;
                            float f14 = i2;
                            float f15 = f13 / f14;
                            if (abs < 1.0f && abs2 < 1.0f && abs3 < 1.0f) {
                                return;
                            }
                            float f16 = (f12 / 20.0f) + f7;
                            float f17 = (f10 / 20.0f) + f5;
                            float f18 = (f11 / 20.0f) + f6;
                            float f19 = f16 / 2.0f;
                            float f20 = f17 - f19;
                            if (f20 < 0.0f) {
                                rectF.left = 0.0f;
                                rectF.right = f16;
                            } else {
                                float f21 = f17 + f19;
                                if (f21 > f13) {
                                    rectF.left = f13 - f16;
                                    rectF.right = f13;
                                } else {
                                    rectF.left = Math.max(f20, 0.0f);
                                    rectF.right = Math.min(f21, f13);
                                }
                            }
                            float f22 = (rectF.right - rectF.left) / f15;
                            float f23 = f22 / 2.0f;
                            float f24 = f18 - f23;
                            if (f24 < 0.0f) {
                                rectF.top = 0.0f;
                                rectF.bottom = f22;
                                return;
                            }
                            float f25 = f18 + f23;
                            if (f25 > f14) {
                                rectF.top = f14 - f22;
                                rectF.bottom = f14;
                                return;
                            }
                            rectF.top = Math.max(f24, 0.0f);
                            rectF.bottom = Math.min(f25, f14);
                            return;
                        }
                    }
                }
            }
            Log.e(TAG, String.format(Locale.US, "Failed to update the current ROI, due to currentROI.left: %.2f or currentROI.top: %.2f or currentROI.right: %.2f or currentROI.bottom: %.2f.", Float.valueOf(rectF.left), Float.valueOf(rectF.top), Float.valueOf(rectF.right), Float.valueOf(rectF.bottom)));
            return;
        }
        Log.e(TAG, String.format(Locale.US, "Failed to update the current ROI, due to captureWidth: %d or captureHeight: %d", Integer.valueOf(i), Integer.valueOf(i2)));
    }
}
