package com.amazon.alexa.client.metrics.kinesis.context;

import com.amazon.alexa.client.metrics.kinesis.system.AppDetails;
import com.amazon.alexa.client.metrics.kinesis.system.DeviceDetails;
import com.amazon.alexa.client.metrics.kinesis.util.KinesisContextIdUtil;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.regions.Regions;
/* loaded from: classes6.dex */
public interface KinesisContext {
    AWSCredentialsProvider getAWSCredentialsProvider();

    AppDetails getAppDetails();

    String getAppID();

    DeviceDetails getDeviceDetails();

    Regions getKinesisAwsRegion();

    String getKinesisStreamName();

    KinesisContextIdUtil getUniqueId();
}
