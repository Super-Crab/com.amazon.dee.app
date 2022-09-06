package org.pjsip.pjsua2;
/* loaded from: classes5.dex */
public class VidDevManager {
    protected transient boolean swigCMemOwn;
    private transient long swigCPtr;

    /* JADX INFO: Access modifiers changed from: protected */
    public VidDevManager(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    protected static long getCPtr(VidDevManager vidDevManager) {
        if (vidDevManager == null) {
            return 0L;
        }
        return vidDevManager.swigCPtr;
    }

    public String capName(pjmedia_vid_dev_cap pjmedia_vid_dev_capVar) {
        return pjsua2JNI.VidDevManager_capName(this.swigCPtr, this, pjmedia_vid_dev_capVar.swigValue());
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (!this.swigCMemOwn) {
                this.swigCPtr = 0L;
            } else {
                this.swigCMemOwn = false;
                throw new UnsupportedOperationException("C++ destructor does not have public access");
            }
        }
    }

    public VideoDevInfoVector enumDev() throws Exception {
        return new VideoDevInfoVector(pjsua2JNI.VidDevManager_enumDev(this.swigCPtr, this), false);
    }

    public long getDevCount() {
        return pjsua2JNI.VidDevManager_getDevCount(this.swigCPtr, this);
    }

    public VideoDevInfo getDevInfo(int i) throws Exception {
        return new VideoDevInfo(pjsua2JNI.VidDevManager_getDevInfo(this.swigCPtr, this, i), true);
    }

    public MediaFormatVideo getFormat(int i) throws Exception {
        return new MediaFormatVideo(pjsua2JNI.VidDevManager_getFormat(this.swigCPtr, this, i), true);
    }

    public MediaSize getInputScale(int i) throws Exception {
        return new MediaSize(pjsua2JNI.VidDevManager_getInputScale(this.swigCPtr, this, i), true);
    }

    public int getOutputWindowFlags(int i) throws Exception {
        return pjsua2JNI.VidDevManager_getOutputWindowFlags(this.swigCPtr, this, i);
    }

    public boolean isCaptureActive(int i) {
        return pjsua2JNI.VidDevManager_isCaptureActive(this.swigCPtr, this, i);
    }

    public int lookupDev(String str, String str2) throws Exception {
        return pjsua2JNI.VidDevManager_lookupDev(this.swigCPtr, this, str, str2);
    }

    public void refreshDevs() throws Exception {
        pjsua2JNI.VidDevManager_refreshDevs(this.swigCPtr, this);
    }

    public void setCaptureOrient(int i, pjmedia_orient pjmedia_orientVar, boolean z) throws Exception {
        pjsua2JNI.VidDevManager_setCaptureOrient__SWIG_0(this.swigCPtr, this, i, pjmedia_orientVar.swigValue(), z);
    }

    public void setFormat(int i, MediaFormatVideo mediaFormatVideo, boolean z) throws Exception {
        pjsua2JNI.VidDevManager_setFormat(this.swigCPtr, this, i, MediaFormatVideo.getCPtr(mediaFormatVideo), mediaFormatVideo, z);
    }

    public void setInputScale(int i, MediaSize mediaSize, boolean z) throws Exception {
        pjsua2JNI.VidDevManager_setInputScale(this.swigCPtr, this, i, MediaSize.getCPtr(mediaSize), mediaSize, z);
    }

    public void setOutputWindowFlags(int i, int i2, boolean z) throws Exception {
        pjsua2JNI.VidDevManager_setOutputWindowFlags(this.swigCPtr, this, i, i2, z);
    }

    public void switchDev(int i, VideoSwitchParam videoSwitchParam) throws Exception {
        pjsua2JNI.VidDevManager_switchDev(this.swigCPtr, this, i, VideoSwitchParam.getCPtr(videoSwitchParam), videoSwitchParam);
    }

    public void setCaptureOrient(int i, pjmedia_orient pjmedia_orientVar) throws Exception {
        pjsua2JNI.VidDevManager_setCaptureOrient__SWIG_1(this.swigCPtr, this, i, pjmedia_orientVar.swigValue());
    }
}
