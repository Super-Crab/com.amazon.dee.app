package com.amazon.rtcsc.service.surfaceview;

import android.view.Surface;
import androidx.core.util.Pair;
import com.amazon.rtcmedia.service.android.RTCSurfaceRendererService;
import com.amazon.rtcmedia.service.android.RTCSurfaceRendererServiceInterface;
import com.amazon.rtcmedia.util.ViewDirection;
import com.amazon.rtcsc.interfaces.IRtcscViewRendererListener;
import com.amazon.rtcsc.interfaces.RtcscScalingType;
import com.amazon.rtcsc.interfaces.RtcscViewDirection;
import com.amazon.rtcsc.utils.RtcscLogger;
import java.util.HashMap;
/* loaded from: classes13.dex */
public class RtcscSurfaceViewWrapper {
    private RtcscLogger mLog = RtcscLogger.getLogger(RtcscSurfaceViewWrapper.class);
    private HashMap<Pair<String, String>, RTCSurfaceRendererServiceInterface> mRendererMap = new HashMap<>();

    public void clearImage(String str, String str2) {
        RTCSurfaceRendererServiceInterface rTCSurfaceRendererServiceInterface = this.mRendererMap.get(new Pair(str, str2));
        if (rTCSurfaceRendererServiceInterface != null) {
            rTCSurfaceRendererServiceInterface.clearImage();
        } else {
            this.mLog.w("sessionId was not found. Ignoring the clearImage() request.");
        }
    }

    public void init(String str, String str2, IRtcscViewRendererListener iRtcscViewRendererListener, RtcscViewDirection rtcscViewDirection, String str3) {
        RtcscViewRendererListenerWrapper rtcscViewRendererListenerWrapper = new RtcscViewRendererListenerWrapper(iRtcscViewRendererListener);
        RTCSurfaceRendererService rTCSurfaceRendererService = new RTCSurfaceRendererService();
        this.mRendererMap.put(new Pair<>(str, str2), rTCSurfaceRendererService);
        rTCSurfaceRendererService.init(rtcscViewRendererListenerWrapper, ViewDirection.valueOf(rtcscViewDirection.toString()), str, str3);
    }

    public void onLayout(String str, String str2, boolean z, int i, int i2, int i3, int i4) {
        RTCSurfaceRendererServiceInterface rTCSurfaceRendererServiceInterface = this.mRendererMap.get(new Pair(str, str2));
        if (rTCSurfaceRendererServiceInterface != null) {
            rTCSurfaceRendererServiceInterface.onLayout(z, i, i2, i3, i4);
        } else {
            this.mLog.w("sessionId was not found. Ignoring the onLayout() request.");
        }
    }

    public void onMeasure(String str, String str2, int i, int i2) {
        RTCSurfaceRendererServiceInterface rTCSurfaceRendererServiceInterface = this.mRendererMap.get(new Pair(str, str2));
        if (rTCSurfaceRendererServiceInterface != null) {
            rTCSurfaceRendererServiceInterface.onMeasure(i, i2);
        } else {
            this.mLog.w("sessionId was not found. Ignoring the onMeasure() request.");
        }
    }

    public void release(String str, String str2) {
        RTCSurfaceRendererServiceInterface remove = this.mRendererMap.remove(new Pair(str, str2));
        if (remove != null) {
            remove.release();
        } else {
            this.mLog.w("sessionId was not found. Ignoring the release() request.");
        }
    }

    public void setEnableHardwareScaler(String str, String str2, boolean z) {
        RTCSurfaceRendererServiceInterface rTCSurfaceRendererServiceInterface = this.mRendererMap.get(new Pair(str, str2));
        if (rTCSurfaceRendererServiceInterface != null) {
            rTCSurfaceRendererServiceInterface.setEnableHardwareScaler(Boolean.valueOf(z));
        } else {
            this.mLog.w("sessionId was not found. Ignoring the setEnableHardwareScaler() request.");
        }
    }

    public void setMirror(String str, String str2, boolean z) {
        RTCSurfaceRendererServiceInterface rTCSurfaceRendererServiceInterface = this.mRendererMap.get(new Pair(str, str2));
        if (rTCSurfaceRendererServiceInterface != null) {
            rTCSurfaceRendererServiceInterface.setMirror(z);
        } else {
            this.mLog.w("sessionId was not found. Ignoring the setMirror() request.");
        }
    }

    public void setScalingType(String str, String str2, RtcscScalingType rtcscScalingType) {
        RTCSurfaceRendererServiceInterface rTCSurfaceRendererServiceInterface = this.mRendererMap.get(new Pair(str, str2));
        if (rTCSurfaceRendererServiceInterface != null) {
            rTCSurfaceRendererServiceInterface.setScalingType(RTCSurfaceRendererServiceInterface.ScalingType.valueOf(rtcscScalingType.toString()));
        } else {
            this.mLog.w("sessionId was not found. Ignoring the setScalingType() request.");
        }
    }

    public void surfaceChanged(String str, String str2, Surface surface, int i, int i2, int i3) {
        RTCSurfaceRendererServiceInterface rTCSurfaceRendererServiceInterface = this.mRendererMap.get(new Pair(str, str2));
        if (rTCSurfaceRendererServiceInterface != null) {
            rTCSurfaceRendererServiceInterface.surfaceChanged(surface, i, i2, i3);
        } else {
            this.mLog.w("sessionId was not found. Ignoring the surfaceChanged() request.");
        }
    }

    public void surfaceCreated(String str, String str2, Surface surface) {
        RTCSurfaceRendererServiceInterface rTCSurfaceRendererServiceInterface = this.mRendererMap.get(new Pair(str, str2));
        if (rTCSurfaceRendererServiceInterface != null) {
            rTCSurfaceRendererServiceInterface.surfaceCreated(surface);
        } else {
            this.mLog.w("sessionId was not found. Ignoring the surfaceCreated() request.");
        }
    }

    public void surfaceDestroyed(String str, String str2, Surface surface) {
        RTCSurfaceRendererServiceInterface rTCSurfaceRendererServiceInterface = this.mRendererMap.get(new Pair(str, str2));
        if (rTCSurfaceRendererServiceInterface != null) {
            rTCSurfaceRendererServiceInterface.surfaceDestroyed(surface);
        } else {
            this.mLog.w("sessionId was not found. Ignoring the surfaceDestroyed() request.");
        }
    }
}
