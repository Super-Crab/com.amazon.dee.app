package com.amazon.scxml.internal;

import com.amazon.scxml.logging.Loggable;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.text.StringsKt__StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: EventQueueWithDelay.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0010#\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0010\u0006\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\b\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u000e\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\nJ\b\u0010\u0010\u001a\u0004\u0018\u00010\u0007J\u000e\u0010\u0011\u001a\u00020\u000e2\u0006\u0010\u0012\u001a\u00020\u0007J*\u0010\u0011\u001a\u00020\u000e2\u0006\u0010\u0012\u001a\u00020\u00072\u0006\u0010\u0013\u001a\u00020\u00142\u0012\u0010\u0015\u001a\u000e\u0012\u0004\u0012\u00020\u0017\u0012\u0004\u0012\u00020\u000e0\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R6\u0010\b\u001a*\u0012\u0004\u0012\u00020\n\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u000b0\tj\u0014\u0012\u0004\u0012\u00020\n\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u000b`\fX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0018"}, d2 = {"Lcom/amazon/scxml/internal/EventQueueWithDelay;", "Lcom/amazon/scxml/logging/Loggable;", "()V", "dispatchQueue", "Lcom/amazon/scxml/internal/DispatchQueue;", "internalEventQueue", "Ljava/util/Queue;", "Lcom/amazon/scxml/internal/Event;", "sendIdToDelayedEventsMap", "Ljava/util/HashMap;", "", "", "Lkotlin/collections/HashMap;", "cancelEvent", "", "sendId", "pop", "push", "event", "delay", "", "completion", "Lkotlin/Function1;", "", "AlexaMobileAndroidVoice-TTA_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class EventQueueWithDelay extends Loggable {
    private final HashMap<String, Set<Event>> sendIdToDelayedEventsMap = new HashMap<>();
    private final DispatchQueue dispatchQueue = new DispatchQueue();
    private final Queue<Event> internalEventQueue = new LinkedList();

    public final void cancelEvent(@NotNull String sendId) {
        CharSequence trim;
        Intrinsics.checkParameterIsNotNull(sendId, "sendId");
        trim = StringsKt__StringsKt.trim((CharSequence) sendId);
        if (trim.toString().length() == 0) {
            Loggable.Companion.getLog().i(getTAG(), "SCXML:delayed-event cannot cancel an event by referencing an empty sendId");
        } else {
            this.dispatchQueue.async(new EventQueueWithDelay$cancelEvent$1(this, sendId));
        }
    }

    @Nullable
    public final Event pop() {
        Ref.ObjectRef objectRef = new Ref.ObjectRef();
        objectRef.element = null;
        this.dispatchQueue.sync(new EventQueueWithDelay$pop$1(this, objectRef));
        return (Event) objectRef.element;
    }

    public final void push(@NotNull Event event, double d, @NotNull Function1<? super Boolean, Unit> completion) {
        Intrinsics.checkParameterIsNotNull(event, "event");
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        this.dispatchQueue.sync(new EventQueueWithDelay$push$1(this, event, d, completion));
    }

    public final void push(@NotNull Event event) {
        Intrinsics.checkParameterIsNotNull(event, "event");
        this.dispatchQueue.async(new EventQueueWithDelay$push$2(this, event));
    }
}
