package org.pjsip.pjsua2;
/* loaded from: classes5.dex */
public class VideoDevInfo {
    protected transient boolean swigCMemOwn;
    private transient long swigCPtr;

    /* JADX INFO: Access modifiers changed from: protected */
    public VideoDevInfo(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static long getCPtr(VideoDevInfo videoDevInfo) {
        if (videoDevInfo == null) {
            return 0L;
        }
        return videoDevInfo.swigCPtr;
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                pjsua2JNI.delete_VideoDevInfo(this.swigCPtr);
            }
            this.swigCPtr = 0L;
        }
    }

    protected void finalize() {
        delete();
    }

    public long getCaps() {
        return pjsua2JNI.VideoDevInfo_caps_get(this.swigCPtr, this);
    }

    public pjmedia_dir getDir() {
        return pjmedia_dir.swigToEnum(pjsua2JNI.VideoDevInfo_dir_get(this.swigCPtr, this));
    }

    public String getDriver() {
        return pjsua2JNI.VideoDevInfo_driver_get(this.swigCPtr, this);
    }

    public MediaFormatVector getFmt() {
        long VideoDevInfo_fmt_get = pjsua2JNI.VideoDevInfo_fmt_get(this.swigCPtr, this);
        if (VideoDevInfo_fmt_get == 0) {
            return null;
        }
        return new MediaFormatVector(VideoDevInfo_fmt_get, false);
    }

    public int getId() {
        return pjsua2JNI.VideoDevInfo_id_get(this.swigCPtr, this);
    }

    public String getName() {
        return pjsua2JNI.VideoDevInfo_name_get(this.swigCPtr, this);
    }

    public void setCaps(long j) {
        pjsua2JNI.VideoDevInfo_caps_set(this.swigCPtr, this, j);
    }

    public void setDir(pjmedia_dir pjmedia_dirVar) {
        pjsua2JNI.VideoDevInfo_dir_set(this.swigCPtr, this, pjmedia_dirVar.swigValue());
    }

    public void setDriver(String str) {
        pjsua2JNI.VideoDevInfo_driver_set(this.swigCPtr, this, str);
    }

    public void setFmt(MediaFormatVector mediaFormatVector) {
        pjsua2JNI.VideoDevInfo_fmt_set(this.swigCPtr, this, MediaFormatVector.getCPtr(mediaFormatVector), mediaFormatVector);
    }

    public void setId(int i) {
        pjsua2JNI.VideoDevInfo_id_set(this.swigCPtr, this, i);
    }

    public void setName(String str) {
        pjsua2JNI.VideoDevInfo_name_set(this.swigCPtr, this, str);
    }

    public VideoDevInfo() {
        this(pjsua2JNI.new_VideoDevInfo(), true);
    }
}
