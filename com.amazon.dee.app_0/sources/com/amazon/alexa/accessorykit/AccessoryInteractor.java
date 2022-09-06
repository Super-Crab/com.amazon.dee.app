package com.amazon.alexa.accessorykit;

import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.accessory.Accessory;
import com.amazon.alexa.accessory.AccessorySessionOptions;
import com.amazon.alexa.accessory.internal.util.Logger;
import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.amazon.alexa.accessory.internal.util.RedactionUtil;
import com.amazon.alexa.accessory.metrics.AccessoryMetricsService;
import com.amazon.alexa.accessory.protocol.Device;
import com.amazon.alexa.accessory.repositories.device.v2.Device;
import com.amazon.alexa.accessory.repositories.device.v2.DeviceGroup;
import com.amazon.alexa.accessory.utils.feature.AccessoryFeature;
import com.amazon.alexa.accessory.utils.feature.FeatureChecker;
import com.amazon.alexa.accessoryclient.client.accessories.Accessories;
import com.amazon.alexa.accessoryclient.client.accessories.AccessorySession;
import com.amazon.alexa.accessoryclient.client.accessories.SessionSupplier;
import com.amazon.alexa.accessoryclient.common.api.ConnectionStatus;
import com.android.tools.r8.GeneratedOutlineSupport1;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.CompletableSource;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleSource;
import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Function;
import java.util.HashMap;
import java.util.Map;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes6.dex */
public class AccessoryInteractor {
    private static final String TAG = "AccessoryInteractor";
    private final Map<String, Accessory> accessoryMap;
    private final Accessories clientAccessories;
    private final FeatureChecker featureChecker;
    private final AccessoryMetricsService metricsService;

    @VisibleForTesting
    /* loaded from: classes6.dex */
    static final class MetricsConstants {
        static final String LINK = "AccessorySupplierLink";
        static final String UNKNOWN = "UNKNOWN";

