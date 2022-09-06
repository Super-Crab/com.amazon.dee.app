package org.pjsip.pjsua2;
/* loaded from: classes5.dex */
public class Media {
    protected transient boolean swigCMemOwn;
    private transient long swigCPtr;

    /* JADX INFO: Access modifiers changed from: protected */
    public Media(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static long getCPtr(Media media) {
        if (media == null) {
            return 0L;
        }
        return media.swigCPtr;
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                pjsua2JNI.delete_Media(this.swigCPtr);
            }
            this.swigCPtr = 0L;
        }
    }

    protected void finalize() {
        delete();
    }

    public pjmedia_type getType() {
        return pjmedia_type.swigToEnum(pjsua2JNI.Media_getType(this.swigCPtr, this));
    }
}
