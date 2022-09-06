package com.amazon.commscore.api.metrics;

import java.util.Map;
/* loaded from: classes12.dex */
public class InteractionMetric extends BaseMetric<InteractionMetric> {
    private InteractionMetricType type;

    public InteractionMetric(String str, String str2, String str3) {
        super(str, str2, str3);
    }

    @Override // com.amazon.commscore.api.metrics.BaseMetric
    public /* bridge */ /* synthetic */ String getComponent() {
        return super.getComponent();
    }

    @Override // com.amazon.commscore.api.metrics.BaseMetric
    public /* bridge */ /* synthetic */ Map getCustomEntries() {
        return super.getCustomEntries();
    }

    @Override // com.amazon.commscore.api.metrics.BaseMetric
    public /* bridge */ /* synthetic */ String getName() {
        return super.getName();
    }

    @Override // com.amazon.commscore.api.metrics.BaseMetric
    public /* bridge */ /* synthetic */ String getOwnerIdentifier() {
        return super.getOwnerIdentifier();
    }

    @Override // com.amazon.commscore.api.metrics.BaseMetric
    public /* bridge */ /* synthetic */ String getSubComponent() {
        return super.getSubComponent();
    }

    public InteractionMetricType getType() {
        return this.type;
    }

    public InteractionMetric ofType(InteractionMetricType interactionMetricType) {
        this.type = interactionMetricType;
        return this;
    }

    public InteractionMetric(String str, String str2, String str3, String str4) {
        super(str, str2, str3, str4);
    }
}
