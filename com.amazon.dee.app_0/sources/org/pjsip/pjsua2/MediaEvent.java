package org.pjsip.pjsua2;
/* loaded from: classes5.dex */
public class MediaEvent {
    protected transient boolean swigCMemOwn;
    private transient long swigCPtr;

    /* JADX INFO: Access modifiers changed from: protected */
    public MediaEvent(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static long getCPtr(MediaEvent mediaEvent) {
        if (mediaEvent == null) {
            return 0L;
        }
        return mediaEvent.swigCPtr;
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                pjsua2JNI.delete_MediaEvent(this.swigCPtr);
            }
            this.swigCPtr = 0L;
        }
    }

    protected void finalize() {
        delete();
    }

    public MediaEventData getData() {
        long MediaEvent_data_get = pjsua2JNI.MediaEvent_data_get(this.swigCPtr, this);
        if (MediaEvent_data_get == 0) {
            return null;
        }
        return new MediaEventData(MediaEvent_data_get, false);
    }

    public SWIGTYPE_p_void getPjMediaEvent() {
        long MediaEvent_pjMediaEvent_get = pjsua2JNI.MediaEvent_pjMediaEvent_get(this.swigCPtr, this);
        if (MediaEvent_pjMediaEvent_get == 0) {
            return null;
        }
        return new SWIGTYPE_p_void(MediaEvent_pjMediaEvent_get, false);
    }

    public pjmedia_event_type getType() {
        return pjmedia_event_type.swigToEnum(pjsua2JNI.MediaEvent_type_get(this.swigCPtr, this));
    }

    public void setData(MediaEventData mediaEventData) {
        pjsua2JNI.MediaEvent_data_set(this.swigCPtr, this, MediaEventData.getCPtr(mediaEventData), mediaEventData);
    }

    public void setPjMediaEvent(SWIGTYPE_p_void sWIGTYPE_p_void) {
        pjsua2JNI.MediaEvent_pjMediaEvent_set(this.swigCPtr, this, SWIGTYPE_p_void.getCPtr(sWIGTYPE_p_void));
    }

    public void setType(pjmedia_event_type pjmedia_event_typeVar) {
        pjsua2JNI.MediaEvent_type_set(this.swigCPtr, this, pjmedia_event_typeVar.swigValue());
    }

    public MediaEvent() {
        this(pjsua2JNI.new_MediaEvent(), true);
    }
}
