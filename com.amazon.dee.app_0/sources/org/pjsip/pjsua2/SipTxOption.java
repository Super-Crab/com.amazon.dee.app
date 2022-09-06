package org.pjsip.pjsua2;
/* loaded from: classes5.dex */
public class SipTxOption {
    protected transient boolean swigCMemOwn;
    private transient long swigCPtr;

    /* JADX INFO: Access modifiers changed from: protected */
    public SipTxOption(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static long getCPtr(SipTxOption sipTxOption) {
        if (sipTxOption == null) {
            return 0L;
        }
        return sipTxOption.swigCPtr;
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                pjsua2JNI.delete_SipTxOption(this.swigCPtr);
            }
            this.swigCPtr = 0L;
        }
    }

    protected void finalize() {
        delete();
    }

    public String getContentType() {
        return pjsua2JNI.SipTxOption_contentType_get(this.swigCPtr, this);
    }

    public SipHeaderVector getHeaders() {
        long SipTxOption_headers_get = pjsua2JNI.SipTxOption_headers_get(this.swigCPtr, this);
        if (SipTxOption_headers_get == 0) {
            return null;
        }
        return new SipHeaderVector(SipTxOption_headers_get, false);
    }

    public String getMsgBody() {
        return pjsua2JNI.SipTxOption_msgBody_get(this.swigCPtr, this);
    }

    public SipMediaType getMultipartContentType() {
        long SipTxOption_multipartContentType_get = pjsua2JNI.SipTxOption_multipartContentType_get(this.swigCPtr, this);
        if (SipTxOption_multipartContentType_get == 0) {
            return null;
        }
        return new SipMediaType(SipTxOption_multipartContentType_get, false);
    }

    public SipMultipartPartVector getMultipartParts() {
        long SipTxOption_multipartParts_get = pjsua2JNI.SipTxOption_multipartParts_get(this.swigCPtr, this);
        if (SipTxOption_multipartParts_get == 0) {
            return null;
        }
        return new SipMultipartPartVector(SipTxOption_multipartParts_get, false);
    }

    public String getTargetUri() {
        return pjsua2JNI.SipTxOption_targetUri_get(this.swigCPtr, this);
    }

    public boolean isEmpty() {
        return pjsua2JNI.SipTxOption_isEmpty(this.swigCPtr, this);
    }

    public void setContentType(String str) {
        pjsua2JNI.SipTxOption_contentType_set(this.swigCPtr, this, str);
    }

    public void setHeaders(SipHeaderVector sipHeaderVector) {
        pjsua2JNI.SipTxOption_headers_set(this.swigCPtr, this, SipHeaderVector.getCPtr(sipHeaderVector), sipHeaderVector);
    }

    public void setMsgBody(String str) {
        pjsua2JNI.SipTxOption_msgBody_set(this.swigCPtr, this, str);
    }

    public void setMultipartContentType(SipMediaType sipMediaType) {
        pjsua2JNI.SipTxOption_multipartContentType_set(this.swigCPtr, this, SipMediaType.getCPtr(sipMediaType), sipMediaType);
    }

    public void setMultipartParts(SipMultipartPartVector sipMultipartPartVector) {
        pjsua2JNI.SipTxOption_multipartParts_set(this.swigCPtr, this, SipMultipartPartVector.getCPtr(sipMultipartPartVector), sipMultipartPartVector);
    }

    public void setTargetUri(String str) {
        pjsua2JNI.SipTxOption_targetUri_set(this.swigCPtr, this, str);
    }

    public SipTxOption() {
        this(pjsua2JNI.new_SipTxOption(), true);
    }
}
