package com.amazon.scxml.internal;

import java.util.Queue;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.Ref;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: EventQueue.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "", "invoke"}, k = 3, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class EventQueue$pop$1 extends Lambda implements Function0<Unit> {
    final /* synthetic */ Ref.ObjectRef $result;
    final /* synthetic */ EventQueue this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public EventQueue$pop$1(EventQueue eventQueue, Ref.ObjectRef objectRef) {
        super(0);
        this.this$0 = eventQueue;
        this.$result = objectRef;
    }

    @Override // kotlin.jvm.functions.Function0
    /* renamed from: invoke */
    public /* bridge */ /* synthetic */ Unit mo12560invoke() {
        mo12560invoke();
        return Unit.INSTANCE;
    }

    /* JADX WARN: Type inference failed for: r1v3, types: [T, com.amazon.scxml.internal.Event] */
    @Override // kotlin.jvm.functions.Function0
    /* renamed from: invoke  reason: collision with other method in class */
    public final void mo12560invoke() {
        Queue queue;
        Ref.ObjectRef objectRef = this.$result;
        queue = this.this$0.internalEventQueue;
        objectRef.element = (Event) queue.remove();
    }
}
