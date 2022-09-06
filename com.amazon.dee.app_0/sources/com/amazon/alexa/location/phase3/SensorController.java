package com.amazon.alexa.location.phase3;

import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.crashreporting.api.CrashReporter;
import com.amazon.alexa.location.models.ALSGeofence;
import com.amazon.alexa.location.phase3.SensorController;
import com.amazon.alexa.location.phase3.sensor.AbstractSensor;
import com.amazon.alexa.location.utils.ExceptionRecordUtil;
import com.amazon.alexa.protocols.service.api.LazyComponent;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.subjects.Subject;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
/* loaded from: classes9.dex */
public class SensorController {
    @VisibleForTesting
    static final int HIGH_ACCURACY_EXPIRATION_TIMER = 300000;
    private static final String TAG = "SensorController";
    private Observable<Configuration> configurationObservable;
    private final LazyComponent<CrashReporter> crashReporter;
    private Observable<List<ALSGeofence>> geofencesObservable;
    @VisibleForTesting
    Observable<Boolean> needMoreDataObservable;
    private final List<AbstractSensor> sensors;
    private SensorState state;

    /* renamed from: com.amazon.alexa.location.phase3.SensorController$1  reason: invalid class name */
    /* loaded from: classes9.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$alexa$location$phase3$Configuration = new int[Configuration.values().length];

        static {
            try {
                $SwitchMap$com$amazon$alexa$location$phase3$Configuration[Configuration.PHASE3.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$alexa$location$phase3$Configuration[Configuration.DEFAULT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    /* loaded from: classes9.dex */
    public enum SensorState {
        OFF,
        DEFAULT,
        NORMAL,
        HIGH_ACCURACY
    }

    public SensorController(List<AbstractSensor> list, @NonNull LazyComponent<CrashReporter> lazyComponent) {
        this.sensors = list;
        this.crashReporter = lazyComponent;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ Boolean lambda$setNeedMoreDataObservable$0(Boolean bool) throws Throwable {
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ SensorState lambda$start$1(List list, Configuration configuration, Boolean bool) throws Throwable {
        if (list.isEmpty()) {
            return SensorState.OFF;
        }
        int ordinal = configuration.ordinal();
        if (ordinal == 1) {
            return SensorState.DEFAULT;
        }
        if (ordinal != 2) {
            return SensorState.OFF;
        }
        return bool.booleanValue() ? SensorState.HIGH_ACCURACY : SensorState.NORMAL;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void transitionState(SensorState sensorState) {
        if (this.state != sensorState) {
            for (AbstractSensor abstractSensor : this.sensors) {
                try {
                    abstractSensor.toggleSensorState(sensorState);
                } catch (Exception e) {
                    Log.e(TAG, "transitionState", e);
                }
            }
            this.state = sensorState;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Map<String, AbstractSensor> buildIntentHandlerMap() {
        HashMap hashMap = new HashMap();
        for (AbstractSensor abstractSensor : this.sensors) {
            for (String str : abstractSensor.getCorrespondingIntentActions()) {
                hashMap.put(str, abstractSensor);
            }
        }
        return hashMap;
    }

    public /* synthetic */ void lambda$start$2$SensorController(Throwable th) throws Throwable {
        ExceptionRecordUtil.recordExceptions(TAG, "transitionState", th, this.crashReporter);
    }

    public void setConfigurationObservable(Observable<Configuration> observable) {
        this.configurationObservable = observable;
    }

    public void setGeofencesObservable(Observable<List<ALSGeofence>> observable) {
        this.geofencesObservable = observable;
    }

    public void setNeedMoreDataObservable(Subject<Boolean> subject) {
        this.needMoreDataObservable = Observable.merge(subject, subject.map($$Lambda$SensorController$hCQIwxF_5SJJrCGZUOfDJGfWbA.INSTANCE).throttleWithTimeout(300000L, TimeUnit.MILLISECONDS)).startWithItem(false);
    }

    public void start() {
        Observable.combineLatest(this.geofencesObservable, this.configurationObservable, this.needMoreDataObservable, $$Lambda$SensorController$t9WJ0HuHqIxhtdaV4NW1ua2hhc.INSTANCE).distinctUntilChanged().subscribe(new Consumer() { // from class: com.amazon.alexa.location.phase3.-$$Lambda$SensorController$hlos-JDkle_6gSVvLWb9mSGZJvo
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                SensorController.this.transitionState((SensorController.SensorState) obj);
            }
        }, new Consumer() { // from class: com.amazon.alexa.location.phase3.-$$Lambda$SensorController$MdhEibFk4H8dyOiOSZ9xosm8Y3Y
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                SensorController.this.lambda$start$2$SensorController((Throwable) obj);
            }
        });
    }
}
