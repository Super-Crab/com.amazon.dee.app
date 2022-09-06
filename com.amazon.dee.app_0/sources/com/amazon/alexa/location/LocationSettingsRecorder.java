package com.amazon.alexa.location;

import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.crashreporting.api.CrashReporter;
import com.amazon.alexa.location.phase3.LocationDataStore;
import com.amazon.alexa.location.phase3.LocationDataStoreException;
import com.amazon.alexa.location.phase3.LocationDataStoreService;
import com.amazon.alexa.location.utils.ExceptionRecordUtil;
import com.amazon.alexa.location.utils.MobilyticsUtil;
import com.amazon.alexa.mobilytics.Mobilytics;
import com.amazon.alexa.mobilytics.OwnerIdentifier;
import com.amazon.alexa.protocols.service.api.LazyComponent;
import com.google.android.gms.location.LocationSettingsStates;
import io.reactivex.rxjava3.functions.Consumer;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Objects;
/* loaded from: classes9.dex */
public class LocationSettingsRecorder {
    @VisibleForTesting
    static final String LAST_SETTINGS_KEY = "LAST";
    public static final int PERMISSION_ALWAYS = 2;
    public static final int PERMISSION_DENIED = 0;
    public static final int PERMISSION_FOREGROUND = 1;
    private static final String TAG = "LocationSettingsRecord";
    private final LazyComponent<CrashReporter> crashReporter;
    private final GoogleApiService googleApiService;
    private final LocationManager locationManager;
    private final LocationPermissionService locationPermissionService;
    private final LocationDataStore<LocationSettings> locationSettingsStore;
    private final LazyComponent<Mobilytics> mobilytics;
    private static final String COMPONENT_SETTING = MobilyticsUtil.getComponentName("setting");
    private static final String COMPONENT_PERMISSION = MobilyticsUtil.getComponentName("permission");

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes9.dex */
    public @interface LocationPermission {
    }

    /* loaded from: classes9.dex */
    public static class LocationSettings {
        public final boolean isGpsPresent;
        public final boolean isGpsUsable;
        public final boolean isNetworkLocationPresent;
        public final boolean isNetworkLocationUsable;
        public final int locationPermission;

        LocationSettings(int i, boolean z, boolean z2, boolean z3, boolean z4) {
            this.locationPermission = i;
            this.isGpsPresent = z;
            this.isGpsUsable = z2;
            this.isNetworkLocationPresent = z3;
            this.isNetworkLocationUsable = z4;
        }

        static LocationSettings createFromLocationSettingState(LocationSettingsStates locationSettingsStates) {
            return new LocationSettings(2, locationSettingsStates.isGpsPresent(), locationSettingsStates.isGpsUsable(), locationSettingsStates.isNetworkLocationPresent(), locationSettingsStates.isNetworkLocationUsable());
        }

        static LocationSettings createWithoutSettings(int i) {
            return new LocationSettings(i, false, false, false, false);
        }

        public boolean equals(@Nullable Object obj) {
            if (obj instanceof LocationSettings) {
                LocationSettings locationSettings = (LocationSettings) obj;
                return this.locationPermission == locationSettings.locationPermission && this.isGpsPresent == locationSettings.isGpsPresent && this.isGpsUsable == locationSettings.isGpsUsable && this.isNetworkLocationPresent == locationSettings.isNetworkLocationPresent && this.isNetworkLocationUsable == locationSettings.isNetworkLocationUsable;
            }
            return false;
        }

        public int hashCode() {
            return Objects.hash(Integer.valueOf(this.locationPermission), Boolean.valueOf(this.isGpsPresent), Boolean.valueOf(this.isGpsUsable), Boolean.valueOf(this.isNetworkLocationPresent), Boolean.valueOf(this.isNetworkLocationUsable));
        }

