package com.facebook.drawee.backends.pipeline.info;

import com.amazon.alexa.redesign.view.viewtypes.AlarmsTimersView;
/* loaded from: classes2.dex */
public class ImagePerfUtils {
    private ImagePerfUtils() {
    }

    public static String toString(int imageLoadStatus) {
        return imageLoadStatus != 0 ? imageLoadStatus != 1 ? imageLoadStatus != 2 ? imageLoadStatus != 3 ? imageLoadStatus != 4 ? imageLoadStatus != 5 ? "unknown" : "error" : AlarmsTimersView.STATE_CANCELED : "success" : "intermediate_available" : "origin_available" : "requested";
    }
}
