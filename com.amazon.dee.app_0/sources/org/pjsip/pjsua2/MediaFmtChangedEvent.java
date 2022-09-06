package org.pjsip.pjsua2;
/* loaded from: classes5.dex */
public class MediaFmtChangedEvent {
    protected transient boolean swigCMemOwn;
    private transient long swigCPtr;

    /* JADX INFO: Access modifiers changed from: protected */
    public MediaFmtChangedEvent(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static long getCPtr(MediaFmtChangedEvent mediaFmtChangedEvent) {
        if (mediaFmtChangedEvent == null) {
            return 0L;
        }
        return mediaFmtChangedEvent.swigCPtr;
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                pjsua2JNI.delete_MediaFmtChangedEvent(this.swigCPtr);
            }
            this.swigCPtr = 0L;
        }
    }

    protected void finalize() {
        delete();
    }

    public long getNewHeight() {
        return pjsua2JNI.MediaFmtChangedEvent_newHeight_get(this.swigCPtr, this);
    }

    public long getNewWidth() {
        return pjsua2JNI.MediaFmtChangedEvent_newWidth_get(this.swigCPtr, this);
    }

    public void setNewHeight(long j) {
        pjsua2JNI.MediaFmtChangedEvent_newHeight_set(this.swigCPtr, this, j);
    }

    public void setNewWidth(long j) {
        pjsua2JNI.MediaFmtChangedEvent_newWidth_set(this.swigCPtr, this, j);
    }

    public MediaFmtChangedEvent() {
        this(pjsua2JNI.new_MediaFmtChangedEvent(), true);
    }
}
