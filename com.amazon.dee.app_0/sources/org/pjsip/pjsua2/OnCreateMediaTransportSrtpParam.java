package org.pjsip.pjsua2;
/* loaded from: classes5.dex */
public class OnCreateMediaTransportSrtpParam {
    protected transient boolean swigCMemOwn;
    private transient long swigCPtr;

    /* JADX INFO: Access modifiers changed from: protected */
    public OnCreateMediaTransportSrtpParam(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static long getCPtr(OnCreateMediaTransportSrtpParam onCreateMediaTransportSrtpParam) {
        if (onCreateMediaTransportSrtpParam == null) {
            return 0L;
        }
        return onCreateMediaTransportSrtpParam.swigCPtr;
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                pjsua2JNI.delete_OnCreateMediaTransportSrtpParam(this.swigCPtr);
            }
            this.swigCPtr = 0L;
        }
    }

    protected void finalize() {
        delete();
    }

    public SWIGTYPE_p_vectorT_pj__SrtpCrypto_t getCryptos() {
        long OnCreateMediaTransportSrtpParam_cryptos_get = pjsua2JNI.OnCreateMediaTransportSrtpParam_cryptos_get(this.swigCPtr, this);
        if (OnCreateMediaTransportSrtpParam_cryptos_get == 0) {
            return null;
        }
        return new SWIGTYPE_p_vectorT_pj__SrtpCrypto_t(OnCreateMediaTransportSrtpParam_cryptos_get, false);
    }

    public long getMediaIdx() {
        return pjsua2JNI.OnCreateMediaTransportSrtpParam_mediaIdx_get(this.swigCPtr, this);
    }

    public pjmedia_srtp_use getSrtpUse() {
        return pjmedia_srtp_use.swigToEnum(pjsua2JNI.OnCreateMediaTransportSrtpParam_srtpUse_get(this.swigCPtr, this));
    }

    public void setCryptos(SWIGTYPE_p_vectorT_pj__SrtpCrypto_t sWIGTYPE_p_vectorT_pj__SrtpCrypto_t) {
        pjsua2JNI.OnCreateMediaTransportSrtpParam_cryptos_set(this.swigCPtr, this, SWIGTYPE_p_vectorT_pj__SrtpCrypto_t.getCPtr(sWIGTYPE_p_vectorT_pj__SrtpCrypto_t));
    }

    public void setMediaIdx(long j) {
        pjsua2JNI.OnCreateMediaTransportSrtpParam_mediaIdx_set(this.swigCPtr, this, j);
    }

    public void setSrtpUse(pjmedia_srtp_use pjmedia_srtp_useVar) {
        pjsua2JNI.OnCreateMediaTransportSrtpParam_srtpUse_set(this.swigCPtr, this, pjmedia_srtp_useVar.swigValue());
    }

    public OnCreateMediaTransportSrtpParam() {
        this(pjsua2JNI.new_OnCreateMediaTransportSrtpParam(), true);
    }
}
