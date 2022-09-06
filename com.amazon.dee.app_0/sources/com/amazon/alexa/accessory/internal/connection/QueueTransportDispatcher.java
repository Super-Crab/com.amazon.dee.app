package com.amazon.alexa.accessory.internal.connection;

import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.amazon.alexa.accessory.transport.TransportDispatcher;
import com.amazon.alexa.accessory.transport.TransportTransaction;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.Set;
/* loaded from: classes.dex */
public class QueueTransportDispatcher implements TransportDispatcher {
    private TransportDispatcher dispatcher;
    private volatile boolean suspended;
    private final Object monitor = new Object();
    private final Deque<TransportTransaction> queue = new ArrayDeque();
    private final Set<DrainCondition> drainConditions = new HashSet();

    /* loaded from: classes.dex */
    private class DispatcherDrainCondition implements DrainCondition {
        private DispatcherDrainCondition() {
        }

        @Override // com.amazon.alexa.accessory.internal.connection.QueueTransportDispatcher.DrainCondition
        public boolean canDrain() {
            return QueueTransportDispatcher.this.dispatcher != null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public interface DrainCondition {
        boolean canDrain();
    }

    /* loaded from: classes.dex */
    public interface DrainablePredicate<T> {
        boolean canDrain(T t);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public interface IsDrainableCallback {
        void onNowDrainable();
    }

    /* loaded from: classes.dex */
    public static final class StatefulDrainCondition<T> implements DrainCondition {
        private final IsDrainableCallback drainableCallback;
        private final DrainablePredicate<T> drainablePredicate;
        private final Object monitor;
        private T state;

        @Override // com.amazon.alexa.accessory.internal.connection.QueueTransportDispatcher.DrainCondition
        public boolean canDrain() {
            return this.drainablePredicate.canDrain(this.state);
        }

        public void setState(T t) {
            synchronized (this.monitor) {
                this.state = t;
                if (canDrain()) {
                    this.drainableCallback.onNowDrainable();
                }
            }
        }

        private StatefulDrainCondition(Object obj, IsDrainableCallback isDrainableCallback, DrainablePredicate<T> drainablePredicate, T t) {
            Preconditions.notNull(obj, "monitor");
            Preconditions.notNull(isDrainableCallback, "callback");
            Preconditions.notNull(drainablePredicate, "drainablePredicate");
            this.monitor = obj;
            this.drainablePredicate = drainablePredicate;
            this.drainableCallback = isDrainableCallback;
            this.state = t;
        }
    }

    /* loaded from: classes.dex */
    private class SuspendedDrainCondition implements DrainCondition {
        private SuspendedDrainCondition() {
        }

        @Override // com.amazon.alexa.accessory.internal.connection.QueueTransportDispatcher.DrainCondition
        public boolean canDrain() {
            return !QueueTransportDispatcher.this.suspended;
        }
    }

    public QueueTransportDispatcher() {
        this.drainConditions.add(new DispatcherDrainCondition());
        this.drainConditions.add(new SuspendedDrainCondition());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void drain() {
        synchronized (this.monitor) {
            for (DrainCondition drainCondition : this.drainConditions) {
                if (!drainCondition.canDrain()) {
                    return;
                }
            }
            while (!this.queue.isEmpty()) {
                this.dispatcher.dispatch(this.queue.poll());
            }
        }
    }

    @Override // com.amazon.alexa.accessory.transport.TransportDispatcher
    public void abort(TransportTransaction transportTransaction) {
        Preconditions.notNull(transportTransaction, "transaction");
        synchronized (this.monitor) {
            this.queue.remove(transportTransaction);
            if (this.dispatcher != null) {
                this.dispatcher.abort(transportTransaction);
            }
        }
    }

    public <T> StatefulDrainCondition<T> addDrainCondition(T t, DrainablePredicate<T> drainablePredicate) {
        StatefulDrainCondition<T> statefulDrainCondition;
        Preconditions.notNull(drainablePredicate, "canDrainPredicate");
        synchronized (this.monitor) {
            statefulDrainCondition = new StatefulDrainCondition<>(this.monitor, new IsDrainableCallback() { // from class: com.amazon.alexa.accessory.internal.connection.-$$Lambda$QueueTransportDispatcher$u23Yg3W0PSdY9on1I_xoA_PD9OY
                @Override // com.amazon.alexa.accessory.internal.connection.QueueTransportDispatcher.IsDrainableCallback
                public final void onNowDrainable() {
                    QueueTransportDispatcher.this.drain();
                }
            }, drainablePredicate, t);
            this.drainConditions.add(statefulDrainCondition);
        }
        return statefulDrainCondition;
    }

    @Override // com.amazon.alexa.accessory.transport.TransportDispatcher
    public void dispatch(TransportTransaction transportTransaction) {
        Preconditions.notNull(transportTransaction, "transaction");
        synchronized (this.monitor) {
            this.queue.add(transportTransaction);
            drain();
        }
    }

    public void resume() {
        synchronized (this.monitor) {
            this.suspended = false;
            drain();
        }
    }

    public void setDispatcher(TransportDispatcher transportDispatcher) {
        synchronized (this.monitor) {
            this.dispatcher = transportDispatcher;
            drain();
        }
    }

    public void suspend() {
        synchronized (this.monitor) {
            this.suspended = true;
        }
    }
}
