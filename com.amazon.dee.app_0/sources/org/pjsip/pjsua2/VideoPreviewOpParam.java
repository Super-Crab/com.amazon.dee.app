package org.pjsip.pjsua2;
/* loaded from: classes5.dex */
public class VideoPreviewOpParam {
    protected transient boolean swigCMemOwn;
    private transient long swigCPtr;

    protected VideoPreviewOpParam(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static long getCPtr(VideoPreviewOpParam videoPreviewOpParam) {
        if (videoPreviewOpParam == null) {
            return 0L;
        }
        return videoPreviewOpParam.swigCPtr;
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                pjsua2JNI.delete_VideoPreviewOpParam(this.swigCPtr);
            }
            this.swigCPtr = 0L;
        }
    }

    protected void finalize() {
        delete();
    }

    public MediaFormat getFormat() {
        long VideoPreviewOpParam_format_get = pjsua2JNI.VideoPreviewOpParam_format_get(this.swigCPtr, this);
        if (VideoPreviewOpParam_format_get == 0) {
            return null;
        }
        return new MediaFormat(VideoPreviewOpParam_format_get, false);
    }

    public int getRendId() {
        return pjsua2JNI.VideoPreviewOpParam_rendId_get(this.swigCPtr, this);
    }

    public boolean getShow() {
        return pjsua2JNI.VideoPreviewOpParam_show_get(this.swigCPtr, this);
    }

    public VideoWindowHandle getWindow() {
        long VideoPreviewOpParam_window_get = pjsua2JNI.VideoPreviewOpParam_window_get(this.swigCPtr, this);
        if (VideoPreviewOpParam_window_get == 0) {
            return null;
        }
        return new VideoWindowHandle(VideoPreviewOpParam_window_get, false);
    }

    public long getWindowFlags() {
        return pjsua2JNI.VideoPreviewOpParam_windowFlags_get(this.swigCPtr, this);
    }

    public void setFormat(MediaFormat mediaFormat) {
        pjsua2JNI.VideoPreviewOpParam_format_set(this.swigCPtr, this, MediaFormat.getCPtr(mediaFormat), mediaFormat);
    }

    public void setRendId(int i) {
        pjsua2JNI.VideoPreviewOpParam_rendId_set(this.swigCPtr, this, i);
    }

    public void setShow(boolean z) {
        pjsua2JNI.VideoPreviewOpParam_show_set(this.swigCPtr, this, z);
    }

    public void setWindow(VideoWindowHandle videoWindowHandle) {
        pjsua2JNI.VideoPreviewOpParam_window_set(this.swigCPtr, this, VideoWindowHandle.getCPtr(videoWindowHandle), videoWindowHandle);
    }

    public void setWindowFlags(long j) {
        pjsua2JNI.VideoPreviewOpParam_windowFlags_set(this.swigCPtr, this, j);
    }

    public VideoPreviewOpParam() {
        this(pjsua2JNI.new_VideoPreviewOpParam(), true);
    }
}
