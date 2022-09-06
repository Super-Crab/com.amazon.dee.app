package org.pjsip.pjsua2;
/* loaded from: classes5.dex */
public class SipHeader {
    protected transient boolean swigCMemOwn;
    private transient long swigCPtr;

    /* JADX INFO: Access modifiers changed from: protected */
    public SipHeader(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static long getCPtr(SipHeader sipHeader) {
        if (sipHeader == null) {
            return 0L;
        }
        return sipHeader.swigCPtr;
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                pjsua2JNI.delete_SipHeader(this.swigCPtr);
            }
            this.swigCPtr = 0L;
        }
    }

    protected void finalize() {
        delete();
    }

    public String getHName() {
        return pjsua2JNI.SipHeader_hName_get(this.swigCPtr, this);
    }

    public String getHValue() {
        return pjsua2JNI.SipHeader_hValue_get(this.swigCPtr, this);
    }

    public void setHName(String str) {
        pjsua2JNI.SipHeader_hName_set(this.swigCPtr, this, str);
    }

    public void setHValue(String str) {
        pjsua2JNI.SipHeader_hValue_set(this.swigCPtr, this, str);
    }

    public SipHeader() {
        this(pjsua2JNI.new_SipHeader(), true);
    }
}
