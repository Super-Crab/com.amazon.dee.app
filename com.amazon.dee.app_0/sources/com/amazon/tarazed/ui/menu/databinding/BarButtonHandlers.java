package com.amazon.tarazed.ui.menu.databinding;

import android.content.Context;
import android.content.Intent;
import androidx.databinding.ObservableBoolean;
import androidx.databinding.ObservableField;
import com.amazon.tarazed.core.TarazedIntents;
import com.amazon.tarazed.core.metrics.BizMetricsHelper;
import com.amazon.tarazed.core.session.sessionEvents.PlaybackStates;
import com.amazon.tarazed.sessionmanager.TarazedSessionAndroidService;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: BarButtonHandlers.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0006\b\u0000\u0018\u00002\u00020\u0001B+\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n¢\u0006\u0002\u0010\u000bJ\u0006\u0010\f\u001a\u00020\rJ\u0006\u0010\u000e\u001a\u00020\rJ\u0006\u0010\u000f\u001a\u00020\rJ\u0006\u0010\u0010\u001a\u00020\rJ\u0010\u0010\u0011\u001a\u00020\r2\u0006\u0010\u0012\u001a\u00020\u0006H\u0002R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Lcom/amazon/tarazed/ui/menu/databinding/BarButtonHandlers;", "", "context", "Landroid/content/Context;", "playbackStateObservable", "Landroidx/databinding/ObservableField;", "", "overflowMenuVisible", "Landroidx/databinding/ObservableBoolean;", "bizMetricsHelper", "Lcom/amazon/tarazed/core/metrics/BizMetricsHelper;", "(Landroid/content/Context;Landroidx/databinding/ObservableField;Landroidx/databinding/ObservableBoolean;Lcom/amazon/tarazed/core/metrics/BizMetricsHelper;)V", "onClickCloseMenu", "", "onClickEnd", "onClickOpenMenu", "onClickPauseResume", "sendSessionStateChange", "action", "TarazedAndroidLibrary_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class BarButtonHandlers {
    private final BizMetricsHelper bizMetricsHelper;
    private final Context context;
    private final ObservableBoolean overflowMenuVisible;
    private final ObservableField<String> playbackStateObservable;

    public BarButtonHandlers(@NotNull Context context, @NotNull ObservableField<String> playbackStateObservable, @NotNull ObservableBoolean overflowMenuVisible, @NotNull BizMetricsHelper bizMetricsHelper) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(playbackStateObservable, "playbackStateObservable");
        Intrinsics.checkParameterIsNotNull(overflowMenuVisible, "overflowMenuVisible");
        Intrinsics.checkParameterIsNotNull(bizMetricsHelper, "bizMetricsHelper");
        this.context = context;
        this.playbackStateObservable = playbackStateObservable;
        this.overflowMenuVisible = overflowMenuVisible;
        this.bizMetricsHelper = bizMetricsHelper;
    }

    private final void sendSessionStateChange(String str) {
        Intent intent = new Intent(this.context, TarazedSessionAndroidService.class);
        intent.setAction(str);
        this.context.startService(intent);
    }

    public final void onClickCloseMenu() {
        this.overflowMenuVisible.set(false);
    }

    public final void onClickEnd() {
        sendSessionStateChange(TarazedIntents.SessionAndroidService.END_SESSION);
    }

    public final void onClickOpenMenu() {
        this.overflowMenuVisible.set(true);
    }

    public final void onClickPauseResume() {
        String str = this.playbackStateObservable.get();
        if (Intrinsics.areEqual(str, PlaybackStates.PLAYING)) {
            sendSessionStateChange(TarazedIntents.SessionAndroidService.PAUSE_SESSION);
        } else if (!Intrinsics.areEqual(str, "paused")) {
        } else {
            sendSessionStateChange(TarazedIntents.SessionAndroidService.RESUME_SESSION);
        }
    }
}
