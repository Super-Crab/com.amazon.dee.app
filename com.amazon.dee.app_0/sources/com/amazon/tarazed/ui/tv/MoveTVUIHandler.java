package com.amazon.tarazed.ui.tv;

import androidx.annotation.VisibleForTesting;
import com.amazon.tarazed.core.signaling.TarazedEventHandler;
import com.amazon.tarazed.core.signaling.events.EmptySerializable;
import com.amazon.tarazed.core.signaling.events.TarazedEvent;
import java.util.List;
import javax.inject.Inject;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt__CollectionsJVMKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.DeserializationStrategy;
import org.jetbrains.annotations.NotNull;
/* compiled from: MoveTVUIHandler.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \u00132\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001\u0013B\u000f\b\u0007\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\u0016\u0010\u000f\u001a\u00020\u00102\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00020\u0012H\u0016R\u001a\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u001c\u0010\u000b\u001a\n\u0012\u0006\b\u0000\u0012\u00020\u00020\fX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0014"}, d2 = {"Lcom/amazon/tarazed/ui/tv/MoveTVUIHandler;", "Lcom/amazon/tarazed/core/signaling/TarazedEventHandler;", "Lcom/amazon/tarazed/core/signaling/events/EmptySerializable;", "tvUIManager", "Lcom/amazon/tarazed/ui/tv/TVUIManager;", "(Lcom/amazon/tarazed/ui/tv/TVUIManager;)V", "handledEventTypes", "", "", "getHandledEventTypes", "()Ljava/util/List;", "payloadSerializer", "Lkotlinx/serialization/DeserializationStrategy;", "getPayloadSerializer", "()Lkotlinx/serialization/DeserializationStrategy;", "handleEvent", "", "e", "Lcom/amazon/tarazed/core/signaling/events/TarazedEvent;", "Companion", "TarazedAndroidLibrary_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class MoveTVUIHandler implements TarazedEventHandler<EmptySerializable> {
    public static final Companion Companion = new Companion(null);
    @NotNull
    public static final String EVENT_TYPE_MOVE_TV_UI = "moveTVUI";
    @NotNull
    private final List<String> handledEventTypes;
    @NotNull
    private final DeserializationStrategy<? super EmptySerializable> payloadSerializer;
    private final TVUIManager tvUIManager;

    /* compiled from: MoveTVUIHandler.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0016\u0010\u0003\u001a\u00020\u00048\u0006X\u0087T¢\u0006\b\n\u0000\u0012\u0004\b\u0005\u0010\u0002¨\u0006\u0006"}, d2 = {"Lcom/amazon/tarazed/ui/tv/MoveTVUIHandler$Companion;", "", "()V", "EVENT_TYPE_MOVE_TV_UI", "", "EVENT_TYPE_MOVE_TV_UI$annotations", "TarazedAndroidLibrary_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes13.dex */
    public static final class Companion {
        private Companion() {
        }

        @VisibleForTesting
        public static /* synthetic */ void EVENT_TYPE_MOVE_TV_UI$annotations() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    @Inject
    public MoveTVUIHandler(@NotNull TVUIManager tvUIManager) {
        List<String> listOf;
        Intrinsics.checkParameterIsNotNull(tvUIManager, "tvUIManager");
        this.tvUIManager = tvUIManager;
        listOf = CollectionsKt__CollectionsJVMKt.listOf(EVENT_TYPE_MOVE_TV_UI);
        this.handledEventTypes = listOf;
        this.payloadSerializer = EmptySerializable.Companion.serializer();
    }

    @Override // com.amazon.tarazed.core.signaling.TarazedEventHandler
    @NotNull
    public List<String> getHandledEventTypes() {
        return this.handledEventTypes;
    }

    @Override // com.amazon.tarazed.core.signaling.TarazedEventHandler
    @NotNull
    public DeserializationStrategy<? super EmptySerializable> getPayloadSerializer() {
        return this.payloadSerializer;
    }

    @Override // com.amazon.tarazed.core.signaling.TarazedEventHandler
    public void handleEvent(@NotNull TarazedEvent<? extends EmptySerializable> e) {
        Intrinsics.checkParameterIsNotNull(e, "e");
        this.tvUIManager.moveUI$TarazedAndroidLibrary_release();
    }
}
