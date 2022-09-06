package org.pjsip.pjsua2;
/* loaded from: classes5.dex */
public class TsxStateEvent {
    protected transient boolean swigCMemOwn;
    private transient long swigCPtr;

    /* JADX INFO: Access modifiers changed from: protected */
    public TsxStateEvent(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static long getCPtr(TsxStateEvent tsxStateEvent) {
        if (tsxStateEvent == null) {
            return 0L;
        }
        return tsxStateEvent.swigCPtr;
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                pjsua2JNI.delete_TsxStateEvent(this.swigCPtr);
            }
            this.swigCPtr = 0L;
        }
    }

    protected void finalize() {
        delete();
    }

    public pjsip_tsx_state_e getPrevState() {
        return pjsip_tsx_state_e.swigToEnum(pjsua2JNI.TsxStateEvent_prevState_get(this.swigCPtr, this));
    }

    public TsxStateEventSrc getSrc() {
        long TsxStateEvent_src_get = pjsua2JNI.TsxStateEvent_src_get(this.swigCPtr, this);
        if (TsxStateEvent_src_get == 0) {
            return null;
        }
        return new TsxStateEventSrc(TsxStateEvent_src_get, false);
    }

    public SipTransaction getTsx() {
        long TsxStateEvent_tsx_get = pjsua2JNI.TsxStateEvent_tsx_get(this.swigCPtr, this);
        if (TsxStateEvent_tsx_get == 0) {
            return null;
        }
        return new SipTransaction(TsxStateEvent_tsx_get, false);
    }

    public pjsip_event_id_e getType() {
        return pjsip_event_id_e.swigToEnum(pjsua2JNI.TsxStateEvent_type_get(this.swigCPtr, this));
    }

    public void setPrevState(pjsip_tsx_state_e pjsip_tsx_state_eVar) {
        pjsua2JNI.TsxStateEvent_prevState_set(this.swigCPtr, this, pjsip_tsx_state_eVar.swigValue());
    }

    public void setSrc(TsxStateEventSrc tsxStateEventSrc) {
        pjsua2JNI.TsxStateEvent_src_set(this.swigCPtr, this, TsxStateEventSrc.getCPtr(tsxStateEventSrc), tsxStateEventSrc);
    }

    public void setTsx(SipTransaction sipTransaction) {
        pjsua2JNI.TsxStateEvent_tsx_set(this.swigCPtr, this, SipTransaction.getCPtr(sipTransaction), sipTransaction);
    }

    public void setType(pjsip_event_id_e pjsip_event_id_eVar) {
        pjsua2JNI.TsxStateEvent_type_set(this.swigCPtr, this, pjsip_event_id_eVar.swigValue());
    }

    public TsxStateEvent() {
        this(pjsua2JNI.new_TsxStateEvent(), true);
    }
}
