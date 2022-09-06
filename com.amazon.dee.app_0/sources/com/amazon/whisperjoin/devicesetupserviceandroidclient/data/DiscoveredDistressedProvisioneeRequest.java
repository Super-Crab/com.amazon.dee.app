package com.amazon.whisperjoin.devicesetupserviceandroidclient.data;

import com.amazon.devicesetupservice.v1.ProvisionableInfo;
import com.amazon.devicesetupservice.v1.ProvisionerInfo;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
/* loaded from: classes13.dex */
public class DiscoveredDistressedProvisioneeRequest extends AbstractDiscoveredProvisioneeRequest {
    private final int mAdvertisedSdkVersionIndex;
    private final String mAuthMaterialIndex;
    private final String mConnectivityErrorCode;
    private final String mNonce;
    private final String mProductIndex;
    private final ProvisionableInfo mProvisioneeInfo;
    private final String mProvisioningMethod;
    private final String mRadio;
    private final int mRssi;
    private final String mTrustMethod;

    public DiscoveredDistressedProvisioneeRequest(String str, String str2, String str3, String str4, ProvisionableInfo provisionableInfo, int i, String str5, String str6, String str7, ProvisionerInfo provisionerInfo, int i2) {
        this.mTrustMethod = str;
        this.mProvisioningMethod = str2;
        this.mNonce = str3;
        this.mConnectivityErrorCode = str4;
        this.mProvisioneeInfo = provisionableInfo;
        this.mRssi = i;
        this.mProductIndex = str5;
        this.mAuthMaterialIndex = str6;
        this.mRadio = str7;
        this.mProvisionerInfo = provisionerInfo;
        this.mAdvertisedSdkVersionIndex = i2;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || DiscoveredDistressedProvisioneeRequest.class != obj.getClass()) {
            return false;
        }
        DiscoveredDistressedProvisioneeRequest discoveredDistressedProvisioneeRequest = (DiscoveredDistressedProvisioneeRequest) obj;
        return this.mRssi == discoveredDistressedProvisioneeRequest.mRssi && Objects.equal(this.mTrustMethod, discoveredDistressedProvisioneeRequest.mTrustMethod) && Objects.equal(this.mProvisioningMethod, discoveredDistressedProvisioneeRequest.mProvisioningMethod) && Objects.equal(this.mNonce, discoveredDistressedProvisioneeRequest.mNonce) && Objects.equal(this.mConnectivityErrorCode, discoveredDistressedProvisioneeRequest.mConnectivityErrorCode) && Objects.equal(this.mProvisioneeInfo, discoveredDistressedProvisioneeRequest.mProvisioneeInfo) && Objects.equal(this.mProductIndex, discoveredDistressedProvisioneeRequest.mProductIndex) && Objects.equal(this.mAuthMaterialIndex, discoveredDistressedProvisioneeRequest.mAuthMaterialIndex) && Objects.equal(this.mRadio, discoveredDistressedProvisioneeRequest.mRadio) && Objects.equal(this.mProvisionerInfo, discoveredDistressedProvisioneeRequest.mProvisionerInfo) && Objects.equal(Integer.valueOf(this.mAdvertisedSdkVersionIndex), Integer.valueOf(discoveredDistressedProvisioneeRequest.mAdvertisedSdkVersionIndex));
    }

    public int getAdvertisedSdkVersionIndex() {
        return this.mAdvertisedSdkVersionIndex;
    }

    public String getAuthMaterialIndex() {
        return this.mAuthMaterialIndex;
    }

    public String getConnectivityErrorCode() {
        return this.mConnectivityErrorCode;
    }

    public String getNonce() {
        return this.mNonce;
    }

    public String getProductIndex() {
        return this.mProductIndex;
    }

    public ProvisionableInfo getProvisioneeInfo() {
        return this.mProvisioneeInfo;
    }

    public String getProvisioningMethod() {
        return this.mProvisioningMethod;
    }

    public String getRadio() {
        return this.mRadio;
    }

    public int getRssi() {
        return this.mRssi;
    }

    public String getTrustMethod() {
        return this.mTrustMethod;
    }

    public int hashCode() {
        return Objects.hashCode(this.mTrustMethod, this.mProvisioningMethod, this.mNonce, this.mConnectivityErrorCode, this.mProvisioneeInfo, Integer.valueOf(this.mRssi), this.mProductIndex, this.mAuthMaterialIndex, this.mRadio, this.mProvisionerInfo, Integer.valueOf(this.mAdvertisedSdkVersionIndex));
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("mTrustMethod", this.mTrustMethod).add("mProvisioningMethod", this.mProvisioningMethod).add("mNonce", this.mNonce).add("mConnectivityErrorCode", this.mConnectivityErrorCode).add("mProvisioneeInfo", this.mProvisioneeInfo).add("mRssi", this.mRssi).add("mProductIndex", this.mProductIndex).add("mAuthMaterialIndex", this.mAuthMaterialIndex).add("mRadio", this.mRadio).add("mProvisionerInfo", this.mProvisionerInfo).add("mAdvertisedSdkVersionIndex", this.mAdvertisedSdkVersionIndex).toString();
    }
}
