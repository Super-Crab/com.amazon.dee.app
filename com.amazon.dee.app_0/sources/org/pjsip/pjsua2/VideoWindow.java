package org.pjsip.pjsua2;
/* loaded from: classes5.dex */
public class VideoWindow {
    protected transient boolean swigCMemOwn;
    private transient long swigCPtr;

    /* JADX INFO: Access modifiers changed from: protected */
    public VideoWindow(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static long getCPtr(VideoWindow videoWindow) {
        if (videoWindow == null) {
            return 0L;
        }
        return videoWindow.swigCPtr;
    }

    public void Show(boolean z) throws Exception {
        pjsua2JNI.VideoWindow_Show(this.swigCPtr, this, z);
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                pjsua2JNI.delete_VideoWindow(this.swigCPtr);
            }
            this.swigCPtr = 0L;
        }
    }

    protected void finalize() {
        delete();
    }

    public VideoWindowInfo getInfo() throws Exception {
        return new VideoWindowInfo(pjsua2JNI.VideoWindow_getInfo(this.swigCPtr, this), true);
    }

    public void rotate(int i) throws Exception {
        pjsua2JNI.VideoWindow_rotate(this.swigCPtr, this, i);
    }

    public void setPos(MediaCoordinate mediaCoordinate) throws Exception {
        pjsua2JNI.VideoWindow_setPos(this.swigCPtr, this, MediaCoordinate.getCPtr(mediaCoordinate), mediaCoordinate);
    }

    public void setSize(MediaSize mediaSize) throws Exception {
        pjsua2JNI.VideoWindow_setSize(this.swigCPtr, this, MediaSize.getCPtr(mediaSize), mediaSize);
    }

    public void setWindow(VideoWindowHandle videoWindowHandle) throws Exception {
        pjsua2JNI.VideoWindow_setWindow(this.swigCPtr, this, VideoWindowHandle.getCPtr(videoWindowHandle), videoWindowHandle);
    }

    public VideoWindow(int i) {
        this(pjsua2JNI.new_VideoWindow(i), true);
    }
}
