package com.amazon.alexa.fitness.accessory;

import androidx.annotation.MainThread;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.accessory.Accessory;
import com.amazon.alexa.accessory.protocol.Device;
import com.amazon.alexa.accessory.protocol.Firmware;
import com.amazon.alexa.accessoryclient.client.accessories.Accessories;
import com.amazon.alexa.accessoryclient.client.accessories.AccessorySession;
import com.amazon.alexa.accessorykit.ModelTransformer;
import com.amazon.alexa.fitness.api.afx.SensorAvailability;
import com.amazon.alexa.fitness.logs.ILog;
import com.amazon.alexa.fitness.metrics.MetricEvent;
import com.amazon.alexa.fitness.metrics.MetricEventFactory;
import com.amazon.alexa.fitness.metrics.MetricEventRecorder;
import com.amazon.alexa.fitness.metrics.MetricsAggregator;
import com.amazon.alexa.fitness.metrics.MetricsClass;
import com.amazon.alexa.fitness.metrics.MetricsConstantsKt;
import com.amazon.alexa.fitness.metrics.MetricsName;
import com.amazon.alexa.fitness.metrics.MetricsOperation;
import com.amazon.alexa.fitness.model.device.DeviceType;
import com.amazon.alexa.fitness.model.device.DeviceTypeKt;
import com.amazon.alexa.fitness.sdk.AfxMessageProcessor;
import com.amazon.alexa.fitness.util.Callback;
import com.amazon.alexa.fitness.util.DisposableManager;
import com.amazon.alexa.fitness.util.DisposableManagerFactory;
import com.amazon.alexa.fitness.util.RxJavaUtilsKt;
import com.amazon.alexa.location.utils.MetricsUtil;
import com.amazon.dee.app.services.bluetooth.DefaultBluetoothService;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.BiFunction;
import io.reactivex.rxjava3.functions.Consumer;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.locks.ReentrantLock;
import javax.inject.Inject;
import javax.inject.Singleton;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.ArraysKt___ArraysKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.internal.Intrinsics;
import org.aspectj.lang.JoinPoint;
import org.jetbrains.annotations.NotNull;
/* compiled from: FitnessAccessorySessionMonitorImpl.kt */
@Singleton
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000³\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0003\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0004*\u0001\u001e\b\u0007\u0018\u00002\u00020\u0001B?\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r\u0012\u0006\u0010\u000e\u001a\u00020\u000f¢\u0006\u0002\u0010\u0010J4\u00101\u001a\u0002002\u0006\u00102\u001a\u0002032\u0006\u00104\u001a\u00020\u00182\u0006\u0010)\u001a\u00020&2\u0012\u00105\u001a\u000e\u0012\u0004\u0012\u000200\u0012\u0004\u0012\u00020.06H\u0002J,\u00107\u001a\u0002002\u0006\u0010)\u001a\u00020&2\u0006\u0010+\u001a\u00020*2\u0006\u0010-\u001a\u00020,2\n\b\u0002\u0010/\u001a\u0004\u0018\u00010.H\u0002J$\u00108\u001a\u0002002\u0006\u00109\u001a\u00020:2\u0012\u00105\u001a\u000e\u0012\u0004\u0012\u000200\u0012\u0004\u0012\u00020.06H\u0002J<\u0010;\u001a\u0002002\u0006\u0010<\u001a\u00020=2\u0006\u0010)\u001a\u00020&2\u0006\u0010>\u001a\u00020!2\u0006\u00109\u001a\u00020:2\u0012\u00105\u001a\u000e\u0012\u0004\u0012\u000200\u0012\u0004\u0012\u00020.06H\u0002J\u0012\u0010?\u001a\u0004\u0018\u00010\u00132\u0006\u0010)\u001a\u00020&H\u0002Jl\u0010@\u001a\u0002002b\u0010$\u001a^\u0012\u0013\u0012\u00110&¢\u0006\f\b'\u0012\b\b(\u0012\u0004\b\b()\u0012\u0013\u0012\u00110*¢\u0006\f\b'\u0012\b\b(\u0012\u0004\b\b(+\u0012\u0013\u0012\u00110,¢\u0006\f\b'\u0012\b\b(\u0012\u0004\b\b(-\u0012\u0015\u0012\u0013\u0018\u00010.¢\u0006\f\b'\u0012\b\b(\u0012\u0004\b\b(/\u0012\u0004\u0012\u0002000%H\u0017J\u0012\u0010A\u001a\u00020B*\b\u0012\u0004\u0012\u00020=0\u0012H\u0002J\u0012\u0010C\u001a\u00020B*\b\u0012\u0004\u0012\u00020=0\u0012H\u0002J\f\u0010D\u001a\u00020B*\u000203H\u0002J\f\u0010E\u001a\u00020B*\u000203H\u0002R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00130\u00128VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u0015R(\u0010\u0016\u001a\u000e\u0012\u0004\u0012\u00020\u0018\u0012\u0004\u0012\u00020\u00130\u00178\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0019\u0010\u001a\u001a\u0004\b\u001b\u0010\u001cR\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u001d\u001a\u00020\u001eX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u001fR\u000e\u0010 \u001a\u00020!X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\"\u001a\u00020#X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000Rl\u0010$\u001a`\u0012\u0013\u0012\u00110&¢\u0006\f\b'\u0012\b\b(\u0012\u0004\b\b()\u0012\u0013\u0012\u00110*¢\u0006\f\b'\u0012\b\b(\u0012\u0004\b\b(+\u0012\u0013\u0012\u00110,¢\u0006\f\b'\u0012\b\b(\u0012\u0004\b\b(-\u0012\u0015\u0012\u0013\u0018\u00010.¢\u0006\f\b'\u0012\b\b(\u0012\u0004\b\b(/\u0012\u0004\u0012\u000200\u0018\u00010%X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006F"}, d2 = {"Lcom/amazon/alexa/fitness/accessory/FitnessAccessorySessionMonitorImpl;", "Lcom/amazon/alexa/fitness/accessory/FitnessAccessorySessionMonitor;", "disposableManagerFactory", "Lcom/amazon/alexa/fitness/util/DisposableManagerFactory;", "metricEventFactory", "Lcom/amazon/alexa/fitness/metrics/MetricEventFactory;", "metricEventRecorder", "Lcom/amazon/alexa/fitness/metrics/MetricEventRecorder;", "afxMessageProcessor", "Lcom/amazon/alexa/fitness/sdk/AfxMessageProcessor;", "metricsAggregator", "Lcom/amazon/alexa/fitness/metrics/MetricsAggregator;", "log", "Lcom/amazon/alexa/fitness/logs/ILog;", "accessories", "Lcom/amazon/alexa/accessoryclient/client/accessories/Accessories;", "(Lcom/amazon/alexa/fitness/util/DisposableManagerFactory;Lcom/amazon/alexa/fitness/metrics/MetricEventFactory;Lcom/amazon/alexa/fitness/metrics/MetricEventRecorder;Lcom/amazon/alexa/fitness/sdk/AfxMessageProcessor;Lcom/amazon/alexa/fitness/metrics/MetricsAggregator;Lcom/amazon/alexa/fitness/logs/ILog;Lcom/amazon/alexa/accessoryclient/client/accessories/Accessories;)V", "activeAccessories", "", "Lcom/amazon/alexa/fitness/accessory/FitnessAccessory;", "getActiveAccessories", "()Ljava/util/Set;", "activeAccessoriesMap", "Ljava/util/concurrent/ConcurrentMap;", "Lcom/amazon/alexa/fitness/model/device/DeviceType;", "activeAccessoriesMap$annotations", "()V", "getActiveAccessoriesMap", "()Ljava/util/concurrent/ConcurrentMap;", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "com/amazon/alexa/fitness/accessory/FitnessAccessorySessionMonitorImpl$listener$1", "Lcom/amazon/alexa/fitness/accessory/FitnessAccessorySessionMonitorImpl$listener$1;", "localDisposableManager", "Lcom/amazon/alexa/fitness/util/DisposableManager;", JoinPoint.SYNCHRONIZATION_LOCK, "Ljava/util/concurrent/locks/ReentrantLock;", "stateChangeCallback", "Lkotlin/Function4;", "Lcom/amazon/alexa/accessory/Accessory;", "Lkotlin/ParameterName;", "name", ModelTransformer.KEY_ACCESSORY, "Lcom/amazon/alexa/fitness/api/afx/SensorAvailability;", MetricsUtil.LegacyMetricTypes.AVAILABILITY, "", "timestamp", "", "error", "", "handleFitnessAccessoryConnection", DefaultBluetoothService.AUDIO_DEVICE_NAME, "", "deviceType", "callback", "Lcom/amazon/alexa/fitness/util/Callback;", "notifyStateChange", "onDeviceSubscribeFailure", "metricEvent", "Lcom/amazon/alexa/fitness/metrics/MetricEvent;", "onDeviceSubscribeSuccess", "deviceInformation", "Lcom/amazon/alexa/accessory/protocol/Device$DeviceInformation;", "disposableManager", "removeAccessoryFromMap", "startMonitoring", "isAccessoryArmstrongDevice", "", "isAccessoryPresent", "isArmstrongDevice", "isEarBud", "AlexaMobileAndroidFitnessExtension_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes7.dex */
public final class FitnessAccessorySessionMonitorImpl implements FitnessAccessorySessionMonitor {
    private final Accessories accessories;
    @NotNull
    private final ConcurrentMap<DeviceType, FitnessAccessory> activeAccessoriesMap;
    private final AfxMessageProcessor afxMessageProcessor;
    private final DisposableManagerFactory disposableManagerFactory;
    private final FitnessAccessorySessionMonitorImpl$listener$1 listener;
    private final DisposableManager localDisposableManager;
    private final ReentrantLock lock;
    private final ILog log;
    private final MetricEventFactory metricEventFactory;
    private final MetricEventRecorder metricEventRecorder;
    private final MetricsAggregator metricsAggregator;
    private Function4<? super Accessory, ? super SensorAvailability, ? super Long, ? super Throwable, Unit> stateChangeCallback;

    @Inject
    public FitnessAccessorySessionMonitorImpl(@NotNull DisposableManagerFactory disposableManagerFactory, @NotNull MetricEventFactory metricEventFactory, @NotNull MetricEventRecorder metricEventRecorder, @NotNull AfxMessageProcessor afxMessageProcessor, @NotNull MetricsAggregator metricsAggregator, @NotNull ILog log, @NotNull Accessories accessories) {
        Intrinsics.checkParameterIsNotNull(disposableManagerFactory, "disposableManagerFactory");
        Intrinsics.checkParameterIsNotNull(metricEventFactory, "metricEventFactory");
        Intrinsics.checkParameterIsNotNull(metricEventRecorder, "metricEventRecorder");
        Intrinsics.checkParameterIsNotNull(afxMessageProcessor, "afxMessageProcessor");
        Intrinsics.checkParameterIsNotNull(metricsAggregator, "metricsAggregator");
        Intrinsics.checkParameterIsNotNull(log, "log");
        Intrinsics.checkParameterIsNotNull(accessories, "accessories");
        this.disposableManagerFactory = disposableManagerFactory;
        this.metricEventFactory = metricEventFactory;
        this.metricEventRecorder = metricEventRecorder;
        this.afxMessageProcessor = afxMessageProcessor;
        this.metricsAggregator = metricsAggregator;
        this.log = log;
        this.accessories = accessories;
        this.activeAccessoriesMap = new ConcurrentHashMap();
        this.lock = new ReentrantLock();
        this.localDisposableManager = this.disposableManagerFactory.createDisposableManager();
        this.listener = new FitnessAccessorySessionMonitorImpl$listener$1(this);
    }

    @VisibleForTesting
    public static /* synthetic */ void activeAccessoriesMap$annotations() {
    }

    private final void handleFitnessAccessoryConnection(final String str, final DeviceType deviceType, final Accessory accessory, final Callback<Unit, Throwable> callback) {
        if (this.activeAccessoriesMap.containsKey(deviceType)) {
            ILog iLog = this.log;
            ILog.DefaultImpls.debug$default(iLog, MetricsClass.FITNESS_ACCESSORY_SESSION_MONITOR, "Fitness accessory device type is already connected, DeviceType: " + deviceType, null, 4, null);
            Callback.DefaultImpls.onError$default(callback, MetricsConstantsKt.buildMetricName("Accessory", MetricsName.ALREADY_CONNECTED), null, 2, null);
            return;
        }
        Accessories accessories = this.accessories;
        String address = accessory.getAddress();
        Intrinsics.checkExpressionValueIsNotNull(address, "accessory.address");
        AccessorySession accessorySession = accessories.getAccessorySession(address);
        this.activeAccessoriesMap.put(deviceType, new FitnessAccessory(accessory, str, deviceType, 0, 8, null));
        Disposable subscribe = Observable.combineLatest(accessorySession.getFirmwareRepository().queryInformationSet().toObservable(), accessorySession.getDeviceRepository().queryDeviceInformationSet(), new BiFunction<Set<Firmware.FirmwareInformation>, Set<Device.DeviceInformation>, Unit>() { // from class: com.amazon.alexa.fitness.accessory.FitnessAccessorySessionMonitorImpl$handleFitnessAccessoryConnection$1
            @Override // io.reactivex.rxjava3.functions.BiFunction
            public /* bridge */ /* synthetic */ Unit apply(Set<Firmware.FirmwareInformation> set, Set<Device.DeviceInformation> set2) {
                apply2(set, set2);
                return Unit.INSTANCE;
            }

            /* JADX WARN: Code restructure failed: missing block: B:8:0x0034, code lost:
                if (r3.size() > 0) goto L8;
             */
            /* renamed from: apply  reason: avoid collision after fix types in other method */
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public final void apply2(@org.jetbrains.annotations.NotNull java.util.Set<com.amazon.alexa.accessory.protocol.Firmware.FirmwareInformation> r13, @org.jetbrains.annotations.NotNull java.util.Set<com.amazon.alexa.accessory.protocol.Device.DeviceInformation> r14) {
                /*
                    Method dump skipped, instructions count: 252
                    To view this dump add '--comments-level debug' option
                */
                throw new UnsupportedOperationException("Method not decompiled: com.amazon.alexa.fitness.accessory.FitnessAccessorySessionMonitorImpl$handleFitnessAccessoryConnection$1.apply2(java.util.Set, java.util.Set):void");
            }
        }).subscribe(FitnessAccessorySessionMonitorImpl$handleFitnessAccessoryConnection$2.INSTANCE, new Consumer<Throwable>() { // from class: com.amazon.alexa.fitness.accessory.FitnessAccessorySessionMonitorImpl$handleFitnessAccessoryConnection$3
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Throwable th) {
                ILog iLog2;
                iLog2 = FitnessAccessorySessionMonitorImpl.this.log;
                iLog2.error(MetricsClass.FITNESS_ACCESSORY_SESSION_MONITOR, "Error in handleFitnessAccessoryConnection", th);
            }
        });
        Intrinsics.checkExpressionValueIsNotNull(subscribe, "Observable.combineLatest…cessoryConnection\", e) })");
        RxJavaUtilsKt.addTo(subscribe, this.localDisposableManager);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean isAccessoryArmstrongDevice(@NotNull Set<Device.DeviceInformation> set) {
        if (!(set instanceof Collection) || !set.isEmpty()) {
            for (Device.DeviceInformation deviceInformation : set) {
                String deviceType = deviceInformation.getDeviceType();
                Intrinsics.checkExpressionValueIsNotNull(deviceType, "it.deviceType");
                if (isArmstrongDevice(deviceType)) {
                    return true;
                }
            }
            return false;
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:22:0x0048 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean isAccessoryPresent(@org.jetbrains.annotations.NotNull java.util.Set<com.amazon.alexa.accessory.protocol.Device.DeviceInformation> r6) {
        /*
            r5 = this;
            boolean r0 = r6 instanceof java.util.Collection
            r1 = 1
            r2 = 0
            if (r0 == 0) goto Ld
            boolean r0 = r6.isEmpty()
            if (r0 == 0) goto Ld
            goto L49
        Ld:
            java.util.Iterator r6 = r6.iterator()
        L11:
            boolean r0 = r6.hasNext()
            if (r0 == 0) goto L49
            java.lang.Object r0 = r6.next()
            com.amazon.alexa.accessory.protocol.Device$DeviceInformation r0 = (com.amazon.alexa.accessory.protocol.Device.DeviceInformation) r0
            java.lang.String r3 = r0.getDeviceType()
            java.lang.String r4 = "it.deviceType"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r3, r4)
            boolean r3 = r5.isEarBud(r3)
            if (r3 == 0) goto L45
            boolean r3 = r0.hasStatus()
            if (r3 == 0) goto L45
            com.amazon.alexa.accessory.protocol.Device$DeviceStatus r0 = r0.getStatus()
            java.lang.String r3 = "it.status"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r0, r3)
            com.amazon.alexa.accessory.protocol.Device$DevicePresence r0 = r0.getPresence()
            com.amazon.alexa.accessory.protocol.Device$DevicePresence r3 = com.amazon.alexa.accessory.protocol.Device.DevicePresence.DEVICE_PRESENCE_ACTIVE
            if (r0 != r3) goto L45
            r0 = r1
            goto L46
        L45:
            r0 = r2
        L46:
            if (r0 == 0) goto L11
            r2 = r1
        L49:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.alexa.fitness.accessory.FitnessAccessorySessionMonitorImpl.isAccessoryPresent(java.util.Set):boolean");
    }

    private final boolean isArmstrongDevice(@NotNull String str) {
        return DeviceType.ARMSTRONG.getIdentifiers().contains(str);
    }

    private final boolean isEarBud(@NotNull String str) {
        boolean contains;
        contains = ArraysKt___ArraysKt.contains(new String[]{"AS8OPU4NNXJI8", "ALWUIN0P635PT", "A2QDHDQIWC2LTG", "A31PMVIWCRNTX2", "A168KS6Z8QG6RV", "A3KF60B9RDMWLY"}, str);
        return contains;
    }

    private final void notifyStateChange(final Accessory accessory, final SensorAvailability sensorAvailability, final long j, final Throwable th) {
        ILog iLog = this.log;
        iLog.info(MetricsClass.FITNESS_ACCESSORY_SESSION_MONITOR, "Availability changed to " + sensorAvailability, th);
        this.afxMessageProcessor.post(new Runnable() { // from class: com.amazon.alexa.fitness.accessory.FitnessAccessorySessionMonitorImpl$notifyStateChange$1
            @Override // java.lang.Runnable
            public final void run() {
                Function4 function4;
                function4 = FitnessAccessorySessionMonitorImpl.this.stateChangeCallback;
                if (function4 != null) {
                    Unit unit = (Unit) function4.invoke(accessory, sensorAvailability, Long.valueOf(j), th);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void notifyStateChange$default(FitnessAccessorySessionMonitorImpl fitnessAccessorySessionMonitorImpl, Accessory accessory, SensorAvailability sensorAvailability, long j, Throwable th, int i, Object obj) {
        if ((i & 8) != 0) {
            th = null;
        }
        fitnessAccessorySessionMonitorImpl.notifyStateChange(accessory, sensorAvailability, j, th);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void onDeviceSubscribeFailure(MetricEvent metricEvent, Callback<Unit, Throwable> callback) {
        metricEvent.removeTimer(MetricsConstantsKt.buildMetricName(MetricsOperation.ON_RESPONSE, "DeviceInformation", "Success"));
        Callback.DefaultImpls.onError$default(callback, MetricsConstantsKt.buildMetricName("DeviceInformation", "Failure"), null, 2, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void onDeviceSubscribeSuccess(Device.DeviceInformation deviceInformation, Accessory accessory, DisposableManager disposableManager, MetricEvent metricEvent, Callback<Unit, Throwable> callback) {
        ILog iLog = this.log;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Device information received, DeviceType: ");
        outline107.append(deviceInformation.getDeviceType());
        ILog.DefaultImpls.debug$default(iLog, MetricsClass.FITNESS_ACCESSORY_SESSION_MONITOR, outline107.toString(), null, 4, null);
        String deviceType = deviceInformation.getDeviceType();
        Intrinsics.checkExpressionValueIsNotNull(deviceType, "deviceInformation.deviceType");
        DeviceType deviceType2 = DeviceTypeKt.toDeviceType(deviceType);
        if (deviceType2 != null) {
            metricEvent.stopTimer(MetricsConstantsKt.buildMetricName(MetricsOperation.ON_RESPONSE, "DeviceInformation", "Success"));
            String name = deviceInformation.getName();
            Intrinsics.checkExpressionValueIsNotNull(name, "deviceInformation.name");
            handleFitnessAccessoryConnection(name, deviceType2, accessory, callback);
        } else {
            metricEvent.removeTimer(MetricsConstantsKt.buildMetricName(MetricsOperation.ON_RESPONSE, "DeviceInformation", "Success"));
            Callback.DefaultImpls.onError$default(callback, MetricsConstantsKt.buildMetricName("DeviceType", MetricsName.INVALID), null, 2, null);
        }
        disposableManager.clear();
        ILog.DefaultImpls.debug$default(this.log, MetricsClass.FITNESS_ACCESSORY_SESSION_MONITOR, "Subscription to DeviceInformation complete", null, 4, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final FitnessAccessory removeAccessoryFromMap(Accessory accessory) {
        Object obj;
        Iterator<T> it2 = this.activeAccessoriesMap.entrySet().iterator();
        while (true) {
            if (!it2.hasNext()) {
                obj = null;
                break;
            }
            obj = it2.next();
            if (Intrinsics.areEqual(((FitnessAccessory) ((Map.Entry) obj).getValue()).getAccessory().getAddress(), accessory.getAddress())) {
                break;
            }
        }
        Map.Entry entry = (Map.Entry) obj;
        if (entry != null) {
            return this.activeAccessoriesMap.remove(entry.getKey());
        }
        return null;
    }

    @Override // com.amazon.alexa.fitness.accessory.FitnessAccessorySessionMonitor
    @NotNull
    public Set<FitnessAccessory> getActiveAccessories() {
        Set<FitnessAccessory> set;
        set = CollectionsKt___CollectionsKt.toSet(this.activeAccessoriesMap.values());
        return set;
    }

    @NotNull
    public final ConcurrentMap<DeviceType, FitnessAccessory> getActiveAccessoriesMap() {
        return this.activeAccessoriesMap;
    }

    @Override // com.amazon.alexa.fitness.accessory.FitnessAccessorySessionMonitor
    @MainThread
    public void startMonitoring(@NotNull final Function4<? super Accessory, ? super SensorAvailability, ? super Long, ? super Throwable, Unit> stateChangeCallback) {
        Intrinsics.checkParameterIsNotNull(stateChangeCallback, "stateChangeCallback");
        ReentrantLock reentrantLock = this.lock;
        reentrantLock.lock();
        try {
            this.stateChangeCallback = stateChangeCallback;
            ILog.DefaultImpls.debug$default(this.log, MetricsClass.FITNESS_ACCESSORY_SESSION_MONITOR, "Adding accessory session listener...", null, 4, null);
            this.accessories.getSessionSupplier().observeSessionConnected().observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<Accessory>() { // from class: com.amazon.alexa.fitness.accessory.FitnessAccessorySessionMonitorImpl$startMonitoring$$inlined$withLock$lambda$1
                @Override // io.reactivex.rxjava3.functions.Consumer
                public final void accept(Accessory accessory) {
                    FitnessAccessorySessionMonitorImpl$listener$1 fitnessAccessorySessionMonitorImpl$listener$1;
                    fitnessAccessorySessionMonitorImpl$listener$1 = FitnessAccessorySessionMonitorImpl.this.listener;
                    Intrinsics.checkExpressionValueIsNotNull(accessory, "accessory");
                    fitnessAccessorySessionMonitorImpl$listener$1.onAccessorySessionConnected(accessory);
                }
            }, new Consumer<Throwable>() { // from class: com.amazon.alexa.fitness.accessory.FitnessAccessorySessionMonitorImpl$startMonitoring$$inlined$withLock$lambda$2
                @Override // io.reactivex.rxjava3.functions.Consumer
                public final void accept(Throwable th) {
                    ILog iLog;
                    iLog = FitnessAccessorySessionMonitorImpl.this.log;
                    iLog.error(MetricsClass.FITNESS_ACCESSORY_SESSION_MONITOR, "Critical: error in startMonitoring.observeSessionConnected", th);
                }
            });
            this.accessories.getSessionSupplier().observeSessionReleased().observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<Accessory>() { // from class: com.amazon.alexa.fitness.accessory.FitnessAccessorySessionMonitorImpl$startMonitoring$$inlined$withLock$lambda$3
                @Override // io.reactivex.rxjava3.functions.Consumer
                public final void accept(Accessory accessory) {
                    FitnessAccessorySessionMonitorImpl$listener$1 fitnessAccessorySessionMonitorImpl$listener$1;
                    fitnessAccessorySessionMonitorImpl$listener$1 = FitnessAccessorySessionMonitorImpl.this.listener;
                    Intrinsics.checkExpressionValueIsNotNull(accessory, "accessory");
                    fitnessAccessorySessionMonitorImpl$listener$1.onAccessorySessionReleased(accessory);
                }
            }, new Consumer<Throwable>() { // from class: com.amazon.alexa.fitness.accessory.FitnessAccessorySessionMonitorImpl$startMonitoring$$inlined$withLock$lambda$4
                @Override // io.reactivex.rxjava3.functions.Consumer
                public final void accept(Throwable th) {
                    ILog iLog;
                    iLog = FitnessAccessorySessionMonitorImpl.this.log;
                    iLog.error(MetricsClass.FITNESS_ACCESSORY_SESSION_MONITOR, "Critical: error in startMonitoring.observeSessionReleased", th);
                }
            });
            this.accessories.getSessionSupplier().observeSessionFailed().observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<Accessory>() { // from class: com.amazon.alexa.fitness.accessory.FitnessAccessorySessionMonitorImpl$startMonitoring$$inlined$withLock$lambda$5
                @Override // io.reactivex.rxjava3.functions.Consumer
                public final void accept(Accessory accessory) {
                    FitnessAccessorySessionMonitorImpl$listener$1 fitnessAccessorySessionMonitorImpl$listener$1;
                    fitnessAccessorySessionMonitorImpl$listener$1 = FitnessAccessorySessionMonitorImpl.this.listener;
                    Intrinsics.checkExpressionValueIsNotNull(accessory, "accessory");
                    fitnessAccessorySessionMonitorImpl$listener$1.onAccessorySessionFailed(accessory, new Exception("Session Failed"));
                }
            }, new Consumer<Throwable>() { // from class: com.amazon.alexa.fitness.accessory.FitnessAccessorySessionMonitorImpl$startMonitoring$$inlined$withLock$lambda$6
                @Override // io.reactivex.rxjava3.functions.Consumer
                public final void accept(Throwable th) {
                    ILog iLog;
                    iLog = FitnessAccessorySessionMonitorImpl.this.log;
                    iLog.error(MetricsClass.FITNESS_ACCESSORY_SESSION_MONITOR, "Critical: error in startMonitoring.observeSessionFailed", th);
                }
            });
            this.accessories.getSessionSupplier().observeSessionDisconnected().observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<Accessory>() { // from class: com.amazon.alexa.fitness.accessory.FitnessAccessorySessionMonitorImpl$startMonitoring$$inlined$withLock$lambda$7
                @Override // io.reactivex.rxjava3.functions.Consumer
                public final void accept(Accessory accessory) {
                    FitnessAccessorySessionMonitorImpl$listener$1 fitnessAccessorySessionMonitorImpl$listener$1;
                    fitnessAccessorySessionMonitorImpl$listener$1 = FitnessAccessorySessionMonitorImpl.this.listener;
                    Intrinsics.checkExpressionValueIsNotNull(accessory, "accessory");
                    fitnessAccessorySessionMonitorImpl$listener$1.onAccessorySessionDisconnected(accessory);
                }
            }, new Consumer<Throwable>() { // from class: com.amazon.alexa.fitness.accessory.FitnessAccessorySessionMonitorImpl$startMonitoring$$inlined$withLock$lambda$8
                @Override // io.reactivex.rxjava3.functions.Consumer
                public final void accept(Throwable th) {
                    ILog iLog;
                    iLog = FitnessAccessorySessionMonitorImpl.this.log;
                    iLog.error(MetricsClass.FITNESS_ACCESSORY_SESSION_MONITOR, "Critical: error in startMonitoring.observeSessionDisconnected", th);
                }
            });
            this.accessories.getSessionSupplier().getActiveAccessories().observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<List<? extends Accessory>>() { // from class: com.amazon.alexa.fitness.accessory.FitnessAccessorySessionMonitorImpl$startMonitoring$$inlined$withLock$lambda$9
                @Override // io.reactivex.rxjava3.functions.Consumer
                public /* bridge */ /* synthetic */ void accept(List<? extends Accessory> list) {
                    accept2((List<Accessory>) list);
                }

                /* renamed from: accept  reason: avoid collision after fix types in other method */
                public final void accept2(List<Accessory> accessoryList) {
                    FitnessAccessorySessionMonitorImpl$listener$1 fitnessAccessorySessionMonitorImpl$listener$1;
                    Intrinsics.checkExpressionValueIsNotNull(accessoryList, "accessoryList");
                    for (Accessory accessory : accessoryList) {
                        fitnessAccessorySessionMonitorImpl$listener$1 = FitnessAccessorySessionMonitorImpl.this.listener;
                        fitnessAccessorySessionMonitorImpl$listener$1.onAccessorySessionConnected(accessory);
                    }
                }
            }, new Consumer<Throwable>() { // from class: com.amazon.alexa.fitness.accessory.FitnessAccessorySessionMonitorImpl$startMonitoring$$inlined$withLock$lambda$10
                @Override // io.reactivex.rxjava3.functions.Consumer
                public final void accept(Throwable th) {
                    ILog iLog;
                    iLog = FitnessAccessorySessionMonitorImpl.this.log;
                    iLog.error(MetricsClass.FITNESS_ACCESSORY_SESSION_MONITOR, "Critical: error in startMonitoring.getActiveAccessories", th);
                }
            });
        } finally {
            reentrantLock.unlock();
        }
    }
}
