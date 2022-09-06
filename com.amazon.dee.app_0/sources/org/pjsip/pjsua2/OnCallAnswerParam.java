package org.pjsip.pjsua2;
/* loaded from: classes5.dex */
public class OnCallAnswerParam {
    protected transient boolean swigCMemOwn;
    private transient long swigCPtr;

    /* JADX INFO: Access modifiers changed from: protected */
    public OnCallAnswerParam(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static long getCPtr(OnCallAnswerParam onCallAnswerParam) {
        if (onCallAnswerParam == null) {
            return 0L;
        }
        return onCallAnswerParam.swigCPtr;
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                pjsua2JNI.delete_OnCallAnswerParam(this.swigCPtr);
            }
            this.swigCPtr = 0L;
        }
    }

    protected void finalize() {
        delete();
    }

    public SipRxData getRdata() {
        long OnCallAnswerParam_rdata_get = pjsua2JNI.OnCallAnswerParam_rdata_get(this.swigCPtr, this);
        if (OnCallAnswerParam_rdata_get == 0) {
            return null;
        }
        return new SipRxData(OnCallAnswerParam_rdata_get, false);
    }

    public SdpSession getRemSdp() {
        long OnCallAnswerParam_remSdp_get = pjsua2JNI.OnCallAnswerParam_remSdp_get(this.swigCPtr, this);
        if (OnCallAnswerParam_remSdp_get == 0) {
            return null;
        }
        return new SdpSession(OnCallAnswerParam_remSdp_get, false);
    }

    public void setRdata(SipRxData sipRxData) {
        pjsua2JNI.OnCallAnswerParam_rdata_set(this.swigCPtr, this, SipRxData.getCPtr(sipRxData), sipRxData);
    }

    public void setRemSdp(SdpSession sdpSession) {
        pjsua2JNI.OnCallAnswerParam_remSdp_set(this.swigCPtr, this, SdpSession.getCPtr(sdpSession), sdpSession);
    }

    public OnCallAnswerParam() {
        this(pjsua2JNI.new_OnCallAnswerParam(), true);
    }
}
