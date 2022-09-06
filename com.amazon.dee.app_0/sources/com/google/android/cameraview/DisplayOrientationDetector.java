package com.google.android.cameraview;

import android.content.Context;
import android.util.SparseIntArray;
import android.view.Display;
import android.view.OrientationEventListener;
/* loaded from: classes2.dex */
abstract class DisplayOrientationDetector {
    static final SparseIntArray DISPLAY_ORIENTATIONS = new SparseIntArray();
    Display mDisplay;
    private final OrientationEventListener mOrientationEventListener;
    private int mLastKnownDisplayOrientation = 0;
    private int mLastKnownDeviceOrientation = 0;

    static {
        DISPLAY_ORIENTATIONS.put(0, 0);
        DISPLAY_ORIENTATIONS.put(1, 90);
        DISPLAY_ORIENTATIONS.put(2, 180);
        DISPLAY_ORIENTATIONS.put(3, 270);
    }

    public DisplayOrientationDetector(Context context) {
        this.mOrientationEventListener = new OrientationEventListener(context) { // from class: com.google.android.cameraview.DisplayOrientationDetector.1
            private int mLastKnownRotation = -1;

            /* JADX WARN: Removed duplicated region for block: B:26:0x0037  */
            /* JADX WARN: Removed duplicated region for block: B:29:0x0049  */
            /* JADX WARN: Removed duplicated region for block: B:31:0x004e  */
            /* JADX WARN: Removed duplicated region for block: B:34:? A[RETURN, SYNTHETIC] */
            @Override // android.view.OrientationEventListener
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public void onOrientationChanged(int r5) {
                /*
                    r4 = this;
                    r0 = -1
                    if (r5 == r0) goto L59
                    com.google.android.cameraview.DisplayOrientationDetector r0 = com.google.android.cameraview.DisplayOrientationDetector.this
                    android.view.Display r0 = r0.mDisplay
                    if (r0 != 0) goto La
                    goto L59
                La:
                    r0 = 315(0x13b, float:4.41E-43)
                    r1 = 0
                    if (r5 > r0) goto L2d
                    r2 = 45
                    if (r5 >= r2) goto L14
                    goto L2d
                L14:
                    r3 = 135(0x87, float:1.89E-43)
                    if (r5 <= r2) goto L1d
                    if (r5 >= r3) goto L1d
                    r5 = 90
                    goto L2e
                L1d:
                    r2 = 225(0xe1, float:3.15E-43)
                    if (r5 <= r3) goto L26
                    if (r5 >= r2) goto L26
                    r5 = 180(0xb4, float:2.52E-43)
                    goto L2e
                L26:
                    if (r5 <= r2) goto L2d
                    if (r5 >= r0) goto L2d
                    r5 = 270(0x10e, float:3.78E-43)
                    goto L2e
                L2d:
                    r5 = r1
                L2e:
                    com.google.android.cameraview.DisplayOrientationDetector r0 = com.google.android.cameraview.DisplayOrientationDetector.this
                    int r0 = com.google.android.cameraview.DisplayOrientationDetector.access$000(r0)
                    r2 = 1
                    if (r0 == r5) goto L3d
                    com.google.android.cameraview.DisplayOrientationDetector r0 = com.google.android.cameraview.DisplayOrientationDetector.this
                    com.google.android.cameraview.DisplayOrientationDetector.access$002(r0, r5)
                    r1 = r2
                L3d:
                    com.google.android.cameraview.DisplayOrientationDetector r5 = com.google.android.cameraview.DisplayOrientationDetector.this
                    android.view.Display r5 = r5.mDisplay
                    int r5 = r5.getRotation()
                    int r0 = r4.mLastKnownRotation
                    if (r0 == r5) goto L4c
                    r4.mLastKnownRotation = r5
                    r1 = r2
                L4c:
                    if (r1 == 0) goto L59
                    com.google.android.cameraview.DisplayOrientationDetector r0 = com.google.android.cameraview.DisplayOrientationDetector.this
                    android.util.SparseIntArray r1 = com.google.android.cameraview.DisplayOrientationDetector.DISPLAY_ORIENTATIONS
                    int r5 = r1.get(r5)
                    r0.dispatchOnDisplayOrientationChanged(r5)
                L59:
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: com.google.android.cameraview.DisplayOrientationDetector.AnonymousClass1.onOrientationChanged(int):void");
            }
        };
    }

    public void disable() {
        this.mOrientationEventListener.disable();
        this.mDisplay = null;
    }

    void dispatchOnDisplayOrientationChanged(int i) {
        this.mLastKnownDisplayOrientation = i;
        onDisplayOrientationChanged(i, this.mLastKnownDeviceOrientation);
    }

    public void enable(Display display) {
        this.mDisplay = display;
        this.mOrientationEventListener.enable();
        dispatchOnDisplayOrientationChanged(DISPLAY_ORIENTATIONS.get(display.getRotation()));
    }

    public int getLastKnownDisplayOrientation() {
        return this.mLastKnownDisplayOrientation;
    }

    public abstract void onDisplayOrientationChanged(int i, int i2);
}
