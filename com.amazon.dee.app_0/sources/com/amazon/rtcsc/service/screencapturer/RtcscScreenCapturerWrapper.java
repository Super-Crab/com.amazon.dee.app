package com.amazon.rtcsc.service.screencapturer;

import android.content.Intent;
import com.amazon.rtcmedia.service.android.RTCScreenCapturerService;
import com.amazon.rtcmedia.service.android.RTCScreenCapturerServiceInterface;
import com.amazon.rtcsc.interfaces.IRtcscScreenCapturerListener;
import com.amazon.rtcsc.utils.RtcscLogger;
import java.util.HashMap;
import java.util.Locale;
/* loaded from: classes13.dex */
public class RtcscScreenCapturerWrapper {
    private RtcscLogger mLog = RtcscLogger.getLogger(RtcscScreenCapturerWrapper.class);
    private HashMap<String, RTCScreenCapturerServiceInterface> mScreenCapturerServiceMap = new HashMap<>();

    public void putScreenCapturerData(String str, Intent intent) {
        this.mLog.i(String.format(Locale.US, "putScreenCapturerData called for SessionId: %s", str));
        RTCScreenCapturerServiceInterface rTCScreenCapturerServiceInterface = this.mScreenCapturerServiceMap.get(str);
        if (rTCScreenCapturerServiceInterface != null) {
            rTCScreenCapturerServiceInterface.putScreenCapturerData(str, intent);
        } else {
            this.mLog.w(String.format(Locale.US, "A valid RTCScreenCapturerService was not found for sessionId: %s. Ignoring the putScreenCapturerData() request.", str));
        }
    }

    public void registerScreenCapturerListener(String str, IRtcscScreenCapturerListener iRtcscScreenCapturerListener) {
        this.mLog.i(String.format(Locale.US, "registerScreenCapturerListener called for SessionId: %s", str));
        RtcscScreenCapturerListenerWrapper rtcscScreenCapturerListenerWrapper = new RtcscScreenCapturerListenerWrapper(iRtcscScreenCapturerListener);
        RTCScreenCapturerService rTCScreenCapturerService = new RTCScreenCapturerService();
        rTCScreenCapturerService.registerScreenCapturerListener(str, rtcscScreenCapturerListenerWrapper);
        this.mScreenCapturerServiceMap.put(str, rTCScreenCapturerService);
    }

    public void setScreenCapturerDimensions(String str, int i, int i2) {
        this.mLog.i(String.format(Locale.US, "setScreenCapturerDimensions called for SessionId: %s with width: %d, height: %d", str, Integer.valueOf(i), Integer.valueOf(i2)));
        RTCScreenCapturerServiceInterface rTCScreenCapturerServiceInterface = this.mScreenCapturerServiceMap.get(str);
        if (rTCScreenCapturerServiceInterface != null) {
            rTCScreenCapturerServiceInterface.setScreenCapturerDimensions(str, i, i2);
        } else {
            this.mLog.w(String.format(Locale.US, "A valid RTCScreenCapturerService was not found for sessionId: %s. Ignoring the setScreenCapturerDimensions() request.", str));
        }
    }

    public void unregisterScreenCapturerListener(String str) {
        this.mLog.i(String.format(Locale.US, "unregisterScreenCapturerListener called for SessionId: %s", str));
        RTCScreenCapturerServiceInterface rTCScreenCapturerServiceInterface = this.mScreenCapturerServiceMap.get(str);
        if (rTCScreenCapturerServiceInterface != null) {
            this.mScreenCapturerServiceMap.remove(str);
            rTCScreenCapturerServiceInterface.removeScreenCapturerData(str);
            return;
        }
        this.mLog.w(String.format(Locale.US, "A valid RTCScreenCapturerService was not found for sessionId: %s. Ignoring the unregisterScreenCapturerListener() request.", str));
    }
}
