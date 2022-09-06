package org.pjsip.pjsua2;
/* loaded from: classes5.dex */
public class CallSetting {
    protected transient boolean swigCMemOwn;
    private transient long swigCPtr;

    /* JADX INFO: Access modifiers changed from: protected */
    public CallSetting(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static long getCPtr(CallSetting callSetting) {
        if (callSetting == null) {
            return 0L;
        }
        return callSetting.swigCPtr;
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                pjsua2JNI.delete_CallSetting(this.swigCPtr);
            }
            this.swigCPtr = 0L;
        }
    }

    protected void finalize() {
        delete();
    }

    public long getAudioCount() {
        return pjsua2JNI.CallSetting_audioCount_get(this.swigCPtr, this);
    }

    public long getFlag() {
        return pjsua2JNI.CallSetting_flag_get(this.swigCPtr, this);
    }

    public long getReqKeyframeMethod() {
        return pjsua2JNI.CallSetting_reqKeyframeMethod_get(this.swigCPtr, this);
    }

    public long getVideoCount() {
        return pjsua2JNI.CallSetting_videoCount_get(this.swigCPtr, this);
    }

    public boolean isEmpty() {
        return pjsua2JNI.CallSetting_isEmpty(this.swigCPtr, this);
    }

    public void setAudioCount(long j) {
        pjsua2JNI.CallSetting_audioCount_set(this.swigCPtr, this, j);
    }

    public void setFlag(long j) {
        pjsua2JNI.CallSetting_flag_set(this.swigCPtr, this, j);
    }

    public void setReqKeyframeMethod(long j) {
        pjsua2JNI.CallSetting_reqKeyframeMethod_set(this.swigCPtr, this, j);
    }

    public void setVideoCount(long j) {
        pjsua2JNI.CallSetting_videoCount_set(this.swigCPtr, this, j);
    }

    public CallSetting(SWIGTYPE_p_pj_bool_t sWIGTYPE_p_pj_bool_t) {
        this(pjsua2JNI.new_CallSetting__SWIG_0(SWIGTYPE_p_pj_bool_t.getCPtr(sWIGTYPE_p_pj_bool_t)), true);
    }

    public CallSetting() {
        this(pjsua2JNI.new_CallSetting__SWIG_1(), true);
    }
}
