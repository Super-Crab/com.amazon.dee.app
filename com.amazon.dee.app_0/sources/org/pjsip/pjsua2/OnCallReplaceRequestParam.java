package org.pjsip.pjsua2;
/* loaded from: classes5.dex */
public class OnCallReplaceRequestParam {
    protected transient boolean swigCMemOwn;
    private transient long swigCPtr;

    /* JADX INFO: Access modifiers changed from: protected */
    public OnCallReplaceRequestParam(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static long getCPtr(OnCallReplaceRequestParam onCallReplaceRequestParam) {
        if (onCallReplaceRequestParam == null) {
            return 0L;
        }
        return onCallReplaceRequestParam.swigCPtr;
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                pjsua2JNI.delete_OnCallReplaceRequestParam(this.swigCPtr);
            }
            this.swigCPtr = 0L;
        }
    }

    protected void finalize() {
        delete();
    }

    public CallSetting getOpt() {
        long OnCallReplaceRequestParam_opt_get = pjsua2JNI.OnCallReplaceRequestParam_opt_get(this.swigCPtr, this);
        if (OnCallReplaceRequestParam_opt_get == 0) {
            return null;
        }
        return new CallSetting(OnCallReplaceRequestParam_opt_get, false);
    }

    public SipRxData getRdata() {
        long OnCallReplaceRequestParam_rdata_get = pjsua2JNI.OnCallReplaceRequestParam_rdata_get(this.swigCPtr, this);
        if (OnCallReplaceRequestParam_rdata_get == 0) {
            return null;
        }
        return new SipRxData(OnCallReplaceRequestParam_rdata_get, false);
    }

    public String getReason() {
        return pjsua2JNI.OnCallReplaceRequestParam_reason_get(this.swigCPtr, this);
    }

    public pjsip_status_code getStatusCode() {
        return pjsip_status_code.swigToEnum(pjsua2JNI.OnCallReplaceRequestParam_statusCode_get(this.swigCPtr, this));
    }

    public void setOpt(CallSetting callSetting) {
        pjsua2JNI.OnCallReplaceRequestParam_opt_set(this.swigCPtr, this, CallSetting.getCPtr(callSetting), callSetting);
    }

    public void setRdata(SipRxData sipRxData) {
        pjsua2JNI.OnCallReplaceRequestParam_rdata_set(this.swigCPtr, this, SipRxData.getCPtr(sipRxData), sipRxData);
    }

    public void setReason(String str) {
        pjsua2JNI.OnCallReplaceRequestParam_reason_set(this.swigCPtr, this, str);
    }

    public void setStatusCode(pjsip_status_code pjsip_status_codeVar) {
        pjsua2JNI.OnCallReplaceRequestParam_statusCode_set(this.swigCPtr, this, pjsip_status_codeVar.swigValue());
    }

    public OnCallReplaceRequestParam() {
        this(pjsua2JNI.new_OnCallReplaceRequestParam(), true);
    }
}
