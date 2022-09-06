package com.amazon.alexa.accessory.transport.queue;

import com.amazon.alexa.accessory.AccessoryTransport;
import com.amazon.alexa.accessory.internal.util.IOUtils;
import com.amazon.alexa.accessory.internal.util.Logger;
import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.amazon.alexa.accessory.transport.TransportData;
import com.amazon.alexa.accessory.transport.TransportDispatcher;
import com.amazon.alexa.accessory.transport.TransportTransaction;
import com.amazon.alexa.accessory.transport.codecs.TransportCodec;
import com.amazon.alexa.accessory.transport.operations.TransportOperation;
import com.amazon.alexa.accessory.transport.operations.TransportOperationSequence;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.util.ArrayDeque;
import java.util.Deque;
/* loaded from: classes6.dex */
public final class PrioritizedTransportQueue implements TransportQueue, TransportDispatcher {
    private TransportCodec codec;
    private final TransportCodec.Factory codecFactory;
    private final Object codecLock;
    private volatile boolean disposed;
    private final TransportOperationQueue queue;
    private final Deque<TransportTransaction> transactions;
    private final AccessoryTransport transport;

    public PrioritizedTransportQueue(AccessoryTransport accessoryTransport, TransportCodec.Factory factory, TransportOperationQueue transportOperationQueue) {
        Preconditions.notNull(accessoryTransport, "transport");
        Preconditions.notNull(factory, "codecFactory");
        Preconditions.notNull(transportOperationQueue, "queue");
        this.transport = accessoryTransport;
        this.codecLock = new Object();
        this.codecFactory = factory;
        this.transactions = new ArrayDeque();
        this.queue = transportOperationQueue;
    }

    private TransportCodec awaitCodec() throws IOException {
        synchronized (this.codecLock) {
            if (this.codec != null) {
                return this.codec;
            }
            try {
                this.codecLock.wait();
                if (this.codec != null) {
                    return this.codec;
                }
                throw new IOException("Transport code is unavailable!");
            } catch (InterruptedException e) {
                InterruptedIOException interruptedIOException = new InterruptedIOException();
                interruptedIOException.addSuppressed(e);
                throw interruptedIOException;
            }
        }
    }

    private void awaitTransactions() throws InterruptedIOException {
        synchronized (this.transactions) {
            if (!this.disposed && this.transactions.isEmpty()) {
                try {
                    this.transactions.wait();
                } catch (InterruptedException e) {
                    InterruptedIOException interruptedIOException = new InterruptedIOException(e.getMessage());
                    interruptedIOException.addSuppressed(e);
                    throw interruptedIOException;
                }
            }
        }
    }

    private void encodeTransactions(TransportCodec transportCodec) throws IOException {
        synchronized (this.transactions) {
            while (!this.transactions.isEmpty()) {
                this.queue.enqueue(transportCodec.encode(this.transactions.poll()));
            }
        }
    }

    private TransportCodec requireCodec() throws IOException {
        synchronized (this.codecLock) {
            if (this.codec != null) {
                return this.codec;
            }
            this.codec = this.codecFactory.create(this.transport);
            if (this.codec != null) {
                this.codecLock.notifyAll();
                return this.codec;
            }
            throw new IllegalStateException("Transport codec factory must not return null!");
        }
    }

    @Override // com.amazon.alexa.accessory.transport.TransportDispatcher
    public void abort(TransportTransaction transportTransaction) {
        Logger.d("Aborting transaction " + transportTransaction);
        this.queue.abort(transportTransaction.getKey());
        synchronized (this.transactions) {
            this.transactions.remove(transportTransaction);
        }
    }

    @Override // com.amazon.alexa.accessory.transport.TransportDispatcher
    public void dispatch(TransportTransaction transportTransaction) {
        Logger.d("Dispatching transaction " + transportTransaction);
        synchronized (this.transactions) {
            this.transactions.add(transportTransaction);
            this.transactions.notifyAll();
        }
    }

    @Override // com.amazon.alexa.accessory.transport.queue.TransportQueue
    public void dispose() {
        if (this.disposed) {
            return;
        }
        this.disposed = true;
        IOUtils.closeQuietly(this.transport);
        synchronized (this.codecLock) {
            this.codecLock.notifyAll();
        }
        synchronized (this.transactions) {
            this.transactions.clear();
            this.transactions.notifyAll();
        }
    }

    @Override // com.amazon.alexa.accessory.transport.queue.TransportQueue
    public void executeNext() throws IOException {
        if (!this.disposed) {
            TransportCodec awaitCodec = awaitCodec();
            while (!this.disposed) {
                encodeTransactions(awaitCodec);
                TransportOperation dequeue = this.queue.dequeue();
                if (dequeue != null) {
                    dequeue.execute();
                    return;
                }
                awaitTransactions();
            }
            return;
        }
        throw new IllegalStateException("Transport queue is disposed!");
    }

    @Override // com.amazon.alexa.accessory.transport.queue.TransportQueue
    public void prepare() throws IOException {
        if (!this.disposed) {
            this.transport.source();
            return;
        }
        throw new IllegalStateException("Transport queue is disposed!");
    }

    @Override // com.amazon.alexa.accessory.transport.queue.TransportQueue
    public TransportData read() throws IOException {
        if (!this.disposed) {
            TransportCodec requireCodec = requireCodec();
            final TransportOperationQueue transportOperationQueue = this.queue;
            transportOperationQueue.getClass();
            return requireCodec.decode(new TransportCodec.OperationSequenceEncoder() { // from class: com.amazon.alexa.accessory.transport.queue.-$$Lambda$PMROXOAoCqLGovd1wYGEo0_fhT4
                @Override // com.amazon.alexa.accessory.transport.codecs.TransportCodec.OperationSequenceEncoder
                public final void encoded(TransportOperationSequence transportOperationSequence) {
                    TransportOperationQueue.this.enqueue(transportOperationSequence);
                }
            });
        }
        throw new IllegalStateException("Transport queue is disposed!");
    }

    public PrioritizedTransportQueue(AccessoryTransport accessoryTransport, TransportCodec.Factory factory) {
        this(accessoryTransport, factory, new PrioritizedTransportOperationQueue());
    }
}
