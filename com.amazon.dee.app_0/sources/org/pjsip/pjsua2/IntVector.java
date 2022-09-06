package org.pjsip.pjsua2;
/* loaded from: classes5.dex */
public class IntVector {
    protected transient boolean swigCMemOwn;
    private transient long swigCPtr;

    /* JADX INFO: Access modifiers changed from: protected */
    public IntVector(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static long getCPtr(IntVector intVector) {
        if (intVector == null) {
            return 0L;
        }
        return intVector.swigCPtr;
    }

    public void add(int i) {
        pjsua2JNI.IntVector_add(this.swigCPtr, this, i);
    }

    public long capacity() {
        return pjsua2JNI.IntVector_capacity(this.swigCPtr, this);
    }

    public void clear() {
        pjsua2JNI.IntVector_clear(this.swigCPtr, this);
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                pjsua2JNI.delete_IntVector(this.swigCPtr);
            }
            this.swigCPtr = 0L;
        }
    }

    protected void finalize() {
        delete();
    }

    public int get(int i) {
        return pjsua2JNI.IntVector_get(this.swigCPtr, this, i);
    }

    public boolean isEmpty() {
        return pjsua2JNI.IntVector_isEmpty(this.swigCPtr, this);
    }

    public void reserve(long j) {
        pjsua2JNI.IntVector_reserve(this.swigCPtr, this, j);
    }

    public void set(int i, int i2) {
        pjsua2JNI.IntVector_set(this.swigCPtr, this, i, i2);
    }

    public long size() {
        return pjsua2JNI.IntVector_size(this.swigCPtr, this);
    }

    public IntVector() {
        this(pjsua2JNI.new_IntVector__SWIG_0(), true);
    }

    public IntVector(long j) {
        this(pjsua2JNI.new_IntVector__SWIG_1(j), true);
    }
}
