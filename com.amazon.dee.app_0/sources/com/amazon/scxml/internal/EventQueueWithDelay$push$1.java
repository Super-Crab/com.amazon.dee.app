package com.amazon.scxml.internal;

import com.amazon.scxml.logging.Loggable;
import com.amazon.scxml.logging.Logger;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.text.StringsKt__StringsKt;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: EventQueueWithDelay.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "", "invoke"}, k = 3, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class EventQueueWithDelay$push$1 extends Lambda implements Function0<Unit> {
    final /* synthetic */ Function1 $completion;
    final /* synthetic */ double $delay;
    final /* synthetic */ Event $event;
    final /* synthetic */ EventQueueWithDelay this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: EventQueueWithDelay.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "", "invoke"}, k = 3, mv = {1, 1, 16})
    /* renamed from: com.amazon.scxml.internal.EventQueueWithDelay$push$1$1  reason: invalid class name */
    /* loaded from: classes13.dex */
    public static final class AnonymousClass1 extends Lambda implements Function0<Unit> {
        AnonymousClass1() {
            super(0);
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
            String tag;
            Queue queue;
            String tag2;
            if (EventQueueWithDelay$push$1.this.$event.isCancelled()) {
                Logger log = Loggable.Companion.getLog();
                tag2 = EventQueueWithDelay$push$1.this.this$0.getTAG();
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("SCXML:delayed-event discarding delayed event (");
                outline107.append(EventQueueWithDelay$push$1.this.$event.getName());
                outline107.append(", ");
                outline107.append(EventQueueWithDelay$push$1.this.$event.getSendId());
                outline107.append("), ");
                log.i(tag2, GeneratedOutlineSupport1.outline84(outline107, EventQueueWithDelay$push$1.this.$delay, ") due to cancellation"));
                EventQueueWithDelay$push$1.this.$completion.mo12165invoke(false);
                return;
            }
            Logger log2 = Loggable.Companion.getLog();
            tag = EventQueueWithDelay$push$1.this.this$0.getTAG();
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("SCXML:delayed-event actually enqueueing delayed event (");
            outline1072.append(EventQueueWithDelay$push$1.this.$event.getName());
            outline1072.append(", ");
            outline1072.append(EventQueueWithDelay$push$1.this.$event.getSendId());
            outline1072.append("), ");
            outline1072.append(EventQueueWithDelay$push$1.this.$delay);
            outline1072.append(')');
            log2.i(tag, outline1072.toString());
            queue = EventQueueWithDelay$push$1.this.this$0.internalEventQueue;
            queue.add(EventQueueWithDelay$push$1.this.$event);
            EventQueueWithDelay$push$1.this.$completion.mo12165invoke(true);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public EventQueueWithDelay$push$1(EventQueueWithDelay eventQueueWithDelay, Event event, double d, Function1 function1) {
        super(0);
        this.this$0 = eventQueueWithDelay;
        this.$event = event;
        this.$delay = d;
        this.$completion = function1;
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
        CharSequence trim;
        HashMap hashMap;
        HashMap hashMap2;
        DispatchQueue dispatchQueue;
        String tag;
        String sendId = this.$event.getSendId();
        if (sendId != null) {
            trim = StringsKt__StringsKt.trim((CharSequence) sendId);
            if (!(trim.toString().length() == 0)) {
                hashMap = this.this$0.sendIdToDelayedEventsMap;
                Set set = (Set) hashMap.get(this.$event.getSendId());
                if (set == null) {
                    set = new HashSet();
                }
                Intrinsics.checkExpressionValueIsNotNull(set, "sendIdToDelayedEventsMap…vent.sendId] ?: HashSet()");
                set.add(this.$event);
                hashMap2 = this.this$0.sendIdToDelayedEventsMap;
                hashMap2.put(this.$event.getSendId(), set);
            } else {
                Logger log = Loggable.Companion.getLog();
                tag = this.this$0.getTAG();
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("SCXML:delayed-event delayed event (");
                outline107.append(this.$event.getName());
                outline107.append(", ");
                log.i(tag, GeneratedOutlineSupport1.outline84(outline107, this.$delay, ") can never be cancelled because it has a blank sendId"));
            }
            dispatchQueue = this.this$0.dispatchQueue;
            dispatchQueue.asyncAfter(this.$delay, new AnonymousClass1());
            return;
        }
        throw new TypeCastException("null cannot be cast to non-null type kotlin.CharSequence");
    }
}
