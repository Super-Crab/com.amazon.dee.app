package com.amazon.client.metrics.common.configuration;

import com.amazon.client.metrics.common.configuration.internal.ICodecConfiguration;
import com.amazon.client.metrics.common.configuration.internal.NullCodecConfiguration;
import com.amazon.client.metrics.common.configuration.internal.android.AndroidCodecConfiguration;
import com.amazon.client.metrics.common.internal.util.DevicePlatformIdentifierUtil;
/* loaded from: classes11.dex */
public class CodecConfiguration implements ICodecConfiguration {
    private final ICodecConfiguration mDelegateCodecConfiguration;

    public CodecConfiguration(CodecType codecType, String str) throws MetricsConfigurationException {
        if (DevicePlatformIdentifierUtil.getInstance().isDevicePlatformAndroid()) {
            this.mDelegateCodecConfiguration = getAndroidCodecConfiguration(codecType, str);
        } else {
            this.mDelegateCodecConfiguration = new NullCodecConfiguration();
        }
    }

    private AndroidCodecConfiguration getAndroidCodecConfiguration(CodecType codecType, String str) throws MetricsConfigurationException {
        try {
            return new AndroidCodecConfiguration(codecType, str);
        } catch (com.amazon.client.metrics.thirdparty.configuration.MetricsConfigurationException e) {
            throw new MetricsConfigurationException(e.getMessage(), e);
        }
    }

    @Override // com.amazon.client.metrics.common.configuration.internal.ICodecConfiguration
    public CodecType getCodecType() {
        return this.mDelegateCodecConfiguration.getCodecType();
    }

    @Override // com.amazon.client.metrics.common.configuration.internal.ICodecConfiguration
    public String getCodecVersion() {
        return this.mDelegateCodecConfiguration.getCodecVersion();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ICodecConfiguration getDelegateCodecConfiguration() {
        return this.mDelegateCodecConfiguration;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public CodecConfiguration(ICodecConfiguration iCodecConfiguration) {
        if (iCodecConfiguration != null) {
            this.mDelegateCodecConfiguration = iCodecConfiguration;
            return;
        }
        throw new NullPointerException("Codec Configuration may not be null");
    }
}
