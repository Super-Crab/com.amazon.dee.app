package org.pjsip.pjsua2;
/* loaded from: classes5.dex */
public class SipMultipartPart {
    protected transient boolean swigCMemOwn;
    private transient long swigCPtr;

    /* JADX INFO: Access modifiers changed from: protected */
    public SipMultipartPart(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static long getCPtr(SipMultipartPart sipMultipartPart) {
        if (sipMultipartPart == null) {
            return 0L;
        }
        return sipMultipartPart.swigCPtr;
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                pjsua2JNI.delete_SipMultipartPart(this.swigCPtr);
            }
            this.swigCPtr = 0L;
        }
    }

    protected void finalize() {
        delete();
    }

    public String getBody() {
        return pjsua2JNI.SipMultipartPart_body_get(this.swigCPtr, this);
    }

    public SipMediaType getContentType() {
        long SipMultipartPart_contentType_get = pjsua2JNI.SipMultipartPart_contentType_get(this.swigCPtr, this);
        if (SipMultipartPart_contentType_get == 0) {
            return null;
        }
        return new SipMediaType(SipMultipartPart_contentType_get, false);
    }

    public SipHeaderVector getHeaders() {
        long SipMultipartPart_headers_get = pjsua2JNI.SipMultipartPart_headers_get(this.swigCPtr, this);
        if (SipMultipartPart_headers_get == 0) {
            return null;
        }
        return new SipHeaderVector(SipMultipartPart_headers_get, false);
    }

    public void setBody(String str) {
        pjsua2JNI.SipMultipartPart_body_set(this.swigCPtr, this, str);
    }

    public void setContentType(SipMediaType sipMediaType) {
        pjsua2JNI.SipMultipartPart_contentType_set(this.swigCPtr, this, SipMediaType.getCPtr(sipMediaType), sipMediaType);
    }

    public void setHeaders(SipHeaderVector sipHeaderVector) {
        pjsua2JNI.SipMultipartPart_headers_set(this.swigCPtr, this, SipHeaderVector.getCPtr(sipHeaderVector), sipHeaderVector);
    }

    public SipMultipartPart() {
        this(pjsua2JNI.new_SipMultipartPart(), true);
    }
}