        private MetricsConstants() {
            throw new IllegalStateException("No instances!");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public AccessoryInteractor(FeatureChecker featureChecker, Accessories accessories, AccessoryMetricsService accessoryMetricsService) {
        Preconditions.notNull(featureChecker, "featureChecker");
        Preconditions.notNull(accessories, "clientAccessories");
        Preconditions.notNull(accessoryMetricsService, "metricsService");
        this.featureChecker = featureChecker;
        this.clientAccessories = accessories;
        this.metricsService = accessoryMetricsService;
        this.accessoryMap = new HashMap();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static AccessoryInformation createAccessoryInformation(DeviceGroup deviceGroup, boolean z) {
        Device device = deviceGroup.getDevice();
        return new AccessoryInformation(device != null ? device.getName() : null, device != null ? device.getType() : null, device != null ? device.getSerialNumber() : null, deviceGroup.getIdentifier(), deviceGroup.getCondition(System.currentTimeMillis()).ordinal(), deviceGroup.getTransportType().ordinal(), z);
    }

    private static Single<String> getDeviceTypeFromAccessory(Accessory accessory, SessionSupplier sessionSupplier) {
        return sessionSupplier.getSession(accessory.getAddress()).getDeviceRepository().queryDeviceInformationSet().firstOrError().map($$Lambda$AccessoryInteractor$lb6YHcEUbOlRDQ81VXVdnk4ArkQ.INSTANCE);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ int lambda$null$12(Device.DeviceInformation deviceInformation, Device.DeviceInformation deviceInformation2) {
        return deviceInformation.getDeviceId() - deviceInformation2.getDeviceId();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ SingleSource lambda$registerDeviceClient$10(AccessorySession accessorySession, ConnectionStatus connectionStatus) throws Throwable {
        if (connectionStatus == ConnectionStatus.CONNECTED) {
            return accessorySession.getDeviceRepository().queryDeviceConfiguration().firstOrError().map($$Lambda$AccessoryInteractor$hzzhMkQJ4PCnxbsEaZxXoBk3fs.INSTANCE);
        }
        return Single.just(false);
    }

    public Completable createSessionClient(final String str) {
        Preconditions.mainThread();
        final Accessory accessory = this.accessoryMap.get(str);
        if (accessory == null) {
            return Completable.error(new Exception(GeneratedOutlineSupport1.outline72("No such accessory: ", str)));
        }
        return this.clientAccessories.getSessionSupplier().getSession(str).queryConnectionStatus().flatMapCompletable(new Function() { // from class: com.amazon.alexa.accessorykit.-$$Lambda$AccessoryInteractor$eOqpayIuK93Wv2Eelt79r4xgqLU
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                return AccessoryInteractor.this.lambda$createSessionClient$8$AccessoryInteractor(accessory, str, (ConnectionStatus) obj);
            }
        });
    }

    @Nullable
    public Accessory getAccessory(String str) {
        Preconditions.mainThread();
        return this.accessoryMap.get(str);
    }

    public /* synthetic */ CompletableSource lambda$createSessionClient$8$AccessoryInteractor(final Accessory accessory, final String str, ConnectionStatus connectionStatus) throws Throwable {
        if (connectionStatus != ConnectionStatus.NONEXISTENT) {
            return Completable.complete();
        }
        if (!this.featureChecker.hasAccess(AccessoryFeature.VERSION_NOTIFICATION_RESPONSE)) {
            return this.clientAccessories.getSessionSupplier().createAndConnectSessionAwaitConnection(accessory);
        }
        return this.clientAccessories.getDeviceSupplier().hasDeviceGroup(str).flatMapCompletable(new Function() { // from class: com.amazon.alexa.accessorykit.-$$Lambda$AccessoryInteractor$qPP30Uswsln4QCj3T6SIOwXerRU
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                return AccessoryInteractor.this.lambda$null$7$AccessoryInteractor(str, accessory, (Boolean) obj);
            }
        });
    }

    public /* synthetic */ CompletableSource lambda$linkAccessoryClient$6$AccessoryInteractor(final Accessory accessory) throws Throwable {
        return this.clientAccessories.linkAccessory(accessory).doOnComplete(new Action() { // from class: com.amazon.alexa.accessorykit.-$$Lambda$AccessoryInteractor$6LqIgjc4H2VXOQyXmfZ9_cDJZcs
            @Override // io.reactivex.rxjava3.functions.Action
            public final void run() {
                AccessoryInteractor.this.lambda$null$2$AccessoryInteractor(accessory);
            }
        }).doOnError(new Consumer() { // from class: com.amazon.alexa.accessorykit.-$$Lambda$AccessoryInteractor$o3k18LiByMlNJ5m-2TkUT9qhVI0
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                AccessoryInteractor.this.lambda$null$5$AccessoryInteractor(accessory, (Throwable) obj);
            }
        });
    }

    public /* synthetic */ void lambda$null$0$AccessoryInteractor(String str) throws Throwable {
        this.metricsService.recordOccurrence("AccessorySupplierLink", str, true, null);
    }

    public /* synthetic */ void lambda$null$1$AccessoryInteractor(Accessory accessory, Throwable th) throws Throwable {
        this.metricsService.recordOccurrence("AccessorySupplierLink", "UNKNOWN", true, null);
        Logger.d("ERROR: Unable to determine device type to record link success metric for accessory %s", th, accessory);
        Logger.e("Unable to determine device type to record link success metric for accessory %s", th, RedactionUtil.redact(accessory));
    }

    public /* synthetic */ void lambda$null$2$AccessoryInteractor(final Accessory accessory) throws Throwable {
        getDeviceTypeFromAccessory(accessory, this.clientAccessories.getSessionSupplier()).subscribe(new Consumer() { // from class: com.amazon.alexa.accessorykit.-$$Lambda$AccessoryInteractor$oL4AXXBzueGEKjGN0FekSSwhWPk
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                AccessoryInteractor.this.lambda$null$0$AccessoryInteractor((String) obj);
            }
        }, new Consumer() { // from class: com.amazon.alexa.accessorykit.-$$Lambda$AccessoryInteractor$BTHpCEXBiyh3PiXBup5Hv9G6vrk
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                AccessoryInteractor.this.lambda$null$1$AccessoryInteractor(accessory, (Throwable) obj);
            }
        });
    }