        public void recordToMobilytics(LazyComponent<Mobilytics> lazyComponent) {
            lazyComponent.mo10268get().recordOccurrence(MobilyticsUtil.MetricsID.PERMISSION_ALWAYS, true, LocationSettingsRecorder.COMPONENT_PERMISSION, LocationSettingsRecorder.COMPONENT_PERMISSION, OwnerIdentifier.APP_LOCATION_LIBRARIES_ANDROID);
            lazyComponent.mo10268get().recordOccurrence(MobilyticsUtil.MetricsID.GPS_PRESENT, this.isGpsPresent, LocationSettingsRecorder.COMPONENT_SETTING, LocationSettingsRecorder.COMPONENT_SETTING, OwnerIdentifier.APP_LOCATION_LIBRARIES_ANDROID);
            lazyComponent.mo10268get().recordOccurrence(MobilyticsUtil.MetricsID.GPS_USABLE, this.isGpsUsable, LocationSettingsRecorder.COMPONENT_SETTING, LocationSettingsRecorder.COMPONENT_SETTING, OwnerIdentifier.APP_LOCATION_LIBRARIES_ANDROID);
            lazyComponent.mo10268get().recordOccurrence(MobilyticsUtil.MetricsID.NETWORK_LOCATION_PRESENT, this.isNetworkLocationPresent, LocationSettingsRecorder.COMPONENT_SETTING, LocationSettingsRecorder.COMPONENT_SETTING, OwnerIdentifier.APP_LOCATION_LIBRARIES_ANDROID);
            lazyComponent.mo10268get().recordOccurrence(MobilyticsUtil.MetricsID.NETWORK_LOCATION_USABLE, this.isNetworkLocationUsable, LocationSettingsRecorder.COMPONENT_SETTING, LocationSettingsRecorder.COMPONENT_SETTING, OwnerIdentifier.APP_LOCATION_LIBRARIES_ANDROID);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public LocationSettingsRecorder(LazyComponent<Mobilytics> lazyComponent, LocationDataStoreService locationDataStoreService, LocationManager locationManager, LocationPermissionService locationPermissionService, LazyComponent<CrashReporter> lazyComponent2, GoogleApiService googleApiService) {
        this.mobilytics = lazyComponent;
        this.locationSettingsStore = locationDataStoreService.getDataStore(LocationDataStoreService.LOCATION_SETTINGS_TABLE, LocationSettings.class);
        this.locationManager = locationManager;
        this.locationPermissionService = locationPermissionService;
        this.crashReporter = lazyComponent2;
        this.googleApiService = googleApiService;
    }

    public /* synthetic */ void lambda$recordLocationSettings$0$LocationSettingsRecorder(LocationSettings locationSettings, LocationSettingsStates locationSettingsStates) throws Throwable {
        LocationSettings createFromLocationSettingState = LocationSettings.createFromLocationSettingState(locationSettingsStates);
        if (!createFromLocationSettingState.equals(locationSettings)) {
            createFromLocationSettingState.recordToMobilytics(this.mobilytics);
            try {
                this.locationSettingsStore.lambda$storeValueAsRx$1$LocationDataStore(LAST_SETTINGS_KEY, createFromLocationSettingState);
            } catch (LocationDataStoreException e) {
                ExceptionRecordUtil.recordExceptions(TAG, "recordLocationSettings", e, this.crashReporter);
            }
        }
    }

    public /* synthetic */ void lambda$recordLocationSettings$1$LocationSettingsRecorder(Throwable th) throws Throwable {
        ExceptionRecordUtil.recordExceptions(TAG, "recordLocationSettings", th, this.crashReporter);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void recordLocationSettings() {
        try {
            if (!this.googleApiService.isGoogleApiAvailable()) {
                return;
            }
            final LocationSettings retrieveValue = this.locationSettingsStore.retrieveValue(LAST_SETTINGS_KEY);
            if (this.locationPermissionService.hasFullLocationPermission()) {
                this.locationManager.getLocationSettings().subscribe(new Consumer() { // from class: com.amazon.alexa.location.-$$Lambda$LocationSettingsRecorder$wVp_cgiiYehG5OtjjjOdTSLGR50
                    @Override // io.reactivex.rxjava3.functions.Consumer
                    public final void accept(Object obj) {
                        LocationSettingsRecorder.this.lambda$recordLocationSettings$0$LocationSettingsRecorder(retrieveValue, (LocationSettingsStates) obj);
                    }
                }, new Consumer() { // from class: com.amazon.alexa.location.-$$Lambda$LocationSettingsRecorder$j9trxmsmeufMdcPgsg_g-KsEOU0
                    @Override // io.reactivex.rxjava3.functions.Consumer
                    public final void accept(Object obj) {
                        LocationSettingsRecorder.this.lambda$recordLocationSettings$1$LocationSettingsRecorder((Throwable) obj);
                    }
                });
            } else if (retrieveValue == null || retrieveValue.locationPermission == 2) {
                this.mobilytics.mo10268get().recordOccurrence(MobilyticsUtil.MetricsID.PERMISSION_DENIED, true, COMPONENT_PERMISSION, COMPONENT_PERMISSION, OwnerIdentifier.APP_LOCATION_LIBRARIES_ANDROID);
                this.locationSettingsStore.lambda$storeValueAsRx$1$LocationDataStore(LAST_SETTINGS_KEY, LocationSettings.createWithoutSettings(0));
            }
        } catch (LocationDataStoreException e) {
            ExceptionRecordUtil.recordExceptions(TAG, "recordLocationSettings", e, this.crashReporter);
        }
    }
}
