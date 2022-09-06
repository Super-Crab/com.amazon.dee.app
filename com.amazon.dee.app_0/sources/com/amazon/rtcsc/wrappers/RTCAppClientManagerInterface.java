package com.amazon.rtcsc.wrappers;
/* loaded from: classes13.dex */
public class RTCAppClientManagerInterface {
    protected transient boolean swigCMemOwn;
    private transient long swigCPtr;

    /* JADX INFO: Access modifiers changed from: protected */
    public RTCAppClientManagerInterface(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    protected static long getCPtr(RTCAppClientManagerInterface rTCAppClientManagerInterface) {
        if (rTCAppClientManagerInterface == null) {
            return 0L;
        }
        return rTCAppClientManagerInterface.swigCPtr;
    }

    public RTCSCErrorCode acceptSession(String str) {
        return RTCSCErrorCode.swigToEnum(RTCControllerAndroidJNI.RTCAppClientManagerInterface_acceptSession(this.swigCPtr, this, str));
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

    public RTCSCErrorCode disconnectSession(String str, RTCSCAppDisconnectCode rTCSCAppDisconnectCode) {
        return RTCSCErrorCode.swigToEnum(RTCControllerAndroidJNI.RTCAppClientManagerInterface_disconnectSession(this.swigCPtr, this, str, rTCSCAppDisconnectCode.swigValue()));
    }

    public RTCSCErrorCode registerRTCAppClientListener(RTCAppInfo rTCAppInfo, RTCAppClientListenerInterface rTCAppClientListenerInterface) {
        return RTCSCErrorCode.swigToEnum(RTCControllerAndroidJNI.RTCAppClientManagerInterface_registerRTCAppClientListener(this.swigCPtr, this, RTCAppInfo.getCPtr(rTCAppInfo), rTCAppInfo, RTCAppClientListenerInterface.getCPtr(rTCAppClientListenerInterface), rTCAppClientListenerInterface));
    }

    public RTCSCErrorCode setLocalAudioState(String str, boolean z) {
        return RTCSCErrorCode.swigToEnum(RTCControllerAndroidJNI.RTCAppClientManagerInterface_setLocalAudioState(this.swigCPtr, this, str, z));
    }

    public RTCSCErrorCode setLocalVideoState(String str, boolean z) {
        return RTCSCErrorCode.swigToEnum(RTCControllerAndroidJNI.RTCAppClientManagerInterface_setLocalVideoState(this.swigCPtr, this, str, z));
    }

    public RTCSCErrorCode setRemoteAudioState(String str, boolean z) {
        return RTCSCErrorCode.swigToEnum(RTCControllerAndroidJNI.RTCAppClientManagerInterface_setRemoteAudioState(this.swigCPtr, this, str, z));
    }

    public RTCSCErrorCode setVideoEffect(String str, RTCVideoEffect rTCVideoEffect, int i) {
        return RTCSCErrorCode.swigToEnum(RTCControllerAndroidJNI.RTCAppClientManagerInterface_setVideoEffect(this.swigCPtr, this, str, rTCVideoEffect.swigValue(), i));
    }

    public RTCSCErrorCode signalReadyForSession(String str) {
        return RTCSCErrorCode.swigToEnum(RTCControllerAndroidJNI.RTCAppClientManagerInterface_signalReadyForSession(this.swigCPtr, this, str));
    }

    public RTCSCErrorCode switchCamera(String str, String str2) {
        return RTCSCErrorCode.swigToEnum(RTCControllerAndroidJNI.RTCAppClientManagerInterface_switchCamera(this.swigCPtr, this, str, str2));
    }

    public RTCSCErrorCode unregisterRTCAppClientListener(RTCAppInfo rTCAppInfo) {
        return RTCSCErrorCode.swigToEnum(RTCControllerAndroidJNI.RTCAppClientManagerInterface_unregisterRTCAppClientListener(this.swigCPtr, this, RTCAppInfo.getCPtr(rTCAppInfo), rTCAppInfo));
    }
}
