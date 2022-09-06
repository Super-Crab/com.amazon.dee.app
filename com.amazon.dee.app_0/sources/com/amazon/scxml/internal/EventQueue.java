package com.amazon.scxml.internal;

import java.util.LinkedList;
import java.util.Queue;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: EventQueue.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\b\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\b\u001a\u0004\u0018\u00010\u0007J\u000e\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Lcom/amazon/scxml/internal/EventQueue;", "", "()V", "dispatchQueue", "Lcom/amazon/scxml/internal/DispatchQueue;", "internalEventQueue", "Ljava/util/Queue;", "Lcom/amazon/scxml/internal/Event;", "pop", "push", "", "event", "AlexaMobileAndroidVoice-TTA_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class EventQueue {
    private final DispatchQueue dispatchQueue = new DispatchQueue();
    private Queue<Event> internalEventQueue = new LinkedList();

    @Nullable
    public final Event pop() {
        Ref.ObjectRef objectRef = new Ref.ObjectRef();
        objectRef.element = null;
        this.dispatchQueue.sync(new EventQueue$pop$1(this, objectRef));
        return (Event) objectRef.element;
    }

    public final void push(@NotNull Event event) {
        Intrinsics.checkParameterIsNotNull(event, "event");
        this.dispatchQueue.async(new EventQueue$push$1(this, event));
    }
}
