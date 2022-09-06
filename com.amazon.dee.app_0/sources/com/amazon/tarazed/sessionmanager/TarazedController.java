package com.amazon.tarazed.sessionmanager;

import android.content.Context;
import android.os.PowerManager;
import androidx.annotation.VisibleForTesting;
import com.amazon.tarazed.core.coroutine.dispatcher.DispatcherProvider;
import com.amazon.tarazed.core.logging.TarazedSessionLogger;
import com.amazon.tarazed.core.metrics.TarazedMetricsHelper;
import com.amazon.tarazed.core.notification.type.TarazedLaunchRequest;
import com.amazon.tarazed.core.notifier.TarazedNotificationEvent;
import com.amazon.tarazed.core.notifier.TarazedSessionNotifier;
import com.amazon.tarazed.core.registry.TarazedComponentRegistry;
import com.amazon.tarazed.core.registry.component.AccountMetadataProvider;
import com.amazon.tarazed.core.session.TarazedSession;
import com.amazon.tarazed.core.session.TarazedSessionStateChangeRequest;
import com.amazon.tarazed.core.session.TarazedSessionStateChangeSource;
import com.amazon.tarazed.core.session.TarazedSessionStateChangeType;
import com.amazon.tarazed.core.sessionclient.sessioncache.Post23SessionClientCache;
import com.amazon.tarazed.core.type.Account;
import com.amazon.tarazed.core.utility.DeviceInfoUtility;
import com.amazon.tarazed.dagger.scopes.LibraryScope;
import com.amazon.tarazed.ui.manager.TarazedUIManager;
import com.amazon.tarazed.utility.BrowserPresenceDetectorToResumeSuspendedSession;
import java.util.concurrent.ConcurrentSkipListSet;
import javax.inject.Inject;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.atomicfu.AtomicFU;
import kotlinx.atomicfu.AtomicLong;
import kotlinx.coroutines.BuildersKt__BuildersKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Job;
import kotlinx.serialization.json.Json;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: TarazedController.kt */
@LibraryScope
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b2\b\u0001\u0018\u0000 q2\u00020\u0001:\u0001qBG\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r\u0012\u0006\u0010\u000e\u001a\u00020\u000f\u0012\u0006\u0010\u0010\u001a\u00020\u0011¢\u0006\u0002\u0010\u0012J\b\u00109\u001a\u00020:H\u0002J\u0019\u0010;\u001a\u00020+2\u0006\u0010<\u001a\u00020\u0019H\u0082@ø\u0001\u0000¢\u0006\u0002\u0010=J\u0017\u0010>\u001a\u00020+2\b\u0010?\u001a\u0004\u0018\u00010@H\u0000¢\u0006\u0002\bAJ\r\u0010B\u001a\u00020+H\u0000¢\u0006\u0002\bCJ\r\u0010D\u001a\u00020+H\u0000¢\u0006\u0002\bEJ\r\u0010F\u001a\u00020+H\u0000¢\u0006\u0002\bGJ\r\u0010H\u001a\u00020+H\u0000¢\u0006\u0002\bIJ\r\u0010J\u001a\u00020+H\u0000¢\u0006\u0002\bKJ\r\u0010L\u001a\u00020+H\u0000¢\u0006\u0002\bMJ\r\u0010N\u001a\u00020+H\u0000¢\u0006\u0002\bOJ\u0015\u0010P\u001a\u00020+2\u0006\u0010Q\u001a\u00020@H\u0000¢\u0006\u0002\bRJ\b\u0010S\u001a\u00020+H\u0002J\u0011\u0010T\u001a\u00020+H\u0082@ø\u0001\u0000¢\u0006\u0002\u0010UJ\r\u0010V\u001a\u00020\u001bH\u0000¢\u0006\u0002\bWJ\u0018\u0010X\u001a\u00020+2\u0006\u0010<\u001a\u00020\u00192\u0006\u0010Y\u001a\u00020\u001bH\u0002J\r\u0010Z\u001a\u00020+H\u0000¢\u0006\u0002\b[J\r\u0010\\\u001a\u00020+H\u0000¢\u0006\u0002\b]J\r\u0010^\u001a\u00020+H\u0000¢\u0006\u0002\b_J\r\u0010`\u001a\u00020+H\u0000¢\u0006\u0002\baJ\r\u0010b\u001a\u00020+H\u0000¢\u0006\u0002\bcJ\b\u0010d\u001a\u00020+H\u0002J\r\u0010e\u001a\u00020+H\u0000¢\u0006\u0002\bfJ\b\u0010g\u001a\u00020:H\u0002J\r\u0010h\u001a\u00020+H\u0000¢\u0006\u0002\biJ\r\u0010j\u001a\u00020+H\u0000¢\u0006\u0002\bkJ\r\u0010l\u001a\u00020+H\u0000¢\u0006\u0002\bmJ\u0011\u0010n\u001a\u00020+H\u0082@ø\u0001\u0000¢\u0006\u0002\u0010UJ\r\u0010o\u001a\u00020+H\u0001¢\u0006\u0002\bpR\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0013\u001a\u00020\u00148BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0015\u0010\u0016R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00190\u0018X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u001bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\u001c\u001a\u00020\u001d8\u0000X\u0081\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u001e\u0010\u001f\u001a\u0004\b \u0010!R\u0014\u0010\"\u001a\b\u0012\u0004\u0012\u00020\u00190\u0018X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010#\u001a\u0004\u0018\u00010$8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b%\u0010&R/\u0010'\u001a\u001e\b\u0001\u0012\u0004\u0012\u00020)\u0012\n\u0012\b\u0012\u0004\u0012\u00020+0*\u0012\u0006\u0012\u0004\u0018\u00010\u00010(X\u0082\u0004ø\u0001\u0000¢\u0006\u0004\n\u0002\u0010,R\u0014\u0010-\u001a\u00020.8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b/\u00100R\u0014\u00101\u001a\u0002028BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b3\u00104R\u0016\u00105\u001a\u0004\u0018\u0001068BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b7\u00108\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006r"}, d2 = {"Lcom/amazon/tarazed/sessionmanager/TarazedController;", "", "resourceManager", "Lcom/amazon/tarazed/sessionmanager/TarazedResourceManager;", "metricsHelper", "Lcom/amazon/tarazed/core/metrics/TarazedMetricsHelper;", "logger", "Lcom/amazon/tarazed/core/logging/TarazedSessionLogger;", "deviceInfo", "Lcom/amazon/tarazed/core/utility/DeviceInfoUtility;", "browserPresenceDetector", "Lcom/amazon/tarazed/utility/BrowserPresenceDetectorToResumeSuspendedSession;", "coroutineScope", "Lkotlinx/coroutines/CoroutineScope;", "dispatchers", "Lcom/amazon/tarazed/core/coroutine/dispatcher/DispatcherProvider;", "headsUpNotificationManager", "Lcom/amazon/tarazed/sessionmanager/HeadsUpNotificationManager;", "(Lcom/amazon/tarazed/sessionmanager/TarazedResourceManager;Lcom/amazon/tarazed/core/metrics/TarazedMetricsHelper;Lcom/amazon/tarazed/core/logging/TarazedSessionLogger;Lcom/amazon/tarazed/core/utility/DeviceInfoUtility;Lcom/amazon/tarazed/utility/BrowserPresenceDetectorToResumeSuspendedSession;Lkotlinx/coroutines/CoroutineScope;Lcom/amazon/tarazed/core/coroutine/dispatcher/DispatcherProvider;Lcom/amazon/tarazed/sessionmanager/HeadsUpNotificationManager;)V", "context", "Landroid/content/Context;", "getContext", "()Landroid/content/Context;", "encounteredLaunchRequestSet", "Ljava/util/concurrent/ConcurrentSkipListSet;", "Lcom/amazon/tarazed/core/notification/type/TarazedLaunchRequest;", "hasSessionStarted", "", "lastLaunchTimestamp", "Lkotlinx/atomicfu/AtomicLong;", "lastLaunchTimestamp$annotations", "()V", "getLastLaunchTimestamp$TarazedAndroidLibrary_release", "()Lkotlinx/atomicfu/AtomicLong;", "launchRequestQueue", "session", "Lcom/amazon/tarazed/core/session/TarazedSession;", "getSession", "()Lcom/amazon/tarazed/core/session/TarazedSession;", "sessionNotificationHandler", "Lkotlin/Function2;", "Lcom/amazon/tarazed/core/notifier/TarazedNotificationEvent;", "Lkotlin/coroutines/Continuation;", "", "Lkotlin/jvm/functions/Function2;", "sessionNotificationManager", "Lcom/amazon/tarazed/sessionmanager/SessionNotificationManager;", "getSessionNotificationManager", "()Lcom/amazon/tarazed/sessionmanager/SessionNotificationManager;", "sessionNotifier", "Lcom/amazon/tarazed/core/notifier/TarazedSessionNotifier;", "getSessionNotifier", "()Lcom/amazon/tarazed/core/notifier/TarazedSessionNotifier;", "uiManager", "Lcom/amazon/tarazed/ui/manager/TarazedUIManager;", "getUiManager", "()Lcom/amazon/tarazed/ui/manager/TarazedUIManager;", "clearCachedLaunchRequest", "Lkotlinx/coroutines/Job;", "createSession", Post23SessionClientCache.LAUNCH_REQUEST_CACHE, "(Lcom/amazon/tarazed/core/notification/type/TarazedLaunchRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "displayToast", "message", "", "displayToast$TarazedAndroidLibrary_release", "endSession", "endSession$TarazedAndroidLibrary_release", "endSessionImmediate", "endSessionImmediate$TarazedAndroidLibrary_release", "endSessionOn3pAppEvent", "endSessionOn3pAppEvent$TarazedAndroidLibrary_release", "endSessionOn3pAppPauseTimeout", "endSessionOn3pAppPauseTimeout$TarazedAndroidLibrary_release", "endSessionOnProfileSwitch", "endSessionOnProfileSwitch$TarazedAndroidLibrary_release", "endSessionOnShutdown", "endSessionOnShutdown$TarazedAndroidLibrary_release", "forceShowEndSessionNotification", "forceShowEndSessionNotification$TarazedAndroidLibrary_release", "handleLaunchRequest", "launchRequestString", "handleLaunchRequest$TarazedAndroidLibrary_release", "handleMediaConnected", "handleSessionEnd", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "isSessionActive", "isSessionActive$TarazedAndroidLibrary_release", "launchSession", "pingBrowser", "pauseSession", "pauseSession$TarazedAndroidLibrary_release", "pauseSessionOn3pAppStop", "pauseSessionOn3pAppStop$TarazedAndroidLibrary_release", "resumeSession", "resumeSession$TarazedAndroidLibrary_release", "resumeSuspendedSession", "resumeSuspendedSession$TarazedAndroidLibrary_release", "showNavigationDrawer", "showNavigationDrawer$TarazedAndroidLibrary_release", "startBackgroundSession", "startSession", "startSession$TarazedAndroidLibrary_release", "subscribeToNotifier", "suspendSession", "suspendSession$TarazedAndroidLibrary_release", "suspendSessionDeviceShutdown", "suspendSessionDeviceShutdown$TarazedAndroidLibrary_release", "suspendSessionOnProfileSwitch", "suspendSessionOnProfileSwitch$TarazedAndroidLibrary_release", "tearDownSession", "wakeDevice", "wakeDevice$TarazedAndroidLibrary_release", "Companion", "TarazedAndroidLibrary_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class TarazedController {
    private static final long BROWSER_PING_TIMEOUT_MS = 5000;
    @Deprecated
    public static final Companion Companion = new Companion(null);
    private static final String METRIC_3P_REQUESTED_FORCE_SHOW_END_SESSION = "3PRequestedForceShowEndSession";
    private static final String METRIC_DATED_LAUNCH_REQUEST = "DatedLaunchRequest";
    private static final String METRIC_DUPLICATE_LAUNCH_REQUEST = "DuplicateLaunchRequest";
    private static final String METRIC_REPLACED_DATED_SESSION = "ReplacedDatedSession";
    private static final String METRIC_SESSION_CACHE_NOT_CONNECTED = "SessionCacheNotConnected";
    private static final String METRIC_SESSION_REQUEST_RECEIPT_DELAY = "SessionRequestReceiptDelay";
    private static final String TAG = "TarazedSessionControl";
    private static final long WAKE_LOCK_TIMEOUT = 1000;
    private final BrowserPresenceDetectorToResumeSuspendedSession browserPresenceDetector;
    private final CoroutineScope coroutineScope;
    private final DeviceInfoUtility deviceInfo;
    private final DispatcherProvider dispatchers;
    private final ConcurrentSkipListSet<TarazedLaunchRequest> encounteredLaunchRequestSet;
    private boolean hasSessionStarted;
    private final HeadsUpNotificationManager headsUpNotificationManager;
    @NotNull
    private final AtomicLong lastLaunchTimestamp;
    private final ConcurrentSkipListSet<TarazedLaunchRequest> launchRequestQueue;
    private final TarazedSessionLogger logger;
    private final TarazedMetricsHelper metricsHelper;
    private final TarazedResourceManager resourceManager;
    private final Function2<TarazedNotificationEvent, Continuation<? super Unit>, Object> sessionNotificationHandler;

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: TarazedController.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\b\b\u0082\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Lcom/amazon/tarazed/sessionmanager/TarazedController$Companion;", "", "()V", "BROWSER_PING_TIMEOUT_MS", "", "METRIC_3P_REQUESTED_FORCE_SHOW_END_SESSION", "", "METRIC_DATED_LAUNCH_REQUEST", "METRIC_DUPLICATE_LAUNCH_REQUEST", "METRIC_REPLACED_DATED_SESSION", "METRIC_SESSION_CACHE_NOT_CONNECTED", "METRIC_SESSION_REQUEST_RECEIPT_DELAY", "TAG", "WAKE_LOCK_TIMEOUT", "TarazedAndroidLibrary_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes13.dex */
    public static final class Companion {
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

        static {
            $EnumSwitchMapping$0[TarazedNotificationEvent.MEDIA_CONNECTED.ordinal()] = 1;
            $EnumSwitchMapping$0[TarazedNotificationEvent.SESSION_SUSPENDED.ordinal()] = 2;
            $EnumSwitchMapping$0[TarazedNotificationEvent.SESSION_END.ordinal()] = 3;
            $EnumSwitchMapping$0[TarazedNotificationEvent.APP_FOREGROUND.ordinal()] = 4;
            $EnumSwitchMapping$0[TarazedNotificationEvent.SESSION_STARTING_3P_BACKGROUND.ordinal()] = 5;
        }
    }

    @Inject
    public TarazedController(@NotNull TarazedResourceManager resourceManager, @NotNull TarazedMetricsHelper metricsHelper, @NotNull TarazedSessionLogger logger, @NotNull DeviceInfoUtility deviceInfo, @NotNull BrowserPresenceDetectorToResumeSuspendedSession browserPresenceDetector, @NotNull CoroutineScope coroutineScope, @NotNull DispatcherProvider dispatchers, @NotNull HeadsUpNotificationManager headsUpNotificationManager) {
        Intrinsics.checkParameterIsNotNull(resourceManager, "resourceManager");
        Intrinsics.checkParameterIsNotNull(metricsHelper, "metricsHelper");
        Intrinsics.checkParameterIsNotNull(logger, "logger");
        Intrinsics.checkParameterIsNotNull(deviceInfo, "deviceInfo");
        Intrinsics.checkParameterIsNotNull(browserPresenceDetector, "browserPresenceDetector");
        Intrinsics.checkParameterIsNotNull(coroutineScope, "coroutineScope");
        Intrinsics.checkParameterIsNotNull(dispatchers, "dispatchers");
        Intrinsics.checkParameterIsNotNull(headsUpNotificationManager, "headsUpNotificationManager");
        this.resourceManager = resourceManager;
        this.metricsHelper = metricsHelper;
        this.logger = logger;
        this.deviceInfo = deviceInfo;
        this.browserPresenceDetector = browserPresenceDetector;
        this.coroutineScope = coroutineScope;
        this.dispatchers = dispatchers;
        this.headsUpNotificationManager = headsUpNotificationManager;
        this.lastLaunchTimestamp = AtomicFU.atomic(0L);
        this.launchRequestQueue = new ConcurrentSkipListSet<>(TarazedController$launchRequestQueue$1.INSTANCE);
        this.encounteredLaunchRequestSet = new ConcurrentSkipListSet<>(TarazedController$encounteredLaunchRequestSet$1.INSTANCE);
        this.sessionNotificationHandler = new TarazedController$sessionNotificationHandler$1(this, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Job clearCachedLaunchRequest() {
        Object runBlocking$default;
        runBlocking$default = BuildersKt__BuildersKt.runBlocking$default(null, new TarazedController$clearCachedLaunchRequest$1(this, null), 1, null);
        return (Job) runBlocking$default;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Context getContext() {
        return this.resourceManager.getContext$TarazedAndroidLibrary_release();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final TarazedSession getSession() {
        return this.resourceManager.getSession$TarazedAndroidLibrary_release();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final SessionNotificationManager getSessionNotificationManager() {
        return this.resourceManager.getSessionNotificationManager$TarazedAndroidLibrary_release();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final TarazedSessionNotifier getSessionNotifier() {
        return this.resourceManager.getSessionNotifier$TarazedAndroidLibrary_release();
    }

    private final TarazedUIManager getUiManager() {
        return this.resourceManager.getUiManager$TarazedAndroidLibrary_release();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void handleMediaConnected() {
        this.hasSessionStarted = true;
    }

    @VisibleForTesting
    public static /* synthetic */ void lastLaunchTimestamp$annotations() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void launchSession(TarazedLaunchRequest tarazedLaunchRequest, boolean z) {
        BuildersKt__Builders_commonKt.launch$default(this.coroutineScope, this.dispatchers.mo4557default(), null, new TarazedController$launchSession$1(this, z, tarazedLaunchRequest, null), 2, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void startBackgroundSession() {
        this.logger.i(TAG, "Starting session in background");
        TarazedSession session = getSession();
        if (session != null) {
            session.sendStateChangeEventRequest(new TarazedSessionStateChangeRequest(TarazedSessionStateChangeType.CONNECT_IOT, TarazedSessionStateChangeSource.SOURCE_3P_APP_EVENT));
        }
    }

    private final Job subscribeToNotifier() {
        Job launch$default;
        launch$default = BuildersKt__Builders_commonKt.launch$default(this.coroutineScope, this.dispatchers.mo4557default(), null, new TarazedController$subscribeToNotifier$1(this, null), 2, null);
        return launch$default;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Nullable
    public final /* synthetic */ Object createSession(@NotNull TarazedLaunchRequest tarazedLaunchRequest, @NotNull Continuation<? super Unit> continuation) {
        Object coroutine_suspended;
        subscribeToNotifier();
        Object createSession$TarazedAndroidLibrary_release = this.resourceManager.createSession$TarazedAndroidLibrary_release(tarazedLaunchRequest, continuation);
        coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        return createSession$TarazedAndroidLibrary_release == coroutine_suspended ? createSession$TarazedAndroidLibrary_release : Unit.INSTANCE;
    }

    public final void displayToast$TarazedAndroidLibrary_release(@Nullable String str) {
        if (str == null) {
            this.logger.w(TAG, "Ignoring null toast message");
            return;
        }
        try {
            TarazedUIManager uiManager = getUiManager();
            if (uiManager == null) {
                return;
            }
            uiManager.displayToast(str);
        } catch (UnsupportedOperationException e) {
            this.logger.e(TAG, "Displaying toasts unsupported", e);
        }
    }

    public final void endSession$TarazedAndroidLibrary_release() {
        this.logger.i(TAG, "Begin Ending Session");
        TarazedSession session = getSession();
        if (session != null) {
            session.sendStateChangeEventRequest(new TarazedSessionStateChangeRequest(TarazedSessionStateChangeType.CONFIRM_END_SESSION, TarazedSessionStateChangeSource.SOURCE_CUSTOMER));
        }
    }

    public final void endSessionImmediate$TarazedAndroidLibrary_release() {
        this.logger.i(TAG, "End session - immediate");
        TarazedSession session = getSession();
        if (session != null) {
            session.sendStateChangeEventRequest(new TarazedSessionStateChangeRequest(TarazedSessionStateChangeType.BEGIN_ENDING_SESSION, TarazedSessionStateChangeSource.SOURCE_OTHER));
        }
    }

    public final void endSessionOn3pAppEvent$TarazedAndroidLibrary_release() {
        this.logger.i(TAG, "End session - 3p app event");
        TarazedSession session = getSession();
        if (session != null) {
            session.sendStateChangeEventRequest(new TarazedSessionStateChangeRequest(TarazedSessionStateChangeType.BEGIN_ENDING_SESSION, TarazedSessionStateChangeSource.SOURCE_3P_APP_EVENT));
        }
    }

    public final void endSessionOn3pAppPauseTimeout$TarazedAndroidLibrary_release() {
        this.logger.i(TAG, "End session - 3p app in background timeout");
        TarazedSession session = getSession();
        if (session != null) {
            session.sendStateChangeEventRequest(new TarazedSessionStateChangeRequest(TarazedSessionStateChangeType.BEGIN_ENDING_SESSION, TarazedSessionStateChangeSource.SOURCE_INACTIVE));
        }
    }

    public final void endSessionOnProfileSwitch$TarazedAndroidLibrary_release() {
        this.logger.i(TAG, "End session - profile switch");
        TarazedSession session = getSession();
        if (session != null) {
            session.sendStateChangeEventRequest(new TarazedSessionStateChangeRequest(TarazedSessionStateChangeType.BEGIN_ENDING_SESSION, TarazedSessionStateChangeSource.SOURCE_PROFILE_SWITCH));
        }
        getSessionNotifier().notifyProfileSwitch();
    }

    public final void endSessionOnShutdown$TarazedAndroidLibrary_release() {
        this.logger.i(TAG, "End session - device shutdown");
        TarazedSession session = getSession();
        if (session != null) {
            session.sendStateChangeEventRequest(new TarazedSessionStateChangeRequest(TarazedSessionStateChangeType.BEGIN_ENDING_SESSION, TarazedSessionStateChangeSource.SOURCE_DEVICE_SHUTDOWN));
        }
    }

    public final void forceShowEndSessionNotification$TarazedAndroidLibrary_release() {
        if (!this.deviceInfo.is1PDevice()) {
            this.logger.w(TAG, "Request to force-show end session notification made by 3P device, ignoring.");
            this.metricsHelper.addCount(TAG, METRIC_3P_REQUESTED_FORCE_SHOW_END_SESSION, 1.0d);
            return;
        }
        getSessionNotificationManager().forceNotifySessionEnded$TarazedAndroidLibrary_release();
        this.resourceManager.forceShowSessionEndedDialog$TarazedAndroidLibrary_release();
        this.logger.i(TAG, "Force showed end session notification");
    }

    @NotNull
    public final AtomicLong getLastLaunchTimestamp$TarazedAndroidLibrary_release() {
        return this.lastLaunchTimestamp;
    }

    public final void handleLaunchRequest$TarazedAndroidLibrary_release(@NotNull String launchRequestString) {
        long value;
        Account accountMetadata;
        Intrinsics.checkParameterIsNotNull(launchRequestString, "launchRequestString");
        TarazedSessionLogger tarazedSessionLogger = this.logger;
        tarazedSessionLogger.i(TAG, "Launch request: " + launchRequestString);
        TarazedLaunchRequest tarazedLaunchRequest = (TarazedLaunchRequest) Json.Default.parse(TarazedLaunchRequest.Companion.serializer(), launchRequestString);
        this.metricsHelper.addMetricTimer(TAG, METRIC_SESSION_REQUEST_RECEIPT_DELAY, ((double) System.currentTimeMillis()) - ((double) tarazedLaunchRequest.getNotificationTime()));
        this.metricsHelper.startMetricTimer(TAG, TarazedSession.METRIC_LAUNCH_PERMISSION_DELAY);
        if (!this.deviceInfo.is1PDevice()) {
            AccountMetadataProvider accountMetadataProvider = (AccountMetadataProvider) TarazedComponentRegistry.INSTANCE.getComponent(AccountMetadataProvider.class);
            String preferredMarketplace = (accountMetadataProvider == null || (accountMetadata = accountMetadataProvider.getAccountMetadata()) == null) ? null : accountMetadata.getPreferredMarketplace();
            TarazedMetricsHelper tarazedMetricsHelper = this.metricsHelper;
            tarazedMetricsHelper.startMetricTimer("LaunchPermissionDelayByPfm", "LaunchPermissionDelay:" + preferredMarketplace);
        }
        AtomicLong atomicLong = this.lastLaunchTimestamp;
        do {
            value = atomicLong.getValue();
        } while (!atomicLong.compareAndSet(value, Math.max(value, tarazedLaunchRequest.getNotificationTime())));
        BuildersKt__Builders_commonKt.launch$default(this.coroutineScope, this.dispatchers.mo4557default(), null, new TarazedController$handleLaunchRequest$3(this, tarazedLaunchRequest, null), 2, null);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:10:0x0023  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0035  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x0055  */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final /* synthetic */ java.lang.Object handleSessionEnd(@org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super kotlin.Unit> r5) {
        /*
            r4 = this;
            boolean r0 = r5 instanceof com.amazon.tarazed.sessionmanager.TarazedController$handleSessionEnd$1
            if (r0 == 0) goto L13
            r0 = r5
            com.amazon.tarazed.sessionmanager.TarazedController$handleSessionEnd$1 r0 = (com.amazon.tarazed.sessionmanager.TarazedController$handleSessionEnd$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.amazon.tarazed.sessionmanager.TarazedController$handleSessionEnd$1 r0 = new com.amazon.tarazed.sessionmanager.TarazedController$handleSessionEnd$1
            r0.<init>(r4, r5)
        L18:
            java.lang.Object r5 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L35
            if (r2 != r3) goto L2d
            java.lang.Object r0 = r0.L$0
            com.amazon.tarazed.sessionmanager.TarazedController r0 = (com.amazon.tarazed.sessionmanager.TarazedController) r0
            kotlin.ResultKt.throwOnFailure(r5)
            goto L4a
        L2d:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r0)
            throw r5
        L35:
            kotlin.ResultKt.throwOnFailure(r5)
            com.amazon.tarazed.sessionmanager.TarazedResourceManager r5 = r4.resourceManager
            r2 = 0
            r5.setLaunchRequestString$TarazedAndroidLibrary_release(r2)
            r0.L$0 = r4
            r0.label = r3
            java.lang.Object r5 = r4.tearDownSession(r0)
            if (r5 != r1) goto L49
            return r1
        L49:
            r0 = r4
        L4a:
            java.util.concurrent.ConcurrentSkipListSet<com.amazon.tarazed.core.notification.type.TarazedLaunchRequest> r5 = r0.launchRequestQueue
            r1 = 0
            java.lang.Object r5 = kotlin.collections.CollectionsKt.elementAtOrNull(r5, r1)
            com.amazon.tarazed.core.notification.type.TarazedLaunchRequest r5 = (com.amazon.tarazed.core.notification.type.TarazedLaunchRequest) r5
            if (r5 == 0) goto L5d
            r0.launchSession(r5, r1)
            java.util.concurrent.ConcurrentSkipListSet<com.amazon.tarazed.core.notification.type.TarazedLaunchRequest> r5 = r0.launchRequestQueue
            r5.clear()
        L5d:
            kotlin.Unit r5 = kotlin.Unit.INSTANCE
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.tarazed.sessionmanager.TarazedController.handleSessionEnd(kotlin.coroutines.Continuation):java.lang.Object");
    }

    public final boolean isSessionActive$TarazedAndroidLibrary_release() {
        return this.resourceManager.isSessionActive$TarazedAndroidLibrary_release();
    }

    public final void pauseSession$TarazedAndroidLibrary_release() {
        this.logger.i(TAG, "Pausing Session");
        TarazedSession session = getSession();
        if (session != null) {
            session.sendStateChangeEventRequest(new TarazedSessionStateChangeRequest(TarazedSessionStateChangeType.PAUSE_SESSION, TarazedSessionStateChangeSource.SOURCE_CUSTOMER));
        }
    }

    public final void pauseSessionOn3pAppStop$TarazedAndroidLibrary_release() {
        this.logger.i(TAG, "Pausing Session");
        TarazedSession session = getSession();
        if (session != null) {
            session.sendStateChangeEventRequest(new TarazedSessionStateChangeRequest(TarazedSessionStateChangeType.PAUSE_SESSION, TarazedSessionStateChangeSource.SOURCE_3P_APP_EVENT));
        }
    }

    public final void resumeSession$TarazedAndroidLibrary_release() {
        this.logger.i(TAG, "Resuming Session");
        TarazedSession session = getSession();
        if (session != null) {
            session.sendStateChangeEventRequest(new TarazedSessionStateChangeRequest(TarazedSessionStateChangeType.RESUME_SESSION, TarazedSessionStateChangeSource.SOURCE_CUSTOMER));
        }
    }

    public final void resumeSuspendedSession$TarazedAndroidLibrary_release() {
        this.logger.i(TAG, "Resuming session if previously suspended");
        BuildersKt__Builders_commonKt.launch$default(this.coroutineScope, this.dispatchers.mo4557default(), null, new TarazedController$resumeSuspendedSession$1(this, null), 2, null);
    }

    public final void showNavigationDrawer$TarazedAndroidLibrary_release() {
        TarazedUIManager uiManager = getUiManager();
        if (uiManager != null) {
            uiManager.showSessionControls();
        }
    }

    public final void startSession$TarazedAndroidLibrary_release() {
        this.logger.i(TAG, "Starting Session");
        TarazedSession session = getSession();
        if (session != null) {
            session.sendStateChangeEventRequest(new TarazedSessionStateChangeRequest(TarazedSessionStateChangeType.REQUEST_START_SESSION, TarazedSessionStateChangeSource.SOURCE_CUSTOMER));
        }
    }

    public final void suspendSession$TarazedAndroidLibrary_release() {
        this.logger.i(TAG, "Suspending Session");
        TarazedSession session = getSession();
        if (session != null) {
            session.sendStateChangeEventRequest(new TarazedSessionStateChangeRequest(TarazedSessionStateChangeType.BEGIN_SUSPENDING_SESSION, TarazedSessionStateChangeSource.SOURCE_CUSTOMER));
        }
    }

    public final void suspendSessionDeviceShutdown$TarazedAndroidLibrary_release() {
        this.logger.i(TAG, "Suspending Session due to device shutdown");
        this.resourceManager.cacheLaunchRequest$TarazedAndroidLibrary_release();
        TarazedSession session = getSession();
        if (session != null) {
            session.sendStateChangeEventRequest(new TarazedSessionStateChangeRequest(TarazedSessionStateChangeType.BEGIN_SUSPENDING_SESSION, TarazedSessionStateChangeSource.SOURCE_DEVICE_SHUTDOWN));
        }
    }

    public final void suspendSessionOnProfileSwitch$TarazedAndroidLibrary_release() {
        this.logger.i(TAG, "Suspending Session immediately due to profile switch");
        TarazedSession session = getSession();
        if (session != null) {
            session.sendStateChangeEventRequest(new TarazedSessionStateChangeRequest(TarazedSessionStateChangeType.BEGIN_SUSPENDING_SESSION, TarazedSessionStateChangeSource.SOURCE_PROFILE_SWITCH));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:10:0x0023  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0035  */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final /* synthetic */ java.lang.Object tearDownSession(@org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super kotlin.Unit> r5) {
        /*
            r4 = this;
            boolean r0 = r5 instanceof com.amazon.tarazed.sessionmanager.TarazedController$tearDownSession$1
            if (r0 == 0) goto L13
            r0 = r5
            com.amazon.tarazed.sessionmanager.TarazedController$tearDownSession$1 r0 = (com.amazon.tarazed.sessionmanager.TarazedController$tearDownSession$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.amazon.tarazed.sessionmanager.TarazedController$tearDownSession$1 r0 = new com.amazon.tarazed.sessionmanager.TarazedController$tearDownSession$1
            r0.<init>(r4, r5)
        L18:
            java.lang.Object r5 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L35
            if (r2 != r3) goto L2d
            java.lang.Object r0 = r0.L$0
            com.amazon.tarazed.sessionmanager.TarazedController r0 = (com.amazon.tarazed.sessionmanager.TarazedController) r0
            kotlin.ResultKt.throwOnFailure(r5)
            goto L46
        L2d:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r0)
            throw r5
        L35:
            kotlin.ResultKt.throwOnFailure(r5)
            com.amazon.tarazed.sessionmanager.TarazedResourceManager r5 = r4.resourceManager
            r0.L$0 = r4
            r0.label = r3
            java.lang.Object r5 = r5.tearDownSession$TarazedAndroidLibrary_release(r0)
            if (r5 != r1) goto L45
            return r1
        L45:
            r0 = r4
        L46:
            r5 = 0
            r0.hasSessionStarted = r5
            kotlin.Unit r5 = kotlin.Unit.INSTANCE
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.tarazed.sessionmanager.TarazedController.tearDownSession(kotlin.coroutines.Continuation):java.lang.Object");
    }

    @VisibleForTesting
    public final void wakeDevice$TarazedAndroidLibrary_release() {
        this.logger.i(TAG, "Waking device");
        Object systemService = getContext().getSystemService("power");
        if (systemService != null) {
            PowerManager.WakeLock newWakeLock = ((PowerManager) systemService).newWakeLock(268435466, "TarazedSessionControl:SessionWakeLock");
            newWakeLock.acquire(1000L);
            newWakeLock.release();
            return;
        }
        throw new TypeCastException("null cannot be cast to non-null type android.os.PowerManager");
    }
}
