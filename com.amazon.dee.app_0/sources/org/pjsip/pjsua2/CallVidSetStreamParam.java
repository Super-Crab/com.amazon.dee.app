package org.pjsip.pjsua2;
/* loaded from: classes5.dex */
public class CallVidSetStreamParam {
    protected transient boolean swigCMemOwn;
    private transient long swigCPtr;

    protected CallVidSetStreamParam(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static long getCPtr(CallVidSetStreamParam callVidSetStreamParam) {
        if (callVidSetStreamParam == null) {
            return 0L;
        }
        return callVidSetStreamParam.swigCPtr;
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                pjsua2JNI.delete_CallVidSetStreamParam(this.swigCPtr);
            }
            this.swigCPtr = 0L;
        }
    }

    protected void finalize() {
        delete();
    }

    public int getCapDev() {
        return pjsua2JNI.CallVidSetStreamParam_capDev_get(this.swigCPtr, this);
    }

    public pjmedia_dir getDir() {
        return pjmedia_dir.swigToEnum(pjsua2JNI.CallVidSetStreamParam_dir_get(this.swigCPtr, this));
    }

    public int getMedIdx() {
        return pjsua2JNI.CallVidSetStreamParam_medIdx_get(this.swigCPtr, this);
    }

    public void setCapDev(int i) {
        pjsua2JNI.CallVidSetStreamParam_capDev_set(this.swigCPtr, this, i);
    }

    public void setDir(pjmedia_dir pjmedia_dirVar) {
        pjsua2JNI.CallVidSetStreamParam_dir_set(this.swigCPtr, this, pjmedia_dirVar.swigValue());
    }

    public void setMedIdx(int i) {
        pjsua2JNI.CallVidSetStreamParam_medIdx_set(this.swigCPtr, this, i);
    }

    public CallVidSetStreamParam() {
        this(pjsua2JNI.new_CallVidSetStreamParam(), true);
    }
}
