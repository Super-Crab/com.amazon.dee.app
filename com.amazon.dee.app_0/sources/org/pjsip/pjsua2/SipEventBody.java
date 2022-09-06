package org.pjsip.pjsua2;
/* loaded from: classes5.dex */
public class SipEventBody {
    protected transient boolean swigCMemOwn;
    private transient long swigCPtr;

    /* JADX INFO: Access modifiers changed from: protected */
    public SipEventBody(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static long getCPtr(SipEventBody sipEventBody) {
        if (sipEventBody == null) {
            return 0L;
        }
        return sipEventBody.swigCPtr;
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                pjsua2JNI.delete_SipEventBody(this.swigCPtr);
            }
            this.swigCPtr = 0L;
        }
    }

    protected void finalize() {
        delete();
    }

    public RxMsgEvent getRxMsg() {
        long SipEventBody_rxMsg_get = pjsua2JNI.SipEventBody_rxMsg_get(this.swigCPtr, this);
        if (SipEventBody_rxMsg_get == 0) {
            return null;
        }
        return new RxMsgEvent(SipEventBody_rxMsg_get, false);
    }

    public TimerEvent getTimer() {
        long SipEventBody_timer_get = pjsua2JNI.SipEventBody_timer_get(this.swigCPtr, this);
        if (SipEventBody_timer_get == 0) {
            return null;
        }
        return new TimerEvent(SipEventBody_timer_get, false);
    }

    public TsxStateEvent getTsxState() {
        long SipEventBody_tsxState_get = pjsua2JNI.SipEventBody_tsxState_get(this.swigCPtr, this);
        if (SipEventBody_tsxState_get == 0) {
            return null;
        }
        return new TsxStateEvent(SipEventBody_tsxState_get, false);
    }

    public TxErrorEvent getTxError() {
        long SipEventBody_txError_get = pjsua2JNI.SipEventBody_txError_get(this.swigCPtr, this);
        if (SipEventBody_txError_get == 0) {
            return null;
        }
        return new TxErrorEvent(SipEventBody_txError_get, false);
    }

    public TxMsgEvent getTxMsg() {
        long SipEventBody_txMsg_get = pjsua2JNI.SipEventBody_txMsg_get(this.swigCPtr, this);
        if (SipEventBody_txMsg_get == 0) {
            return null;
        }
        return new TxMsgEvent(SipEventBody_txMsg_get, false);
    }

    public UserEvent getUser() {
        long SipEventBody_user_get = pjsua2JNI.SipEventBody_user_get(this.swigCPtr, this);
        if (SipEventBody_user_get == 0) {
            return null;
        }
        return new UserEvent(SipEventBody_user_get, false);
    }

    public void setRxMsg(RxMsgEvent rxMsgEvent) {
        pjsua2JNI.SipEventBody_rxMsg_set(this.swigCPtr, this, RxMsgEvent.getCPtr(rxMsgEvent), rxMsgEvent);
    }

    public void setTimer(TimerEvent timerEvent) {
        pjsua2JNI.SipEventBody_timer_set(this.swigCPtr, this, TimerEvent.getCPtr(timerEvent), timerEvent);
    }

    public void setTsxState(TsxStateEvent tsxStateEvent) {
        pjsua2JNI.SipEventBody_tsxState_set(this.swigCPtr, this, TsxStateEvent.getCPtr(tsxStateEvent), tsxStateEvent);
    }

    public void setTxError(TxErrorEvent txErrorEvent) {
        pjsua2JNI.SipEventBody_txError_set(this.swigCPtr, this, TxErrorEvent.getCPtr(txErrorEvent), txErrorEvent);
    }

    public void setTxMsg(TxMsgEvent txMsgEvent) {
        pjsua2JNI.SipEventBody_txMsg_set(this.swigCPtr, this, TxMsgEvent.getCPtr(txMsgEvent), txMsgEvent);
    }

    public void setUser(UserEvent userEvent) {
        pjsua2JNI.SipEventBody_user_set(this.swigCPtr, this, UserEvent.getCPtr(userEvent), userEvent);
    }

    public SipEventBody() {
        this(pjsua2JNI.new_SipEventBody(), true);
    }
}
