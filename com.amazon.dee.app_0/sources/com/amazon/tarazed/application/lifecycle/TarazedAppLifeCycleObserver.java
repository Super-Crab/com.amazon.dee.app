package com.amazon.tarazed.application.lifecycle;

import android.content.Context;
import android.content.Intent;
import androidx.annotation.VisibleForTesting;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;
import com.amazon.tarazed.arcus.ArcusConstants;
import com.amazon.tarazed.arcus.ArcusHelper;
import com.amazon.tarazed.core.TarazedIntents;
import com.amazon.tarazed.core.coroutine.dispatcher.DispatcherProvider;
import com.amazon.tarazed.core.logging.TarazedLogger;
import com.amazon.tarazed.core.metrics.BizMetricsConstants;
import com.amazon.tarazed.core.metrics.BizMetricsHelper;
import com.amazon.tarazed.core.notifier.ListenerPriority;
import com.amazon.tarazed.core.notifier.TarazedNotificationEvent;
import com.amazon.tarazed.core.notifier.TarazedSessionNotifier;
import com.amazon.tarazed.sessionmanager.TarazedSessionAndroidService;
import java.util.HashMap;
import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Job;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: TarazedAppLifeCycleObserver.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000p\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0010\u0000\n\u0002\b\u0006\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000b\n\u0002\b\t\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0000\u0018\u0000 /2\u00020\u0001:\u0001/B=\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r\u0012\u0006\u0010\u000e\u001a\u00020\u000f¢\u0006\u0002\u0010\u0010J\u0011\u0010&\u001a\u00020\u0019H\u0082@ø\u0001\u0000¢\u0006\u0002\u0010'J\b\u0010(\u001a\u00020\u0019H\u0007J\b\u0010)\u001a\u00020\u0019H\u0007J\b\u0010*\u001a\u00020\u0019H\u0007J\u0010\u0010+\u001a\u00020\u00192\u0006\u0010,\u001a\u00020-H\u0002J\u0011\u0010.\u001a\u00020\u0019H\u0082@ø\u0001\u0000¢\u0006\u0002\u0010'R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0013\u001a\u0004\u0018\u00010\u0014X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R=\u0010\u0015\u001a\u001e\b\u0001\u0012\u0004\u0012\u00020\u0017\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00190\u0018\u0012\u0006\u0012\u0004\u0018\u00010\u001a0\u00168\u0000X\u0081\u0004ø\u0001\u0000¢\u0006\u0010\n\u0002\u0010\u001f\u0012\u0004\b\u001b\u0010\u001c\u001a\u0004\b\u001d\u0010\u001eR\u000e\u0010\f\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010 \u001a\u00020!X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\"\u001a\u00020#X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010$\u001a\u00020!X\u0082D¢\u0006\u0002\n\u0000R\u0010\u0010%\u001a\u0004\u0018\u00010\u0014X\u0082\u000e¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u00060"}, d2 = {"Lcom/amazon/tarazed/application/lifecycle/TarazedAppLifeCycleObserver;", "Landroidx/lifecycle/LifecycleObserver;", "logger", "Lcom/amazon/tarazed/core/logging/TarazedLogger;", "context", "Landroid/content/Context;", "coroutineScope", "Lkotlinx/coroutines/CoroutineScope;", "dispatcher", "Lcom/amazon/tarazed/core/coroutine/dispatcher/DispatcherProvider;", "arcusHelper", "Lcom/amazon/tarazed/arcus/ArcusHelper;", "notifier", "Lcom/amazon/tarazed/core/notifier/TarazedSessionNotifier;", "bizMetricsHelper", "Lcom/amazon/tarazed/core/metrics/BizMetricsHelper;", "(Lcom/amazon/tarazed/core/logging/TarazedLogger;Landroid/content/Context;Lkotlinx/coroutines/CoroutineScope;Lcom/amazon/tarazed/core/coroutine/dispatcher/DispatcherProvider;Lcom/amazon/tarazed/arcus/ArcusHelper;Lcom/amazon/tarazed/core/notifier/TarazedSessionNotifier;Lcom/amazon/tarazed/core/metrics/BizMetricsHelper;)V", "endSessionTimeout", "", "endSessionTimer", "Lkotlinx/coroutines/Job;", "notificationHandler", "Lkotlin/Function2;", "Lcom/amazon/tarazed/core/notifier/TarazedNotificationEvent;", "Lkotlin/coroutines/Continuation;", "", "", "notificationHandler$annotations", "()V", "getNotificationHandler$TarazedAndroidLibrary_release", "()Lkotlin/jvm/functions/Function2;", "Lkotlin/jvm/functions/Function2;", "sessionPauseTimeStamp", "", "sessionPausedWhenBackgrounded", "", "startSessionNotificationDelay", "startSessionTimer", "endSession", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "onAppCreateEvent", "onAppToBackground", "onAppToForeground", "sendSessionStateChange", "action", "", "startSessionFromBackground", "Companion", "TarazedAndroidLibrary_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class TarazedAppLifeCycleObserver implements LifecycleObserver {
    @Deprecated
    public static final Companion Companion = new Companion(null);
    private static final String TAG = "TarazedLifeCycObserver";
    private final ArcusHelper arcusHelper;
    private final BizMetricsHelper bizMetricsHelper;
    private final Context context;
    private final CoroutineScope coroutineScope;
    private final DispatcherProvider dispatcher;
    private final int endSessionTimeout;
    private Job endSessionTimer;
    private final TarazedLogger logger;
    @NotNull
    private final Function2<TarazedNotificationEvent, Continuation<? super Unit>, Object> notificationHandler;
    private final TarazedSessionNotifier notifier;
    private long sessionPauseTimeStamp;
    private boolean sessionPausedWhenBackgrounded;
    private final long startSessionNotificationDelay;
    private Job startSessionTimer;

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: TarazedAppLifeCycleObserver.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0082\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"Lcom/amazon/tarazed/application/lifecycle/TarazedAppLifeCycleObserver$Companion;", "", "()V", "TAG", "", "TarazedAndroidLibrary_release"}, k = 1, mv = {1, 1, 16})
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
            $EnumSwitchMapping$0[TarazedNotificationEvent.SESSION_END.ordinal()] = 1;
        }
    }

    public TarazedAppLifeCycleObserver(@NotNull TarazedLogger logger, @NotNull Context context, @NotNull CoroutineScope coroutineScope, @NotNull DispatcherProvider dispatcher, @NotNull ArcusHelper arcusHelper, @NotNull TarazedSessionNotifier notifier, @NotNull BizMetricsHelper bizMetricsHelper) {
        Intrinsics.checkParameterIsNotNull(logger, "logger");
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(coroutineScope, "coroutineScope");
        Intrinsics.checkParameterIsNotNull(dispatcher, "dispatcher");
        Intrinsics.checkParameterIsNotNull(arcusHelper, "arcusHelper");
        Intrinsics.checkParameterIsNotNull(notifier, "notifier");
        Intrinsics.checkParameterIsNotNull(bizMetricsHelper, "bizMetricsHelper");
        this.logger = logger;
        this.context = context;
        this.coroutineScope = coroutineScope;
        this.dispatcher = dispatcher;
        this.arcusHelper = arcusHelper;
        this.notifier = notifier;
        this.bizMetricsHelper = bizMetricsHelper;
        Integer num = this.arcusHelper.getInt(ArcusConstants.KEY_END_SESSION_TIMEOUT_ON_3P_APP_BACKGROUND);
        this.endSessionTimeout = num != null ? num.intValue() : 180000;
        this.startSessionNotificationDelay = 1000L;
        this.notificationHandler = new TarazedAppLifeCycleObserver$notificationHandler$1(this, null);
        this.notifier.subscribe(this.notificationHandler, ListenerPriority.LOW, false);
    }

    @VisibleForTesting
    public static /* synthetic */ void notificationHandler$annotations() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void sendSessionStateChange(String str) {
        Intent intent = new Intent(this.context, TarazedSessionAndroidService.class);
        intent.setAction(str);
        this.context.startService(intent);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Nullable
    public final /* synthetic */ Object endSession(@NotNull Continuation<? super Unit> continuation) {
        Object coroutine_suspended;
        Object withContext = BuildersKt.withContext(this.dispatcher.mo4557default(), new TarazedAppLifeCycleObserver$endSession$2(this, null), continuation);
        coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        return withContext == coroutine_suspended ? withContext : Unit.INSTANCE;
    }

    @NotNull
    public final Function2<TarazedNotificationEvent, Continuation<? super Unit>, Object> getNotificationHandler$TarazedAndroidLibrary_release() {
        return this.notificationHandler;
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    public final void onAppCreateEvent() {
        Job launch$default;
        launch$default = BuildersKt__Builders_commonKt.launch$default(this.coroutineScope, null, null, new TarazedAppLifeCycleObserver$onAppCreateEvent$1(this, null), 3, null);
        this.startSessionTimer = launch$default;
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    public final void onAppToBackground() {
        Job launch$default;
        if (TarazedSessionAndroidService.Companion.isStreamPaused()) {
            this.logger.i(TAG, "App went to background, but session is already paused, ignoring");
            this.sessionPausedWhenBackgrounded = true;
            return;
        }
        this.sessionPausedWhenBackgrounded = false;
        this.logger.i(TAG, "Parent application is background now, attempting to pause MSS session");
        sendSessionStateChange(TarazedIntents.SessionAndroidService.PAUSE_SESSION_3P_APP_STOP);
        this.sessionPauseTimeStamp = System.currentTimeMillis();
        launch$default = BuildersKt__Builders_commonKt.launch$default(this.coroutineScope, null, null, new TarazedAppLifeCycleObserver$onAppToBackground$1(this, null), 3, null);
        this.endSessionTimer = launch$default;
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    public final void onAppToForeground() {
        Job job = this.startSessionTimer;
        if (job != null) {
            Job.DefaultImpls.cancel$default(job, (CancellationException) null, 1, (Object) null);
        }
        this.startSessionTimer = null;
        this.logger.i(TAG, "Parent application is foreground now");
        if (!this.sessionPausedWhenBackgrounded) {
            this.notifier.notifyAppForeground();
            Job job2 = this.endSessionTimer;
            if (job2 != null) {
                Job.DefaultImpls.cancel$default(job2, (CancellationException) null, 1, (Object) null);
            }
        }
        if (this.sessionPauseTimeStamp > 0) {
            HashMap hashMap = new HashMap();
            hashMap.put(BizMetricsConstants.DURATION_METADATA_NAME, String.valueOf((System.currentTimeMillis() - this.sessionPauseTimeStamp) / 1000));
            this.bizMetricsHelper.publishBizMetric(BizMetricsConstants.SESSION_PAUSED_APP_BACKGROUND_DURATION_EVENT_NAME, hashMap);
        }
        this.sessionPausedWhenBackgrounded = false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Nullable
    public final /* synthetic */ Object startSessionFromBackground(@NotNull Continuation<? super Unit> continuation) {
        Object coroutine_suspended;
        Object withContext = BuildersKt.withContext(this.dispatcher.mo4557default(), new TarazedAppLifeCycleObserver$startSessionFromBackground$2(this, null), continuation);
        coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        return withContext == coroutine_suspended ? withContext : Unit.INSTANCE;
    }
}
