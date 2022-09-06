package org.pjsip.pjsua2;
/* loaded from: classes5.dex */
public class VideoSwitchParam {
    protected transient boolean swigCMemOwn;
    private transient long swigCPtr;

    protected VideoSwitchParam(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static long getCPtr(VideoSwitchParam videoSwitchParam) {
        if (videoSwitchParam == null) {
            return 0L;
        }
        return videoSwitchParam.swigCPtr;
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                pjsua2JNI.delete_VideoSwitchParam(this.swigCPtr);
            }
            this.swigCPtr = 0L;
        }
    }

    protected void finalize() {
        delete();
    }

    public int getTarget_id() {
        return pjsua2JNI.VideoSwitchParam_target_id_get(this.swigCPtr, this);
    }

    public void setTarget_id(int i) {
        pjsua2JNI.VideoSwitchParam_target_id_set(this.swigCPtr, this, i);
    }

    public VideoSwitchParam() {
        this(pjsua2JNI.new_VideoSwitchParam(), true);
    }
}
