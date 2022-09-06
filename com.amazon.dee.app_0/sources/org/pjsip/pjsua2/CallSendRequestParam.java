package org.pjsip.pjsua2;
/* loaded from: classes5.dex */
public class CallSendRequestParam {
    protected transient boolean swigCMemOwn;
    private transient long swigCPtr;

    protected CallSendRequestParam(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static long getCPtr(CallSendRequestParam callSendRequestParam) {
        if (callSendRequestParam == null) {
            return 0L;
        }
        return callSendRequestParam.swigCPtr;
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                pjsua2JNI.delete_CallSendRequestParam(this.swigCPtr);
            }
            this.swigCPtr = 0L;
        }
    }

    protected void finalize() {
        delete();
    }

    public String getMethod() {
        return pjsua2JNI.CallSendRequestParam_method_get(this.swigCPtr, this);
    }

    public SipTxOption getTxOption() {
        long CallSendRequestParam_txOption_get = pjsua2JNI.CallSendRequestParam_txOption_get(this.swigCPtr, this);
        if (CallSendRequestParam_txOption_get == 0) {
            return null;
        }
        return new SipTxOption(CallSendRequestParam_txOption_get, false);
    }

    public void setMethod(String str) {
        pjsua2JNI.CallSendRequestParam_method_set(this.swigCPtr, this, str);
    }

    public void setTxOption(SipTxOption sipTxOption) {
        pjsua2JNI.CallSendRequestParam_txOption_set(this.swigCPtr, this, SipTxOption.getCPtr(sipTxOption), sipTxOption);
    }

    public CallSendRequestParam() {
        this(pjsua2JNI.new_CallSendRequestParam(), true);
    }
}
