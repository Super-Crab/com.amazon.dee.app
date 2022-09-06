package com.amazon.alexa.fitness.location;

import android.location.Location;
import android.os.Build;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.client.metrics.kinesis.session.client.AppDefaultSessionClient;
import com.amazon.alexa.fitness.api.LocationService;
import com.amazon.alexa.fitness.api.LocationServiceListener;
import com.amazon.alexa.fitness.api.UserPreferenceKey;
import com.amazon.alexa.fitness.api.UserPreferenceStore;
import com.amazon.alexa.fitness.api.afx.SensorAvailability;
import com.amazon.alexa.fitness.api.afx.SensorUnavailabilityReason;
import com.amazon.alexa.fitness.api.fitnessSdk.Command;
import com.amazon.alexa.fitness.api.fitnessSdk.SensorError;
import com.amazon.alexa.fitness.api.fitnessSdk.SensorProviderType;
import com.amazon.alexa.fitness.api.util.DateTime;
import com.amazon.alexa.fitness.logs.ILog;
import com.amazon.alexa.fitness.metrics.AggregatedMetricsConstants;
import com.amazon.alexa.fitness.metrics.MetricsAggregator;
import com.amazon.alexa.fitness.sdk.AfxMessageProcessor;
import com.amazon.alexa.fitness.sdk.LocationSampleData;
import com.amazon.alexa.fitness.sdk.Sample;
import com.amazon.alexa.fitness.sdk.SampleObserver;
import com.amazon.alexa.fitness.sdk.SensorProvider;
import com.amazon.alexa.fitness.sdk.SensorStateObserver;
import com.amazon.alexa.location.utils.MetricsUtil;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import javax.inject.Inject;
import javax.inject.Singleton;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: LocationSensorProvider.kt */
@Singleton
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000¦\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\b\u0005\b\u0007\u0018\u00002\u00020\u00012\u00020\u0002B/\b\u0007\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\f¢\u0006\u0002\u0010\rJ\u0016\u0010H\u001a\u00020-2\f\u0010I\u001a\b\u0012\u0004\u0012\u00020C0BH\u0016J\b\u0010J\u001a\u00020\u001eH\u0002J\u0010\u0010K\u001a\u00020-2\u0006\u0010L\u001a\u00020\u001eH\u0016J\u0010\u0010M\u001a\u00020-2\u0006\u0010N\u001a\u00020OH\u0016J-\u0010P\u001a\u00020-2#\u0010Q\u001a\u001f\u0012\u0015\u0012\u0013\u0018\u00010R¢\u0006\f\b*\u0012\b\b+\u0012\u0004\b\b(S\u0012\u0004\u0012\u00020-0(H\u0016J9\u0010T\u001a\u00020-2#\u0010Q\u001a\u001f\u0012\u0015\u0012\u0013\u0018\u00010R¢\u0006\f\b*\u0012\b\b+\u0012\u0004\b\b(S\u0012\u0004\u0012\u00020-0(2\n\b\u0002\u0010S\u001a\u0004\u0018\u00010RH\u0002J\b\u0010U\u001a\u00020-H\u0016JC\u0010V\u001a\u00020-2\u0006\u00108\u001a\u0002092\f\u0010W\u001a\b\u0012\u0004\u0012\u00020\u00130X2#\u0010Q\u001a\u001f\u0012\u0015\u0012\u0013\u0018\u00010R¢\u0006\f\b*\u0012\b\b+\u0012\u0004\b\b(S\u0012\u0004\u0012\u00020-0(H\u0016J\u0016\u0010Y\u001a\u00020-2\f\u0010I\u001a\b\u0012\u0004\u0012\u00020C0BH\u0016J-\u0010Z\u001a\u00020-2#\u0010Q\u001a\u001f\u0012\u0015\u0012\u0013\u0018\u00010R¢\u0006\f\b*\u0012\b\b+\u0012\u0004\b\b(S\u0012\u0004\u0012\u00020-0(H\u0016J5\u0010[\u001a\u00020-2\u0006\u00108\u001a\u0002092#\u0010Q\u001a\u001f\u0012\u0015\u0012\u0013\u0018\u00010R¢\u0006\f\b*\u0012\b\b+\u0012\u0004\b\b(S\u0012\u0004\u0012\u00020-0(H\u0016J-\u0010\\\u001a\u00020-2#\u0010Q\u001a\u001f\u0012\u0015\u0012\u0013\u0018\u00010R¢\u0006\f\b*\u0012\b\b+\u0012\u0004\b\b(S\u0012\u0004\u0012\u00020-0(H\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u000e\u001a\u0004\u0018\u00010\u000f8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0010\u0010\u0011R\u0016\u0010\u0012\u001a\u0004\u0018\u00010\u0013X\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0018\u0010\u0016\u001a\u0004\u0018\u00010\u0017X\u0096\u0004¢\u0006\n\n\u0002\u0010\u001a\u001a\u0004\b\u0018\u0010\u0019R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u000b\u001a\u00020\f¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001cR\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u001d\u001a\u00020\u001eX\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010 R\u001c\u0010!\u001a\u0004\u0018\u00010\"X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b#\u0010$\"\u0004\b%\u0010&R7\u0010'\u001a\u001f\u0012\u0013\u0012\u00110)¢\u0006\f\b*\u0012\b\b+\u0012\u0004\b\b(,\u0012\u0004\u0012\u00020-\u0018\u00010(X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b.\u0010/\"\u0004\b0\u00101R\u0016\u00102\u001a\u0004\u0018\u00010\u0013X\u0096D¢\u0006\b\n\u0000\u001a\u0004\b3\u0010\u0015R\u0014\u00104\u001a\u000205X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b6\u00107R&\u00108\u001a\u0004\u0018\u0001098\u0006@\u0006X\u0087\u000e¢\u0006\u0014\n\u0000\u0012\u0004\b:\u0010;\u001a\u0004\b<\u0010=\"\u0004\b>\u0010?R(\u0010@\u001a\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u00020C0B\u0018\u00010AX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bD\u0010E\"\u0004\bF\u0010GR\u000e\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006]"}, d2 = {"Lcom/amazon/alexa/fitness/location/LocationSensorProvider;", "Lcom/amazon/alexa/fitness/sdk/SensorProvider;", "Lcom/amazon/alexa/fitness/api/LocationServiceListener;", "afxMessageProcessor", "Lcom/amazon/alexa/fitness/sdk/AfxMessageProcessor;", "locationService", "Lcom/amazon/alexa/fitness/api/LocationService;", "metricsAggregator", "Lcom/amazon/alexa/fitness/metrics/MetricsAggregator;", "userPreferenceStore", "Lcom/amazon/alexa/fitness/api/UserPreferenceStore;", "log", "Lcom/amazon/alexa/fitness/logs/ILog;", "(Lcom/amazon/alexa/fitness/sdk/AfxMessageProcessor;Lcom/amazon/alexa/fitness/api/LocationService;Lcom/amazon/alexa/fitness/metrics/MetricsAggregator;Lcom/amazon/alexa/fitness/api/UserPreferenceStore;Lcom/amazon/alexa/fitness/logs/ILog;)V", MetricsUtil.LegacyMetricTypes.AVAILABILITY, "Lcom/amazon/alexa/fitness/api/afx/SensorAvailability;", "getAvailability", "()Lcom/amazon/alexa/fitness/api/afx/SensorAvailability;", "deviceTypeInUse", "", "getDeviceTypeInUse", "()Ljava/lang/String;", "deviceTypeInUseFirmwareVersion", "", "getDeviceTypeInUseFirmwareVersion", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getLog", "()Lcom/amazon/alexa/fitness/logs/ILog;", "required", "", "getRequired", "()Z", "sampleObserver", "Lcom/amazon/alexa/fitness/sdk/SampleObserver;", "getSampleObserver", "()Lcom/amazon/alexa/fitness/sdk/SampleObserver;", "setSampleObserver", "(Lcom/amazon/alexa/fitness/sdk/SampleObserver;)V", "sensorCommandReceiver", "Lkotlin/Function1;", "Lcom/amazon/alexa/fitness/api/fitnessSdk/Command;", "Lkotlin/ParameterName;", "name", "sensorCommand", "", "getSensorCommandReceiver", "()Lkotlin/jvm/functions/Function1;", "setSensorCommandReceiver", "(Lkotlin/jvm/functions/Function1;)V", "sensorInUse", "getSensorInUse", "sensorProviderType", "Lcom/amazon/alexa/fitness/api/fitnessSdk/SensorProviderType;", "getSensorProviderType", "()Lcom/amazon/alexa/fitness/api/fitnessSdk/SensorProviderType;", AppDefaultSessionClient.CRASH_REPORTER_SESSION_ID_KEY, "Ljava/util/UUID;", "sessionId$annotations", "()V", "getSessionId", "()Ljava/util/UUID;", "setSessionId", "(Ljava/util/UUID;)V", "stateObservers", "", "Ljava/lang/ref/WeakReference;", "Lcom/amazon/alexa/fitness/sdk/SensorStateObserver;", "getStateObservers", "()Ljava/util/List;", "setStateObservers", "(Ljava/util/List;)V", "addObserver", "observer", "isRouteTrackingEnabled", "onLocationStatusChanged", "isDataAvailable", "onLocationUpdated", "location", "Landroid/location/Location;", "pause", "callback", "Lcom/amazon/alexa/fitness/api/fitnessSdk/SensorError;", "error", "postResultCallbackToAfx", "prepare", "recover", "sensorIds", "", "removeObserver", "resume", "start", "stop", "AlexaMobileAndroidFitnessExtension_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes8.dex */
public final class LocationSensorProvider implements SensorProvider, LocationServiceListener {
    private final AfxMessageProcessor afxMessageProcessor;
    @Nullable
    private final String deviceTypeInUse;
    @Nullable
    private final Integer deviceTypeInUseFirmwareVersion;
    private final LocationService locationService;
    @NotNull
    private final ILog log;
    private final MetricsAggregator metricsAggregator;
    private final boolean required;
    @Nullable
    private SampleObserver sampleObserver;
    @Nullable
    private Function1<? super Command, Unit> sensorCommandReceiver;
    @Nullable
    private final String sensorInUse;
    @NotNull
    private final SensorProviderType sensorProviderType;
    @Nullable
    private UUID sessionId;
    @Nullable
    private List<WeakReference<SensorStateObserver>> stateObservers;
    private final UserPreferenceStore userPreferenceStore;

