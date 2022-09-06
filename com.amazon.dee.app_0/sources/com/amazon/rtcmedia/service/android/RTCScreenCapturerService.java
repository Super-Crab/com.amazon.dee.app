package com.amazon.rtcmedia.service.android;

import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import com.amazon.rtcmedia.service.android.RTCScreenCapturerServiceInterface;
import com.amazon.rtcmedia.util.MediaUIBridge;
import com.amazon.rtcmedia.util.RtcscLogger;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes13.dex */
public class RTCScreenCapturerService implements RTCScreenCapturerServiceInterface {
    private static RtcscLogger mLog = RtcscLogger.getLogger(RTCScreenCapturerService.class);
    private final Handler mainHandler = new Handler(Looper.getMainLooper());
    private RTCScreenCapturerServiceInterface.RTCScreenCapturerServiceListener screenCapturerServiceListener;

    private boolean isValidSessionId(String str) {
        if (str == null || str.isEmpty()) {
            RtcscLogger rtcscLogger = mLog;
            rtcscLogger.e("sessionId is null or empty " + str);
            return false;
        }
        return true;
    }

    @Override // com.amazon.rtcmedia.service.android.RTCScreenCapturerServiceInterface
    public void putScreenCapturerData(String str, Intent intent) {
        GeneratedOutlineSupport1.outline160("putScreenCapturerData, sessionId = ", str, mLog);
        if (!isValidSessionId(str)) {
            return;
        }
        MediaUIBridge.getInstance().putScreenCapturerData(str, intent);
        this.mainHandler.post(new Runnable() { // from class: com.amazon.rtcmedia.service.android.RTCScreenCapturerService.1
            @Override // java.lang.Runnable
            public void run() {
                RTCScreenCapturerService.this.screenCapturerServiceListener.onPutScreenCapturerData();
            }
        });
    }

    @Override // com.amazon.rtcmedia.service.android.RTCScreenCapturerServiceInterface
    public void registerScreenCapturerListener(String str, RTCScreenCapturerServiceInterface.RTCScreenCapturerServiceListener rTCScreenCapturerServiceListener) {
        GeneratedOutlineSupport1.outline160("registerScreenCapturerListener, sessionId = ", str, mLog);
        if (!isValidSessionId(str)) {
            return;
        }
        this.screenCapturerServiceListener = rTCScreenCapturerServiceListener;
    }

    @Override // com.amazon.rtcmedia.service.android.RTCScreenCapturerServiceInterface
    public void removeScreenCapturerData(String str) {
        GeneratedOutlineSupport1.outline160("removeScreenCapturerData, sessionId = ", str, mLog);
        if (!isValidSessionId(str)) {
            return;
        }
        MediaUIBridge.getInstance().removeScreenCapturerData(str);
    }

    @Override // com.amazon.rtcmedia.service.android.RTCScreenCapturerServiceInterface
    public void setScreenCapturerDimensions(String str, int i, int i2) {
        GeneratedOutlineSupport1.outline160("setScreenCapturerDimensions, sessionId = ", str, mLog);
        if (!isValidSessionId(str)) {
            return;
        }
        MediaUIBridge.getInstance().setScreenCapturerDimensions(str, i, i2);
    }
}
