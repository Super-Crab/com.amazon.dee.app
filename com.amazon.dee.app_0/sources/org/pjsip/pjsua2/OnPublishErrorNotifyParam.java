package org.pjsip.pjsua2;
/* loaded from: classes5.dex */
public class OnPublishErrorNotifyParam {
    protected transient boolean swigCMemOwn;
    private transient long swigCPtr;

    /* JADX INFO: Access modifiers changed from: protected */
    public OnPublishErrorNotifyParam(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static long getCPtr(OnPublishErrorNotifyParam onPublishErrorNotifyParam) {
        if (onPublishErrorNotifyParam == null) {
            return 0L;
        }
        return onPublishErrorNotifyParam.swigCPtr;
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                pjsua2JNI.delete_OnPublishErrorNotifyParam(this.swigCPtr);
            }
            this.swigCPtr = 0L;
        }
    }

    protected void finalize() {
        delete();
    }

    public String getReason() {
        return pjsua2JNI.OnPublishErrorNotifyParam_reason_get(this.swigCPtr, this);
    }

    public pjsip_status_code getStatusCode() {
        return pjsip_status_code.swigToEnum(pjsua2JNI.OnPublishErrorNotifyParam_statusCode_get(this.swigCPtr, this));
    }

    public void setReason(String str) {
        pjsua2JNI.OnPublishErrorNotifyParam_reason_set(this.swigCPtr, this, str);
    }

    public void setStatusCode(pjsip_status_code pjsip_status_codeVar) {
        pjsua2JNI.OnPublishErrorNotifyParam_statusCode_set(this.swigCPtr, this, pjsip_status_codeVar.swigValue());
    }

    public OnPublishErrorNotifyParam() {
        this(pjsua2JNI.new_OnPublishErrorNotifyParam(), true);
    }
}
