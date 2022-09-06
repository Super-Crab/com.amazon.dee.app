package amazon.speech.simclient.metrics.dcmadapter;
/* loaded from: classes.dex */
public interface MetricEventInterface {
    void addCounter(String str, double d);

    void addString(String str, String str2);

    void addTimer(String str, double d);

    void addTimer(String str, double d, int i);

    void clear();

    Object getDcmMetricEvent();

    void incrementCounter(String str, double d);

    void removeTimer(String str);

    void startTimer(String str);

    void stopTimer(String str);
}
