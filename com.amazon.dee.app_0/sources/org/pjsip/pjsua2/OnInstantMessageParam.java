package org.pjsip.pjsua2;
/* loaded from: classes5.dex */
public class OnInstantMessageParam {
    protected transient boolean swigCMemOwn;
    private transient long swigCPtr;

    /* JADX INFO: Access modifiers changed from: protected */
    public OnInstantMessageParam(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static long getCPtr(OnInstantMessageParam onInstantMessageParam) {
        if (onInstantMessageParam == null) {
            return 0L;
        }
        return onInstantMessageParam.swigCPtr;
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                pjsua2JNI.delete_OnInstantMessageParam(this.swigCPtr);
            }
            this.swigCPtr = 0L;
        }
    }

    protected void finalize() {
        delete();
    }

    public String getContactUri() {
        return pjsua2JNI.OnInstantMessageParam_contactUri_get(this.swigCPtr, this);
    }

    public String getContentType() {
        return pjsua2JNI.OnInstantMessageParam_contentType_get(this.swigCPtr, this);
    }

    public String getFromUri() {
        return pjsua2JNI.OnInstantMessageParam_fromUri_get(this.swigCPtr, this);
    }

    public String getMsgBody() {
        return pjsua2JNI.OnInstantMessageParam_msgBody_get(this.swigCPtr, this);
    }

    public SipRxData getRdata() {
        long OnInstantMessageParam_rdata_get = pjsua2JNI.OnInstantMessageParam_rdata_get(this.swigCPtr, this);
        if (OnInstantMessageParam_rdata_get == 0) {
            return null;
        }
        return new SipRxData(OnInstantMessageParam_rdata_get, false);
    }

    public String getToUri() {
        return pjsua2JNI.OnInstantMessageParam_toUri_get(this.swigCPtr, this);
    }

    public void setContactUri(String str) {
        pjsua2JNI.OnInstantMessageParam_contactUri_set(this.swigCPtr, this, str);
    }

    public void setContentType(String str) {
        pjsua2JNI.OnInstantMessageParam_contentType_set(this.swigCPtr, this, str);
    }

    public void setFromUri(String str) {
        pjsua2JNI.OnInstantMessageParam_fromUri_set(this.swigCPtr, this, str);
    }

    public void setMsgBody(String str) {
        pjsua2JNI.OnInstantMessageParam_msgBody_set(this.swigCPtr, this, str);
    }

    public void setRdata(SipRxData sipRxData) {
        pjsua2JNI.OnInstantMessageParam_rdata_set(this.swigCPtr, this, SipRxData.getCPtr(sipRxData), sipRxData);
    }

    public void setToUri(String str) {
        pjsua2JNI.OnInstantMessageParam_toUri_set(this.swigCPtr, this, str);
    }

    public OnInstantMessageParam() {
        this(pjsua2JNI.new_OnInstantMessageParam(), true);
    }
}
