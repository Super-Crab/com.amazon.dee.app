package org.pjsip.pjsua2;
/* loaded from: classes5.dex */
public class PresNotifyParam {
    protected transient boolean swigCMemOwn;
    private transient long swigCPtr;

    protected PresNotifyParam(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static long getCPtr(PresNotifyParam presNotifyParam) {
        if (presNotifyParam == null) {
            return 0L;
        }
        return presNotifyParam.swigCPtr;
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                pjsua2JNI.delete_PresNotifyParam(this.swigCPtr);
            }
            this.swigCPtr = 0L;
        }
    }

    protected void finalize() {
        delete();
    }

    public String getReason() {
        return pjsua2JNI.PresNotifyParam_reason_get(this.swigCPtr, this);
    }

    public SWIGTYPE_p_void getSrvPres() {
        long PresNotifyParam_srvPres_get = pjsua2JNI.PresNotifyParam_srvPres_get(this.swigCPtr, this);
        if (PresNotifyParam_srvPres_get == 0) {
            return null;
        }
        return new SWIGTYPE_p_void(PresNotifyParam_srvPres_get, false);
    }

    public pjsip_evsub_state getState() {
        return pjsip_evsub_state.swigToEnum(pjsua2JNI.PresNotifyParam_state_get(this.swigCPtr, this));
    }

    public String getStateStr() {
        return pjsua2JNI.PresNotifyParam_stateStr_get(this.swigCPtr, this);
    }

    public SipTxOption getTxOption() {
        long PresNotifyParam_txOption_get = pjsua2JNI.PresNotifyParam_txOption_get(this.swigCPtr, this);
        if (PresNotifyParam_txOption_get == 0) {
            return null;
        }
        return new SipTxOption(PresNotifyParam_txOption_get, false);
    }

    public boolean getWithBody() {
        return pjsua2JNI.PresNotifyParam_withBody_get(this.swigCPtr, this);
    }

    public void setReason(String str) {
        pjsua2JNI.PresNotifyParam_reason_set(this.swigCPtr, this, str);
    }

    public void setSrvPres(SWIGTYPE_p_void sWIGTYPE_p_void) {
        pjsua2JNI.PresNotifyParam_srvPres_set(this.swigCPtr, this, SWIGTYPE_p_void.getCPtr(sWIGTYPE_p_void));
    }

    public void setState(pjsip_evsub_state pjsip_evsub_stateVar) {
        pjsua2JNI.PresNotifyParam_state_set(this.swigCPtr, this, pjsip_evsub_stateVar.swigValue());
    }

    public void setStateStr(String str) {
        pjsua2JNI.PresNotifyParam_stateStr_set(this.swigCPtr, this, str);
    }

    public void setTxOption(SipTxOption sipTxOption) {
        pjsua2JNI.PresNotifyParam_txOption_set(this.swigCPtr, this, SipTxOption.getCPtr(sipTxOption), sipTxOption);
    }

    public void setWithBody(boolean z) {
        pjsua2JNI.PresNotifyParam_withBody_set(this.swigCPtr, this, z);
    }

    public PresNotifyParam() {
        this(pjsua2JNI.new_PresNotifyParam(), true);
    }
}
