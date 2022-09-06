package com.amazon.rtcsc.wrappers;
/* loaded from: classes13.dex */
public class RTCCustomMetricsPublisherAdapterInterface {
    protected transient boolean swigCMemOwn;
    private transient long swigCPtr;

    protected RTCCustomMetricsPublisherAdapterInterface(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static long getCPtr(RTCCustomMetricsPublisherAdapterInterface rTCCustomMetricsPublisherAdapterInterface) {
        if (rTCCustomMetricsPublisherAdapterInterface == null) {
            return 0L;
        }
        return rTCCustomMetricsPublisherAdapterInterface.swigCPtr;
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

    public boolean recordMetric(RTCCustomMetricInterface rTCCustomMetricInterface) {
        return RTCControllerAndroidJNI.RTCCustomMetricsPublisherAdapterInterface_recordMetric(this.swigCPtr, this, RTCCustomMetricInterface.getCPtr(rTCCustomMetricInterface), rTCCustomMetricInterface);
    }

    protected void swigDirectorDisconnect() {
        this.swigCMemOwn = false;
        delete();
    }

    public void swigReleaseOwnership() {
        this.swigCMemOwn = false;
        RTCControllerAndroidJNI.RTCCustomMetricsPublisherAdapterInterface_change_ownership(this, this.swigCPtr, false);
    }

    public void swigTakeOwnership() {
        this.swigCMemOwn = true;
        RTCControllerAndroidJNI.RTCCustomMetricsPublisherAdapterInterface_change_ownership(this, this.swigCPtr, true);
    }

    public RTCCustomMetricsPublisherAdapterInterface() {
        this(RTCControllerAndroidJNI.new_RTCCustomMetricsPublisherAdapterInterface(), true);
        RTCControllerAndroidJNI.RTCCustomMetricsPublisherAdapterInterface_director_connect(this, this.swigCPtr, true, true);
    }
}
