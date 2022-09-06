package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;
import java.util.ArrayDeque;
import java.util.Collections;
import java.util.Queue;
@GwtCompatible
/* loaded from: classes3.dex */
class ConsumingQueueIterator<T> extends AbstractIterator<T> {
    private final Queue<T> queue;

    ConsumingQueueIterator(T... tArr) {
        this.queue = new ArrayDeque(tArr.length);
        Collections.addAll(this.queue, tArr);
    }

    @Override // com.google.common.collect.AbstractIterator
    /* renamed from: computeNext */
    public T mo8230computeNext() {
        return this.queue.isEmpty() ? endOfData() : this.queue.remove();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ConsumingQueueIterator(Queue<T> queue) {
        this.queue = (Queue) Preconditions.checkNotNull(queue);
    }
}
