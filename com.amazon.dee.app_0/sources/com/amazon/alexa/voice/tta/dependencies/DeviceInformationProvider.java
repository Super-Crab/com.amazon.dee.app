package com.amazon.alexa.voice.tta.dependencies;

import com.amazon.alexa.device.api.DeviceInformation;
import com.amazon.alexa.marketplace.api.Marketplace;
import com.amazon.alexa.protocols.environment.EnvironmentService;
import com.amazon.alexa.protocols.service.api.LazyComponent;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: DeviceInformationProvider.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u0000 \r2\u00020\u0001:\u0001\rB!\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00060\u0003¢\u0006\u0002\u0010\u0007J\b\u0010\f\u001a\u0004\u0018\u00010\tR\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00060\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Lcom/amazon/alexa/voice/tta/dependencies/DeviceInformationProvider;", "", "deviceInformationLazy", "Lcom/amazon/alexa/protocols/service/api/LazyComponent;", "Lcom/amazon/alexa/device/api/DeviceInformation;", "environmentServiceLazy", "Lcom/amazon/alexa/protocols/environment/EnvironmentService;", "(Lcom/amazon/alexa/protocols/service/api/LazyComponent;Lcom/amazon/alexa/protocols/service/api/LazyComponent;)V", "deviceInfo", "Lcom/amazon/alexa/voice/tta/dependencies/DeviceInfo;", "deviceInformation", "environmentService", "getDeviceInformation", "Companion", "AlexaMobileAndroidVoice-TTA_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes11.dex */
public final class DeviceInformationProvider {
    public static final Companion Companion = new Companion(null);
    @NotNull
    public static final String PLATFORM = "Android";
    private DeviceInfo deviceInfo;
    private DeviceInformation deviceInformation;
    private final LazyComponent<DeviceInformation> deviceInformationLazy;
    private EnvironmentService environmentService;
    private final LazyComponent<EnvironmentService> environmentServiceLazy;

    /* compiled from: DeviceInformationProvider.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"Lcom/amazon/alexa/voice/tta/dependencies/DeviceInformationProvider$Companion;", "", "()V", "PLATFORM", "", "AlexaMobileAndroidVoice-TTA_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes11.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public DeviceInformationProvider(@NotNull LazyComponent<DeviceInformation> deviceInformationLazy, @NotNull LazyComponent<EnvironmentService> environmentServiceLazy) {
        Intrinsics.checkParameterIsNotNull(deviceInformationLazy, "deviceInformationLazy");
        Intrinsics.checkParameterIsNotNull(environmentServiceLazy, "environmentServiceLazy");
        this.deviceInformationLazy = deviceInformationLazy;
        this.environmentServiceLazy = environmentServiceLazy;
    }

    @Nullable
    public final DeviceInfo getDeviceInformation() {
        DeviceInformation deviceInformation;
        DeviceInformation deviceInformation2 = this.deviceInformation;
        if (deviceInformation2 == null) {
            deviceInformation2 = this.deviceInformationLazy.mo10268get();
        }
        this.deviceInformation = deviceInformation2;
        EnvironmentService environmentService = this.environmentService;
        if (environmentService == null) {
            environmentService = this.environmentServiceLazy.mo10268get();
        }
        this.environmentService = environmentService;
        EnvironmentService environmentService2 = this.environmentService;
        if (environmentService2 == null || (deviceInformation = this.deviceInformation) == null) {
            return null;
        }
        DeviceInfo deviceInfo = this.deviceInfo;
        if (deviceInfo != null) {
            return deviceInfo;
        }
        String versionName = environmentService2.getVersionName();
        Intrinsics.checkExpressionValueIsNotNull(versionName, "environmentService.versionName");
        String versionRelease = deviceInformation.getVersionRelease();
        Intrinsics.checkExpressionValueIsNotNull(versionRelease, "deviceInformation.versionRelease");
        String deviceSerialNumber = deviceInformation.getDeviceSerialNumber();
        Intrinsics.checkExpressionValueIsNotNull(deviceSerialNumber, "deviceInformation.deviceSerialNumber");
        Marketplace marketplace = environmentService2.getMarketplace();
        Intrinsics.checkExpressionValueIsNotNull(marketplace, "environmentService.marketplace");
        String marketplaceId = marketplace.getObfuscatedId().toString();
        Intrinsics.checkExpressionValueIsNotNull(marketplaceId, "environmentService.marke…e.obfuscatedId.toString()");
        return new DeviceInfo(versionName, "Android", versionRelease, deviceSerialNumber, marketplaceId);
    }
}
