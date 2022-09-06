package com.amazon.rtcmedia.service.android;

import android.content.Intent;
/* loaded from: classes13.dex */
public interface RTCScreenCapturerServiceInterface {

    /* loaded from: classes13.dex */
    public interface RTCScreenCapturerServiceListener {
        void onPutScreenCapturerData();
    }

    void putScreenCapturerData(String str, Intent intent);

    void registerScreenCapturerListener(String str, RTCScreenCapturerServiceListener rTCScreenCapturerServiceListener);

    void removeScreenCapturerData(String str);

    void setScreenCapturerDimensions(String str, int i, int i2);
}
