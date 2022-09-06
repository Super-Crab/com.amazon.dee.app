package com.amazon.whisperjoin.common.sharedtypes.devices.interfaces;

import java.util.concurrent.Future;
/* loaded from: classes13.dex */
public interface ConnectableDevice<TScanResults, TConfiguration, TNetwork, TConnectionDetails> {
    Future<Void> connectPeripheralNetwork(TNetwork tnetwork);

    Future<Void> connectPeripheralNetworkConfiguration(TConfiguration tconfiguration);

    Future<Void> deleteNetwork(TNetwork tnetwork);

    Future<Void> deleteNetworks();

    Future<TConnectionDetails> getConnectionInformation();

    Future<TScanResults> getVisibleNetworks(int i);

    Future<Void> saveNetwork(TConfiguration tconfiguration);

    Future<Void> startNetworkScan();
}
