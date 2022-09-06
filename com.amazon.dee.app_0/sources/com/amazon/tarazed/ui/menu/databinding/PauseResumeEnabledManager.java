package com.amazon.tarazed.ui.menu.databinding;

import androidx.databinding.ObservableBoolean;
import com.amazon.tarazed.core.notifier.ListenerPriority;
import com.amazon.tarazed.core.notifier.TarazedNotificationEvent;
import com.amazon.tarazed.core.notifier.TarazedSessionNotifier;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: PauseResumeEnabledManager.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0015\u0010\u000b\u001a\u00020\t2\u0006\u0010\f\u001a\u00020\rH\u0000¢\u0006\u0002\b\u000eR/\u0010\u0005\u001a\u001e\b\u0001\u0012\u0004\u0012\u00020\u0007\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\b\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0006X\u0082\u0004ø\u0001\u0000¢\u0006\u0004\n\u0002\u0010\nR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u000f"}, d2 = {"Lcom/amazon/tarazed/ui/menu/databinding/PauseResumeEnabledManager;", "", "pauseResumeEnabledObservable", "Landroidx/databinding/ObservableBoolean;", "(Landroidx/databinding/ObservableBoolean;)V", "notificationHandler", "Lkotlin/Function2;", "Lcom/amazon/tarazed/core/notifier/TarazedNotificationEvent;", "Lkotlin/coroutines/Continuation;", "", "Lkotlin/jvm/functions/Function2;", "subscribeToNotifier", "notifier", "Lcom/amazon/tarazed/core/notifier/TarazedSessionNotifier;", "subscribeToNotifier$TarazedAndroidLibrary_release", "TarazedAndroidLibrary_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class PauseResumeEnabledManager {
    private final Function2<TarazedNotificationEvent, Continuation<? super Unit>, Object> notificationHandler;
    private final ObservableBoolean pauseResumeEnabledObservable;

    @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 1, 16})
    /* loaded from: classes13.dex */
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = new int[TarazedNotificationEvent.values().length];

        static {
            $EnumSwitchMapping$0[TarazedNotificationEvent.SESSION_PAUSE_AGENT.ordinal()] = 1;
            $EnumSwitchMapping$0[TarazedNotificationEvent.SESSION_END.ordinal()] = 2;
            $EnumSwitchMapping$0[TarazedNotificationEvent.SESSION_ENDING_3P_APP_EVENT.ordinal()] = 3;
            $EnumSwitchMapping$0[TarazedNotificationEvent.SESSION_SUSPENDED.ordinal()] = 4;
            $EnumSwitchMapping$0[TarazedNotificationEvent.SESSION_RESUME_AGENT.ordinal()] = 5;
            $EnumSwitchMapping$0[TarazedNotificationEvent.SESSION_ENDING_INACTIVITY.ordinal()] = 6;
        }
    }

    public PauseResumeEnabledManager(@NotNull ObservableBoolean pauseResumeEnabledObservable) {
        Intrinsics.checkParameterIsNotNull(pauseResumeEnabledObservable, "pauseResumeEnabledObservable");
        this.pauseResumeEnabledObservable = pauseResumeEnabledObservable;
        this.notificationHandler = new PauseResumeEnabledManager$notificationHandler$1(this, null);
    }

    public final void subscribeToNotifier$TarazedAndroidLibrary_release(@NotNull TarazedSessionNotifier notifier) {
        Intrinsics.checkParameterIsNotNull(notifier, "notifier");
        TarazedSessionNotifier.subscribe$default(notifier, this.notificationHandler, ListenerPriority.HIGH, false, 4, null);
    }
}
