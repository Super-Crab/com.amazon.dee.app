package com.amazon.alexa.fitness.service.hds;

import com.amazon.alexa.fitness.service.hds.model.SessionSummary;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;
/* compiled from: HdsClientImpl.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "", "invoke"}, k = 3, mv = {1, 1, 16})
/* loaded from: classes8.dex */
final class HdsClientImpl$uploadSession$1 extends Lambda implements Function0<Unit> {
    final /* synthetic */ boolean $isRetry;
    final /* synthetic */ SessionSummary $sessionSummary;
    final /* synthetic */ HdsClientImpl this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public HdsClientImpl$uploadSession$1(HdsClientImpl hdsClientImpl, SessionSummary sessionSummary, boolean z) {
        super(0);
        this.this$0 = hdsClientImpl;
        this.$sessionSummary = sessionSummary;
        this.$isRetry = z;
    }

    @Override // kotlin.jvm.functions.Function0
    /* renamed from: invoke */
    public /* bridge */ /* synthetic */ Unit mo12560invoke() {
        mo12560invoke();
        return Unit.INSTANCE;
    }

    @Override // kotlin.jvm.functions.Function0
    /* renamed from: invoke  reason: collision with other method in class */
    public final void mo12560invoke() {
        this.this$0.uploadSessionAndQuantityRecords(this.$sessionSummary, this.$isRetry);
    }
}
