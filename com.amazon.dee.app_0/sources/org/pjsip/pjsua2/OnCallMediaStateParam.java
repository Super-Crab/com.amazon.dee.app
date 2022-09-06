package org.pjsip.pjsua2;
/* loaded from: classes5.dex */
public class OnCallMediaStateParam {
    protected transient boolean swigCMemOwn;
    private transient long swigCPtr;

    /* JADX INFO: Access modifiers changed from: protected */
    public OnCallMediaStateParam(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static long getCPtr(OnCallMediaStateParam onCallMediaStateParam) {
        if (onCallMediaStateParam == null) {
            return 0L;
        }
        return onCallMediaStateParam.swigCPtr;
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                pjsua2JNI.delete_OnCallMediaStateParam(this.swigCPtr);
            }
            this.swigCPtr = 0L;
        }
    }

    protected void finalize() {
        delete();
    }

    public OnCallMediaStateParam() {
        this(pjsua2JNI.new_OnCallMediaStateParam(), true);
    }
}
