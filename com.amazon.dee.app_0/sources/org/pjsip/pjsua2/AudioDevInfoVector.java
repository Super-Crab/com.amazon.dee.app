package org.pjsip.pjsua2;
/* loaded from: classes5.dex */
public class AudioDevInfoVector {
    protected transient boolean swigCMemOwn;
    private transient long swigCPtr;

    /* JADX INFO: Access modifiers changed from: protected */
    public AudioDevInfoVector(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    protected static long getCPtr(AudioDevInfoVector audioDevInfoVector) {
        if (audioDevInfoVector == null) {
            return 0L;
        }
        return audioDevInfoVector.swigCPtr;
    }

    public void add(AudioDevInfo audioDevInfo) {
        pjsua2JNI.AudioDevInfoVector_add(this.swigCPtr, this, AudioDevInfo.getCPtr(audioDevInfo), audioDevInfo);
    }

    public long capacity() {
        return pjsua2JNI.AudioDevInfoVector_capacity(this.swigCPtr, this);
    }

    public void clear() {
        pjsua2JNI.AudioDevInfoVector_clear(this.swigCPtr, this);
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                pjsua2JNI.delete_AudioDevInfoVector(this.swigCPtr);
            }
            this.swigCPtr = 0L;
        }
    }

    protected void finalize() {
        delete();
    }

    public AudioDevInfo get(int i) {
        long AudioDevInfoVector_get = pjsua2JNI.AudioDevInfoVector_get(this.swigCPtr, this, i);
        if (AudioDevInfoVector_get == 0) {
            return null;
        }
        return new AudioDevInfo(AudioDevInfoVector_get, false);
    }

    public boolean isEmpty() {
        return pjsua2JNI.AudioDevInfoVector_isEmpty(this.swigCPtr, this);
    }

    public void reserve(long j) {
        pjsua2JNI.AudioDevInfoVector_reserve(this.swigCPtr, this, j);
    }

    public void set(int i, AudioDevInfo audioDevInfo) {
        pjsua2JNI.AudioDevInfoVector_set(this.swigCPtr, this, i, AudioDevInfo.getCPtr(audioDevInfo), audioDevInfo);
    }

    public long size() {
        return pjsua2JNI.AudioDevInfoVector_size(this.swigCPtr, this);
    }

    public AudioDevInfoVector() {
        this(pjsua2JNI.new_AudioDevInfoVector__SWIG_0(), true);
    }

    public AudioDevInfoVector(long j) {
        this(pjsua2JNI.new_AudioDevInfoVector__SWIG_1(j), true);
    }
}
