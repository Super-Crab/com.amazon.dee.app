package com.amazon.maft.metrics;
/* loaded from: classes12.dex */
public class NullMetricsFactory implements MetricsFactory {

    /* loaded from: classes12.dex */
    public static class NullPmetMetrics implements PmetMetrics {
        @Override // com.amazon.maft.metrics.PmetMetrics
        public PmetMetrics addCount(String str, double d) {
            return this;
        }

        @Override // com.amazon.maft.metrics.PmetMetrics
        public PmetMetrics addProperty(String str, String str2) {
            return this;
        }

        @Override // com.amazon.maft.metrics.PmetMetrics
        public PmetMetrics addTime(String str, double d) {
            return this;
        }

        @Override // com.amazon.maft.metrics.PmetMetrics
        public void record() {
        }
    }

    @Override // com.amazon.maft.metrics.MetricsFactory
    /* renamed from: newPmetMetrics */
    public NullPmetMetrics mo4086newPmetMetrics(String str) {
        return new NullPmetMetrics();
    }
}
