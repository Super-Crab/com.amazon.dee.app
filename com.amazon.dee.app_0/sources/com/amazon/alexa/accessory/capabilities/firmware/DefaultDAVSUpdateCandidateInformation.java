package com.amazon.alexa.accessory.capabilities.firmware;

import android.annotation.SuppressLint;
import com.amazon.alexa.accessory.metrics.FirmwareTaskMetricsReporter;
import com.amazon.alexa.accessory.protocol.Firmware;
import com.amazon.alexa.accessory.repositories.firmware.FirmwareContract;
import com.amazon.alexa.accessory.repositories.firmware.FirmwareRepositoryV2;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.functions.Consumer;
import java.util.Set;
/* loaded from: classes.dex */
public class DefaultDAVSUpdateCandidateInformation implements DfuCandidateInformation {
    private final String deviceType;
    private Firmware.FirmwareInformation firmwareInformation;

    @SuppressLint({"CheckResult"})
    public DefaultDAVSUpdateCandidateInformation(String str, FirmwareRepositoryV2 firmwareRepositoryV2) {
        this.deviceType = str;
        firmwareRepositoryV2.queryInformationSet().map($$Lambda$DefaultDAVSUpdateCandidateInformation$ybJ7lCN0kyEKam1SybFCsNNwe70.INSTANCE).subscribe(new Consumer() { // from class: com.amazon.alexa.accessory.capabilities.firmware.-$$Lambda$DefaultDAVSUpdateCandidateInformation$fIGdxbZEcAMIQ71MudUpsRdIqFo
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                DefaultDAVSUpdateCandidateInformation.this.lambda$new$1$DefaultDAVSUpdateCandidateInformation((Firmware.FirmwareInformation) obj);
            }
        }, $$Lambda$DefaultDAVSUpdateCandidateInformation$1jz5DtqN9pZIWQ7_ilWbLEAYAw.INSTANCE);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ Firmware.FirmwareInformation lambda$new$0(Set set) throws Throwable {
        return (Firmware.FirmwareInformation) set.iterator().next();
    }

    @Override // com.amazon.alexa.accessory.capabilities.firmware.DfuCandidateInformation
    public FirmwareContract.Component getComponent(String str) {
        throw new RuntimeException("Not implemented");
    }

    @Override // com.amazon.alexa.accessory.capabilities.firmware.DfuCandidateInformation
    public int getCurrentVersion() {
        return getVersion();
    }

    @Override // com.amazon.alexa.accessory.capabilities.firmware.DfuCandidateInformation
    public String getDeviceType() {
        return this.deviceType;
    }

    @Override // com.amazon.alexa.accessory.capabilities.firmware.DfuCandidateInformation
    public String getName() {
        Firmware.FirmwareInformation firmwareInformation = this.firmwareInformation;
        return firmwareInformation == null ? "UNKNOWN" : firmwareInformation.getName();
    }

    @Override // com.amazon.alexa.accessory.capabilities.firmware.DfuCandidateInformation
    public Single<FirmwareTaskMetricsReporter.PackageMetricsData> getPackageMetricsData() {
        return Single.just(new FirmwareTaskMetricsReporter.PackageMetricsData(getName(), getName(), getVersion(), getVersion()));
    }

    @Override // com.amazon.alexa.accessory.capabilities.firmware.DfuCandidateInformation
    public int getVersion() {
        Firmware.FirmwareInformation firmwareInformation = this.firmwareInformation;
        if (firmwareInformation == null) {
            return 0;
        }
        return firmwareInformation.getVersion();
    }

    public /* synthetic */ void lambda$new$1$DefaultDAVSUpdateCandidateInformation(Firmware.FirmwareInformation firmwareInformation) throws Throwable {
        this.firmwareInformation = firmwareInformation;
    }
}
