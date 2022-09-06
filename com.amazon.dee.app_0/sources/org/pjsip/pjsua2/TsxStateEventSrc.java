package org.pjsip.pjsua2;
/* loaded from: classes5.dex */
public class TsxStateEventSrc {
    protected transient boolean swigCMemOwn;
    private transient long swigCPtr;

    /* JADX INFO: Access modifiers changed from: protected */
    public TsxStateEventSrc(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static long getCPtr(TsxStateEventSrc tsxStateEventSrc) {
        if (tsxStateEventSrc == null) {
            return 0L;
        }
        return tsxStateEventSrc.swigCPtr;
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                pjsua2JNI.delete_TsxStateEventSrc(this.swigCPtr);
            }
            this.swigCPtr = 0L;
        }
    }

    protected void finalize() {
        delete();
    }

    public SWIGTYPE_p_void getData() {
        long TsxStateEventSrc_data_get = pjsua2JNI.TsxStateEventSrc_data_get(this.swigCPtr, this);
        if (TsxStateEventSrc_data_get == 0) {
            return null;
        }
        return new SWIGTYPE_p_void(TsxStateEventSrc_data_get, false);
    }

    public SipRxData getRdata() {
        long TsxStateEventSrc_rdata_get = pjsua2JNI.TsxStateEventSrc_rdata_get(this.swigCPtr, this);
        if (TsxStateEventSrc_rdata_get == 0) {
            return null;
        }
        return new SipRxData(TsxStateEventSrc_rdata_get, false);
    }

    public int getStatus() {
        return pjsua2JNI.TsxStateEventSrc_status_get(this.swigCPtr, this);
    }

    public SipTxData getTdata() {
        long TsxStateEventSrc_tdata_get = pjsua2JNI.TsxStateEventSrc_tdata_get(this.swigCPtr, this);
        if (TsxStateEventSrc_tdata_get == 0) {
            return null;
        }
        return new SipTxData(TsxStateEventSrc_tdata_get, false);
    }

    public SWIGTYPE_p_void getTimer() {
        long TsxStateEventSrc_timer_get = pjsua2JNI.TsxStateEventSrc_timer_get(this.swigCPtr, this);
        if (TsxStateEventSrc_timer_get == 0) {
            return null;
        }
        return new SWIGTYPE_p_void(TsxStateEventSrc_timer_get, false);
    }

    public void setData(SWIGTYPE_p_void sWIGTYPE_p_void) {
        pjsua2JNI.TsxStateEventSrc_data_set(this.swigCPtr, this, SWIGTYPE_p_void.getCPtr(sWIGTYPE_p_void));
    }

    public void setRdata(SipRxData sipRxData) {
        pjsua2JNI.TsxStateEventSrc_rdata_set(this.swigCPtr, this, SipRxData.getCPtr(sipRxData), sipRxData);
    }

    public void setStatus(int i) {
        pjsua2JNI.TsxStateEventSrc_status_set(this.swigCPtr, this, i);
    }

    public void setTdata(SipTxData sipTxData) {
        pjsua2JNI.TsxStateEventSrc_tdata_set(this.swigCPtr, this, SipTxData.getCPtr(sipTxData), sipTxData);
    }

    public void setTimer(SWIGTYPE_p_void sWIGTYPE_p_void) {
        pjsua2JNI.TsxStateEventSrc_timer_set(this.swigCPtr, this, SWIGTYPE_p_void.getCPtr(sWIGTYPE_p_void));
    }

    public TsxStateEventSrc() {
        this(pjsua2JNI.new_TsxStateEventSrc(), true);
    }
}
