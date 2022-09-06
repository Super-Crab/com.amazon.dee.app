package org.pjsip.pjsua2;
/* loaded from: classes5.dex */
public class OnIncomingSubscribeParam {
    protected transient boolean swigCMemOwn;
    private transient long swigCPtr;

    /* JADX INFO: Access modifiers changed from: protected */
    public OnIncomingSubscribeParam(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static long getCPtr(OnIncomingSubscribeParam onIncomingSubscribeParam) {
        if (onIncomingSubscribeParam == null) {
            return 0L;
        }
        return onIncomingSubscribeParam.swigCPtr;
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                pjsua2JNI.delete_OnIncomingSubscribeParam(this.swigCPtr);
            }
            this.swigCPtr = 0L;
        }
    }

    protected void finalize() {
        delete();
    }

    public pjsip_status_code getCode() {
        return pjsip_status_code.swigToEnum(pjsua2JNI.OnIncomingSubscribeParam_code_get(this.swigCPtr, this));
    }

    public String getFromUri() {
        return pjsua2JNI.OnIncomingSubscribeParam_fromUri_get(this.swigCPtr, this);
    }

    public SipRxData getRdata() {
        long OnIncomingSubscribeParam_rdata_get = pjsua2JNI.OnIncomingSubscribeParam_rdata_get(this.swigCPtr, this);
        if (OnIncomingSubscribeParam_rdata_get == 0) {
            return null;
        }
        return new SipRxData(OnIncomingSubscribeParam_rdata_get, false);
    }

    public String getReason() {
        return pjsua2JNI.OnIncomingSubscribeParam_reason_get(this.swigCPtr, this);
    }

    public SWIGTYPE_p_void getSrvPres() {
        long OnIncomingSubscribeParam_srvPres_get = pjsua2JNI.OnIncomingSubscribeParam_srvPres_get(this.swigCPtr, this);
        if (OnIncomingSubscribeParam_srvPres_get == 0) {
            return null;
        }
        return new SWIGTYPE_p_void(OnIncomingSubscribeParam_srvPres_get, false);
    }

    public SipTxOption getTxOption() {
        long OnIncomingSubscribeParam_txOption_get = pjsua2JNI.OnIncomingSubscribeParam_txOption_get(this.swigCPtr, this);
        if (OnIncomingSubscribeParam_txOption_get == 0) {
            return null;
        }
        return new SipTxOption(OnIncomingSubscribeParam_txOption_get, false);
    }

    public void setCode(pjsip_status_code pjsip_status_codeVar) {
        pjsua2JNI.OnIncomingSubscribeParam_code_set(this.swigCPtr, this, pjsip_status_codeVar.swigValue());
    }

    public void setFromUri(String str) {
        pjsua2JNI.OnIncomingSubscribeParam_fromUri_set(this.swigCPtr, this, str);
    }

    public void setRdata(SipRxData sipRxData) {
        pjsua2JNI.OnIncomingSubscribeParam_rdata_set(this.swigCPtr, this, SipRxData.getCPtr(sipRxData), sipRxData);
    }

    public void setReason(String str) {
        pjsua2JNI.OnIncomingSubscribeParam_reason_set(this.swigCPtr, this, str);
    }

    public void setSrvPres(SWIGTYPE_p_void sWIGTYPE_p_void) {
        pjsua2JNI.OnIncomingSubscribeParam_srvPres_set(this.swigCPtr, this, SWIGTYPE_p_void.getCPtr(sWIGTYPE_p_void));
    }

    public void setTxOption(SipTxOption sipTxOption) {
        pjsua2JNI.OnIncomingSubscribeParam_txOption_set(this.swigCPtr, this, SipTxOption.getCPtr(sipTxOption), sipTxOption);
    }

    public OnIncomingSubscribeParam() {
        this(pjsua2JNI.new_OnIncomingSubscribeParam(), true);
    }
}
