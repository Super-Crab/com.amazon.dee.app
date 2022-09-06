package com.amazon.client.metrics.common.configuration.internal.android;

import com.amazon.client.metrics.common.configuration.CodecType;
import com.amazon.client.metrics.common.configuration.internal.ICodecConfiguration;
import com.amazon.client.metrics.thirdparty.configuration.CodecConfiguration;
import com.amazon.client.metrics.thirdparty.configuration.MetricsConfigurationException;
/* loaded from: classes11.dex */
public class AndroidCodecConfiguration implements ICodecConfiguration {
    private final CodecConfiguration mDelegateCodecConfiguration;

    public AndroidCodecConfiguration(CodecType codecType, String str) throws MetricsConfigurationException {
        this.mDelegateCodecConfiguration = new CodecConfiguration(com.amazon.client.metrics.thirdparty.configuration.CodecType.valueOf(codecType.name()), str);
    }

    @Override // com.amazon.client.metrics.common.configuration.internal.ICodecConfiguration
    public CodecType getCodecType() {
        return CodecType.valueOf(this.mDelegateCodecConfiguration.getCodecType().name());
    }

    @Override // com.amazon.client.metrics.common.configuration.internal.ICodecConfiguration
    public String getCodecVersion() {
        return this.mDelegateCodecConfiguration.getCodecVersion();
    }

    public CodecConfiguration getDelegateCodecConfiguration() {
        return this.mDelegateCodecConfiguration;
    }

    public AndroidCodecConfiguration(CodecConfiguration codecConfiguration) {
        if (codecConfiguration != null) {
            this.mDelegateCodecConfiguration = codecConfiguration;
            return;
        }
        throw new NullPointerException("Delegate ThirdParty CodecConfiguration may not be null");
    }
}
