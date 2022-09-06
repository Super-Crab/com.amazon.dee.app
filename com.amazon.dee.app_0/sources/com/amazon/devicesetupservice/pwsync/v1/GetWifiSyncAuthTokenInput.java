package com.amazon.devicesetupservice.pwsync.v1;

import com.amazon.CoralAndroidClient.ClientBase.ClientInput;
import com.amazon.CoralAndroidClient.ClientBase.ClientOutput;
import com.amazon.CoralAndroidClient.ClientBase.Helper;
import com.amazon.devicesetupservice.v1.AuthenticatedInput;
/* loaded from: classes12.dex */
public class GetWifiSyncAuthTokenInput extends AuthenticatedInput implements ClientOutput, ClientInput {
    private static final int classNameHashCode = Helper.hash("com.amazon.devicesetupservice.pwsync.v1.GetWifiSyncAuthTokenInput");
    private String certificate;
    private Boolean isCertificateChainPresent;
    private String publicKey;
    private String signature;
    private long timestamp;

    @Override // com.amazon.devicesetupservice.v1.AuthenticatedInput
    public boolean equals(Object obj) {
        if (!(obj instanceof GetWifiSyncAuthTokenInput)) {
            return false;
        }
        GetWifiSyncAuthTokenInput getWifiSyncAuthTokenInput = (GetWifiSyncAuthTokenInput) obj;
        return super.equals(obj) && Helper.equals(this.publicKey, getWifiSyncAuthTokenInput.publicKey) && Helper.equals(Long.valueOf(this.timestamp), Long.valueOf(getWifiSyncAuthTokenInput.timestamp)) && Helper.equals(this.isCertificateChainPresent, getWifiSyncAuthTokenInput.isCertificateChainPresent) && Helper.equals(this.signature, getWifiSyncAuthTokenInput.signature) && Helper.equals(this.certificate, getWifiSyncAuthTokenInput.certificate);
    }

    public String getCertificate() {
        return this.certificate;
    }

    public String getPublicKey() {
        return this.publicKey;
    }

    public String getSignature() {
        return this.signature;
    }

    public long getTimestamp() {
        return this.timestamp;
    }

    @Override // com.amazon.devicesetupservice.v1.AuthenticatedInput
    public int hashCode() {
        return Helper.hash(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), this.publicKey, Long.valueOf(this.timestamp), this.isCertificateChainPresent, this.signature, this.certificate);
    }

    public Boolean isIsCertificateChainPresent() {
        return this.isCertificateChainPresent;
    }

    public void setCertificate(String str) {
        this.certificate = str;
    }

    public void setIsCertificateChainPresent(Boolean bool) {
        this.isCertificateChainPresent = bool;
    }

    public void setPublicKey(String str) {
        this.publicKey = str;
    }

    public void setSignature(String str) {
        this.signature = str;
    }

    public void setTimestamp(long j) {
        this.timestamp = j;
    }
}
