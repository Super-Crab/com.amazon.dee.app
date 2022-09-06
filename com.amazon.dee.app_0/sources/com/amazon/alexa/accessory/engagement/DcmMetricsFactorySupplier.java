package com.amazon.alexa.accessory.engagement;

import android.content.Context;
import androidx.annotation.NonNull;
import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.amazon.client.metrics.common.AndroidMetricsFactoryImpl;
import com.amazon.client.metrics.common.MetricsFactory;
/* loaded from: classes.dex */
final class DcmMetricsFactorySupplier extends SingletonSupplier<MetricsFactory> {
    private final Context context;
    private final EnvironmentAttributes environmentAttributes;

    /* JADX INFO: Access modifiers changed from: package-private */
    public DcmMetricsFactorySupplier(@NonNull EnvironmentAttributes environmentAttributes, @NonNull Context context) {
        Preconditions.notNull(environmentAttributes, "environmentAttributes");
        Preconditions.notNull(context, "context");
        this.environmentAttributes = environmentAttributes;
        this.context = context;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.alexa.accessory.engagement.SingletonSupplier
    /* renamed from: generate */
    public MetricsFactory mo304generate() {
        AndroidMetricsFactoryImpl.setDeviceType(this.context, this.environmentAttributes.getApplicationDeviceType());
        AndroidMetricsFactoryImpl.setDeviceId(this.context, this.environmentAttributes.getApplicationDeviceSerialNumber());
        AndroidMetricsFactoryImpl.setPreferredMarketplace(this.context, this.environmentAttributes.getPreferredMarketplaceId());
        return AndroidMetricsFactoryImpl.getInstance(this.context);
    }
}
