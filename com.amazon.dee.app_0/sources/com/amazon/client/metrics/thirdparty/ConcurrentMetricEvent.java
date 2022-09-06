package com.amazon.client.metrics.thirdparty;

import com.amazon.client.metrics.thirdparty.internal.BasicMetricEvent;
import java.util.List;
import java.util.Map;
/* loaded from: classes11.dex */
public class ConcurrentMetricEvent implements MetricEvent {
    private final Object mCounterLock;
    private final Object mDVLock;
    private final MetricEvent mDelegate;
    private final Object mTimerLock;

    public ConcurrentMetricEvent(String str, String str2) {
        this(str, str2, MetricEventType.getDefault());
    }

    @Override // com.amazon.client.metrics.thirdparty.MetricEvent
    public void addCounter(String str, double d) {
        synchronized (this.mCounterLock) {
            this.mDelegate.addCounter(str, d);
        }
    }

    @Override // com.amazon.client.metrics.thirdparty.MetricEvent
    public void addDataPoint(DataPoint dataPoint) throws MetricsException {
        synchronized (this.mCounterLock) {
            synchronized (this.mTimerLock) {
                synchronized (this.mDVLock) {
                    this.mDelegate.addDataPoint(dataPoint);
                }
            }
        }
    }

    @Override // com.amazon.client.metrics.thirdparty.MetricEvent
    public void addDataPoints(List<DataPoint> list) throws MetricsException {
        synchronized (this.mCounterLock) {
            synchronized (this.mTimerLock) {
                synchronized (this.mDVLock) {
                    this.mDelegate.addDataPoints(list);
                }
            }
        }
    }

    @Override // com.amazon.client.metrics.thirdparty.MetricEvent
    public void addString(String str, String str2) {
        synchronized (this.mDVLock) {
            this.mDelegate.addString(str, str2);
        }
    }

    @Override // com.amazon.client.metrics.thirdparty.MetricEvent
    public void addTimer(String str, double d) {
        synchronized (this.mTimerLock) {
            this.mDelegate.addTimer(str, d);
        }
    }

    @Override // com.amazon.client.metrics.thirdparty.MetricEvent
    public void appendString(String str, String str2) {
        synchronized (this.mDVLock) {
            this.mDelegate.appendString(str, str2);
        }
    }

    @Override // com.amazon.client.metrics.thirdparty.MetricEvent
    public void clear() {
        synchronized (this.mCounterLock) {
            synchronized (this.mTimerLock) {
                synchronized (this.mDVLock) {
                    this.mDelegate.clear();
                }
            }
        }
    }

    @Override // com.amazon.client.metrics.thirdparty.MetricEvent
    public boolean getAnonymous() {
        return this.mDelegate.getAnonymous();
    }

    @Override // com.amazon.client.metrics.thirdparty.MetricEvent
    public List<DataPoint> getAsDataPoints() {
        List<DataPoint> asDataPoints;
        synchronized (this.mCounterLock) {
            synchronized (this.mTimerLock) {
                synchronized (this.mDVLock) {
                    asDataPoints = this.mDelegate.getAsDataPoints();
                }
            }
        }
        return asDataPoints;
    }

    public List<DataPoint> getAsDataPointsAndClear() {
        List<DataPoint> asDataPoints;
        synchronized (this.mCounterLock) {
            synchronized (this.mTimerLock) {
                synchronized (this.mDVLock) {
                    asDataPoints = this.mDelegate.getAsDataPoints();
                    this.mDelegate.clear();
                }
            }
        }
        return asDataPoints;
    }

    @Override // com.amazon.client.metrics.thirdparty.MetricEvent
    public MetricEventType getMetricEventType() {
        return this.mDelegate.getMetricEventType();
    }

    @Override // com.amazon.client.metrics.thirdparty.MetricEvent
    public String getNonAnonymousCustomerId() {
        return this.mDelegate.getNonAnonymousCustomerId();
    }

    @Override // com.amazon.client.metrics.thirdparty.MetricEvent
    public String getNonAnonymousSessionId() {
        return this.mDelegate.getNonAnonymousSessionId();
    }

    @Override // com.amazon.client.metrics.thirdparty.MetricEvent
    public String getProgram() {
        return this.mDelegate.getProgram();
    }

    @Override // com.amazon.client.metrics.thirdparty.MetricEvent
    public String getSource() {
        return this.mDelegate.getSource();
    }

