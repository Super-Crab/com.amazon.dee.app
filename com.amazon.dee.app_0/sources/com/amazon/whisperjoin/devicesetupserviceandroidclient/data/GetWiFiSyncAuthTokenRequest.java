package com.amazon.whisperjoin.devicesetupserviceandroidclient.data;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
/* loaded from: classes13.dex */
public class GetWiFiSyncAuthTokenRequest {
    private final boolean mIsCertificateChainPresent;
    private final String mPemCertificate;
    private final String mPublicKey;
    private final String mSignature;
    private final long mTimestamp;

    public GetWiFiSyncAuthTokenRequest(String str, String str2, boolean z, long j, String str3) {
        this.mPemCertificate = str;
        this.mPublicKey = str2;
        this.mIsCertificateChainPresent = z;
        this.mTimestamp = j;
        this.mSignature = str3;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || GetWiFiSyncAuthTokenRequest.class != obj.getClass()) {
            return false;
        }
        GetWiFiSyncAuthTokenRequest getWiFiSyncAuthTokenRequest = (GetWiFiSyncAuthTokenRequest) obj;
        return this.mPemCertificate.equals(getWiFiSyncAuthTokenRequest.mPemCertificate) && this.mPublicKey.equals(getWiFiSyncAuthTokenRequest.mPublicKey) && this.mIsCertificateChainPresent == getWiFiSyncAuthTokenRequest.mIsCertificateChainPresent && this.mTimestamp == getWiFiSyncAuthTokenRequest.mTimestamp && this.mSignature.equals(getWiFiSyncAuthTokenRequest.mSignature);
    }

    public boolean getIsCertificateChainPresent() {
        return this.mIsCertificateChainPresent;
    }

    public String getPemCertificate() {
        return this.mPemCertificate;
    }

    public String getPublicKey() {
        return this.mPublicKey;
    }

    public String getSignature() {
        return this.mSignature;
    }

    public long getTimestamp() {
        return this.mTimestamp;
    }

    public int hashCode() {
        return Objects.hashCode(this.mPemCertificate, this.mPublicKey, Boolean.valueOf(this.mIsCertificateChainPresent), Long.valueOf(this.mTimestamp), this.mSignature);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("mPemCertificate", this.mPemCertificate).add("mPublicKey", this.mPublicKey).add("mIsCertificateChainPresent", this.mIsCertificateChainPresent).add("mTimestamp", this.mTimestamp).add("mSignature", this.mSignature).toString();
    }
}
