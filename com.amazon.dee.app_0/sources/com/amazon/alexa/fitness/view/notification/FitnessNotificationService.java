package com.amazon.alexa.fitness.view.notification;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.widget.RemoteViews;
import androidx.core.app.NotificationCompat;
import com.amazon.alexa.fitness.api.afx.FitnessMetrics;
import com.amazon.alexa.fitness.api.afx.FitnessSessionOrchestrator;
import com.amazon.alexa.fitness.api.afx.FitnessSessionOrchestratorDelegate;
import com.amazon.alexa.fitness.api.afx.SensorAvailability;
import com.amazon.alexa.fitness.api.fitnessSdk.Command;
import com.amazon.alexa.fitness.api.fitnessSdk.FitnessSessionState;
import com.amazon.alexa.fitness.api.fitnessSdk.Session;
import com.amazon.alexa.fitness.api.fitnessSdk.SessionCommandSource;
import com.amazon.alexa.fitness.api.fitnessSdk.SessionDataModelsKt;
import com.amazon.alexa.fitness.api.fitnessSdk.Units;
import com.amazon.alexa.fitness.ui.R;
import com.amazon.alexa.fitness.utils.FormatUtilKt;
import com.amazon.alexa.location.utils.MetricsUtil;
import com.amazon.deecomms.common.network.AppUrl;
import com.amazon.identity.auth.device.api.MAPAccountManager;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.lang.ref.WeakReference;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TypeCastException;
import kotlin.collections.ArraysKt___ArraysKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: FitnessNotificationService.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0082\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u00012\u00020\u0002B\u0005¢\u0006\u0002\u0010\u0003J\b\u0010\u0018\u001a\u00020\u0019H\u0002J\b\u0010\u001a\u001a\u00020\u001bH\u0002J\b\u0010\u001c\u001a\u00020\rH\u0002J\b\u0010\u001d\u001a\u00020\u001bH\u0002J\u0012\u0010\u001e\u001a\u0004\u0018\u00010\u001f2\u0006\u0010 \u001a\u00020!H\u0016J\b\u0010\"\u001a\u00020\u001bH\u0016J\b\u0010#\u001a\u00020\u001bH\u0016J\u0012\u0010$\u001a\u00020\u001b2\b\u0010%\u001a\u0004\u0018\u00010\u0005H\u0016J\u001c\u0010&\u001a\u00020\u001b2\b\u0010'\u001a\u0004\u0018\u00010\u00072\b\u0010(\u001a\u0004\u0018\u00010)H\u0016J\u0018\u0010*\u001a\u00020\u001b2\u0006\u0010+\u001a\u00020\u00072\u0006\u0010,\u001a\u00020\u0005H\u0016J\"\u0010-\u001a\u00020.2\b\u0010 \u001a\u0004\u0018\u00010!2\u0006\u0010/\u001a\u00020.2\u0006\u00100\u001a\u00020.H\u0016J\u0010\u00101\u001a\u00020\u001b2\u0006\u00102\u001a\u000203H\u0016J \u00104\u001a\u00020\u0019*\u00020\u00192\b\u0010'\u001a\u0004\u0018\u00010\u00072\b\u0010%\u001a\u0004\u0018\u00010\u0005H\u0002J\f\u00105\u001a\u00020\u0005*\u00020\u0005H\u0002J\f\u00105\u001a\u00020\u0007*\u00020\u0007H\u0002J\f\u00106\u001a\u00020\u001b*\u000207H\u0002J\f\u00108\u001a\u000207*\u00020\u0019H\u0002R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\b\u001a\u00020\t8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\u000bR\u000e\u0010\f\u001a\u00020\rX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082.¢\u0006\u0002\n\u0000R\u0016\u0010\u0010\u001a\n \u0012*\u0004\u0018\u00010\u00110\u0011X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\t0\u0014X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0015R\u001c\u0010\u0016\u001a\u0010\u0012\f\u0012\n \u0012*\u0004\u0018\u00010\u00020\u00020\u0017X\u0082\u0004¢\u0006\u0002\n\u0000¨\u00069"}, d2 = {"Lcom/amazon/alexa/fitness/view/notification/FitnessNotificationService;", "Landroid/app/Service;", "Lcom/amazon/alexa/fitness/api/afx/FitnessSessionOrchestratorDelegate;", "()V", "currentMetrics", "Lcom/amazon/alexa/fitness/api/afx/FitnessMetrics;", "currentSession", "Lcom/amazon/alexa/fitness/api/fitnessSdk/Session;", "currentWorkoutState", "Lcom/amazon/alexa/fitness/api/fitnessSdk/FitnessSessionState;", "getCurrentWorkoutState", "()Lcom/amazon/alexa/fitness/api/fitnessSdk/FitnessSessionState;", "launchAppIntent", "Landroid/app/PendingIntent;", "notificationManager", "Landroid/app/NotificationManager;", "orchestrator", "Lcom/amazon/alexa/fitness/api/afx/FitnessSessionOrchestrator;", "kotlin.jvm.PlatformType", "statesToNotifyOnSessionChange", "", "[Lcom/amazon/alexa/fitness/api/fitnessSdk/FitnessSessionState;", "weakReference", "Ljava/lang/ref/WeakReference;", "createFitnessNotificationContent", "Landroid/widget/RemoteViews;", "createNotificationChannel", "", "getWorkoutButtonClickIntent", "handleWorkoutButtonClick", "onBind", "Landroid/os/IBinder;", MAPAccountManager.KEY_INTENT, "Landroid/content/Intent;", "onCreate", "onDestroy", "onMetricsUpdated", "metrics", "onSessionChanged", "session", "error", "Lcom/amazon/alexa/fitness/api/afx/FitnessSessionOrchestrator$CommandProcessingError;", "onSessionEnded", "endedSession", "finalMetrics", "onStartCommand", "", "flags", AppUrl.ACMS.QueryParam.Keys.MESSAGE_START_ID, "sensorAvailabilityChanged", MetricsUtil.LegacyMetricTypes.AVAILABILITY, "Lcom/amazon/alexa/fitness/api/afx/SensorAvailability;", "applyFitnessValues", "captureAsCurrent", "notify", "Landroid/app/Notification;", "toFitnessNotification", "AlexaMobileAndroidFitnessUI_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes8.dex */
public final class FitnessNotificationService extends Service implements FitnessSessionOrchestratorDelegate {
    private FitnessMetrics currentMetrics;
    private Session currentSession;
    private PendingIntent launchAppIntent;
    private NotificationManager notificationManager;
    private final FitnessSessionState[] statesToNotifyOnSessionChange = {FitnessSessionState.ACTIVE, FitnessSessionState.PAUSED};
    private final FitnessSessionOrchestrator orchestrator = (FitnessSessionOrchestrator) GeneratedOutlineSupport1.outline20(FitnessSessionOrchestrator.class);
    private final WeakReference<FitnessSessionOrchestratorDelegate> weakReference = new WeakReference<>(this);

    @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 1, 16})
    /* loaded from: classes8.dex */
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = new int[FitnessSessionState.values().length];

        static {
            $EnumSwitchMapping$0[FitnessSessionState.PAUSED.ordinal()] = 1;
            $EnumSwitchMapping$0[FitnessSessionState.ACTIVE.ordinal()] = 2;
        }
    }

    private final RemoteViews applyFitnessValues(@NotNull RemoteViews remoteViews, Session session, FitnessMetrics fitnessMetrics) {
        if (fitnessMetrics instanceof FitnessMetrics.StepBased) {
            FitnessMetrics.StepBased stepBased = (FitnessMetrics.StepBased) fitnessMetrics;
            String string = getApplicationContext().getString(R.string.workout_calories, FormatUtilKt.getFormattedCalories(stepBased.getCalories()));
            Intrinsics.checkExpressionValueIsNotNull(string, "applicationContext.getSt…lories(metrics.calories))");
            remoteViews.setTextViewText(R.id.workoutCalories, string);
            Pair<Double, Units> distance = stepBased.getDistance();
            Context applicationContext = getApplicationContext();
            Intrinsics.checkExpressionValueIsNotNull(applicationContext, "applicationContext");
            remoteViews.setTextViewText(R.id.workoutDistance, FormatUtilKt.toDistanceStringWithUnits(distance, applicationContext));
            remoteViews.setTextViewText(R.id.workoutPace, FormatUtilKt.getFormattedPaceInDeviceLocale$default(FormatUtilKt.convertMilesPerHourToMinutesPerMile(stepBased.getSpeed()), getApplicationContext(), null, 2, null));
        }
        if (session != null) {
            remoteViews.setTextViewText(R.id.workoutDurationTextView, FormatUtilKt.getFormattedDuration(Integer.valueOf(SessionDataModelsKt.processingDuration$default(session, null, 1, null))));
            int i = WhenMappings.$EnumSwitchMapping$0[SessionDataModelsKt.currentState(session).ordinal()];
            if (i == 1) {
                remoteViews.setTextViewText(R.id.workoutStateTextView, getApplicationContext().getString(R.string.workout_notification_paused_state));
                remoteViews.setImageViewResource(R.id.workoutStateButton, R.drawable.ic_play);
            } else if (i == 2) {
                remoteViews.setTextViewText(R.id.workoutStateTextView, getApplicationContext().getString(R.string.workout_notification_active_state));
                remoteViews.setImageViewResource(R.id.workoutStateButton, R.drawable.ic_pause);
            }
        }
        return remoteViews;
    }

    private final Session captureAsCurrent(@NotNull Session session) {
        this.currentSession = session;
        return session;
    }

    private final RemoteViews createFitnessNotificationContent() {
        Context applicationContext = getApplicationContext();
        Intrinsics.checkExpressionValueIsNotNull(applicationContext, "applicationContext");
        RemoteViews remoteViews = new RemoteViews(applicationContext.getPackageName(), R.layout.workout_notification_layout);
        remoteViews.setOnClickPendingIntent(R.id.workoutStateButton, getWorkoutButtonClickIntent());
        return remoteViews;
    }

    private final void createNotificationChannel() {
        int i = Build.VERSION.SDK_INT;
        NotificationChannel notificationChannel = new NotificationChannel("FITNESS_NOTIFICATION", "FITNESS_NOTIFICATION", 3);
        NotificationManager notificationManager = this.notificationManager;
        if (notificationManager == null) {
            Intrinsics.throwUninitializedPropertyAccessException("notificationManager");
        }
        notificationManager.createNotificationChannel(notificationChannel);
    }

    private final FitnessSessionState getCurrentWorkoutState() {
        FitnessSessionState currentState;
        Session session = this.currentSession;
        return (session == null || (currentState = SessionDataModelsKt.currentState(session)) == null) ? FitnessSessionState.IDLE : currentState;
    }

    private final PendingIntent getWorkoutButtonClickIntent() {
        PendingIntent service = PendingIntent.getService(this, 0, new Intent().setAction("com.amazon.fitness.notification.workout.button.clicked").setClassName(getPackageName(), FitnessNotificationService.class.getName()), 0);
        Intrinsics.checkExpressionValueIsNotNull(service, "PendingIntent.getService…),\n                    0)");
        return service;
    }

    private final void handleWorkoutButtonClick() {
        FitnessSessionState currentWorkoutState = getCurrentWorkoutState();
        if (currentWorkoutState == FitnessSessionState.PAUSED) {
            this.orchestrator.receive(new Command.Resume(SessionCommandSource.GUI), FitnessNotificationService$handleWorkoutButtonClick$1$1.INSTANCE);
        } else if (currentWorkoutState != FitnessSessionState.ACTIVE) {
        } else {
            this.orchestrator.receive(new Command.Pause(SessionCommandSource.GUI), FitnessNotificationService$handleWorkoutButtonClick$1$2.INSTANCE);
        }
    }

    private final void notify(@NotNull Notification notification) {
        NotificationManager notificationManager = this.notificationManager;
        if (notificationManager == null) {
            Intrinsics.throwUninitializedPropertyAccessException("notificationManager");
        }
        notificationManager.notify(1, notification);
    }

    private final Notification toFitnessNotification(@NotNull RemoteViews remoteViews) {
        NotificationCompat.Builder customContentView = new NotificationCompat.Builder(this, "FITNESS_NOTIFICATION").setCustomContentView(remoteViews);
        PendingIntent pendingIntent = this.launchAppIntent;
        if (pendingIntent == null) {
            Intrinsics.throwUninitializedPropertyAccessException("launchAppIntent");
        }
        Notification build = customContentView.setContentIntent(pendingIntent).setSmallIcon(R.drawable.alexa_icon_notification_title).setOnlyAlertOnce(true).build();
        Intrinsics.checkExpressionValueIsNotNull(build, "NotificationCompat.Build…                 .build()");
        return build;
    }

    @Override // android.app.Service
    @Nullable
    public IBinder onBind(@NotNull Intent intent) {
        Intrinsics.checkParameterIsNotNull(intent, "intent");
        return null;
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        Object systemService = getApplicationContext().getSystemService("notification");
        if (systemService != null) {
            this.notificationManager = (NotificationManager) systemService;
            PendingIntent activity = PendingIntent.getActivity(this, 0, new Intent("android.intent.action.VIEW").setClassName(getPackageName(), "com.amazon.dee.app.ui.main.MainActivity"), 0);
            Intrinsics.checkExpressionValueIsNotNull(activity, "PendingIntent.getActivit…                       0)");
            this.launchAppIntent = activity;
            this.orchestrator.registerDelegate(this.weakReference);
            return;
        }
        throw new TypeCastException("null cannot be cast to non-null type android.app.NotificationManager");
    }

    @Override // android.app.Service
    public void onDestroy() {
        super.onDestroy();
        this.orchestrator.removeDelegate(this.weakReference);
    }

    @Override // com.amazon.alexa.fitness.api.afx.FitnessSessionOrchestratorDelegate
    public void onMetricsUpdated(@Nullable FitnessMetrics fitnessMetrics) {
        if (fitnessMetrics != null) {
            captureAsCurrent(fitnessMetrics);
            notify(toFitnessNotification(applyFitnessValues(createFitnessNotificationContent(), this.currentSession, this.currentMetrics)));
        }
    }

    @Override // com.amazon.alexa.fitness.api.afx.FitnessSessionOrchestratorDelegate
    public void onSessionChanged(@Nullable Session session, @Nullable FitnessSessionOrchestrator.CommandProcessingError commandProcessingError) {
        boolean contains;
        FitnessSessionState currentState;
        GeneratedOutlineSupport1.outline179(GeneratedOutlineSupport1.outline107("session changed: "), (session == null || (currentState = SessionDataModelsKt.currentState(session)) == null) ? null : currentState.name(), "AFX-Notification");
        if (session != null) {
            captureAsCurrent(session);
            FitnessSessionState currentWorkoutState = getCurrentWorkoutState();
            if (getCurrentWorkoutState() == SessionDataModelsKt.previousState(session)) {
                return;
            }
            contains = ArraysKt___ArraysKt.contains(this.statesToNotifyOnSessionChange, currentWorkoutState);
            if (!contains) {
                return;
            }
            notify(toFitnessNotification(applyFitnessValues(createFitnessNotificationContent(), this.currentSession, this.currentMetrics)));
        }
    }

    @Override // com.amazon.alexa.fitness.api.afx.FitnessSessionOrchestratorDelegate
    public void onSessionEnded(@NotNull Session endedSession, @NotNull FitnessMetrics finalMetrics) {
        Intrinsics.checkParameterIsNotNull(endedSession, "endedSession");
        Intrinsics.checkParameterIsNotNull(finalMetrics, "finalMetrics");
        captureAsCurrent(endedSession);
        onMetricsUpdated(finalMetrics);
    }

    @Override // android.app.Service
    public int onStartCommand(@Nullable Intent intent, int i, int i2) {
        if (Intrinsics.areEqual(intent != null ? intent.getAction() : null, "com.amazon.fitness.notification.workout.button.clicked")) {
            handleWorkoutButtonClick();
            return 2;
        }
        createNotificationChannel();
        startForeground(1, toFitnessNotification(createFitnessNotificationContent()));
        return 2;
    }

    @Override // com.amazon.alexa.fitness.api.afx.FitnessSessionOrchestratorDelegate
    public void sensorAvailabilityChanged(@NotNull SensorAvailability availability) {
        Intrinsics.checkParameterIsNotNull(availability, "availability");
    }

    private final FitnessMetrics captureAsCurrent(@NotNull FitnessMetrics fitnessMetrics) {
        this.currentMetrics = fitnessMetrics;
        return fitnessMetrics;
    }
}
