package org.pjsip.pjsua2;
/* loaded from: classes5.dex */
public class OnCallReplacedParam {
    protected transient boolean swigCMemOwn;
    private transient long swigCPtr;

    /* JADX INFO: Access modifiers changed from: protected */
    public OnCallReplacedParam(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static long getCPtr(OnCallReplacedParam onCallReplacedParam) {
        if (onCallReplacedParam == null) {
            return 0L;
        }
        return onCallReplacedParam.swigCPtr;
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                pjsua2JNI.delete_OnCallReplacedParam(this.swigCPtr);
            }
            this.swigCPtr = 0L;
        }
    }

    protected void finalize() {
        delete();
    }

    public int getNewCallId() {
        return pjsua2JNI.OnCallReplacedParam_newCallId_get(this.swigCPtr, this);
    }

    public void setNewCallId(int i) {
        pjsua2JNI.OnCallReplacedParam_newCallId_set(this.swigCPtr, this, i);
    }

    public OnCallReplacedParam() {
        this(pjsua2JNI.new_OnCallReplacedParam(), true);
    }
}
