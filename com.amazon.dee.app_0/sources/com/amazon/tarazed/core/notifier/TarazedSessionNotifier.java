package com.amazon.tarazed.core.notifier;

import androidx.annotation.VisibleForTesting;
import com.amazon.tarazed.core.metrics.TarazedMetricsHelper;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.atomicfu.AtomicBoolean;
import kotlinx.atomicfu.AtomicFU;
import kotlinx.atomicfu.AtomicInt;
import kotlinx.coroutines.BuildersKt__BuildersKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.GlobalScope;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.channels.Channel;
import kotlinx.coroutines.channels.ChannelKt;
import kotlinx.coroutines.sync.Mutex;
import kotlinx.coroutines.sync.MutexKt;
import org.jetbrains.annotations.NotNull;
/* compiled from: TarazedSessionNotifier.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000b\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010#\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0019\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u0000 ?2\u00020\u0001:\u0001?B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020\rH\u0002J\u0006\u0010!\u001a\u00020\u0015J\u0006\u0010\"\u001a\u00020\u0015J\u0010\u0010#\u001a\u00020\u00152\u0006\u0010$\u001a\u00020\rH\u0002J\u0006\u0010%\u001a\u00020\u0015J\u0006\u0010&\u001a\u00020\u0015J\u0006\u0010'\u001a\u00020\u0015J\u0006\u0010(\u001a\u00020\u0015J\u0006\u0010)\u001a\u00020\u0015J\u0006\u0010*\u001a\u00020\u0015J\u0006\u0010+\u001a\u00020\u0015J\u0006\u0010,\u001a\u00020\u0015J\u0006\u0010-\u001a\u00020\u0015J\u0006\u0010.\u001a\u00020\u0015J\u0006\u0010/\u001a\u00020\u0015J\u0006\u00100\u001a\u00020\u0015J\u0006\u00101\u001a\u00020\u0015J\u0006\u00102\u001a\u00020\u0015J\u0006\u00103\u001a\u00020\u0015J\u0006\u00104\u001a\u00020\u0015J\u0006\u00105\u001a\u00020\u0015J\u0006\u00106\u001a\u00020\u0015J\u0006\u00107\u001a\u00020\u0015J\b\u00108\u001a\u000209H\u0002JD\u0010:\u001a\u0002092\"\u0010;\u001a\u001e\b\u0001\u0012\u0004\u0012\u00020\r\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00150\u0014\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u00132\u0006\u0010<\u001a\u00020\u00162\b\b\u0002\u0010=\u001a\u00020\u001fø\u0001\u0000¢\u0006\u0002\u0010>R\u001c\u0010\u0005\u001a\u00020\u00068\u0000X\u0081\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0007\u0010\b\u001a\u0004\b\t\u0010\nR\u0014\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R?\u0010\u0010\u001a0\u0012,\u0012*\u0012 \u0012\u001e\b\u0001\u0012\u0004\u0012\u00020\r\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00150\u0014\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0013\u0012\u0004\u0012\u00020\u00160\u00120\u0011X\u0082\u0004ø\u0001\u0000¢\u0006\u0002\n\u0000RM\u0010\u0017\u001a0\u0012,\u0012*\u0012 \u0012\u001e\b\u0001\u0012\u0004\u0012\u00020\r\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00150\u0014\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0013\u0012\u0004\u0012\u00020\u00160\u00120\u00188\u0000X\u0081\u0004ø\u0001\u0000¢\u0006\u000e\n\u0000\u0012\u0004\b\u0019\u0010\b\u001a\u0004\b\u001a\u0010\u001bR\u000e\u0010\u001c\u001a\u00020\u001dX\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006@"}, d2 = {"Lcom/amazon/tarazed/core/notifier/TarazedSessionNotifier;", "", "metricsHelper", "Lcom/amazon/tarazed/core/metrics/TarazedMetricsHelper;", "(Lcom/amazon/tarazed/core/metrics/TarazedMetricsHelper;)V", "bufferSize", "Lkotlinx/atomicfu/AtomicInt;", "bufferSize$annotations", "()V", "getBufferSize$TarazedMobileCore_release", "()Lkotlinx/atomicfu/AtomicInt;", "eventChannel", "Lkotlinx/coroutines/channels/Channel;", "Lcom/amazon/tarazed/core/notifier/TarazedNotificationEvent;", "isListening", "Lkotlinx/atomicfu/AtomicBoolean;", "permanentSubscribers", "", "Lkotlin/Pair;", "Lkotlin/Function2;", "Lkotlin/coroutines/Continuation;", "", "Lcom/amazon/tarazed/core/notifier/ListenerPriority;", "subscribers", "", "subscribers$annotations", "getSubscribers$TarazedMobileCore_release", "()Ljava/util/List;", "subscribersMutex", "Lkotlinx/coroutines/sync/Mutex;", "isSessionEnding", "", "e", "notify3pSessionStartingInBackground", "notifyAppForeground", "notifyEvent", "eventID", "notifyIotDisconnected", "notifyMediaConnected", "notifyMediaConnecting", "notifyMediaDisconnected", "notifyMediaFailed", "notifyMediaPermissionAccepted", "notifyMediaStopped", "notifyProfileSwitch", "notifyResumeRequested", "notifySessionActive", "notifySessionEnded", "notifySessionEndingBy3pAppEvent", "notifySessionEndingByInactivity", "notifySessionPausedBy3pAppEvent", "notifySessionPausedByAgent", "notifySessionPausedByCustomer", "notifySessionResumedByAgent", "notifySessionResumedByCustomer", "notifySessionSuspended", "startListeningForEvents", "Lkotlinx/coroutines/Job;", "subscribe", "onReceive", "priority", "unsubscribeOnSessionEnd", "(Lkotlin/jvm/functions/Function2;Lcom/amazon/tarazed/core/notifier/ListenerPriority;Z)Lkotlinx/coroutines/Job;", "Companion", "TarazedMobileCore_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class TarazedSessionNotifier {
    private static final int BUFFER_EXPECTED_MAX_SIZE = 3;
    public static final Companion Companion = new Companion(null);
    @NotNull
    public static final String METRIC_BUFFER_EXCEEDED_EXPECTED_SIZE = "BufferExceedsExpectedSize";
    @NotNull
    public static final String TAG = "TarazedSessionNotifier";
    @NotNull
    private final AtomicInt bufferSize;
    private final Channel<TarazedNotificationEvent> eventChannel;
    private AtomicBoolean isListening;
    private final TarazedMetricsHelper metricsHelper;
    private final Set<Pair<Function2<TarazedNotificationEvent, Continuation<? super Unit>, Object>, ListenerPriority>> permanentSubscribers;
    @NotNull
    private final List<Pair<Function2<TarazedNotificationEvent, Continuation<? super Unit>, Object>, ListenerPriority>> subscribers;
    private final Mutex subscribersMutex;

    /* compiled from: TarazedSessionNotifier.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\b\u0080\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u0016\u0010\u0005\u001a\u00020\u00068\u0000X\u0081T¢\u0006\b\n\u0000\u0012\u0004\b\u0007\u0010\u0002R\u0016\u0010\b\u001a\u00020\u00068\u0000X\u0081T¢\u0006\b\n\u0000\u0012\u0004\b\t\u0010\u0002¨\u0006\n"}, d2 = {"Lcom/amazon/tarazed/core/notifier/TarazedSessionNotifier$Companion;", "", "()V", "BUFFER_EXPECTED_MAX_SIZE", "", "METRIC_BUFFER_EXCEEDED_EXPECTED_SIZE", "", "METRIC_BUFFER_EXCEEDED_EXPECTED_SIZE$annotations", "TAG", "TAG$annotations", "TarazedMobileCore_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes13.dex */
    public static final class Companion {
        private Companion() {
        }

        @VisibleForTesting
        public static /* synthetic */ void METRIC_BUFFER_EXCEEDED_EXPECTED_SIZE$annotations() {
        }

        @VisibleForTesting
        public static /* synthetic */ void TAG$annotations() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public TarazedSessionNotifier(@NotNull TarazedMetricsHelper metricsHelper) {
        Intrinsics.checkParameterIsNotNull(metricsHelper, "metricsHelper");
        this.metricsHelper = metricsHelper;
        this.subscribers = new ArrayList();
        this.eventChannel = ChannelKt.Channel(Integer.MAX_VALUE);
        this.bufferSize = AtomicFU.atomic(0);
        this.subscribersMutex = MutexKt.Mutex$default(false, 1, null);
        this.isListening = AtomicFU.atomic(false);
        this.permanentSubscribers = new LinkedHashSet();
    }

    @VisibleForTesting
    public static /* synthetic */ void bufferSize$annotations() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean isSessionEnding(TarazedNotificationEvent tarazedNotificationEvent) {
        return tarazedNotificationEvent == TarazedNotificationEvent.SESSION_END || tarazedNotificationEvent == TarazedNotificationEvent.SESSION_SUSPENDED;
    }

    private final void notifyEvent(TarazedNotificationEvent tarazedNotificationEvent) {
        BuildersKt__BuildersKt.runBlocking$default(null, new TarazedSessionNotifier$notifyEvent$1(this, tarazedNotificationEvent, null), 1, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Job startListeningForEvents() {
        Job launch$default;
        launch$default = BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new TarazedSessionNotifier$startListeningForEvents$1(this, null), 3, null);
        return launch$default;
    }

    public static /* synthetic */ Job subscribe$default(TarazedSessionNotifier tarazedSessionNotifier, Function2 function2, ListenerPriority listenerPriority, boolean z, int i, Object obj) {
        if ((i & 4) != 0) {
            z = true;
        }
        return tarazedSessionNotifier.subscribe(function2, listenerPriority, z);
    }

    @VisibleForTesting
    public static /* synthetic */ void subscribers$annotations() {
    }

    @NotNull
    public final AtomicInt getBufferSize$TarazedMobileCore_release() {
        return this.bufferSize;
    }

    @NotNull
    public final List<Pair<Function2<TarazedNotificationEvent, Continuation<? super Unit>, Object>, ListenerPriority>> getSubscribers$TarazedMobileCore_release() {
        return this.subscribers;
    }

    public final void notify3pSessionStartingInBackground() {
        notifyEvent(TarazedNotificationEvent.SESSION_STARTING_3P_BACKGROUND);
    }

    public final void notifyAppForeground() {
        notifyEvent(TarazedNotificationEvent.APP_FOREGROUND);
    }

    public final void notifyIotDisconnected() {
        notifyEvent(TarazedNotificationEvent.IOT_DISCONNECTED);
    }

    public final void notifyMediaConnected() {
        notifyEvent(TarazedNotificationEvent.MEDIA_CONNECTED);
    }

    public final void notifyMediaConnecting() {
        notifyEvent(TarazedNotificationEvent.MEDIA_CONNECTING);
    }

    public final void notifyMediaDisconnected() {
        notifyEvent(TarazedNotificationEvent.MEDIA_DISCONNECTED);
    }

    public final void notifyMediaFailed() {
        notifyEvent(TarazedNotificationEvent.MEDIA_FAILED);
    }

    public final void notifyMediaPermissionAccepted() {
        notifyEvent(TarazedNotificationEvent.MEDIA_PERMISSION_ACCEPTED);
    }

    public final void notifyMediaStopped() {
        notifyEvent(TarazedNotificationEvent.MEDIA_STOPPED);
    }

    public final void notifyProfileSwitch() {
        notifyEvent(TarazedNotificationEvent.PROFILE_SWITCH);
    }

    public final void notifyResumeRequested() {
        notifyEvent(TarazedNotificationEvent.RESUME_REQUESTED);
    }

    public final void notifySessionActive() {
        notifyEvent(TarazedNotificationEvent.SESSION_ACTIVE);
    }

    public final void notifySessionEnded() {
        notifyEvent(TarazedNotificationEvent.SESSION_END);
    }

    public final void notifySessionEndingBy3pAppEvent() {
        notifyEvent(TarazedNotificationEvent.SESSION_ENDING_3P_APP_EVENT);
    }

    public final void notifySessionEndingByInactivity() {
        notifyEvent(TarazedNotificationEvent.SESSION_ENDING_INACTIVITY);
    }

    public final void notifySessionPausedBy3pAppEvent() {
        notifyEvent(TarazedNotificationEvent.SESSION_PAUSE_3P_APP_EVENT);
    }

    public final void notifySessionPausedByAgent() {
        notifyEvent(TarazedNotificationEvent.SESSION_PAUSE_AGENT);
    }

    public final void notifySessionPausedByCustomer() {
        notifyEvent(TarazedNotificationEvent.SESSION_PAUSE_CUSTOMER);
    }

    public final void notifySessionResumedByAgent() {
        notifyEvent(TarazedNotificationEvent.SESSION_RESUME_AGENT);
    }

    public final void notifySessionResumedByCustomer() {
        notifyEvent(TarazedNotificationEvent.SESSION_RESUME_CUSTOMER);
    }

    public final void notifySessionSuspended() {
        notifyEvent(TarazedNotificationEvent.SESSION_SUSPENDED);
    }

    @NotNull
    public final Job subscribe(@NotNull Function2<? super TarazedNotificationEvent, ? super Continuation<? super Unit>, ? extends Object> onReceive, @NotNull ListenerPriority priority, boolean z) {
        Job launch$default;
        Intrinsics.checkParameterIsNotNull(onReceive, "onReceive");
        Intrinsics.checkParameterIsNotNull(priority, "priority");
        launch$default = BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new TarazedSessionNotifier$subscribe$1(this, onReceive, priority, z, null), 3, null);
        return launch$default;
    }
}
