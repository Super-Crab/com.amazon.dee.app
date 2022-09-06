package com.amazon.alexa.location.phase3.sensor;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;
import com.amazon.alexa.location.LocationService;
import com.amazon.alexa.location.phase3.Phase3LocationService;
import com.amazon.alexa.location.utils.MobilyticsUtil;
import com.amazon.alexa.location.utils.NotificationUtil;
import com.amazon.alexa.mobilytics.Mobilytics;
import com.amazon.alexa.mobilytics.OwnerIdentifier;
import com.amazon.alexa.mobilytics.event.operational.MobilyticsMetricsTimer;
import com.amazon.alexa.protocols.service.api.ComponentRegistry;
import com.amazon.alexa.protocols.service.api.LazyComponent;
import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.functions.Consumer;
import java.util.Locale;
/* loaded from: classes9.dex */
public class SensorIntentService extends IntentService {
    private static final int JOB_ID = 537202346;
    private LazyComponent<LocationService> locationService;
    private LazyComponent<Mobilytics> mobilytics;
    private static final String TAG = "SensorIntentService";
    public static final String COMOPONENT_NAME = MobilyticsUtil.getComponentName(TAG);

    public SensorIntentService() {
        super(TAG);
        ComponentRegistry componentRegistry = ComponentRegistry.getInstance();
        this.locationService = componentRegistry.getLazy(LocationService.class);
        this.mobilytics = componentRegistry.getLazy(Mobilytics.class);
    }

    private static String getSimpleAction(Intent intent) {
        String action = intent.getAction();
        return action.substring(action.lastIndexOf(46) + 1);
    }

    public /* synthetic */ void lambda$onHandleIntent$0$SensorIntentService(MobilyticsMetricsTimer mobilyticsMetricsTimer) throws Throwable {
        mobilyticsMetricsTimer.finishTimer();
        this.mobilytics.mo10268get().recordTimer(mobilyticsMetricsTimer);
    }

    public /* synthetic */ void lambda$onHandleIntent$1$SensorIntentService(String str) throws Throwable {
        Mobilytics mo10268get = this.mobilytics.mo10268get();
        String format = String.format(MobilyticsUtil.MetricsID.INTENT_SUCCESS, str);
        String str2 = COMOPONENT_NAME;
        mo10268get.recordOccurrence(format, true, str2, str2, OwnerIdentifier.APP_LOCATION_LIBRARIES_ANDROID);
    }

    public /* synthetic */ void lambda$onHandleIntent$2$SensorIntentService(String str, Throwable th) throws Throwable {
        Log.e(TAG, "onHandleIntent ", th);
        Mobilytics mo10268get = this.mobilytics.mo10268get();
        String format = String.format(MobilyticsUtil.MetricsID.INTENT_FAILURE, str);
        String str2 = COMOPONENT_NAME;
        mo10268get.recordOccurrence(format, true, str2, str2, OwnerIdentifier.APP_LOCATION_LIBRARIES_ANDROID);
    }

    @Override // android.app.IntentService, android.app.Service
    public void onCreate() {
        super.onCreate();
        startForeground(JOB_ID, NotificationUtil.getNotificationForBackgroundTasks(this, "Sensors"));
    }

    @Override // android.app.IntentService
    protected void onHandleIntent(Intent intent) {
        if (this.locationService.mo10268get() instanceof Phase3LocationService) {
            final String lowerCase = getSimpleAction(intent).toLowerCase(Locale.US);
            String format = String.format(MobilyticsUtil.MetricsID.INTENT_START, lowerCase);
            String str = COMOPONENT_NAME;
            this.mobilytics.mo10268get().recordOccurrence(format, true, str, str, OwnerIdentifier.APP_LOCATION_LIBRARIES_ANDROID);
            String format2 = String.format(MobilyticsUtil.MetricsID.INTENT_HANDLED_TIME, lowerCase);
            String str2 = COMOPONENT_NAME;
            final MobilyticsMetricsTimer createTimer = this.mobilytics.mo10268get().createTimer(format2, str2, str2, OwnerIdentifier.APP_LOCATION_LIBRARIES_ANDROID);
            ((Phase3LocationService) this.locationService.mo10268get()).handleIntent(intent).doOnTerminate(new Action() { // from class: com.amazon.alexa.location.phase3.sensor.-$$Lambda$SensorIntentService$f1Tqg1rFs0PsXZBqSSxDzuyPwUA
                @Override // io.reactivex.rxjava3.functions.Action
                public final void run() {
                    SensorIntentService.this.lambda$onHandleIntent$0$SensorIntentService(createTimer);
                }
            }).subscribe(new Action() { // from class: com.amazon.alexa.location.phase3.sensor.-$$Lambda$SensorIntentService$oIb1n6ohWvZceo4AOVAmVqRiK9A
                @Override // io.reactivex.rxjava3.functions.Action
                public final void run() {
                    SensorIntentService.this.lambda$onHandleIntent$1$SensorIntentService(lowerCase);
                }
            }, new Consumer() { // from class: com.amazon.alexa.location.phase3.sensor.-$$Lambda$SensorIntentService$F7RiEJcJwIs08LQBUbEpqz68fDw
                @Override // io.reactivex.rxjava3.functions.Consumer
                public final void accept(Object obj) {
                    SensorIntentService.this.lambda$onHandleIntent$2$SensorIntentService(lowerCase, (Throwable) obj);
                }
            });
            return;
        }
        String str3 = COMOPONENT_NAME;
        this.mobilytics.mo10268get().recordOccurrence(MobilyticsUtil.MetricsID.PHASE3_SERVICE_NOT_AVAILABLE, true, str3, str3, OwnerIdentifier.APP_LOCATION_LIBRARIES_ANDROID);
    }
}
