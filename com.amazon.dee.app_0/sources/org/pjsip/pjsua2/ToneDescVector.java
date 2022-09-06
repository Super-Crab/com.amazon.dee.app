package org.pjsip.pjsua2;
/* loaded from: classes5.dex */
public class ToneDescVector {
    protected transient boolean swigCMemOwn;
    private transient long swigCPtr;

    protected ToneDescVector(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static long getCPtr(ToneDescVector toneDescVector) {
        if (toneDescVector == null) {
            return 0L;
        }
        return toneDescVector.swigCPtr;
    }

    public void add(ToneDesc toneDesc) {
        pjsua2JNI.ToneDescVector_add(this.swigCPtr, this, ToneDesc.getCPtr(toneDesc), toneDesc);
    }

    public long capacity() {
        return pjsua2JNI.ToneDescVector_capacity(this.swigCPtr, this);
    }

    public void clear() {
        pjsua2JNI.ToneDescVector_clear(this.swigCPtr, this);
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                pjsua2JNI.delete_ToneDescVector(this.swigCPtr);
            }
            this.swigCPtr = 0L;
        }
    }

    protected void finalize() {
        delete();
    }

    public ToneDesc get(int i) {
        return new ToneDesc(pjsua2JNI.ToneDescVector_get(this.swigCPtr, this, i), false);
    }

    public boolean isEmpty() {
        return pjsua2JNI.ToneDescVector_isEmpty(this.swigCPtr, this);
    }

    public void reserve(long j) {
        pjsua2JNI.ToneDescVector_reserve(this.swigCPtr, this, j);
    }

    public void set(int i, ToneDesc toneDesc) {
        pjsua2JNI.ToneDescVector_set(this.swigCPtr, this, i, ToneDesc.getCPtr(toneDesc), toneDesc);
    }

    public long size() {
        return pjsua2JNI.ToneDescVector_size(this.swigCPtr, this);
    }

    public ToneDescVector() {
        this(pjsua2JNI.new_ToneDescVector__SWIG_0(), true);
    }

    public ToneDescVector(long j) {
        this(pjsua2JNI.new_ToneDescVector__SWIG_1(j), true);
    }
}
