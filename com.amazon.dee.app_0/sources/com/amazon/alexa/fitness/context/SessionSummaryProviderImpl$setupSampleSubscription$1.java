package com.amazon.alexa.fitness.context;

import android.os.Handler;
import com.amazon.alexa.fitness.sdk.Sample;
import com.amazon.alexa.fitness.sdk.SampleType;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: SessionSummaryProvider.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "sample", "Lcom/amazon/alexa/fitness/sdk/Sample$MeasurementSample;", "invoke"}, k = 3, mv = {1, 1, 16})
/* loaded from: classes8.dex */
public final class SessionSummaryProviderImpl$setupSampleSubscription$1 extends Lambda implements Function1<Sample.MeasurementSample, Unit> {
    final /* synthetic */ SampleType $sampleType;
    final /* synthetic */ SessionSummaryProviderImpl this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SessionSummaryProviderImpl$setupSampleSubscription$1(SessionSummaryProviderImpl sessionSummaryProviderImpl, SampleType sampleType) {
        super(1);
        this.this$0 = sessionSummaryProviderImpl;
        this.$sampleType = sampleType;
    }

    @Override // kotlin.jvm.functions.Function1
    /* renamed from: invoke */
    public /* bridge */ /* synthetic */ Unit mo12165invoke(Sample.MeasurementSample measurementSample) {
        invoke2(measurementSample);
        return Unit.INSTANCE;
    }

    /* renamed from: invoke  reason: avoid collision after fix types in other method */
    public final void invoke2(@NotNull final Sample.MeasurementSample sample) {
        Handler handler;
        Intrinsics.checkParameterIsNotNull(sample, "sample");
        handler = this.this$0.mainHandler;
        handler.post(new Runnable() { // from class: com.amazon.alexa.fitness.context.SessionSummaryProviderImpl$setupSampleSubscription$1.1
            @Override // java.lang.Runnable
            public final void run() {
                SessionSummaryProviderImpl$setupSampleSubscription$1 sessionSummaryProviderImpl$setupSampleSubscription$1 = SessionSummaryProviderImpl$setupSampleSubscription$1.this;
                sessionSummaryProviderImpl$setupSampleSubscription$1.this$0.handleMeasurementSample(sample, sessionSummaryProviderImpl$setupSampleSubscription$1.$sampleType);
            }
        });
    }
}
