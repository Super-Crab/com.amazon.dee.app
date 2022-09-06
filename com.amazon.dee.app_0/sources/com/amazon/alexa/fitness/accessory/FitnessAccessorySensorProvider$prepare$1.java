package com.amazon.alexa.fitness.accessory;

import android.os.Handler;
import com.amazon.alexa.accessory.Accessory;
import com.amazon.alexa.accessoryclient.client.accessories.Accessories;
import com.amazon.alexa.accessoryclient.client.accessories.AccessorySession;
import com.amazon.alexa.accessoryclient.common.api.ConnectionStatus;
import com.amazon.alexa.accessorykit.ModelTransformer;
import com.amazon.alexa.fitness.api.afx.SensorAvailability;
import com.amazon.alexa.fitness.api.fitnessSdk.SensorError;
import com.amazon.alexa.fitness.logs.ILog;
import com.amazon.alexa.fitness.metrics.MetricsClass;
import com.amazon.alexa.fitness.sdk.SensorStateObserver;
import com.amazon.alexa.location.utils.MetricsUtil;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.functions.Consumer;
import java.lang.ref.WeakReference;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: FitnessAccessorySensorProvider.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "", "run"}, k = 3, mv = {1, 1, 16})
/* loaded from: classes7.dex */
public final class FitnessAccessorySensorProvider$prepare$1 implements Runnable {
    final /* synthetic */ FitnessAccessorySensorProvider this$0;

    /* compiled from: FitnessAccessorySensorProvider.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0003\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\b\u0010\b\u001a\u0004\u0018\u00010\tH\n¢\u0006\u0002\b\n"}, d2 = {"<anonymous>", "", ModelTransformer.KEY_ACCESSORY, "Lcom/amazon/alexa/accessory/Accessory;", MetricsUtil.LegacyMetricTypes.AVAILABILITY, "Lcom/amazon/alexa/fitness/api/afx/SensorAvailability;", "timestamp", "", "error", "", "invoke"}, k = 3, mv = {1, 1, 16})
    /* renamed from: com.amazon.alexa.fitness.accessory.FitnessAccessorySensorProvider$prepare$1$1  reason: invalid class name */
    /* loaded from: classes7.dex */
    static final class AnonymousClass1 extends Lambda implements Function4<Accessory, SensorAvailability, Long, Throwable, Unit> {

