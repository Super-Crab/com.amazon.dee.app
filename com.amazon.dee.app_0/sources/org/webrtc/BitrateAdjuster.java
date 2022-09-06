package org.webrtc;
/* loaded from: classes5.dex */
interface BitrateAdjuster {
    int getAdjustedBitrateBps();

    int getAdjustedFramerate();

    void reportEncodedFrame(int i);

    void setTargets(int i, int i2);
}
