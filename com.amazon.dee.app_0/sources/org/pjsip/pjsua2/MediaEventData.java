package org.pjsip.pjsua2;
/* loaded from: classes5.dex */
public class MediaEventData {
    protected transient boolean swigCMemOwn;
    private transient long swigCPtr;

    /* JADX INFO: Access modifiers changed from: protected */
    public MediaEventData(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static long getCPtr(MediaEventData mediaEventData) {
        if (mediaEventData == null) {
            return 0L;
        }
        return mediaEventData.swigCPtr;
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                pjsua2JNI.delete_MediaEventData(this.swigCPtr);
            }
            this.swigCPtr = 0L;
        }
    }

    protected void finalize() {
        delete();
    }

    public MediaFmtChangedEvent getFmtChanged() {
        long MediaEventData_fmtChanged_get = pjsua2JNI.MediaEventData_fmtChanged_get(this.swigCPtr, this);
        if (MediaEventData_fmtChanged_get == 0) {
            return null;
        }
        return new MediaFmtChangedEvent(MediaEventData_fmtChanged_get, false);
    }

    public SWIGTYPE_p_void getPtr() {
        long MediaEventData_ptr_get = pjsua2JNI.MediaEventData_ptr_get(this.swigCPtr, this);
        if (MediaEventData_ptr_get == 0) {
            return null;
        }
        return new SWIGTYPE_p_void(MediaEventData_ptr_get, false);
    }

    public void setFmtChanged(MediaFmtChangedEvent mediaFmtChangedEvent) {
        pjsua2JNI.MediaEventData_fmtChanged_set(this.swigCPtr, this, MediaFmtChangedEvent.getCPtr(mediaFmtChangedEvent), mediaFmtChangedEvent);
    }

    public void setPtr(SWIGTYPE_p_void sWIGTYPE_p_void) {
        pjsua2JNI.MediaEventData_ptr_set(this.swigCPtr, this, SWIGTYPE_p_void.getCPtr(sWIGTYPE_p_void));
    }

    public MediaEventData() {
        this(pjsua2JNI.new_MediaEventData(), true);
    }
}
