package com.amazon.rtcsc.wrappers;
/* loaded from: classes13.dex */
public class RTCClientInterface {
    protected transient boolean swigCMemOwn;
    private transient long swigCPtr;

    /* JADX INFO: Access modifiers changed from: protected */
    public RTCClientInterface(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    protected static long getCPtr(RTCClientInterface rTCClientInterface) {
        if (rTCClientInterface == null) {
            return 0L;
        }
        return rTCClientInterface.swigCPtr;
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (!this.swigCMemOwn) {
                this.swigCPtr = 0L;
            } else {
                this.swigCMemOwn = false;
                throw new UnsupportedOperationException("C++ destructor does not have public access");
            }
        }
    }

    public RTCSCErrorCode handleDirective(String str, String str2) {
        return RTCSCErrorCode.swigToEnum(RTCControllerAndroidJNI.RTCClientInterface_handleDirective(this.swigCPtr, this, str, str2));
    }

    public RTCSCErrorCode registerEventListener(RTCAppInfo rTCAppInfo, RTCEventListenerInterface rTCEventListenerInterface) {
        return RTCSCErrorCode.swigToEnum(RTCControllerAndroidJNI.RTCClientInterface_registerEventListener(this.swigCPtr, this, RTCAppInfo.getCPtr(rTCAppInfo), rTCAppInfo, RTCEventListenerInterface.getCPtr(rTCEventListenerInterface), rTCEventListenerInterface));
    }

    public RTCSCErrorCode unregisterEventListener(RTCAppInfo rTCAppInfo) {
        return RTCSCErrorCode.swigToEnum(RTCControllerAndroidJNI.RTCClientInterface_unregisterEventListener(this.swigCPtr, this, RTCAppInfo.getCPtr(rTCAppInfo), rTCAppInfo));
    }
}
