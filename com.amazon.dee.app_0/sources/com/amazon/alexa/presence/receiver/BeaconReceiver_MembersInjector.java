package com.amazon.alexa.presence.receiver;

import com.amazon.alexa.presence.detection.BlePacketConsumer;
import com.dee.app.metrics.MetricsServiceV2;
import dagger.MembersInjector;
import javax.inject.Provider;
/* loaded from: classes9.dex */
public final class BeaconReceiver_MembersInjector implements MembersInjector<BeaconReceiver> {
    private final Provider<BlePacketConsumer> mBlePacketConsumerProvider;
    private final Provider<MetricsServiceV2> mMetricsServiceV2Provider;

    public BeaconReceiver_MembersInjector(Provider<BlePacketConsumer> provider, Provider<MetricsServiceV2> provider2) {
        this.mBlePacketConsumerProvider = provider;
        this.mMetricsServiceV2Provider = provider2;
    }

    public static MembersInjector<BeaconReceiver> create(Provider<BlePacketConsumer> provider, Provider<MetricsServiceV2> provider2) {
        return new BeaconReceiver_MembersInjector(provider, provider2);
    }

    public static void injectMBlePacketConsumer(BeaconReceiver beaconReceiver, BlePacketConsumer blePacketConsumer) {
        beaconReceiver.mBlePacketConsumer = blePacketConsumer;
    }

    public static void injectMMetricsServiceV2(BeaconReceiver beaconReceiver, MetricsServiceV2 metricsServiceV2) {
        beaconReceiver.mMetricsServiceV2 = metricsServiceV2;
    }

    @Override // dagger.MembersInjector
    public void injectMembers(BeaconReceiver beaconReceiver) {
        injectMBlePacketConsumer(beaconReceiver, this.mBlePacketConsumerProvider.mo10268get());
        injectMMetricsServiceV2(beaconReceiver, this.mMetricsServiceV2Provider.mo10268get());
    }
}
