package com.amazon.alexa.accessory.capabilities.firmware;

import com.amazon.alexa.accessory.internal.util.Logger;
import com.amazon.alexa.accessory.metrics.FirmwareTaskMetricsReporter;
import com.amazon.alexa.accessory.repositories.firmware.FirmwareContract;
import io.reactivex.rxjava3.core.Single;
import java.io.IOException;
import org.json.JSONException;
/* loaded from: classes.dex */
public class DefaultDfuCandidateInformation implements DfuCandidateInformation {
    private static final String TAG = "DefaultDfuCandidateInformation:";
    private final FirmwareContract.Package aPackage;
    private final String deviceType;

    public DefaultDfuCandidateInformation(String str, FirmwareContract.Package r2) {
        this.deviceType = str;
        this.aPackage = r2;
    }

    @Override // com.amazon.alexa.accessory.capabilities.firmware.DfuCandidateInformation
    public FirmwareContract.Component getComponent(String str) {
        try {
            return this.aPackage.getComponent(str);
        } catch (IOException | JSONException e) {
            Logger.e("%s Exception thrown in getComponent", e, TAG);
            throw new RuntimeException(e);
        }
    }

    @Override // com.amazon.alexa.accessory.capabilities.firmware.DfuCandidateInformation
    public int getCurrentVersion() {
        return this.aPackage.getCurrentVersion().intValue();
    }

    @Override // com.amazon.alexa.accessory.capabilities.firmware.DfuCandidateInformation
    public String getDeviceType() {
        return this.deviceType;
    }

    @Override // com.amazon.alexa.accessory.capabilities.firmware.DfuCandidateInformation
    public String getName() {
        try {
            return this.aPackage.getName();
        } catch (IOException | JSONException e) {
            Logger.e("%s Exception thrown in getName", e, TAG);
            throw new RuntimeException(e);
        }
    }

    @Override // com.amazon.alexa.accessory.capabilities.firmware.DfuCandidateInformation
    public Single<FirmwareTaskMetricsReporter.PackageMetricsData> getPackageMetricsData() {
        try {
            return Single.just(new FirmwareTaskMetricsReporter.PackageMetricsData(this.aPackage.getReference(), this.aPackage.getName(), this.aPackage.getVersion(), this.aPackage.getCurrentVersion().intValue()));
        } catch (IOException | JSONException e) {
            Logger.e("Error initializing PackageMetricsData", e);
            return Single.error(e);
        }
    }

    @Override // com.amazon.alexa.accessory.capabilities.firmware.DfuCandidateInformation
    public int getVersion() {
        try {
            return this.aPackage.getVersion();
        } catch (IOException | JSONException e) {
            Logger.e("%s Exception thrown in getVersion", e, TAG);
            throw new RuntimeException(e);
        }
    }
}
