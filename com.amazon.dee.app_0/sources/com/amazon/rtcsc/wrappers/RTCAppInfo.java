package com.amazon.rtcsc.wrappers;
/* loaded from: classes13.dex */
public class RTCAppInfo {
    protected transient boolean swigCMemOwn;
    private transient long swigCPtr;

    protected RTCAppInfo(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static long getCPtr(RTCAppInfo rTCAppInfo) {
        if (rTCAppInfo == null) {
            return 0L;
        }
        return rTCAppInfo.swigCPtr;
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                RTCControllerAndroidJNI.delete_RTCAppInfo(this.swigCPtr);
            }
            this.swigCPtr = 0L;
        }
    }

    protected void finalize() {
        delete();
    }

    public String getAppIdentifier() {
        return RTCControllerAndroidJNI.RTCAppInfo_appIdentifier_get(this.swigCPtr, this);
    }

    public void setAppIdentifier(String str) {
        RTCControllerAndroidJNI.RTCAppInfo_appIdentifier_set(this.swigCPtr, this, str);
    }

    public RTCAppInfo() {
        this(RTCControllerAndroidJNI.new_RTCAppInfo(), true);
    }
}
