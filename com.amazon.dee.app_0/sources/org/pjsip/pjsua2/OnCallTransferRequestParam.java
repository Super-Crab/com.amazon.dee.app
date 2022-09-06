package org.pjsip.pjsua2;
/* loaded from: classes5.dex */
public class OnCallTransferRequestParam {
    protected transient boolean swigCMemOwn;
    private transient long swigCPtr;

    /* JADX INFO: Access modifiers changed from: protected */
    public OnCallTransferRequestParam(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static long getCPtr(OnCallTransferRequestParam onCallTransferRequestParam) {
        if (onCallTransferRequestParam == null) {
            return 0L;
        }
        return onCallTransferRequestParam.swigCPtr;
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                pjsua2JNI.delete_OnCallTransferRequestParam(this.swigCPtr);
            }
            this.swigCPtr = 0L;
        }
    }

    protected void finalize() {
        delete();
    }

    public String getDstUri() {
        return pjsua2JNI.OnCallTransferRequestParam_dstUri_get(this.swigCPtr, this);
    }

    public CallSetting getOpt() {
        long OnCallTransferRequestParam_opt_get = pjsua2JNI.OnCallTransferRequestParam_opt_get(this.swigCPtr, this);
        if (OnCallTransferRequestParam_opt_get == 0) {
            return null;
        }
        return new CallSetting(OnCallTransferRequestParam_opt_get, false);
    }

    public pjsip_status_code getStatusCode() {
        return pjsip_status_code.swigToEnum(pjsua2JNI.OnCallTransferRequestParam_statusCode_get(this.swigCPtr, this));
    }

    public void setDstUri(String str) {
        pjsua2JNI.OnCallTransferRequestParam_dstUri_set(this.swigCPtr, this, str);
    }

    public void setOpt(CallSetting callSetting) {
        pjsua2JNI.OnCallTransferRequestParam_opt_set(this.swigCPtr, this, CallSetting.getCPtr(callSetting), callSetting);
    }

    public void setStatusCode(pjsip_status_code pjsip_status_codeVar) {
        pjsua2JNI.OnCallTransferRequestParam_statusCode_set(this.swigCPtr, this, pjsip_status_codeVar.swigValue());
    }

    public OnCallTransferRequestParam() {
        this(pjsua2JNI.new_OnCallTransferRequestParam(), true);
    }
}
