package org.pjsip.pjsua2;
/* loaded from: classes5.dex */
public class VideoWindowInfo {
    protected transient boolean swigCMemOwn;
    private transient long swigCPtr;

    /* JADX INFO: Access modifiers changed from: protected */
    public VideoWindowInfo(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    protected static long getCPtr(VideoWindowInfo videoWindowInfo) {
        if (videoWindowInfo == null) {
            return 0L;
        }
        return videoWindowInfo.swigCPtr;
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                pjsua2JNI.delete_VideoWindowInfo(this.swigCPtr);
            }
            this.swigCPtr = 0L;
        }
    }

    protected void finalize() {
        delete();
    }

    public boolean getIsNative() {
        return pjsua2JNI.VideoWindowInfo_isNative_get(this.swigCPtr, this);
    }

    public MediaCoordinate getPos() {
        long VideoWindowInfo_pos_get = pjsua2JNI.VideoWindowInfo_pos_get(this.swigCPtr, this);
        if (VideoWindowInfo_pos_get == 0) {
            return null;
        }
        return new MediaCoordinate(VideoWindowInfo_pos_get, false);
    }

    public int getRenderDeviceId() {
        return pjsua2JNI.VideoWindowInfo_renderDeviceId_get(this.swigCPtr, this);
    }

    public boolean getShow() {
        return pjsua2JNI.VideoWindowInfo_show_get(this.swigCPtr, this);
    }

    public MediaSize getSize() {
        long VideoWindowInfo_size_get = pjsua2JNI.VideoWindowInfo_size_get(this.swigCPtr, this);
        if (VideoWindowInfo_size_get == 0) {
            return null;
        }
        return new MediaSize(VideoWindowInfo_size_get, false);
    }

    public VideoWindowHandle getWinHandle() {
        long VideoWindowInfo_winHandle_get = pjsua2JNI.VideoWindowInfo_winHandle_get(this.swigCPtr, this);
        if (VideoWindowInfo_winHandle_get == 0) {
            return null;
        }
        return new VideoWindowHandle(VideoWindowInfo_winHandle_get, false);
    }

    public void setIsNative(boolean z) {
        pjsua2JNI.VideoWindowInfo_isNative_set(this.swigCPtr, this, z);
    }

    public void setPos(MediaCoordinate mediaCoordinate) {
        pjsua2JNI.VideoWindowInfo_pos_set(this.swigCPtr, this, MediaCoordinate.getCPtr(mediaCoordinate), mediaCoordinate);
    }

    public void setRenderDeviceId(int i) {
        pjsua2JNI.VideoWindowInfo_renderDeviceId_set(this.swigCPtr, this, i);
    }

    public void setShow(boolean z) {
        pjsua2JNI.VideoWindowInfo_show_set(this.swigCPtr, this, z);
    }

    public void setSize(MediaSize mediaSize) {
        pjsua2JNI.VideoWindowInfo_size_set(this.swigCPtr, this, MediaSize.getCPtr(mediaSize), mediaSize);
    }

    public void setWinHandle(VideoWindowHandle videoWindowHandle) {
        pjsua2JNI.VideoWindowInfo_winHandle_set(this.swigCPtr, this, VideoWindowHandle.getCPtr(videoWindowHandle), videoWindowHandle);
    }

    public VideoWindowInfo() {
        this(pjsua2JNI.new_VideoWindowInfo(), true);
    }
}
