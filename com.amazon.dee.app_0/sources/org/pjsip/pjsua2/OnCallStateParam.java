package org.pjsip.pjsua2;
/* loaded from: classes5.dex */
public class OnCallStateParam {
    protected transient boolean swigCMemOwn;
    private transient long swigCPtr;

    /* JADX INFO: Access modifiers changed from: protected */
    public OnCallStateParam(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static long getCPtr(OnCallStateParam onCallStateParam) {
        if (onCallStateParam == null) {
            return 0L;
        }
        return onCallStateParam.swigCPtr;
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                pjsua2JNI.delete_OnCallStateParam(this.swigCPtr);
            }
            this.swigCPtr = 0L;
        }
    }

    protected void finalize() {
        delete();
    }

    public SipEvent getE() {
        long OnCallStateParam_e_get = pjsua2JNI.OnCallStateParam_e_get(this.swigCPtr, this);
        if (OnCallStateParam_e_get == 0) {
            return null;
        }
        return new SipEvent(OnCallStateParam_e_get, false);
    }

    public void setE(SipEvent sipEvent) {
        pjsua2JNI.OnCallStateParam_e_set(this.swigCPtr, this, SipEvent.getCPtr(sipEvent), sipEvent);
    }

    public OnCallStateParam() {
        this(pjsua2JNI.new_OnCallStateParam(), true);
    }
}
