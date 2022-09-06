package com.amazon.alexa.voice.tta.metrics;

import com.amazon.alexa.api.UiEventName;
import com.amazon.alexa.voice.ui.tta.metrics.TtaEvent;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: SimbaEventProcessor.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\u0005H\n¢\u0006\u0002\b\b"}, d2 = {"<anonymous>", "", "<anonymous parameter 0>", "Lcom/amazon/alexa/voice/ui/tta/metrics/TtaEvent;", "start", "Lcom/amazon/alexa/voice/tta/metrics/EventTime;", "<anonymous parameter 2>", "stop", "invoke"}, k = 3, mv = {1, 1, 16})
/* loaded from: classes11.dex */
public final class SimbaEventProcessor$createTimer$1 extends Lambda implements Function4<TtaEvent, EventTime, TtaEvent, EventTime, Unit> {
    final /* synthetic */ UiEventName $name;
    final /* synthetic */ SimbaEventProcessor this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SimbaEventProcessor$createTimer$1(SimbaEventProcessor simbaEventProcessor, UiEventName uiEventName) {
        super(4);
        this.this$0 = simbaEventProcessor;
        this.$name = uiEventName;
    }

    @Override // kotlin.jvm.functions.Function4
    public /* bridge */ /* synthetic */ Unit invoke(TtaEvent ttaEvent, EventTime eventTime, TtaEvent ttaEvent2, EventTime eventTime2) {
        invoke2(ttaEvent, eventTime, ttaEvent2, eventTime2);
        return Unit.INSTANCE;
    }

    /* renamed from: invoke  reason: avoid collision after fix types in other method */
    public final void invoke2(@NotNull TtaEvent ttaEvent, @NotNull EventTime start, @NotNull TtaEvent ttaEvent2, @NotNull EventTime stop) {
        Intrinsics.checkParameterIsNotNull(ttaEvent, "<anonymous parameter 0>");
        Intrinsics.checkParameterIsNotNull(start, "start");
        Intrinsics.checkParameterIsNotNull(ttaEvent2, "<anonymous parameter 2>");
        Intrinsics.checkParameterIsNotNull(stop, "stop");
        this.this$0.sendSdkEvent(new SdkEvent(this.$name, null, stop.minus(start), 2, null));
    }
}
