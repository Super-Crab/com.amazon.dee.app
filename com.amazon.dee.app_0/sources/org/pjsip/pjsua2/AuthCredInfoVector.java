package org.pjsip.pjsua2;
/* loaded from: classes5.dex */
public class AuthCredInfoVector {
    protected transient boolean swigCMemOwn;
    private transient long swigCPtr;

    /* JADX INFO: Access modifiers changed from: protected */
    public AuthCredInfoVector(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static long getCPtr(AuthCredInfoVector authCredInfoVector) {
        if (authCredInfoVector == null) {
            return 0L;
        }
        return authCredInfoVector.swigCPtr;
    }

    public void add(AuthCredInfo authCredInfo) {
        pjsua2JNI.AuthCredInfoVector_add(this.swigCPtr, this, AuthCredInfo.getCPtr(authCredInfo), authCredInfo);
    }

    public long capacity() {
        return pjsua2JNI.AuthCredInfoVector_capacity(this.swigCPtr, this);
    }

    public void clear() {
        pjsua2JNI.AuthCredInfoVector_clear(this.swigCPtr, this);
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                pjsua2JNI.delete_AuthCredInfoVector(this.swigCPtr);
            }
            this.swigCPtr = 0L;
        }
    }

    protected void finalize() {
        delete();
    }

    public AuthCredInfo get(int i) {
        return new AuthCredInfo(pjsua2JNI.AuthCredInfoVector_get(this.swigCPtr, this, i), false);
    }

    public boolean isEmpty() {
        return pjsua2JNI.AuthCredInfoVector_isEmpty(this.swigCPtr, this);
    }

    public void reserve(long j) {
        pjsua2JNI.AuthCredInfoVector_reserve(this.swigCPtr, this, j);
    }

    public void set(int i, AuthCredInfo authCredInfo) {
        pjsua2JNI.AuthCredInfoVector_set(this.swigCPtr, this, i, AuthCredInfo.getCPtr(authCredInfo), authCredInfo);
    }

    public long size() {
        return pjsua2JNI.AuthCredInfoVector_size(this.swigCPtr, this);
    }

    public AuthCredInfoVector() {
        this(pjsua2JNI.new_AuthCredInfoVector__SWIG_0(), true);
    }

    public AuthCredInfoVector(long j) {
        this(pjsua2JNI.new_AuthCredInfoVector__SWIG_1(j), true);
    }
}
