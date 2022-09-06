package com.amazon.alexa.accessorykit.findmy;

import android.location.Location;
import com.amazon.alexa.accessory.Accessory;
import com.amazon.alexa.accessory.AccessorySession;
import com.amazon.alexa.accessory.AccessorySessionListener;
import com.amazon.alexa.accessory.SessionSupplier;
import com.amazon.alexa.accessory.internal.util.Logger;
import com.amazon.alexa.accessory.internal.util.ObservableUtils;
import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.amazon.alexa.accessory.metrics.AccessoryMetricsService;
import com.amazon.alexa.accessory.metrics.AccessoryMetricsServiceHolder;
import com.amazon.alexa.accessory.protocol.Device;
import com.amazon.alexa.accessory.registration.deviceaccount.DeviceAccount;
import com.amazon.alexa.accessory.registration.deviceaccount.DeviceAccountSupplier;
import com.amazon.alexa.accessory.repositories.device.v2.DeviceGroup;
import com.amazon.alexa.accessory.repositories.device.v2.DeviceSupplierV2;
import com.amazon.alexa.accessory.utils.feature.AccessoryFeature;
import com.amazon.alexa.accessory.utils.feature.FeatureChecker;
import com.amazon.alexa.accessorykit.findmy.FindMyAccessoryInformation;
import com.amazon.alexa.accessorykit.findmy.reporter.ReportLocationsResponse;
import com.amazon.alexa.accessorykit.findmy.setting.SettingProvider;
import com.amazon.alexa.accessorykit.findmy.setting.SettingRequest;
import com.amazon.alexa.accessorykit.findmy.setting.SettingResponse;
import com.amazon.alexa.accessorykit.metrics.MetricsConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import io.reactivex.rxjava3.core.SingleSource;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.functions.BiPredicate;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.functions.Predicate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/* loaded from: classes6.dex */
public class FindMyAccessoryManager {
    private static final int API_RETRY_COUNT = 4;
    private static final long RETRY_DELAY_DEFAULT_MILLIS = 1000;
    private static final String TAG = "FindMyAccessoryManager";
    private boolean activated;
    private final Map<String, List<Device.DeviceInformation>> cachedDeviceInfo;
    private final Object cachedDeviceInfoLock;
    private final CompositeDisposable compositeDisposable;
    private final DeviceAccountSupplier deviceAccountSupplier;
    private final DeviceSupplierV2 deviceSupplierV2;
    private final FeatureChecker featureChecker;
    private final LocationProvider locationProvider;
    private final AccessoryLocationReporter locationReporter;
    private final SessionListener sessionListener;
    private final SessionSupplier sessionSupplier;
    private final SettingProvider settingProvider;

    /* renamed from: com.amazon.alexa.accessorykit.findmy.FindMyAccessoryManager$1  reason: invalid class name */
    /* loaded from: classes6.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$alexa$accessorykit$findmy$FindMyAccessoryInformation$EventCause = new int[FindMyAccessoryInformation.EventCause.values().length];

        static {
            try {
                $SwitchMap$com$amazon$alexa$accessorykit$findmy$FindMyAccessoryInformation$EventCause[FindMyAccessoryInformation.EventCause.BLUETOOTH_LINK.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessorykit$findmy$FindMyAccessoryInformation$EventCause[FindMyAccessoryInformation.EventCause.BLUETOOTH_UNLINK.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessorykit$findmy$FindMyAccessoryInformation$EventCause[FindMyAccessoryInformation.EventCause.PRESENCE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessorykit$findmy$FindMyAccessoryInformation$EventCause[FindMyAccessoryInformation.EventCause.BATTERY.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes6.dex */
    public final class SessionListener extends AccessorySessionListener {
        private SessionListener() {
        }

        @Override // com.amazon.alexa.accessory.AccessorySessionListener
        public void onAccessorySessionConnected(Accessory accessory) {
            FindMyAccessoryManager.this.monitorEventsForSession(FindMyAccessoryManager.this.sessionSupplier.getSession(accessory));
        }

