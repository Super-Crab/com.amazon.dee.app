package com.amazon.rtcsc.wrappers;
/* loaded from: classes13.dex */
public class RTCEventListenerInterface {
    protected transient boolean swigCMemOwn;
    private transient long swigCPtr;

    protected RTCEventListenerInterface(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static long getCPtr(RTCEventListenerInterface rTCEventListenerInterface) {
        if (rTCEventListenerInterface == null) {
            return 0L;
        }
        return rTCEventListenerInterface.swigCPtr;
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

    public void onRTCSessionContextUpdated(String str) {
        RTCControllerAndroidJNI.RTCEventListenerInterface_onRTCSessionContextUpdated(this.swigCPtr, this, str);
    }

    public void onSendEvent(String str, String str2, String str3, String str4) {
        RTCControllerAndroidJNI.RTCEventListenerInterface_onSendEvent(this.swigCPtr, this, str, str2, str3, str4);
    }

    protected void swigDirectorDisconnect() {
        this.swigCMemOwn = false;
        delete();
    }

    public void swigReleaseOwnership() {
        this.swigCMemOwn = false;
        RTCControllerAndroidJNI.RTCEventListenerInterface_change_ownership(this, this.swigCPtr, false);
    }

    public void swigTakeOwnership() {
        this.swigCMemOwn = true;
        RTCControllerAndroidJNI.RTCEventListenerInterface_change_ownership(this, this.swigCPtr, true);
    }

    public RTCEventListenerInterface() {
        this(RTCControllerAndroidJNI.new_RTCEventListenerInterface(), true);
        RTCControllerAndroidJNI.RTCEventListenerInterface_director_connect(this, this.swigCPtr, true, true);
    }
}
