package com.amazon.whisperjoin.common.sharedtypes.cryptography;

import java.util.Arrays;
import java.util.List;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
/* loaded from: classes13.dex */
public class ProvisionableDeviceAuthenticationData {
    private final List<byte[]> mCertificateChain;
    private final byte[] mSignature;

    public ProvisionableDeviceAuthenticationData(List<byte[]> list, byte[] bArr) {
        this.mCertificateChain = list;
        this.mSignature = (byte[]) bArr.clone();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || ProvisionableDeviceAuthenticationData.class != obj.getClass()) {
            return false;
        }
        ProvisionableDeviceAuthenticationData provisionableDeviceAuthenticationData = (ProvisionableDeviceAuthenticationData) obj;
        return new EqualsBuilder().append(this.mSignature, provisionableDeviceAuthenticationData.mSignature).isEquals() && Arrays.deepEquals(this.mCertificateChain.toArray(), provisionableDeviceAuthenticationData.mCertificateChain.toArray());
    }

    public List<byte[]> getCertificateChain() {
        return this.mCertificateChain;
    }

    public byte[] getSignature() {
        return (byte[]) this.mSignature.clone();
    }

    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(this.mCertificateChain).append(this.mSignature).toHashCode();
    }
}
