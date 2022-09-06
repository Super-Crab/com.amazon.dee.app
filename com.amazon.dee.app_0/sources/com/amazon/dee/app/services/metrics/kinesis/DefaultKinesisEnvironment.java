package com.amazon.dee.app.services.metrics.kinesis;
/* loaded from: classes12.dex */
public final class DefaultKinesisEnvironment implements KinesisEnvironment {
    @Override // com.amazon.dee.app.services.metrics.kinesis.KinesisEnvironment
    public String getKinesisMetricsAwsRegion() {
        return "us-east-1";
    }

    @Override // com.amazon.dee.app.services.metrics.kinesis.KinesisEnvironment
    public String getKinesisMetricsIdentityPoolId() {
        return "us-east-1:5de045a1-113d-437d-be67-68496504240c";
    }

    @Override // com.amazon.dee.app.services.metrics.kinesis.KinesisEnvironment
    public String getKinesisMetricsStreamName() {
        return "alexa-mobile-analytics";
    }
}
