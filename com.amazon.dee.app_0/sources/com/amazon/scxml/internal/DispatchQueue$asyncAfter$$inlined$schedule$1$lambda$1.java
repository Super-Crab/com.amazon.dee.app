package com.amazon.scxml.internal;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;
/* compiled from: DispatchQueue.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002¨\u0006\u0003"}, d2 = {"<anonymous>", "", "invoke", "com/amazon/scxml/internal/DispatchQueue$asyncAfter$1$1"}, k = 3, mv = {1, 1, 16})
/* loaded from: classes13.dex */
final class DispatchQueue$asyncAfter$$inlined$schedule$1$lambda$1 extends Lambda implements Function0<Unit> {
    final /* synthetic */ DispatchQueue$asyncAfter$$inlined$schedule$1 this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DispatchQueue$asyncAfter$$inlined$schedule$1$lambda$1(DispatchQueue$asyncAfter$$inlined$schedule$1 dispatchQueue$asyncAfter$$inlined$schedule$1) {
        super(0);
        this.this$0 = dispatchQueue$asyncAfter$$inlined$schedule$1;
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
        this.this$0.$completion$inlined.mo12560invoke();
    }
}
