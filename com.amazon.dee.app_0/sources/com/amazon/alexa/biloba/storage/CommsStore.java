package com.amazon.alexa.biloba.storage;

import androidx.annotation.VisibleForTesting;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.amazon.alexa.biloba.dependency.BilobaScope;
import com.amazon.alexa.biloba.generated.models.EmergencyAddress;
import com.amazon.alexa.biloba.generated.models.EmergencyContact;
import com.amazon.alexa.biloba.generated.models.EmergencySettingsResponse;
import com.amazon.alexa.biloba.generated.network.api.CommsApi;
import com.amazon.alexa.biloba.generated.network.api.EmergencySettingsApi;
import com.amazon.alexa.biloba.metrics.BilobaMetricsService;
import com.amazon.alexa.biloba.utils.LogUtils;
import com.amazon.alexa.crashreporting.api.CrashMetadata;
import com.amazon.alexa.crashreporting.api.CrashReporter;
import com.amazon.alexa.mobilytics.event.operational.MobilyticsMetricsTimer;
import dagger.Lazy;
import javax.inject.Inject;
import javax.inject.Singleton;
import rx.functions.Action1;
@Singleton
@BilobaScope
/* loaded from: classes6.dex */
public class CommsStore extends DataStore {
    private static final String TAG = "CommsStore";
    @Inject
    Lazy<BilobaMetricsService> bilobaMetricsService;
    @Inject
    Lazy<CommsApi> commsApi;
    @Inject
    Lazy<CrashMetadata> crashMetadata;
    @Inject
    Lazy<CrashReporter> crashReporter;
    private MutableLiveData<EmergencyAddress> emergencyAddress;
    private MutableLiveData<EmergencyContact> emergencyContact;
    private MutableLiveData<String> emergencyHelplineStatus;
    @Inject
    Lazy<EmergencySettingsApi> emergencySettingsApi;

    /* loaded from: classes6.dex */
    public static final class EmergencyHelplineStatus {
        public static final String NOT_ACTIVE = "NOT_ACTIVE";
        public static final String VERIFIED = "VERIFIED";

        private EmergencyHelplineStatus() {
        }
    }

    @Inject
    public CommsStore() {
        this.emergencyContact = new MutableLiveData<>();
        this.emergencyHelplineStatus = new MutableLiveData<>();
        this.emergencyAddress = new MutableLiveData<>();
    }

    public LiveData<EmergencyAddress> getEmergencyAddress() {
        return this.emergencyAddress;
    }

    public LiveData<EmergencyContact> getEmergencyContact() {
        return this.emergencyContact;
    }

    public LiveData<String> getEmergencyHelplineStatus() {
        return this.emergencyHelplineStatus;
    }

    public /* synthetic */ void lambda$requestEmergencyContact$0$CommsStore(MobilyticsMetricsTimer mobilyticsMetricsTimer, EmergencyContact emergencyContact) {
        this.crashMetadata.mo358get().put("fetch_emergency_contact_success", true);
        this.emergencyContact.setValue(emergencyContact);
        postError(null);
        setIsLoading(false);
        this.bilobaMetricsService.mo358get().finishTimer(mobilyticsMetricsTimer);
        this.bilobaMetricsService.mo358get().recordOccurrence("getEmergencyContactApi.Success", true);
    }

    public /* synthetic */ void lambda$requestEmergencyContact$1$CommsStore(Throwable th) {
        LogUtils.e(TAG, th.getMessage());
        this.crashMetadata.mo358get().put("fetch_emergency_contact_success", false);
        this.crashReporter.mo358get().reportNonFatal(th);
        this.emergencyContact.setValue(null);
        postError(th);
        setIsLoading(false);
        this.bilobaMetricsService.mo358get().recordOccurrence("getEmergencyContactApi.Success", false);
    }

    public /* synthetic */ void lambda$requestEmergencySettings$2$CommsStore(MobilyticsMetricsTimer mobilyticsMetricsTimer, EmergencySettingsResponse emergencySettingsResponse) {
        this.crashMetadata.mo358get().put("fetch_emergency_settings_success", true);
        this.emergencyHelplineStatus.setValue(emergencySettingsResponse.getEmergencyHelplineStatus());
        this.emergencyAddress.setValue(emergencySettingsResponse.getEmergencyAddress());
        postError(null);
        setIsLoading(false);
        this.bilobaMetricsService.mo358get().finishTimer(mobilyticsMetricsTimer);
        this.bilobaMetricsService.mo358get().recordOccurrence("getEmergencySettingsApi.Success", true);
    }

    public /* synthetic */ void lambda$requestEmergencySettings$3$CommsStore(Throwable th) {
        LogUtils.e(TAG, th.getMessage());
        this.crashMetadata.mo358get().put("fetch_emergency_settings_success", false);
        this.crashReporter.mo358get().reportNonFatal(th);
        this.emergencyHelplineStatus.setValue(null);
        this.emergencyAddress.setValue(null);
        postError(th);
        setIsLoading(false);
        this.bilobaMetricsService.mo358get().recordOccurrence("getEmergencySettingsApi.Success", false);
    }

    public void requestEmergencyContact(String str) {
        setIsLoading(true);
        LogUtils.i(TAG, "request for emergency contact");
        final MobilyticsMetricsTimer startTimer = this.bilobaMetricsService.mo358get().startTimer("getEmergencyContactApi.Time");
        this.commsApi.mo358get().emergencyContactObservable(str).subscribe(new Action1() { // from class: com.amazon.alexa.biloba.storage.-$$Lambda$CommsStore$nH7Ki1GMhYO2dN4mLNZ5hbnrqNE
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                CommsStore.this.lambda$requestEmergencyContact$0$CommsStore(startTimer, (EmergencyContact) obj);
            }
        }, new Action1() { // from class: com.amazon.alexa.biloba.storage.-$$Lambda$CommsStore$7Hcseau9vAg4tEIGM4akxGbQsGo
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                CommsStore.this.lambda$requestEmergencyContact$1$CommsStore((Throwable) obj);
            }
        });
    }

    public void requestEmergencySettings(String str) {
        setIsLoading(true);
        LogUtils.i(TAG, "request for emergency settings");
        final MobilyticsMetricsTimer startTimer = this.bilobaMetricsService.mo358get().startTimer("getEmergencySettingsApi.Time");
        this.emergencySettingsApi.mo358get().getEmergencySettingsObservable(str).subscribe(new Action1() { // from class: com.amazon.alexa.biloba.storage.-$$Lambda$CommsStore$3LQawowgupYJZdRbXFHqe-GVSm8
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                CommsStore.this.lambda$requestEmergencySettings$2$CommsStore(startTimer, (EmergencySettingsResponse) obj);
            }
        }, new Action1() { // from class: com.amazon.alexa.biloba.storage.-$$Lambda$CommsStore$xYb15Sd-tFTr40Kyhj0VZHFZScQ
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                CommsStore.this.lambda$requestEmergencySettings$3$CommsStore((Throwable) obj);
            }
        });
    }

    @VisibleForTesting
    public CommsStore(Lazy<CommsApi> lazy, Lazy<EmergencySettingsApi> lazy2, Lazy<CrashReporter> lazy3, Lazy<CrashMetadata> lazy4, Lazy<BilobaMetricsService> lazy5) {
        this();
        this.commsApi = lazy;
        this.emergencySettingsApi = lazy2;
        this.crashReporter = lazy3;
        this.crashMetadata = lazy4;
        this.bilobaMetricsService = lazy5;
    }
}
