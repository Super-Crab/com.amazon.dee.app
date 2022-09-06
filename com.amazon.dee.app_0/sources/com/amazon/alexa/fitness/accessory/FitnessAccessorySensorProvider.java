package com.amazon.alexa.fitness.accessory;

import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import com.amazon.alexa.accessory.Accessory;
import com.amazon.alexa.accessory.repositories.fitness.FitnessRepository;
import com.amazon.alexa.accessoryclient.client.accessories.Accessories;
import com.amazon.alexa.accessoryclient.client.accessories.AccessorySession;
import com.amazon.alexa.client.metrics.kinesis.session.client.AppDefaultSessionClient;
import com.amazon.alexa.fitness.api.afits.DataSource;
import com.amazon.alexa.fitness.api.afits.DeviceMetadata;
import com.amazon.alexa.fitness.api.afits.SoftwareMetadata;
import com.amazon.alexa.fitness.api.afx.SensorAvailability;
import com.amazon.alexa.fitness.api.afx.SensorUnavailabilityReason;
import com.amazon.alexa.fitness.api.fitnessSdk.Command;
import com.amazon.alexa.fitness.api.fitnessSdk.SensorError;
import com.amazon.alexa.fitness.api.fitnessSdk.SensorProviderType;
import com.amazon.alexa.fitness.logs.ILog;
import com.amazon.alexa.fitness.metrics.AggregatedMetricsConstants;
import com.amazon.alexa.fitness.metrics.MetricEventFactory;
import com.amazon.alexa.fitness.metrics.MetricEventRecorder;
import com.amazon.alexa.fitness.metrics.MetricsAggregator;
import com.amazon.alexa.fitness.metrics.MetricsClass;
import com.amazon.alexa.fitness.model.device.DeviceType;
import com.amazon.alexa.fitness.sdk.AfxMessageProcessor;
import com.amazon.alexa.fitness.sdk.Sample;
import com.amazon.alexa.fitness.sdk.SampleObserver;
import com.amazon.alexa.fitness.sdk.SensorProvider;
import com.amazon.alexa.fitness.sdk.SensorStateObserver;
import com.amazon.alexa.fitness.service.hds.model.DataSourceType;
import com.amazon.alexa.fitness.session.FitnessAccessorySessionUpdateObserver;
import com.amazon.alexa.fitness.session.FitnessDataHandler;
import com.amazon.alexa.fitness.util.CollectionUtilsKt;
import com.amazon.alexa.fitness.utils.MetricHelperKt;
import com.amazon.alexa.location.utils.MetricsUtil;
import com.amazon.alexa.mobilytics.Mobilytics;
import com.amazon.alexa.mobilytics.event.operational.MobilyticsMetricsTimer;
import com.amazon.alexa.protocols.environment.EnvironmentService;
import com.android.tools.r8.GeneratedOutlineSupport1;
import dagger.Lazy;
import io.reactivex.rxjava3.disposables.Disposable;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import javax.inject.Inject;
import javax.inject.Singleton;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.Ref;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: FitnessAccessorySensorProvider.kt */
@Singleton
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000è\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010%\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0017\u0018\u00002\u00020\u0001B]\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012\u0006\u0010\r\u001a\u00020\u000e\u0012\u0006\u0010\u000f\u001a\u00020\u0010\u0012\u0006\u0010\u0011\u001a\u00020\u0012\u0012\u0006\u0010\u0013\u001a\u00020\u0014\u0012\u0006\u0010\u0015\u001a\u00020\u0016¢\u0006\u0002\u0010\u0017J\u0016\u0010Z\u001a\u0002072\f\u0010[\u001a\b\u0012\u0004\u0012\u00020S0RH\u0016J\u0006\u0010\\\u001a\u00020]J-\u0010^\u001a\u0002072#\u0010_\u001a\u001f\u0012\u0015\u0012\u0013\u0018\u000103¢\u0006\f\b4\u0012\b\b5\u0012\u0004\b\b(6\u0012\u0004\u0012\u00020702H\u0016J9\u0010`\u001a\u0002072#\u0010_\u001a\u001f\u0012\u0015\u0012\u0013\u0018\u000103¢\u0006\f\b4\u0012\b\b5\u0012\u0004\b\b(6\u0012\u0004\u0012\u000207022\n\b\u0002\u00106\u001a\u0004\u0018\u000103H\u0002J\b\u0010a\u001a\u000207H\u0016J\u0010\u0010b\u001a\u0002072\u0006\u0010c\u001a\u00020dH\u0002JC\u0010e\u001a\u0002072\u0006\u0010f\u001a\u00020\u001f2\f\u0010g\u001a\b\u0012\u0004\u0012\u00020!0O2#\u0010_\u001a\u001f\u0012\u0015\u0012\u0013\u0018\u000103¢\u0006\f\b4\u0012\b\b5\u0012\u0004\b\b(6\u0012\u0004\u0012\u00020702H\u0016J\u0016\u0010h\u001a\u0002072\f\u0010[\u001a\b\u0012\u0004\u0012\u00020S0RH\u0016J-\u0010i\u001a\u0002072#\u0010_\u001a\u001f\u0012\u0015\u0012\u0013\u0018\u000103¢\u0006\f\b4\u0012\b\b5\u0012\u0004\b\b(6\u0012\u0004\u0012\u00020702H\u0016J5\u0010j\u001a\u0002072\u0006\u0010f\u001a\u00020\u001f2#\u0010_\u001a\u001f\u0012\u0015\u0012\u0013\u0018\u000103¢\u0006\f\b4\u0012\b\b5\u0012\u0004\b\b(6\u0012\u0004\u0012\u00020702H\u0016J-\u0010k\u001a\u0002072#\u0010_\u001a\u001f\u0012\u0015\u0012\u0013\u0018\u000103¢\u0006\f\b4\u0012\b\b5\u0012\u0004\b\b(6\u0012\u0004\u0012\u00020702H\u0016J\u0010\u0010l\u001a\u0002072\u0006\u0010m\u001a\u00020nH\u0002J\f\u0010o\u001a\u00020,*\u00020!H\u0002J\f\u0010p\u001a\u00020,*\u00020\u001bH\u0002R\u000e\u0010\u0015\u001a\u00020\u0016X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0018\u001a\u0004\u0018\u00010\u0019X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u001a\u001a\u0004\u0018\u00010\u001b8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u001c\u0010\u001dR\u0010\u0010\u001e\u001a\u0004\u0018\u00010\u001fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010 \u001a\u0004\u0018\u00010!8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\"\u0010#R\u0014\u0010$\u001a\u00020%8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b&\u0010'R\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010(\u001a\u0004\u0018\u00010)X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010*\u001a\u0004\u0018\u00010)X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010+\u001a\u00020,X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010-\u001a\u000e\u0012\u0004\u0012\u00020!\u0012\u0004\u0012\u00020\u001b0.X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0014X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010/\u001a\u000200X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u0004¢\u0006\u0002\n\u0000R-\u00101\u001a!\u0012\u0015\u0012\u0013\u0018\u000103¢\u0006\f\b4\u0012\b\b5\u0012\u0004\b\b(6\u0012\u0004\u0012\u000207\u0018\u000102X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u00108\u001a\u00020,X\u0096D¢\u0006\b\n\u0000\u001a\u0004\b9\u0010:R\u001c\u0010;\u001a\u0004\u0018\u00010<X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b=\u0010>\"\u0004\b?\u0010@R7\u0010A\u001a\u001f\u0012\u0013\u0012\u00110B¢\u0006\f\b4\u0012\b\b5\u0012\u0004\b\b(C\u0012\u0004\u0012\u000207\u0018\u000102X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bD\u0010E\"\u0004\bF\u0010GR\u0016\u0010H\u001a\u0004\u0018\u00010!8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\bI\u0010#R\u0014\u0010J\u001a\u00020KX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\bL\u0010MR\u0016\u0010N\u001a\n\u0012\u0004\u0012\u00020!\u0018\u00010OX\u0082\u000e¢\u0006\u0002\n\u0000R(\u0010P\u001a\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u00020S0R\u0018\u00010QX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bT\u0010U\"\u0004\bV\u0010WR\u001c\u0010X\u001a\u0010\u0012\f\u0012\n Y*\u0004\u0018\u00010\u00000\u00000RX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006q"}, d2 = {"Lcom/amazon/alexa/fitness/accessory/FitnessAccessorySensorProvider;", "Lcom/amazon/alexa/fitness/sdk/SensorProvider;", "fitnessAccessorySessionMonitor", "Lcom/amazon/alexa/fitness/accessory/FitnessAccessorySessionMonitor;", "fitnessAccessoryCommandHandler", "Ldagger/Lazy;", "Lcom/amazon/alexa/fitness/accessory/FitnessAccessoryCommandHandler;", "afxMessageProcessor", "Lcom/amazon/alexa/fitness/sdk/AfxMessageProcessor;", "metrics", "Lcom/amazon/alexa/mobilytics/Mobilytics;", "metricEventRecorder", "Lcom/amazon/alexa/fitness/metrics/MetricEventRecorder;", "metricEventFactory", "Lcom/amazon/alexa/fitness/metrics/MetricEventFactory;", "fitnessDataHandler", "Lcom/amazon/alexa/fitness/session/FitnessDataHandler;", "metricsAggregator", "Lcom/amazon/alexa/fitness/metrics/MetricsAggregator;", "log", "Lcom/amazon/alexa/fitness/logs/ILog;", "accessories", "Lcom/amazon/alexa/accessoryclient/client/accessories/Accessories;", "(Lcom/amazon/alexa/fitness/accessory/FitnessAccessorySessionMonitor;Ldagger/Lazy;Lcom/amazon/alexa/fitness/sdk/AfxMessageProcessor;Lcom/amazon/alexa/mobilytics/Mobilytics;Lcom/amazon/alexa/fitness/metrics/MetricEventRecorder;Lcom/amazon/alexa/fitness/metrics/MetricEventFactory;Lcom/amazon/alexa/fitness/session/FitnessDataHandler;Lcom/amazon/alexa/fitness/metrics/MetricsAggregator;Lcom/amazon/alexa/fitness/logs/ILog;Lcom/amazon/alexa/accessoryclient/client/accessories/Accessories;)V", "activeFitnessAccessory", "Lcom/amazon/alexa/fitness/accessory/FitnessAccessory;", MetricsUtil.LegacyMetricTypes.AVAILABILITY, "Lcom/amazon/alexa/fitness/api/afx/SensorAvailability;", "getAvailability", "()Lcom/amazon/alexa/fitness/api/afx/SensorAvailability;", "currentSessionID", "Ljava/util/UUID;", "deviceTypeInUse", "", "getDeviceTypeInUse", "()Ljava/lang/String;", "deviceTypeInUseFirmwareVersion", "", "getDeviceTypeInUseFirmwareVersion", "()Ljava/lang/Integer;", "fitnessAccessorySessionUpdateObserver", "Lio/reactivex/rxjava3/disposables/Disposable;", "fitnessDataAvailableObserver", "isWorkoutInProgress", "", "lastKnownSensorAvailability", "", "mainHandler", "Landroid/os/Handler;", "recoverySensorCallback", "Lkotlin/Function1;", "Lcom/amazon/alexa/fitness/api/fitnessSdk/SensorError;", "Lkotlin/ParameterName;", "name", "error", "", "required", "getRequired", "()Z", "sampleObserver", "Lcom/amazon/alexa/fitness/sdk/SampleObserver;", "getSampleObserver", "()Lcom/amazon/alexa/fitness/sdk/SampleObserver;", "setSampleObserver", "(Lcom/amazon/alexa/fitness/sdk/SampleObserver;)V", "sensorCommandReceiver", "Lcom/amazon/alexa/fitness/api/fitnessSdk/Command;", "sensorCommand", "getSensorCommandReceiver", "()Lkotlin/jvm/functions/Function1;", "setSensorCommandReceiver", "(Lkotlin/jvm/functions/Function1;)V", "sensorInUse", "getSensorInUse", "sensorProviderType", "Lcom/amazon/alexa/fitness/api/fitnessSdk/SensorProviderType;", "getSensorProviderType", "()Lcom/amazon/alexa/fitness/api/fitnessSdk/SensorProviderType;", "sensorsToBeRecovered", "", "stateObservers", "", "Ljava/lang/ref/WeakReference;", "Lcom/amazon/alexa/fitness/sdk/SensorStateObserver;", "getStateObservers", "()Ljava/util/List;", "setStateObservers", "(Ljava/util/List;)V", "weakThis", "kotlin.jvm.PlatformType", "addObserver", "observer", "getDataSource", "Lcom/amazon/alexa/fitness/api/afits/DataSource;", "pause", "callback", "postResultCallbackToAfx", "prepare", "publishSample", "sample", "Lcom/amazon/alexa/fitness/sdk/Sample;", "recover", AppDefaultSessionClient.CRASH_REPORTER_SESSION_ID_KEY, "sensorIds", "removeObserver", "resume", "start", "stop", "subscribeToNotifications", "accessorySession", "Lcom/amazon/alexa/accessoryclient/client/accessories/AccessorySession;", "isArmstrongDevice", "isSensorAvailable", "AlexaMobileAndroidFitnessExtension_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes7.dex */
public class FitnessAccessorySensorProvider implements SensorProvider {
    private final Accessories accessories;
    private FitnessAccessory activeFitnessAccessory;
    private final AfxMessageProcessor afxMessageProcessor;
    private UUID currentSessionID;
    private final Lazy<FitnessAccessoryCommandHandler> fitnessAccessoryCommandHandler;
    private final FitnessAccessorySessionMonitor fitnessAccessorySessionMonitor;
    private Disposable fitnessAccessorySessionUpdateObserver;
    private Disposable fitnessDataAvailableObserver;
    private final FitnessDataHandler fitnessDataHandler;
    private boolean isWorkoutInProgress;
    private Map<String, SensorAvailability> lastKnownSensorAvailability;
    private final ILog log;
    private final Handler mainHandler;
    private final MetricEventFactory metricEventFactory;
    private final MetricEventRecorder metricEventRecorder;
    private final Mobilytics metrics;
    private final MetricsAggregator metricsAggregator;
    private Function1<? super SensorError, Unit> recoverySensorCallback;
    private final boolean required;
    @Nullable
    private SampleObserver sampleObserver;
    @Nullable
    private Function1<? super Command, Unit> sensorCommandReceiver;
    @NotNull
    private final SensorProviderType sensorProviderType;
    private List<String> sensorsToBeRecovered;
    @Nullable
    private List<WeakReference<SensorStateObserver>> stateObservers;
    private final WeakReference<FitnessAccessorySensorProvider> weakThis;

