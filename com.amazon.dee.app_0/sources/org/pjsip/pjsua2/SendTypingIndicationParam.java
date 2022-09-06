package org.pjsip.pjsua2;
/* loaded from: classes5.dex */
public class SendTypingIndicationParam {
    protected transient boolean swigCMemOwn;
    private transient long swigCPtr;

    protected SendTypingIndicationParam(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static long getCPtr(SendTypingIndicationParam sendTypingIndicationParam) {
        if (sendTypingIndicationParam == null) {
            return 0L;
        }
        return sendTypingIndicationParam.swigCPtr;
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                pjsua2JNI.delete_SendTypingIndicationParam(this.swigCPtr);
            }
            this.swigCPtr = 0L;
        }
    }

    protected void finalize() {
        delete();
    }

    public boolean getIsTyping() {
        return pjsua2JNI.SendTypingIndicationParam_isTyping_get(this.swigCPtr, this);
    }

    public SipTxOption getTxOption() {
        long SendTypingIndicationParam_txOption_get = pjsua2JNI.SendTypingIndicationParam_txOption_get(this.swigCPtr, this);
        if (SendTypingIndicationParam_txOption_get == 0) {
            return null;
        }
        return new SipTxOption(SendTypingIndicationParam_txOption_get, false);
    }

    public void setIsTyping(boolean z) {
        pjsua2JNI.SendTypingIndicationParam_isTyping_set(this.swigCPtr, this, z);
    }

    public void setTxOption(SipTxOption sipTxOption) {
        pjsua2JNI.SendTypingIndicationParam_txOption_set(this.swigCPtr, this, SipTxOption.getCPtr(sipTxOption), sipTxOption);
    }

    public SendTypingIndicationParam() {
        this(pjsua2JNI.new_SendTypingIndicationParam(), true);
    }
}