    @Inject
    public LocationSensorProvider(@NotNull AfxMessageProcessor afxMessageProcessor, @NotNull LocationService locationService, @NotNull MetricsAggregator metricsAggregator, @NotNull UserPreferenceStore userPreferenceStore, @NotNull ILog log) {
        Intrinsics.checkParameterIsNotNull(afxMessageProcessor, "afxMessageProcessor");
        Intrinsics.checkParameterIsNotNull(locationService, "locationService");
        Intrinsics.checkParameterIsNotNull(metricsAggregator, "metricsAggregator");
        Intrinsics.checkParameterIsNotNull(userPreferenceStore, "userPreferenceStore");
        Intrinsics.checkParameterIsNotNull(log, "log");
        this.afxMessageProcessor = afxMessageProcessor;
        this.locationService = locationService;
        this.metricsAggregator = metricsAggregator;
        this.userPreferenceStore = userPreferenceStore;
        this.log = log;
        this.sensorProviderType = SensorProviderType.PHONE_BASED_LOCATION;
        this.sensorInUse = LocationSensorProviderKt.LOCATION_SENSOR_ID;
        this.deviceTypeInUse = LocationSensorProviderKt.LOCATION_SENSOR_ID;
        this.stateObservers = Collections.synchronizedList(new ArrayList());
    }

