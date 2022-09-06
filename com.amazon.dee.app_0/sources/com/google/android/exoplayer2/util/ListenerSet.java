package com.google.android.exoplayer2.util;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import androidx.annotation.CheckResult;
import androidx.annotation.Nullable;
import com.google.android.exoplayer2.util.MutableFlags;
import com.google.common.base.Supplier;
import java.util.ArrayDeque;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArraySet;
import javax.annotation.Nonnull;
/* loaded from: classes2.dex */
public final class ListenerSet<T, E extends MutableFlags> {
    private static final int MSG_ITERATION_FINISHED = 0;
    private static final int MSG_LAZY_RELEASE = 1;
    private final Clock clock;
    private final Supplier<E> eventFlagsSupplier;
    private final ArrayDeque<Runnable> flushingEvents;
    private final HandlerWrapper handler;
    private final IterationFinishedEvent<T, E> iterationFinishedEvent;
    private final CopyOnWriteArraySet<ListenerHolder<T, E>> listeners;
    private final ArrayDeque<Runnable> queuedEvents;
    private boolean released;

    /* loaded from: classes2.dex */
    public interface Event<T> {
        void invoke(T t);
    }

    /* loaded from: classes2.dex */
    public interface IterationFinishedEvent<T, E extends MutableFlags> {
        void invoke(T t, E e);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public static final class ListenerHolder<T, E extends MutableFlags> {
        private E eventsFlags;
        @Nonnull
        public final T listener;
        private boolean needsIterationFinishedEvent;
        private boolean released;

        public ListenerHolder(@Nonnull T t, Supplier<E> supplier) {
            this.listener = t;
            this.eventsFlags = supplier.mo8291get();
        }

        public boolean equals(@Nullable Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj != null && ListenerHolder.class == obj.getClass()) {
                return this.listener.equals(((ListenerHolder) obj).listener);
            }
            return false;
        }

        public int hashCode() {
            return this.listener.hashCode();
        }

        public void invoke(int i, Event<T> event) {
            if (!this.released) {
                if (i != -1) {
                    this.eventsFlags.add(i);
                }
                this.needsIterationFinishedEvent = true;
                event.invoke(this.listener);
            }
        }

        public void iterationFinished(Supplier<E> supplier, IterationFinishedEvent<T, E> iterationFinishedEvent) {
            if (this.released || !this.needsIterationFinishedEvent) {
                return;
            }
            E e = this.eventsFlags;
            this.eventsFlags = supplier.mo8291get();
            this.needsIterationFinishedEvent = false;
            iterationFinishedEvent.invoke(this.listener, e);
        }

        public void release(IterationFinishedEvent<T, E> iterationFinishedEvent) {
            this.released = true;
            if (this.needsIterationFinishedEvent) {
                iterationFinishedEvent.invoke(this.listener, this.eventsFlags);
            }
        }
    }

    public ListenerSet(Looper looper, Clock clock, Supplier<E> supplier, IterationFinishedEvent<T, E> iterationFinishedEvent) {
        this(new CopyOnWriteArraySet(), looper, clock, supplier, iterationFinishedEvent);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean handleMessage(Message message) {
        int i = message.what;
        if (i == 0) {
            Iterator<ListenerHolder<T, E>> it2 = this.listeners.iterator();
            while (it2.hasNext()) {
                it2.next().iterationFinished(this.eventFlagsSupplier, this.iterationFinishedEvent);
                if (this.handler.hasMessages(0)) {
                    break;
                }
            }
        } else if (i == 1) {
            sendEvent(message.arg1, (Event) message.obj);
            release();
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$queueEvent$0(CopyOnWriteArraySet copyOnWriteArraySet, int i, Event event) {
        Iterator it2 = copyOnWriteArraySet.iterator();
        while (it2.hasNext()) {
            ((ListenerHolder) it2.next()).invoke(i, event);
        }
    }

    public void add(T t) {
        if (this.released) {
            return;
        }
        Assertions.checkNotNull(t);
        this.listeners.add(new ListenerHolder<>(t, this.eventFlagsSupplier));
    }

    @CheckResult
    public ListenerSet<T, E> copy(Looper looper, IterationFinishedEvent<T, E> iterationFinishedEvent) {
        return new ListenerSet<>(this.listeners, looper, this.clock, this.eventFlagsSupplier, iterationFinishedEvent);
    }

    public void flushEvents() {
        if (this.queuedEvents.isEmpty()) {
            return;
        }
        if (!this.handler.hasMessages(0)) {
            this.handler.obtainMessage(0).sendToTarget();
        }
        boolean z = !this.flushingEvents.isEmpty();
        this.flushingEvents.addAll(this.queuedEvents);
        this.queuedEvents.clear();
        if (z) {
            return;
        }
        while (!this.flushingEvents.isEmpty()) {
            this.flushingEvents.peekFirst().run();
            this.flushingEvents.removeFirst();
        }
    }

    public void lazyRelease(int i, Event<T> event) {
        this.handler.obtainMessage(1, i, 0, event).sendToTarget();
    }

    public void queueEvent(final int i, final Event<T> event) {
        final CopyOnWriteArraySet copyOnWriteArraySet = new CopyOnWriteArraySet(this.listeners);
        this.queuedEvents.add(new Runnable() { // from class: com.google.android.exoplayer2.util.-$$Lambda$ListenerSet$Q1s2242IGqOFgK3lFhqwOk8KBXE
            @Override // java.lang.Runnable
            public final void run() {
                ListenerSet.lambda$queueEvent$0(copyOnWriteArraySet, i, event);
            }
        });
    }

    public void release() {
        Iterator<ListenerHolder<T, E>> it2 = this.listeners.iterator();
        while (it2.hasNext()) {
            it2.next().release(this.iterationFinishedEvent);
        }
        this.listeners.clear();
        this.released = true;
    }

    public void remove(T t) {
        Iterator<ListenerHolder<T, E>> it2 = this.listeners.iterator();
        while (it2.hasNext()) {
            ListenerHolder<T, E> next = it2.next();
            if (next.listener.equals(t)) {
                next.release(this.iterationFinishedEvent);
                this.listeners.remove(next);
            }
        }
    }

    public void sendEvent(int i, Event<T> event) {
        queueEvent(i, event);
        flushEvents();
    }

    private ListenerSet(CopyOnWriteArraySet<ListenerHolder<T, E>> copyOnWriteArraySet, Looper looper, Clock clock, Supplier<E> supplier, IterationFinishedEvent<T, E> iterationFinishedEvent) {
        this.clock = clock;
        this.listeners = copyOnWriteArraySet;
        this.eventFlagsSupplier = supplier;
        this.iterationFinishedEvent = iterationFinishedEvent;
        this.flushingEvents = new ArrayDeque<>();
        this.queuedEvents = new ArrayDeque<>();
        this.handler = clock.createHandler(looper, new Handler.Callback() { // from class: com.google.android.exoplayer2.util.-$$Lambda$ListenerSet$gt28PMFfhLXGfIVY4v7eP9kmalg
            @Override // android.os.Handler.Callback
            public final boolean handleMessage(Message message) {
                boolean handleMessage;
                handleMessage = ListenerSet.this.handleMessage(message);
                return handleMessage;
            }
        });
    }
}
