package com.amazon.alexa.biloba.storage;

import androidx.annotation.VisibleForTesting;
import androidx.lifecycle.MutableLiveData;
import com.amazon.alexa.biloba.dependency.BilobaScope;
import com.amazon.alexa.biloba.metrics.BilobaMetricsService;
import com.amazon.alexa.biloba.model.TimeZoneItem;
import com.amazon.alexa.biloba.model.TimeZoneRegionItem;
import com.amazon.alexa.biloba.model.TimeZones;
import com.amazon.alexa.biloba.service.TimeZoneApi;
import com.amazon.alexa.biloba.utils.LogUtils;
import com.amazon.alexa.crashreporting.api.CrashMetadata;
import com.amazon.alexa.crashreporting.api.CrashReporter;
import com.amazon.alexa.mobilytics.event.operational.MobilyticsMetricsTimer;
import dagger.Lazy;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;
import javax.inject.Singleton;
import rx.functions.Action1;
@Singleton
@BilobaScope
/* loaded from: classes6.dex */
public class TimeZoneStore extends DataStore {
    private static final String TAG = "TimeZoneStore";
    @Inject
    Lazy<BilobaMetricsService> bilobaMetricsService;
    @Inject
    Lazy<CrashMetadata> crashMetadata;
    @Inject
    Lazy<CrashReporter> crashReporter;
    private Map<String, List<TimeZoneItem>> regionToTimeZones;
    @Inject
    Lazy<TimeZoneApi> timeZoneApi;
    private MutableLiveData<List<String>> timeZoneRegions;
    private Map<String, String> timeZoneToRegion;

    @Inject
    public TimeZoneStore() {
        this.timeZoneRegions = new MutableLiveData<>();
        this.regionToTimeZones = new HashMap();
        this.timeZoneToRegion = new HashMap();
    }

    public void convertTimeZonestoMap(TimeZones timeZones) {
        ArrayList arrayList = new ArrayList();
        for (TimeZoneRegionItem timeZoneRegionItem : timeZones.getTimeZones()) {
            TimeZoneItem region = timeZoneRegionItem.getRegion();
            arrayList.add(region.getText());
            List<TimeZoneItem> zones = timeZoneRegionItem.getZones();
            this.regionToTimeZones.put(region.getText(), zones);
            for (TimeZoneItem timeZoneItem : zones) {
                this.timeZoneToRegion.put(timeZoneItem.getValue(), region.getText());
            }
        }
        this.timeZoneRegions.setValue(arrayList);
    }

    public void getAllAvailableTimeZones() {
        LogUtils.i(TAG, "sendRequest for fetch timezones");
        final MobilyticsMetricsTimer startTimer = this.bilobaMetricsService.mo358get().startTimer("getTimeZoneListApi.Time");
        this.timeZoneApi.mo358get().getTimeZonesObservable().subscribe(new Action1() { // from class: com.amazon.alexa.biloba.storage.-$$Lambda$TimeZoneStore$EJUfrBIkMdMkQQ4GAdWZA08n3Ho
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                TimeZoneStore.this.lambda$getAllAvailableTimeZones$0$TimeZoneStore(startTimer, (TimeZones) obj);
            }
        }, new Action1() { // from class: com.amazon.alexa.biloba.storage.-$$Lambda$TimeZoneStore$DN9uTId5Ojk7nG2gsygkChC10UA
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                TimeZoneStore.this.lambda$getAllAvailableTimeZones$1$TimeZoneStore((Throwable) obj);
            }
        });
    }

    public String getRegionForTimeZone(String str) {
        return this.timeZoneToRegion.get(str);
    }

    public int getRegionIndex(String str) {
        if (this.timeZoneRegions.getValue() != null) {
            return this.timeZoneRegions.getValue().indexOf(str);
        }
        return -1;
    }

    public Map<String, List<TimeZoneItem>> getRegionToTimeZones() {
        return this.regionToTimeZones;
    }

    public MutableLiveData<List<String>> getTimeZoneRegions() {
        return this.timeZoneRegions;
    }

    public List<TimeZoneItem> getTimeZonesForRegion(String str) {
        if (this.regionToTimeZones.get(str) == null) {
            return new ArrayList();
        }
        return this.regionToTimeZones.get(str);
    }

    public /* synthetic */ void lambda$getAllAvailableTimeZones$0$TimeZoneStore(MobilyticsMetricsTimer mobilyticsMetricsTimer, TimeZones timeZones) {
        this.crashMetadata.mo358get().put("get_available_time_zones_success", true);
        convertTimeZonestoMap(timeZones);
        postError(null);
        this.bilobaMetricsService.mo358get().finishTimer(mobilyticsMetricsTimer);
        this.bilobaMetricsService.mo358get().recordOccurrence("getTimeZoneListApi.Success", true);
    }

    public /* synthetic */ void lambda$getAllAvailableTimeZones$1$TimeZoneStore(Throwable th) {
        LogUtils.e(TAG, th.getMessage());
        postError(th);
        this.crashMetadata.mo358get().put("get_available_time_zones_success", false);
        this.crashReporter.mo358get().reportNonFatal(th);
        this.bilobaMetricsService.mo358get().recordOccurrence("getTimeZoneListApi.Success", false);
    }

    @VisibleForTesting
    public TimeZoneStore(Lazy<TimeZoneApi> lazy, Lazy<CrashReporter> lazy2, Lazy<CrashMetadata> lazy3, Lazy<BilobaMetricsService> lazy4) {
        this();
        this.timeZoneApi = lazy;
        this.crashReporter = lazy2;
        this.crashMetadata = lazy3;
        this.bilobaMetricsService = lazy4;
        this.timeZoneRegions = new MutableLiveData<>();
        this.regionToTimeZones = new HashMap();
        this.timeZoneToRegion = new HashMap();
    }
}
