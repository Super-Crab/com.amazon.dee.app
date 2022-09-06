package com.amazon.dee.app.services.appreviewrequest;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.appcompat.app.AlertDialog;
import com.amazon.alexa.eventbus.api.EventBus;
import com.amazon.alexa.eventbus.api.Message;
import com.amazon.alexa.eventbus.api.MessageHandler;
import com.amazon.alexa.eventbus.message.EventTypeMessageFilter;
import com.amazon.alexa.featureservice.api.FeatureServiceV2;
import com.amazon.alexa.identity.api.IdentityService;
import com.amazon.alexa.mobilytics.Mobilytics;
import com.amazon.alexa.mobilytics.event.operational.OperationalEventType;
import com.amazon.alexa.mode.ModeName;
import com.amazon.alexa.mode.ModeService;
import com.amazon.alexa.protocols.environment.EnvironmentService;
import com.amazon.alexa.routing.api.RoutingService;
import com.amazon.alexa.routing.data.RouteName;
import com.amazon.dee.app.R;
import com.amazon.dee.app.services.logging.Log;
import com.amazon.dee.app.services.metrics.AlexaMetricsConstants;
import com.amazon.dee.app.services.wifi.WifiService;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.android.play.core.review.ReviewException;
import com.google.android.play.core.review.ReviewInfo;
import com.google.android.play.core.review.ReviewManager;
import com.google.android.play.core.tasks.OnCompleteListener;
import com.google.android.play.core.tasks.Task;
import dagger.Lazy;
import java.util.Date;
import java.util.UUID;
/* loaded from: classes12.dex */
public class AppReviewRequestService {
    @VisibleForTesting
    static final String DATE_APP_INSTALLED = "DateAppInstalled";
    @VisibleForTesting
    static final String DATE_APP_UPDATED = "DateAppUpdated";
    @VisibleForTesting
    static final String DATE_LAST_REQUESTED_REVIEW = "DateLastRequestedReview";
    private static final String TAG = Log.tag(AppReviewRequestService.class);
    @VisibleForTesting
    static final String TIMES_APP_OPENED_SINCE_LAST_REVIEW_REQUEST = "TimesOpenedSinceLastReviewRequest";
    @VisibleForTesting
    static final String TOTAL_TIME_IN_APP = "TotalTimeInApp";
    @VisibleForTesting
    static final String USER_PREFS_SHOULD_REQUEST_REVIEW = "UserPrefsShouldRequestReview";
    @VisibleForTesting
    static final String VERSION_CODE_LAST_REVIEWED = "VersionCodeLastReviewed";
    private UUID askForReviewUUID;
    private final Context context;
    @VisibleForTesting
    Runnable createAlertDialogRunnable;
    private final EnvironmentService environmentService;
    private final EventBus eventBus;
    private final Lazy<FeatureServiceV2> featureServiceV2Lazy;
    private final IdentityService identityService;
    private final Lazy<Mobilytics> mobilyticsLazy;
    private final Lazy<ModeService> modeService;
    private UUID recordAppOpenedUUID;
    private final ReviewManager reviewManager;
    private final RoutingService routingService;
    @VisibleForTesting
    Date startTime;
    private UUID startUserEngagementTimerUUID;
    @VisibleForTesting
    Date stopTime;
    private UUID stopUserEngagementTimerUUID;
    private UUID userChangedUUID;
    private final WifiService wifiService;
    @VisibleForTesting
    Boolean neverAsked = null;
    private boolean appReviewDisabled = false;
    @VisibleForTesting
    String inAppRatingExperimentTreatment = "C";
    @VisibleForTesting
    Handler mainHandler = new Handler(Looper.getMainLooper());

    /* JADX INFO: Access modifiers changed from: package-private */
    @VisibleForTesting
    /* loaded from: classes12.dex */
    public enum StoreType {
        GOOGLE_PLAY_STORE,
        AMAZON_APP_STORE
    }

