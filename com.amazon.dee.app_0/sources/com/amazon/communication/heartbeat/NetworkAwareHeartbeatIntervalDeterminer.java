package com.amazon.communication.heartbeat;

import com.amazon.communication.ConnectivityChangedHandler;
import com.amazon.communication.ConnectivityManagerWrapper;
import com.amazon.communication.ConnectivityMonitor;
import com.amazon.communication.NetworkType;
import com.amazon.communication.heartbeat.HeartbeatControlMessage;
import com.amazon.communication.heartbeat.HeartbeatControlMessageHandler;
import com.amazon.communication.heartbeat.store.HeartbeatIntervalDeterminerStore;
import com.amazon.communication.wifi.WifiManagerWrapper;
import com.amazon.dp.logger.DPLogger;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
/* loaded from: classes12.dex */
public class NetworkAwareHeartbeatIntervalDeterminer implements HeartbeatIntervalDeterminer, ConnectionHealthStatisticsAggregator, ConnectivityChangedHandler {
    private static final String METRICS_SOURCE_NAME = "Heartbeats";
    private static final DPLogger log = new DPLogger("TComm.NetworkAwareHeartbeatIntervalDeterminer");
    private final ConnectivityManagerWrapper mConnectivityManager;
    private final ConnectivityMonitor mConnectivityMonitor;
    protected NetworkType mCurrentNetworkType;
    private final HeartbeatControlMessageHandler mHeartbeatControlMessageHandler;
    protected final Map<NetworkType, HeartbeatIntervalDeterminer> mHeartbeatIntervalDeterminers = new ConcurrentHashMap(NetworkType.values().length);
    protected final Map<NetworkType, ConnectionHealthStatisticsAggregator> mConnectionHealthStatsAggregators = new ConcurrentHashMap(NetworkType.values().length);
    private String mTowerMccMnc = null;

    /* loaded from: classes12.dex */
    private class TriggerLearningCommandInvoker implements HeartbeatControlMessageHandler.HeartbeatCommandInvoker {
        private TriggerLearningCommandInvoker() {
        }

        @Override // com.amazon.communication.heartbeat.HeartbeatControlMessageHandler.HeartbeatCommandInvoker
        public void execute(HeartbeatControlMessage heartbeatControlMessage) {
            if (!(heartbeatControlMessage instanceof TriggerLearningCommand)) {
                NetworkAwareHeartbeatIntervalDeterminer.log.warn("TriggerLearningCommandInvoker.execute", "can't execute non-TriggerLearningCommand heartbeat control message", "controlMessage", heartbeatControlMessage);
                throw new IllegalArgumentException("Can't process non-TriggerLearningCommand message: " + heartbeatControlMessage);
            }
            TriggerLearningCommand triggerLearningCommand = (TriggerLearningCommand) heartbeatControlMessage;
            HeartbeatIntervalDeterminer heartbeatIntervalDeterminer = NetworkAwareHeartbeatIntervalDeterminer.this.mHeartbeatIntervalDeterminers.get(triggerLearningCommand.getNetworkType());
            heartbeatIntervalDeterminer.forceLearningMode(triggerLearningCommand);
            NetworkAwareHeartbeatIntervalDeterminer.log.info("TriggerLearningCommandInvoker.execute", "forced learning mode", "triggerLearningCommand", triggerLearningCommand, "heartbeatIntervalDeterminer", heartbeatIntervalDeterminer);
        }
    }

