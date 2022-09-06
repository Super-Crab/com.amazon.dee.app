package org.pjsip.pjsua2;
/* loaded from: classes5.dex */
public class OnIncomingCallParam {
    protected transient boolean swigCMemOwn;
    private transient long swigCPtr;

    /* JADX INFO: Access modifiers changed from: protected */
    public OnIncomingCallParam(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static long getCPtr(OnIncomingCallParam onIncomingCallParam) {
        if (onIncomingCallParam == null) {
            return 0L;
        }
        return onIncomingCallParam.swigCPtr;
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                pjsua2JNI.delete_OnIncomingCallParam(this.swigCPtr);
            }
            this.swigCPtr = 0L;
        }
    }

    protected void finalize() {
        delete();
    }

    public int getCallId() {
        return pjsua2JNI.OnIncomingCallParam_callId_get(this.swigCPtr, this);
    }

    public SipRxData getRdata() {
        long OnIncomingCallParam_rdata_get = pjsua2JNI.OnIncomingCallParam_rdata_get(this.swigCPtr, this);
        if (OnIncomingCallParam_rdata_get == 0) {
            return null;
        }
        return new SipRxData(OnIncomingCallParam_rdata_get, false);
    }

    public void setCallId(int i) {
        pjsua2JNI.OnIncomingCallParam_callId_set(this.swigCPtr, this, i);
    }

    public void setRdata(SipRxData sipRxData) {
        pjsua2JNI.OnIncomingCallParam_rdata_set(this.swigCPtr, this, SipRxData.getCPtr(sipRxData), sipRxData);
    }

    public OnIncomingCallParam() {
        this(pjsua2JNI.new_OnIncomingCallParam(), true);
    }
}
