package com.amazon.tarazed.core.session;

import com.amazon.tarazed.core.metrics.TarazedMetricsHelper;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: TarazedSession.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "", "invoke"}, k = 3, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class TarazedSession$setPermissionDialogCallbacks$1 extends Lambda implements Function0<Unit> {
    final /* synthetic */ TarazedSession this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TarazedSession$setPermissionDialogCallbacks$1(TarazedSession tarazedSession) {
        super(0);
        this.this$0 = tarazedSession;
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
        TarazedMetricsHelper tarazedMetricsHelper;
        tarazedMetricsHelper = this.this$0.metricsHelper;
        tarazedMetricsHelper.startMetricTimer("TarazedSession", "SessionDurationFromAccept");
        this.this$0.sendStateChangeEventRequest(new TarazedSessionStateChangeRequest(TarazedSessionStateChangeType.START_SESSION, TarazedSessionStateChangeSource.SOURCE_AGENT));
    }
}
