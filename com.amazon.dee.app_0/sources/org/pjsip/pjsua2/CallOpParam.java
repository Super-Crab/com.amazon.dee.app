package org.pjsip.pjsua2;
/* loaded from: classes5.dex */
public class CallOpParam {
    protected transient boolean swigCMemOwn;
    private transient long swigCPtr;

    protected CallOpParam(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static long getCPtr(CallOpParam callOpParam) {
        if (callOpParam == null) {
            return 0L;
        }
        return callOpParam.swigCPtr;
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                pjsua2JNI.delete_CallOpParam(this.swigCPtr);
            }
            this.swigCPtr = 0L;
        }
    }

    protected void finalize() {
        delete();
    }

    public CallSetting getOpt() {
        long CallOpParam_opt_get = pjsua2JNI.CallOpParam_opt_get(this.swigCPtr, this);
        if (CallOpParam_opt_get == 0) {
            return null;
        }
        return new CallSetting(CallOpParam_opt_get, false);
    }

    public long getOptions() {
        return pjsua2JNI.CallOpParam_options_get(this.swigCPtr, this);
    }

    public String getReason() {
        return pjsua2JNI.CallOpParam_reason_get(this.swigCPtr, this);
    }

    public pjsip_status_code getStatusCode() {
        return pjsip_status_code.swigToEnum(pjsua2JNI.CallOpParam_statusCode_get(this.swigCPtr, this));
    }

    public SipTxOption getTxOption() {
        long CallOpParam_txOption_get = pjsua2JNI.CallOpParam_txOption_get(this.swigCPtr, this);
        if (CallOpParam_txOption_get == 0) {
            return null;
        }
        return new SipTxOption(CallOpParam_txOption_get, false);
    }

    public void setOpt(CallSetting callSetting) {
        pjsua2JNI.CallOpParam_opt_set(this.swigCPtr, this, CallSetting.getCPtr(callSetting), callSetting);
    }

    public void setOptions(long j) {
        pjsua2JNI.CallOpParam_options_set(this.swigCPtr, this, j);
    }

    public void setReason(String str) {
        pjsua2JNI.CallOpParam_reason_set(this.swigCPtr, this, str);
    }

    public void setStatusCode(pjsip_status_code pjsip_status_codeVar) {
        pjsua2JNI.CallOpParam_statusCode_set(this.swigCPtr, this, pjsip_status_codeVar.swigValue());
    }

    public void setTxOption(SipTxOption sipTxOption) {
        pjsua2JNI.CallOpParam_txOption_set(this.swigCPtr, this, SipTxOption.getCPtr(sipTxOption), sipTxOption);
    }

    public CallOpParam(boolean z) {
        this(pjsua2JNI.new_CallOpParam__SWIG_0(z), true);
    }

    public CallOpParam() {
        this(pjsua2JNI.new_CallOpParam__SWIG_1(), true);
    }
}
