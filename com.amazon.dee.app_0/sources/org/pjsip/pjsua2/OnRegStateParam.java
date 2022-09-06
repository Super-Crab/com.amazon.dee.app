package org.pjsip.pjsua2;
/* loaded from: classes5.dex */
public class OnRegStateParam {
    protected transient boolean swigCMemOwn;
    private transient long swigCPtr;

    /* JADX INFO: Access modifiers changed from: protected */
    public OnRegStateParam(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static long getCPtr(OnRegStateParam onRegStateParam) {
        if (onRegStateParam == null) {
            return 0L;
        }
        return onRegStateParam.swigCPtr;
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                pjsua2JNI.delete_OnRegStateParam(this.swigCPtr);
            }
            this.swigCPtr = 0L;
        }
    }

    protected void finalize() {
        delete();
    }

    public pjsip_status_code getCode() {
        return pjsip_status_code.swigToEnum(pjsua2JNI.OnRegStateParam_code_get(this.swigCPtr, this));
    }

    public int getExpiration() {
        return pjsua2JNI.OnRegStateParam_expiration_get(this.swigCPtr, this);
    }

    public SipRxData getRdata() {
        long OnRegStateParam_rdata_get = pjsua2JNI.OnRegStateParam_rdata_get(this.swigCPtr, this);
        if (OnRegStateParam_rdata_get == 0) {
            return null;
        }
        return new SipRxData(OnRegStateParam_rdata_get, false);
    }

    public String getReason() {
        return pjsua2JNI.OnRegStateParam_reason_get(this.swigCPtr, this);
    }

    public int getStatus() {
        return pjsua2JNI.OnRegStateParam_status_get(this.swigCPtr, this);
    }

    public void setCode(pjsip_status_code pjsip_status_codeVar) {
        pjsua2JNI.OnRegStateParam_code_set(this.swigCPtr, this, pjsip_status_codeVar.swigValue());
    }

    public void setExpiration(int i) {
        pjsua2JNI.OnRegStateParam_expiration_set(this.swigCPtr, this, i);
    }

    public void setRdata(SipRxData sipRxData) {
        pjsua2JNI.OnRegStateParam_rdata_set(this.swigCPtr, this, SipRxData.getCPtr(sipRxData), sipRxData);
    }

    public void setReason(String str) {
        pjsua2JNI.OnRegStateParam_reason_set(this.swigCPtr, this, str);
    }

    public void setStatus(int i) {
        pjsua2JNI.OnRegStateParam_status_set(this.swigCPtr, this, i);
    }

    public OnRegStateParam() {
        this(pjsua2JNI.new_OnRegStateParam(), true);
    }
}
