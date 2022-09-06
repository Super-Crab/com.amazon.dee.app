package org.pjsip.pjsua2;
/* loaded from: classes5.dex */
public class SipRequestLine {
    protected transient boolean swigCMemOwn;
    private transient long swigCPtr;

    /* JADX INFO: Access modifiers changed from: protected */
    public SipRequestLine(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static long getCPtr(SipRequestLine sipRequestLine) {
        if (sipRequestLine == null) {
            return 0L;
        }
        return sipRequestLine.swigCPtr;
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                pjsua2JNI.delete_SipRequestLine(this.swigCPtr);
            }
            this.swigCPtr = 0L;
        }
    }

    protected void finalize() {
        delete();
    }

    public pjsip_method_e getMethod() {
        return pjsip_method_e.swigToEnum(pjsua2JNI.SipRequestLine_method_get(this.swigCPtr, this));
    }

    public String getName() {
        return pjsua2JNI.SipRequestLine_name_get(this.swigCPtr, this);
    }

    public SWIGTYPE_p_void getPjRequest() {
        long SipRequestLine_pjRequest_get = pjsua2JNI.SipRequestLine_pjRequest_get(this.swigCPtr, this);
        if (SipRequestLine_pjRequest_get == 0) {
            return null;
        }
        return new SWIGTYPE_p_void(SipRequestLine_pjRequest_get, false);
    }

    public void setMethod(pjsip_method_e pjsip_method_eVar) {
        pjsua2JNI.SipRequestLine_method_set(this.swigCPtr, this, pjsip_method_eVar.swigValue());
    }

    public void setName(String str) {
        pjsua2JNI.SipRequestLine_name_set(this.swigCPtr, this, str);
    }

    public void setPjRequest(SWIGTYPE_p_void sWIGTYPE_p_void) {
        pjsua2JNI.SipRequestLine_pjRequest_set(this.swigCPtr, this, SWIGTYPE_p_void.getCPtr(sWIGTYPE_p_void));
    }

    public SipRequestLine() {
        this(pjsua2JNI.new_SipRequestLine(), true);
    }
}
