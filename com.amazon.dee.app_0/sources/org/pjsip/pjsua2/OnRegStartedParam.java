package org.pjsip.pjsua2;
/* loaded from: classes5.dex */
public class OnRegStartedParam {
    protected transient boolean swigCMemOwn;
    private transient long swigCPtr;

    /* JADX INFO: Access modifiers changed from: protected */
    public OnRegStartedParam(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static long getCPtr(OnRegStartedParam onRegStartedParam) {
        if (onRegStartedParam == null) {
            return 0L;
        }
        return onRegStartedParam.swigCPtr;
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                pjsua2JNI.delete_OnRegStartedParam(this.swigCPtr);
            }
            this.swigCPtr = 0L;
        }
    }

    protected void finalize() {
        delete();
    }

    public boolean getRenew() {
        return pjsua2JNI.OnRegStartedParam_renew_get(this.swigCPtr, this);
    }

    public void setRenew(boolean z) {
        pjsua2JNI.OnRegStartedParam_renew_set(this.swigCPtr, this, z);
    }

    public OnRegStartedParam() {
        this(pjsua2JNI.new_OnRegStartedParam(), true);
    }
}
