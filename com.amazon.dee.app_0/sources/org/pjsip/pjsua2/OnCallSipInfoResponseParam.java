package org.pjsip.pjsua2;
/* loaded from: classes5.dex */
public class OnCallSipInfoResponseParam {
    protected transient boolean swigCMemOwn;
    private transient long swigCPtr;

    /* JADX INFO: Access modifiers changed from: protected */
    public OnCallSipInfoResponseParam(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static long getCPtr(OnCallSipInfoResponseParam onCallSipInfoResponseParam) {
        if (onCallSipInfoResponseParam == null) {
            return 0L;
        }
        return onCallSipInfoResponseParam.swigCPtr;
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                pjsua2JNI.delete_OnCallSipInfoResponseParam(this.swigCPtr);
            }
            this.swigCPtr = 0L;
        }
    }

    protected void finalize() {
        delete();
    }

    public String getContentType() {
        return pjsua2JNI.OnCallSipInfoResponseParam_contentType_get(this.swigCPtr, this);
    }

    public int getCseq() {
        return pjsua2JNI.OnCallSipInfoResponseParam_cseq_get(this.swigCPtr, this);
    }

    public String getReason() {
        return pjsua2JNI.OnCallSipInfoResponseParam_reason_get(this.swigCPtr, this);
    }

    public int getStatusCode() {
        return pjsua2JNI.OnCallSipInfoResponseParam_statusCode_get(this.swigCPtr, this);
    }

    public void setContentType(String str) {
        pjsua2JNI.OnCallSipInfoResponseParam_contentType_set(this.swigCPtr, this, str);
    }

    public void setCseq(int i) {
        pjsua2JNI.OnCallSipInfoResponseParam_cseq_set(this.swigCPtr, this, i);
    }

    public void setReason(String str) {
        pjsua2JNI.OnCallSipInfoResponseParam_reason_set(this.swigCPtr, this, str);
    }

    public void setStatusCode(int i) {
        pjsua2JNI.OnCallSipInfoResponseParam_statusCode_set(this.swigCPtr, this, i);
    }

    public OnCallSipInfoResponseParam() {
        this(pjsua2JNI.new_OnCallSipInfoResponseParam(), true);
    }
}
