package com.amazon.dee.app.services.metrics.kinesis.context;

import com.amazon.dee.app.services.metrics.kinesis.system.AppDetails;
import com.amazon.dee.app.services.metrics.kinesis.system.DeviceDetails;
import com.amazon.dee.app.util.KinesisContextIdUtil;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.regions.Regions;
/* loaded from: classes12.dex */
public interface KinesisContext {
    AWSCredentialsProvider getAWSCredentialsProvider();

    AppDetails getAppDetails();

    String getAppID();

    DeviceDetails getDeviceDetails();

    Regions getKinesisAwsRegion();

    String getKinesisStreamName();

    KinesisContextIdUtil getUniqueId();
}
