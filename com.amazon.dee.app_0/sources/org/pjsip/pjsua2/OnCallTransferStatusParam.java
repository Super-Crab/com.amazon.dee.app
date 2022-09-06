package org.pjsip.pjsua2;
/* loaded from: classes5.dex */
public class OnCallTransferStatusParam {
    protected transient boolean swigCMemOwn;
    private transient long swigCPtr;

    /* JADX INFO: Access modifiers changed from: protected */
    public OnCallTransferStatusParam(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static long getCPtr(OnCallTransferStatusParam onCallTransferStatusParam) {
        if (onCallTransferStatusParam == null) {
            return 0L;
        }
        return onCallTransferStatusParam.swigCPtr;
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                pjsua2JNI.delete_OnCallTransferStatusParam(this.swigCPtr);
            }
            this.swigCPtr = 0L;
        }
    }

    protected void finalize() {
        delete();
    }

    public boolean getCont() {
        return pjsua2JNI.OnCallTransferStatusParam_cont_get(this.swigCPtr, this);
    }

    public boolean getFinalNotify() {
        return pjsua2JNI.OnCallTransferStatusParam_finalNotify_get(this.swigCPtr, this);
    }

    public String getReason() {
        return pjsua2JNI.OnCallTransferStatusParam_reason_get(this.swigCPtr, this);
    }

    public pjsip_status_code getStatusCode() {
        return pjsip_status_code.swigToEnum(pjsua2JNI.OnCallTransferStatusParam_statusCode_get(this.swigCPtr, this));
    }

    public void setCont(boolean z) {
        pjsua2JNI.OnCallTransferStatusParam_cont_set(this.swigCPtr, this, z);
    }

    public void setFinalNotify(boolean z) {
        pjsua2JNI.OnCallTransferStatusParam_finalNotify_set(this.swigCPtr, this, z);
    }

    public void setReason(String str) {
        pjsua2JNI.OnCallTransferStatusParam_reason_set(this.swigCPtr, this, str);
    }

    public void setStatusCode(pjsip_status_code pjsip_status_codeVar) {
        pjsua2JNI.OnCallTransferStatusParam_statusCode_set(this.swigCPtr, this, pjsip_status_codeVar.swigValue());
    }

    public OnCallTransferStatusParam() {
        this(pjsua2JNI.new_OnCallTransferStatusParam(), true);
    }
}
