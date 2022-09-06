package com.amazon.tarazed.core.utility;

import com.amazon.tarazed.core.logging.TarazedSessionLogger;
import com.amazon.tarazed.core.signaling.TarazedEventDispatcher;
import com.amazon.tarazed.core.signaling.TarazedIoTManager;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScopeKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: BrowserPresenceDetectorAtSessionStart.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\u0018\u0000 \u000e2\u00020\u0001:\u0001\u000eB\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u0019\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0096@ø\u0001\u0000¢\u0006\u0002\u0010\rR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u000f"}, d2 = {"Lcom/amazon/tarazed/core/utility/BrowserPresenceDetectorAtSessionStart;", "Lcom/amazon/tarazed/core/utility/BrowserPresenceDetector;", "logger", "Lcom/amazon/tarazed/core/logging/TarazedSessionLogger;", "eventDispatcher", "Lcom/amazon/tarazed/core/signaling/TarazedEventDispatcher;", "iotManager", "Lcom/amazon/tarazed/core/signaling/TarazedIoTManager;", "(Lcom/amazon/tarazed/core/logging/TarazedSessionLogger;Lcom/amazon/tarazed/core/signaling/TarazedEventDispatcher;Lcom/amazon/tarazed/core/signaling/TarazedIoTManager;)V", "isBrowserPresent", "", "timeout", "", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Companion", "TarazedMobileCore_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class BrowserPresenceDetectorAtSessionStart extends BrowserPresenceDetector {
    @Deprecated
    public static final Companion Companion = new Companion(null);
    private static final String TAG = "BrwsrDtectorStart";
    private final TarazedEventDispatcher eventDispatcher;
    private final TarazedIoTManager iotManager;
    private final TarazedSessionLogger logger;

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: BrowserPresenceDetectorAtSessionStart.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0082\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"Lcom/amazon/tarazed/core/utility/BrowserPresenceDetectorAtSessionStart$Companion;", "", "()V", "TAG", "", "TarazedMobileCore_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes13.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public BrowserPresenceDetectorAtSessionStart(@NotNull TarazedSessionLogger logger, @NotNull TarazedEventDispatcher eventDispatcher, @NotNull TarazedIoTManager iotManager) {
        Intrinsics.checkParameterIsNotNull(logger, "logger");
        Intrinsics.checkParameterIsNotNull(eventDispatcher, "eventDispatcher");
        Intrinsics.checkParameterIsNotNull(iotManager, "iotManager");
        this.logger = logger;
        this.eventDispatcher = eventDispatcher;
        this.iotManager = iotManager;
    }

    @Override // com.amazon.tarazed.core.utility.BrowserPresenceDetector
    @Nullable
    public Object isBrowserPresent(long j, @NotNull Continuation<? super Boolean> continuation) {
        return CoroutineScopeKt.coroutineScope(new BrowserPresenceDetectorAtSessionStart$isBrowserPresent$2(this, j, null), continuation);
    }
}
