package org.pjsip.pjsua2;
/* loaded from: classes5.dex */
public class MediaFormatVector {
    protected transient boolean swigCMemOwn;
    private transient long swigCPtr;

    /* JADX INFO: Access modifiers changed from: protected */
    public MediaFormatVector(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static long getCPtr(MediaFormatVector mediaFormatVector) {
        if (mediaFormatVector == null) {
            return 0L;
        }
        return mediaFormatVector.swigCPtr;
    }

    public void add(MediaFormat mediaFormat) {
        pjsua2JNI.MediaFormatVector_add(this.swigCPtr, this, MediaFormat.getCPtr(mediaFormat), mediaFormat);
    }

    public long capacity() {
        return pjsua2JNI.MediaFormatVector_capacity(this.swigCPtr, this);
    }

    public void clear() {
        pjsua2JNI.MediaFormatVector_clear(this.swigCPtr, this);
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                pjsua2JNI.delete_MediaFormatVector(this.swigCPtr);
            }
            this.swigCPtr = 0L;
        }
    }

    protected void finalize() {
        delete();
    }

    public MediaFormat get(int i) {
        long MediaFormatVector_get = pjsua2JNI.MediaFormatVector_get(this.swigCPtr, this, i);
        if (MediaFormatVector_get == 0) {
            return null;
        }
        return new MediaFormat(MediaFormatVector_get, false);
    }

    public boolean isEmpty() {
        return pjsua2JNI.MediaFormatVector_isEmpty(this.swigCPtr, this);
    }

    public void reserve(long j) {
        pjsua2JNI.MediaFormatVector_reserve(this.swigCPtr, this, j);
    }

    public void set(int i, MediaFormat mediaFormat) {
        pjsua2JNI.MediaFormatVector_set(this.swigCPtr, this, i, MediaFormat.getCPtr(mediaFormat), mediaFormat);
    }

    public long size() {
        return pjsua2JNI.MediaFormatVector_size(this.swigCPtr, this);
    }

    public MediaFormatVector() {
        this(pjsua2JNI.new_MediaFormatVector__SWIG_0(), true);
    }

    public MediaFormatVector(long j) {
        this(pjsua2JNI.new_MediaFormatVector__SWIG_1(j), true);
    }
}