    public NetworkAwareHeartbeatIntervalDeterminer(ConnectivityManagerWrapper connectivityManagerWrapper, ConnectivityMonitor connectivityMonitor, WifiManagerWrapper wifiManagerWrapper, HeartbeatIntervalDeterminerStore heartbeatIntervalDeterminerStore, HeartbeatControlMessageHandler heartbeatControlMessageHandler) {
        this.mCurrentNetworkType = NetworkType.WIFI;
        this.mConnectivityManager = connectivityManagerWrapper;
        this.mConnectivityMonitor = connectivityMonitor;
        this.mHeartbeatControlMessageHandler = heartbeatControlMessageHandler;
        WifiHeartbeatIntervalDeterminer createWifiHeartbeatIntervalDeterminer = createWifiHeartbeatIntervalDeterminer(wifiManagerWrapper, heartbeatIntervalDeterminerStore);
        this.mHeartbeatIntervalDeterminers.put(NetworkType.WIFI, createWifiHeartbeatIntervalDeterminer);
        this.mConnectionHealthStatsAggregators.put(NetworkType.WIFI, createWifiHeartbeatIntervalDeterminer);
        AdaptiveHeartbeatIntervalDeterminer createMobileHeartbeatIntervalDeterminer = createMobileHeartbeatIntervalDeterminer();
        this.mHeartbeatIntervalDeterminers.put(NetworkType.MOBILE, createMobileHeartbeatIntervalDeterminer);
        this.mConnectionHealthStatsAggregators.put(NetworkType.MOBILE, createMobileHeartbeatIntervalDeterminer);
        StaticHeartbeatIntervalDeterminer staticHeartbeatIntervalDeterminer = new StaticHeartbeatIntervalDeterminer();
        this.mHeartbeatIntervalDeterminers.put(NetworkType.ETHERNET, staticHeartbeatIntervalDeterminer);
        this.mConnectionHealthStatsAggregators.put(NetworkType.ETHERNET, staticHeartbeatIntervalDeterminer);
        if (this.mConnectivityMonitor.isEthernetAvailable()) {
            this.mCurrentNetworkType = NetworkType.ETHERNET;
        } else if (this.mConnectivityMonitor.isMobileAvailable()) {
            this.mCurrentNetworkType = NetworkType.MOBILE;
        } else {
            this.mCurrentNetworkType = NetworkType.WIFI;
        }
        this.mConnectivityMonitor.registerConnectivityChangedHandler(this);
        this.mHeartbeatControlMessageHandler.registerInvoker(HeartbeatControlMessage.Type.TriggerLearning, new TriggerLearningCommandInvoker());
    }

    private void updateIntervalLimitsOverWanIfNeeded() {
        String str = this.mTowerMccMnc;
        if (str == null || str.equals(this.mConnectivityManager.getTowerCountryAndNetworkCodes())) {
            return;
        }
        this.mTowerMccMnc = this.mConnectivityManager.getTowerCountryAndNetworkCodes();
        ((AdaptiveHeartbeatIntervalDeterminer) this.mHeartbeatIntervalDeterminers.get(NetworkType.MOBILE)).updateIntervalLimits();
    }

    @Override // com.amazon.communication.heartbeat.HeartbeatIntervalDeterminer
    public void addHeartbeatIntervalUpdatesListener(HeartbeatIntervalUpdatesListener heartbeatIntervalUpdatesListener) {
        for (NetworkType networkType : NetworkType.values()) {
            this.mHeartbeatIntervalDeterminers.get(networkType).addHeartbeatIntervalUpdatesListener(heartbeatIntervalUpdatesListener);
        }
    }

    protected AdaptiveHeartbeatIntervalDeterminer createMobileHeartbeatIntervalDeterminer() {
        String simCountryAndNetworkCodes = this.mConnectivityManager.getSimCountryAndNetworkCodes();
        String towerCountryAndNetworkCodes = this.mConnectivityManager.getTowerCountryAndNetworkCodes();
        this.mTowerMccMnc = towerCountryAndNetworkCodes;
        return new AdaptiveHeartbeatIntervalDeterminer(NetworkType.MOBILE, HeartbeatSettings.getMinHeartbeatIntervalMillisOverWan(simCountryAndNetworkCodes, towerCountryAndNetworkCodes), HeartbeatSettings.getMaxHeartbeatIntervalMillisOverWan(simCountryAndNetworkCodes, towerCountryAndNetworkCodes));
    }

    protected WifiHeartbeatIntervalDeterminer createWifiHeartbeatIntervalDeterminer(WifiManagerWrapper wifiManagerWrapper, HeartbeatIntervalDeterminerStore heartbeatIntervalDeterminerStore) {
        return new WifiHeartbeatIntervalDeterminer(wifiManagerWrapper, heartbeatIntervalDeterminerStore);
    }

