package org.pjsip.pjsua2;
/* loaded from: classes5.dex */
public class CodecFmtpVector {
    protected transient boolean swigCMemOwn;
    private transient long swigCPtr;

    /* JADX INFO: Access modifiers changed from: protected */
    public CodecFmtpVector(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static long getCPtr(CodecFmtpVector codecFmtpVector) {
        if (codecFmtpVector == null) {
            return 0L;
        }
        return codecFmtpVector.swigCPtr;
    }

    public void add(CodecFmtp codecFmtp) {
        pjsua2JNI.CodecFmtpVector_add(this.swigCPtr, this, CodecFmtp.getCPtr(codecFmtp), codecFmtp);
    }

    public long capacity() {
        return pjsua2JNI.CodecFmtpVector_capacity(this.swigCPtr, this);
    }

    public void clear() {
        pjsua2JNI.CodecFmtpVector_clear(this.swigCPtr, this);
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                pjsua2JNI.delete_CodecFmtpVector(this.swigCPtr);
            }
            this.swigCPtr = 0L;
        }
    }

    protected void finalize() {
        delete();
    }

    public CodecFmtp get(int i) {
        return new CodecFmtp(pjsua2JNI.CodecFmtpVector_get(this.swigCPtr, this, i), false);
    }

    public boolean isEmpty() {
        return pjsua2JNI.CodecFmtpVector_isEmpty(this.swigCPtr, this);
    }

    public void reserve(long j) {
        pjsua2JNI.CodecFmtpVector_reserve(this.swigCPtr, this, j);
    }

    public void set(int i, CodecFmtp codecFmtp) {
        pjsua2JNI.CodecFmtpVector_set(this.swigCPtr, this, i, CodecFmtp.getCPtr(codecFmtp), codecFmtp);
    }

    public long size() {
        return pjsua2JNI.CodecFmtpVector_size(this.swigCPtr, this);
    }

    public CodecFmtpVector() {
        this(pjsua2JNI.new_CodecFmtpVector__SWIG_0(), true);
    }

    public CodecFmtpVector(long j) {
        this(pjsua2JNI.new_CodecFmtpVector__SWIG_1(j), true);
    }
}
