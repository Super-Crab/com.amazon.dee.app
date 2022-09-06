package rx.internal.producers;

import rx.Producer;
/* loaded from: classes5.dex */
public final class ProducerArbiter implements Producer {
    static final Producer NULL_PRODUCER = new Producer() { // from class: rx.internal.producers.ProducerArbiter.1
        @Override // rx.Producer
        public void request(long j) {
        }
    };
    Producer currentProducer;
    boolean emitting;
    long missedProduced;
    Producer missedProducer;
    long missedRequested;
    long requested;

    public void emitLoop() {
        while (true) {
            synchronized (this) {
                long j = this.missedRequested;
                long j2 = this.missedProduced;
                Producer producer = this.missedProducer;
                int i = (j > 0L ? 1 : (j == 0L ? 0 : -1));
                if (i == 0 && j2 == 0 && producer == null) {
                    this.emitting = false;
                    return;
                }
                this.missedRequested = 0L;
                this.missedProduced = 0L;
                this.missedProducer = null;
                long j3 = this.requested;
                if (j3 != Long.MAX_VALUE) {
                    long j4 = j3 + j;
                    if (j4 < 0 || j4 == Long.MAX_VALUE) {
                        this.requested = Long.MAX_VALUE;
                        j3 = Long.MAX_VALUE;
                    } else {
                        j3 = j4 - j2;
                        if (j3 >= 0) {
                            this.requested = j3;
                        } else {
                            throw new IllegalStateException("more produced than requested");
                        }
                    }
                }
                if (producer != null) {
                    if (producer == NULL_PRODUCER) {
                        this.currentProducer = null;
                    } else {
                        this.currentProducer = producer;
                        producer.request(j3);
                    }
                } else {
                    Producer producer2 = this.currentProducer;
                    if (producer2 != null && i != 0) {
                        producer2.request(j);
                    }
                }
            }
        }
    }

    public void produced(long j) {
        if (j > 0) {
            synchronized (this) {
                if (this.emitting) {
                    this.missedProduced += j;
                    return;
                }
                this.emitting = true;
                try {
                    long j2 = this.requested;
                    if (j2 != Long.MAX_VALUE) {
                        long j3 = j2 - j;
                        if (j3 >= 0) {
                            this.requested = j3;
                        } else {
                            throw new IllegalStateException("more items arrived than were requested");
                        }
                    }
                    emitLoop();
                    return;
                } catch (Throwable th) {
                    synchronized (this) {
                        this.emitting = false;
                        throw th;
                    }
                }
            }
        }
        throw new IllegalArgumentException("n > 0 required");
    }

    @Override // rx.Producer
    public void request(long j) {
        int i = (j > 0L ? 1 : (j == 0L ? 0 : -1));
        if (i >= 0) {
            if (i == 0) {
                return;
            }
            synchronized (this) {
                if (this.emitting) {
                    this.missedRequested += j;
                    return;
                }
                this.emitting = true;
                try {
                    long j2 = this.requested + j;
                    if (j2 < 0) {
                        j2 = Long.MAX_VALUE;
                    }
                    this.requested = j2;
                    Producer producer = this.currentProducer;
                    if (producer != null) {
                        producer.request(j);
                    }
                    emitLoop();
                    return;
                } catch (Throwable th) {
                    synchronized (this) {
                        this.emitting = false;
                        throw th;
                    }
                }
            }
        }
        throw new IllegalArgumentException("n >= 0 required");
    }

    public void setProducer(Producer producer) {
        synchronized (this) {
            if (this.emitting) {
                if (producer == null) {
                    producer = NULL_PRODUCER;
                }
                this.missedProducer = producer;
                return;
            }
            this.emitting = true;
            try {
                this.currentProducer = producer;
                if (producer != null) {
                    producer.request(this.requested);
                }
                emitLoop();
            } catch (Throwable th) {
                synchronized (this) {
                    this.emitting = false;
                    throw th;
                }
            }
        }
    }
}