    @Inject
    public FitnessAccessorySensorProvider(@NotNull FitnessAccessorySessionMonitor fitnessAccessorySessionMonitor, @NotNull Lazy<FitnessAccessoryCommandHandler> fitnessAccessoryCommandHandler, @NotNull AfxMessageProcessor afxMessageProcessor, @NotNull Mobilytics metrics, @NotNull MetricEventRecorder metricEventRecorder, @NotNull MetricEventFactory metricEventFactory, @NotNull FitnessDataHandler fitnessDataHandler, @NotNull MetricsAggregator metricsAggregator, @NotNull ILog log, @NotNull Accessories accessories) {
        Intrinsics.checkParameterIsNotNull(fitnessAccessorySessionMonitor, "fitnessAccessorySessionMonitor");
        Intrinsics.checkParameterIsNotNull(fitnessAccessoryCommandHandler, "fitnessAccessoryCommandHandler");
        Intrinsics.checkParameterIsNotNull(afxMessageProcessor, "afxMessageProcessor");
        Intrinsics.checkParameterIsNotNull(metrics, "metrics");
        Intrinsics.checkParameterIsNotNull(metricEventRecorder, "metricEventRecorder");
        Intrinsics.checkParameterIsNotNull(metricEventFactory, "metricEventFactory");
        Intrinsics.checkParameterIsNotNull(fitnessDataHandler, "fitnessDataHandler");
        Intrinsics.checkParameterIsNotNull(metricsAggregator, "metricsAggregator");
        Intrinsics.checkParameterIsNotNull(log, "log");
        Intrinsics.checkParameterIsNotNull(accessories, "accessories");
        this.fitnessAccessorySessionMonitor = fitnessAccessorySessionMonitor;
        this.fitnessAccessoryCommandHandler = fitnessAccessoryCommandHandler;
        this.afxMessageProcessor = afxMessageProcessor;
        this.metrics = metrics;
        this.metricEventRecorder = metricEventRecorder;
        this.metricEventFactory = metricEventFactory;
        this.fitnessDataHandler = fitnessDataHandler;
        this.metricsAggregator = metricsAggregator;
        this.log = log;
        this.accessories = accessories;
        this.mainHandler = new Handler(Looper.getMainLooper());
        this.weakThis = new WeakReference<>(this);
        this.sensorProviderType = SensorProviderType.ACCESSORY;
        this.required = true;
        this.stateObservers = Collections.synchronizedList(new ArrayList());
        this.lastKnownSensorAvailability = new LinkedHashMap();
    }

