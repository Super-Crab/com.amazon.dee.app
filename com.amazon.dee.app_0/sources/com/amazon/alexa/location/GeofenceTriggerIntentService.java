package com.amazon.alexa.location;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.alexa.crashreporting.api.CrashReporter;
import com.amazon.alexa.featureservice.api.FeatureServiceV2;
import com.amazon.alexa.location.utils.DebugUtil;
import com.amazon.alexa.location.utils.ExceptionRecordUtil;
import com.amazon.alexa.location.utils.MetricsUtil;
import com.amazon.alexa.location.utils.MobilyticsUtil;
import com.amazon.alexa.location.utils.NotificationUtil;
import com.amazon.alexa.location.utils.WriteToFile;
import com.amazon.alexa.mobilytics.Mobilytics;
import com.amazon.alexa.mobilytics.OwnerIdentifier;
import com.amazon.alexa.mobilytics.event.operational.MobilyticsMetricsTimer;
import com.amazon.alexa.protocols.service.api.ComponentRegistry;
import com.amazon.alexa.protocols.service.api.LazyComponent;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import java.util.Locale;
/* loaded from: classes9.dex */
public class GeofenceTriggerIntentService extends IntentService {
    private static final String COMPONENT_NAME = MobilyticsUtil.getComponentName("trigger_geofence");
    private static final int JOB_ID = 537202346;
    private static final String TAG = "GeofenceTriggerService";
    private final LazyComponent<CrashReporter> crashReporter;
    private final LazyComponent<FeatureServiceV2> fsv2;
    private final LazyComponent<LocationService> locationService;
    private final LazyComponent<Mobilytics> mobilytics;
    private volatile boolean successful;

    public GeofenceTriggerIntentService() {
        super(TAG);
        this.successful = false;
        ComponentRegistry componentRegistry = ComponentRegistry.getInstance();
        this.locationService = componentRegistry.getLazy(LocationService.class);
        this.mobilytics = componentRegistry.getLazy(Mobilytics.class);
        this.crashReporter = componentRegistry.getLazy(CrashReporter.class);
        this.fsv2 = componentRegistry.getLazy(FeatureServiceV2.class);
    }

    @Override // android.app.IntentService, android.app.Service
    public void onCreate() {
        super.onCreate();
        startForeground(JOB_ID, NotificationUtil.getNotificationForBackgroundTasks(this, "GeofenceTrigger"));
    }

    @Override // android.app.IntentService, android.app.Service
    public void onDestroy() {
        super.onDestroy();
    }

    @Override // android.app.IntentService
    protected void onHandleIntent(@NonNull Intent intent) {
        WriteToFile.appendLogForDebugBuild(getApplicationContext(), "onHandleIntent + start");
        if (DebugUtil.isOsTriggerDisabled(getApplicationContext())) {
            return;
        }
        long currentTimeMillis = System.currentTimeMillis();
        long longExtra = intent.getLongExtra(LocationManager.GEOFENCE_OS_TRIGGER_DETECTED_TIME, currentTimeMillis);
        String str = COMPONENT_NAME;
        this.mobilytics.mo10268get().recordOccurrence(MobilyticsUtil.MetricsID.INTENT_RECEIVED, true, str, str, OwnerIdentifier.APP_LOCATION_LIBRARIES_ANDROID);
        LazyComponent<Mobilytics> lazyComponent = this.mobilytics;
        String str2 = MobilyticsUtil.MetricsID.GEOFENCE_TRIGGER_PROCESSING_DELAY;
        String str3 = COMPONENT_NAME;
        this.mobilytics.mo10268get().recordTimer(MobilyticsUtil.createTimerWithValue(lazyComponent, str2, str3, str3, currentTimeMillis - longExtra));
        String str4 = COMPONENT_NAME;
        final MobilyticsMetricsTimer createTimer = this.mobilytics.mo10268get().createTimer(MobilyticsUtil.MetricsID.PROCESS_INTENT_TIME, str4, str4, OwnerIdentifier.APP_LOCATION_LIBRARIES_ANDROID);
        this.locationService.mo10268get().triggerGeofence(intent, 5000, 2.0d, 7).blockingSubscribe(new Observer<String>() { // from class: com.amazon.alexa.location.GeofenceTriggerIntentService.1
            @Override // io.reactivex.rxjava3.core.Observer
            public void onComplete() {
                if (!GeofenceTriggerIntentService.this.successful) {
                    MetricsUtil.recordFailure(GeofenceTriggerIntentService.this.mobilytics, MetricsUtil.MetricsId.GEOFENCE_TRIGGER_FAILURE, "als_fetch_geofences", "Trigger geofence final failure.");
                }
                WriteToFile.appendLogForDebugBuild(GeofenceTriggerIntentService.this.getApplicationContext(), "onHandleIntent + finish");
                createTimer.finishTimer();
                ((Mobilytics) GeofenceTriggerIntentService.this.mobilytics.mo10268get()).recordTimer(createTimer);
            }

            @Override // io.reactivex.rxjava3.core.Observer
            public void onError(Throwable th) {
                Log.e(GeofenceTriggerIntentService.TAG, "[ERROR] Fail to send trigger events.");
                WriteToFile.appendLogForDebugBuild(GeofenceTriggerIntentService.this.getApplicationContext(), "onHandleIntent + throw");
                ExceptionRecordUtil.recordExceptions(GeofenceTriggerIntentService.TAG, "triggerGeofence", th, GeofenceTriggerIntentService.this.crashReporter);
                MetricsUtil.recordFailure(GeofenceTriggerIntentService.this.mobilytics, MetricsUtil.MetricsId.GEOFENCE_TRIGGER_FAILURE, "als_fetch_geofences", "Trigger geofence final failure.");
                ((Mobilytics) GeofenceTriggerIntentService.this.mobilytics.mo10268get()).recordOccurrence(MobilyticsUtil.MetricsID.TRIGGER_FAILURE, true, GeofenceTriggerIntentService.COMPONENT_NAME, GeofenceTriggerIntentService.COMPONENT_NAME, OwnerIdentifier.APP_LOCATION_LIBRARIES_ANDROID);
            }

            @Override // io.reactivex.rxjava3.core.Observer
            public void onSubscribe(Disposable disposable) {
            }

            @Override // io.reactivex.rxjava3.core.Observer
            public void onNext(String str5) {
                String.format(Locale.US, "Send trigger event for fenceId [%s] Success.", str5);
                MetricsUtil.recordSuccess(GeofenceTriggerIntentService.this.mobilytics, MetricsUtil.MetricsId.GEOFENCE_TRIGGER_SUCCESS, "als_fetch_geofences");
                WriteToFile.appendLogForDebugBuild(GeofenceTriggerIntentService.this.getApplicationContext(), "onHandleIntent + successful");
                GeofenceTriggerIntentService.this.successful = true;
            }
        });
    }

    @Override // android.app.IntentService, android.app.Service
    public int onStartCommand(@Nullable Intent intent, int i, int i2) {
        String str = COMPONENT_NAME;
        this.mobilytics.mo10268get().recordOccurrence(MobilyticsUtil.MetricsID.INTENT_SERVICE_STARTED, true, str, str, OwnerIdentifier.APP_LOCATION_LIBRARIES_ANDROID);
        return super.onStartCommand(intent, i, i2);
    }
}
