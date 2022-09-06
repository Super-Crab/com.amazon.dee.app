package org.pjsip.pjsua2;
/* loaded from: classes5.dex */
public class TimeVal {
    protected transient boolean swigCMemOwn;
    private transient long swigCPtr;

    /* JADX INFO: Access modifiers changed from: protected */
    public TimeVal(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static long getCPtr(TimeVal timeVal) {
        if (timeVal == null) {
            return 0L;
        }
        return timeVal.swigCPtr;
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                pjsua2JNI.delete_TimeVal(this.swigCPtr);
            }
            this.swigCPtr = 0L;
        }
    }

    protected void finalize() {
        delete();
    }

    public int getMsec() {
        return pjsua2JNI.TimeVal_msec_get(this.swigCPtr, this);
    }

    public int getSec() {
        return pjsua2JNI.TimeVal_sec_get(this.swigCPtr, this);
    }

    public void setMsec(int i) {
        pjsua2JNI.TimeVal_msec_set(this.swigCPtr, this, i);
    }

    public void setSec(int i) {
        pjsua2JNI.TimeVal_sec_set(this.swigCPtr, this, i);
    }

    public TimeVal() {
        this(pjsua2JNI.new_TimeVal(), true);
    }
}
