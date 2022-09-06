package com.amazon.alexa.location.phase3;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import androidx.annotation.NonNull;
import com.amazon.alexa.crashreporting.api.CrashReporter;
import com.amazon.alexa.location.DefaultLocationService;
import com.amazon.alexa.location.LocationManager;
import com.amazon.alexa.location.LocationService;
import com.amazon.alexa.location.TriggeringGeofenceInfo;
import com.amazon.alexa.location.models.ALSGeofence;
import com.amazon.alexa.location.models.GeoFenceStatus;
import com.amazon.alexa.location.phase3.evaluator.LocationEvent;
import com.amazon.alexa.location.phase3.evaluator.LocationEventEvaluator;
import com.amazon.alexa.location.phase3.sensor.AbstractSensor;
import com.amazon.alexa.location.phase3.sensor.activity.ActivitySensorInput;
import com.amazon.alexa.location.phase3.sensor.alexageofence.AlexaGeofenceDetector;
import com.amazon.alexa.location.phase3.sensor.motiondetection.MotionDetector;
import com.amazon.alexa.location.phase3.sensor.motiondetection.MotionEvent;
import com.amazon.alexa.location.phase3.sensor.nativelocation.NativeLocationInput;
import com.amazon.alexa.location.phase3.sensor.osgeofence.OsGeofenceDetector;
import com.amazon.alexa.location.phase3.sensor.osgeofence.OsGeofenceTriggerEvent;
import com.amazon.alexa.location.phase3.sensor.wifidetection.WifiChangeDetector;
import com.amazon.alexa.location.phase3.sensor.wifidetection.WifiDataInput;
import com.amazon.alexa.location.utils.ExceptionRecordUtil;
import com.amazon.alexa.location.utils.MobilyticsUtil;
import com.amazon.alexa.location.utils.WriteToFile;
import com.amazon.alexa.mobilytics.Mobilytics;
import com.amazon.alexa.mobilytics.OwnerIdentifier;
import com.amazon.alexa.protocols.service.api.LazyComponent;
import com.android.tools.r8.GeneratedOutlineSupport1;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Function;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import org.json.JSONObject;
/* loaded from: classes9.dex */
public class Phase3LocationService implements LocationService {
    public static final double EXPONENTIAL_FACTOR = 2.0d;
    public static final int INITIAL_INTERVAL_MILLISECONDS = 1000;
    public static final int MAX_RETRY_TIMES = 10;
    private final ActiveGeofences activeGeofences;
    private final AlexaGeofenceDetector alexaGeofenceDetector;
    private Configuration configuration = Configuration.DISABLED;
    private final ConfigurationManager configurationManager;
    @NonNull
    private final Context context;
    private final LazyComponent<CrashReporter> crashReporter;
    private final DefaultLocationService defaultLocationService;
    private CompositeDisposable disposables;
    private final DwellTracker dwellTracker;
    private final Map<String, AbstractSensor> intentHandlerMap;
    private final LocationEventEvaluator locationEventEvaluator;
    private final LocationManager locationManager;
    private final LazyComponent<Mobilytics> mobilytics;
    private final MotionDetector motionDetector;
    private final OsGeofenceDetector osGeofenceDetector;
    private final SensorController sensorController;
    private final WifiChangeDetector wifiChangeDetector;
    private static final String TAG = "Phase3LocationService";
    private static final String COMPONENT_NAME = MobilyticsUtil.getComponentName(TAG);

    /* renamed from: com.amazon.alexa.location.phase3.Phase3LocationService$1  reason: invalid class name */
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

    public Phase3LocationService(@NonNull DefaultLocationService defaultLocationService, @NonNull ActiveGeofences activeGeofences, @NonNull LocationEventEvaluator locationEventEvaluator, @NonNull ConfigurationManager configurationManager, @NonNull SensorController sensorController, @NonNull LocationManager locationManager, @NonNull OsGeofenceDetector osGeofenceDetector, @NonNull LazyComponent<Mobilytics> lazyComponent, @NonNull AlexaGeofenceDetector alexaGeofenceDetector, @NonNull WifiChangeDetector wifiChangeDetector, @NonNull MotionDetector motionDetector, @NonNull DwellTracker dwellTracker, @NonNull LazyComponent<CrashReporter> lazyComponent2, @NonNull Context context) {
        this.defaultLocationService = defaultLocationService;
        this.activeGeofences = activeGeofences;
        this.locationEventEvaluator = locationEventEvaluator;
        this.configurationManager = configurationManager;
        this.sensorController = sensorController;
        this.intentHandlerMap = sensorController.buildIntentHandlerMap();
        this.locationManager = locationManager;
        this.osGeofenceDetector = osGeofenceDetector;
        this.mobilytics = lazyComponent;
        this.alexaGeofenceDetector = alexaGeofenceDetector;
        this.wifiChangeDetector = wifiChangeDetector;
        this.motionDetector = motionDetector;
        this.dwellTracker = dwellTracker;
        this.crashReporter = lazyComponent2;
        this.context = context;
    }

