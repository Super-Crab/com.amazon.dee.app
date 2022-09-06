package org.pjsip.pjsua2;
/* loaded from: classes5.dex */
public class MediaFormatVideo extends MediaFormat {
    private transient long swigCPtr;

    /* JADX INFO: Access modifiers changed from: protected */
    public MediaFormatVideo(long j, boolean z) {
        super(pjsua2JNI.MediaFormatVideo_SWIGUpcast(j), z);
        this.swigCPtr = j;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static long getCPtr(MediaFormatVideo mediaFormatVideo) {
        if (mediaFormatVideo == null) {
            return 0L;
        }
        return mediaFormatVideo.swigCPtr;
    }

    @Override // org.pjsip.pjsua2.MediaFormat
    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                pjsua2JNI.delete_MediaFormatVideo(this.swigCPtr);
            }
            this.swigCPtr = 0L;
        }
        super.delete();
    }

    @Override // org.pjsip.pjsua2.MediaFormat
    protected void finalize() {
        delete();
    }

    public long getAvgBps() {
        return pjsua2JNI.MediaFormatVideo_avgBps_get(this.swigCPtr, this);
    }

    public int getFpsDenum() {
        return pjsua2JNI.MediaFormatVideo_fpsDenum_get(this.swigCPtr, this);
    }

    public int getFpsNum() {
        return pjsua2JNI.MediaFormatVideo_fpsNum_get(this.swigCPtr, this);
    }

    public long getHeight() {
        return pjsua2JNI.MediaFormatVideo_height_get(this.swigCPtr, this);
    }

    public long getMaxBps() {
        return pjsua2JNI.MediaFormatVideo_maxBps_get(this.swigCPtr, this);
    }

    public long getWidth() {
        return pjsua2JNI.MediaFormatVideo_width_get(this.swigCPtr, this);
    }

    public void setAvgBps(long j) {
        pjsua2JNI.MediaFormatVideo_avgBps_set(this.swigCPtr, this, j);
    }

    public void setFpsDenum(int i) {
        pjsua2JNI.MediaFormatVideo_fpsDenum_set(this.swigCPtr, this, i);
    }

    public void setFpsNum(int i) {
        pjsua2JNI.MediaFormatVideo_fpsNum_set(this.swigCPtr, this, i);
    }

    public void setHeight(long j) {
        pjsua2JNI.MediaFormatVideo_height_set(this.swigCPtr, this, j);
    }

    public void setMaxBps(long j) {
        pjsua2JNI.MediaFormatVideo_maxBps_set(this.swigCPtr, this, j);
    }

    public void setWidth(long j) {
        pjsua2JNI.MediaFormatVideo_width_set(this.swigCPtr, this, j);
    }

    public MediaFormatVideo() {
        this(pjsua2JNI.new_MediaFormatVideo(), true);
    }
}
