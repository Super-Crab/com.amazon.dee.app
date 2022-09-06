package com.amazon.whisperjoin.provisionerSDK.devices;

import com.amazon.whisperbridge.constants.Command;
import com.amazon.whisperjoin.common.sharedtypes.devices.WhisperJoinPeripheralDeviceDetails;
import com.amazon.whisperjoin.common.sharedtypes.devices.interfaces.Configuration;
import com.amazon.whisperjoin.common.sharedtypes.devices.interfaces.Locale;
import com.amazon.whisperjoin.common.sharedtypes.devices.interfaces.Registration;
import com.amazon.whisperjoin.common.sharedtypes.devices.interfaces.WifiConnectableDevice;
import com.amazon.whisperjoin.common.sharedtypes.protobuf.ProtocolBuffersSerializer;
import com.amazon.whisperjoin.common.sharedtypes.provisioning.data.configuration.ConfigurationKeySet;
import com.amazon.whisperjoin.common.sharedtypes.provisioning.data.configuration.DataMap;
import com.amazon.whisperjoin.common.sharedtypes.provisioning.data.locale.LocaleConfiguration;
import com.amazon.whisperjoin.common.sharedtypes.provisioning.data.registration.CBLRegistrationDetails;
import com.amazon.whisperjoin.common.sharedtypes.provisioning.data.registration.CBLRegistrationRequest;
import com.amazon.whisperjoin.common.sharedtypes.provisioning.data.wifi.WifiConfiguration;
import com.amazon.whisperjoin.common.sharedtypes.provisioning.data.wifi.WifiConnectionDetails;
import com.amazon.whisperjoin.common.sharedtypes.provisioning.data.wifi.WifiNetwork;
import com.amazon.whisperjoin.common.sharedtypes.provisioning.data.wifi.WifiScanResultCollection;
import com.amazon.whisperjoin.common.sharedtypes.utility.EncodingHelpers;
import com.amazon.whisperjoin.provisionerSDK.configuration.ConnectionConfiguration;
import com.amazon.whisperjoin.provisionerSDK.devices.helpers.DeviceRadioTransportHelper;
import com.amazon.whisperjoin.provisionerSDK.devices.security.TrustNegotiatorProvider;
import com.google.common.reflect.TypeToken;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
/* loaded from: classes13.dex */
public class AmazonPeripheralDevice extends BasicPeripheralDevice implements Configuration, Registration, WifiConnectableDevice, Locale {
    /* JADX INFO: Access modifiers changed from: package-private */
    public AmazonPeripheralDevice(WhisperJoinPeripheralDeviceDetails whisperJoinPeripheralDeviceDetails, ConnectionConfiguration connectionConfiguration, EncodingHelpers encodingHelpers, DeviceRadioTransportHelper deviceRadioTransportHelper, TrustNegotiatorProvider trustNegotiatorProvider) {
        super(whisperJoinPeripheralDeviceDetails, connectionConfiguration, encodingHelpers, new ProtocolBuffersSerializer(), deviceRadioTransportHelper, trustNegotiatorProvider);
    }

    @Override // com.amazon.whisperjoin.common.sharedtypes.devices.interfaces.Configuration
    public Future<Void> addConfiguration(final DataMap dataMap) {
        if (dataMap != null && !dataMap.isEmpty()) {
            return this.mExecutorService.submit(new Callable<Void>() { // from class: com.amazon.whisperjoin.provisionerSDK.devices.AmazonPeripheralDevice.9
                @Override // java.util.concurrent.Callable
                public Void call() throws Exception {
                    AmazonPeripheralDevice.this.executeOperation(Command.SET_CONFIGURATION, dataMap, null);
                    return null;
                }
            });
        }
        throw new IllegalArgumentException("configuration can not be null");
    }

    @Override // com.amazon.whisperjoin.common.sharedtypes.devices.interfaces.Locale
    public Future<Void> clearLocaleConfiguration() {
        return deleteConfiguration(LocaleConfiguration.getConfigurationKeySet());
    }

    @Override // com.amazon.whisperjoin.common.sharedtypes.devices.interfaces.Registration
    public Future<Void> clearRegistrationConfigurations() {
        return this.mExecutorService.submit(new Callable<Void>() { // from class: com.amazon.whisperjoin.provisionerSDK.devices.AmazonPeripheralDevice.14
            @Override // java.util.concurrent.Callable
            public Void call() throws Exception {
                AmazonPeripheralDevice.this.executeOperation(Command.DELETE_REGISTRATION_CONFIGURATIONS, null, null);
                return null;
            }
        });
    }

