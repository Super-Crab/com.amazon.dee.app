package org.pjsip.pjsua2;
/* loaded from: classes5.dex */
public class VideoWindowHandle {
    protected transient boolean swigCMemOwn;
    private transient long swigCPtr;

    /* JADX INFO: Access modifiers changed from: protected */
    public VideoWindowHandle(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static long getCPtr(VideoWindowHandle videoWindowHandle) {
        if (videoWindowHandle == null) {
            return 0L;
        }
        return videoWindowHandle.swigCPtr;
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                pjsua2JNI.delete_VideoWindowHandle(this.swigCPtr);
            }
            this.swigCPtr = 0L;
        }
    }

    protected void finalize() {
        delete();
    }

    public WindowHandle getHandle() {
        long VideoWindowHandle_handle_get = pjsua2JNI.VideoWindowHandle_handle_get(this.swigCPtr, this);
        if (VideoWindowHandle_handle_get == 0) {
            return null;
        }
        return new WindowHandle(VideoWindowHandle_handle_get, false);
    }

    public SWIGTYPE_p_pjmedia_vid_dev_hwnd_type getType() {
        return new SWIGTYPE_p_pjmedia_vid_dev_hwnd_type(pjsua2JNI.VideoWindowHandle_type_get(this.swigCPtr, this), true);
    }

    public void setHandle(WindowHandle windowHandle) {
        pjsua2JNI.VideoWindowHandle_handle_set(this.swigCPtr, this, WindowHandle.getCPtr(windowHandle), windowHandle);
    }

    public void setType(SWIGTYPE_p_pjmedia_vid_dev_hwnd_type sWIGTYPE_p_pjmedia_vid_dev_hwnd_type) {
        pjsua2JNI.VideoWindowHandle_type_set(this.swigCPtr, this, SWIGTYPE_p_pjmedia_vid_dev_hwnd_type.getCPtr(sWIGTYPE_p_pjmedia_vid_dev_hwnd_type));
    }

    public VideoWindowHandle() {
        this(pjsua2JNI.new_VideoWindowHandle(), true);
    }
}
