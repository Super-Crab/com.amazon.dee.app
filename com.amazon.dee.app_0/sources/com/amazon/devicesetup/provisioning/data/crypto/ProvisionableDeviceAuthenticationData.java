package com.amazon.devicesetup.provisioning.data.crypto;

import java.util.Arrays;
import java.util.List;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
/* loaded from: classes12.dex */
public class ProvisionableDeviceAuthenticationData {
    private final List<byte[]> certificateChain;
    private final byte[] signature;

    public ProvisionableDeviceAuthenticationData(List<byte[]> list, byte[] bArr) {
        this.certificateChain = list;
        this.signature = (byte[]) bArr.clone();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || ProvisionableDeviceAuthenticationData.class != obj.getClass()) {
            return false;
        }
        ProvisionableDeviceAuthenticationData provisionableDeviceAuthenticationData = (ProvisionableDeviceAuthenticationData) obj;
        return new EqualsBuilder().append(this.signature, provisionableDeviceAuthenticationData.signature).isEquals() && Arrays.deepEquals(this.certificateChain.toArray(), provisionableDeviceAuthenticationData.certificateChain.toArray());
    }

    public List<byte[]> getCertificateChain() {
        return this.certificateChain;
    }

    public byte[] getSignature() {
        return (byte[]) this.signature.clone();
    }

    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(this.certificateChain).append(this.signature).toHashCode();
    }
}
