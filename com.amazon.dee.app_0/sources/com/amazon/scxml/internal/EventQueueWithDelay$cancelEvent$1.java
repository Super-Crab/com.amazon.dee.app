package com.amazon.scxml.internal;

import com.amazon.scxml.logging.Loggable;
import com.amazon.scxml.logging.Logger;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: EventQueueWithDelay.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "", "invoke"}, k = 3, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class EventQueueWithDelay$cancelEvent$1 extends Lambda implements Function0<Unit> {
    final /* synthetic */ String $sendId;
    final /* synthetic */ EventQueueWithDelay this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public EventQueueWithDelay$cancelEvent$1(EventQueueWithDelay eventQueueWithDelay, String str) {
        super(0);
        this.this$0 = eventQueueWithDelay;
        this.$sendId = str;
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
        HashMap hashMap;
        String tag2;
        String tag3;
        Logger log = Loggable.Companion.getLog();
        tag = this.this$0.getTAG();
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("SCXML:delayed-event potentially cancelling delayed event (");
        outline107.append(this.$sendId);
        outline107.append(')');
        log.i(tag, outline107.toString());
        hashMap = this.this$0.sendIdToDelayedEventsMap;
        Set<Event> set = (Set) hashMap.remove(this.$sendId);
        if (set == null) {
            set = new HashSet();
        }
        if (!set.isEmpty()) {
            Logger log2 = Loggable.Companion.getLog();
            tag3 = this.this$0.getTAG();
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("SCXML:delayed-event actually cancelled delayed event (");
            outline1072.append(this.$sendId);
            outline1072.append(')');
            log2.i(tag3, outline1072.toString());
        } else {
            Logger log3 = Loggable.Companion.getLog();
            tag2 = this.this$0.getTAG();
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("SCXML:delayed-event did not cancel delayed event (");
            outline1073.append(this.$sendId);
            outline1073.append(") - nothing to cancel");
            log3.i(tag2, outline1073.toString());
        }
        for (Event event : set) {
            event.setCancelled(true);
        }
    }
}
