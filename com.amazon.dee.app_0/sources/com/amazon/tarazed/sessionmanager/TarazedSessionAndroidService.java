package com.amazon.tarazed.sessionmanager;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.os.ResultReceiver;
import androidx.core.app.NotificationCompat;
import com.amazon.deecomms.common.network.AppUrl;
import com.amazon.identity.auth.device.api.MAPAccountManager;
import com.amazon.tarazed.activity.ActivityTracker;
import com.amazon.tarazed.core.TarazedIntents;
import com.amazon.tarazed.core.logging.TarazedSessionLogger;
import com.amazon.tarazed.core.session.TarazedSession;
import com.amazon.tarazed.core.sessionclient.sessioncache.CacheHelper;
import com.amazon.tarazed.core.sessionclient.sessioncache.Post23SessionClientCache;
import com.amazon.tarazed.dagger.components.LibraryComponent;
import com.amazon.tarazed.dagger.injectors.LibraryInjector;
import com.android.tools.r8.GeneratedOutlineSupport1;
import javax.inject.Inject;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: TarazedSessionAndroidService.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0004\u0018\u0000 /2\u00020\u0001:\u0001/B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u001f\u001a\u00020 H\u0002J\u0010\u0010!\u001a\u00020 2\u0006\u0010\"\u001a\u00020#H\u0002J\u0010\u0010$\u001a\u00020 2\u0006\u0010\"\u001a\u00020#H\u0002J\u0010\u0010%\u001a\u00020 2\u0006\u0010\"\u001a\u00020#H\u0002J\u0010\u0010&\u001a\u00020 2\u0006\u0010\"\u001a\u00020#H\u0002J\u0012\u0010'\u001a\u0004\u0018\u00010(2\u0006\u0010\"\u001a\u00020#H\u0016J\b\u0010)\u001a\u00020 H\u0016J\b\u0010*\u001a\u00020 H\u0016J\"\u0010+\u001a\u00020,2\b\u0010\"\u001a\u0004\u0018\u00010#2\u0006\u0010-\u001a\u00020,2\u0006\u0010.\u001a\u00020,H\u0016R\u001e\u0010\u0003\u001a\u00020\u00048\u0000@\u0000X\u0081.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001e\u0010\t\u001a\u00020\n8\u0000@\u0000X\u0081.¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u001e\u0010\u000f\u001a\u00020\u00108\u0006@\u0006X\u0087.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R\u001e\u0010\u0015\u001a\u00020\u00168\u0000@\u0000X\u0081.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001aR\u0016\u0010\u001b\u001a\u0004\u0018\u00010\u001c8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u001d\u0010\u001e\u0082\u0002\u0004\n\u0002\b\u0019¨\u00060"}, d2 = {"Lcom/amazon/tarazed/sessionmanager/TarazedSessionAndroidService;", "Landroid/app/Service;", "()V", "activityTracker", "Lcom/amazon/tarazed/activity/ActivityTracker;", "getActivityTracker$TarazedAndroidLibrary_release", "()Lcom/amazon/tarazed/activity/ActivityTracker;", "setActivityTracker$TarazedAndroidLibrary_release", "(Lcom/amazon/tarazed/activity/ActivityTracker;)V", "controller", "Lcom/amazon/tarazed/sessionmanager/TarazedController;", "getController$TarazedAndroidLibrary_release", "()Lcom/amazon/tarazed/sessionmanager/TarazedController;", "setController$TarazedAndroidLibrary_release", "(Lcom/amazon/tarazed/sessionmanager/TarazedController;)V", "logger", "Lcom/amazon/tarazed/core/logging/TarazedSessionLogger;", "getLogger", "()Lcom/amazon/tarazed/core/logging/TarazedSessionLogger;", "setLogger", "(Lcom/amazon/tarazed/core/logging/TarazedSessionLogger;)V", "resourceManager", "Lcom/amazon/tarazed/sessionmanager/TarazedResourceManager;", "getResourceManager$TarazedAndroidLibrary_release", "()Lcom/amazon/tarazed/sessionmanager/TarazedResourceManager;", "setResourceManager$TarazedAndroidLibrary_release", "(Lcom/amazon/tarazed/sessionmanager/TarazedResourceManager;)V", "session", "Lcom/amazon/tarazed/core/session/TarazedSession;", "getSession", "()Lcom/amazon/tarazed/core/session/TarazedSession;", "displaySideDrawer", "", "handleDisplayToast", MAPAccountManager.KEY_INTENT, "Landroid/content/Intent;", "handleLaunchRequest", "handleStateRequest", "handleUnexpectedRequest", "onBind", "Landroid/os/IBinder;", "onCreate", "onDestroy", "onStartCommand", "", "flags", AppUrl.ACMS.QueryParam.Keys.MESSAGE_START_ID, "Companion", "TarazedAndroidLibrary_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class TarazedSessionAndroidService extends Service {
    @NotNull
    public static final String ACTION_DISPLAY_SESSION_TOAST = "com.amazon.tarazed.DISPLAY_SESSION_TOAST";
    private static final String ACTION_SESSION_STATE_REQUEST = "com.amazon.tarazed.SESSION_STATE_REQUEST";
    private static final String BUNDLE_KEY_SESSION_STATE = "com.amazon.tarazed.SESSION_STATE";
    public static final Companion Companion = new Companion(null);
    @NotNull
    public static final String EXTRA_SESSION_TOAST_MESSAGE = "com.amazon.tarazed.SESSION_TOAST_MESSAGE";
    private static final String EXTRA_STATE_REQUEST_RESULT_RECEIVER = "com.amazon.tarazed.STATE_REQUEST_RESULT_RECEIVER";
    private static final String METRIC_INTENT_NULL = "IntentNull";
    private static final String METRIC_RECEIVED_NULL_LAUNCH_REQUEST = "ReceivedNullLaunchRequest";
    private static final String METRIC_RESULT_RECEIVER_NULL = "NullResultReceiver";
    private static final String TAG = "TarazedSessionAndroidService";
    @Inject
    @NotNull
    public ActivityTracker activityTracker;
    @Inject
    @NotNull
    public TarazedController controller;
    @Inject
    @NotNull
    public TarazedSessionLogger logger;
    @Inject
    @NotNull
    public TarazedResourceManager resourceManager;

    /* compiled from: TarazedSessionAndroidService.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J#\u0010\r\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0011H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u0012J\u0006\u0010\u0013\u001a\u00020\u0014J\u0006\u0010\u0015\u001a\u00020\u0014J\u000e\u0010\u0016\u001a\u00020\u00142\u0006\u0010\u000e\u001a\u00020\u000fJ\u0006\u0010\u0017\u001a\u00020\u0014R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0018"}, d2 = {"Lcom/amazon/tarazed/sessionmanager/TarazedSessionAndroidService$Companion;", "", "()V", "ACTION_DISPLAY_SESSION_TOAST", "", "ACTION_SESSION_STATE_REQUEST", "BUNDLE_KEY_SESSION_STATE", "EXTRA_SESSION_TOAST_MESSAGE", "EXTRA_STATE_REQUEST_RESULT_RECEIVER", "METRIC_INTENT_NULL", "METRIC_RECEIVED_NULL_LAUNCH_REQUEST", "METRIC_RESULT_RECEIVER_NULL", "TAG", "getCachedCredentials", "context", "Landroid/content/Context;", "logger", "Lcom/amazon/tarazed/core/logging/TarazedSessionLogger;", "(Landroid/content/Context;Lcom/amazon/tarazed/core/logging/TarazedSessionLogger;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "isSessionActive", "", "isSessionPaused", "isSessionSuspended", "isStreamPaused", "TarazedAndroidLibrary_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes13.dex */
    public static final class Companion {
        private Companion() {
        }

        @Nullable
        public final Object getCachedCredentials(@NotNull Context context, @NotNull TarazedSessionLogger tarazedSessionLogger, @NotNull Continuation<? super String> continuation) {
            LibraryComponent component = LibraryInjector.getComponent();
            Intrinsics.checkExpressionValueIsNotNull(component, "LibraryInjector.getComponent()");
            TarazedResourceManager resourceManager = component.getResourceManager();
            resourceManager.setContext$TarazedAndroidLibrary_release(context);
            resourceManager.setLogger$TarazedAndroidLibrary_release(tarazedSessionLogger);
            return resourceManager.getCachedCredentials$TarazedAndroidLibrary_release(continuation);
        }

        public final boolean isSessionActive() {
            LibraryComponent component = LibraryInjector.getComponent();
            Intrinsics.checkExpressionValueIsNotNull(component, "LibraryInjector.getComponent()");
            return component.getResourceManager().isSessionActive$TarazedAndroidLibrary_release();
        }

        public final boolean isSessionPaused() {
            LibraryComponent component = LibraryInjector.getComponent();
            Intrinsics.checkExpressionValueIsNotNull(component, "LibraryInjector.getComponent()");
            return component.getResourceManager().isSessionPaused$TarazedAndroidLibrary_release();
        }

        public final boolean isSessionSuspended(@NotNull Context context) {
            Intrinsics.checkParameterIsNotNull(context, "context");
            return CacheHelper.INSTANCE.exists(context, Post23SessionClientCache.LAUNCH_REQUEST_CACHE);
        }

        public final boolean isStreamPaused() {
            LibraryComponent component = LibraryInjector.getComponent();
            Intrinsics.checkExpressionValueIsNotNull(component, "LibraryInjector.getComponent()");
            return component.getResourceManager().isStreamPaused$TarazedAndroidLibrary_release();
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    private final void displaySideDrawer() {
        TarazedSessionLogger tarazedSessionLogger = this.logger;
        if (tarazedSessionLogger == null) {
            Intrinsics.throwUninitializedPropertyAccessException("logger");
        }
        tarazedSessionLogger.i(TAG, "Opening side-drawer UI");
        TarazedController tarazedController = this.controller;
        if (tarazedController == null) {
            Intrinsics.throwUninitializedPropertyAccessException("controller");
        }
        tarazedController.showNavigationDrawer$TarazedAndroidLibrary_release();
    }

    private final TarazedSession getSession() {
        TarazedResourceManager tarazedResourceManager = this.resourceManager;
        if (tarazedResourceManager == null) {
            Intrinsics.throwUninitializedPropertyAccessException("resourceManager");
        }
        return tarazedResourceManager.getSession$TarazedAndroidLibrary_release();
    }

    private final void handleDisplayToast(Intent intent) {
        TarazedController tarazedController = this.controller;
        if (tarazedController == null) {
            Intrinsics.throwUninitializedPropertyAccessException("controller");
        }
        if (!tarazedController.isSessionActive$TarazedAndroidLibrary_release()) {
            TarazedSessionLogger tarazedSessionLogger = this.logger;
            if (tarazedSessionLogger == null) {
                Intrinsics.throwUninitializedPropertyAccessException("logger");
            }
            tarazedSessionLogger.i(TAG, "There's no active session, not displaying session toast.");
        } else if (!intent.hasExtra(EXTRA_SESSION_TOAST_MESSAGE)) {
            TarazedSessionLogger tarazedSessionLogger2 = this.logger;
            if (tarazedSessionLogger2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("logger");
            }
            tarazedSessionLogger2.i(TAG, "Session toast is null, nothing to display");
        } else {
            TarazedController tarazedController2 = this.controller;
            if (tarazedController2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("controller");
            }
            tarazedController2.displayToast$TarazedAndroidLibrary_release(intent.getStringExtra(EXTRA_SESSION_TOAST_MESSAGE));
        }
    }

    private final void handleLaunchRequest(Intent intent) {
        String stringExtra = intent.getStringExtra(TarazedIntents.SessionAndroidService.EXTRA_LAUNCH_REQUEST);
        if (stringExtra == null) {
            TarazedSessionLogger tarazedSessionLogger = this.logger;
            if (tarazedSessionLogger == null) {
                Intrinsics.throwUninitializedPropertyAccessException("logger");
            }
            tarazedSessionLogger.w(TAG, "Launch request was null");
            TarazedResourceManager tarazedResourceManager = this.resourceManager;
            if (tarazedResourceManager == null) {
                Intrinsics.throwUninitializedPropertyAccessException("resourceManager");
            }
            tarazedResourceManager.getMetricsHelper$TarazedAndroidLibrary_release().addCount(TAG, METRIC_RECEIVED_NULL_LAUNCH_REQUEST, 1.0d);
            int i = Build.VERSION.SDK_INT;
            TarazedResourceManager tarazedResourceManager2 = this.resourceManager;
            if (tarazedResourceManager2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("resourceManager");
            }
            tarazedResourceManager2.getSessionNotificationManager$TarazedAndroidLibrary_release().notifySessionStarting();
            stopForeground(true);
            return;
        }
        TarazedController tarazedController = this.controller;
        if (tarazedController == null) {
            Intrinsics.throwUninitializedPropertyAccessException("controller");
        }
        tarazedController.handleLaunchRequest$TarazedAndroidLibrary_release(stringExtra);
    }

    private final void handleStateRequest(Intent intent) {
        ResultReceiver resultReceiver = (ResultReceiver) intent.getParcelableExtra(EXTRA_STATE_REQUEST_RESULT_RECEIVER);
        if (resultReceiver == null) {
            TarazedSessionLogger tarazedSessionLogger = this.logger;
            if (tarazedSessionLogger == null) {
                Intrinsics.throwUninitializedPropertyAccessException("logger");
            }
            tarazedSessionLogger.e(TAG, "Error fetching tarazed state resultReceiver, Not handling state request");
            TarazedResourceManager tarazedResourceManager = this.resourceManager;
            if (tarazedResourceManager == null) {
                Intrinsics.throwUninitializedPropertyAccessException("resourceManager");
            }
            tarazedResourceManager.getMetricsHelper$TarazedAndroidLibrary_release().addCount(TAG, METRIC_RESULT_RECEIVER_NULL, 1.0d);
            return;
        }
        Bundle bundle = new Bundle();
        TarazedSession session = getSession();
        bundle.putString(BUNDLE_KEY_SESSION_STATE, session != null ? session.getPlaybackState() : null);
        resultReceiver.send(0, bundle);
    }

    private final void handleUnexpectedRequest(Intent intent) {
        TarazedSessionLogger tarazedSessionLogger = this.logger;
        if (tarazedSessionLogger == null) {
            Intrinsics.throwUninitializedPropertyAccessException("logger");
        }
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Unexpected request: ");
        outline107.append(intent.getAction());
        tarazedSessionLogger.w(TAG, outline107.toString());
    }

    @NotNull
    public final ActivityTracker getActivityTracker$TarazedAndroidLibrary_release() {
        ActivityTracker activityTracker = this.activityTracker;
        if (activityTracker == null) {
            Intrinsics.throwUninitializedPropertyAccessException("activityTracker");
        }
        return activityTracker;
    }

    @NotNull
    public final TarazedController getController$TarazedAndroidLibrary_release() {
        TarazedController tarazedController = this.controller;
        if (tarazedController == null) {
            Intrinsics.throwUninitializedPropertyAccessException("controller");
        }
        return tarazedController;
    }

    @NotNull
    public final TarazedSessionLogger getLogger() {
        TarazedSessionLogger tarazedSessionLogger = this.logger;
        if (tarazedSessionLogger == null) {
            Intrinsics.throwUninitializedPropertyAccessException("logger");
        }
        return tarazedSessionLogger;
    }

    @NotNull
    public final TarazedResourceManager getResourceManager$TarazedAndroidLibrary_release() {
        TarazedResourceManager tarazedResourceManager = this.resourceManager;
        if (tarazedResourceManager == null) {
            Intrinsics.throwUninitializedPropertyAccessException("resourceManager");
        }
        return tarazedResourceManager;
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
        LibraryInjector.getComponent().inject(this);
        TarazedResourceManager tarazedResourceManager = this.resourceManager;
        if (tarazedResourceManager == null) {
            Intrinsics.throwUninitializedPropertyAccessException("resourceManager");
        }
        tarazedResourceManager.setSessionNotificationManager$TarazedAndroidLibrary_release(new SessionNotificationManager(this, new NotificationCompat.Builder(this)));
        TarazedResourceManager tarazedResourceManager2 = this.resourceManager;
        if (tarazedResourceManager2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("resourceManager");
        }
        tarazedResourceManager2.setContext$TarazedAndroidLibrary_release(this);
    }

    @Override // android.app.Service
    public void onDestroy() {
        super.onDestroy();
        TarazedResourceManager tarazedResourceManager = this.resourceManager;
        if (tarazedResourceManager == null) {
            Intrinsics.throwUninitializedPropertyAccessException("resourceManager");
        }
        tarazedResourceManager.unbindSessionCacheServiceConnection$TarazedAndroidLibrary_release();
    }

    @Override // android.app.Service
    public int onStartCommand(@Nullable Intent intent, int i, int i2) {
        if (intent == null) {
            TarazedResourceManager tarazedResourceManager = this.resourceManager;
            if (tarazedResourceManager == null) {
                Intrinsics.throwUninitializedPropertyAccessException("resourceManager");
            }
            tarazedResourceManager.getMetricsHelper$TarazedAndroidLibrary_release().addCountHighPriority(TAG, METRIC_INTENT_NULL, 1.0d);
            return 2;
        }
        String action = intent.getAction();
        if (action != null) {
            switch (action.hashCode()) {
                case -2098856981:
                    if (action.equals(TarazedIntents.SessionAndroidService.START_SESSION)) {
                        handleLaunchRequest(intent);
                        return 1;
                    }
                    break;
                case -1953429941:
                    if (action.equals(TarazedIntents.SessionAndroidService.END_SESSION_ON_3P_APP_BACKGROUND_TIMEOUT)) {
                        TarazedController tarazedController = this.controller;
                        if (tarazedController == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("controller");
                        }
                        tarazedController.endSessionOn3pAppPauseTimeout$TarazedAndroidLibrary_release();
                        return 1;
                    }
                    break;
                case -1436908370:
                    if (action.equals(TarazedIntents.SessionAndroidService.RESUME_SUSPENDED_SESSION)) {
                        TarazedController tarazedController2 = this.controller;
                        if (tarazedController2 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("controller");
                        }
                        tarazedController2.resumeSuspendedSession$TarazedAndroidLibrary_release();
                        return 1;
                    }
                    break;
                case -1419901724:
                    if (action.equals(TarazedIntents.SessionAndroidService.SUSPEND_SESSION_PROFILE_SWITCH)) {
                        TarazedController tarazedController3 = this.controller;
                        if (tarazedController3 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("controller");
                        }
                        tarazedController3.suspendSessionOnProfileSwitch$TarazedAndroidLibrary_release();
                        return 1;
                    }
                    break;
                case -867726011:
                    if (action.equals(TarazedIntents.SessionAndroidService.SUSPEND_SESSION)) {
                        TarazedController tarazedController4 = this.controller;
                        if (tarazedController4 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("controller");
                        }
                        tarazedController4.suspendSession$TarazedAndroidLibrary_release();
                        return 1;
                    }
                    break;
                case -832882817:
                    if (action.equals(TarazedIntents.SessionAndroidService.END_SESSION_ON_3P_APP_EVENT)) {
                        TarazedController tarazedController5 = this.controller;
                        if (tarazedController5 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("controller");
                        }
                        tarazedController5.endSessionOn3pAppEvent$TarazedAndroidLibrary_release();
                        return 1;
                    }
                    break;
                case -547936284:
                    if (action.equals(TarazedIntents.SessionAndroidService.END_SESSION)) {
                        TarazedController tarazedController6 = this.controller;
                        if (tarazedController6 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("controller");
                        }
                        tarazedController6.endSession$TarazedAndroidLibrary_release();
                        return 1;
                    }
                    break;
                case -505455370:
                    if (action.equals(TarazedIntents.SessionAndroidService.END_SESSION_IMMEDIATE)) {
                        TarazedController tarazedController7 = this.controller;
                        if (tarazedController7 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("controller");
                        }
                        tarazedController7.endSessionImmediate$TarazedAndroidLibrary_release();
                        return 1;
                    }
                    break;
                case -318952296:
                    if (action.equals(TarazedIntents.SessionAndroidService.FORCE_SHOW_END_SESSION_NOTIFICATION)) {
                        TarazedController tarazedController8 = this.controller;
                        if (tarazedController8 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("controller");
                        }
                        tarazedController8.forceShowEndSessionNotification$TarazedAndroidLibrary_release();
                        return 1;
                    }
                    break;
                case 189958527:
                    if (action.equals(TarazedIntents.SessionAndroidService.PAUSE_SESSION)) {
                        TarazedController tarazedController9 = this.controller;
                        if (tarazedController9 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("controller");
                        }
                        tarazedController9.pauseSession$TarazedAndroidLibrary_release();
                        return 1;
                    }
                    break;
                case 816747786:
                    if (action.equals(ACTION_SESSION_STATE_REQUEST)) {
                        handleStateRequest(intent);
                        return 1;
                    }
                    break;
                case 902473266:
                    if (action.equals(TarazedIntents.SessionAndroidService.RESUME_SESSION)) {
                        TarazedController tarazedController10 = this.controller;
                        if (tarazedController10 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("controller");
                        }
                        tarazedController10.resumeSession$TarazedAndroidLibrary_release();
                        return 1;
                    }
                    break;
                case 1413144943:
                    if (action.equals(TarazedIntents.SessionAndroidService.END_SESSION_ON_PROFILE_SWITCH)) {
                        TarazedController tarazedController11 = this.controller;
                        if (tarazedController11 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("controller");
                        }
                        tarazedController11.endSessionOnProfileSwitch$TarazedAndroidLibrary_release();
                        return 1;
                    }
                    break;
                case 1566152923:
                    if (action.equals(TarazedIntents.SessionAndroidService.END_SESSION_ON_SHUTDOWN)) {
                        TarazedController tarazedController12 = this.controller;
                        if (tarazedController12 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("controller");
                        }
                        tarazedController12.endSessionOnShutdown$TarazedAndroidLibrary_release();
                        return 1;
                    }
                    break;
                case 1755716674:
                    if (action.equals(TarazedIntents.SessionAndroidService.PAUSE_SESSION_3P_APP_STOP)) {
                        TarazedController tarazedController13 = this.controller;
                        if (tarazedController13 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("controller");
                        }
                        tarazedController13.pauseSessionOn3pAppStop$TarazedAndroidLibrary_release();
                        return 1;
                    }
                    break;
                case 1791823653:
                    if (action.equals(TarazedIntents.SessionAndroidService.SUSPEND_SESSION_DEVICE_SHUTDOWN)) {
                        TarazedController tarazedController14 = this.controller;
                        if (tarazedController14 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("controller");
                        }
                        tarazedController14.suspendSessionDeviceShutdown$TarazedAndroidLibrary_release();
                        return 1;
                    }
                    break;
                case 1906040060:
                    if (action.equals(TarazedIntents.SessionAndroidService.DISPLAY_DRAWER)) {
                        displaySideDrawer();
                        return 1;
                    }
                    break;
                case 2034818195:
                    if (action.equals(ACTION_DISPLAY_SESSION_TOAST)) {
                        handleDisplayToast(intent);
                        return 1;
                    }
                    break;
            }
        }
        handleUnexpectedRequest(intent);
        return 1;
    }

    public final void setActivityTracker$TarazedAndroidLibrary_release(@NotNull ActivityTracker activityTracker) {
        Intrinsics.checkParameterIsNotNull(activityTracker, "<set-?>");
        this.activityTracker = activityTracker;
    }

    public final void setController$TarazedAndroidLibrary_release(@NotNull TarazedController tarazedController) {
        Intrinsics.checkParameterIsNotNull(tarazedController, "<set-?>");
        this.controller = tarazedController;
    }

    public final void setLogger(@NotNull TarazedSessionLogger tarazedSessionLogger) {
        Intrinsics.checkParameterIsNotNull(tarazedSessionLogger, "<set-?>");
        this.logger = tarazedSessionLogger;
    }

    public final void setResourceManager$TarazedAndroidLibrary_release(@NotNull TarazedResourceManager tarazedResourceManager) {
        Intrinsics.checkParameterIsNotNull(tarazedResourceManager, "<set-?>");
        this.resourceManager = tarazedResourceManager;
    }
}
