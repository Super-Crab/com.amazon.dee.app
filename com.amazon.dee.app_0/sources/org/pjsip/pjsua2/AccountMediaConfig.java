package org.pjsip.pjsua2;
/* loaded from: classes5.dex */
public class AccountMediaConfig extends PersistentObject {
    private transient long swigCPtr;

    /* JADX INFO: Access modifiers changed from: protected */
    public AccountMediaConfig(long j, boolean z) {
        super(pjsua2JNI.AccountMediaConfig_SWIGUpcast(j), z);
        this.swigCPtr = j;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static long getCPtr(AccountMediaConfig accountMediaConfig) {
        if (accountMediaConfig == null) {
            return 0L;
        }
        return accountMediaConfig.swigCPtr;
    }

    @Override // org.pjsip.pjsua2.PersistentObject
    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                pjsua2JNI.delete_AccountMediaConfig(this.swigCPtr);
            }
            this.swigCPtr = 0L;
        }
        super.delete();
    }

    @Override // org.pjsip.pjsua2.PersistentObject
    protected void finalize() {
        delete();
    }

    public pjsua_ipv6_use getIpv6Use() {
        return pjsua_ipv6_use.swigToEnum(pjsua2JNI.AccountMediaConfig_ipv6Use_get(this.swigCPtr, this));
    }

    public boolean getLockCodecEnabled() {
        return pjsua2JNI.AccountMediaConfig_lockCodecEnabled_get(this.swigCPtr, this);
    }

    public int getSrtpSecureSignaling() {
        return pjsua2JNI.AccountMediaConfig_srtpSecureSignaling_get(this.swigCPtr, this);
    }

    public pjmedia_srtp_use getSrtpUse() {
        return pjmedia_srtp_use.swigToEnum(pjsua2JNI.AccountMediaConfig_srtpUse_get(this.swigCPtr, this));
    }

    public boolean getStreamKaEnabled() {
        return pjsua2JNI.AccountMediaConfig_streamKaEnabled_get(this.swigCPtr, this);
    }

    public TransportConfig getTransportConfig() {
        long AccountMediaConfig_transportConfig_get = pjsua2JNI.AccountMediaConfig_transportConfig_get(this.swigCPtr, this);
        if (AccountMediaConfig_transportConfig_get == 0) {
            return null;
        }
        return new TransportConfig(AccountMediaConfig_transportConfig_get, false);
    }

    @Override // org.pjsip.pjsua2.PersistentObject
    public void readObject(ContainerNode containerNode) throws Exception {
        pjsua2JNI.AccountMediaConfig_readObject(this.swigCPtr, this, ContainerNode.getCPtr(containerNode), containerNode);
    }

    public void setIpv6Use(pjsua_ipv6_use pjsua_ipv6_useVar) {
        pjsua2JNI.AccountMediaConfig_ipv6Use_set(this.swigCPtr, this, pjsua_ipv6_useVar.swigValue());
    }

    public void setLockCodecEnabled(boolean z) {
        pjsua2JNI.AccountMediaConfig_lockCodecEnabled_set(this.swigCPtr, this, z);
    }

    public void setSrtpSecureSignaling(int i) {
        pjsua2JNI.AccountMediaConfig_srtpSecureSignaling_set(this.swigCPtr, this, i);
    }

    public void setSrtpUse(pjmedia_srtp_use pjmedia_srtp_useVar) {
        pjsua2JNI.AccountMediaConfig_srtpUse_set(this.swigCPtr, this, pjmedia_srtp_useVar.swigValue());
    }

    public void setStreamKaEnabled(boolean z) {
        pjsua2JNI.AccountMediaConfig_streamKaEnabled_set(this.swigCPtr, this, z);
    }

    public void setTransportConfig(TransportConfig transportConfig) {
        pjsua2JNI.AccountMediaConfig_transportConfig_set(this.swigCPtr, this, TransportConfig.getCPtr(transportConfig), transportConfig);
    }

    @Override // org.pjsip.pjsua2.PersistentObject
    public void writeObject(ContainerNode containerNode) throws Exception {
        pjsua2JNI.AccountMediaConfig_writeObject(this.swigCPtr, this, ContainerNode.getCPtr(containerNode), containerNode);
    }

    public AccountMediaConfig() {
        this(pjsua2JNI.new_AccountMediaConfig(), true);
    }
}