        /* JADX INFO: Access modifiers changed from: package-private */
        /* compiled from: FitnessAccessorySensorProvider.kt */
        @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "", "run"}, k = 3, mv = {1, 1, 16})
        /* renamed from: com.amazon.alexa.fitness.accessory.FitnessAccessorySensorProvider$prepare$1$1$3  reason: invalid class name */
        /* loaded from: classes7.dex */
        public static final class AnonymousClass3 implements Runnable {
            final /* synthetic */ Accessory $accessory;
            final /* synthetic */ boolean $isAvailable;

            AnonymousClass3(boolean z, Accessory accessory) {
                this.$isAvailable = z;
                this.$accessory = accessory;
            }

            @Override // java.lang.Runnable
            public final void run() {
                final Function1 function1;
                boolean z;
                ILog iLog;
                Accessories accessories;
                List list;
                Accessories accessories2;
                function1 = FitnessAccessorySensorProvider$prepare$1.this.this$0.recoverySensorCallback;
                if (function1 != null) {
                    list = FitnessAccessorySensorProvider$prepare$1.this.this$0.sensorsToBeRecovered;
                    if (list != null && list.contains(this.$accessory.getAddress()) && this.$isAvailable) {
                        accessories2 = FitnessAccessorySensorProvider$prepare$1.this.this$0.accessories;
                        String address = this.$accessory.getAddress();
                        Intrinsics.checkExpressionValueIsNotNull(address, "accessory.address");
                        final AccessorySession accessorySession = accessories2.getAccessorySession(address);
                        accessorySession.queryConnectionStatus().observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<ConnectionStatus>() { // from class: com.amazon.alexa.fitness.accessory.FitnessAccessorySensorProvider$prepare$1$1$3$$special$$inlined$let$lambda$1
                            @Override // io.reactivex.rxjava3.functions.Consumer
                            public final void accept(ConnectionStatus connectionStatus) {
                                ILog iLog2;
                                ILog iLog3;
                                if (connectionStatus == ConnectionStatus.NONEXISTENT) {
                                    iLog3 = FitnessAccessorySensorProvider$prepare$1.this.this$0.log;
                                    ILog.DefaultImpls.error$default(iLog3, MetricsClass.FITNESS_ACCESSORY_SENSOR_PROVIDER, "null accessory session during recovery", null, 4, null);
                                    FitnessAccessorySensorProvider$prepare$1.this.this$0.postResultCallbackToAfx(Function1.this, new SensorError.Unavailable());
                                    return;
                                }
                                iLog2 = FitnessAccessorySensorProvider$prepare$1.this.this$0.log;
                                ILog.DefaultImpls.info$default(iLog2, MetricsClass.FITNESS_ACCESSORY_SENSOR_PROVIDER, "subscribing after recovery", null, 4, null);
                                FitnessAccessorySensorProvider$prepare$1.this.this$0.subscribeToNotifications(accessorySession);
                                FitnessAccessorySensorProvider.postResultCallbackToAfx$default(FitnessAccessorySensorProvider$prepare$1.this.this$0, Function1.this, null, 2, null);
                            }
                        }, new Consumer<Throwable>() { // from class: com.amazon.alexa.fitness.accessory.FitnessAccessorySensorProvider$prepare$1$1$3$$special$$inlined$let$lambda$2
                            @Override // io.reactivex.rxjava3.functions.Consumer
                            public final void accept(Throwable th) {
                                ILog iLog2;
                                iLog2 = FitnessAccessorySensorProvider$prepare$1.this.this$0.log;
                                iLog2.error(MetricsClass.FITNESS_ACCESSORY_SENSOR_PROVIDER, "Unexpected error in prepare()", th);
                            }
                        });
                    }
                    FitnessAccessorySensorProvider$prepare$1.this.this$0.recoverySensorCallback = null;
                    return;
                }
                FitnessAccessorySensorProvider fitnessAccessorySensorProvider = FitnessAccessorySensorProvider$prepare$1.this.this$0;
                z = fitnessAccessorySensorProvider.isWorkoutInProgress;
                if (!z || !Intrinsics.areEqual(this.$accessory.getAddress(), fitnessAccessorySensorProvider.getSensorInUse()) || !this.$isAvailable) {
                    return;
                }
                iLog = fitnessAccessorySensorProvider.log;
                ILog.DefaultImpls.info$default(iLog, MetricsClass.FITNESS_ACCESSORY_SENSOR_PROVIDER, "accessory re-connected while workout in progress", null, 4, null);
                accessories = fitnessAccessorySensorProvider.accessories;
                String address2 = this.$accessory.getAddress();
                Intrinsics.checkExpressionValueIsNotNull(address2, "accessory.address");
                fitnessAccessorySensorProvider.subscribeToNotifications(accessories.getAccessorySession(address2));
            }
        }

        AnonymousClass1() {
            super(4);
        }

        @Override // kotlin.jvm.functions.Function4
        public /* bridge */ /* synthetic */ Unit invoke(Accessory accessory, SensorAvailability sensorAvailability, Long l, Throwable th) {
            invoke(accessory, sensorAvailability, l.longValue(), th);
            return Unit.INSTANCE;
        }

        public final void invoke(@NotNull Accessory accessory, @NotNull SensorAvailability availability, long j, @Nullable Throwable th) {
            FitnessAccessorySessionMonitor fitnessAccessorySessionMonitor;
            Object obj;
            Map map;
            boolean isSensorAvailable;
            WeakReference weakReference;
            Handler handler;
            List<WeakReference<SensorStateObserver>> stateObservers;
            Intrinsics.checkParameterIsNotNull(accessory, "accessory");
            Intrinsics.checkParameterIsNotNull(availability, "availability");
            FitnessAccessorySensorProvider fitnessAccessorySensorProvider = FitnessAccessorySensorProvider$prepare$1.this.this$0;
            fitnessAccessorySessionMonitor = fitnessAccessorySensorProvider.fitnessAccessorySessionMonitor;
            Iterator<T> it2 = fitnessAccessorySessionMonitor.getActiveAccessories().iterator();
            while (true) {
                if (!it2.hasNext()) {
                    obj = null;
                    break;
                }
                obj = it2.next();
                if (Intrinsics.areEqual(((FitnessAccessory) obj).getAccessory().getAddress(), accessory.getAddress())) {
                    break;
                }
            }
            fitnessAccessorySensorProvider.activeFitnessAccessory = (FitnessAccessory) obj;
            map = FitnessAccessorySensorProvider$prepare$1.this.this$0.lastKnownSensorAvailability;
            String address = accessory.getAddress();
            Intrinsics.checkExpressionValueIsNotNull(address, "accessory.address");
            map.put(address, availability);
            isSensorAvailable = FitnessAccessorySensorProvider$prepare$1.this.this$0.isSensorAvailable(availability);
            weakReference = FitnessAccessorySensorProvider$prepare$1.this.this$0.weakThis;
            FitnessAccessorySensorProvider fitnessAccessorySensorProvider2 = (FitnessAccessorySensorProvider) weakReference.get();
            if (fitnessAccessorySensorProvider2 != null && (stateObservers = fitnessAccessorySensorProvider2.getStateObservers()) != null) {
                Iterator<T> it3 = stateObservers.iterator();
                while (it3.hasNext()) {
                    SensorStateObserver sensorStateObserver = (SensorStateObserver) ((WeakReference) it3.next()).get();
                    if (sensorStateObserver != null) {
                        String address2 = accessory.getAddress();
                        Intrinsics.checkExpressionValueIsNotNull(address2, "accessory.address");
                        sensorStateObserver.onAvailabilityChanged(address2, availability, j, th);
                    }
                }
            }
            handler = FitnessAccessorySensorProvider$prepare$1.this.this$0.mainHandler;
            handler.post(new AnonymousClass3(isSensorAvailable, accessory));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public FitnessAccessorySensorProvider$prepare$1(FitnessAccessorySensorProvider fitnessAccessorySensorProvider) {
        this.this$0 = fitnessAccessorySensorProvider;
    }

    @Override // java.lang.Runnable
    public final void run() {
        FitnessAccessorySessionMonitor fitnessAccessorySessionMonitor;
        fitnessAccessorySessionMonitor = this.this$0.fitnessAccessorySessionMonitor;
        fitnessAccessorySessionMonitor.startMonitoring(new AnonymousClass1());
    }
}