    private final boolean isRouteTrackingEnabled() {
        return this.userPreferenceStore.get(UserPreferenceKey.IsRouteTrackingEnabled);
    }

    private final void postResultCallbackToAfx(final Function1<? super SensorError, Unit> function1, final SensorError sensorError) {
        this.afxMessageProcessor.post(new Runnable() { // from class: com.amazon.alexa.fitness.location.LocationSensorProvider$postResultCallbackToAfx$1
            @Override // java.lang.Runnable
            public final void run() {
                Function1.this.mo12165invoke(sensorError);
            }
        });
    }

    static /* synthetic */ void postResultCallbackToAfx$default(LocationSensorProvider locationSensorProvider, Function1 function1, SensorError sensorError, int i, Object obj) {
        if ((i & 2) != 0) {
            sensorError = null;
        }
        locationSensorProvider.postResultCallbackToAfx(function1, sensorError);
    }

    @VisibleForTesting
    public static /* synthetic */ void sessionId$annotations() {
    }

    @Override // com.amazon.alexa.fitness.sdk.SensorProvider
    public void addObserver(@NotNull WeakReference<SensorStateObserver> observer) {
        Intrinsics.checkParameterIsNotNull(observer, "observer");
        List<WeakReference<SensorStateObserver>> stateObservers = getStateObservers();
        if (stateObservers != null) {
            boolean z = false;
            if (!stateObservers.isEmpty()) {
                Iterator<T> it2 = stateObservers.iterator();
                while (true) {
                    if (it2.hasNext()) {
                        if (Intrinsics.areEqual((SensorStateObserver) ((WeakReference) it2.next()).get(), observer.get())) {
                            z = true;
                            break;
                        }
                    } else {
                        break;
                    }
                }
            }
            if (z) {
                return;
            }
        }
        List<WeakReference<SensorStateObserver>> stateObservers2 = getStateObservers();
        if (stateObservers2 != null) {
            stateObservers2.add(observer);
        }
    }

