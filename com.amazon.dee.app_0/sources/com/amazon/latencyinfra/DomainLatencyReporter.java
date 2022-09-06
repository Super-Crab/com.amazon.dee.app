package com.amazon.latencyinfra;
/* loaded from: classes12.dex */
public abstract class DomainLatencyReporter implements LatencyReporter {
    private static final LatencyReporterType type = LatencyReporterType.NAMESPACE;
    private final String namespace;

    public DomainLatencyReporter(String str) {
        if (str != null && str.length() != 0) {
            this.namespace = str;
            return;
        }
        throw new IllegalArgumentException("[Latency Infra]: namespace should not be empty for domain reporter.");
    }

    public String getNamespace() {
        return this.namespace;
    }

    @Override // com.amazon.latencyinfra.LatencyReporter
    public LatencyReporterType getType() {
        return type;
    }
}
