package com.amazon.android.codahalemetricreporter.impl;

import com.codahale.metrics.Metric;
import com.codahale.metrics.MetricFilter;
import java.util.regex.Pattern;
/* loaded from: classes11.dex */
public class RegexMetricFilter implements MetricFilter {
    private final Pattern mPattern;

    public RegexMetricFilter(String str) {
        this.mPattern = Pattern.compile(str);
    }

    @Override // com.codahale.metrics.MetricFilter
    public boolean matches(String str, Metric metric) {
        return this.mPattern.matcher(str).matches();
    }
}