    @Override // com.amazon.whisperjoin.common.sharedtypes.devices.interfaces.Configuration
    public Future<Void> deleteConfiguration() {
        return this.mExecutorService.submit(new Callable<Void>() { // from class: com.amazon.whisperjoin.provisionerSDK.devices.AmazonPeripheralDevice.11
            @Override // java.util.concurrent.Callable
            public Void call() throws Exception {
                AmazonPeripheralDevice.this.executeOperation(Command.DELETE_CONFIGURATION, null, null);
                return null;
            }
        });
    }

    @Override // com.amazon.whisperjoin.common.sharedtypes.devices.interfaces.ConnectableDevice
    public Future<Void> deleteNetworks() {
        return this.mExecutorService.submit(new Callable<Void>() { // from class: com.amazon.whisperjoin.provisionerSDK.devices.AmazonPeripheralDevice.4
            @Override // java.util.concurrent.Callable
            public Void call() throws Exception {
                AmazonPeripheralDevice.this.executeOperation(Command.DELETE_ALL_NETWORKS, null, null);
                return null;
            }
        });
    }

    @Override // com.amazon.whisperjoin.common.sharedtypes.devices.interfaces.Configuration
    public Future<DataMap> getConfiguration() {
        return this.mExecutorService.submit(new Callable<DataMap>() { // from class: com.amazon.whisperjoin.provisionerSDK.devices.AmazonPeripheralDevice.10
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // java.util.concurrent.Callable
            /* renamed from: call */
            public DataMap mo6625call() throws Exception {
                return (DataMap) AmazonPeripheralDevice.this.executeOperation(Command.GET_CONFIGURATION, null, new TypeToken<DataMap>() { // from class: com.amazon.whisperjoin.provisionerSDK.devices.AmazonPeripheralDevice.10.1
                });
            }
        });
    }

    @Override // com.amazon.whisperjoin.common.sharedtypes.devices.interfaces.ConnectableDevice
    public Future<WifiConnectionDetails> getConnectionInformation() {
        return this.mExecutorService.submit(new Callable<WifiConnectionDetails>() { // from class: com.amazon.whisperjoin.provisionerSDK.devices.AmazonPeripheralDevice.8
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // java.util.concurrent.Callable
            /* renamed from: call */
            public WifiConnectionDetails mo6628call() throws Exception {
                return (WifiConnectionDetails) AmazonPeripheralDevice.this.executeOperation(Command.GET_CONNECTION_STATUS, null, new TypeToken<WifiConnectionDetails>() { // from class: com.amazon.whisperjoin.provisionerSDK.devices.AmazonPeripheralDevice.8.1
                });
            }
        });
    }

    @Override // com.amazon.whisperjoin.common.sharedtypes.devices.interfaces.Registration
    public Future<CBLRegistrationDetails> getRegistrationTokenStatus() {
        return this.mExecutorService.submit(new Callable<CBLRegistrationDetails>() { // from class: com.amazon.whisperjoin.provisionerSDK.devices.AmazonPeripheralDevice.13
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // java.util.concurrent.Callable
            /* renamed from: call */
            public CBLRegistrationDetails mo6626call() throws Exception {
                return (CBLRegistrationDetails) AmazonPeripheralDevice.this.executeOperation(Command.GET_REGISTRATION_INFORMATION, null, new TypeToken<CBLRegistrationDetails>() { // from class: com.amazon.whisperjoin.provisionerSDK.devices.AmazonPeripheralDevice.13.1
                });
            }
        });
    }

    @Override // com.amazon.whisperjoin.common.sharedtypes.devices.interfaces.ConnectableDevice
    public Future<WifiScanResultCollection> getVisibleNetworks(final int i) {
        return this.mExecutorService.submit(new Callable<WifiScanResultCollection>() { // from class: com.amazon.whisperjoin.provisionerSDK.devices.AmazonPeripheralDevice.2
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // java.util.concurrent.Callable
            /* renamed from: call */
            public WifiScanResultCollection mo6627call() throws Exception {
                return (WifiScanResultCollection) AmazonPeripheralDevice.this.executeOperation(Command.GET_VISIBLE_NETWORKS, Integer.valueOf(i), new TypeToken<WifiScanResultCollection>() { // from class: com.amazon.whisperjoin.provisionerSDK.devices.AmazonPeripheralDevice.2.1
                });
            }
        });
    }

    @Override // com.amazon.whisperjoin.common.sharedtypes.devices.interfaces.Locale
    public Future<Void> setLocaleConfiguration(LocaleConfiguration localeConfiguration) {
        if (localeConfiguration != null) {
            return addConfiguration(localeConfiguration.buildDataMap());
        }
        throw new IllegalArgumentException("localeConfigration can not be null");
    }

