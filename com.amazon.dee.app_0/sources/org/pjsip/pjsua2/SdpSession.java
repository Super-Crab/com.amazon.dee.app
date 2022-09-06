package org.pjsip.pjsua2;
/* loaded from: classes5.dex */
public class SdpSession {
    protected transient boolean swigCMemOwn;
    private transient long swigCPtr;

    /* JADX INFO: Access modifiers changed from: protected */
    public SdpSession(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static long getCPtr(SdpSession sdpSession) {
        if (sdpSession == null) {
            return 0L;
        }
        return sdpSession.swigCPtr;
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                pjsua2JNI.delete_SdpSession(this.swigCPtr);
            }
            this.swigCPtr = 0L;
        }
    }

    protected void finalize() {
        delete();
    }

    public SWIGTYPE_p_void getPjSdpSession() {
        long SdpSession_pjSdpSession_get = pjsua2JNI.SdpSession_pjSdpSession_get(this.swigCPtr, this);
        if (SdpSession_pjSdpSession_get == 0) {
            return null;
        }
        return new SWIGTYPE_p_void(SdpSession_pjSdpSession_get, false);
    }

    public String getWholeSdp() {
        return pjsua2JNI.SdpSession_wholeSdp_get(this.swigCPtr, this);
    }

    public void setPjSdpSession(SWIGTYPE_p_void sWIGTYPE_p_void) {
        pjsua2JNI.SdpSession_pjSdpSession_set(this.swigCPtr, this, SWIGTYPE_p_void.getCPtr(sWIGTYPE_p_void));
    }

    public void setWholeSdp(String str) {
        pjsua2JNI.SdpSession_wholeSdp_set(this.swigCPtr, this, str);
    }

    public SdpSession() {
        this(pjsua2JNI.new_SdpSession(), true);
    }
}
