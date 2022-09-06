package com.amazon.client.metrics.common;
/* loaded from: classes11.dex */
public class MetricsException extends Exception {
    private static final long serialVersionUID = -1411806957109054819L;

    public MetricsException() {
    }

    public MetricsException(String str) {
        super(str);
    }

    public MetricsException(Throwable th) {
        super(th);
    }

    public MetricsException(String str, Throwable th) {
        super(str, th);
    }
}
