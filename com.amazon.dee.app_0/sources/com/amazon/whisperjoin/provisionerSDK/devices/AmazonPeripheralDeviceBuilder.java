package com.amazon.whisperjoin.provisionerSDK.devices;

import com.amazon.whisperjoin.common.sharedtypes.devices.DiscoveredRadio;
import com.amazon.whisperjoin.common.sharedtypes.devices.WhisperJoinPeripheralDeviceDetails;
import com.amazon.whisperjoin.common.sharedtypes.utility.EncodingHelpers;
import com.amazon.whisperjoin.provisionerSDK.configuration.ConnectionConfiguration;
import com.amazon.whisperjoin.provisionerSDK.devices.helpers.DeviceRadioTransportHelperImpl;
import com.amazon.whisperjoin.provisionerSDK.devices.security.TrustNegotiatorProvider;
/* loaded from: classes13.dex */
public class AmazonPeripheralDeviceBuilder {
    private ConnectionConfiguration mConnectionConfiguration;
    private WhisperJoinPeripheralDeviceDetails mDeviceDetails;
    private DiscoveredRadio mDiscoveredRadio;
    private EncodingHelpers mEncodingHelpers;
    private TrustNegotiatorProvider mTrustNegotiatorProvider;

    public AmazonPeripheralDevice createAmazonPeripheralDevice() {
        if (this.mDeviceDetails != null) {
            ConnectionConfiguration connectionConfiguration = this.mConnectionConfiguration;
            if (connectionConfiguration != null) {
                DiscoveredRadio discoveredRadio = this.mDiscoveredRadio;
                if (discoveredRadio != null) {
                    return new AmazonPeripheralDevice(this.mDeviceDetails, this.mConnectionConfiguration, this.mEncodingHelpers, new DeviceRadioTransportHelperImpl(discoveredRadio, connectionConfiguration.getTransportProvider()), this.mTrustNegotiatorProvider);
                }
                throw new IllegalArgumentException("Preferred Radio must be set");
            }
            throw new IllegalArgumentException("Connection Configuration can not be null");
        }
        throw new IllegalArgumentException("DeviceDetails cannot be null");
    }

    public AmazonPeripheralDeviceBuilder setConnectionConfiguration(ConnectionConfiguration connectionConfiguration) {
        this.mConnectionConfiguration = connectionConfiguration;
        return this;
    }

    public AmazonPeripheralDeviceBuilder setDeviceDetails(WhisperJoinPeripheralDeviceDetails whisperJoinPeripheralDeviceDetails) {
        this.mDeviceDetails = whisperJoinPeripheralDeviceDetails;
        return this;
    }

    public AmazonPeripheralDeviceBuilder setDiscoveredRadio(DiscoveredRadio discoveredRadio) {
        this.mDiscoveredRadio = discoveredRadio;
        return this;
    }

    public AmazonPeripheralDeviceBuilder setEncodingHelpers(EncodingHelpers encodingHelpers) {
        this.mEncodingHelpers = encodingHelpers;
        return this;
    }

    public AmazonPeripheralDeviceBuilder setTrustNegotiatorProvider(TrustNegotiatorProvider trustNegotiatorProvider) {
        this.mTrustNegotiatorProvider = trustNegotiatorProvider;
        return this;
    }
}