    private void clearDataFromAllComponents() {
        try {
            this.locationEventEvaluator.clearAllEvents();
            this.dwellTracker.clearData();
            this.alexaGeofenceDetector.clearData();
            this.motionDetector.clearData();
            this.wifiChangeDetector.clearData();
        } catch (LocationDataStoreException e) {
            ExceptionRecordUtil.recordExceptions(TAG, "clearDataFromAllComponents", e, this.crashReporter);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$null$3(String str) throws Throwable {
    }

    private void startPhase3() {
        this.disposables = new CompositeDisposable();
        this.disposables.add(this.locationEventEvaluator.getTriggerSubject().repeat().retryWhen($$Lambda$Phase3LocationService$fXGUCqc5SFoD6lDUnVMCNIJH9QE.INSTANCE).subscribe(new Consumer() { // from class: com.amazon.alexa.location.phase3.-$$Lambda$Phase3LocationService$d_LjHCXNXuPM8gmobgAM7YIYFmI
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                Phase3LocationService.this.lambda$startPhase3$5$Phase3LocationService((TriggeringGeofenceInfo) obj);
            }
        }, new Consumer() { // from class: com.amazon.alexa.location.phase3.-$$Lambda$Phase3LocationService$9rXvG5mByVJvJMskfllfs-mpaXc
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                Phase3LocationService.this.lambda$startPhase3$6$Phase3LocationService((Throwable) obj);
            }
        }));
        Disposable updaterDisposable = this.activeGeofences.getUpdaterDisposable();
        if (updaterDisposable != null) {
            this.disposables.add(updaterDisposable);
        }
    }

