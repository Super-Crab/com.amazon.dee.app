package org.pjsip.pjsua2;
/* loaded from: classes5.dex */
public class OnInstantMessageStatusParam {
    protected transient boolean swigCMemOwn;
    private transient long swigCPtr;

    /* JADX INFO: Access modifiers changed from: protected */
    public OnInstantMessageStatusParam(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static long getCPtr(OnInstantMessageStatusParam onInstantMessageStatusParam) {
        if (onInstantMessageStatusParam == null) {
            return 0L;
        }
        return onInstantMessageStatusParam.swigCPtr;
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                pjsua2JNI.delete_OnInstantMessageStatusParam(this.swigCPtr);
            }
            this.swigCPtr = 0L;
        }
    }

    protected void finalize() {
        delete();
    }

    public pjsip_status_code getCode() {
        return pjsip_status_code.swigToEnum(pjsua2JNI.OnInstantMessageStatusParam_code_get(this.swigCPtr, this));
    }

    public String getMsgBody() {
        return pjsua2JNI.OnInstantMessageStatusParam_msgBody_get(this.swigCPtr, this);
    }

    public SipRxData getRdata() {
        long OnInstantMessageStatusParam_rdata_get = pjsua2JNI.OnInstantMessageStatusParam_rdata_get(this.swigCPtr, this);
        if (OnInstantMessageStatusParam_rdata_get == 0) {
            return null;
        }
        return new SipRxData(OnInstantMessageStatusParam_rdata_get, false);
    }

    public String getReason() {
        return pjsua2JNI.OnInstantMessageStatusParam_reason_get(this.swigCPtr, this);
    }

    public String getToUri() {
        return pjsua2JNI.OnInstantMessageStatusParam_toUri_get(this.swigCPtr, this);
    }

    public SWIGTYPE_p_void getUserData() {
        long OnInstantMessageStatusParam_userData_get = pjsua2JNI.OnInstantMessageStatusParam_userData_get(this.swigCPtr, this);
        if (OnInstantMessageStatusParam_userData_get == 0) {
            return null;
        }
        return new SWIGTYPE_p_void(OnInstantMessageStatusParam_userData_get, false);
    }

    public void setCode(pjsip_status_code pjsip_status_codeVar) {
        pjsua2JNI.OnInstantMessageStatusParam_code_set(this.swigCPtr, this, pjsip_status_codeVar.swigValue());
    }

    public void setMsgBody(String str) {
        pjsua2JNI.OnInstantMessageStatusParam_msgBody_set(this.swigCPtr, this, str);
    }

    public void setRdata(SipRxData sipRxData) {
        pjsua2JNI.OnInstantMessageStatusParam_rdata_set(this.swigCPtr, this, SipRxData.getCPtr(sipRxData), sipRxData);
    }

    public void setReason(String str) {
        pjsua2JNI.OnInstantMessageStatusParam_reason_set(this.swigCPtr, this, str);
    }

    public void setToUri(String str) {
        pjsua2JNI.OnInstantMessageStatusParam_toUri_set(this.swigCPtr, this, str);
    }

    public void setUserData(SWIGTYPE_p_void sWIGTYPE_p_void) {
        pjsua2JNI.OnInstantMessageStatusParam_userData_set(this.swigCPtr, this, SWIGTYPE_p_void.getCPtr(sWIGTYPE_p_void));
    }

    public OnInstantMessageStatusParam() {
        this(pjsua2JNI.new_OnInstantMessageStatusParam(), true);
    }
}
