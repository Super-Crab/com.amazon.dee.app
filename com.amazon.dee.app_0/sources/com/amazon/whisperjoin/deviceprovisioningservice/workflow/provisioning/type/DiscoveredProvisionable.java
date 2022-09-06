package com.amazon.whisperjoin.deviceprovisioningservice.workflow.provisioning.type;

import com.amazon.whisperjoin.deviceprovisioningservice.device.WJProvisionee;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import javax.annotation.Nonnull;
/* loaded from: classes13.dex */
public class DiscoveredProvisionable {
    private final String mDiscoverySessionToken;
    private final String mProvisionableReportingUrl;
    private final String mProvisionerReportingUrl;
    private final WJProvisionee mWJProvisionee;

    public DiscoveredProvisionable(@Nonnull WJProvisionee wJProvisionee, @Nonnull String str, @Nonnull String str2, String str3) {
        if (wJProvisionee != null) {
            if (str == null) {
                throw new IllegalArgumentException("provisionerReportingUrl can not be null");
            }
            if (str2 != null) {
                this.mWJProvisionee = wJProvisionee;
                this.mProvisionerReportingUrl = str;
                this.mProvisionableReportingUrl = str2;
                this.mDiscoverySessionToken = str3;
                return;
            }
            throw new IllegalArgumentException("provisionableReportingUrl can not be null");
        }
        throw new IllegalArgumentException("wjProvisionee can not be null");
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || DiscoveredProvisionable.class != obj.getClass()) {
            return false;
        }
        DiscoveredProvisionable discoveredProvisionable = (DiscoveredProvisionable) obj;
        return Objects.equal(this.mWJProvisionee, discoveredProvisionable.mWJProvisionee) && Objects.equal(this.mProvisionerReportingUrl, discoveredProvisionable.mProvisionerReportingUrl) && Objects.equal(this.mProvisionableReportingUrl, discoveredProvisionable.mProvisionableReportingUrl) && Objects.equal(this.mDiscoverySessionToken, discoveredProvisionable.mDiscoverySessionToken);
    }

    public String getDiscoverySessionToken() {
        return this.mDiscoverySessionToken;
    }

    public String getProvisionableReportingUrl() {
        return this.mProvisionableReportingUrl;
    }

    public String getProvisionerReportingUrl() {
        return this.mProvisionerReportingUrl;
    }

    public WJProvisionee getWJProvisionee() {
        return this.mWJProvisionee;
    }

    public int hashCode() {
        return Objects.hashCode(this.mWJProvisionee, this.mProvisionerReportingUrl, this.mProvisionableReportingUrl, this.mDiscoverySessionToken);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("mWJProvisionee", this.mWJProvisionee).add("mProvisionerReportingUrl", this.mProvisionerReportingUrl).add("mProvisionableReportingUrl", this.mProvisionableReportingUrl).add("mDiscoverySessionToken", this.mDiscoverySessionToken).toString();
    }
}