    @Override // com.amazon.client.metrics.thirdparty.MetricEvent
    public boolean hasDataPoints() {
        boolean hasDataPoints;
        synchronized (this.mCounterLock) {
            synchronized (this.mTimerLock) {
                synchronized (this.mDVLock) {
                    hasDataPoints = this.mDelegate.hasDataPoints();
                }
            }
        }
        return hasDataPoints;
    }

    @Override // com.amazon.client.metrics.thirdparty.MetricEvent
    public void incrementCounter(String str, double d) {
        synchronized (this.mCounterLock) {
            this.mDelegate.incrementCounter(str, d);
        }
    }

    @Override // com.amazon.client.metrics.thirdparty.MetricEvent
    public void removeCounter(String str) {
        synchronized (this.mCounterLock) {
            this.mDelegate.removeCounter(str);
        }
    }

    @Override // com.amazon.client.metrics.thirdparty.MetricEvent
    public void removeString(String str) {
        synchronized (this.mDVLock) {
            this.mDelegate.removeString(str);
        }
    }

    @Override // com.amazon.client.metrics.thirdparty.MetricEvent
    public void removeTimer(String str) {
        synchronized (this.mTimerLock) {
            this.mDelegate.removeTimer(str);
        }
    }

    @Override // com.amazon.client.metrics.thirdparty.MetricEvent
    public void restoreFromMap(Map<String, String> map) {
        synchronized (this.mCounterLock) {
            synchronized (this.mTimerLock) {
                synchronized (this.mDVLock) {
                    this.mDelegate.restoreFromMap(map);
                }
            }
        }
    }

    @Override // com.amazon.client.metrics.thirdparty.MetricEvent
    public void saveToMap(Map<String, String> map) {
        synchronized (this.mCounterLock) {
            synchronized (this.mTimerLock) {
                synchronized (this.mDVLock) {
                    this.mDelegate.saveToMap(map);
                }
            }
        }
    }

    @Override // com.amazon.client.metrics.thirdparty.MetricEvent
    public void setAnonymous(boolean z) {
        synchronized (this.mDVLock) {
            this.mDelegate.setAnonymous(z);
        }
    }

    @Override // com.amazon.client.metrics.thirdparty.MetricEvent
    public void setClickstreamUserAgent(String str) {
        synchronized (this.mDVLock) {
            this.mDelegate.setClickstreamUserAgent(str);
        }
    }

    @Override // com.amazon.client.metrics.thirdparty.MetricEvent
    public void setNonAnonymousCustomerId(String str) {
        synchronized (this.mDVLock) {
            this.mDelegate.setNonAnonymousCustomerId(str);
        }
    }

    @Override // com.amazon.client.metrics.thirdparty.MetricEvent
    public void setNonAnonymousSessionId(String str) {
        synchronized (this.mDVLock) {
            this.mDelegate.setNonAnonymousSessionId(str);
        }
    }

    @Override // com.amazon.client.metrics.thirdparty.MetricEvent
    public void startTimer(String str) {
        synchronized (this.mTimerLock) {
            this.mDelegate.startTimer(str);
        }
    }

    @Override // com.amazon.client.metrics.thirdparty.MetricEvent
    public void stopTimer(String str) {
        synchronized (this.mTimerLock) {
            this.mDelegate.stopTimer(str);
        }
    }

    public String toString() {
        String obj;
        synchronized (this.mCounterLock) {
            synchronized (this.mTimerLock) {
                synchronized (this.mDVLock) {
                    obj = this.mDelegate.toString();
                }
            }
        }
        return obj;
    }

    public ConcurrentMetricEvent(String str, String str2, MetricEventType metricEventType) {
        this(str, str2, metricEventType, false);
    }

    public ConcurrentMetricEvent(String str, String str2, MetricEventType metricEventType, boolean z) {
        this.mCounterLock = new Object();
        this.mTimerLock = new Object();
        this.mDVLock = new Object();
        this.mDelegate = new BasicMetricEvent(str, str2, metricEventType, z);
    }

    @Override // com.amazon.client.metrics.thirdparty.MetricEvent
    public void addTimer(String str, double d, int i) {
        synchronized (this.mTimerLock) {
            this.mDelegate.addTimer(str, d, i);
        }
    }

    protected ConcurrentMetricEvent(MetricEvent metricEvent) {
        this.mCounterLock = new Object();
        this.mTimerLock = new Object();
        this.mDVLock = new Object();
        this.mDelegate = metricEvent;
    }
}
