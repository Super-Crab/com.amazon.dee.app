package com.amazon.tarazed.sessionmanager;

import android.app.Activity;
import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.AudioAttributes;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import androidx.annotation.RequiresApi;
import androidx.annotation.VisibleForTesting;
import androidx.core.app.NotificationCompat;
import com.amazon.deecomms.common.Constants;
import com.amazon.tarazed.R;
import com.amazon.tarazed.activity.ActivityTracker;
import com.amazon.tarazed.core.notifier.ListenerPriority;
import com.amazon.tarazed.core.notifier.TarazedNotificationEvent;
import com.amazon.tarazed.core.notifier.TarazedSessionNotifier;
import com.amazon.tarazed.utility.DeviceInfoUtilityAndroid;
import com.android.tools.r8.GeneratedOutlineSupport1;
import javax.inject.Inject;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: HeadsUpNotificationManager.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000^\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\b\u0000\u0018\u0000 &2\u00020\u0001:\u0001&B'\b\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nB-\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\rJ\b\u0010\u001e\u001a\u00020\u001fH\u0002J\u0010\u0010 \u001a\u00020\u00142\u0006\u0010!\u001a\u00020\"H\u0002J\b\u0010#\u001a\u00020\u0014H\u0002J\b\u0010$\u001a\u00020\u0014H\u0003J\u0006\u0010%\u001a\u00020\u0014R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004¢\u0006\u0002\n\u0000R=\u0010\u0010\u001a\u001e\b\u0001\u0012\u0004\u0012\u00020\u0012\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00140\u0013\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u00118\u0000X\u0081\u0004ø\u0001\u0000¢\u0006\u0010\n\u0002\u0010\u0019\u0012\u0004\b\u0015\u0010\u0016\u001a\u0004\b\u0017\u0010\u0018R\u0014\u0010\u001a\u001a\u00020\u001b8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u001c\u0010\u001dR\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006'"}, d2 = {"Lcom/amazon/tarazed/sessionmanager/HeadsUpNotificationManager;", "", "context", "Landroid/content/Context;", "activityTracker", "Lcom/amazon/tarazed/activity/ActivityTracker;", "deviceInfo", "Lcom/amazon/tarazed/utility/DeviceInfoUtilityAndroid;", "notifier", "Lcom/amazon/tarazed/core/notifier/TarazedSessionNotifier;", "(Landroid/content/Context;Lcom/amazon/tarazed/activity/ActivityTracker;Lcom/amazon/tarazed/utility/DeviceInfoUtilityAndroid;Lcom/amazon/tarazed/core/notifier/TarazedSessionNotifier;)V", "notificationBuilder", "Landroidx/core/app/NotificationCompat$Builder;", "(Landroid/content/Context;Landroidx/core/app/NotificationCompat$Builder;Lcom/amazon/tarazed/activity/ActivityTracker;Lcom/amazon/tarazed/utility/DeviceInfoUtilityAndroid;Lcom/amazon/tarazed/core/notifier/TarazedSessionNotifier;)V", "clearNotificationOnSessionEnd", "", "notificationHandler", "Lkotlin/Function2;", "Lcom/amazon/tarazed/core/notifier/TarazedNotificationEvent;", "Lkotlin/coroutines/Continuation;", "", "notificationHandler$annotations", "()V", "getNotificationHandler$TarazedAndroidLibrary_release", "()Lkotlin/jvm/functions/Function2;", "Lkotlin/jvm/functions/Function2;", "notificationManager", "Landroid/app/NotificationManager;", "getNotificationManager", "()Landroid/app/NotificationManager;", "createOpenAppPendingIntent", "Landroid/app/PendingIntent;", "notify", "msgRes", "", "setupBuilder", "setupNotificationChannel", "subscribeToNotifier", "Companion", "TarazedAndroidLibrary_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class HeadsUpNotificationManager {
    private static final String NOTIFICATION_CHANNEL_ID = "847285";
    private static final int NOTIFICATION_ID = 847285;
    public static final long PERMISSION_NOTIFICATION_EXPIRY_MS = 120000;
    private final ActivityTracker activityTracker;
    private boolean clearNotificationOnSessionEnd;
    private final Context context;
    private final DeviceInfoUtilityAndroid deviceInfo;
    private final NotificationCompat.Builder notificationBuilder;
    @NotNull
    private final Function2<TarazedNotificationEvent, Continuation<? super Unit>, Object> notificationHandler;
    private final TarazedSessionNotifier notifier;
    public static final Companion Companion = new Companion(null);
    private static final int AUTO_PAUSE_MESSAGE = R.string.screen_sharing_paused;
    private static final int AUTO_END_MESSAGE = R.string.notification_auto_ended;
    private static final int PERMISSION_REQUEST_MESSAGE = R.string.permission_request_3p_notification;
    private static final int RESUME_MESSAGE = R.string.dialog_request_resume;

    /* compiled from: HeadsUpNotificationManager.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0006\b\u0080\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0014\u0010\u0003\u001a\u00020\u0004X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0014\u0010\u0007\u001a\u00020\u0004X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0006R\u000e\u0010\t\u001a\u00020\nX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u0016\u0010\f\u001a\u00020\r8\u0000X\u0081T¢\u0006\b\n\u0000\u0012\u0004\b\u000e\u0010\u0002R\u0014\u0010\u000f\u001a\u00020\u0004X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0006R\u0014\u0010\u0011\u001a\u00020\u0004X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0006¨\u0006\u0013"}, d2 = {"Lcom/amazon/tarazed/sessionmanager/HeadsUpNotificationManager$Companion;", "", "()V", "AUTO_END_MESSAGE", "", "getAUTO_END_MESSAGE$TarazedAndroidLibrary_release", "()I", "AUTO_PAUSE_MESSAGE", "getAUTO_PAUSE_MESSAGE$TarazedAndroidLibrary_release", "NOTIFICATION_CHANNEL_ID", "", Constants.NOTIFICATION_ID, "PERMISSION_NOTIFICATION_EXPIRY_MS", "", "PERMISSION_NOTIFICATION_EXPIRY_MS$annotations", "PERMISSION_REQUEST_MESSAGE", "getPERMISSION_REQUEST_MESSAGE$TarazedAndroidLibrary_release", "RESUME_MESSAGE", "getRESUME_MESSAGE$TarazedAndroidLibrary_release", "TarazedAndroidLibrary_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes13.dex */
    public static final class Companion {
        private Companion() {
        }

        @VisibleForTesting
        public static /* synthetic */ void PERMISSION_NOTIFICATION_EXPIRY_MS$annotations() {
        }

        public final int getAUTO_END_MESSAGE$TarazedAndroidLibrary_release() {
            return HeadsUpNotificationManager.AUTO_END_MESSAGE;
        }

        public final int getAUTO_PAUSE_MESSAGE$TarazedAndroidLibrary_release() {
            return HeadsUpNotificationManager.AUTO_PAUSE_MESSAGE;
        }

        public final int getPERMISSION_REQUEST_MESSAGE$TarazedAndroidLibrary_release() {
            return HeadsUpNotificationManager.PERMISSION_REQUEST_MESSAGE;
        }

        public final int getRESUME_MESSAGE$TarazedAndroidLibrary_release() {
            return HeadsUpNotificationManager.RESUME_MESSAGE;
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 1, 16})
    /* loaded from: classes13.dex */
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = new int[TarazedNotificationEvent.values().length];

        static {
            $EnumSwitchMapping$0[TarazedNotificationEvent.SESSION_PAUSE_3P_APP_EVENT.ordinal()] = 1;
            $EnumSwitchMapping$0[TarazedNotificationEvent.SESSION_ENDING_INACTIVITY.ordinal()] = 2;
            $EnumSwitchMapping$0[TarazedNotificationEvent.SESSION_STARTING_3P_BACKGROUND.ordinal()] = 3;
            $EnumSwitchMapping$0[TarazedNotificationEvent.APP_FOREGROUND.ordinal()] = 4;
            $EnumSwitchMapping$0[TarazedNotificationEvent.RESUME_REQUESTED.ordinal()] = 5;
            $EnumSwitchMapping$0[TarazedNotificationEvent.SESSION_END.ordinal()] = 6;
        }
    }

    public HeadsUpNotificationManager(@NotNull Context context, @NotNull NotificationCompat.Builder notificationBuilder, @NotNull ActivityTracker activityTracker, @NotNull DeviceInfoUtilityAndroid deviceInfo, @NotNull TarazedSessionNotifier notifier) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(notificationBuilder, "notificationBuilder");
        Intrinsics.checkParameterIsNotNull(activityTracker, "activityTracker");
        Intrinsics.checkParameterIsNotNull(deviceInfo, "deviceInfo");
        Intrinsics.checkParameterIsNotNull(notifier, "notifier");
        this.context = context;
        this.notificationBuilder = notificationBuilder;
        this.activityTracker = activityTracker;
        this.deviceInfo = deviceInfo;
        this.notifier = notifier;
        this.clearNotificationOnSessionEnd = true;
        setupBuilder();
        int i = Build.VERSION.SDK_INT;
        setupNotificationChannel();
        this.notificationHandler = new HeadsUpNotificationManager$notificationHandler$1(this, null);
    }

    private final PendingIntent createOpenAppPendingIntent() {
        Intent launchIntentForPackage;
        Context applicationContext = this.context.getApplicationContext();
        if (applicationContext != null) {
            Application application = (Application) applicationContext;
            if (this.activityTracker.getCurrentActivity() != null) {
                Activity currentActivity = this.activityTracker.getCurrentActivity();
                if (currentActivity == null) {
                    Intrinsics.throwNpe();
                }
                launchIntentForPackage = new Intent(application, currentActivity.getClass());
                launchIntentForPackage.setFlags(536870912);
                launchIntentForPackage.addCategory("android.intent.category.LAUNCHER");
                launchIntentForPackage.putExtra("TarazedEmptyBundle", Bundle.EMPTY);
            } else {
                launchIntentForPackage = application.getPackageManager().getLaunchIntentForPackage(application.getPackageName());
            }
            PendingIntent activity = PendingIntent.getActivity(this.context.getApplicationContext(), 0, launchIntentForPackage, 134217728);
            Intrinsics.checkExpressionValueIsNotNull(activity, "PendingIntent.getActivit…tent.FLAG_UPDATE_CURRENT)");
            return activity;
        }
        throw new TypeCastException("null cannot be cast to non-null type android.app.Application");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final NotificationManager getNotificationManager() {
        Object systemService = this.context.getSystemService("notification");
        if (systemService != null) {
            return (NotificationManager) systemService;
        }
        throw new TypeCastException("null cannot be cast to non-null type android.app.NotificationManager");
    }

    @VisibleForTesting
    public static /* synthetic */ void notificationHandler$annotations() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void notify(int i) {
        this.notificationBuilder.setContentIntent(createOpenAppPendingIntent());
        if (i == PERMISSION_REQUEST_MESSAGE) {
            this.notificationBuilder.setTimeoutAfter(120000L);
        }
        String string = this.context.getString(i);
        Intrinsics.checkExpressionValueIsNotNull(string, "context.getString(msgRes)");
        getNotificationManager().notify(NOTIFICATION_ID, this.notificationBuilder.setContentText(string).setStyle(new NotificationCompat.BigTextStyle().bigText(string)).build());
    }

    private final void setupBuilder() {
        this.notificationBuilder.setPriority(1).setSmallIcon(R.drawable.cs_icon).setContentTitle(this.context.getString(R.string.amazon_customer_service)).setAutoCancel(true).setOngoing(false).setShowWhen(false).setExtras(GeneratedOutlineSupport1.outline13("is_child", true));
        int i = Build.VERSION.SDK_INT;
    }

    @RequiresApi(26)
    private final void setupNotificationChannel() {
        this.notificationBuilder.setChannelId(NOTIFICATION_CHANNEL_ID);
        NotificationChannel notificationChannel = new NotificationChannel(NOTIFICATION_CHANNEL_ID, this.context.getString(R.string.amazon_customer_service), 4);
        notificationChannel.setLightColor(-16776961);
        notificationChannel.setLockscreenVisibility(1);
        int i = Build.VERSION.SDK_INT;
        notificationChannel.setSound(Settings.System.DEFAULT_NOTIFICATION_URI, new AudioAttributes.Builder().setContentType(4).setUsage(5).build());
        getNotificationManager().createNotificationChannel(notificationChannel);
    }

    @NotNull
    public final Function2<TarazedNotificationEvent, Continuation<? super Unit>, Object> getNotificationHandler$TarazedAndroidLibrary_release() {
        return this.notificationHandler;
    }

    public final void subscribeToNotifier() {
        TarazedSessionNotifier.subscribe$default(this.notifier, this.notificationHandler, ListenerPriority.MEDIUM, false, 4, null);
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    @Inject
    public HeadsUpNotificationManager(@NotNull Context context, @NotNull ActivityTracker activityTracker, @NotNull DeviceInfoUtilityAndroid deviceInfo, @NotNull TarazedSessionNotifier notifier) {
        this(context, new NotificationCompat.Builder(context), activityTracker, deviceInfo, notifier);
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(activityTracker, "activityTracker");
        Intrinsics.checkParameterIsNotNull(deviceInfo, "deviceInfo");
        Intrinsics.checkParameterIsNotNull(notifier, "notifier");
    }
}
