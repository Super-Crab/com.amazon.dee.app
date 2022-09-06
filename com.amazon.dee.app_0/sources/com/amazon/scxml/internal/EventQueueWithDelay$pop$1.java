package com.amazon.scxml.internal;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.Ref;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: EventQueueWithDelay.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "", "invoke"}, k = 3, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class EventQueueWithDelay$pop$1 extends Lambda implements Function0<Unit> {
    final /* synthetic */ Ref.ObjectRef $finalResult;
    final /* synthetic */ EventQueueWithDelay this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public EventQueueWithDelay$pop$1(EventQueueWithDelay eventQueueWithDelay, Ref.ObjectRef objectRef) {
        super(0);
        this.this$0 = eventQueueWithDelay;
        this.$finalResult = objectRef;
    }

    @Override // kotlin.jvm.functions.Function0
    /* renamed from: invoke */
    public /* bridge */ /* synthetic */ Unit mo12560invoke() {
        mo12560invoke();
        return Unit.INSTANCE;
    }

    /* JADX WARN: Type inference failed for: r0v3, types: [T, com.amazon.scxml.internal.Event, java.lang.Object] */
    @Override // kotlin.jvm.functions.Function0
    /* renamed from: invoke  reason: collision with other method in class */
    public final void mo12560invoke() {
        Queue queue;
        Queue queue2;
        HashMap hashMap;
        HashMap hashMap2;
        queue = this.this$0.internalEventQueue;
        ?? r0 = (Event) queue.peek();
        if (r0 != 0) {
            queue2 = this.this$0.internalEventQueue;
            queue2.remove();
            hashMap = this.this$0.sendIdToDelayedEventsMap;
            Set set = (Set) hashMap.get(r0.getSendId());
            HashSet hashSet = set;
            if (set == null) {
                hashSet = new HashSet();
            }
            Intrinsics.checkExpressionValueIsNotNull(hashSet, "sendIdToDelayedEventsMap…sult.sendId] ?: HashSet()");
            if (!hashSet.isEmpty()) {
                hashSet.remove(r0);
                hashMap2 = this.this$0.sendIdToDelayedEventsMap;
                hashMap2.put(r0.getSendId(), hashSet);
            }
            if (r0.isCancelled()) {
                return;
            }
            this.$finalResult.element = r0;
        }
    }
}
