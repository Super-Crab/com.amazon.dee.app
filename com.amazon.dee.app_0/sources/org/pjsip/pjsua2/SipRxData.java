package org.pjsip.pjsua2;
/* loaded from: classes5.dex */
public class SipRxData {
    protected transient boolean swigCMemOwn;
    private transient long swigCPtr;

    /* JADX INFO: Access modifiers changed from: protected */
    public SipRxData(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static long getCPtr(SipRxData sipRxData) {
        if (sipRxData == null) {
            return 0L;
        }
        return sipRxData.swigCPtr;
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                pjsua2JNI.delete_SipRxData(this.swigCPtr);
            }
            this.swigCPtr = 0L;
        }
    }

    protected void finalize() {
        delete();
    }

    public String getInfo() {
        return pjsua2JNI.SipRxData_info_get(this.swigCPtr, this);
    }

    public SWIGTYPE_p_void getPjRxData() {
        long SipRxData_pjRxData_get = pjsua2JNI.SipRxData_pjRxData_get(this.swigCPtr, this);
        if (SipRxData_pjRxData_get == 0) {
            return null;
        }
        return new SWIGTYPE_p_void(SipRxData_pjRxData_get, false);
    }

    public String getSdp() {
        return pjsua2JNI.SipRxData_sdp_get(this.swigCPtr, this);
    }

    public SipMsg getSipMsg() {
        long SipRxData_sipMsg_get = pjsua2JNI.SipRxData_sipMsg_get(this.swigCPtr, this);
        if (SipRxData_sipMsg_get == 0) {
            return null;
        }
        return new SipMsg(SipRxData_sipMsg_get, false);
    }

    public String getSrcAddress() {
        return pjsua2JNI.SipRxData_srcAddress_get(this.swigCPtr, this);
    }

    public String getWholeMsg() {
        return pjsua2JNI.SipRxData_wholeMsg_get(this.swigCPtr, this);
    }

    public void setInfo(String str) {
        pjsua2JNI.SipRxData_info_set(this.swigCPtr, this, str);
    }

    public void setPjRxData(SWIGTYPE_p_void sWIGTYPE_p_void) {
        pjsua2JNI.SipRxData_pjRxData_set(this.swigCPtr, this, SWIGTYPE_p_void.getCPtr(sWIGTYPE_p_void));
    }

    public void setSdp(String str) {
        pjsua2JNI.SipRxData_sdp_set(this.swigCPtr, this, str);
    }

    public void setSipMsg(SipMsg sipMsg) {
        pjsua2JNI.SipRxData_sipMsg_set(this.swigCPtr, this, SipMsg.getCPtr(sipMsg), sipMsg);
    }

    public void setSrcAddress(String str) {
        pjsua2JNI.SipRxData_srcAddress_set(this.swigCPtr, this, str);
    }

    public void setWholeMsg(String str) {
        pjsua2JNI.SipRxData_wholeMsg_set(this.swigCPtr, this, str);
    }

    public SipRxData() {
        this(pjsua2JNI.new_SipRxData(), true);
    }
}
