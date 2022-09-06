package com.amazon.tarazed.ui.menu.databinding;

import android.content.Context;
import androidx.annotation.VisibleForTesting;
import androidx.databinding.Observable;
import androidx.databinding.ObservableBoolean;
import androidx.databinding.ObservableField;
import com.amazon.alexa.presence.bleconn.service.PresenceBleService;
import com.amazon.tarazed.R;
import com.amazon.tarazed.core.session.sessionEvents.PlaybackStates;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.collections.SetsKt__SetsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: StatusTextObservable.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0000\u0018\u0000 \n2\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0002\t\nB#\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"Lcom/amazon/tarazed/ui/menu/databinding/StatusTextObservable;", "Landroidx/databinding/ObservableField;", "", "context", "Landroid/content/Context;", "playbackStateObservable", "mediaConnectedObservable", "Landroidx/databinding/ObservableBoolean;", "(Landroid/content/Context;Landroidx/databinding/ObservableField;Landroidx/databinding/ObservableBoolean;)V", "Callback", "Companion", "TarazedAndroidLibrary_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class StatusTextObservable extends ObservableField<String> {
    private final Context context;
    private final ObservableBoolean mediaConnectedObservable;
    private final ObservableField<String> playbackStateObservable;
    public static final Companion Companion = new Companion(null);
    private static final int STARTING = R.string.status_connecting;
    private static final int ACTIVE = R.string.status_connected;
    private static final int PAUSED = R.string.status_paused;
    private static final int ENDING = R.string.status_ending;

    /* compiled from: StatusTextObservable.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\b\u0086\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0002J\b\u0010\u0005\u001a\u00020\u0004H\u0002J\b\u0010\u0006\u001a\u00020\u0004H\u0002J\u001a\u0010\u0007\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\n2\u0006\u0010\u000b\u001a\u00020\fH\u0016¨\u0006\r"}, d2 = {"Lcom/amazon/tarazed/ui/menu/databinding/StatusTextObservable$Callback;", "Landroidx/databinding/Observable$OnPropertyChangedCallback;", "(Lcom/amazon/tarazed/ui/menu/databinding/StatusTextObservable;)V", "isSessionPaused", "", "isSessionPlaying", "isSessionStopping", "onPropertyChanged", "", "p0", "Landroidx/databinding/Observable;", "p1", "", "TarazedAndroidLibrary_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes13.dex */
    public final class Callback extends Observable.OnPropertyChangedCallback {
        public Callback() {
        }

        private final boolean isSessionPaused() {
            return Intrinsics.areEqual((String) StatusTextObservable.this.playbackStateObservable.get(), "paused");
        }

        private final boolean isSessionPlaying() {
            return Intrinsics.areEqual((String) StatusTextObservable.this.playbackStateObservable.get(), PlaybackStates.PLAYING);
        }

        private final boolean isSessionStopping() {
            Set of;
            boolean contains;
            of = SetsKt__SetsKt.setOf((Object[]) new String[]{PlaybackStates.ENDING, PlaybackStates.ENDED, PlaybackStates.SUSPENDED});
            contains = CollectionsKt___CollectionsKt.contains(of, StatusTextObservable.this.playbackStateObservable.get());
            return contains;
        }

        @Override // androidx.databinding.Observable.OnPropertyChangedCallback
        public void onPropertyChanged(@Nullable Observable observable, int i) {
            if (!StatusTextObservable.this.mediaConnectedObservable.get()) {
                StatusTextObservable statusTextObservable = StatusTextObservable.this;
                statusTextObservable.set(statusTextObservable.context.getString(StatusTextObservable.Companion.getSTARTING$TarazedAndroidLibrary_release()));
            } else if (isSessionPlaying()) {
                StatusTextObservable statusTextObservable2 = StatusTextObservable.this;
                statusTextObservable2.set(statusTextObservable2.context.getString(StatusTextObservable.Companion.getACTIVE$TarazedAndroidLibrary_release()));
            } else if (isSessionPaused()) {
                StatusTextObservable statusTextObservable3 = StatusTextObservable.this;
                statusTextObservable3.set(statusTextObservable3.context.getString(StatusTextObservable.Companion.getPAUSED$TarazedAndroidLibrary_release()));
            } else if (!isSessionStopping()) {
            } else {
                StatusTextObservable statusTextObservable4 = StatusTextObservable.this;
                statusTextObservable4.set(statusTextObservable4.context.getString(StatusTextObservable.Companion.getENDING$TarazedAndroidLibrary_release()));
            }
        }
    }

    /* compiled from: StatusTextObservable.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\t\b\u0081\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0014\u0010\u0003\u001a\u00020\u0004X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0014\u0010\u0007\u001a\u00020\u0004X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0006R\u0014\u0010\t\u001a\u00020\u0004X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u0006R\u0014\u0010\u000b\u001a\u00020\u0004X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u0006¨\u0006\r"}, d2 = {"Lcom/amazon/tarazed/ui/menu/databinding/StatusTextObservable$Companion;", "", "()V", "ACTIVE", "", "getACTIVE$TarazedAndroidLibrary_release", "()I", "ENDING", "getENDING$TarazedAndroidLibrary_release", "PAUSED", "getPAUSED$TarazedAndroidLibrary_release", PresenceBleService.ServiceState.STARTING, "getSTARTING$TarazedAndroidLibrary_release", "TarazedAndroidLibrary_release"}, k = 1, mv = {1, 1, 16})
    @VisibleForTesting
    /* loaded from: classes13.dex */
    public static final class Companion {
        private Companion() {
        }

        public final int getACTIVE$TarazedAndroidLibrary_release() {
            return StatusTextObservable.ACTIVE;
        }

        public final int getENDING$TarazedAndroidLibrary_release() {
            return StatusTextObservable.ENDING;
        }

        public final int getPAUSED$TarazedAndroidLibrary_release() {
            return StatusTextObservable.PAUSED;
        }

        public final int getSTARTING$TarazedAndroidLibrary_release() {
            return StatusTextObservable.STARTING;
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public StatusTextObservable(@NotNull Context context, @NotNull ObservableField<String> playbackStateObservable, @NotNull ObservableBoolean mediaConnectedObservable) {
        super(playbackStateObservable, mediaConnectedObservable);
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(playbackStateObservable, "playbackStateObservable");
        Intrinsics.checkParameterIsNotNull(mediaConnectedObservable, "mediaConnectedObservable");
        this.context = context;
        this.playbackStateObservable = playbackStateObservable;
        this.mediaConnectedObservable = mediaConnectedObservable;
        addOnPropertyChangedCallback(new Callback());
        set(this.context.getString(STARTING));
    }
}
