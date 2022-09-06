package org.pjsip.pjsua2;
/* loaded from: classes5.dex */
public class VideoDevInfoVector {
    protected transient boolean swigCMemOwn;
    private transient long swigCPtr;

    /* JADX INFO: Access modifiers changed from: protected */
    public VideoDevInfoVector(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    protected static long getCPtr(VideoDevInfoVector videoDevInfoVector) {
        if (videoDevInfoVector == null) {
            return 0L;
        }
        return videoDevInfoVector.swigCPtr;
    }

    public void add(VideoDevInfo videoDevInfo) {
        pjsua2JNI.VideoDevInfoVector_add(this.swigCPtr, this, VideoDevInfo.getCPtr(videoDevInfo), videoDevInfo);
    }

    public long capacity() {
        return pjsua2JNI.VideoDevInfoVector_capacity(this.swigCPtr, this);
    }

    public void clear() {
        pjsua2JNI.VideoDevInfoVector_clear(this.swigCPtr, this);
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                pjsua2JNI.delete_VideoDevInfoVector(this.swigCPtr);
            }
            this.swigCPtr = 0L;
        }
    }

    protected void finalize() {
        delete();
    }

    public VideoDevInfo get(int i) {
        long VideoDevInfoVector_get = pjsua2JNI.VideoDevInfoVector_get(this.swigCPtr, this, i);
        if (VideoDevInfoVector_get == 0) {
            return null;
        }
        return new VideoDevInfo(VideoDevInfoVector_get, false);
    }

    public boolean isEmpty() {
        return pjsua2JNI.VideoDevInfoVector_isEmpty(this.swigCPtr, this);
    }

    public void reserve(long j) {
        pjsua2JNI.VideoDevInfoVector_reserve(this.swigCPtr, this, j);
    }

    public void set(int i, VideoDevInfo videoDevInfo) {
        pjsua2JNI.VideoDevInfoVector_set(this.swigCPtr, this, i, VideoDevInfo.getCPtr(videoDevInfo), videoDevInfo);
    }

    public long size() {
        return pjsua2JNI.VideoDevInfoVector_size(this.swigCPtr, this);
    }

    public VideoDevInfoVector() {
        this(pjsua2JNI.new_VideoDevInfoVector__SWIG_0(), true);
    }

    public VideoDevInfoVector(long j) {
        this(pjsua2JNI.new_VideoDevInfoVector__SWIG_1(j), true);
    }
}
