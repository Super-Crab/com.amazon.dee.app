package com.amazon.alexa.accessory.transport.queue;

import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.amazon.alexa.accessory.transport.TransportPriority;
import com.amazon.alexa.accessory.transport.operations.TransportOperation;
import com.amazon.alexa.accessory.transport.operations.TransportOperationSequence;
import java.util.Deque;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
/* loaded from: classes6.dex */
public final class PrioritizedTransportOperationQueue implements TransportOperationQueue {
    private final Map<TransportPriority, Deque<TransportOperationSequence>> queues = new HashMap();

    private TransportOperation next(TransportPriority transportPriority) {
        TransportOperationSequence peekFirst;
        Deque<TransportOperationSequence> deque = this.queues.get(transportPriority);
        if (deque == null || (peekFirst = deque.peekFirst()) == null) {
            return null;
        }
        TransportOperation next = peekFirst.next();
        if (next != null) {
            return next;
        }
        deque.removeFirst();
        return next(transportPriority);
    }

    @Override // com.amazon.alexa.accessory.transport.queue.TransportOperationQueue
    public void abort(String str) {
        Preconditions.notNull(str, "key");
        synchronized (this.queues) {
            for (TransportPriority transportPriority : this.queues.keySet()) {
                Deque<TransportOperationSequence> deque = this.queues.get(transportPriority);
                if (deque != null) {
                    Iterator<TransportOperationSequence> it2 = deque.iterator();
                    while (it2.hasNext()) {
                        TransportOperationSequence next = it2.next();
                        if (str.equals(next.getKey())) {
                            next.abort();
                            it2.remove();
                        }
                    }
                }
            }
        }
    }

    @Override // com.amazon.alexa.accessory.transport.queue.TransportOperationQueue
    public TransportOperation dequeue() {
        synchronized (this.queues) {
            TransportOperation next = next(TransportPriority.HIGH);
            if (next != null) {
                return next;
            }
            TransportOperation next2 = next(TransportPriority.MEDIUM);
            if (next2 != null) {
                return next2;
            }
            return next(TransportPriority.LOW);
        }
    }

    @Override // com.amazon.alexa.accessory.transport.queue.TransportOperationQueue
    public void enqueue(TransportOperationSequence transportOperationSequence) {
        Preconditions.notNull(transportOperationSequence, "sequence");
        synchronized (this.queues) {
            Deque<TransportOperationSequence> deque = this.queues.get(transportOperationSequence.getPriority());
            if (deque == null) {
                deque = new LinkedList<>();
                this.queues.put(transportOperationSequence.getPriority(), deque);
            }
            deque.add(transportOperationSequence);
        }
    }
}
