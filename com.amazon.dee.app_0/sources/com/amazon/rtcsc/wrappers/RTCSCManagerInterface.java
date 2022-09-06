package com.amazon.rtcsc.wrappers;
/* loaded from: classes13.dex */
public class RTCSCManagerInterface {
    protected transient boolean swigCMemOwn;
    private transient long swigCPtr;

    protected RTCSCManagerInterface(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    public static RTCSCManagerInterface createAndInitInstance(String str) {
        long RTCSCManagerInterface_createAndInitInstance = RTCControllerAndroidJNI.RTCSCManagerInterface_createAndInitInstance(str);
        if (RTCSCManagerInterface_createAndInitInstance == 0) {
            return null;
        }
        return new RTCSCManagerInterface(RTCSCManagerInterface_createAndInitInstance, false);
    }

    protected static long getCPtr(RTCSCManagerInterface rTCSCManagerInterface) {
        if (rTCSCManagerInterface == null) {
            return 0L;
        }
        return rTCSCManagerInterface.swigCPtr;
    }

    public static RTCSCManagerInterface getInstance() {
        long RTCSCManagerInterface_getInstance = RTCControllerAndroidJNI.RTCSCManagerInterface_getInstance();
        if (RTCSCManagerInterface_getInstance == 0) {
            return null;
        }
        return new RTCSCManagerInterface(RTCSCManagerInterface_getInstance, false);
    }

    public static void releaseInstance() {
        RTCControllerAndroidJNI.RTCSCManagerInterface_releaseInstance();
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

    public RTCAppClientManagerInterface getRTCAppClientManager() {
        long RTCSCManagerInterface_getRTCAppClientManager = RTCControllerAndroidJNI.RTCSCManagerInterface_getRTCAppClientManager(this.swigCPtr, this);
        if (RTCSCManagerInterface_getRTCAppClientManager == 0) {
            return null;
        }
        return new RTCAppClientManagerInterface(RTCSCManagerInterface_getRTCAppClientManager, false);
    }

    public RTCClientInterface getRTCClient() {
        long RTCSCManagerInterface_getRTCClient = RTCControllerAndroidJNI.RTCSCManagerInterface_getRTCClient(this.swigCPtr, this);
        if (RTCSCManagerInterface_getRTCClient == 0) {
            return null;
        }
        return new RTCClientInterface(RTCSCManagerInterface_getRTCClient, false);
    }

    public RTCSCErrorCode registerRTCCustomMetricsPublisherAdapter(RTCAppInfo rTCAppInfo, RTCCustomMetricsPublisherAdapterInterface rTCCustomMetricsPublisherAdapterInterface) {
        return RTCSCErrorCode.swigToEnum(RTCControllerAndroidJNI.RTCSCManagerInterface_registerRTCCustomMetricsPublisherAdapter(this.swigCPtr, this, RTCAppInfo.getCPtr(rTCAppInfo), rTCAppInfo, RTCCustomMetricsPublisherAdapterInterface.getCPtr(rTCCustomMetricsPublisherAdapterInterface), rTCCustomMetricsPublisherAdapterInterface));
    }

    public RTCSCErrorCode unregisterRTCCustomMetricsPublisherAdapter(RTCAppInfo rTCAppInfo) {
        return RTCSCErrorCode.swigToEnum(RTCControllerAndroidJNI.RTCSCManagerInterface_unregisterRTCCustomMetricsPublisherAdapter(this.swigCPtr, this, RTCAppInfo.getCPtr(rTCAppInfo), rTCAppInfo));
    }
}
