package org.pjsip.pjsua2;
/* loaded from: classes5.dex */
public class OnCallRxOfferParam {
    protected transient boolean swigCMemOwn;
    private transient long swigCPtr;

    /* JADX INFO: Access modifiers changed from: protected */
    public OnCallRxOfferParam(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static long getCPtr(OnCallRxOfferParam onCallRxOfferParam) {
        if (onCallRxOfferParam == null) {
            return 0L;
        }
        return onCallRxOfferParam.swigCPtr;
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                pjsua2JNI.delete_OnCallRxOfferParam(this.swigCPtr);
            }
            this.swigCPtr = 0L;
        }
    }

    protected void finalize() {
        delete();
    }

    public SdpSession getOffer() {
        long OnCallRxOfferParam_offer_get = pjsua2JNI.OnCallRxOfferParam_offer_get(this.swigCPtr, this);
        if (OnCallRxOfferParam_offer_get == 0) {
            return null;
        }
        return new SdpSession(OnCallRxOfferParam_offer_get, false);
    }

    public CallSetting getOpt() {
        long OnCallRxOfferParam_opt_get = pjsua2JNI.OnCallRxOfferParam_opt_get(this.swigCPtr, this);
        if (OnCallRxOfferParam_opt_get == 0) {
            return null;
        }
        return new CallSetting(OnCallRxOfferParam_opt_get, false);
    }

    public pjsip_status_code getStatusCode() {
        return pjsip_status_code.swigToEnum(pjsua2JNI.OnCallRxOfferParam_statusCode_get(this.swigCPtr, this));
    }

    public void setOffer(SdpSession sdpSession) {
        pjsua2JNI.OnCallRxOfferParam_offer_set(this.swigCPtr, this, SdpSession.getCPtr(sdpSession), sdpSession);
    }

    public void setOpt(CallSetting callSetting) {
        pjsua2JNI.OnCallRxOfferParam_opt_set(this.swigCPtr, this, CallSetting.getCPtr(callSetting), callSetting);
    }

    public void setStatusCode(pjsip_status_code pjsip_status_codeVar) {
        pjsua2JNI.OnCallRxOfferParam_statusCode_set(this.swigCPtr, this, pjsip_status_codeVar.swigValue());
    }

    public OnCallRxOfferParam() {
        this(pjsua2JNI.new_OnCallRxOfferParam(), true);
    }
}
