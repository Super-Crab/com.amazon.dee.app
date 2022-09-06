package org.pjsip.pjsua2;
/* loaded from: classes5.dex */
public class SipTransaction {
    protected transient boolean swigCMemOwn;
    private transient long swigCPtr;

    /* JADX INFO: Access modifiers changed from: protected */
    public SipTransaction(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static long getCPtr(SipTransaction sipTransaction) {
        if (sipTransaction == null) {
            return 0L;
        }
        return sipTransaction.swigCPtr;
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                pjsua2JNI.delete_SipTransaction(this.swigCPtr);
            }
            this.swigCPtr = 0L;
        }
    }

    protected void finalize() {
        delete();
    }

    public int getCseq() {
        return pjsua2JNI.SipTransaction_cseq_get(this.swigCPtr, this);
    }

    public SipTxData getLastTx() {
        long SipTransaction_lastTx_get = pjsua2JNI.SipTransaction_lastTx_get(this.swigCPtr, this);
        if (SipTransaction_lastTx_get == 0) {
            return null;
        }
        return new SipTxData(SipTransaction_lastTx_get, false);
    }

    public String getMethod() {
        return pjsua2JNI.SipTransaction_method_get(this.swigCPtr, this);
    }

    public SWIGTYPE_p_void getPjTransaction() {
        long SipTransaction_pjTransaction_get = pjsua2JNI.SipTransaction_pjTransaction_get(this.swigCPtr, this);
        if (SipTransaction_pjTransaction_get == 0) {
            return null;
        }
        return new SWIGTYPE_p_void(SipTransaction_pjTransaction_get, false);
    }

    public pjsip_role_e getRole() {
        return pjsip_role_e.swigToEnum(pjsua2JNI.SipTransaction_role_get(this.swigCPtr, this));
    }

    public pjsip_tsx_state_e getState() {
        return pjsip_tsx_state_e.swigToEnum(pjsua2JNI.SipTransaction_state_get(this.swigCPtr, this));
    }

    public int getStatusCode() {
        return pjsua2JNI.SipTransaction_statusCode_get(this.swigCPtr, this);
    }

    public String getStatusText() {
        return pjsua2JNI.SipTransaction_statusText_get(this.swigCPtr, this);
    }

    public void setCseq(int i) {
        pjsua2JNI.SipTransaction_cseq_set(this.swigCPtr, this, i);
    }

    public void setLastTx(SipTxData sipTxData) {
        pjsua2JNI.SipTransaction_lastTx_set(this.swigCPtr, this, SipTxData.getCPtr(sipTxData), sipTxData);
    }

    public void setMethod(String str) {
        pjsua2JNI.SipTransaction_method_set(this.swigCPtr, this, str);
    }

    public void setPjTransaction(SWIGTYPE_p_void sWIGTYPE_p_void) {
        pjsua2JNI.SipTransaction_pjTransaction_set(this.swigCPtr, this, SWIGTYPE_p_void.getCPtr(sWIGTYPE_p_void));
    }

    public void setRole(pjsip_role_e pjsip_role_eVar) {
        pjsua2JNI.SipTransaction_role_set(this.swigCPtr, this, pjsip_role_eVar.swigValue());
    }

    public void setState(pjsip_tsx_state_e pjsip_tsx_state_eVar) {
        pjsua2JNI.SipTransaction_state_set(this.swigCPtr, this, pjsip_tsx_state_eVar.swigValue());
    }

    public void setStatusCode(int i) {
        pjsua2JNI.SipTransaction_statusCode_set(this.swigCPtr, this, i);
    }

    public void setStatusText(String str) {
        pjsua2JNI.SipTransaction_statusText_set(this.swigCPtr, this, str);
    }

    public SipTransaction() {
        this(pjsua2JNI.new_SipTransaction(), true);
    }
}
