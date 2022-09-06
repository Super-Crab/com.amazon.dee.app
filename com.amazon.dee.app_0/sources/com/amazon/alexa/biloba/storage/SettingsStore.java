package com.amazon.alexa.biloba.storage;

import android.os.Bundle;
import androidx.annotation.VisibleForTesting;
import androidx.lifecycle.MutableLiveData;
import com.amazon.alexa.biloba.dependency.BilobaScope;
import com.amazon.alexa.biloba.generated.models.Setting;
import com.amazon.alexa.biloba.generated.models.SettingValueObject;
import com.amazon.alexa.biloba.generated.network.api.SettingsApi;
import com.amazon.alexa.biloba.metrics.BilobaMetricsService;
import com.amazon.alexa.biloba.model.TimeZoneItem;
import com.amazon.alexa.biloba.utils.LanguageUtils;
import com.amazon.alexa.biloba.utils.LogUtils;
import com.amazon.alexa.crashreporting.api.CrashMetadata;
import com.amazon.alexa.crashreporting.api.CrashReporter;
import com.amazon.alexa.featureservice.api.FeatureServiceV2;
import com.amazon.alexa.mobilytics.event.operational.MobilyticsMetricsTimer;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.dee.app.data.api.ElementLocalStorage;
import dagger.Lazy;
import java.util.Locale;
import java.util.TimeZone;
import javax.inject.Inject;
import javax.inject.Singleton;
import rx.functions.Action1;
@Singleton
@BilobaScope
/* loaded from: classes6.dex */
public class SettingsStore extends DataStore {
    private static final String DEFAULT_TIMEZONE = "America/Los_Angeles";
    private static final String TAG = "SettingsStore";
    @Inject
    Lazy<BilobaMetricsService> bilobaMetricsService;
    @Inject
    Lazy<CrashMetadata> crashMetadata;
    @Inject
    Lazy<CrashReporter> crashReporter;
    @Inject
    Lazy<ElementLocalStorage> elementLocalStorage;
    @Inject
    Lazy<FeatureServiceV2> featureServiceV2;
    @Inject
    Lazy<SettingsApi> settingsApi;
    private MutableLiveData<String> timeZone;

    @Inject
    public SettingsStore() {
        this.timeZone = new MutableLiveData<>();
    }

    public MutableLiveData<String> getTimeZone() {
        return this.timeZone;
    }

    public String getTimeZoneDisplayName(MutableLiveData<String> mutableLiveData) {
        if (mutableLiveData.getValue() == null) {
            LogUtils.i(TAG, "TimeZone is null. Sending empty string for timezone");
            return "";
        }
        TimeZone timeZone = TimeZone.getTimeZone(mutableLiveData.getValue());
        String str = TAG;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("posting the value of the display name  of timezone ");
        outline107.append(timeZone.getDisplayName());
        LogUtils.i(str, outline107.toString());
        return timeZone.getDisplayName(LanguageUtils.getAcceptableLanguageTag(Locale.getDefault()));
    }

