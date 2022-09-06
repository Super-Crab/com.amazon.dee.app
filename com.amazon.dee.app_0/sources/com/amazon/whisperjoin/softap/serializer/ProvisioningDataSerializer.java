package com.amazon.whisperjoin.softap.serializer;

import com.amazon.whisperjoin.softap.pojos.ProvisioningData;
/* loaded from: classes13.dex */
public interface ProvisioningDataSerializer extends ByteSerializer<ProvisioningData> {
    byte[] serialize(ProvisioningData provisioningData);
}