    @Override // com.amazon.whisperjoin.common.sharedtypes.devices.interfaces.Registration
    public Future<Void> setRegistrationToken(final CBLRegistrationRequest cBLRegistrationRequest) {
        if (cBLRegistrationRequest != null) {
            return this.mExecutorService.submit(new Callable<Void>() { // from class: com.amazon.whisperjoin.provisionerSDK.devices.AmazonPeripheralDevice.15
                @Override // java.util.concurrent.Callable
                public Void call() throws Exception {
                    AmazonPeripheralDevice.this.executeOperation(Command.SET_REGISTRATION_CONFIGURATION, cBLRegistrationRequest, null);
                    return null;
                }
            });
        }
        throw new IllegalArgumentException("registrationToken can not be null");
    }

    @Override // com.amazon.whisperjoin.common.sharedtypes.devices.interfaces.ConnectableDevice
    public Future<Void> startNetworkScan() {
        return this.mExecutorService.submit(new Callable<Void>() { // from class: com.amazon.whisperjoin.provisionerSDK.devices.AmazonPeripheralDevice.1
            @Override // java.util.concurrent.Callable
            public Void call() throws Exception {
                AmazonPeripheralDevice.this.executeOperation(Command.START_NETWORK_SCAN, null, null);
                return null;
            }
        });
    }

    @Override // com.amazon.whisperjoin.common.sharedtypes.devices.interfaces.ConnectableDevice
    public Future<Void> connectPeripheralNetwork(final WifiNetwork wifiNetwork) {
        return this.mExecutorService.submit(new Callable<Void>() { // from class: com.amazon.whisperjoin.provisionerSDK.devices.AmazonPeripheralDevice.6
            @Override // java.util.concurrent.Callable
            public Void call() throws Exception {
                AmazonPeripheralDevice.this.executeOperation(Command.CONNECT_TO_PROVIDED_NETWORK, wifiNetwork, null);
                return null;
            }
        });
    }

    @Override // com.amazon.whisperjoin.common.sharedtypes.devices.interfaces.ConnectableDevice
    public Future<Void> connectPeripheralNetworkConfiguration(final WifiConfiguration wifiConfiguration) {
        return this.mExecutorService.submit(new Callable<Void>() { // from class: com.amazon.whisperjoin.provisionerSDK.devices.AmazonPeripheralDevice.7
            @Override // java.util.concurrent.Callable
            public Void call() throws Exception {
                AmazonPeripheralDevice.this.executeOperation(Command.CONNECT_AND_SAVE_PROVIDED_NETWORK, wifiConfiguration, null);
                return null;
            }
        });
    }

    @Override // com.amazon.whisperjoin.common.sharedtypes.devices.interfaces.Configuration
    public Future<Void> deleteConfiguration(final ConfigurationKeySet configurationKeySet) {
        return this.mExecutorService.submit(new Callable<Void>() { // from class: com.amazon.whisperjoin.provisionerSDK.devices.AmazonPeripheralDevice.12
            @Override // java.util.concurrent.Callable
            public Void call() throws Exception {
                AmazonPeripheralDevice.this.executeOperation(Command.DELETE_CONFIGURATION_SET, configurationKeySet, null);
                return null;
            }
        });
    }

    @Override // com.amazon.whisperjoin.common.sharedtypes.devices.interfaces.ConnectableDevice
    public Future<Void> deleteNetwork(final WifiNetwork wifiNetwork) {
        return this.mExecutorService.submit(new Callable<Void>() { // from class: com.amazon.whisperjoin.provisionerSDK.devices.AmazonPeripheralDevice.5
            @Override // java.util.concurrent.Callable
            public Void call() throws Exception {
                AmazonPeripheralDevice.this.executeOperation(Command.DELETE_NETWORK, wifiNetwork, null);
                return null;
            }
        });
    }

    @Override // com.amazon.whisperjoin.common.sharedtypes.devices.interfaces.ConnectableDevice
    public Future<Void> saveNetwork(final WifiConfiguration wifiConfiguration) {
        return this.mExecutorService.submit(new Callable<Void>() { // from class: com.amazon.whisperjoin.provisionerSDK.devices.AmazonPeripheralDevice.3
            @Override // java.util.concurrent.Callable
            public Void call() throws Exception {
                AmazonPeripheralDevice.this.executeOperation(Command.SAVE_NETWORK, wifiConfiguration, null);
                return null;
            }
        });
    }
}
