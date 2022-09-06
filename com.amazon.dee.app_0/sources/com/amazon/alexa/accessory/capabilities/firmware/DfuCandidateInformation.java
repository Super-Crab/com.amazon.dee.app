package com.amazon.alexa.accessory.capabilities.firmware;

import com.amazon.alexa.accessory.metrics.FirmwareTaskMetricsReporter;
import com.amazon.alexa.accessory.repositories.firmware.FirmwareContract;
import io.reactivex.rxjava3.core.Single;
/* loaded from: classes.dex */
public interface DfuCandidateInformation {
    FirmwareContract.Component getComponent(String str);

    int getCurrentVersion();

    String getDeviceType();

    String getName();

    Single<FirmwareTaskMetricsReporter.PackageMetricsData> getPackageMetricsData();

    int getVersion();
}
