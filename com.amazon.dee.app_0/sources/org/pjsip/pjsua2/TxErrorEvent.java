package org.pjsip.pjsua2;
/* loaded from: classes5.dex */
public class TxErrorEvent {
    protected transient boolean swigCMemOwn;
    private transient long swigCPtr;

    /* JADX INFO: Access modifiers changed from: protected */
    public TxErrorEvent(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static long getCPtr(TxErrorEvent txErrorEvent) {
        if (txErrorEvent == null) {
            return 0L;
        }
        return txErrorEvent.swigCPtr;
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                pjsua2JNI.delete_TxErrorEvent(this.swigCPtr);
            }
            this.swigCPtr = 0L;
        }
    }

    protected void finalize() {
        delete();
    }

    public SipTxData getTdata() {
        long TxErrorEvent_tdata_get = pjsua2JNI.TxErrorEvent_tdata_get(this.swigCPtr, this);
        if (TxErrorEvent_tdata_get == 0) {
            return null;
        }
        return new SipTxData(TxErrorEvent_tdata_get, false);
    }

    public SipTransaction getTsx() {
        long TxErrorEvent_tsx_get = pjsua2JNI.TxErrorEvent_tsx_get(this.swigCPtr, this);
        if (TxErrorEvent_tsx_get == 0) {
            return null;
        }
        return new SipTransaction(TxErrorEvent_tsx_get, false);
    }

    public void setTdata(SipTxData sipTxData) {
        pjsua2JNI.TxErrorEvent_tdata_set(this.swigCPtr, this, SipTxData.getCPtr(sipTxData), sipTxData);
    }

    public void setTsx(SipTransaction sipTransaction) {
        pjsua2JNI.TxErrorEvent_tsx_set(this.swigCPtr, this, SipTransaction.getCPtr(sipTransaction), sipTransaction);
    }

    public TxErrorEvent() {
        this(pjsua2JNI.new_TxErrorEvent(), true);
    }
}
