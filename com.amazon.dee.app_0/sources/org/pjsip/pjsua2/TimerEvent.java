package org.pjsip.pjsua2;
/* loaded from: classes5.dex */
public class TimerEvent {
    protected transient boolean swigCMemOwn;
    private transient long swigCPtr;

    /* JADX INFO: Access modifiers changed from: protected */
    public TimerEvent(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static long getCPtr(TimerEvent timerEvent) {
        if (timerEvent == null) {
            return 0L;
        }
        return timerEvent.swigCPtr;
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                pjsua2JNI.delete_TimerEvent(this.swigCPtr);
            }
            this.swigCPtr = 0L;
        }
    }

    protected void finalize() {
        delete();
    }

    public SWIGTYPE_p_void getEntry() {
        long TimerEvent_entry_get = pjsua2JNI.TimerEvent_entry_get(this.swigCPtr, this);
        if (TimerEvent_entry_get == 0) {
            return null;
        }
        return new SWIGTYPE_p_void(TimerEvent_entry_get, false);
    }

    public void setEntry(SWIGTYPE_p_void sWIGTYPE_p_void) {
        pjsua2JNI.TimerEvent_entry_set(this.swigCPtr, this, SWIGTYPE_p_void.getCPtr(sWIGTYPE_p_void));
    }

    public TimerEvent() {
        this(pjsua2JNI.new_TimerEvent(), true);
    }
}
