package org.pjsip.pjsua2;
/* loaded from: classes5.dex */
public class OnTransportStateParam {
    protected transient boolean swigCMemOwn;
    private transient long swigCPtr;

    /* JADX INFO: Access modifiers changed from: protected */
    public OnTransportStateParam(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static long getCPtr(OnTransportStateParam onTransportStateParam) {
        if (onTransportStateParam == null) {
            return 0L;
        }
        return onTransportStateParam.swigCPtr;
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                pjsua2JNI.delete_OnTransportStateParam(this.swigCPtr);
            }
            this.swigCPtr = 0L;
        }
    }

    protected void finalize() {
        delete();
    }

    public SWIGTYPE_p_void getHnd() {
        long OnTransportStateParam_hnd_get = pjsua2JNI.OnTransportStateParam_hnd_get(this.swigCPtr, this);
        if (OnTransportStateParam_hnd_get == 0) {
            return null;
        }
        return new SWIGTYPE_p_void(OnTransportStateParam_hnd_get, false);
    }

    public int getLastError() {
        return pjsua2JNI.OnTransportStateParam_lastError_get(this.swigCPtr, this);
    }

    public pjsip_transport_state getState() {
        return pjsip_transport_state.swigToEnum(pjsua2JNI.OnTransportStateParam_state_get(this.swigCPtr, this));
    }

    public void setHnd(SWIGTYPE_p_void sWIGTYPE_p_void) {
        pjsua2JNI.OnTransportStateParam_hnd_set(this.swigCPtr, this, SWIGTYPE_p_void.getCPtr(sWIGTYPE_p_void));
    }

    public void setLastError(int i) {
        pjsua2JNI.OnTransportStateParam_lastError_set(this.swigCPtr, this, i);
    }

    public void setState(pjsip_transport_state pjsip_transport_stateVar) {
        pjsua2JNI.OnTransportStateParam_state_set(this.swigCPtr, this, pjsip_transport_stateVar.swigValue());
    }

    public OnTransportStateParam() {
        this(pjsua2JNI.new_OnTransportStateParam(), true);
    }
}