    public void getTimeZoneSettings(String str) {
        LogUtils.i(TAG, "sendRequest for getting time zone settings through service");
        setIsLoading(true);
        final MobilyticsMetricsTimer startTimer = this.bilobaMetricsService.mo358get().startTimer("getSettingsApi.Time");
        this.settingsApi.mo358get().getSettingObservable(Setting.TypeEnum.TIMEZONE.getValue(), str).subscribe(new Action1() { // from class: com.amazon.alexa.biloba.storage.-$$Lambda$SettingsStore$sJYyZm55afVF9wTuUVQKlMpSpq4
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                SettingsStore.this.lambda$getTimeZoneSettings$0$SettingsStore(startTimer, (Setting) obj);
            }
        }, new Action1() { // from class: com.amazon.alexa.biloba.storage.-$$Lambda$SettingsStore$3d18kW1XIG-f839fY7Y_ChMVZOs
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                SettingsStore.this.lambda$getTimeZoneSettings$1$SettingsStore((Throwable) obj);
            }
        });
    }

    public /* synthetic */ void lambda$getTimeZoneSettings$0$SettingsStore(MobilyticsMetricsTimer mobilyticsMetricsTimer, Setting setting) {
        this.crashMetadata.mo358get().put("get_time_zone_settings_success", true);
        String str = setting != null ? (String) setting.getValue() : DEFAULT_TIMEZONE;
        this.timeZone.setValue(str);
        setIsLoading(false);
        postError(null);
        this.bilobaMetricsService.mo358get().finishTimer(mobilyticsMetricsTimer);
        this.bilobaMetricsService.mo358get().recordOccurrence("getSettingsApi.Success", true);
        saveTimeZoneToElementLocalStorage(str);
    }

    public /* synthetic */ void lambda$getTimeZoneSettings$1$SettingsStore(Throwable th) {
        LogUtils.e(TAG, th.getMessage());
        postError(th);
        this.crashMetadata.mo358get().put("get_time_zone_settings_success", false);
        this.crashReporter.mo358get().reportNonFatal(th);
        setIsLoading(false);
        this.bilobaMetricsService.mo358get().recordOccurrence("getSettingsApi.Success", false);
    }

    public /* synthetic */ void lambda$setTimeZoneSettings$2$SettingsStore(TimeZoneItem timeZoneItem, MobilyticsMetricsTimer mobilyticsMetricsTimer, Void r5) {
        this.crashMetadata.mo358get().put("set_time_zone_settings_success", true);
        this.timeZone.setValue(timeZoneItem.getValue());
        postError(null);
        setIsLoading(false);
        this.bilobaMetricsService.mo358get().finishTimer(mobilyticsMetricsTimer);
        this.bilobaMetricsService.mo358get().recordOccurrence("updateSettingsApi.Success", true);
        saveTimeZoneToElementLocalStorage(timeZoneItem.getValue());
    }

    public /* synthetic */ void lambda$setTimeZoneSettings$3$SettingsStore(Throwable th) {
        postError(th);
        LogUtils.e(TAG, th.getMessage());
        this.crashMetadata.mo358get().put("set_time_zone_settings_success", false);
        this.crashReporter.mo358get().reportNonFatal(th);
        setIsLoading(false);
        this.bilobaMetricsService.mo358get().recordOccurrence("updateSettingsApi.Success", false);
    }

    public void saveTimeZoneToElementLocalStorage(String str) {
        try {
            this.elementLocalStorage.mo358get().put("biloba", ElementLocalStorageKeyStore.BILOBA_CR_TIMEZONE, str, new Bundle()).subscribe();
            String str2 = TAG;
            LogUtils.d(str2, "Successfully put CR timezone in shared elementLocalStorage: " + str);
        } catch (IllegalArgumentException e) {
            String str3 = TAG;
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Error putting timezone in ElementLocalStorage: ");
            outline107.append(e.getMessage());
            LogUtils.e(str3, outline107.toString());
        }
    }

    public void setTimeZoneSettings(String str, final TimeZoneItem timeZoneItem) {
        LogUtils.i(TAG, "sendRequest for setting time zone settings through service");
        SettingValueObject settingValueObject = new SettingValueObject();
        settingValueObject.setValue(timeZoneItem.getValue());
        setIsLoading(true);
        final MobilyticsMetricsTimer startTimer = this.bilobaMetricsService.mo358get().startTimer("updateSettingsApi.Time");
        this.settingsApi.mo358get().setSettingObservable(Setting.TypeEnum.TIMEZONE.getValue(), str, settingValueObject).subscribe(new Action1() { // from class: com.amazon.alexa.biloba.storage.-$$Lambda$SettingsStore$OBh7B2f6a-2uZm80_BJRQHx_GMY
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                SettingsStore.this.lambda$setTimeZoneSettings$2$SettingsStore(timeZoneItem, startTimer, (Void) obj);
            }
        }, new Action1() { // from class: com.amazon.alexa.biloba.storage.-$$Lambda$SettingsStore$P-2Jhm9c6NvnWJcZymVaQYwLfR0
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                SettingsStore.this.lambda$setTimeZoneSettings$3$SettingsStore((Throwable) obj);
            }
        });
    }

    @VisibleForTesting
    public SettingsStore(Lazy<SettingsApi> lazy, Lazy<CrashReporter> lazy2, Lazy<CrashMetadata> lazy3, Lazy<BilobaMetricsService> lazy4, Lazy<ElementLocalStorage> lazy5, Lazy<FeatureServiceV2> lazy6) {
        this();
        this.settingsApi = lazy;
        this.crashReporter = lazy2;
        this.crashMetadata = lazy3;
        this.bilobaMetricsService = lazy4;
        this.timeZone = new MutableLiveData<>();
        this.elementLocalStorage = lazy5;
        this.featureServiceV2 = lazy6;
    }
}
