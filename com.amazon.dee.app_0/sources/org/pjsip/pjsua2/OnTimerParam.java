package org.pjsip.pjsua2;
/* loaded from: classes5.dex */
public class OnTimerParam {
    protected transient boolean swigCMemOwn;
    private transient long swigCPtr;

    /* JADX INFO: Access modifiers changed from: protected */
    public OnTimerParam(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static long getCPtr(OnTimerParam onTimerParam) {
        if (onTimerParam == null) {
            return 0L;
        }
        return onTimerParam.swigCPtr;
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                pjsua2JNI.delete_OnTimerParam(this.swigCPtr);
            }
            this.swigCPtr = 0L;
        }
    }

    protected void finalize() {
        delete();
    }

    public long getMsecDelay() {
        return pjsua2JNI.OnTimerParam_msecDelay_get(this.swigCPtr, this);
    }

    public SWIGTYPE_p_void getUserData() {
        long OnTimerParam_userData_get = pjsua2JNI.OnTimerParam_userData_get(this.swigCPtr, this);
        if (OnTimerParam_userData_get == 0) {
            return null;
        }
        return new SWIGTYPE_p_void(OnTimerParam_userData_get, false);
    }

    public void setMsecDelay(long j) {
        pjsua2JNI.OnTimerParam_msecDelay_set(this.swigCPtr, this, j);
    }

    public void setUserData(SWIGTYPE_p_void sWIGTYPE_p_void) {
        pjsua2JNI.OnTimerParam_userData_set(this.swigCPtr, this, SWIGTYPE_p_void.getCPtr(sWIGTYPE_p_void));
    }

    public OnTimerParam() {
        this(pjsua2JNI.new_OnTimerParam(), true);
    }
}
