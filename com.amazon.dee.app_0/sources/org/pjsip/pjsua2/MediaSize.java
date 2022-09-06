package org.pjsip.pjsua2;
/* loaded from: classes5.dex */
public class MediaSize {
    protected transient boolean swigCMemOwn;
    private transient long swigCPtr;

    /* JADX INFO: Access modifiers changed from: protected */
    public MediaSize(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static long getCPtr(MediaSize mediaSize) {
        if (mediaSize == null) {
            return 0L;
        }
        return mediaSize.swigCPtr;
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                pjsua2JNI.delete_MediaSize(this.swigCPtr);
            }
            this.swigCPtr = 0L;
        }
    }

    protected void finalize() {
        delete();
    }

    public long getH() {
        return pjsua2JNI.MediaSize_h_get(this.swigCPtr, this);
    }

    public long getW() {
        return pjsua2JNI.MediaSize_w_get(this.swigCPtr, this);
    }

    public void setH(long j) {
        pjsua2JNI.MediaSize_h_set(this.swigCPtr, this, j);
    }

    public void setW(long j) {
        pjsua2JNI.MediaSize_w_set(this.swigCPtr, this, j);
    }

    public MediaSize() {
        this(pjsua2JNI.new_MediaSize(), true);
    }
}
