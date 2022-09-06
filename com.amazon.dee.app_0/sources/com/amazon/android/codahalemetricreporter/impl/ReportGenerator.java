package com.amazon.android.codahalemetricreporter.impl;

import com.codahale.metrics.MetricFilter;
import com.codahale.metrics.MetricRegistry;
import java.io.IOException;
import java.io.OutputStream;
/* loaded from: classes11.dex */
public interface ReportGenerator {
    void generate(MetricRegistry metricRegistry, MetricFilter metricFilter, OutputStream outputStream) throws IOException;
}