    @Override // com.amazon.alexa.fitness.sdk.SensorProvider
    @Nullable
    public SensorAvailability getAvailability() {
        if (isRouteTrackingEnabled() && this.locationService.getAuthorizationStatus() && this.locationService.getLocationStatus()) {
            return SensorAvailability.Available.INSTANCE;
        }
        return new SensorAvailability.Unavailable(SensorUnavailabilityReason.Disconnected);
    }

    @Override // com.amazon.alexa.fitness.sdk.SensorProvider
    @Nullable
    public String getDeviceTypeInUse() {
        return this.deviceTypeInUse;
    }

    @Override // com.amazon.alexa.fitness.sdk.SensorProvider
    @Nullable
    public Integer getDeviceTypeInUseFirmwareVersion() {
        return this.deviceTypeInUseFirmwareVersion;
    }

    @NotNull
    public final ILog getLog() {
        return this.log;
    }

    @Override // com.amazon.alexa.fitness.sdk.SensorProvider
    public boolean getRequired() {
        return this.required;
    }

    @Override // com.amazon.alexa.fitness.sdk.SensorProvider
    @Nullable
    public SampleObserver getSampleObserver() {
        return this.sampleObserver;
    }

    @Override // com.amazon.alexa.fitness.sdk.SensorProvider
    @Nullable
    public Function1<Command, Unit> getSensorCommandReceiver() {
        return this.sensorCommandReceiver;
    }

    @Override // com.amazon.alexa.fitness.sdk.SensorProvider
    @Nullable
    public String getSensorInUse() {
        return this.sensorInUse;
    }

