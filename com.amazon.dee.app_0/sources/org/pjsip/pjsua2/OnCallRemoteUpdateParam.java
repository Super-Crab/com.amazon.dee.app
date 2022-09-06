package org.pjsip.pjsua2;
/* loaded from: classes5.dex */
public class OnCallRemoteUpdateParam {
    protected transient boolean swigCMemOwn;
    private transient long swigCPtr;

    /* JADX INFO: Access modifiers changed from: protected */
    public OnCallRemoteUpdateParam(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static long getCPtr(OnCallRemoteUpdateParam onCallRemoteUpdateParam) {
        if (onCallRemoteUpdateParam == null) {
            return 0L;
        }
        return onCallRemoteUpdateParam.swigCPtr;
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                pjsua2JNI.delete_OnCallRemoteUpdateParam(this.swigCPtr);
            }
            this.swigCPtr = 0L;
        }
    }

    protected void finalize() {
        delete();
    }

    public String getCType() {
        return pjsua2JNI.OnCallRemoteUpdateParam_cType_get(this.swigCPtr, this);
    }

    public boolean getIsOffer() {
        return pjsua2JNI.OnCallRemoteUpdateParam_isOffer_get(this.swigCPtr, this);
    }

    public String getMsgBody() {
        return pjsua2JNI.OnCallRemoteUpdateParam_msgBody_get(this.swigCPtr, this);
    }

    public void setCType(String str) {
        pjsua2JNI.OnCallRemoteUpdateParam_cType_set(this.swigCPtr, this, str);
    }

    public void setIsOffer(boolean z) {
        pjsua2JNI.OnCallRemoteUpdateParam_isOffer_set(this.swigCPtr, this, z);
    }

    public void setMsgBody(String str) {
        pjsua2JNI.OnCallRemoteUpdateParam_msgBody_set(this.swigCPtr, this, str);
    }

    public OnCallRemoteUpdateParam() {
        this(pjsua2JNI.new_OnCallRemoteUpdateParam(), true);
    }
}
