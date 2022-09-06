package com.amazon.client.metrics.common.configuration.internal;

import com.amazon.client.metrics.common.configuration.CodecType;
/* loaded from: classes11.dex */
public class NullCodecConfiguration implements ICodecConfiguration {
    @Override // com.amazon.client.metrics.common.configuration.internal.ICodecConfiguration
    public CodecType getCodecType() {
        return null;
    }

    @Override // com.amazon.client.metrics.common.configuration.internal.ICodecConfiguration
    public String getCodecVersion() {
        return null;
    }
}
