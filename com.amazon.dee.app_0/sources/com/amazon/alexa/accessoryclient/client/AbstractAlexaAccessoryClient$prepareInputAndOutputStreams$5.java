package com.amazon.alexa.accessoryclient.client;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: AbstractAlexaAccessoryClient.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "", "invoke"}, k = 3, mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class AbstractAlexaAccessoryClient$prepareInputAndOutputStreams$5 extends Lambda implements Function0<Unit> {
    final /* synthetic */ AbstractAlexaAccessoryClient this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AbstractAlexaAccessoryClient$prepareInputAndOutputStreams$5(AbstractAlexaAccessoryClient abstractAlexaAccessoryClient) {
        super(0);
        this.this$0 = abstractAlexaAccessoryClient;
    }

    @Override // kotlin.jvm.functions.Function0
    /* renamed from: invoke */
    public /* bridge */ /* synthetic */ Unit mo12560invoke() {
        mo12560invoke();
        return Unit.INSTANCE;
    }

    @Override // kotlin.jvm.functions.Function0
    /* renamed from: invoke  reason: avoid collision after fix types in other method */
    public final void mo12560invoke() {
        Object obj;
        obj = this.this$0.lock;
        synchronized (obj) {
            this.this$0.serviceHasAckedHandshake = true;
            Unit unit = Unit.INSTANCE;
        }
    }
}