    @Override // com.amazon.alexa.fitness.sdk.SensorProvider
    @NotNull
    public SensorProviderType getSensorProviderType() {
        return this.sensorProviderType;
    }

    @Nullable
    public final UUID getSessionId() {
        return this.sessionId;
    }

    @Override // com.amazon.alexa.fitness.sdk.SensorProvider
    @Nullable
    public List<WeakReference<SensorStateObserver>> getStateObservers() {
        return this.stateObservers;
    }

    @Override // com.amazon.alexa.fitness.api.LocationServiceListener
    public void onLocationStatusChanged(final boolean z) {
        if (this.sessionId != null) {
            this.afxMessageProcessor.post(new Runnable() { // from class: com.amazon.alexa.fitness.location.LocationSensorProvider$onLocationStatusChanged$$inlined$let$lambda$1
                @Override // java.lang.Runnable
                public final void run() {
                    SensorAvailability unavailable = z ? SensorAvailability.Available.INSTANCE : new SensorAvailability.Unavailable(SensorUnavailabilityReason.LocationDataUnavailable);
                    List<WeakReference<SensorStateObserver>> stateObservers = LocationSensorProvider.this.getStateObservers();
                    if (stateObservers != null) {
                        Iterator<T> it2 = stateObservers.iterator();
                        while (it2.hasNext()) {
                            SensorStateObserver sensorStateObserver = (SensorStateObserver) ((WeakReference) it2.next()).get();
                            if (sensorStateObserver != null) {
                                SensorStateObserver.DefaultImpls.onAvailabilityChanged$default(sensorStateObserver, LocationSensorProviderKt.LOCATION_SENSOR_ID, unavailable, DateTime.Companion.now().getEpochMilli(), null, 8, null);
                            }
                        }
                    }
                }
            });
        } else {
            ILog.DefaultImpls.error$default(this.log, "LocationSensorProvider", "location status received when session not present", null, 4, null);
        }
    }

    @Override // com.amazon.alexa.fitness.api.LocationServiceListener
    public void onLocationUpdated(@NotNull final Location location) {
        Intrinsics.checkParameterIsNotNull(location, "location");
        final UUID uuid = this.sessionId;
        if (uuid != null) {
            this.afxMessageProcessor.post(new Runnable() { // from class: com.amazon.alexa.fitness.location.LocationSensorProvider$onLocationUpdated$$inlined$let$lambda$1
                @Override // java.lang.Runnable
                public final void run() {
                    MetricsAggregator metricsAggregator;
                    metricsAggregator = this.metricsAggregator;
                    metricsAggregator.incrementCounter(AggregatedMetricsConstants.Companion.getLOCATION_SAMPLE_RAW_COUNT());
                    int i = Build.VERSION.SDK_INT;
                    float verticalAccuracyMeters = location.getVerticalAccuracyMeters();
                    SampleObserver sampleObserver = this.getSampleObserver();
                    if (sampleObserver != null) {
                        sampleObserver.onNextSample(new Sample.LocationSample(uuid, LocationSensorProviderKt.LOCATION_SENSOR_ID, location.getTime(), new LocationSampleData(location.getLatitude(), location.getLongitude(), location.getAltitude(), location.getAccuracy(), verticalAccuracyMeters, location.getBearing(), location.getSpeed(), location.getTime())));
                    }
                }
            });
        } else {
            ILog.DefaultImpls.error$default(this.log, "LocationSensorProvider", "location received when session not present", null, 4, null);
        }
    }

    @Override // com.amazon.alexa.fitness.sdk.SensorProvider
    public void pause(@NotNull Function1<? super SensorError, Unit> callback) {
        Intrinsics.checkParameterIsNotNull(callback, "callback");
        this.locationService.removeListener(this);
        postResultCallbackToAfx$default(this, callback, null, 2, null);
    }

    @Override // com.amazon.alexa.fitness.sdk.SensorProvider
    public void prepare() {
    }

