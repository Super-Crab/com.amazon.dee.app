package com.amazon.commscore.api.metrics;

import java.util.Map;
/* loaded from: classes12.dex */
public class OperationalMetric extends BaseMetric<OperationalMetric> {
    private OperationalMetricType type;

    public OperationalMetric(String str, String str2, String str3) {
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

    public OperationalMetricType getType() {
        return this.type;
    }

    public OperationalMetric ofType(OperationalMetricType operationalMetricType) {
        this.type = operationalMetricType;
        return this;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public OperationalMetric(String str, String str2, String str3, OperationalMetricType operationalMetricType) {
        super(str, str2, str3);
        this.type = operationalMetricType;
    }

    public OperationalMetric(String str, String str2, String str3, String str4) {
        super(str, str2, str3, str4);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public OperationalMetric(String str, String str2, String str3, String str4, OperationalMetricType operationalMetricType) {
        super(str, str2, str3, str4);
        this.type = operationalMetricType;
    }
}
