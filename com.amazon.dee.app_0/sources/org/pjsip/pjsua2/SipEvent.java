package org.pjsip.pjsua2;
/* loaded from: classes5.dex */
public class SipEvent {
    protected transient boolean swigCMemOwn;
    private transient long swigCPtr;

    /* JADX INFO: Access modifiers changed from: protected */
    public SipEvent(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static long getCPtr(SipEvent sipEvent) {
        if (sipEvent == null) {
            return 0L;
        }
        return sipEvent.swigCPtr;
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                pjsua2JNI.delete_SipEvent(this.swigCPtr);
            }
            this.swigCPtr = 0L;
        }
    }

    protected void finalize() {
        delete();
    }

    public SipEventBody getBody() {
        long SipEvent_body_get = pjsua2JNI.SipEvent_body_get(this.swigCPtr, this);
        if (SipEvent_body_get == 0) {
            return null;
        }
        return new SipEventBody(SipEvent_body_get, false);
    }

    public SWIGTYPE_p_void getPjEvent() {
        long SipEvent_pjEvent_get = pjsua2JNI.SipEvent_pjEvent_get(this.swigCPtr, this);
        if (SipEvent_pjEvent_get == 0) {
            return null;
        }
        return new SWIGTYPE_p_void(SipEvent_pjEvent_get, false);
    }

    public pjsip_event_id_e getType() {
        return pjsip_event_id_e.swigToEnum(pjsua2JNI.SipEvent_type_get(this.swigCPtr, this));
    }

    public void setBody(SipEventBody sipEventBody) {
        pjsua2JNI.SipEvent_body_set(this.swigCPtr, this, SipEventBody.getCPtr(sipEventBody), sipEventBody);
    }

    public void setPjEvent(SWIGTYPE_p_void sWIGTYPE_p_void) {
        pjsua2JNI.SipEvent_pjEvent_set(this.swigCPtr, this, SWIGTYPE_p_void.getCPtr(sWIGTYPE_p_void));
    }

    public void setType(pjsip_event_id_e pjsip_event_id_eVar) {
        pjsua2JNI.SipEvent_type_set(this.swigCPtr, this, pjsip_event_id_eVar.swigValue());
    }

    public SipEvent() {
        this(pjsua2JNI.new_SipEvent(), true);
    }
}
