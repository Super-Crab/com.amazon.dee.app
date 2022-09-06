package com.amazon.whisperjoin.softap.serializer;

import com.amazon.whisperjoin.softap.exceptions.SoftAPDeserializerException;
import com.amazon.whisperjoin.softap.pojos.DeviceDetails;
/* loaded from: classes13.dex */
public interface DeviceDetailsDeserializer extends StringDeserializer<DeviceDetails> {
    @Override // com.amazon.whisperjoin.softap.serializer.StringDeserializer
    /* renamed from: deserialize */
    DeviceDetails mo6645deserialize(String str) throws SoftAPDeserializerException;
}
