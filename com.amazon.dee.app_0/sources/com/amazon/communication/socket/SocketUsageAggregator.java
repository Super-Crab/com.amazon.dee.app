package com.amazon.communication.socket;

import amazon.communication.identity.EndpointIdentity;
import com.amazon.communication.time.GlobalTimeSource;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
/* loaded from: classes12.dex */
public class SocketUsageAggregator implements SocketUsageAggregatedReader, SocketUsageWriter {
    protected static final double DECAY_CONSTANT = Math.pow(0.5d, 3.3333333333333335E-5d);
    protected static final int DECAY_HALFLIFE_MILLISECONDS = 30000;
    protected static final long MINIMUM_DELAY_BETWEEN_DECAYS_MILLISECONDS = 100;
    protected final ConcurrentMap<EndpointIdentity, ConcurrentMap<Measurements, DecayingCount>> mAverageIntervalsByEndpoint = new ConcurrentHashMap();

    /* JADX INFO: Access modifiers changed from: protected */
    /* loaded from: classes12.dex */
    public static class DecayingCount {
        private long mLastTimestamp = -1;
        protected double mCount = 0.0d;

        protected DecayingCount() {
        }

        public synchronized int decayCount(long j) {
            if (this.mLastTimestamp == -1) {
                this.mLastTimestamp = j;
            } else {
                long j2 = j - this.mLastTimestamp;
                if (j2 >= SocketUsageAggregator.MINIMUM_DELAY_BETWEEN_DECAYS_MILLISECONDS) {
                    this.mCount *= Math.pow(SocketUsageAggregator.DECAY_CONSTANT, j2);
                    this.mLastTimestamp = j;
                }
            }
            return (int) Math.round(this.mCount);
        }

        public int getCount() {
            decayCount(GlobalTimeSource.INSTANCE.currentTimeMillis());
            return (int) Math.round(this.mCount);
        }

        public synchronized void incrementCount(long j) {
            decayCount(j);
            this.mCount += 1.0d;
        }
    }

    private int countMeasurementForEndpoint(EndpointIdentity endpointIdentity, Measurements measurements) {
        DecayingCount decayingCount;
        if (endpointIdentity != null) {
            if (measurements != null) {
                ConcurrentMap<Measurements, DecayingCount> concurrentMap = this.mAverageIntervalsByEndpoint.get(endpointIdentity);
                if (concurrentMap != null && (decayingCount = concurrentMap.get(measurements)) != null) {
                    return decayingCount.getCount();
                }
                return 0;
            }
            throw new IllegalArgumentException("Argument: measurement must not be null");
        }
        throw new IllegalArgumentException("Argument: endpoint must not be null");
    }

    private DecayingCount findOrCreateDecayingCount(EndpointIdentity endpointIdentity, Measurements measurements) {
        ConcurrentMap<Measurements, DecayingCount> putIfAbsent;
        ConcurrentMap<Measurements, DecayingCount> concurrentMap = this.mAverageIntervalsByEndpoint.get(endpointIdentity);
        if (concurrentMap == null && (putIfAbsent = this.mAverageIntervalsByEndpoint.putIfAbsent(endpointIdentity, (concurrentMap = new ConcurrentHashMap<>(Measurements.values().length)))) != null) {
            concurrentMap = putIfAbsent;
        }
        DecayingCount decayingCount = concurrentMap.get(measurements);
        if (decayingCount == null) {
            DecayingCount decayingCount2 = new DecayingCount();
            DecayingCount putIfAbsent2 = concurrentMap.putIfAbsent(measurements, decayingCount2);
            return putIfAbsent2 == null ? decayingCount2 : putIfAbsent2;
        }
        return decayingCount;
    }

    @Override // com.amazon.communication.socket.SocketUsageAggregatedReader
    public int countClosedSocketsToEndpoint(EndpointIdentity endpointIdentity) {
        return countMeasurementForEndpoint(endpointIdentity, Measurements.COUNT_SOCKETS_CLOSED_TO_ENDPOINT);
    }

    @Override // com.amazon.communication.socket.SocketUsageAggregatedReader
    public int countOpenedSocketsToEndpoint(EndpointIdentity endpointIdentity) {
        return countMeasurementForEndpoint(endpointIdentity, Measurements.COUNT_SOCKETS_OPENED_TO_ENDPOINT);
    }

    @Override // com.amazon.communication.socket.SocketUsageWriter
    public void writeTimestamp(Measurements measurements, EndpointIdentity endpointIdentity, long j) {
        if (measurements != null) {
            if (endpointIdentity == null) {
                throw new IllegalArgumentException("Argument: endpoint must not be null");
            }
            if (j >= 0) {
                findOrCreateDecayingCount(endpointIdentity, measurements).incrementCount(j);
                return;
            }
            throw new IllegalArgumentException("Argument: timestamp must be positive");
        }
        throw new IllegalArgumentException("Argument: measurement must not be null");
    }
}
