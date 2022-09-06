package org.pjsip.pjsua2;
/* loaded from: classes5.dex */
public class SendInstantMessageParam {
    protected transient boolean swigCMemOwn;
    private transient long swigCPtr;

    protected SendInstantMessageParam(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static long getCPtr(SendInstantMessageParam sendInstantMessageParam) {
        if (sendInstantMessageParam == null) {
            return 0L;
        }
        return sendInstantMessageParam.swigCPtr;
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                pjsua2JNI.delete_SendInstantMessageParam(this.swigCPtr);
            }
            this.swigCPtr = 0L;
        }
    }

    protected void finalize() {
        delete();
    }

    public String getContent() {
        return pjsua2JNI.SendInstantMessageParam_content_get(this.swigCPtr, this);
    }

    public String getContentType() {
        return pjsua2JNI.SendInstantMessageParam_contentType_get(this.swigCPtr, this);
    }

    public SipTxOption getTxOption() {
        long SendInstantMessageParam_txOption_get = pjsua2JNI.SendInstantMessageParam_txOption_get(this.swigCPtr, this);
        if (SendInstantMessageParam_txOption_get == 0) {
            return null;
        }
        return new SipTxOption(SendInstantMessageParam_txOption_get, false);
    }

    public SWIGTYPE_p_void getUserData() {
        long SendInstantMessageParam_userData_get = pjsua2JNI.SendInstantMessageParam_userData_get(this.swigCPtr, this);
        if (SendInstantMessageParam_userData_get == 0) {
            return null;
        }
        return new SWIGTYPE_p_void(SendInstantMessageParam_userData_get, false);
    }

    public void setContent(String str) {
        pjsua2JNI.SendInstantMessageParam_content_set(this.swigCPtr, this, str);
    }

    public void setContentType(String str) {
        pjsua2JNI.SendInstantMessageParam_contentType_set(this.swigCPtr, this, str);
    }

    public void setTxOption(SipTxOption sipTxOption) {
        pjsua2JNI.SendInstantMessageParam_txOption_set(this.swigCPtr, this, SipTxOption.getCPtr(sipTxOption), sipTxOption);
    }

    public void setUserData(SWIGTYPE_p_void sWIGTYPE_p_void) {
        pjsua2JNI.SendInstantMessageParam_userData_set(this.swigCPtr, this, SWIGTYPE_p_void.getCPtr(sWIGTYPE_p_void));
    }

    public SendInstantMessageParam() {
        this(pjsua2JNI.new_SendInstantMessageParam(), true);
    }
}
