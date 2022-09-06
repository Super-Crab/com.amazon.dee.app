package com.amazon.tarazed.core.utility;

import com.amazon.tarazed.core.logging.TarazedSessionLogger;
import com.amazon.tarazed.core.signaling.TarazedEventHandler;
import com.amazon.tarazed.core.signaling.events.EmptySerializable;
import com.amazon.tarazed.core.signaling.events.TarazedEvent;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt__CollectionsJVMKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CompletableDeferred;
import kotlinx.serialization.DeserializationStrategy;
import kotlinx.serialization.KSerializer;
import org.jetbrains.annotations.NotNull;
/* compiled from: BrowserPongHandler.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \u00172\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001\u0017B\u001b\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u0016\u0010\u0013\u001a\u00020\u00142\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00020\u0016H\u0016R\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\nX\u0096\u0004¢\u0006\n\n\u0002\b\u000e\u001a\u0004\b\f\u0010\rR\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00020\u0010X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012¨\u0006\u0018"}, d2 = {"Lcom/amazon/tarazed/core/utility/BrowserPongHandler;", "Lcom/amazon/tarazed/core/signaling/TarazedEventHandler;", "Lcom/amazon/tarazed/core/signaling/events/EmptySerializable;", "deferred", "Lkotlinx/coroutines/CompletableDeferred;", "", "logger", "Lcom/amazon/tarazed/core/logging/TarazedSessionLogger;", "(Lkotlinx/coroutines/CompletableDeferred;Lcom/amazon/tarazed/core/logging/TarazedSessionLogger;)V", "handledEventTypes", "", "", "getHandledEventTypes", "()Ljava/util/List;", "handledEventTypes$1", "payloadSerializer", "Lkotlinx/serialization/KSerializer;", "getPayloadSerializer", "()Lkotlinx/serialization/KSerializer;", "handleEvent", "", "e", "Lcom/amazon/tarazed/core/signaling/events/TarazedEvent;", "Companion", "TarazedMobileCore_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class BrowserPongHandler implements TarazedEventHandler<EmptySerializable> {
    public static final Companion Companion = new Companion(null);
    private static final String EVENT_TYPE_PONG = "pong";
    private static final String TAG = "BrowserPongHandler";
    private static final List<String> handledEventTypes;
    private final CompletableDeferred<Boolean> deferred;
    @NotNull
    private final List<String> handledEventTypes$1;
    private final TarazedSessionLogger logger;
    @NotNull
    private final KSerializer<EmptySerializable> payloadSerializer;

    /* compiled from: BrowserPongHandler.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0003\b\u0080\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0013\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00040\u0007H\u0000¢\u0006\u0002\b\tR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00040\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Lcom/amazon/tarazed/core/utility/BrowserPongHandler$Companion;", "", "()V", "EVENT_TYPE_PONG", "", "TAG", "handledEventTypes", "", "getHandledEventTypes", "getHandledEventTypes$TarazedMobileCore_release", "TarazedMobileCore_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes13.dex */
    public static final class Companion {
        private Companion() {
        }

        @NotNull
        public final List<String> getHandledEventTypes$TarazedMobileCore_release() {
            return BrowserPongHandler.handledEventTypes;
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    static {
        List<String> listOf;
        listOf = CollectionsKt__CollectionsJVMKt.listOf(EVENT_TYPE_PONG);
        handledEventTypes = listOf;
    }

    public BrowserPongHandler(@NotNull CompletableDeferred<Boolean> deferred, @NotNull TarazedSessionLogger logger) {
        Intrinsics.checkParameterIsNotNull(deferred, "deferred");
        Intrinsics.checkParameterIsNotNull(logger, "logger");
        this.deferred = deferred;
        this.logger = logger;
        this.handledEventTypes$1 = handledEventTypes;
        this.payloadSerializer = EmptySerializable.Companion.serializer();
    }

    @Override // com.amazon.tarazed.core.signaling.TarazedEventHandler
    @NotNull
    public List<String> getHandledEventTypes() {
        return this.handledEventTypes$1;
    }

    @Override // com.amazon.tarazed.core.signaling.TarazedEventHandler
    public void handleEvent(@NotNull TarazedEvent<? extends EmptySerializable> e) {
        Intrinsics.checkParameterIsNotNull(e, "e");
        String type = e.getType();
        if (type.hashCode() == 3446776 && type.equals(EVENT_TYPE_PONG)) {
            this.logger.d(TAG, "Handling pong received from browser.");
            this.deferred.complete(true);
            return;
        }
        TarazedSessionLogger tarazedSessionLogger = this.logger;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Received unexpected event: ");
        outline107.append(e.getType());
        outline107.append(" Should have only received pong event.");
        tarazedSessionLogger.e(TAG, outline107.toString());
    }

    @Override // com.amazon.tarazed.core.signaling.TarazedEventHandler
    @NotNull
    public DeserializationStrategy<? super EmptySerializable> getPayloadSerializer() {
        return this.payloadSerializer;
    }
}
