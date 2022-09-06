package com.amazon.alexa.voice.tta.metrics;

import androidx.exifinterface.media.ExifInterface;
import com.amazon.alexa.voice.ui.tta.metrics.TtaEvent;
import kotlin.Metadata;
import kotlin.collections.ArraysKt___ArraysKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;
/* compiled from: AggregateEventProcessor.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00004\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0004\n\u0002\b\u0004\n\u0002\b\u0004\n\u0002\b\u0005\u0010\u0000\u001a\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u00032\u0006\u0010\u0004\u001a\u0002H\u0002H\n¢\u0006\u0004\b\u0005\u0010\u0006¨\u0006\b"}, d2 = {"<anonymous>", "", ExifInterface.GPS_DIRECTION_TRUE, "Lcom/amazon/alexa/voice/ui/tta/metrics/TtaEvent;", "event", "invoke", "(Lcom/amazon/alexa/voice/ui/tta/metrics/TtaEvent;)Z", "com/amazon/alexa/voice/tta/metrics/AggregateEventProcessor$Builder$where$1$2", "com/amazon/alexa/voice/tta/metrics/AggregateEventProcessor$Builder$where$$inlined$apply$lambda$6"}, k = 3, mv = {1, 1, 16})
/* loaded from: classes11.dex */
public final class AggregateEventProcessor$Builder$isOneOf$$inlined$where$2 extends Lambda implements Function1<T, Boolean> {
    final /* synthetic */ TtaEvent[] $items$inlined;
    final /* synthetic */ Function1 $oldPredicate;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AggregateEventProcessor$Builder$isOneOf$$inlined$where$2(Function1 function1, TtaEvent[] ttaEventArr) {
        super(1);
        this.$oldPredicate = function1;
        this.$items$inlined = ttaEventArr;
    }

    @Override // kotlin.jvm.functions.Function1
    /* renamed from: invoke */
    public /* bridge */ /* synthetic */ Boolean mo12165invoke(Object obj) {
        return Boolean.valueOf(invoke((TtaEvent) obj));
    }

    /* JADX WARN: Incorrect types in method signature: (TT;)Z */
    public final boolean invoke(@NotNull TtaEvent event) {
        boolean contains;
        Intrinsics.checkParameterIsNotNull(event, "event");
        if (((Boolean) this.$oldPredicate.mo12165invoke(event)).booleanValue()) {
            contains = ArraysKt___ArraysKt.contains(this.$items$inlined, event);
            if (contains) {
                return true;
            }
        }
        return false;
    }
}