    private void stopPhase3() {
        clearDataFromAllComponents();
        this.disposables.dispose();
        this.disposables = null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void transitionConfiguration(Configuration configuration) {
        Mobilytics mo10268get = this.mobilytics.mo10268get();
        String format = String.format(MobilyticsUtil.MetricsID.CONFIGURATION_SET, configuration.toString().toLowerCase(Locale.US));
        String str = COMPONENT_NAME;
        mo10268get.recordOccurrence(format, true, str, str, OwnerIdentifier.APP_LOCATION_LIBRARIES_ANDROID);
        Configuration configuration2 = this.configuration;
        if (configuration2 != configuration) {
            Configuration configuration3 = Configuration.PHASE3;
            if (configuration == configuration3) {
                startPhase3();
            } else if (configuration2 == configuration3) {
                stopPhase3();
            }
            this.configuration = configuration;
        }
    }

    @Override // com.amazon.alexa.location.LocationService
    public Completable clearGeofences() {
        return this.defaultLocationService.clearGeofences();
    }

    @Override // com.amazon.alexa.location.LocationService
    public Single<String> createGeofence(double d, double d2, double d3) {
        return this.defaultLocationService.createGeofence(d, d2, d3);
    }

    @Override // com.amazon.alexa.location.LocationService
    public Single<JSONObject> getGeofenceStates() {
        return this.defaultLocationService.getGeofenceStates();
    }

    @Override // com.amazon.alexa.location.LocationService
    public Single<JSONObject> getRegisteredGeofences() {
        return this.defaultLocationService.getRegisteredGeofences();
    }

    public Completable handleIntent(@NonNull Intent intent) {
        if (this.configuration != Configuration.PHASE3) {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Wrong configuration ");
            outline107.append(this.configuration);
            return Completable.error(new RuntimeException(outline107.toString()));
        }
        try {
            WriteToFile.dumpIntent(this.context, "handleIntent:Intent", intent);
            String action = intent.getAction();
            AbstractSensor abstractSensor = this.intentHandlerMap.get(action);
            if (abstractSensor == null) {
                Log.w(TAG, String.format("Received unrecognized intent %s", action));
                return Completable.complete();
            }
            List<? extends LocationSignal> processInput = abstractSensor.processInput(intent);
            if (processInput.isEmpty()) {
                Log.w(TAG, String.format("No LocationSignal generated from input Intent %s", action));
                return Completable.complete();
            }
            ArrayList arrayList = new ArrayList();
            for (LocationSignal locationSignal : processInput) {
                WriteToFile.dumpAsJson(this.context, "handleIntent:Signal", locationSignal);
                if (locationSignal instanceof NativeLocationInput) {
                    final NativeLocationInput nativeLocationInput = (NativeLocationInput) locationSignal;
                    arrayList.add(Completable.fromAction(new Action() { // from class: com.amazon.alexa.location.phase3.-$$Lambda$Phase3LocationService$sjuEiyYZV8_DtMvDVgM0VSSCsU4
                        @Override // io.reactivex.rxjava3.functions.Action
                        public final void run() {
                            Phase3LocationService.this.lambda$handleIntent$7$Phase3LocationService(nativeLocationInput);
                        }
                    }));
                    Observable<LocationEvent> evaluateEvent = this.alexaGeofenceDetector.evaluateEvent(nativeLocationInput);
                    final LocationEventEvaluator locationEventEvaluator = this.locationEventEvaluator;
                    locationEventEvaluator.getClass();
                    arrayList.add(evaluateEvent.flatMapCompletable(new Function() { // from class: com.amazon.alexa.location.phase3.-$$Lambda$7xzKnHeh7bS3lrweebui5gDnZFs
                        @Override // io.reactivex.rxjava3.functions.Function
                        /* renamed from: apply */
                        public final Object mo10358apply(Object obj) {
                            return LocationEventEvaluator.this.evaluateEvent((LocationEvent) obj);
                        }
                    }));
                    Observable<MotionEvent> evaluateLocationInput = this.motionDetector.evaluateLocationInput(nativeLocationInput);
                    final LocationEventEvaluator locationEventEvaluator2 = this.locationEventEvaluator;
                    locationEventEvaluator2.getClass();
                    arrayList.add(evaluateLocationInput.flatMapCompletable(new Function() { // from class: com.amazon.alexa.location.phase3.-$$Lambda$3l9bx-bFMSXSE0Rs0WlZqbXyMHA
                        @Override // io.reactivex.rxjava3.functions.Function
                        /* renamed from: apply */
                        public final Object mo10358apply(Object obj) {
                            return LocationEventEvaluator.this.evaluateEvent((MotionEvent) obj);
                        }
                    }));
                } else if (locationSignal instanceof ActivitySensorInput) {
                    Observable<MotionEvent> evaluateActivityInput = this.motionDetector.evaluateActivityInput((ActivitySensorInput) locationSignal);
                    final LocationEventEvaluator locationEventEvaluator3 = this.locationEventEvaluator;
                    locationEventEvaluator3.getClass();
                    arrayList.add(evaluateActivityInput.flatMapCompletable(new Function() { // from class: com.amazon.alexa.location.phase3.-$$Lambda$3l9bx-bFMSXSE0Rs0WlZqbXyMHA
                        @Override // io.reactivex.rxjava3.functions.Function
                        /* renamed from: apply */
                        public final Object mo10358apply(Object obj) {
                            return LocationEventEvaluator.this.evaluateEvent((MotionEvent) obj);
                        }
                    }));
                } else if (locationSignal instanceof WifiDataInput) {
                    Observable<LocationEvent> evaluateEvent2 = this.wifiChangeDetector.evaluateEvent((WifiDataInput) locationSignal);
                    final LocationEventEvaluator locationEventEvaluator4 = this.locationEventEvaluator;
                    locationEventEvaluator4.getClass();
                    arrayList.add(evaluateEvent2.flatMapCompletable(new Function() { // from class: com.amazon.alexa.location.phase3.-$$Lambda$7xzKnHeh7bS3lrweebui5gDnZFs
                        @Override // io.reactivex.rxjava3.functions.Function
                        /* renamed from: apply */
                        public final Object mo10358apply(Object obj) {
                            return LocationEventEvaluator.this.evaluateEvent((LocationEvent) obj);
                        }
                    }));
                }
            }
            return Completable.merge(arrayList);
        } catch (Throwable th) {
            return Completable.error(th);
        }
    }

    public /* synthetic */ void lambda$handleIntent$7$Phase3LocationService(NativeLocationInput nativeLocationInput) throws Throwable {
        this.dwellTracker.updateDwellSession(nativeLocationInput);
    }

    public /* synthetic */ void lambda$null$4$Phase3LocationService(Throwable th) throws Throwable {
        ExceptionRecordUtil.recordExceptions(TAG, "defaultLocationService.sendTriggerEvent", th, this.crashReporter);
    }

    public /* synthetic */ void lambda$start$0$Phase3LocationService(Throwable th) throws Throwable {
        ExceptionRecordUtil.recordExceptions(TAG, "Unexpected error by ConfigurationManager:", th, this.crashReporter);
    }

    public /* synthetic */ void lambda$startPhase3$5$Phase3LocationService(TriggeringGeofenceInfo triggeringGeofenceInfo) throws Throwable {
        this.defaultLocationService.sendTriggerEvent(triggeringGeofenceInfo, 1000, 2.0d, 10).subscribe($$Lambda$Phase3LocationService$fptCaUYOK5n0dciqqkEr0gTne0.INSTANCE, new Consumer() { // from class: com.amazon.alexa.location.phase3.-$$Lambda$Phase3LocationService$xCUPBAkEqQJ1t3LB5UhMp3GWDaA
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                Phase3LocationService.this.lambda$null$4$Phase3LocationService((Throwable) obj);
            }
        });
    }