    @Override // com.amazon.communication.heartbeat.HeartbeatIntervalDeterminer
    public void forceLearningMode(TriggerLearningCommand triggerLearningCommand) {
        getCurrentHeartbeatIntervalDeterminer().forceLearningMode(triggerLearningCommand);
    }

    ConnectionHealthStatisticsAggregator getCurrentConnectionHealthStatisticsAggregator() {
        return this.mConnectionHealthStatsAggregators.get(this.mCurrentNetworkType);
    }

    HeartbeatIntervalDeterminer getCurrentHeartbeatIntervalDeterminer() {
        return this.mHeartbeatIntervalDeterminers.get(this.mCurrentNetworkType);
    }

    @Override // com.amazon.communication.heartbeat.HeartbeatIntervalDeterminer
    public long getLastKnownGoodHeartbeatIntervalMillis() {
        return getCurrentHeartbeatIntervalDeterminer().getLastKnownGoodHeartbeatIntervalMillis();
    }

    @Override // com.amazon.communication.heartbeat.HeartbeatIntervalDeterminer
    public long getMaximumHeartbeatIntervalMillis() {
        return getCurrentHeartbeatIntervalDeterminer().getMaximumHeartbeatIntervalMillis();
    }

    @Override // com.amazon.communication.heartbeat.HeartbeatIntervalDeterminer
    public long getMinimumHeartbeatIntervalMillis() {
        return getCurrentHeartbeatIntervalDeterminer().getMinimumHeartbeatIntervalMillis();
    }

    @Override // com.amazon.communication.heartbeat.HeartbeatIntervalDeterminer
    public boolean hasLearntHeartbeatInterval() {
        return getCurrentHeartbeatIntervalDeterminer().hasLearntHeartbeatInterval();
    }

    @Override // com.amazon.communication.ConnectivityChangedHandler
    public void onConnectivityChanged() {
        log.verbose("onConnectivityChanged", "connectivity changed", "wifi available?", Boolean.valueOf(this.mConnectivityMonitor.isWiFiAvailable()), "mobile available?", Boolean.valueOf(this.mConnectivityMonitor.isMobileAvailable()), "lan available?", Boolean.valueOf(this.mConnectivityMonitor.isEthernetAvailable()));
        if (this.mConnectivityMonitor.isEthernetAvailable()) {
            this.mCurrentNetworkType = NetworkType.ETHERNET;
        } else if (this.mConnectivityMonitor.isWiFiAvailable()) {
            this.mCurrentNetworkType = NetworkType.WIFI;
        } else if (this.mConnectivityMonitor.isMobileAvailable()) {
            this.mCurrentNetworkType = NetworkType.MOBILE;
            updateIntervalLimitsOverWanIfNeeded();
        } else {
            log.verbose("onConnectivityChanged", "none of the network is currently active", new Object[0]);
        }
    }

    @Override // com.amazon.communication.heartbeat.ConnectionHealthStatisticsAggregator
    public void onHealthyConnection(long j) {
        getCurrentConnectionHealthStatisticsAggregator().onHealthyConnection(j);
    }

    @Override // com.amazon.communication.heartbeat.ConnectionHealthStatisticsAggregator
    public void onUnhealthyConnection(long j) {
        if (this.mCurrentNetworkType == NetworkType.MOBILE) {
            updateIntervalLimitsOverWanIfNeeded();
        }
        getCurrentConnectionHealthStatisticsAggregator().onUnhealthyConnection(j);
    }

    @Override // com.amazon.communication.heartbeat.HeartbeatIntervalDeterminer
    public void shutdown() {
        this.mConnectivityMonitor.deregisterConnectivityChangedHandler(this);
        this.mHeartbeatControlMessageHandler.unregisterInvoker(HeartbeatControlMessage.Type.TriggerLearning);
        for (HeartbeatIntervalDeterminer heartbeatIntervalDeterminer : this.mHeartbeatIntervalDeterminers.values()) {
            heartbeatIntervalDeterminer.shutdown();
        }
        this.mHeartbeatIntervalDeterminers.clear();
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("NetworkType: ");
        outline107.append(this.mCurrentNetworkType);
        outline107.append(", ");
        outline107.append(getCurrentHeartbeatIntervalDeterminer().toString());
        return outline107.toString();
    }
}
