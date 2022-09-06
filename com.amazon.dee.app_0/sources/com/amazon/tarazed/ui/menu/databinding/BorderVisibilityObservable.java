package com.amazon.tarazed.ui.menu.databinding;

import androidx.databinding.Observable;
import androidx.databinding.ObservableBoolean;
import androidx.databinding.ObservableField;
import com.amazon.tarazed.core.session.sessionEvents.PlaybackStates;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: BorderVisibilityObservable.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001\bB\u001b\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00040\u0001\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00040\u0001X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Lcom/amazon/tarazed/ui/menu/databinding/BorderVisibilityObservable;", "Landroidx/databinding/ObservableField;", "", "playbackStateObservable", "", "mediaConnectedObservable", "Landroidx/databinding/ObservableBoolean;", "(Landroidx/databinding/ObservableField;Landroidx/databinding/ObservableBoolean;)V", "Callback", "TarazedAndroidLibrary_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class BorderVisibilityObservable extends ObservableField<Boolean> {
    private final ObservableBoolean mediaConnectedObservable;
    private final ObservableField<String> playbackStateObservable;

    /* compiled from: BorderVisibilityObservable.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\b\u0086\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u001a\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0016¨\u0006\t"}, d2 = {"Lcom/amazon/tarazed/ui/menu/databinding/BorderVisibilityObservable$Callback;", "Landroidx/databinding/Observable$OnPropertyChangedCallback;", "(Lcom/amazon/tarazed/ui/menu/databinding/BorderVisibilityObservable;)V", "onPropertyChanged", "", "p0", "Landroidx/databinding/Observable;", "p1", "", "TarazedAndroidLibrary_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes13.dex */
    public final class Callback extends Observable.OnPropertyChangedCallback {
        public Callback() {
        }

        @Override // androidx.databinding.Observable.OnPropertyChangedCallback
        public void onPropertyChanged(@Nullable Observable observable, int i) {
            String str = (String) BorderVisibilityObservable.this.playbackStateObservable.get();
            if (str == null) {
                return;
            }
            switch (str.hashCode()) {
                case -1661628965:
                    if (!str.equals(PlaybackStates.SUSPENDED)) {
                        return;
                    }
                    break;
                case -1298752217:
                    if (!str.equals(PlaybackStates.ENDING)) {
                        return;
                    }
                    break;
                case -995321554:
                    if (!str.equals("paused")) {
                        return;
                    }
                    break;
                case -493563858:
                    if (!str.equals(PlaybackStates.PLAYING)) {
                        return;
                    }
                    if (!BorderVisibilityObservable.this.mediaConnectedObservable.get()) {
                        BorderVisibilityObservable.this.set(false);
                        return;
                    } else {
                        BorderVisibilityObservable.this.set(true);
                        return;
                    }
                case 24665195:
                    if (!str.equals("inactive")) {
                        return;
                    }
                    break;
                case 96651962:
                    if (!str.equals(PlaybackStates.ENDED)) {
                        return;
                    }
                    break;
                default:
                    return;
            }
            BorderVisibilityObservable.this.set(false);
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public BorderVisibilityObservable(@NotNull ObservableField<String> playbackStateObservable, @NotNull ObservableBoolean mediaConnectedObservable) {
        super(playbackStateObservable, mediaConnectedObservable);
        Intrinsics.checkParameterIsNotNull(playbackStateObservable, "playbackStateObservable");
        Intrinsics.checkParameterIsNotNull(mediaConnectedObservable, "mediaConnectedObservable");
        this.playbackStateObservable = playbackStateObservable;
        this.mediaConnectedObservable = mediaConnectedObservable;
        addOnPropertyChangedCallback(new Callback());
    }
}
