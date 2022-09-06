package com.amazonaws.services.s3.model.metrics;

import java.io.Serializable;
/* loaded from: classes13.dex */
public abstract class MetricsFilterPredicate implements Serializable {
    public abstract void accept(MetricsPredicateVisitor metricsPredicateVisitor);
}
