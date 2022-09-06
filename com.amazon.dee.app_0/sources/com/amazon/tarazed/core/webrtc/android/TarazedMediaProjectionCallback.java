package com.amazon.tarazed.core.webrtc.android;

import android.media.projection.MediaProjection;
import com.amazon.alexa.accessorykit.ApplicationLifecycleObserverEventEmitter;
import com.amazon.tarazed.core.logging.TarazedSessionLogger;
import com.amazon.tarazed.core.notifier.TarazedSessionNotifier;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: TarazedMediaProjectionCallback.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\b\u0000\u0018\u0000 \t2\u00020\u0001:\u0001\tB\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\b\u0010\u0007\u001a\u00020\bH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Lcom/amazon/tarazed/core/webrtc/android/TarazedMediaProjectionCallback;", "Landroid/media/projection/MediaProjection$Callback;", "logger", "Lcom/amazon/tarazed/core/logging/TarazedSessionLogger;", "sessionNotifier", "Lcom/amazon/tarazed/core/notifier/TarazedSessionNotifier;", "(Lcom/amazon/tarazed/core/logging/TarazedSessionLogger;Lcom/amazon/tarazed/core/notifier/TarazedSessionNotifier;)V", ApplicationLifecycleObserverEventEmitter.LIFECYCLE_EVENT_STOP, "", "Companion", "TarazedMobileCore_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class TarazedMediaProjectionCallback extends MediaProjection.Callback {
    @Deprecated
    public static final Companion Companion = new Companion(null);
    private static final String TAG = "TarazedMediaProjCB";
    private final TarazedSessionLogger logger;
    private final TarazedSessionNotifier sessionNotifier;

    /* compiled from: TarazedMediaProjectionCallback.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0082\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"Lcom/amazon/tarazed/core/webrtc/android/TarazedMediaProjectionCallback$Companion;", "", "()V", "TAG", "", "TarazedMobileCore_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes13.dex */
    private static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public TarazedMediaProjectionCallback(@NotNull TarazedSessionLogger logger, @NotNull TarazedSessionNotifier sessionNotifier) {
        Intrinsics.checkParameterIsNotNull(logger, "logger");
        Intrinsics.checkParameterIsNotNull(sessionNotifier, "sessionNotifier");
        this.logger = logger;
        this.sessionNotifier = sessionNotifier;
    }

    @Override // android.media.projection.MediaProjection.Callback
    public void onStop() {
        this.logger.i(TAG, "MediaProjection has stopped");
        this.sessionNotifier.notifyMediaStopped();
    }
}
