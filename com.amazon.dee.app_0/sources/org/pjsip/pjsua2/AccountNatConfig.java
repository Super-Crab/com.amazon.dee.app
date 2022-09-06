package org.pjsip.pjsua2;
/* loaded from: classes5.dex */
public class AccountNatConfig extends PersistentObject {
    private transient long swigCPtr;

    /* JADX INFO: Access modifiers changed from: protected */
    public AccountNatConfig(long j, boolean z) {
        super(pjsua2JNI.AccountNatConfig_SWIGUpcast(j), z);
        this.swigCPtr = j;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static long getCPtr(AccountNatConfig accountNatConfig) {
        if (accountNatConfig == null) {
            return 0L;
        }
        return accountNatConfig.swigCPtr;
    }

    @Override // org.pjsip.pjsua2.PersistentObject
    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                pjsua2JNI.delete_AccountNatConfig(this.swigCPtr);
            }
            this.swigCPtr = 0L;
        }
        super.delete();
    }

    @Override // org.pjsip.pjsua2.PersistentObject
    protected void finalize() {
        delete();
    }

    public int getContactRewriteMethod() {
        return pjsua2JNI.AccountNatConfig_contactRewriteMethod_get(this.swigCPtr, this);
    }

    public int getContactRewriteUse() {
        return pjsua2JNI.AccountNatConfig_contactRewriteUse_get(this.swigCPtr, this);
    }

    public int getContactUseSrcPort() {
        return pjsua2JNI.AccountNatConfig_contactUseSrcPort_get(this.swigCPtr, this);
    }

    public boolean getIceAggressiveNomination() {
        return pjsua2JNI.AccountNatConfig_iceAggressiveNomination_get(this.swigCPtr, this);
    }

    public boolean getIceAlwaysUpdate() {
        return pjsua2JNI.AccountNatConfig_iceAlwaysUpdate_get(this.swigCPtr, this);
    }

    public boolean getIceEnabled() {
        return pjsua2JNI.AccountNatConfig_iceEnabled_get(this.swigCPtr, this);
    }

    public int getIceMaxHostCands() {
        return pjsua2JNI.AccountNatConfig_iceMaxHostCands_get(this.swigCPtr, this);
    }

    public boolean getIceNoRtcp() {
        return pjsua2JNI.AccountNatConfig_iceNoRtcp_get(this.swigCPtr, this);
    }

    public long getIceNominatedCheckDelayMsec() {
        return pjsua2JNI.AccountNatConfig_iceNominatedCheckDelayMsec_get(this.swigCPtr, this);
    }

    public int getIceWaitNominationTimeoutMsec() {
        return pjsua2JNI.AccountNatConfig_iceWaitNominationTimeoutMsec_get(this.swigCPtr, this);
    }

    public pjsua_stun_use getMediaStunUse() {
        return pjsua_stun_use.swigToEnum(pjsua2JNI.AccountNatConfig_mediaStunUse_get(this.swigCPtr, this));
    }

    public int getSdpNatRewriteUse() {
        return pjsua2JNI.AccountNatConfig_sdpNatRewriteUse_get(this.swigCPtr, this);
    }

    public String getSipOutboundInstanceId() {
        return pjsua2JNI.AccountNatConfig_sipOutboundInstanceId_get(this.swigCPtr, this);
    }

    public String getSipOutboundRegId() {
        return pjsua2JNI.AccountNatConfig_sipOutboundRegId_get(this.swigCPtr, this);
    }

    public int getSipOutboundUse() {
        return pjsua2JNI.AccountNatConfig_sipOutboundUse_get(this.swigCPtr, this);
    }

    public pjsua_stun_use getSipStunUse() {
        return pjsua_stun_use.swigToEnum(pjsua2JNI.AccountNatConfig_sipStunUse_get(this.swigCPtr, this));
    }

    public pj_turn_tp_type getTurnConnType() {
        return pj_turn_tp_type.swigToEnum(pjsua2JNI.AccountNatConfig_turnConnType_get(this.swigCPtr, this));
    }

    public boolean getTurnEnabled() {
        return pjsua2JNI.AccountNatConfig_turnEnabled_get(this.swigCPtr, this);
    }

    public String getTurnPassword() {
        return pjsua2JNI.AccountNatConfig_turnPassword_get(this.swigCPtr, this);
    }

    public int getTurnPasswordType() {
        return pjsua2JNI.AccountNatConfig_turnPasswordType_get(this.swigCPtr, this);
    }

    public String getTurnServer() {
        return pjsua2JNI.AccountNatConfig_turnServer_get(this.swigCPtr, this);
    }

    public String getTurnUserName() {
        return pjsua2JNI.AccountNatConfig_turnUserName_get(this.swigCPtr, this);
    }

    public String getUdpKaData() {
        return pjsua2JNI.AccountNatConfig_udpKaData_get(this.swigCPtr, this);
    }

    public long getUdpKaIntervalSec() {
        return pjsua2JNI.AccountNatConfig_udpKaIntervalSec_get(this.swigCPtr, this);
    }

    public int getViaRewriteUse() {
        return pjsua2JNI.AccountNatConfig_viaRewriteUse_get(this.swigCPtr, this);
    }

    @Override // org.pjsip.pjsua2.PersistentObject
    public void readObject(ContainerNode containerNode) throws Exception {
        pjsua2JNI.AccountNatConfig_readObject(this.swigCPtr, this, ContainerNode.getCPtr(containerNode), containerNode);
    }

    public void setContactRewriteMethod(int i) {
        pjsua2JNI.AccountNatConfig_contactRewriteMethod_set(this.swigCPtr, this, i);
    }

    public void setContactRewriteUse(int i) {
        pjsua2JNI.AccountNatConfig_contactRewriteUse_set(this.swigCPtr, this, i);
    }

    public void setContactUseSrcPort(int i) {
        pjsua2JNI.AccountNatConfig_contactUseSrcPort_set(this.swigCPtr, this, i);
    }

    public void setIceAggressiveNomination(boolean z) {
        pjsua2JNI.AccountNatConfig_iceAggressiveNomination_set(this.swigCPtr, this, z);
    }

    public void setIceAlwaysUpdate(boolean z) {
        pjsua2JNI.AccountNatConfig_iceAlwaysUpdate_set(this.swigCPtr, this, z);
    }

    public void setIceEnabled(boolean z) {
        pjsua2JNI.AccountNatConfig_iceEnabled_set(this.swigCPtr, this, z);
    }

    public void setIceMaxHostCands(int i) {
        pjsua2JNI.AccountNatConfig_iceMaxHostCands_set(this.swigCPtr, this, i);
    }

    public void setIceNoRtcp(boolean z) {
        pjsua2JNI.AccountNatConfig_iceNoRtcp_set(this.swigCPtr, this, z);
    }

    public void setIceNominatedCheckDelayMsec(long j) {
        pjsua2JNI.AccountNatConfig_iceNominatedCheckDelayMsec_set(this.swigCPtr, this, j);
    }

    public void setIceWaitNominationTimeoutMsec(int i) {
        pjsua2JNI.AccountNatConfig_iceWaitNominationTimeoutMsec_set(this.swigCPtr, this, i);
    }

    public void setMediaStunUse(pjsua_stun_use pjsua_stun_useVar) {
        pjsua2JNI.AccountNatConfig_mediaStunUse_set(this.swigCPtr, this, pjsua_stun_useVar.swigValue());
    }

    public void setSdpNatRewriteUse(int i) {
        pjsua2JNI.AccountNatConfig_sdpNatRewriteUse_set(this.swigCPtr, this, i);
    }

    public void setSipOutboundInstanceId(String str) {
        pjsua2JNI.AccountNatConfig_sipOutboundInstanceId_set(this.swigCPtr, this, str);
    }

    public void setSipOutboundRegId(String str) {
        pjsua2JNI.AccountNatConfig_sipOutboundRegId_set(this.swigCPtr, this, str);
    }

    public void setSipOutboundUse(int i) {
        pjsua2JNI.AccountNatConfig_sipOutboundUse_set(this.swigCPtr, this, i);
    }

    public void setSipStunUse(pjsua_stun_use pjsua_stun_useVar) {
        pjsua2JNI.AccountNatConfig_sipStunUse_set(this.swigCPtr, this, pjsua_stun_useVar.swigValue());
    }

    public void setTurnConnType(pj_turn_tp_type pj_turn_tp_typeVar) {
        pjsua2JNI.AccountNatConfig_turnConnType_set(this.swigCPtr, this, pj_turn_tp_typeVar.swigValue());
    }

    public void setTurnEnabled(boolean z) {
        pjsua2JNI.AccountNatConfig_turnEnabled_set(this.swigCPtr, this, z);
    }

    public void setTurnPassword(String str) {
        pjsua2JNI.AccountNatConfig_turnPassword_set(this.swigCPtr, this, str);
    }

    public void setTurnPasswordType(int i) {
        pjsua2JNI.AccountNatConfig_turnPasswordType_set(this.swigCPtr, this, i);
    }

    public void setTurnServer(String str) {
        pjsua2JNI.AccountNatConfig_turnServer_set(this.swigCPtr, this, str);
    }

    public void setTurnUserName(String str) {
        pjsua2JNI.AccountNatConfig_turnUserName_set(this.swigCPtr, this, str);
    }

    public void setUdpKaData(String str) {
        pjsua2JNI.AccountNatConfig_udpKaData_set(this.swigCPtr, this, str);
    }

    public void setUdpKaIntervalSec(long j) {
        pjsua2JNI.AccountNatConfig_udpKaIntervalSec_set(this.swigCPtr, this, j);
    }

    public void setViaRewriteUse(int i) {
        pjsua2JNI.AccountNatConfig_viaRewriteUse_set(this.swigCPtr, this, i);
    }

    @Override // org.pjsip.pjsua2.PersistentObject
    public void writeObject(ContainerNode containerNode) throws Exception {
        pjsua2JNI.AccountNatConfig_writeObject(this.swigCPtr, this, ContainerNode.getCPtr(containerNode), containerNode);
    }

    public AccountNatConfig() {
        this(pjsua2JNI.new_AccountNatConfig(), true);
    }
}
