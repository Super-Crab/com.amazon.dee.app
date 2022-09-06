package org.pjsip.pjsua2;
/* loaded from: classes5.dex */
public class AudioDevInfo {
    protected transient boolean swigCMemOwn;
    private transient long swigCPtr;

    /* JADX INFO: Access modifiers changed from: protected */
    public AudioDevInfo(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static long getCPtr(AudioDevInfo audioDevInfo) {
        if (audioDevInfo == null) {
            return 0L;
        }
        return audioDevInfo.swigCPtr;
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                pjsua2JNI.delete_AudioDevInfo(this.swigCPtr);
            }
            this.swigCPtr = 0L;
        }
    }

    protected void finalize() {
        delete();
    }

    public long getCaps() {
        return pjsua2JNI.AudioDevInfo_caps_get(this.swigCPtr, this);
    }

    public long getDefaultSamplesPerSec() {
        return pjsua2JNI.AudioDevInfo_defaultSamplesPerSec_get(this.swigCPtr, this);
    }

    public String getDriver() {
        return pjsua2JNI.AudioDevInfo_driver_get(this.swigCPtr, this);
    }

    public MediaFormatVector getExtFmt() {
        long AudioDevInfo_extFmt_get = pjsua2JNI.AudioDevInfo_extFmt_get(this.swigCPtr, this);
        if (AudioDevInfo_extFmt_get == 0) {
            return null;
        }
        return new MediaFormatVector(AudioDevInfo_extFmt_get, false);
    }

    public long getInputCount() {
        return pjsua2JNI.AudioDevInfo_inputCount_get(this.swigCPtr, this);
    }

    public String getName() {
        return pjsua2JNI.AudioDevInfo_name_get(this.swigCPtr, this);
    }

    public long getOutputCount() {
        return pjsua2JNI.AudioDevInfo_outputCount_get(this.swigCPtr, this);
    }

    public long getRoutes() {
        return pjsua2JNI.AudioDevInfo_routes_get(this.swigCPtr, this);
    }

    public void setCaps(long j) {
        pjsua2JNI.AudioDevInfo_caps_set(this.swigCPtr, this, j);
    }

    public void setDefaultSamplesPerSec(long j) {
        pjsua2JNI.AudioDevInfo_defaultSamplesPerSec_set(this.swigCPtr, this, j);
    }

    public void setDriver(String str) {
        pjsua2JNI.AudioDevInfo_driver_set(this.swigCPtr, this, str);
    }

    public void setExtFmt(MediaFormatVector mediaFormatVector) {
        pjsua2JNI.AudioDevInfo_extFmt_set(this.swigCPtr, this, MediaFormatVector.getCPtr(mediaFormatVector), mediaFormatVector);
    }

    public void setInputCount(long j) {
        pjsua2JNI.AudioDevInfo_inputCount_set(this.swigCPtr, this, j);
    }

    public void setName(String str) {
        pjsua2JNI.AudioDevInfo_name_set(this.swigCPtr, this, str);
    }

    public void setOutputCount(long j) {
        pjsua2JNI.AudioDevInfo_outputCount_set(this.swigCPtr, this, j);
    }

    public void setRoutes(long j) {
        pjsua2JNI.AudioDevInfo_routes_set(this.swigCPtr, this, j);
    }

    public AudioDevInfo() {
        this(pjsua2JNI.new_AudioDevInfo(), true);
    }
}
