package com.amazon.whisperjoin.deviceprovisioningservice.workflow.deferred;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
/* loaded from: classes13.dex */
public class DeferredDiscoveryCallDetails {
    private String deviceIdentity;
    private String nonce;
    private String productIndex;

    public DeferredDiscoveryCallDetails(String str, String str2, String str3) {
        this.deviceIdentity = str;
        this.productIndex = str2;
        this.nonce = str3;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || DeferredDiscoveryCallDetails.class != obj.getClass()) {
            return false;
        }
        DeferredDiscoveryCallDetails deferredDiscoveryCallDetails = (DeferredDiscoveryCallDetails) obj;
        return new EqualsBuilder().append(this.deviceIdentity, deferredDiscoveryCallDetails.deviceIdentity).append(this.productIndex, deferredDiscoveryCallDetails.productIndex).append(this.nonce, deferredDiscoveryCallDetails.nonce).isEquals();
    }

    public String getDeviceIdentity() {
        return this.deviceIdentity;
    }

    public String getNonce() {
        return this.nonce;
    }

    public String getProductIndex() {
        return this.productIndex;
    }

    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(this.deviceIdentity).append(this.productIndex).append(this.nonce).toHashCode();
    }

    public String toString() {
        return new ToStringBuilder(this).append("deviceIdentity", this.deviceIdentity).append("productIndex", this.productIndex).append("nonce", this.nonce).toString();
    }
}
