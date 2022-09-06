package org.pjsip.pjsua2;
/* loaded from: classes5.dex */
public class SipTxData {
    protected transient boolean swigCMemOwn;
    private transient long swigCPtr;

    /* JADX INFO: Access modifiers changed from: protected */
    public SipTxData(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static long getCPtr(SipTxData sipTxData) {
        if (sipTxData == null) {
            return 0L;
        }
        return sipTxData.swigCPtr;
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                pjsua2JNI.delete_SipTxData(this.swigCPtr);
            }
            this.swigCPtr = 0L;
        }
    }

    protected void finalize() {
        delete();
    }

    public String getDstAddress() {
        return pjsua2JNI.SipTxData_dstAddress_get(this.swigCPtr, this);
    }

    public String getInfo() {
        return pjsua2JNI.SipTxData_info_get(this.swigCPtr, this);
    }

    public SWIGTYPE_p_void getPjTxData() {
        long SipTxData_pjTxData_get = pjsua2JNI.SipTxData_pjTxData_get(this.swigCPtr, this);
        if (SipTxData_pjTxData_get == 0) {
            return null;
        }
        return new SWIGTYPE_p_void(SipTxData_pjTxData_get, false);
    }

    public SipMsg getSipMsg() {
        long SipTxData_sipMsg_get = pjsua2JNI.SipTxData_sipMsg_get(this.swigCPtr, this);
        if (SipTxData_sipMsg_get == 0) {
            return null;
        }
        return new SipMsg(SipTxData_sipMsg_get, false);
    }

    public String getWholeMsg() {
        return pjsua2JNI.SipTxData_wholeMsg_get(this.swigCPtr, this);
    }

    public void setDstAddress(String str) {
        pjsua2JNI.SipTxData_dstAddress_set(this.swigCPtr, this, str);
    }

    public void setInfo(String str) {
        pjsua2JNI.SipTxData_info_set(this.swigCPtr, this, str);
    }

    public void setPjTxData(SWIGTYPE_p_void sWIGTYPE_p_void) {
        pjsua2JNI.SipTxData_pjTxData_set(this.swigCPtr, this, SWIGTYPE_p_void.getCPtr(sWIGTYPE_p_void));
    }

    public void setSipMsg(SipMsg sipMsg) {
        pjsua2JNI.SipTxData_sipMsg_set(this.swigCPtr, this, SipMsg.getCPtr(sipMsg), sipMsg);
    }

    public void setWholeMsg(String str) {
        pjsua2JNI.SipTxData_wholeMsg_set(this.swigCPtr, this, str);
    }

    public SipTxData() {
        this(pjsua2JNI.new_SipTxData(), true);
    }
}
