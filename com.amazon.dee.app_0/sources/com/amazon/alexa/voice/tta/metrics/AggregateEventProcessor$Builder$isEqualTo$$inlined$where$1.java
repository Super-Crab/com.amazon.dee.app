package com.amazon.alexa.voice.tta.metrics;

import androidx.exifinterface.media.ExifInterface;
import com.amazon.alexa.voice.ui.tta.metrics.TtaEvent;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;
/* compiled from: AggregateEventProcessor.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00004\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0004\n\u0002\b\u0004\n\u0002\b\u0004\n\u0002\b\u0005\u0010\u0000\u001a\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u00032\u0006\u0010\u0004\u001a\u0002H\u0002H\n¢\u0006\u0004\b\u0005\u0010\u0006¨\u0006\b"}, d2 = {"<anonymous>", "", ExifInterface.GPS_DIRECTION_TRUE, "Lcom/amazon/alexa/voice/ui/tta/metrics/TtaEvent;", "event", "invoke", "(Lcom/amazon/alexa/voice/ui/tta/metrics/TtaEvent;)Z", "com/amazon/alexa/voice/tta/metrics/AggregateEventProcessor$Builder$where$1$1", "com/amazon/alexa/voice/tta/metrics/AggregateEventProcessor$Builder$where$$inlined$apply$lambda$3"}, k = 3, mv = {1, 1, 16})
/* loaded from: classes11.dex */
public final class AggregateEventProcessor$Builder$isEqualTo$$inlined$where$1 extends Lambda implements Function1<T, Boolean> {
    final /* synthetic */ TtaEvent $item$inlined;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AggregateEventProcessor$Builder$isEqualTo$$inlined$where$1(TtaEvent ttaEvent) {
        super(1);
        this.$item$inlined = ttaEvent;
    }

    @Override // kotlin.jvm.functions.Function1
    /* renamed from: invoke */
    public /* bridge */ /* synthetic */ Boolean mo12165invoke(Object obj) {
        return Boolean.valueOf(invoke((TtaEvent) obj));
    }

    /* JADX WARN: Incorrect types in method signature: (TT;)Z */
    public final boolean invoke(@NotNull TtaEvent event) {
        Intrinsics.checkParameterIsNotNull(event, "event");
        return Intrinsics.areEqual(event, this.$item$inlined);
    }
}