        @Override // com.amazon.alexa.accessory.AccessorySessionListener
        public void onAccessorySessionReleased(Accessory accessory) {
            FindMyAccessoryManager.this.handleReleasedAccessory(accessory);
        }

        /* synthetic */ SessionListener(FindMyAccessoryManager findMyAccessoryManager, AnonymousClass1 anonymousClass1) {
            this();
        }
    }

    public FindMyAccessoryManager(SessionSupplier sessionSupplier, DeviceSupplierV2 deviceSupplierV2, LocationProvider locationProvider, AccessoryLocationReporter accessoryLocationReporter, AccessoryMetricsService accessoryMetricsService, FeatureChecker featureChecker, SettingProvider settingProvider, DeviceAccountSupplier deviceAccountSupplier) {
        Preconditions.notNull(sessionSupplier, "sessionSupplier");
        Preconditions.notNull(deviceSupplierV2, "deviceSupplierV2");
        Preconditions.notNull(locationProvider, "locationProvider");
        Preconditions.notNull(accessoryLocationReporter, "locationReporter");
        Preconditions.notNull(accessoryMetricsService, "accessoryMetricsService");
        Preconditions.notNull(featureChecker, "featureChecker");
        Preconditions.notNull(settingProvider, "settingProvider");
        Preconditions.notNull(deviceAccountSupplier, "deviceAccountSupplier");
        AccessoryMetricsServiceHolder.getInstance().set(accessoryMetricsService);
        this.sessionSupplier = sessionSupplier;
        this.deviceSupplierV2 = deviceSupplierV2;
        this.locationProvider = locationProvider;
        this.locationReporter = accessoryLocationReporter;
        this.featureChecker = featureChecker;
        this.settingProvider = settingProvider;
        this.deviceAccountSupplier = deviceAccountSupplier;
        this.sessionListener = new SessionListener(this, null);
        this.compositeDisposable = new CompositeDisposable();
        this.cachedDeviceInfoLock = new Object();
        this.cachedDeviceInfo = new HashMap();
    }

