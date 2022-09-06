package com.amazon.tarazed.ui.menu.databinding;

import android.content.Context;
import androidx.databinding.ObservableField;
import com.amazon.tarazed.R;
import com.amazon.tarazed.core.metrics.BizMetricsHelper;
import com.amazon.tarazed.core.notifier.ListenerPriority;
import com.amazon.tarazed.core.notifier.TarazedNotificationEvent;
import com.amazon.tarazed.core.notifier.TarazedSessionNotifier;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: BarNotificationTextManager.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0000\u0018\u0000 \u00152\u00020\u0001:\u0001\u0015B1\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\u0015\u0010\u0011\u001a\u00020\u000f2\u0006\u0010\u0012\u001a\u00020\u0013H\u0000¢\u0006\u0002\b\u0014R\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R/\u0010\u000b\u001a\u001e\b\u0001\u0012\u0004\u0012\u00020\r\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000f0\u000e\u0012\u0006\u0012\u0004\u0018\u00010\u00010\fX\u0082\u0004ø\u0001\u0000¢\u0006\u0004\n\u0002\u0010\u0010R\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0016"}, d2 = {"Lcom/amazon/tarazed/ui/menu/databinding/BarNotificationTextManager;", "", "context", "Landroid/content/Context;", "playbackStateObservable", "Landroidx/databinding/ObservableField;", "", "barNotificationTextObservable", "bizMetricsHelper", "Lcom/amazon/tarazed/core/metrics/BizMetricsHelper;", "(Landroid/content/Context;Landroidx/databinding/ObservableField;Landroidx/databinding/ObservableField;Lcom/amazon/tarazed/core/metrics/BizMetricsHelper;)V", "notificationHandler", "Lkotlin/Function2;", "Lcom/amazon/tarazed/core/notifier/TarazedNotificationEvent;", "Lkotlin/coroutines/Continuation;", "", "Lkotlin/jvm/functions/Function2;", "subscribeToNotifier", "notifier", "Lcom/amazon/tarazed/core/notifier/TarazedSessionNotifier;", "subscribeToNotifier$TarazedAndroidLibrary_release", "Companion", "TarazedAndroidLibrary_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class BarNotificationTextManager {
    private final ObservableField<String> barNotificationTextObservable;
    private final BizMetricsHelper bizMetricsHelper;
    private final Context context;
    private final Function2<TarazedNotificationEvent, Continuation<? super Unit>, Object> notificationHandler;
    private final ObservableField<String> playbackStateObservable;
    public static final Companion Companion = new Companion(null);
    private static final int AGENT_PAUSED = R.string.message_agent_paused;
    private static final int AUTO_PAUSED = R.string.screen_sharing_paused;

    /* compiled from: BarNotificationTextManager.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\b\u0080\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0014\u0010\u0003\u001a\u00020\u0004X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0014\u0010\u0007\u001a\u00020\u0004X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0006¨\u0006\t"}, d2 = {"Lcom/amazon/tarazed/ui/menu/databinding/BarNotificationTextManager$Companion;", "", "()V", "AGENT_PAUSED", "", "getAGENT_PAUSED$TarazedAndroidLibrary_release", "()I", "AUTO_PAUSED", "getAUTO_PAUSED$TarazedAndroidLibrary_release", "TarazedAndroidLibrary_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes13.dex */
    public static final class Companion {
        private Companion() {
        }

        public final int getAGENT_PAUSED$TarazedAndroidLibrary_release() {
            return BarNotificationTextManager.AGENT_PAUSED;
        }

        public final int getAUTO_PAUSED$TarazedAndroidLibrary_release() {
            return BarNotificationTextManager.AUTO_PAUSED;
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
            $EnumSwitchMapping$0[TarazedNotificationEvent.SESSION_PAUSE_AGENT.ordinal()] = 1;
            $EnumSwitchMapping$0[TarazedNotificationEvent.SESSION_PAUSE_3P_APP_EVENT.ordinal()] = 2;
        }
    }

    public BarNotificationTextManager(@NotNull Context context, @NotNull ObservableField<String> playbackStateObservable, @NotNull ObservableField<String> barNotificationTextObservable, @NotNull BizMetricsHelper bizMetricsHelper) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(playbackStateObservable, "playbackStateObservable");
        Intrinsics.checkParameterIsNotNull(barNotificationTextObservable, "barNotificationTextObservable");
        Intrinsics.checkParameterIsNotNull(bizMetricsHelper, "bizMetricsHelper");
        this.context = context;
        this.playbackStateObservable = playbackStateObservable;
        this.barNotificationTextObservable = barNotificationTextObservable;
        this.bizMetricsHelper = bizMetricsHelper;
        this.notificationHandler = new BarNotificationTextManager$notificationHandler$1(this, null);
    }

    public final void subscribeToNotifier$TarazedAndroidLibrary_release(@NotNull TarazedSessionNotifier notifier) {
        Intrinsics.checkParameterIsNotNull(notifier, "notifier");
        TarazedSessionNotifier.subscribe$default(notifier, this.notificationHandler, ListenerPriority.HIGH, false, 4, null);
    }
}
