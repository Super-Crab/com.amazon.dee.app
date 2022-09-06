package org.pjsip.pjsua2;
/* loaded from: classes5.dex */
public class OnCallSipInfoParam {
    protected transient boolean swigCMemOwn;
    private transient long swigCPtr;

    /* JADX INFO: Access modifiers changed from: protected */
    public OnCallSipInfoParam(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static long getCPtr(OnCallSipInfoParam onCallSipInfoParam) {
        if (onCallSipInfoParam == null) {
            return 0L;
        }
        return onCallSipInfoParam.swigCPtr;
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                pjsua2JNI.delete_OnCallSipInfoParam(this.swigCPtr);
            }
            this.swigCPtr = 0L;
        }
    }

    protected void finalize() {
        delete();
    }

    public String getCType() {
        return pjsua2JNI.OnCallSipInfoParam_cType_get(this.swigCPtr, this);
    }

    public String getMsgBody() {
        return pjsua2JNI.OnCallSipInfoParam_msgBody_get(this.swigCPtr, this);
    }

    public void setCType(String str) {
        pjsua2JNI.OnCallSipInfoParam_cType_set(this.swigCPtr, this, str);
    }

    public void setMsgBody(String str) {
        pjsua2JNI.OnCallSipInfoParam_msgBody_set(this.swigCPtr, this, str);
    }

    public OnCallSipInfoParam() {
        this(pjsua2JNI.new_OnCallSipInfoParam(), true);
    }
}
