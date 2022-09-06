package org.pjsip.pjsua2;
/* loaded from: classes5.dex */
public class LossType {
    protected transient boolean swigCMemOwn;
    private transient long swigCPtr;

    /* JADX INFO: Access modifiers changed from: protected */
    public LossType(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static long getCPtr(LossType lossType) {
        if (lossType == null) {
            return 0L;
        }
        return lossType.swigCPtr;
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                pjsua2JNI.delete_LossType(this.swigCPtr);
            }
            this.swigCPtr = 0L;
        }
    }

    protected void finalize() {
        delete();
    }

    public long getBurst() {
        return pjsua2JNI.LossType_burst_get(this.swigCPtr, this);
    }

    public long getRandom() {
        return pjsua2JNI.LossType_random_get(this.swigCPtr, this);
    }

    public void setBurst(long j) {
        pjsua2JNI.LossType_burst_set(this.swigCPtr, this, j);
    }

    public void setRandom(long j) {
        pjsua2JNI.LossType_random_set(this.swigCPtr, this, j);
    }

    public LossType() {
        this(pjsua2JNI.new_LossType(), true);
    }
}