    public /* synthetic */ void lambda$null$3$AccessoryInteractor(String str) throws Throwable {
        this.metricsService.recordOccurrence("AccessorySupplierLink", str, false, null);
    }

    public /* synthetic */ void lambda$null$4$AccessoryInteractor(Accessory accessory, Throwable th) throws Throwable {
        this.metricsService.recordOccurrence("AccessorySupplierLink", "UNKNOWN", false, null);
        Logger.d("ERROR: Unable to determine device type to record link failed metric for accessory %s", th, accessory);
        Logger.e("Unable to determine device type to record link failed metric for accessory %s", th, RedactionUtil.redact(accessory));
    }

    public /* synthetic */ void lambda$null$5$AccessoryInteractor(final Accessory accessory, Throwable th) throws Throwable {
        getDeviceTypeFromAccessory(accessory, this.clientAccessories.getSessionSupplier()).subscribe(new Consumer() { // from class: com.amazon.alexa.accessorykit.-$$Lambda$AccessoryInteractor$giBkheKZLuUlYai3ElyVAdEqEJ4
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                AccessoryInteractor.this.lambda$null$3$AccessoryInteractor((String) obj);
            }
        }, new Consumer() { // from class: com.amazon.alexa.accessorykit.-$$Lambda$AccessoryInteractor$BEVinWGVW-jGH-smdpGNwG96vXc
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                AccessoryInteractor.this.lambda$null$4$AccessoryInteractor(accessory, (Throwable) obj);
            }
        });
    }

    public /* synthetic */ CompletableSource lambda$null$7$AccessoryInteractor(String str, Accessory accessory, Boolean bool) throws Throwable {
        AccessorySessionOptions accessorySessionOptions = new AccessorySessionOptions();
        accessorySessionOptions.setDeviceKnown(bool.booleanValue());
        Logger.d("%s: Setting deviceKnown flag %b for accessory %s", TAG, bool, str);
        return this.clientAccessories.getSessionSupplier().createAndConnectSessionWithOptionsAwaitConnection(accessory, accessorySessionOptions).ignoreElement();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Completable linkAccessoryClient(String str) {
        return this.clientAccessories.getSessionSupplier().getSession(str).queryConnectedAccessory().flatMapCompletable(new Function() { // from class: com.amazon.alexa.accessorykit.-$$Lambda$AccessoryInteractor$oqcEPzYk7QMln1qyOLqQRF7Q9_M
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                return AccessoryInteractor.this.lambda$linkAccessoryClient$6$AccessoryInteractor((Accessory) obj);
            }
        });
    }

    public void registerAccessory(String str, Accessory accessory) {
        this.accessoryMap.put(str, accessory);
    }

    public Single<AccessoryInformation> registerDeviceClient(final DeviceGroup deviceGroup) {
        Preconditions.mainThread();
        com.amazon.alexa.accessory.repositories.device.v2.Device device = deviceGroup.getDevice();
        if (!this.accessoryMap.containsKey(deviceGroup.getIdentifier())) {
            this.accessoryMap.put(deviceGroup.getIdentifier(), new Accessory(deviceGroup.getIdentifier(), deviceGroup.getTransportType(), device != null ? device.getName() : null));
        }
        final AccessorySession session = this.clientAccessories.getSessionSupplier().getSession(deviceGroup.getIdentifier());
        return session.queryConnectionStatus().flatMap(new Function() { // from class: com.amazon.alexa.accessorykit.-$$Lambda$AccessoryInteractor$rlSIyBvllXYr7xCZbEmAGJutelU
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                return AccessoryInteractor.lambda$registerDeviceClient$10(AccessorySession.this, (ConnectionStatus) obj);
            }
        }).map(new Function() { // from class: com.amazon.alexa.accessorykit.-$$Lambda$AccessoryInteractor$7L2E5mDtV-t5YNjxGzkP1ukMeJM
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                AccessoryInformation createAccessoryInformation;
                createAccessoryInformation = AccessoryInteractor.createAccessoryInformation(DeviceGroup.this, ((Boolean) obj).booleanValue());
                return createAccessoryInformation;
            }
        });
    }
}
