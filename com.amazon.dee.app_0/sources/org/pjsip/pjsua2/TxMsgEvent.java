package org.pjsip.pjsua2;
/* loaded from: classes5.dex */
public class TxMsgEvent {
    protected transient boolean swigCMemOwn;
    private transient long swigCPtr;

    /* JADX INFO: Access modifiers changed from: protected */
    public TxMsgEvent(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static long getCPtr(TxMsgEvent txMsgEvent) {
        if (txMsgEvent == null) {
            return 0L;
        }
        return txMsgEvent.swigCPtr;
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                pjsua2JNI.delete_TxMsgEvent(this.swigCPtr);
            }
            this.swigCPtr = 0L;
        }
    }

    protected void finalize() {
        delete();
    }

    public SipTxData getTdata() {
        long TxMsgEvent_tdata_get = pjsua2JNI.TxMsgEvent_tdata_get(this.swigCPtr, this);
        if (TxMsgEvent_tdata_get == 0) {
            return null;
        }
        return new SipTxData(TxMsgEvent_tdata_get, false);
    }

    public void setTdata(SipTxData sipTxData) {
        pjsua2JNI.TxMsgEvent_tdata_set(this.swigCPtr, this, SipTxData.getCPtr(sipTxData), sipTxData);
    }

    public TxMsgEvent() {
        this(pjsua2JNI.new_TxMsgEvent(), true);
    }
}
