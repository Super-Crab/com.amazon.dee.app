package org.pjsip.pjsua2;
/* loaded from: classes5.dex */
public class SipStatusLine {
    protected transient boolean swigCMemOwn;
    private transient long swigCPtr;

    /* JADX INFO: Access modifiers changed from: protected */
    public SipStatusLine(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static long getCPtr(SipStatusLine sipStatusLine) {
        if (sipStatusLine == null) {
            return 0L;
        }
        return sipStatusLine.swigCPtr;
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                pjsua2JNI.delete_SipStatusLine(this.swigCPtr);
            }
            this.swigCPtr = 0L;
        }
    }

    protected void finalize() {
        delete();
    }

    public int getCode() {
        return pjsua2JNI.SipStatusLine_code_get(this.swigCPtr, this);
    }

    public SWIGTYPE_p_void getPjStatus() {
        long SipStatusLine_pjStatus_get = pjsua2JNI.SipStatusLine_pjStatus_get(this.swigCPtr, this);
        if (SipStatusLine_pjStatus_get == 0) {
            return null;
        }
        return new SWIGTYPE_p_void(SipStatusLine_pjStatus_get, false);
    }

    public String getReason() {
        return pjsua2JNI.SipStatusLine_reason_get(this.swigCPtr, this);
    }

    public void setCode(int i) {
        pjsua2JNI.SipStatusLine_code_set(this.swigCPtr, this, i);
    }

    public void setPjStatus(SWIGTYPE_p_void sWIGTYPE_p_void) {
        pjsua2JNI.SipStatusLine_pjStatus_set(this.swigCPtr, this, SWIGTYPE_p_void.getCPtr(sWIGTYPE_p_void));
    }

    public void setReason(String str) {
        pjsua2JNI.SipStatusLine_reason_set(this.swigCPtr, this, str);
    }

    public SipStatusLine() {
        this(pjsua2JNI.new_SipStatusLine(), true);
    }
}
