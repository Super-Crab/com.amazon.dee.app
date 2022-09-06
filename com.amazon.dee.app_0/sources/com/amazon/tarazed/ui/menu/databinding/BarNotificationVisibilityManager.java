package com.amazon.tarazed.ui.menu.databinding;

import androidx.databinding.ObservableBoolean;
import androidx.databinding.ObservableField;
import com.amazon.tarazed.core.notifier.ListenerPriority;
import com.amazon.tarazed.core.notifier.TarazedNotificationEvent;
import com.amazon.tarazed.core.notifier.TarazedSessionNotifier;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: BarNotificationVisibilityManager.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0000\u0018\u00002\u00020\u0001B\u001b\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\u0015\u0010\u000e\u001a\u00020\f2\u0006\u0010\u000f\u001a\u00020\u0010H\u0000¢\u0006\u0002\b\u0011R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R/\u0010\b\u001a\u001e\b\u0001\u0012\u0004\u0012\u00020\n\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u000b\u0012\u0006\u0012\u0004\u0018\u00010\u00010\tX\u0082\u0004ø\u0001\u0000¢\u0006\u0004\n\u0002\u0010\rR\u0014\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0012"}, d2 = {"Lcom/amazon/tarazed/ui/menu/databinding/BarNotificationVisibilityManager;", "", "playbackStateObservable", "Landroidx/databinding/ObservableField;", "", "barNotificationVisibilityObservable", "Landroidx/databinding/ObservableBoolean;", "(Landroidx/databinding/ObservableField;Landroidx/databinding/ObservableBoolean;)V", "notificationHandler", "Lkotlin/Function2;", "Lcom/amazon/tarazed/core/notifier/TarazedNotificationEvent;", "Lkotlin/coroutines/Continuation;", "", "Lkotlin/jvm/functions/Function2;", "subscribeToNotifier", "notifier", "Lcom/amazon/tarazed/core/notifier/TarazedSessionNotifier;", "subscribeToNotifier$TarazedAndroidLibrary_release", "TarazedAndroidLibrary_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class BarNotificationVisibilityManager {
    private final ObservableBoolean barNotificationVisibilityObservable;
    private final Function2<TarazedNotificationEvent, Continuation<? super Unit>, Object> notificationHandler;
    private final ObservableField<String> playbackStateObservable;

    @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 1, 16})
    /* loaded from: classes13.dex */
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = new int[TarazedNotificationEvent.values().length];

        static {
            $EnumSwitchMapping$0[TarazedNotificationEvent.SESSION_PAUSE_AGENT.ordinal()] = 1;
            $EnumSwitchMapping$0[TarazedNotificationEvent.SESSION_PAUSE_3P_APP_EVENT.ordinal()] = 2;
            $EnumSwitchMapping$0[TarazedNotificationEvent.APP_FOREGROUND.ordinal()] = 3;
            $EnumSwitchMapping$0[TarazedNotificationEvent.SESSION_RESUME_AGENT.ordinal()] = 4;
            $EnumSwitchMapping$0[TarazedNotificationEvent.SESSION_RESUME_CUSTOMER.ordinal()] = 5;
        }
    }

    public BarNotificationVisibilityManager(@NotNull ObservableField<String> playbackStateObservable, @NotNull ObservableBoolean barNotificationVisibilityObservable) {
        Intrinsics.checkParameterIsNotNull(playbackStateObservable, "playbackStateObservable");
        Intrinsics.checkParameterIsNotNull(barNotificationVisibilityObservable, "barNotificationVisibilityObservable");
        this.playbackStateObservable = playbackStateObservable;
        this.barNotificationVisibilityObservable = barNotificationVisibilityObservable;
        this.notificationHandler = new BarNotificationVisibilityManager$notificationHandler$1(this, null);
    }

    public final void subscribeToNotifier$TarazedAndroidLibrary_release(@NotNull TarazedSessionNotifier notifier) {
        Intrinsics.checkParameterIsNotNull(notifier, "notifier");
        TarazedSessionNotifier.subscribe$default(notifier, this.notificationHandler, ListenerPriority.HIGH, false, 4, null);
    }
}
