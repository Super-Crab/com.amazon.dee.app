package com.amazon.tarazed.sessionmanager;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;
import androidx.annotation.VisibleForTesting;
import androidx.core.app.NotificationCompat;
import com.amazon.deecomms.common.Constants;
import com.amazon.tarazed.R;
import com.amazon.tarazed.core.notifier.TarazedNotificationEvent;
import com.android.tools.r8.GeneratedOutlineSupport1;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: SessionNotificationManager.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0010\b\u0000\u0018\u0000 02\u00020\u0001:\u00010B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0014\u0010\u001e\u001a\u00020\u001f2\n\b\u0002\u0010 \u001a\u0004\u0018\u00010!H\u0002J\u0010\u0010\"\u001a\u00020\u00182\u0006\u0010#\u001a\u00020\tH\u0002J\r\u0010$\u001a\u00020\u0018H\u0000¢\u0006\u0002\b%J\b\u0010&\u001a\u00020\u0018H\u0002J\b\u0010'\u001a\u00020\u0018H\u0002J\b\u0010(\u001a\u00020\u0018H\u0002J\b\u0010)\u001a\u00020\u0018H\u0002J\u0006\u0010*\u001a\u00020\u0018J\b\u0010+\u001a\u00020\u0018H\u0002J\b\u0010,\u001a\u00020\u0018H\u0002J\u001e\u0010-\u001a\u00020\u00182\n\b\u0002\u0010.\u001a\u0004\u0018\u00010!2\b\b\u0002\u0010/\u001a\u00020\tH\u0002R\u000e\u0010\u0007\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R$\u0010\f\u001a\u00020\r8\u0000@\u0000X\u0081\u000e¢\u0006\u0014\n\u0000\u0012\u0004\b\u000e\u0010\u000f\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R2\u0010\u0014\u001a\u001e\b\u0001\u0012\u0004\u0012\u00020\u0016\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00180\u0017\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0015ø\u0001\u0000¢\u0006\n\n\u0002\u0010\u001b\u001a\u0004\b\u0019\u0010\u001aR2\u0010\u001c\u001a\u001e\b\u0001\u0012\u0004\u0012\u00020\u0016\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00180\u0017\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0015ø\u0001\u0000¢\u0006\n\n\u0002\u0010\u001b\u001a\u0004\b\u001d\u0010\u001a\u0082\u0002\u0004\n\u0002\b\u0019¨\u00061"}, d2 = {"Lcom/amazon/tarazed/sessionmanager/SessionNotificationManager;", "", "sessionAndroidService", "Lcom/amazon/tarazed/sessionmanager/TarazedSessionAndroidService;", "notificationBuilder", "Landroidx/core/app/NotificationCompat$Builder;", "(Lcom/amazon/tarazed/sessionmanager/TarazedSessionAndroidService;Landroidx/core/app/NotificationCompat$Builder;)V", "context", "hasProfileSwitchOccurred", "", "hasSessionStarted", "isSessionPaused", "notificationManager", "Landroid/app/NotificationManager;", "notificationManager$annotations", "()V", "getNotificationManager$TarazedAndroidLibrary_release", "()Landroid/app/NotificationManager;", "setNotificationManager$TarazedAndroidLibrary_release", "(Landroid/app/NotificationManager;)V", "sessionNotificationHandler1P", "Lkotlin/Function2;", "Lcom/amazon/tarazed/core/notifier/TarazedNotificationEvent;", "Lkotlin/coroutines/Continuation;", "", "getSessionNotificationHandler1P", "()Lkotlin/jvm/functions/Function2;", "Lkotlin/jvm/functions/Function2;", "sessionNotificationHandler3P", "getSessionNotificationHandler3P", "createNotification", "Landroid/app/Notification;", "notificationText", "", "endForegroundService", "removeNotification", "forceNotifySessionEnded", "forceNotifySessionEnded$TarazedAndroidLibrary_release", "notifySessionActive", "notifySessionConnecting", "notifySessionEnded", "notifySessionPaused", "notifySessionStarting", "setupNotificationBuilder", "setupNotificationChannel", "showNotification", "text", "isForegroundNotification", "Companion", "TarazedAndroidLibrary_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class SessionNotificationManager {
    @Deprecated
    public static final Companion Companion = new Companion(null);
    private static final String NOTIFICATION_CHANNEL_ID = "847683";
    private static final int NOTIFICATION_ID = 847683;
    private final TarazedSessionAndroidService context;
    private boolean hasProfileSwitchOccurred;
    private boolean hasSessionStarted;
    private boolean isSessionPaused;
    private final NotificationCompat.Builder notificationBuilder;
    @NotNull
    private NotificationManager notificationManager;
    private final TarazedSessionAndroidService sessionAndroidService;
    @NotNull
    private final Function2<TarazedNotificationEvent, Continuation<? super Unit>, Object> sessionNotificationHandler1P;
    @NotNull
    private final Function2<TarazedNotificationEvent, Continuation<? super Unit>, Object> sessionNotificationHandler3P;

    /* compiled from: SessionNotificationManager.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\b\u0082\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0007"}, d2 = {"Lcom/amazon/tarazed/sessionmanager/SessionNotificationManager$Companion;", "", "()V", "NOTIFICATION_CHANNEL_ID", "", Constants.NOTIFICATION_ID, "", "TarazedAndroidLibrary_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes13.dex */
    private static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 1, 16})
    /* loaded from: classes13.dex */
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = new int[TarazedNotificationEvent.values().length];
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;

        static {
            $EnumSwitchMapping$0[TarazedNotificationEvent.SESSION_END.ordinal()] = 1;
            $EnumSwitchMapping$1 = new int[TarazedNotificationEvent.values().length];
            $EnumSwitchMapping$1[TarazedNotificationEvent.MEDIA_CONNECTING.ordinal()] = 1;
            $EnumSwitchMapping$1[TarazedNotificationEvent.MEDIA_CONNECTED.ordinal()] = 2;
            $EnumSwitchMapping$1[TarazedNotificationEvent.SESSION_RESUME_AGENT.ordinal()] = 3;
            $EnumSwitchMapping$1[TarazedNotificationEvent.SESSION_RESUME_CUSTOMER.ordinal()] = 4;
            $EnumSwitchMapping$1[TarazedNotificationEvent.SESSION_PAUSE_CUSTOMER.ordinal()] = 5;
            $EnumSwitchMapping$1[TarazedNotificationEvent.SESSION_PAUSE_AGENT.ordinal()] = 6;
            $EnumSwitchMapping$1[TarazedNotificationEvent.SESSION_PAUSE_3P_APP_EVENT.ordinal()] = 7;
            $EnumSwitchMapping$1[TarazedNotificationEvent.SESSION_END.ordinal()] = 8;
            $EnumSwitchMapping$1[TarazedNotificationEvent.PROFILE_SWITCH.ordinal()] = 9;
        }
    }

    public SessionNotificationManager(@NotNull TarazedSessionAndroidService sessionAndroidService, @NotNull NotificationCompat.Builder notificationBuilder) {
        Intrinsics.checkParameterIsNotNull(sessionAndroidService, "sessionAndroidService");
        Intrinsics.checkParameterIsNotNull(notificationBuilder, "notificationBuilder");
        this.sessionAndroidService = sessionAndroidService;
        this.notificationBuilder = notificationBuilder;
        this.context = this.sessionAndroidService;
        Object systemService = this.context.getSystemService("notification");
        if (systemService != null) {
            this.notificationManager = (NotificationManager) systemService;
            setupNotificationBuilder();
            setupNotificationChannel();
            this.sessionNotificationHandler3P = new SessionNotificationManager$sessionNotificationHandler3P$1(this, null);
            this.sessionNotificationHandler1P = new SessionNotificationManager$sessionNotificationHandler1P$1(this, null);
            return;
        }
        throw new TypeCastException("null cannot be cast to non-null type android.app.NotificationManager");
    }

    private final Notification createNotification(String str) {
        this.notificationBuilder.setContentText(str);
        Notification build = this.notificationBuilder.build();
        Intrinsics.checkExpressionValueIsNotNull(build, "notificationBuilder.build()");
        return build;
    }

    static /* synthetic */ Notification createNotification$default(SessionNotificationManager sessionNotificationManager, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            str = null;
        }
        return sessionNotificationManager.createNotification(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void endForegroundService(boolean z) {
        this.sessionAndroidService.stopForeground(z);
    }

    @VisibleForTesting
    public static /* synthetic */ void notificationManager$annotations() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void notifySessionActive() {
        String string = this.context.getResources().getString(R.string.screen_sharing_active);
        Intrinsics.checkExpressionValueIsNotNull(string, "context.resources.getStr…ng.screen_sharing_active)");
        showNotification$default(this, string, false, 2, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void notifySessionConnecting() {
        String string = this.context.getResources().getString(R.string.screen_sharing_connecting);
        Intrinsics.checkExpressionValueIsNotNull(string, "context.resources.getStr…creen_sharing_connecting)");
        showNotification$default(this, string, false, 2, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void notifySessionEnded() {
        String string = this.context.getResources().getString(R.string.screen_sharing_ended);
        Intrinsics.checkExpressionValueIsNotNull(string, "context.resources.getStr…ing.screen_sharing_ended)");
        showNotification$default(this, string, false, 2, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void notifySessionPaused() {
        String string = this.context.getResources().getString(R.string.screen_sharing_paused);
        Intrinsics.checkExpressionValueIsNotNull(string, "context.resources.getStr…ng.screen_sharing_paused)");
        showNotification$default(this, string, false, 2, null);
    }

    private final void setupNotificationBuilder() {
        this.notificationBuilder.setSmallIcon(R.drawable.cs_icon).setContentTitle(this.context.getString(R.string.amazon_customer_service)).setAutoCancel(true).setOngoing(false).setShowWhen(false).setExtras(GeneratedOutlineSupport1.outline13("is_child", true));
    }

    private final void setupNotificationChannel() {
        int i = Build.VERSION.SDK_INT;
        this.notificationBuilder.setChannelId(NOTIFICATION_CHANNEL_ID);
        NotificationChannel notificationChannel = new NotificationChannel(NOTIFICATION_CHANNEL_ID, this.context.getString(R.string.amazon_customer_service), 0);
        notificationChannel.setLightColor(-16776961);
        notificationChannel.setLockscreenVisibility(0);
        this.notificationManager.createNotificationChannel(notificationChannel);
    }

    private final void showNotification(String str, boolean z) {
        Notification createNotification = createNotification(str);
        if (z) {
            this.sessionAndroidService.startForeground(NOTIFICATION_ID, createNotification);
        } else {
            this.notificationManager.notify(NOTIFICATION_ID, createNotification);
        }
    }

    static /* synthetic */ void showNotification$default(SessionNotificationManager sessionNotificationManager, String str, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            str = null;
        }
        if ((i & 2) != 0) {
            z = true;
        }
        sessionNotificationManager.showNotification(str, z);
    }

    public final void forceNotifySessionEnded$TarazedAndroidLibrary_release() {
        String string = this.context.getResources().getString(R.string.screen_sharing_ended);
        Intrinsics.checkExpressionValueIsNotNull(string, "context.resources.getStr…ing.screen_sharing_ended)");
        showNotification(string, false);
        this.hasSessionStarted = false;
    }

    @NotNull
    public final NotificationManager getNotificationManager$TarazedAndroidLibrary_release() {
        return this.notificationManager;
    }

    @NotNull
    public final Function2<TarazedNotificationEvent, Continuation<? super Unit>, Object> getSessionNotificationHandler1P() {
        return this.sessionNotificationHandler1P;
    }

    @NotNull
    public final Function2<TarazedNotificationEvent, Continuation<? super Unit>, Object> getSessionNotificationHandler3P() {
        return this.sessionNotificationHandler3P;
    }

    public final void notifySessionStarting() {
        showNotification$default(this, null, false, 3, null);
        this.hasProfileSwitchOccurred = false;
    }

    public final void setNotificationManager$TarazedAndroidLibrary_release(@NotNull NotificationManager notificationManager) {
        Intrinsics.checkParameterIsNotNull(notificationManager, "<set-?>");
        this.notificationManager = notificationManager;
    }
}
