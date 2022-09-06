package org.pjsip.pjsua2;
/* loaded from: classes5.dex */
public class ToneDigitVector {
    protected transient boolean swigCMemOwn;
    private transient long swigCPtr;

    protected ToneDigitVector(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static long getCPtr(ToneDigitVector toneDigitVector) {
        if (toneDigitVector == null) {
            return 0L;
        }
        return toneDigitVector.swigCPtr;
    }

    public void add(ToneDigit toneDigit) {
        pjsua2JNI.ToneDigitVector_add(this.swigCPtr, this, ToneDigit.getCPtr(toneDigit), toneDigit);
    }

    public long capacity() {
        return pjsua2JNI.ToneDigitVector_capacity(this.swigCPtr, this);
    }

    public void clear() {
        pjsua2JNI.ToneDigitVector_clear(this.swigCPtr, this);
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                pjsua2JNI.delete_ToneDigitVector(this.swigCPtr);
            }
            this.swigCPtr = 0L;
        }
    }

    protected void finalize() {
        delete();
    }

    public ToneDigit get(int i) {
        return new ToneDigit(pjsua2JNI.ToneDigitVector_get(this.swigCPtr, this, i), false);
    }

    public boolean isEmpty() {
        return pjsua2JNI.ToneDigitVector_isEmpty(this.swigCPtr, this);
    }

    public void reserve(long j) {
        pjsua2JNI.ToneDigitVector_reserve(this.swigCPtr, this, j);
    }

    public void set(int i, ToneDigit toneDigit) {
        pjsua2JNI.ToneDigitVector_set(this.swigCPtr, this, i, ToneDigit.getCPtr(toneDigit), toneDigit);
    }

    public long size() {
        return pjsua2JNI.ToneDigitVector_size(this.swigCPtr, this);
    }

    public ToneDigitVector() {
        this(pjsua2JNI.new_ToneDigitVector__SWIG_0(), true);
    }

    public ToneDigitVector(long j) {
        this(pjsua2JNI.new_ToneDigitVector__SWIG_1(j), true);
    }
}
