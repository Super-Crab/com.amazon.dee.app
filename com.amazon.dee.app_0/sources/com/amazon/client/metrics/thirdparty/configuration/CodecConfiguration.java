package com.amazon.client.metrics.thirdparty.configuration;
/* loaded from: classes11.dex */
public class CodecConfiguration {
    final CodecType mCodecType;
    final String mCodecVersion;

    public CodecConfiguration(CodecType codecType, String str) throws MetricsConfigurationException {
        if (codecType != null) {
            if (str != null && !str.equals("")) {
                this.mCodecType = codecType;
                this.mCodecVersion = str;
                return;
            }
            throw new MetricsConfigurationException("Codec version is null in configuration");
        }
        throw new MetricsConfigurationException("CodecType is null in configuration");
    }

    public CodecType getCodecType() {
        return this.mCodecType;
    }

    public String getCodecVersion() {
        return this.mCodecVersion;
    }
}