    private void fetchAndReportLocationInformation(final FindMyAccessoryInformation findMyAccessoryInformation) {
        this.compositeDisposable.add(this.locationProvider.getLatestLocation().subscribe(new Consumer() { // from class: com.amazon.alexa.accessorykit.findmy.-$$Lambda$FindMyAccessoryManager$urVlF6i9FfEpmdty82nEM_kIifk
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                FindMyAccessoryManager.this.lambda$fetchAndReportLocationInformation$0$FindMyAccessoryManager(findMyAccessoryInformation, (Location) obj);
            }
        }, new Consumer() { // from class: com.amazon.alexa.accessorykit.findmy.-$$Lambda$FindMyAccessoryManager$kCem9TUy6GEuMpmtCRE7UroSxoQ
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                FindMyAccessoryManager.lambda$fetchAndReportLocationInformation$1(FindMyAccessoryInformation.this, (Throwable) obj);
            }
        }));
    }

    private void fetchUssSettingAndReportLocation(com.amazon.alexa.accessory.repositories.device.v2.Device device, final FindMyAccessoryInformation findMyAccessoryInformation) {
        final String type = device.getType();
        this.compositeDisposable.add(this.deviceAccountSupplier.getDeviceAccount(type, device.getSerialNumber()).flatMap(new Function() { // from class: com.amazon.alexa.accessorykit.findmy.-$$Lambda$FindMyAccessoryManager$S9N40BEe0VBu35dy41wqYAIV5Aw
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                return FindMyAccessoryManager.this.lambda$fetchUssSettingAndReportLocation$7$FindMyAccessoryManager(type, (DeviceAccount) obj);
            }
        }).subscribe(new Consumer() { // from class: com.amazon.alexa.accessorykit.findmy.-$$Lambda$FindMyAccessoryManager$xfWIIw0Erd2wL9zZUNGpvzm5M1I
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                FindMyAccessoryManager.this.lambda$fetchUssSettingAndReportLocation$8$FindMyAccessoryManager(findMyAccessoryInformation, (SettingResponse) obj);
            }
        }, new Consumer() { // from class: com.amazon.alexa.accessorykit.findmy.-$$Lambda$FindMyAccessoryManager$0wJNOoKEsc83qoW64oqStdFm_58
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                FindMyAccessoryManager.lambda$fetchUssSettingAndReportLocation$9(FindMyAccessoryInformation.this, (Throwable) obj);
            }
        }));
    }

    private LocationInformation getLocationInformation(Location location) {
        return LocationInformation.builder().setLatitudeInDegrees(location.getLatitude()).setLongitudeInDegrees(location.getLongitude()).setAccuracyInMeters(location.getAccuracy()).setAltitudeInMeters(location.getAltitude()).setSpeedInMetersPerSecond(location.getSpeed()).setBearingInDegrees(location.getBearing()).setTime(location.getTime()).build();
    }

    private String getMetricFromEvent(FindMyAccessoryInformation.EventCause eventCause) {
        int ordinal = eventCause.ordinal();
        return ordinal != 0 ? ordinal != 1 ? ordinal != 2 ? ordinal != 3 ? MetricsConstants.FindMy.UNKNOWN_EVENT : MetricsConstants.FindMy.BATTERY_EVENT : MetricsConstants.FindMy.PRESENCE_EVENT : MetricsConstants.FindMy.BLUETOOTH_UNLINK_EVENT : MetricsConstants.FindMy.BLUETOOTH_LINK_EVENT;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleReleasedAccessory(Accessory accessory) {
        synchronized (this.cachedDeviceInfoLock) {
            List<Device.DeviceInformation> list = this.cachedDeviceInfo.get(accessory.getAddress());
            if (list == null) {
                return;
            }
            Logger.d("%s: handleReleasedAccessory sending event: %s", TAG, FindMyAccessoryInformation.EventCause.BLUETOOTH_UNLINK.getDescription());
            if (this.featureChecker.hasAccess(AccessoryFeature.FIND_MY_NON_CLUSTERED)) {
                reportLocationForDeviceInfoList(list, accessory.getAddress(), FindMyAccessoryInformation.EventCause.BLUETOOTH_UNLINK);
            } else {
                reportLocationForDeviceInfoList(list, accessory.getAddress(), FindMyAccessoryInformation.EventCause.BLUETOOTH_UNLINK, true);
            }
            this.cachedDeviceInfo.remove(accessory.getAddress());
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$fetchAndReportLocationInformation$1(FindMyAccessoryInformation findMyAccessoryInformation, Throwable th) throws Throwable {
        Logger.e("%s: Error retrieving location information", th, TAG);
        recordEventMetric(MetricsConstants.FindMy.GET_LOCATION_SUCCESS, false, findMyAccessoryInformation.getDeviceType());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$fetchUssSettingAndReportLocation$9(FindMyAccessoryInformation findMyAccessoryInformation, Throwable th) throws Throwable {
        Logger.d("%s: ERROR: fetchUssSettingAndReportLocation error fetching uss setting for accessory %s", th, TAG, findMyAccessoryInformation.getIdentifier());
        Logger.e("%s: fetchUssSettingAndReportLocation error fetching uss setting for accessory", th, TAG);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$queryDeviceForUssSettingAndReportLocation$6(FindMyAccessoryInformation findMyAccessoryInformation, Throwable th) throws Throwable {
        Logger.d("%s: ERROR: queryDeviceForUssSettingAndReportLocation error querying deviceGroup for accessory %s", th, TAG, findMyAccessoryInformation.getIdentifier());
        Logger.e("%s: queryDeviceForUssSettingAndReportLocation error querying deviceGroup for accessory", th, TAG);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$reportLocationInformation$2(ReportLocationsResponse reportLocationsResponse) throws Throwable {
        if (Logger.shouldLog(Logger.Level.DEBUG)) {
            Logger.d("%s: reportLocationInformation - response: %s", TAG, reportLocationsResponse.toJsonObject().toString(4));
        }
    }

    private void monitorConnectStatusForSession(final AccessorySession accessorySession) {
        final FindMyAccessoryInformation.EventCause eventCause = FindMyAccessoryInformation.EventCause.BLUETOOTH_LINK;
        this.compositeDisposable.add(accessorySession.getDeviceRepositoryV2().queryDeviceInformationSet().flatMap($$Lambda$FindMyAccessoryManager$IVQAeG_IsSFeCO9n6cyya9yOdbA.INSTANCE).distinctUntilChanged(DeviceFilterUtils.BT_STATUS_FILTER).take(1L).subscribe(new Consumer() { // from class: com.amazon.alexa.accessorykit.findmy.-$$Lambda$FindMyAccessoryManager$eWdO4aXXK0Yi6aZ_Z1zcLInDf5o
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                FindMyAccessoryManager.this.lambda$monitorConnectStatusForSession$14$FindMyAccessoryManager(accessorySession, eventCause, (List) obj);
            }
        }, $$Lambda$FindMyAccessoryManager$YriNXOfG19DODk6spQ05ci1pPPo.INSTANCE));
        this.compositeDisposable.add(accessorySession.getDeviceRepositoryV2().queryDeviceInformationSet().flatMap($$Lambda$FindMyAccessoryManager$yiV205_ln2g8M45KHUukYpeGnQ.INSTANCE).distinctUntilChanged(DeviceFilterUtils.BT_STATUS_FILTER).skip(1L).subscribe(new Consumer() { // from class: com.amazon.alexa.accessorykit.findmy.-$$Lambda$FindMyAccessoryManager$VqEPnT5aVizgrckyfiKHVFv27bE
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                FindMyAccessoryManager.this.lambda$monitorConnectStatusForSession$17$FindMyAccessoryManager(accessorySession, eventCause, (List) obj);
            }
        }, $$Lambda$FindMyAccessoryManager$DTAYLvW9XEHRH6Vm3YzQ9SeduME.INSTANCE));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void monitorEventsForSession(AccessorySession accessorySession) {
        monitorConnectStatusForSession(accessorySession);
        monitorStatusForSession(accessorySession, DeviceFilterUtils.DEVICE_PRESENCE_STATUS_FILTER, FindMyAccessoryInformation.EventCause.PRESENCE);
        monitorStatusForSession(accessorySession, DeviceFilterUtils.BATTERY_STATUS_FILTER, FindMyAccessoryInformation.EventCause.BATTERY);
    }

    private void monitorStatusForSession(final AccessorySession accessorySession, BiPredicate<List<Device.DeviceInformation>, List<Device.DeviceInformation>> biPredicate, final FindMyAccessoryInformation.EventCause eventCause) {
        this.compositeDisposable.add(accessorySession.getDeviceRepositoryV2().queryDeviceInformationSet().flatMap($$Lambda$FindMyAccessoryManager$iNVLENAJXTgSMEcMOOLmOY2az0U.INSTANCE).distinctUntilChanged(biPredicate).skip(1L).subscribe(new Consumer() { // from class: com.amazon.alexa.accessorykit.findmy.-$$Lambda$FindMyAccessoryManager$KShPedi9zIDiZZyWJ9YrzCKslh4
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                FindMyAccessoryManager.this.lambda$monitorStatusForSession$11$FindMyAccessoryManager(eventCause, accessorySession, (List) obj);
            }
        }, $$Lambda$FindMyAccessoryManager$UOhdlJFtgImwQEYmA9aZJRl_SmI.INSTANCE));
    }

    private void queryDeviceForUssSettingAndReportLocation(final FindMyAccessoryInformation findMyAccessoryInformation) {
        this.compositeDisposable.add(this.deviceSupplierV2.queryDeviceGroups().firstOrError().flatMapObservable($$Lambda$tNLLyz36wpjmL1kezURjOHIEA.INSTANCE).filter(new Predicate() { // from class: com.amazon.alexa.accessorykit.findmy.-$$Lambda$FindMyAccessoryManager$UbBen-2WVTEueCZgsirAQPSQZ6k
            @Override // io.reactivex.rxjava3.functions.Predicate
            public final boolean test(Object obj) {
                boolean equals;
                equals = ((DeviceGroup) obj).getIdentifier().equals(FindMyAccessoryInformation.this.getIdentifier());
                return equals;
            }
        }).subscribe(new Consumer() { // from class: com.amazon.alexa.accessorykit.findmy.-$$Lambda$FindMyAccessoryManager$t1j8cbQXKenEcZwd1Nr5H5DLDc8
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                FindMyAccessoryManager.this.lambda$queryDeviceForUssSettingAndReportLocation$5$FindMyAccessoryManager(findMyAccessoryInformation, (DeviceGroup) obj);
            }
        }, new Consumer() { // from class: com.amazon.alexa.accessorykit.findmy.-$$Lambda$FindMyAccessoryManager$oRv_Q3k7DHNV6VssRtC5p4k-KPg
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                FindMyAccessoryManager.lambda$queryDeviceForUssSettingAndReportLocation$6(FindMyAccessoryInformation.this, (Throwable) obj);
            }
        }));
    }

    private static void recordCounterMetric(String str, String str2) {
        AccessoryMetricsServiceHolder.getInstance().get().recordCounter(str, str2, 1.0d, null);
    }

    private static void recordEventMetric(String str, boolean z, String str2) {
        GeneratedOutlineSupport1.outline171(str, str2, z, null);
    }

    private void reportLocationForDeviceInfoList(List<Device.DeviceInformation> list, String str, FindMyAccessoryInformation.EventCause eventCause) {
        for (Device.DeviceInformation deviceInformation : list) {
            if (list.size() == 1 || deviceInformation.getStatus().getLink() == Device.ConnectionStatus.CONNECTION_STATUS_CONNECTED) {
                recordCounterMetric(getMetricFromEvent(eventCause), deviceInformation.getDeviceType());
                queryDeviceForUssSettingAndReportLocation(FindMyAccessoryInformation.builder().setDeviceSerialNumber(deviceInformation.getSerialNumber()).setDeviceType(deviceInformation.getDeviceType()).setEventCause(eventCause).setIdentifier(str).build());
            }
        }
    }

    private void reportLocationInformation(LocationInformation locationInformation, FindMyAccessoryInformation findMyAccessoryInformation) {
        recordCounterMetric(MetricsConstants.FindMy.REPORT_LOCATION_REQUEST, findMyAccessoryInformation.getDeviceType());
        this.compositeDisposable.add(this.locationReporter.reportAccessoryLocation(locationInformation, findMyAccessoryInformation).retryWhen(ObservableUtils.retryBackoff(4, 1000L)).subscribe($$Lambda$FindMyAccessoryManager$EWAo1ujCzXSwxkfqRDA5BpsQ4kQ.INSTANCE, $$Lambda$FindMyAccessoryManager$d4yu6RhhaBGGtBetpwqbNAEvMEw.INSTANCE));
    }

    public void activate() {
        Preconditions.mainThread();
        if (this.activated) {
            return;
        }
        this.activated = true;
        Logger.d("%s Activate", TAG);
        this.sessionSupplier.addSessionListener(this.sessionListener);
    }

    public void deactivate() {
        Preconditions.mainThread();
        if (!this.activated) {
            return;
        }
        this.activated = false;
        Logger.d("%s Deactivate", TAG);
        this.sessionSupplier.removeSessionListener(this.sessionListener);
        this.compositeDisposable.clear();
    }

    public /* synthetic */ void lambda$fetchAndReportLocationInformation$0$FindMyAccessoryManager(FindMyAccessoryInformation findMyAccessoryInformation, Location location) throws Throwable {
        recordEventMetric(MetricsConstants.FindMy.GET_LOCATION_SUCCESS, true, findMyAccessoryInformation.getDeviceType());
        reportLocationInformation(getLocationInformation(location), findMyAccessoryInformation);
    }

    public /* synthetic */ SingleSource lambda$fetchUssSettingAndReportLocation$7$FindMyAccessoryManager(String str, DeviceAccount deviceAccount) throws Throwable {
        SettingRequest create = SettingRequest.create(deviceAccount.getDeviceAccountResponse().getDeviceAccountId(), str);
        recordCounterMetric(MetricsConstants.FindMy.QUERY_SETTING_REQUEST, str);
        return this.settingProvider.querySetting(create).retryWhen(ObservableUtils.retryBackoff(4, 1000L));
    }

    public /* synthetic */ void lambda$fetchUssSettingAndReportLocation$8$FindMyAccessoryManager(FindMyAccessoryInformation findMyAccessoryInformation, SettingResponse settingResponse) throws Throwable {
        SettingResponse.Enablement settingValue = settingResponse.getSettingValue();
        Logger.d("%s: Received USS setting response: %s", TAG, settingValue);
        if (SettingResponse.Enablement.ENABLED == settingValue) {
            fetchAndReportLocationInformation(findMyAccessoryInformation);
        } else {
            Logger.d("%s: Location reporting is not enabled for accessory %s ", TAG, findMyAccessoryInformation.getIdentifier());
        }
    }

    public /* synthetic */ void lambda$monitorConnectStatusForSession$14$FindMyAccessoryManager(AccessorySession accessorySession, FindMyAccessoryInformation.EventCause eventCause, List list) throws Throwable {
        synchronized (this.cachedDeviceInfoLock) {
            this.cachedDeviceInfo.put(accessorySession.getAddress(), list);
        }
        Logger.d("%s: monitorConnectStatusForSession received first event: %s", TAG, eventCause.getDescription());
        if (this.featureChecker.hasAccess(AccessoryFeature.FIND_MY_NON_CLUSTERED)) {
            reportLocationForDeviceInfoList(list, accessorySession.getAddress(), eventCause);
        } else {
            reportLocationForDeviceInfoList(list, accessorySession.getAddress(), eventCause, true);
        }
    }

    public /* synthetic */ void lambda$monitorConnectStatusForSession$17$FindMyAccessoryManager(AccessorySession accessorySession, FindMyAccessoryInformation.EventCause eventCause, List list) throws Throwable {
        synchronized (this.cachedDeviceInfoLock) {
            this.cachedDeviceInfo.put(accessorySession.getAddress(), list);
        }
        Logger.d("%s: monitorConnectStatusForSession received subsequent event: %s", TAG, eventCause.getDescription());
        reportLocationForDeviceInfoList(list, accessorySession.getAddress(), eventCause, false);
    }

    public /* synthetic */ void lambda$monitorStatusForSession$11$FindMyAccessoryManager(FindMyAccessoryInformation.EventCause eventCause, AccessorySession accessorySession, List list) throws Throwable {
        Logger.d("%s: monitorStatusForSession received event: %s", TAG, eventCause.getDescription());
        reportLocationForDeviceInfoList(list, accessorySession.getAddress(), eventCause, true);
    }

    public /* synthetic */ void lambda$queryDeviceForUssSettingAndReportLocation$5$FindMyAccessoryManager(FindMyAccessoryInformation findMyAccessoryInformation, DeviceGroup deviceGroup) throws Throwable {
        com.amazon.alexa.accessory.repositories.device.v2.Device deviceWithHighestDeviceId = deviceGroup.getDeviceWithHighestDeviceId();
        if (deviceWithHighestDeviceId != null) {
            fetchUssSettingAndReportLocation(deviceWithHighestDeviceId, findMyAccessoryInformation);
        }
    }

    private void reportLocationForDeviceInfoList(List<Device.DeviceInformation> list, String str, FindMyAccessoryInformation.EventCause eventCause, boolean z) {
        for (Device.DeviceInformation deviceInformation : list) {
            if (!z || deviceInformation.getStatus().getLink() == Device.ConnectionStatus.CONNECTION_STATUS_CONNECTED) {
                recordCounterMetric(getMetricFromEvent(eventCause), deviceInformation.getDeviceType());
                queryDeviceForUssSettingAndReportLocation(FindMyAccessoryInformation.builder().setDeviceSerialNumber(deviceInformation.getSerialNumber()).setDeviceType(deviceInformation.getDeviceType()).setEventCause(eventCause).setIdentifier(str).build());
            }
        }
    }
}
