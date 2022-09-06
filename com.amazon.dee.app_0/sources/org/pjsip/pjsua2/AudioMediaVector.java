package org.pjsip.pjsua2;
/* loaded from: classes5.dex */
public class AudioMediaVector {
    protected transient boolean swigCMemOwn;
    private transient long swigCPtr;

    /* JADX INFO: Access modifiers changed from: protected */
    public AudioMediaVector(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    protected static long getCPtr(AudioMediaVector audioMediaVector) {
        if (audioMediaVector == null) {
            return 0L;
        }
        return audioMediaVector.swigCPtr;
    }

    public void add(AudioMedia audioMedia) {
        pjsua2JNI.AudioMediaVector_add(this.swigCPtr, this, AudioMedia.getCPtr(audioMedia), audioMedia);
    }

    public long capacity() {
        return pjsua2JNI.AudioMediaVector_capacity(this.swigCPtr, this);
    }

    public void clear() {
        pjsua2JNI.AudioMediaVector_clear(this.swigCPtr, this);
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                pjsua2JNI.delete_AudioMediaVector(this.swigCPtr);
            }
            this.swigCPtr = 0L;
        }
    }

    protected void finalize() {
        delete();
    }

    public AudioMedia get(int i) {
        long AudioMediaVector_get = pjsua2JNI.AudioMediaVector_get(this.swigCPtr, this, i);
        if (AudioMediaVector_get == 0) {
            return null;
        }
        return new AudioMedia(AudioMediaVector_get, false);
    }

    public boolean isEmpty() {
        return pjsua2JNI.AudioMediaVector_isEmpty(this.swigCPtr, this);
    }

    public void reserve(long j) {
        pjsua2JNI.AudioMediaVector_reserve(this.swigCPtr, this, j);
    }

    public void set(int i, AudioMedia audioMedia) {
        pjsua2JNI.AudioMediaVector_set(this.swigCPtr, this, i, AudioMedia.getCPtr(audioMedia), audioMedia);
    }

    public long size() {
        return pjsua2JNI.AudioMediaVector_size(this.swigCPtr, this);
    }

    public AudioMediaVector() {
        this(pjsua2JNI.new_AudioMediaVector__SWIG_0(), true);
    }

    public AudioMediaVector(long j) {
        this(pjsua2JNI.new_AudioMediaVector__SWIG_1(j), true);
    }
}
