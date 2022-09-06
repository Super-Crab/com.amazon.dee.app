package org.pjsip.pjsua2;
/* loaded from: classes5.dex */
public class SipMsg {
    protected transient boolean swigCMemOwn;
    private transient long swigCPtr;

    /* JADX INFO: Access modifiers changed from: protected */
    public SipMsg(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static long getCPtr(SipMsg sipMsg) {
        if (sipMsg == null) {
            return 0L;
        }
        return sipMsg.swigCPtr;
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                pjsua2JNI.delete_SipMsg(this.swigCPtr);
            }
            this.swigCPtr = 0L;
        }
    }

    protected void finalize() {
        delete();
    }

    public String getContentType() {
        return pjsua2JNI.SipMsg_contentType_get(this.swigCPtr, this);
    }

    public SipHeaderVector getHeaders() {
        long SipMsg_headers_get = pjsua2JNI.SipMsg_headers_get(this.swigCPtr, this);
        if (SipMsg_headers_get == 0) {
            return null;
        }
        return new SipHeaderVector(SipMsg_headers_get, false);
    }

    public SWIGTYPE_p_void getPjMsg() {
        long SipMsg_pjMsg_get = pjsua2JNI.SipMsg_pjMsg_get(this.swigCPtr, this);
        if (SipMsg_pjMsg_get == 0) {
            return null;
        }
        return new SWIGTYPE_p_void(SipMsg_pjMsg_get, false);
    }

    public SipRequestLine getRequest() {
        long SipMsg_request_get = pjsua2JNI.SipMsg_request_get(this.swigCPtr, this);
        if (SipMsg_request_get == 0) {
            return null;
        }
        return new SipRequestLine(SipMsg_request_get, false);
    }

    public SipStatusLine getStatus() {
        long SipMsg_status_get = pjsua2JNI.SipMsg_status_get(this.swigCPtr, this);
        if (SipMsg_status_get == 0) {
            return null;
        }
        return new SipStatusLine(SipMsg_status_get, false);
    }

    public pjsip_msg_type_e getType() {
        return pjsip_msg_type_e.swigToEnum(pjsua2JNI.SipMsg_type_get(this.swigCPtr, this));
    }

    public void setContentType(String str) {
        pjsua2JNI.SipMsg_contentType_set(this.swigCPtr, this, str);
    }

    public void setHeaders(SipHeaderVector sipHeaderVector) {
        pjsua2JNI.SipMsg_headers_set(this.swigCPtr, this, SipHeaderVector.getCPtr(sipHeaderVector), sipHeaderVector);
    }

    public void setPjMsg(SWIGTYPE_p_void sWIGTYPE_p_void) {
        pjsua2JNI.SipMsg_pjMsg_set(this.swigCPtr, this, SWIGTYPE_p_void.getCPtr(sWIGTYPE_p_void));
    }

    public void setRequest(SipRequestLine sipRequestLine) {
        pjsua2JNI.SipMsg_request_set(this.swigCPtr, this, SipRequestLine.getCPtr(sipRequestLine), sipRequestLine);
    }

    public void setStatus(SipStatusLine sipStatusLine) {
        pjsua2JNI.SipMsg_status_set(this.swigCPtr, this, SipStatusLine.getCPtr(sipStatusLine), sipStatusLine);
    }

    public void setType(pjsip_msg_type_e pjsip_msg_type_eVar) {
        pjsua2JNI.SipMsg_type_set(this.swigCPtr, this, pjsip_msg_type_eVar.swigValue());
    }

    public SipMsg() {
        this(pjsua2JNI.new_SipMsg(), true);
    }
}
