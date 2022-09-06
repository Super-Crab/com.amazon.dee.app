package com.amazon.alexa.accessory.internal;

import com.amazon.alexa.accessory.internal.util.Preconditions;
import java.util.ArrayDeque;
import java.util.Iterator;
/* loaded from: classes.dex */
public final class ActionQueue {
    private final ArrayDeque<Action> queue = new ArrayDeque<>();

    /* loaded from: classes.dex */
    public interface Action {

        /* loaded from: classes.dex */
        public interface Callback {
            void onFinished();
        }

        void abort();

        void run(Callback callback);
    }

    public void next() {
        Action peekFirst;
        synchronized (this.queue) {
            this.queue.pollFirst();
            peekFirst = this.queue.peekFirst();
        }
        if (peekFirst != null) {
            peekFirst.run(new $$Lambda$ActionQueue$JZ4jDtIw9xxMOP_xw8okhAuj5Bo(this));
        }
    }

    public void cancelAll() {
        ArrayDeque arrayDeque;
        synchronized (this.queue) {
            arrayDeque = new ArrayDeque(this.queue);
            this.queue.clear();
        }
        Iterator it2 = arrayDeque.iterator();
        while (it2.hasNext()) {
            ((Action) it2.next()).abort();
        }
    }

    public void enqueue(Action action) {
        Preconditions.notNull(action, "action");
        synchronized (this.queue) {
            this.queue.add(action);
            if (this.queue.size() > 1) {
                return;
            }
            action.run(new $$Lambda$ActionQueue$JZ4jDtIw9xxMOP_xw8okhAuj5Bo(this));
        }
    }
}
