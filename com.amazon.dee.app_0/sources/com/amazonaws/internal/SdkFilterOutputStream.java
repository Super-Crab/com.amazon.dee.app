package com.amazonaws.internal;

import java.io.FilterOutputStream;
import java.io.OutputStream;
@Deprecated
/* loaded from: classes13.dex */
public class SdkFilterOutputStream extends FilterOutputStream implements MetricAware {
    public SdkFilterOutputStream(OutputStream outputStream) {
        super(outputStream);
    }

    @Override // com.amazonaws.internal.MetricAware
    public boolean isMetricActivated() {
        OutputStream outputStream = ((FilterOutputStream) this).out;
        if (outputStream instanceof MetricAware) {
            return ((MetricAware) outputStream).isMetricActivated();
        }
        return false;
    }
}
