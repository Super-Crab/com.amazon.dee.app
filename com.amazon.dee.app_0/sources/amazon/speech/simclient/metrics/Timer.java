package amazon.speech.simclient.metrics;
/* loaded from: classes.dex */
public class Timer extends MetricsRecord<Timer> {
    private long mDuration = 0;

    Timer() {
    }

    public long getDuration() {
        return this.mDuration;
    }

    @Override // amazon.speech.simclient.metrics.MetricsRecord
    public void recycle() {
        this.mDuration = 0L;
        super.recycle();
    }

    public Timer setDuration(long j) {
        if (j >= 0) {
            this.mDuration = j;
            return this;
        }
        throw new IllegalArgumentException("negative duration");
    }
}
