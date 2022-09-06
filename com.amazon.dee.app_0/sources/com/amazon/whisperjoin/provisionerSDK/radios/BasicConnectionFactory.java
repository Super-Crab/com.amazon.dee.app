package com.amazon.whisperjoin.provisionerSDK.radios;

import com.amazon.whisperjoin.common.sharedtypes.devices.DiscoveredRadio;
import com.amazon.whisperjoin.common.sharedtypes.devices.WhisperJoinPeripheralDeviceDetails;
import com.amazon.whisperjoin.common.sharedtypes.devices.interfaces.PeripheralDevice;
import com.amazon.whisperjoin.common.sharedtypes.protobuf.ProtocolBuffersSerializer;
import com.amazon.whisperjoin.common.sharedtypes.radios.ConnectionFactory;
import com.amazon.whisperjoin.common.sharedtypes.radios.RadioPreferences;
import com.amazon.whisperjoin.common.sharedtypes.utility.DiscoveryController;
import com.amazon.whisperjoin.provisionerSDK.configuration.ConnectionConfiguration;
import com.amazon.whisperjoin.provisionerSDK.devices.AmazonPeripheralDeviceBuilder;
import com.amazon.whisperjoin.provisionerSDK.devices.security.TrustNegotiatorProvider;
/* loaded from: classes13.dex */
public class BasicConnectionFactory implements ConnectionFactory {
    private final ConnectionConfiguration mConnectionConfiguration;

    public BasicConnectionFactory(ConnectionConfiguration connectionConfiguration) {
        if (connectionConfiguration != null) {
            this.mConnectionConfiguration = connectionConfiguration;
            return;
        }
        throw new IllegalArgumentException("connectionConfiguration can not be null");
    }

    @Override // com.amazon.whisperjoin.common.sharedtypes.radios.ConnectionFactory
    public PeripheralDevice create(WhisperJoinPeripheralDeviceDetails whisperJoinPeripheralDeviceDetails, DiscoveredRadio discoveredRadio) {
        return new AmazonPeripheralDeviceBuilder().setDeviceDetails(whisperJoinPeripheralDeviceDetails).setDiscoveredRadio(discoveredRadio).setConnectionConfiguration(this.mConnectionConfiguration).setTrustNegotiatorProvider(new TrustNegotiatorProvider(this.mConnectionConfiguration.getDSSClient(), new ProtocolBuffersSerializer())).createAmazonPeripheralDevice();
    }

    public PeripheralDevice create(WhisperJoinPeripheralDeviceDetails whisperJoinPeripheralDeviceDetails, RadioPreferences radioPreferences, DiscoveryController discoveryController) {
        return new AmazonPeripheralDeviceBuilder().setDeviceDetails(whisperJoinPeripheralDeviceDetails).setConnectionConfiguration(this.mConnectionConfiguration).setTrustNegotiatorProvider(new TrustNegotiatorProvider(this.mConnectionConfiguration.getDSSClient(), new ProtocolBuffersSerializer())).createAmazonPeripheralDevice();
    }
}
