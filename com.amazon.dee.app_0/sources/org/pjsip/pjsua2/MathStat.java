package org.pjsip.pjsua2;
/* loaded from: classes5.dex */
public class MathStat {
    protected transient boolean swigCMemOwn;
    private transient long swigCPtr;

    /* JADX INFO: Access modifiers changed from: protected */
    public MathStat(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static long getCPtr(MathStat mathStat) {
        if (mathStat == null) {
            return 0L;
        }
        return mathStat.swigCPtr;
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                pjsua2JNI.delete_MathStat(this.swigCPtr);
            }
            this.swigCPtr = 0L;
        }
    }

    protected void finalize() {
        delete();
    }

    public int getLast() {
        return pjsua2JNI.MathStat_last_get(this.swigCPtr, this);
    }

    public int getMax() {
        return pjsua2JNI.MathStat_max_get(this.swigCPtr, this);
    }

    public int getMean() {
        return pjsua2JNI.MathStat_mean_get(this.swigCPtr, this);
    }

    public int getMin() {
        return pjsua2JNI.MathStat_min_get(this.swigCPtr, this);
    }

    public int getN() {
        return pjsua2JNI.MathStat_n_get(this.swigCPtr, this);
    }

    public void setLast(int i) {
        pjsua2JNI.MathStat_last_set(this.swigCPtr, this, i);
    }

    public void setMax(int i) {
        pjsua2JNI.MathStat_max_set(this.swigCPtr, this, i);
    }

    public void setMean(int i) {
        pjsua2JNI.MathStat_mean_set(this.swigCPtr, this, i);
    }

    public void setMin(int i) {
        pjsua2JNI.MathStat_min_set(this.swigCPtr, this, i);
    }

    public void setN(int i) {
        pjsua2JNI.MathStat_n_set(this.swigCPtr, this, i);
    }

    public MathStat() {
        this(pjsua2JNI.new_MathStat(), true);
    }
}