    public /* synthetic */ void lambda$startPhase3$6$Phase3LocationService(Throwable th) throws Throwable {
        ExceptionRecordUtil.recordExceptions(TAG, "locationEventEvaluator.getTriggerSubject()", th, this.crashReporter);
    }

    public /* synthetic */ OsGeofenceTriggerEvent lambda$triggerGeofence$8$Phase3LocationService(OsGeofenceTriggerEvent osGeofenceTriggerEvent) throws Throwable {
        this.locationEventEvaluator.evaluateEvent(osGeofenceTriggerEvent);
        return osGeofenceTriggerEvent;
    }

    @Override // com.amazon.alexa.location.LocationService
    public void recordLocationPermission() {
        this.defaultLocationService.recordLocationPermission();
    }

    @Override // com.amazon.alexa.location.LocationService
    public void recordLocationSetting() {
        this.defaultLocationService.recordLocationSetting();
    }

    @Override // com.amazon.alexa.location.LocationService
    public Completable reportStatusToALS(@NonNull List<GeoFenceStatus> list) {
        return this.defaultLocationService.reportStatusToALS(list);
    }

    @Override // com.amazon.alexa.location.LocationService
    public Single<List<ALSGeofence>> restoreGeofences() {
        return this.defaultLocationService.restoreGeofences();
    }

    @Override // com.amazon.alexa.location.LocationService
    public void start() {
        this.defaultLocationService.start();
        this.configurationManager.start();
        this.configurationManager.onConfigChanged().subscribe(new Consumer() { // from class: com.amazon.alexa.location.phase3.-$$Lambda$Phase3LocationService$o0_e3hjPoWhodn7xn8pWFfiF2d0
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                Phase3LocationService.this.transitionConfiguration((Configuration) obj);
            }
        }, new Consumer() { // from class: com.amazon.alexa.location.phase3.-$$Lambda$Phase3LocationService$g9TfJtq1xn1eeFdnGwrES2OWTAQ
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                Phase3LocationService.this.lambda$start$0$Phase3LocationService((Throwable) obj);
            }
        });
        this.sensorController.setGeofencesObservable(this.defaultLocationService.onGeofencesServed());
        this.sensorController.setConfigurationObservable(this.configurationManager.onConfigChanged());
        this.sensorController.setNeedMoreDataObservable(this.locationEventEvaluator.getNeedMoreDataSubject());
        this.sensorController.start();
        this.motionDetector.start();
        this.alexaGeofenceDetector.restoreStates();
        this.activeGeofences.subscribeToUpstream(this.defaultLocationService.onGeofencesServed());
        this.alexaGeofenceDetector.subscribeToActiveFences(this.activeGeofences);
    }

    @Override // com.amazon.alexa.location.LocationService
    public Single<List<ALSGeofence>> syncGeofence() {
        return this.defaultLocationService.syncGeofence();
    }

    @Override // com.amazon.alexa.location.LocationService
    public Observable<String> triggerGeofence(Intent intent, int i, double d, int i2) {
        int ordinal = this.configuration.ordinal();
        if (ordinal != 1) {
            if (ordinal != 2) {
                return Observable.empty();
            }
            return this.osGeofenceDetector.onTriggerGeofence(intent).map(new Function() { // from class: com.amazon.alexa.location.phase3.-$$Lambda$Phase3LocationService$8GmVB6daP225YvQHMvqwO3Lsiic
                @Override // io.reactivex.rxjava3.functions.Function
                /* renamed from: apply */
                public final Object mo10358apply(Object obj) {
                    return Phase3LocationService.this.lambda$triggerGeofence$8$Phase3LocationService((OsGeofenceTriggerEvent) obj);
                }
            }).map($$Lambda$Phase3LocationService$Pw26v4OqdFF6AeKfEnxARnxsqE.INSTANCE);
        }
        return this.defaultLocationService.triggerGeofence(intent, i, d, i2);
    }

    @Override // com.amazon.alexa.location.LocationService
    public Single<String> updateGeofence(@NonNull String str, double d, double d2, double d3) {
        return this.defaultLocationService.updateGeofence(str, d, d2, d3);
    }
}
