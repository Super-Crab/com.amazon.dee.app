package org.pjsip.pjsua2;
/* loaded from: classes5.dex */
public class OnDtmfDigitParam {
    protected transient boolean swigCMemOwn;
    private transient long swigCPtr;

    /* JADX INFO: Access modifiers changed from: protected */
    public OnDtmfDigitParam(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static long getCPtr(OnDtmfDigitParam onDtmfDigitParam) {
        if (onDtmfDigitParam == null) {
            return 0L;
        }
        return onDtmfDigitParam.swigCPtr;
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                pjsua2JNI.delete_OnDtmfDigitParam(this.swigCPtr);
            }
            this.swigCPtr = 0L;
        }
    }

    protected void finalize() {
        delete();
    }

    public String getDigit() {
        return pjsua2JNI.OnDtmfDigitParam_digit_get(this.swigCPtr, this);
    }

    public void setDigit(String str) {
        pjsua2JNI.OnDtmfDigitParam_digit_set(this.swigCPtr, this, str);
    }

    public OnDtmfDigitParam() {
        this(pjsua2JNI.new_OnDtmfDigitParam(), true);
    }
}
