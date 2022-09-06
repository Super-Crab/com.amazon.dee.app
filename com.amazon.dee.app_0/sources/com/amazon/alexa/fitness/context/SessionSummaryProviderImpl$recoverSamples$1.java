package com.amazon.alexa.fitness.context;

import android.os.Handler;
import com.amazon.alexa.fitness.sdk.Sample;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: SessionSummaryProvider.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "samples", "", "Lcom/amazon/alexa/fitness/sdk/Sample;", "invoke"}, k = 3, mv = {1, 1, 16})
/* loaded from: classes8.dex */
public final class SessionSummaryProviderImpl$recoverSamples$1 extends Lambda implements Function1<List<? extends Sample>, Unit> {
    final /* synthetic */ SessionSummaryProviderImpl this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SessionSummaryProviderImpl$recoverSamples$1(SessionSummaryProviderImpl sessionSummaryProviderImpl) {
        super(1);
        this.this$0 = sessionSummaryProviderImpl;
    }

    @Override // kotlin.jvm.functions.Function1
    /* renamed from: invoke */
    public /* bridge */ /* synthetic */ Unit mo12165invoke(List<? extends Sample> list) {
        invoke2(list);
        return Unit.INSTANCE;
    }

    /* renamed from: invoke  reason: avoid collision after fix types in other method */
    public final void invoke2(@NotNull List<? extends Sample> samples) {
        Handler handler;
        Intrinsics.checkParameterIsNotNull(samples, "samples");
        for (Sample sample : samples) {
            if (sample instanceof Sample.MeasurementSample) {
                this.this$0.handleMeasurementSample((Sample.MeasurementSample) sample, sample.getSampleType());
            }
        }
        handler = this.this$0.mainHandler;
        handler.post(new Runnable() { // from class: com.amazon.alexa.fitness.context.SessionSummaryProviderImpl$recoverSamples$1.2
            @Override // java.lang.Runnable
            public final void run() {
                SessionSummaryProviderImpl$recoverSamples$1.this.this$0.updateMetrics();
            }
        });
    }
}