    private final boolean isArmstrongDevice(@NotNull String str) {
        return DeviceType.ARMSTRONG.getIdentifiers().contains(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean isSensorAvailable(@NotNull SensorAvailability sensorAvailability) {
        return (sensorAvailability instanceof SensorAvailability.Available) || ((sensorAvailability instanceof SensorAvailability.Unavailable) && ((SensorAvailability.Unavailable) sensorAvailability).getReason() == SensorUnavailabilityReason.EarBudsOutOfEar);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void postResultCallbackToAfx(final Function1<? super SensorError, Unit> function1, final SensorError sensorError) {
        this.afxMessageProcessor.post(new Runnable() { // from class: com.amazon.alexa.fitness.accessory.FitnessAccessorySensorProvider$postResultCallbackToAfx$1
            @Override // java.lang.Runnable
            public final void run() {
                Function1.this.mo12165invoke(sensorError);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void postResultCallbackToAfx$default(FitnessAccessorySensorProvider fitnessAccessorySensorProvider, Function1 function1, SensorError sensorError, int i, Object obj) {
        if (obj == null) {
            if ((i & 2) != 0) {
                sensorError = null;
            }
            fitnessAccessorySensorProvider.postResultCallbackToAfx(function1, sensorError);
            return;
        }
        throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: postResultCallbackToAfx");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void publishSample(final Sample sample) {
        this.afxMessageProcessor.post(new Runnable() { // from class: com.amazon.alexa.fitness.accessory.FitnessAccessorySensorProvider$publishSample$1
            @Override // java.lang.Runnable
            public final void run() {
                ILog iLog;
                ILog iLog2;
                SampleObserver sampleObserver = FitnessAccessorySensorProvider.this.getSampleObserver();
                if (sampleObserver != null) {
                    iLog2 = FitnessAccessorySensorProvider.this.log;
                    StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Publishing sample ");
                    outline107.append(sample);
                    ILog.DefaultImpls.debug$default(iLog2, MetricsClass.FITNESS_ACCESSORY_SENSOR_PROVIDER, outline107.toString(), null, 4, null);
                    sampleObserver.onNextSample(sample);
                    return;
                }
                iLog = FitnessAccessorySensorProvider.this.log;
                ILog.DefaultImpls.error$default(iLog, MetricsClass.FITNESS_ACCESSORY_SENSOR_PROVIDER, "missing sample observer while publishing sample", null, 4, null);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void subscribeToNotifications(AccessorySession accessorySession) {
        this.isWorkoutInProgress = true;
        FitnessRepository fitnessRepository = accessorySession.getFitnessRepository();
        Disposable disposable = this.fitnessDataAvailableObserver;
        if (disposable != null) {
            disposable.dispose();
        }
        Disposable disposable2 = this.fitnessAccessorySessionUpdateObserver;
        if (disposable2 != null) {
            disposable2.dispose();
        }
        this.fitnessDataAvailableObserver = (Disposable) fitnessRepository.observeFitnessDataAvailableNotifications().subscribeWith(new FitnessAccessoryDataAvailableObserver(new FitnessAccessorySensorProvider$subscribeToNotifications$$inlined$apply$lambda$1(this, accessorySession), accessorySession, this.fitnessDataHandler, this.metricEventRecorder, this.metricEventFactory, this.metricsAggregator, this.log));
        this.fitnessAccessorySessionUpdateObserver = (Disposable) fitnessRepository.observeFitnessSessionUpdates().subscribeWith(new FitnessAccessorySessionUpdateObserver(this.metricEventFactory, this.metricEventRecorder, getSensorCommandReceiver(), this.log));
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
        FitnessAccessory fitnessAccessory = this.activeFitnessAccessory;
        if (fitnessAccessory != null) {
            return this.lastKnownSensorAvailability.get(fitnessAccessory.getAccessory().getAddress());
        }
        return null;
    }

    @NotNull
    public final DataSource getDataSource() {
        String str;
        String deviceName;
        EnvironmentService environmentService = (EnvironmentService) GeneratedOutlineSupport1.outline20(EnvironmentService.class);
        FitnessAccessory fitnessAccessory = this.activeFitnessAccessory;
        String str2 = "Echo Buds";
        if (fitnessAccessory == null || (str = fitnessAccessory.getDeviceName()) == null) {
            str = str2;
        }
        DeviceMetadata deviceMetadata = new DeviceMetadata(str, "Amazon", String.valueOf(getDeviceTypeInUseFirmwareVersion().intValue()), "");
        FitnessAccessory fitnessAccessory2 = this.activeFitnessAccessory;
        if (fitnessAccessory2 != null && (deviceName = fitnessAccessory2.getDeviceName()) != null) {
            str2 = deviceName;
        }
        String value = DataSourceType.ALEXA_FITNESS_HEADPHONES.getValue();
        String str3 = Build.VERSION.RELEASE;
        Intrinsics.checkExpressionValueIsNotNull(environmentService, "environmentService");
        return new DataSource(str2, value, deviceMetadata, new SoftwareMetadata("Android", str3, environmentService.getVersionName()));
    }

    @Override // com.amazon.alexa.fitness.sdk.SensorProvider
    @Nullable
    public String getDeviceTypeInUse() {
        DeviceType deviceType;
        Set<String> identifiers;
        FitnessAccessory fitnessAccessory = this.activeFitnessAccessory;
        if (fitnessAccessory == null || (deviceType = fitnessAccessory.getDeviceType()) == null || (identifiers = deviceType.getIdentifiers()) == null) {
            return null;
        }
        return (String) CollectionsKt.firstOrNull(identifiers);
    }

    @Override // com.amazon.alexa.fitness.sdk.SensorProvider
    @NotNull
    public Integer getDeviceTypeInUseFirmwareVersion() {
        FitnessAccessory fitnessAccessory = this.activeFitnessAccessory;
        return Integer.valueOf(fitnessAccessory != null ? fitnessAccessory.getFirmwareVersion() : Integer.MAX_VALUE);
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
        Accessory accessory;
        FitnessAccessory fitnessAccessory = this.activeFitnessAccessory;
        if (fitnessAccessory == null || (accessory = fitnessAccessory.getAccessory()) == null) {
            return null;
        }
        return accessory.getAddress();
    }

    @Override // com.amazon.alexa.fitness.sdk.SensorProvider
    @NotNull
    public SensorProviderType getSensorProviderType() {
        return this.sensorProviderType;
    }

    @Override // com.amazon.alexa.fitness.sdk.SensorProvider
    @Nullable
    public List<WeakReference<SensorStateObserver>> getStateObservers() {
        return this.stateObservers;
    }

    @Override // com.amazon.alexa.fitness.sdk.SensorProvider
    public void pause(@NotNull final Function1<? super SensorError, Unit> callback) {
        Intrinsics.checkParameterIsNotNull(callback, "callback");
        final FitnessAccessory fitnessAccessory = this.activeFitnessAccessory;
        if (fitnessAccessory != null) {
            if (!this.fitnessAccessorySessionMonitor.getActiveAccessories().contains(fitnessAccessory)) {
                ILog.DefaultImpls.error$default(this.log, MetricsClass.FITNESS_ACCESSORY_SENSOR_PROVIDER, "No connected fitness accessories when processing pause.", null, 4, null);
                postResultCallbackToAfx(callback, new SensorError.Unavailable());
                return;
            }
            final UUID uuid = this.currentSessionID;
            if (uuid != null) {
                this.mainHandler.post(new Runnable() { // from class: com.amazon.alexa.fitness.accessory.FitnessAccessorySensorProvider$pause$$inlined$let$lambda$1
                    @Override // java.lang.Runnable
                    public final void run() {
                        Lazy lazy;
                        Accessories accessories;
                        lazy = this.fitnessAccessoryCommandHandler;
                        accessories = this.accessories;
                        String address = fitnessAccessory.getAccessory().getAddress();
                        Intrinsics.checkExpressionValueIsNotNull(address, "activeFitnessAccessory.accessory.address");
                        ((FitnessAccessoryCommandHandler) lazy.mo358get()).pause(accessories.getAccessorySession(address), uuid, callback);
                    }
                });
                return;
            } else {
                postResultCallbackToAfx(callback, new SensorError.InvalidCommand());
                return;
            }
        }
        postResultCallbackToAfx(callback, new SensorError.Unavailable());
    }

    @Override // com.amazon.alexa.fitness.sdk.SensorProvider
    public void prepare() {
        ILog.DefaultImpls.debug$default(this.log, MetricsClass.FITNESS_ACCESSORY_SENSOR_PROVIDER, "Preparing", null, 4, null);
        this.mainHandler.post(new FitnessAccessorySensorProvider$prepare$1(this));
    }

    @Override // com.amazon.alexa.fitness.sdk.SensorProvider
    public void recover(@NotNull UUID sessionId, @NotNull List<String> sensorIds, @NotNull Function1<? super SensorError, Unit> callback) {
        Object obj;
        Intrinsics.checkParameterIsNotNull(sessionId, "sessionId");
        Intrinsics.checkParameterIsNotNull(sensorIds, "sensorIds");
        Intrinsics.checkParameterIsNotNull(callback, "callback");
        this.currentSessionID = sessionId;
        this.recoverySensorCallback = callback;
        this.sensorsToBeRecovered = sensorIds;
        Runnable runnable = new Runnable() { // from class: com.amazon.alexa.fitness.accessory.FitnessAccessorySensorProvider$recover$recoveryTimeoutRunnable$1
            @Override // java.lang.Runnable
            public final void run() {
                Function1 function1;
                ILog iLog;
                function1 = FitnessAccessorySensorProvider.this.recoverySensorCallback;
                if (function1 != null) {
                    iLog = FitnessAccessorySensorProvider.this.log;
                    ILog.DefaultImpls.error$default(iLog, MetricsClass.FITNESS_ACCESSORY_SENSOR_PROVIDER, "timed out trying to connect to accessory", null, 4, null);
                    FitnessAccessorySensorProvider.this.postResultCallbackToAfx(function1, new SensorError.Unavailable());
                }
                FitnessAccessorySensorProvider.this.recoverySensorCallback = null;
            }
        };
        Iterator<T> it2 = this.fitnessAccessorySessionMonitor.getActiveAccessories().iterator();
        while (true) {
            if (!it2.hasNext()) {
                obj = null;
                break;
            }
            obj = it2.next();
            if (sensorIds.contains(((FitnessAccessory) obj).getAccessory().getAddress())) {
                break;
            }
        }
        FitnessAccessory fitnessAccessory = (FitnessAccessory) obj;
        if (fitnessAccessory != null) {
            this.activeFitnessAccessory = fitnessAccessory;
            postResultCallbackToAfx$default(this, callback, null, 2, null);
            Accessories accessories = this.accessories;
            String address = fitnessAccessory.getAccessory().getAddress();
            Intrinsics.checkExpressionValueIsNotNull(address, "it.accessory.address");
            subscribeToNotifications(accessories.getAccessorySession(address));
            ILog.DefaultImpls.info$default(this.log, MetricsClass.FITNESS_ACCESSORY_SENSOR_PROVIDER, "subscribing after recovery", null, 4, null);
            this.recoverySensorCallback = null;
            this.mainHandler.removeCallbacks(runnable);
        }
        this.mainHandler.postDelayed(runnable, 10000L);
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
    public void resume(@NotNull final Function1<? super SensorError, Unit> callback) {
        Intrinsics.checkParameterIsNotNull(callback, "callback");
        final FitnessAccessory fitnessAccessory = this.activeFitnessAccessory;
        if (fitnessAccessory != null) {
            if (!this.fitnessAccessorySessionMonitor.getActiveAccessories().contains(fitnessAccessory)) {
                ILog.DefaultImpls.error$default(this.log, MetricsClass.FITNESS_ACCESSORY_SENSOR_PROVIDER, "No connected fitness accessories when processing resume.", null, 4, null);
                postResultCallbackToAfx(callback, new SensorError.Unavailable());
                return;
            }
            final UUID uuid = this.currentSessionID;
            if (uuid != null) {
                this.mainHandler.post(new Runnable() { // from class: com.amazon.alexa.fitness.accessory.FitnessAccessorySensorProvider$resume$$inlined$let$lambda$1
                    @Override // java.lang.Runnable
                    public final void run() {
                        Lazy lazy;
                        Accessories accessories;
                        lazy = this.fitnessAccessoryCommandHandler;
                        accessories = this.accessories;
                        String address = fitnessAccessory.getAccessory().getAddress();
                        Intrinsics.checkExpressionValueIsNotNull(address, "activeFitnessAccessory.accessory.address");
                        ((FitnessAccessoryCommandHandler) lazy.mo358get()).resume(accessories.getAccessorySession(address), uuid, callback);
                    }
                });
                return;
            } else {
                postResultCallbackToAfx(callback, new SensorError.InvalidCommand());
                return;
            }
        }
        postResultCallbackToAfx(callback, new SensorError.Unavailable());
    }

    @Override // com.amazon.alexa.fitness.sdk.SensorProvider
    public void setSampleObserver(@Nullable SampleObserver sampleObserver) {
        this.sampleObserver = sampleObserver;
    }

    @Override // com.amazon.alexa.fitness.sdk.SensorProvider
    public void setSensorCommandReceiver(@Nullable Function1<? super Command, Unit> function1) {
        this.sensorCommandReceiver = function1;
    }

    @Override // com.amazon.alexa.fitness.sdk.SensorProvider
    public void setStateObservers(@Nullable List<WeakReference<SensorStateObserver>> list) {
        this.stateObservers = list;
    }

    @Override // com.amazon.alexa.fitness.sdk.SensorProvider
    public void start(@NotNull final UUID sessionId, @NotNull final Function1<? super SensorError, Unit> callback) {
        Intrinsics.checkParameterIsNotNull(sessionId, "sessionId");
        Intrinsics.checkParameterIsNotNull(callback, "callback");
        this.currentSessionID = sessionId;
        final FitnessAccessory fitnessAccessory = (FitnessAccessory) CollectionsKt.firstOrNull(CollectionUtilsKt.ifEmpty(this.fitnessAccessorySessionMonitor.getActiveAccessories(), new FitnessAccessorySensorProvider$start$1(this, sessionId, callback)));
        if (fitnessAccessory != null) {
            Accessories accessories = this.accessories;
            String address = fitnessAccessory.getAccessory().getAddress();
            Intrinsics.checkExpressionValueIsNotNull(address, "fitnessAccessory.accessory.address");
            final AccessorySession accessorySession = accessories.getAccessorySession(address);
            final Ref.BooleanRef booleanRef = new Ref.BooleanRef();
            booleanRef.element = false;
            String deviceTypeInUse = getDeviceTypeInUse();
            if (deviceTypeInUse != null && isArmstrongDevice(deviceTypeInUse) && fitnessAccessory.getFirmwareVersion() < 551881610) {
                booleanRef.element = true;
                ILog iLog = this.log;
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("The current FW ");
                outline107.append(fitnessAccessory.getFirmwareVersion());
                outline107.append(" is too old to be supported");
                ILog.DefaultImpls.error$default(iLog, MetricsClass.FITNESS_ACCESSORY_SENSOR_PROVIDER, outline107.toString(), null, 4, null);
            }
            final MobilyticsMetricsTimer createTimer = MetricHelperKt.createTimer(this.metrics, AggregatedMetricsConstants.Companion.getACCESSORY_START_COMMAND_LATENCY());
            this.mainHandler.post(new Runnable() { // from class: com.amazon.alexa.fitness.accessory.FitnessAccessorySensorProvider$start$$inlined$let$lambda$1

                /* compiled from: FitnessAccessorySensorProvider.kt */
                @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003H\n¢\u0006\u0002\b\u0004¨\u0006\u0005"}, d2 = {"<anonymous>", "", "error", "Lcom/amazon/alexa/fitness/api/fitnessSdk/SensorError;", "invoke", "com/amazon/alexa/fitness/accessory/FitnessAccessorySensorProvider$start$2$1$1"}, k = 3, mv = {1, 1, 16})
                /* renamed from: com.amazon.alexa.fitness.accessory.FitnessAccessorySensorProvider$start$$inlined$let$lambda$1$1  reason: invalid class name */
                /* loaded from: classes7.dex */
                static final class AnonymousClass1 extends Lambda implements Function1<SensorError, Unit> {
                    AnonymousClass1() {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    /* renamed from: invoke */
                    public /* bridge */ /* synthetic */ Unit mo12165invoke(SensorError sensorError) {
                        invoke2(sensorError);
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke  reason: avoid collision after fix types in other method */
                    public final void invoke2(@Nullable SensorError sensorError) {
                        Mobilytics mobilytics;
                        createTimer.finishTimer();
                        if (sensorError == null) {
                            mobilytics = this.metrics;
                            mobilytics.recordTimer(createTimer);
                            FitnessAccessorySensorProvider$start$$inlined$let$lambda$1 fitnessAccessorySensorProvider$start$$inlined$let$lambda$1 = FitnessAccessorySensorProvider$start$$inlined$let$lambda$1.this;
                            this.subscribeToNotifications(accessorySession);
                        }
                        FitnessAccessorySensorProvider$start$$inlined$let$lambda$1 fitnessAccessorySensorProvider$start$$inlined$let$lambda$12 = FitnessAccessorySensorProvider$start$$inlined$let$lambda$1.this;
                        this.postResultCallbackToAfx(callback, sensorError);
                    }
                }

                @Override // java.lang.Runnable
                public final void run() {
                    Lazy lazy;
                    if (Ref.BooleanRef.this.element) {
                        this.postResultCallbackToAfx(callback, new SensorError.Unavailable());
                        return;
                    }
                    this.activeFitnessAccessory = fitnessAccessory;
                    lazy = this.fitnessAccessoryCommandHandler;
                    ((FitnessAccessoryCommandHandler) lazy.mo358get()).start(accessorySession, sessionId, new AnonymousClass1());
                }
            });
        }
    }

    @Override // com.amazon.alexa.fitness.sdk.SensorProvider
    public void stop(@NotNull final Function1<? super SensorError, Unit> callback) {
        Intrinsics.checkParameterIsNotNull(callback, "callback");
        final FitnessAccessory fitnessAccessory = this.activeFitnessAccessory;
        if (fitnessAccessory != null) {
            if (!this.fitnessAccessorySessionMonitor.getActiveAccessories().contains(fitnessAccessory)) {
                ILog.DefaultImpls.error$default(this.log, MetricsClass.FITNESS_ACCESSORY_SENSOR_PROVIDER, "No connected fitness accessories when processing stop.", null, 4, null);
                this.metricsAggregator.incrementCounter(AggregatedMetricsConstants.Companion.getDISCONNECTED_STOP());
                postResultCallbackToAfx(callback, new SensorError.Unavailable());
                return;
            }
            final UUID uuid = this.currentSessionID;
            if (uuid != null) {
                this.mainHandler.post(new Runnable() { // from class: com.amazon.alexa.fitness.accessory.FitnessAccessorySensorProvider$stop$$inlined$let$lambda$1
                    @Override // java.lang.Runnable
                    public final void run() {
                        Lazy lazy;
                        Accessories accessories;
                        lazy = this.fitnessAccessoryCommandHandler;
                        accessories = this.accessories;
                        String address = fitnessAccessory.getAccessory().getAddress();
                        Intrinsics.checkExpressionValueIsNotNull(address, "activeFitnessAccessory.accessory.address");
                        ((FitnessAccessoryCommandHandler) lazy.mo358get()).stop(accessories.getAccessorySession(address), uuid, callback);
                        this.isWorkoutInProgress = false;
                    }
                });
                return;
            } else {
                postResultCallbackToAfx(callback, new SensorError.InvalidCommand());
                return;
            }
        }
        postResultCallbackToAfx(callback, new SensorError.Unavailable());
    }
}
