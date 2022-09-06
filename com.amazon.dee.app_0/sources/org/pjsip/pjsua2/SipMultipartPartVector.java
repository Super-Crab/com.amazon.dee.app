package org.pjsip.pjsua2;
/* loaded from: classes5.dex */
public class SipMultipartPartVector {
    protected transient boolean swigCMemOwn;
    private transient long swigCPtr;

    /* JADX INFO: Access modifiers changed from: protected */
    public SipMultipartPartVector(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static long getCPtr(SipMultipartPartVector sipMultipartPartVector) {
        if (sipMultipartPartVector == null) {
            return 0L;
        }
        return sipMultipartPartVector.swigCPtr;
    }

    public void add(SipMultipartPart sipMultipartPart) {
        pjsua2JNI.SipMultipartPartVector_add(this.swigCPtr, this, SipMultipartPart.getCPtr(sipMultipartPart), sipMultipartPart);
    }

    public long capacity() {
        return pjsua2JNI.SipMultipartPartVector_capacity(this.swigCPtr, this);
    }

    public void clear() {
        pjsua2JNI.SipMultipartPartVector_clear(this.swigCPtr, this);
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                pjsua2JNI.delete_SipMultipartPartVector(this.swigCPtr);
            }
            this.swigCPtr = 0L;
        }
    }

    protected void finalize() {
        delete();
    }

    public SipMultipartPart get(int i) {
        return new SipMultipartPart(pjsua2JNI.SipMultipartPartVector_get(this.swigCPtr, this, i), false);
    }

    public boolean isEmpty() {
        return pjsua2JNI.SipMultipartPartVector_isEmpty(this.swigCPtr, this);
    }

    public void reserve(long j) {
        pjsua2JNI.SipMultipartPartVector_reserve(this.swigCPtr, this, j);
    }

    public void set(int i, SipMultipartPart sipMultipartPart) {
        pjsua2JNI.SipMultipartPartVector_set(this.swigCPtr, this, i, SipMultipartPart.getCPtr(sipMultipartPart), sipMultipartPart);
    }

    public long size() {
        return pjsua2JNI.SipMultipartPartVector_size(this.swigCPtr, this);
    }

    public SipMultipartPartVector() {
        this(pjsua2JNI.new_SipMultipartPartVector__SWIG_0(), true);
    }

    public SipMultipartPartVector(long j) {
        this(pjsua2JNI.new_SipMultipartPartVector__SWIG_1(j), true);
    }
}
