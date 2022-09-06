package com.amazon.alexa;

import com.amazon.alexa.client.core.configuration.Feature;
import com.amazon.alexa.client.metrics.core.MetricsStatusProvider;
import dagger.Lazy;
/* compiled from: DefaultMetricsStatusProvider.java */
/* loaded from: classes.dex */
public class taB implements MetricsStatusProvider {
    public final Feature BIo;
    public final boolean zQM;
    public final Lazy<gSO> zZm;

    public taB(Lazy<gSO> lazy, Feature feature) {
        this.zZm = lazy;
        this.BIo = feature;
        this.zQM = false;
    }

    @Override // com.amazon.alexa.client.metrics.core.MetricsStatusProvider
    public boolean isEnabled() {
        boolean zZm = this.zZm.mo358get().zZm(this.BIo);
        return this.zQM ? !zZm : zZm;
    }

    public taB(Lazy<gSO> lazy, Feature feature, boolean z) {
        this.zZm = lazy;
        this.BIo = feature;
        this.zQM = z;
    }
}
