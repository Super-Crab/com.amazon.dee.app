package amazon.speech.simclient.metrics;
/* loaded from: classes.dex */
public class Counter extends MetricsRecord<Counter> {
    private long mCount = 0;

    Counter() {
    }

    public long getCount() {
        return this.mCount;
    }

    public Counter increment(long j) {
        this.mCount += j;
        return this;
    }

    @Override // amazon.speech.simclient.metrics.MetricsRecord
    public void recycle() {
        this.mCount = 0L;
        super.recycle();
    }

    public Counter setCount(long j) {
        this.mCount = j;
        return this;
    }
}