    public AppReviewRequestService(EventBus eventBus, RoutingService routingService, IdentityService identityService, WifiService wifiService, EnvironmentService environmentService, Lazy<ModeService> lazy, Context context, ReviewManager reviewManager, Lazy<FeatureServiceV2> lazy2, Lazy<Mobilytics> lazy3) {
        this.eventBus = eventBus;
        this.routingService = routingService;
        this.identityService = identityService;
        this.wifiService = wifiService;
        this.environmentService = environmentService;
        this.context = context;
        this.reviewManager = reviewManager;
        this.featureServiceV2Lazy = lazy2;
        this.mobilyticsLazy = lazy3;
        this.modeService = lazy;
        this.startUserEngagementTimerUUID = this.eventBus.getSubscriber().subscribe(new EventTypeMessageFilter(AppReviewRequestServiceConstants.APP_REVIEW_PREFS_USER_ENGAGEMENT_TIMER_START), new MessageHandler() { // from class: com.amazon.dee.app.services.appreviewrequest.-$$Lambda$AppReviewRequestService$jsoGJK8ha6iTsnxwx41UYnrXXU4
            @Override // com.amazon.alexa.eventbus.api.MessageHandler
            public final void handle(Message message) {
                AppReviewRequestService.this.lambda$new$0$AppReviewRequestService(message);
            }
        });
        this.stopUserEngagementTimerUUID = this.eventBus.getSubscriber().subscribe(new EventTypeMessageFilter(AppReviewRequestServiceConstants.APP_REVIEW_PREFS_USER_ENGAGEMENT_TIMER_STOP), new MessageHandler() { // from class: com.amazon.dee.app.services.appreviewrequest.-$$Lambda$AppReviewRequestService$nKr6gz3MiauUvtJFQvYkVyJWDdM
            @Override // com.amazon.alexa.eventbus.api.MessageHandler
            public final void handle(Message message) {
                AppReviewRequestService.this.lambda$new$1$AppReviewRequestService(message);
            }
        });
        this.recordAppOpenedUUID = this.eventBus.getSubscriber().subscribe(new EventTypeMessageFilter(AppReviewRequestServiceConstants.APP_REVIEW_PREFS_RECORD_APP_OPENED), new MessageHandler() { // from class: com.amazon.dee.app.services.appreviewrequest.-$$Lambda$AppReviewRequestService$JC-2xIWEUSj5EIel_AWoW6no0Jg
            @Override // com.amazon.alexa.eventbus.api.MessageHandler
            public final void handle(Message message) {
                AppReviewRequestService.this.lambda$new$2$AppReviewRequestService(message);
            }
        });
        this.userChangedUUID = this.eventBus.getSubscriber().subscribe(new EventTypeMessageFilter(AppReviewRequestServiceConstants.APP_REVIEW_PREFS_USER_LOGGED_OUT), new MessageHandler() { // from class: com.amazon.dee.app.services.appreviewrequest.-$$Lambda$AppReviewRequestService$my_s_gvU5g3DGtePTzx3GcRAv8k
            @Override // com.amazon.alexa.eventbus.api.MessageHandler
            public final void handle(Message message) {
                AppReviewRequestService.this.lambda$new$3$AppReviewRequestService(message);
            }
        });
    }

