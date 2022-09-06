package com.amazon.alexa.client.metrics.mobilytics;

import androidx.annotation.NonNull;
import com.amazon.alexa.client.core.configuration.ClientConfiguration;
import com.amazon.alexa.mobilytics.identity.MobilyticsDevice;
import com.amazon.alexa.mobilytics.identity.MobilyticsDeviceProvider;
/* loaded from: classes6.dex */
public class MobilyticsDeviceProviderImpl implements MobilyticsDeviceProvider {
    private final ClientConfiguration clientConfiguration;

    public MobilyticsDeviceProviderImpl(ClientConfiguration clientConfiguration) {
        this.clientConfiguration = clientConfiguration;
    }

    @Override // com.amazon.alexa.mobilytics.identity.MobilyticsDeviceProvider
    public void addListener(@NonNull MobilyticsDeviceProvider.Listener listener) {
    }

    @Override // com.amazon.alexa.mobilytics.identity.MobilyticsDeviceProvider
    public MobilyticsDevice device() {
        return MobilyticsDeviceImpl.builder().withDeviceType(this.clientConfiguration.getMetricsDeviceType()).build();
    }

    @Override // com.amazon.alexa.mobilytics.identity.MobilyticsDeviceProvider
    public void removeListener(@NonNull MobilyticsDeviceProvider.Listener listener) {
    }
}