    @Override // com.amazon.alexa.fitness.sdk.SensorProvider
    public void recover(@NotNull UUID sessionId, @NotNull List<String> sensorIds, @NotNull Function1<? super SensorError, Unit> callback) {
        boolean contains;
        SensorError.Unavailable unavailable;
        Intrinsics.checkParameterIsNotNull(sessionId, "sessionId");
        Intrinsics.checkParameterIsNotNull(sensorIds, "sensorIds");
        Intrinsics.checkParameterIsNotNull(callback, "callback");
        contains = CollectionsKt___CollectionsKt.contains(sensorIds, getSensorInUse());
        if (contains) {
            this.sessionId = sessionId;
            if (!Intrinsics.areEqual(getAvailability(), SensorAvailability.Available.INSTANCE)) {
                ILog.DefaultImpls.error$default(this.log, "LocationSensorProvider", "location not available", null, 4, null);
                unavailable = new SensorError.Unavailable();
            } else {
                unavailable = null;
            }
            postResultCallbackToAfx(callback, unavailable);
            return;
        }
        ILog.DefaultImpls.error$default(this.log, "LocationSensorProvider", "location sensor not in use, not recovering", null, 4, null);
        postResultCallbackToAfx(callback, new SensorError.InvalidCommand());
    }

    @Override // com.amazon.alexa.fitness.sdk.SensorProvider
    public void removeObserver(@NotNull WeakReference<SensorStateObserver> observer) {
        Intrinsics.checkParameterIsNotNull(observer, "observer");
        List<WeakReference<SensorStateObserver>> stateObservers = getStateObservers();
        if (stateObservers != null) {
            stateObservers.remove(observer);
        }
    }

    @Override // com.amazon.alexa.fitness.sdk.SensorProvider
    public void resume(@NotNull Function1<? super SensorError, Unit> callback) {
        Intrinsics.checkParameterIsNotNull(callback, "callback");
        this.locationService.addListener(this);
        postResultCallbackToAfx$default(this, callback, null, 2, null);
    }

    @Override // com.amazon.alexa.fitness.sdk.SensorProvider
    public void setSampleObserver(@Nullable SampleObserver sampleObserver) {
        this.sampleObserver = sampleObserver;
    }

    @Override // com.amazon.alexa.fitness.sdk.SensorProvider
    public void setSensorCommandReceiver(@Nullable Function1<? super Command, Unit> function1) {
        this.sensorCommandReceiver = function1;
    }

    public final void setSessionId(@Nullable UUID uuid) {
        this.sessionId = uuid;
    }

    @Override // com.amazon.alexa.fitness.sdk.SensorProvider
    public void setStateObservers(@Nullable List<WeakReference<SensorStateObserver>> list) {
        this.stateObservers = list;
    }

    @Override // com.amazon.alexa.fitness.sdk.SensorProvider
    public void start(@NotNull UUID sessionId, @NotNull Function1<? super SensorError, Unit> callback) {
        SensorError.Unavailable unavailable;
        Intrinsics.checkParameterIsNotNull(sessionId, "sessionId");
        Intrinsics.checkParameterIsNotNull(callback, "callback");
        if (Intrinsics.areEqual(getAvailability(), SensorAvailability.Available.INSTANCE)) {
            this.sessionId = sessionId;
            this.locationService.addListener(this);
            unavailable = null;
        } else {
            ILog.DefaultImpls.info$default(this.log, "LocationSensorProvider", "route tracking not available, not starting location sensor", null, 4, null);
            unavailable = new SensorError.Unavailable();
        }
        postResultCallbackToAfx(callback, unavailable);
    }

    @Override // com.amazon.alexa.fitness.sdk.SensorProvider
    public void stop(@NotNull Function1<? super SensorError, Unit> callback) {
        Intrinsics.checkParameterIsNotNull(callback, "callback");
        this.locationService.removeListener(this);
        postResultCallbackToAfx$default(this, callback, null, 2, null);
    }
}