    private Intent getGoToMarketIntent(String str) {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(str);
        outline107.append(this.context.getPackageName());
        Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(outline107.toString()));
        intent.addFlags(1476919296);
        return intent;
    }

    private boolean isInDriveMode() {
        return ModeName.DRIVE_MODE.equals(this.modeService.mo358get().getMode());
    }

    @VisibleForTesting
    void createAlertDialog(Context context) {
        String string;
        Resources resources = context.getResources();
        if (getAppStoreType() == StoreType.GOOGLE_PLAY_STORE) {
            string = resources.getString(R.string.app_review_request_google_play_store);
        } else {
            string = resources.getString(R.string.app_review_request_amazon_app_store);
        }
        String string2 = resources.getString(R.string.app_review_request_positive);
        String string3 = resources.getString(R.string.app_review_request_negative);
        String string4 = resources.getString(R.string.app_review_request_neutral);
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage(string).setTitle(R.string.app_review_request_title).setPositiveButton(string3, new DialogInterface.OnClickListener() { // from class: com.amazon.dee.app.services.appreviewrequest.-$$Lambda$AppReviewRequestService$F7cCr_1jgHy9xUDxq6bnO5Hpq0k
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                AppReviewRequestService.this.lambda$createAlertDialog$4$AppReviewRequestService(dialogInterface, i);
            }
        }).setNegativeButton(string4, new DialogInterface.OnClickListener() { // from class: com.amazon.dee.app.services.appreviewrequest.-$$Lambda$AppReviewRequestService$KkMzj9Hd1H-OxISk8592eeOqAjU
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                AppReviewRequestService.this.lambda$createAlertDialog$5$AppReviewRequestService(dialogInterface, i);
            }
        }).setNeutralButton(string2, new DialogInterface.OnClickListener() { // from class: com.amazon.dee.app.services.appreviewrequest.-$$Lambda$AppReviewRequestService$wD64MpVc1VcfGVOPkDJ0DOhrAEg
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                AppReviewRequestService.this.lambda$createAlertDialog$6$AppReviewRequestService(dialogInterface, i);
            }
        });
        AlertDialog create = builder.create();
        create.setOnShowListener(new DialogInterface.OnShowListener() { // from class: com.amazon.dee.app.services.appreviewrequest.-$$Lambda$AppReviewRequestService$sloPKaGRECZLLeQOnHTxmy8IiQ8
            @Override // android.content.DialogInterface.OnShowListener
            public final void onShow(DialogInterface dialogInterface) {
                AppReviewRequestService.this.lambda$createAlertDialog$7$AppReviewRequestService(dialogInterface);
            }
        });
        create.setCanceledOnTouchOutside(false);
        create.setOnCancelListener(new DialogInterface.OnCancelListener() { // from class: com.amazon.dee.app.services.appreviewrequest.-$$Lambda$AppReviewRequestService$SDhSCe8MRK4CysYXw11MdRiSe8E
            @Override // android.content.DialogInterface.OnCancelListener
            public final void onCancel(DialogInterface dialogInterface) {
                AppReviewRequestService.this.lambda$createAlertDialog$8$AppReviewRequestService(dialogInterface);
            }
        });
        create.show();
        create.getButton(-2).setContentDescription(resources.getString(R.string.app_review_request_neutral));
        create.getButton(-3).setContentDescription(resources.getString(R.string.app_review_request_positive));
        create.getButton(-1).setContentDescription(resources.getString(R.string.app_review_request_negative));
    }

    @VisibleForTesting
    Boolean evaluateShouldRequestReview() {
        boolean z = false;
        if (this.appReviewDisabled) {
            return false;
        }
        SharedPreferences sharedPreferences = this.context.getApplicationContext().getSharedPreferences(USER_PREFS_SHOULD_REQUEST_REVIEW, 0);
        if (!this.wifiService.isConnected()) {
            return false;
        }
        String buildFlavor = this.environmentService.getBuildFlavor();
        boolean z2 = (buildFlavor.equals("prod") || buildFlavor.equals("prodPhoenix")) && !this.environmentService.isDebugBuild();
        long j = 5;
        if (hasAccessToUpdatedInAppRatingExperiment()) {
            this.inAppRatingExperimentTreatment = this.featureServiceV2Lazy.mo358get().getTreatmentAndRecordTrigger("ALEXA_ANDROID_IN_APP_RATING_UPDATE_EXPERIMENTATION", "C");
            if (this.inAppRatingExperimentTreatment.equals("T1")) {
                j = 10;
            }
        }
        if (!z2) {
            j = 3;
        }
        boolean z3 = sharedPreferences.getLong(TOTAL_TIME_IN_APP, 0L) >= j * 60000;
        boolean z4 = sharedPreferences.getInt(TIMES_APP_OPENED_SINCE_LAST_REVIEW_REQUEST, 1) >= 3;
        stopUserEngagementTimer();
        startUserEngagementTimer();
        long time = new Date().getTime();
        long j2 = sharedPreferences.getLong(DATE_LAST_REQUESTED_REVIEW, time);
        long j3 = 86400;
        if (!z2) {
            j3 = 30;
        }
        boolean z5 = (time - j2) / 60000 >= j3;
        this.neverAsked = Boolean.valueOf(j2 == time);
        Integer valueOf = Integer.valueOf(getAppVersion());
        if (valueOf.intValue() != -1 && sharedPreferences.getInt(VERSION_CODE_LAST_REVIEWED, valueOf.intValue()) == valueOf.intValue() && !this.neverAsked.booleanValue()) {
            return false;
        }
        isInDriveMode();
        if (z3 && z4 && ((z5 || this.neverAsked.booleanValue()) && !isInDriveMode())) {
            z = true;
        }
        return Boolean.valueOf(z);
    }

    @VisibleForTesting
    StoreType getAppStoreType() {
        String installerPackageName = this.context.getPackageManager().getInstallerPackageName(this.context.getPackageName());
        if (!Build.MANUFACTURER.toUpperCase().equals("AMAZON") && (installerPackageName == null || !installerPackageName.equals("com.amazon.venezia"))) {
            return StoreType.GOOGLE_PLAY_STORE;
        }
        return StoreType.AMAZON_APP_STORE;
    }

    @VisibleForTesting
    int getAppVersion() {
        PackageManager packageManager = this.context.getPackageManager();
        String packageName = this.context.getPackageName();
        try {
            return packageManager.getPackageInfo(packageName, 0).versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            Log.e(TAG, e, "Package %s not found", packageName);
            return -1;
        }
    }

    @VisibleForTesting
    void goToAmazonAppStore() {
        handleAppStoreIntent("amzn://apps/android?p=", "https://www.amazon.com/gp/mas/dl/android?p=");
    }

    @VisibleForTesting
    void goToGooglePlayStore() {
        handleAppStoreIntent("market://details?id=", "https://play.google.com/store/apps/details?id=");
    }

    @VisibleForTesting
    void goToStore() {
        if (getAppStoreType() == StoreType.GOOGLE_PLAY_STORE) {
            goToGooglePlayStore();
        } else {
            goToAmazonAppStore();
        }
    }

    @VisibleForTesting
    void handleAppStoreIntent(String str, String str2) {
        try {
            this.context.startActivity(getGoToMarketIntent(str));
        } catch (ActivityNotFoundException unused) {
            this.context.startActivity(getGoToMarketIntent(str2));
        }
    }

    @VisibleForTesting
    boolean hasAccessToUpdatedInAppRatingExperiment() {
        return shouldShowInAppRating() && this.featureServiceV2Lazy.mo358get().hasAccess("ALEXA_ANDROID_IN_APP_RATING_UPDATE_EXPERIMENTATION", false);
    }

    @VisibleForTesting
    void initiateInAppRatingFlow(final Activity activity) {
        recordMetrics(AlexaMetricsConstants.MetricEvents.IN_APP_RATING_STARTED, AlexaMetricsConstants.MetricEvents.IN_APP_RATING_STARTED, AlexaMetricsConstants.MetricEvents.IN_APP_RATING_STARTED, null);
        Log.i(TAG, "Initiating In-App Review Rating");
        try {
            this.reviewManager.requestReviewFlow().addOnCompleteListener(new OnCompleteListener() { // from class: com.amazon.dee.app.services.appreviewrequest.-$$Lambda$AppReviewRequestService$RYa0TJbAAPLKs1r-lHcJid9Swj4
                @Override // com.google.android.play.core.tasks.OnCompleteListener
                public final void onComplete(Task task) {
                    AppReviewRequestService.this.lambda$initiateInAppRatingFlow$9$AppReviewRequestService(activity, task);
                }
            });
        } catch (Exception e) {
            Log.w(TAG, "Exception caught while requesting Google API for In-App rating flow.", e);
            recordMetrics(AlexaMetricsConstants.MetricEvents.IN_APP_RATING_ERROR, AlexaMetricsConstants.MetricsComponents.IN_APP_RATING_REVIEW_REQUEST_FLOW_ERROR, AlexaMetricsConstants.MetricEvents.IN_APP_RATING_ERROR, e);
        }
    }

    public /* synthetic */ void lambda$createAlertDialog$4$AppReviewRequestService(DialogInterface dialogInterface, int i) {
        sendMetricsForStoreType(AlexaMetricsConstants.MetricEvents.APP_REVIEW_REQUEST_NOT_NOW_BUTTON_ANDROID, AlexaMetricsConstants.MetricEvents.APP_REVIEW_REQUEST_NOT_NOW_BUTTON_FIREOS);
    }

    public /* synthetic */ void lambda$createAlertDialog$5$AppReviewRequestService(DialogInterface dialogInterface, int i) {
        sendMetricsForStoreType(AlexaMetricsConstants.MetricEvents.APP_REVIEW_REQUEST_REPORT_PROBLEM_ANDROID, AlexaMetricsConstants.MetricEvents.APP_REVIEW_REQUEST_REPORT_PROBLEM_FIREOS);
        GeneratedOutlineSupport1.outline145(this.routingService, RouteName.HELP);
    }

    public /* synthetic */ void lambda$createAlertDialog$6$AppReviewRequestService(DialogInterface dialogInterface, int i) {
        sendMetricsForStoreType(AlexaMetricsConstants.MetricEvents.APP_REVIEW_REQUEST_YES_ANDROID, AlexaMetricsConstants.MetricEvents.APP_REVIEW_REQUEST_YES_FIREOS);
        goToStore();
    }

    public /* synthetic */ void lambda$createAlertDialog$7$AppReviewRequestService(DialogInterface dialogInterface) {
        sendMetricsForStoreType(AlexaMetricsConstants.MetricEvents.APP_REVIEW_REQUEST_ANDROID, AlexaMetricsConstants.MetricEvents.APP_REVIEW_REQUEST_FIREOS);
        resetTimesOpenedLastRequestTotalTime();
    }

    public /* synthetic */ void lambda$createAlertDialog$8$AppReviewRequestService(DialogInterface dialogInterface) {
        sendMetricsForStoreType(AlexaMetricsConstants.MetricEvents.APP_REVIEW_REQUEST_CANCEL_BACK_BUTTON_ANDROID, AlexaMetricsConstants.MetricEvents.APP_REVIEW_REQUEST_CANCEL_BACK_BUTTON_FIREOS);
    }

    public /* synthetic */ void lambda$initiateInAppRatingFlow$9$AppReviewRequestService(Activity activity, Task task) {
        onReviewRequestCompleted(activity, this.reviewManager, task);
    }

    public /* synthetic */ void lambda$new$0$AppReviewRequestService(Message message) {
        startUserEngagementTimer();
    }

    public /* synthetic */ void lambda$new$1$AppReviewRequestService(Message message) {
        stopUserEngagementTimer();
    }

    public /* synthetic */ void lambda$new$2$AppReviewRequestService(Message message) {
        recordAppOpened(false);
    }

    public /* synthetic */ void lambda$new$3$AppReviewRequestService(Message message) {
        resetAllAppReviewPreferences();
    }

    public /* synthetic */ void lambda$onReviewRequestCompleted$10$AppReviewRequestService(Task task) {
        onReviewFlowCompleted();
    }

    @VisibleForTesting
    void onReviewFlowCompleted() {
        resetTimesOpenedLastRequestTotalTime();
        Log.i(TAG, "In-App Rating request successfully completed.");
        recordMetrics(AlexaMetricsConstants.MetricEvents.IN_APP_RATING_FINISHED, AlexaMetricsConstants.MetricEvents.IN_APP_RATING_FINISHED, AlexaMetricsConstants.MetricEvents.IN_APP_RATING_FINISHED, null);
        if (hasAccessToUpdatedInAppRatingExperiment()) {
            if (this.inAppRatingExperimentTreatment.equals("C")) {
                recordMetrics(AlexaMetricsConstants.MetricEvents.IN_APP_RATING_EXPERIMENT, AlexaMetricsConstants.MetricsComponents.IN_APP_RATING_EXPERIMENT_TREATMENT_C, AlexaMetricsConstants.MetricEvents.IN_APP_RATING_FINISHED, null);
            } else if (!this.inAppRatingExperimentTreatment.equals("T1")) {
            } else {
                recordMetrics(AlexaMetricsConstants.MetricEvents.IN_APP_RATING_EXPERIMENT, AlexaMetricsConstants.MetricsComponents.IN_APP_RATING_EXPERIMENT_TREATMENT_T1, AlexaMetricsConstants.MetricEvents.IN_APP_RATING_FINISHED, null);
            }
        }
    }

    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:13:0x0048 -> B:18:0x0068). Please submit an issue!!! */
    @VisibleForTesting
    void onReviewRequestCompleted(Activity activity, ReviewManager reviewManager, Task<ReviewInfo> task) {
        try {
            if (task.isSuccessful()) {
                Log.i(TAG, "In-App Rating request successfully started.");
                reviewManager.launchReviewFlow(activity, task.getResult()).addOnCompleteListener(new OnCompleteListener() { // from class: com.amazon.dee.app.services.appreviewrequest.-$$Lambda$AppReviewRequestService$vbja9wlS5qLJuza15cQuqtKmW9A
                    @Override // com.google.android.play.core.tasks.OnCompleteListener
                    public final void onComplete(Task task2) {
                        AppReviewRequestService.this.lambda$onReviewRequestCompleted$10$AppReviewRequestService(task2);
                    }
                });
            } else {
                try {
                    Throwable exception = task.getException();
                    Log.w(TAG, "In-App Rating request could not be completed.");
                    if (exception instanceof ReviewException) {
                        ReviewException reviewException = (ReviewException) exception;
                        recordMetrics(AlexaMetricsConstants.MetricEvents.IN_APP_RATING_ERROR, String.valueOf(reviewException.getErrorCode()), AlexaMetricsConstants.MetricEvents.IN_APP_RATING_ERROR, reviewException);
                    } else {
                        recordMetrics(AlexaMetricsConstants.MetricEvents.IN_APP_RATING_ERROR, AlexaMetricsConstants.MetricsComponents.IN_APP_RATING_REVIEW_LAUNCH_FLOW_ERROR, AlexaMetricsConstants.MetricEvents.IN_APP_RATING_ERROR, exception);
                    }
                } catch (Exception e) {
                    Log.w(TAG, "Error encountered while handling unsuccessful app review flow.", e);
                    recordMetrics(AlexaMetricsConstants.MetricEvents.IN_APP_RATING_ERROR, AlexaMetricsConstants.MetricsComponents.IN_APP_RATING_REVIEW_UNSUCCESSFUL_FLOW_ERROR, AlexaMetricsConstants.MetricEvents.IN_APP_RATING_ERROR, e);
                }
            }
        } catch (Exception e2) {
            Log.w(TAG, "Exception caught while launching Google In-App rating request review flow.", e2);
            recordMetrics(AlexaMetricsConstants.MetricEvents.IN_APP_RATING_ERROR, AlexaMetricsConstants.MetricsComponents.IN_APP_RATING_REVIEW_LAUNCH_FLOW_ERROR, AlexaMetricsConstants.MetricEvents.IN_APP_RATING_ERROR, e2);
        }
    }

    @VisibleForTesting
    void recordAppOpened(Boolean bool) {
        SharedPreferences sharedPreferences = this.context.getApplicationContext().getSharedPreferences(USER_PREFS_SHOULD_REQUEST_REVIEW, 0);
        SharedPreferences.Editor edit = sharedPreferences.edit();
        int i = 1;
        if (!bool.booleanValue()) {
            i = 1 + sharedPreferences.getInt(TIMES_APP_OPENED_SINCE_LAST_REVIEW_REQUEST, 1);
        }
        edit.putInt(TIMES_APP_OPENED_SINCE_LAST_REVIEW_REQUEST, Integer.valueOf(i).intValue());
        edit.apply();
    }

    public void recordMetrics(@NonNull String str, @NonNull String str2, @NonNull String str3, @Nullable Throwable th) {
        if (this.mobilyticsLazy.mo358get() != null) {
            if (th == null) {
                this.mobilyticsLazy.mo358get().recordOperationalEvent(this.mobilyticsLazy.mo358get().createOperationalEvent(str, OperationalEventType.DIAGNOSTIC, str2, str3, "cbea4080-337a-4b7e-8e0b-ea16ec85c09a"));
                return;
            }
            this.mobilyticsLazy.mo358get().recordCriticalEvent(str, str, str2, str3, th, "cbea4080-337a-4b7e-8e0b-ea16ec85c09a");
        }
    }

    @VisibleForTesting
    void resetAllAppReviewPreferences() {
        resetTimesOpenedLastRequestTotalTime();
        SharedPreferences.Editor edit = this.context.getApplicationContext().getSharedPreferences(USER_PREFS_SHOULD_REQUEST_REVIEW, 0).edit();
        long time = new Date().getTime();
        edit.putLong(DATE_APP_UPDATED, time);
        edit.putLong(DATE_APP_INSTALLED, time);
        edit.apply();
    }

    @VisibleForTesting
    void resetTimesOpenedLastRequestTotalTime() {
        SharedPreferences.Editor edit = this.context.getApplicationContext().getSharedPreferences(USER_PREFS_SHOULD_REQUEST_REVIEW, 0).edit();
        edit.putLong(TOTAL_TIME_IN_APP, 0L);
        edit.putInt(TIMES_APP_OPENED_SINCE_LAST_REVIEW_REQUEST, 0);
        edit.putLong(DATE_LAST_REQUESTED_REVIEW, new Date().getTime());
        Integer valueOf = Integer.valueOf(getAppVersion());
        if (valueOf != null) {
            edit.putInt(VERSION_CODE_LAST_REVIEWED, valueOf.intValue());
        }
        edit.apply();
    }

    @VisibleForTesting
    void sendMetricsForStoreType(String str, String str2) {
        String str3 = getAppStoreType() == StoreType.GOOGLE_PLAY_STORE ? str : str2;
        if (this.mobilyticsLazy.mo358get() != null) {
            this.mobilyticsLazy.mo358get().recordOccurrence(str3, true, TAG, AppReviewRequestService.class.getSimpleName(), "cbea4080-337a-4b7e-8e0b-ea16ec85c09a");
        }
    }

    public void setAppReviewDisabled(boolean z) {
        this.appReviewDisabled = z;
    }

    @VisibleForTesting
    boolean shouldShowInAppRating() {
        return !this.environmentService.getDeviceInformation().isFireOS();
    }

    public void showAlertDialogIfAppropriate(Context context, MainActivityVisibility mainActivityVisibility) {
        if (!evaluateShouldRequestReview().booleanValue() || mainActivityVisibility.isMainActivityBackgrounded()) {
            return;
        }
        if (shouldShowInAppRating()) {
            initiateInAppRatingFlow((Activity) context);
        } else {
            createAlertDialog(context);
        }
    }

    @VisibleForTesting
    void startUserEngagementTimer() {
        this.startTime = new Date();
    }

    @VisibleForTesting
    void stopUserEngagementTimer() {
        this.stopTime = new Date();
        if (this.startTime != null) {
            Long valueOf = Long.valueOf(this.stopTime.getTime() - this.startTime.getTime());
            SharedPreferences sharedPreferences = this.context.getApplicationContext().getSharedPreferences(USER_PREFS_SHOULD_REQUEST_REVIEW, 0);
            SharedPreferences.Editor edit = sharedPreferences.edit();
            edit.putLong(TOTAL_TIME_IN_APP, valueOf.longValue() + Long.valueOf(sharedPreferences.getLong(TOTAL_TIME_IN_APP, 0L)).longValue());
            edit.apply();
        }
    }
}
