package com.amazon.communication.heartbeat;

import com.amazon.communication.NetworkType;
import com.amazon.communication.heartbeat.store.HeartbeatIntervalDeterminerState;
import com.amazon.communication.heartbeat.store.HeartbeatIntervalDeterminerStore;
import com.amazon.communication.remotesetting.RemoteSettingManager;
import com.amazon.communication.wifi.WifiManagerWrapper;
import com.amazon.dp.logger.DPLogger;
import com.amazon.dp.utils.Obfuscator;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.facebook.imagepipeline.memory.BitmapPoolType;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
/* loaded from: classes12.dex */
public class WifiHeartbeatIntervalDeterminer extends HeartbeatIntervalDeterminerBase implements ConnectionHealthStatisticsAggregator {
    private static final int DEFAULT_MAX_ENTRIES = 128;
    protected final HeartbeatIntervalDeterminerStore mHeartbeatIntervalDeterminerStore;
    private final Lock mObfuscatedBssidIntervalDeterminerMapLock;
    protected final Map<String, AdaptiveHeartbeatIntervalDeterminer> mObfuscatedBssidToHeartbeatIntervalDeterminerMap;
    private final WifiManagerWrapper mWifiManagerWrapper;
    private static final DPLogger log = new DPLogger("TComm.WifiHeartbeatIntervalDeterminer");
    private static final AdaptiveHeartbeatIntervalDeterminer DUMMY_HEARTBEAT_INTERVAL_DETERMINER = new AdaptiveHeartbeatIntervalDeterminer(NetworkType.WIFI);

    public WifiHeartbeatIntervalDeterminer(WifiManagerWrapper wifiManagerWrapper, HeartbeatIntervalDeterminerStore heartbeatIntervalDeterminerStore) {
        this(wifiManagerWrapper, heartbeatIntervalDeterminerStore, 128);
    }

    private AdaptiveHeartbeatIntervalDeterminer createAndCacheHeartbeatIntervalDeterminer() {
        this.mObfuscatedBssidIntervalDeterminerMapLock.lock();
        try {
            String obfuscate = Obfuscator.obfuscate(this.mWifiManagerWrapper.getBssid());
            if (this.mObfuscatedBssidToHeartbeatIntervalDeterminerMap.containsKey(obfuscate)) {
                return this.mObfuscatedBssidToHeartbeatIntervalDeterminerMap.get(obfuscate);
            }
            HeartbeatIntervalDeterminerState retrieve = this.mHeartbeatIntervalDeterminerStore.retrieve(obfuscate);
            log.debug("createAndCacheHeartbeatIntervalDeterminer", "creating a new instance for BSSID", "bssid", obfuscate, "storedState", retrieve);
            AdaptiveHeartbeatIntervalDeterminer createHeartbeatIntervalDeterminer = createHeartbeatIntervalDeterminer(obfuscate, retrieve);
            addExistingListeners(createHeartbeatIntervalDeterminer);
            this.mObfuscatedBssidToHeartbeatIntervalDeterminerMap.put(obfuscate, createHeartbeatIntervalDeterminer);
            return createHeartbeatIntervalDeterminer;
        } finally {
            this.mObfuscatedBssidIntervalDeterminerMapLock.unlock();
        }
    }

    private AdaptiveHeartbeatIntervalDeterminer getOrCreateHeartbeatIntervalDeterminer() {
        if (this.mWifiManagerWrapper.getBssid() == null) {
            log.debug("getOrCreateHeartbeatIntervalDeterminer", "BSSID unknown. Returning dummy instance", BitmapPoolType.DUMMY, DUMMY_HEARTBEAT_INTERVAL_DETERMINER);
            return DUMMY_HEARTBEAT_INTERVAL_DETERMINER;
        }
        this.mObfuscatedBssidIntervalDeterminerMapLock.lock();
        try {
            AdaptiveHeartbeatIntervalDeterminer adaptiveHeartbeatIntervalDeterminer = this.mObfuscatedBssidToHeartbeatIntervalDeterminerMap.get(Obfuscator.obfuscate(this.mWifiManagerWrapper.getBssid()));
            if (adaptiveHeartbeatIntervalDeterminer != null) {
                return adaptiveHeartbeatIntervalDeterminer;
            }
            this.mObfuscatedBssidIntervalDeterminerMapLock.unlock();
            return createAndCacheHeartbeatIntervalDeterminer();
        } finally {
            this.mObfuscatedBssidIntervalDeterminerMapLock.unlock();
        }
    }

    protected void addExistingListeners(HeartbeatIntervalDeterminer heartbeatIntervalDeterminer) {
        for (HeartbeatIntervalUpdatesListener heartbeatIntervalUpdatesListener : this.mIntervalUpdatesListeners) {
            heartbeatIntervalDeterminer.addHeartbeatIntervalUpdatesListener(heartbeatIntervalUpdatesListener);
        }
    }

    @Override // com.amazon.communication.heartbeat.HeartbeatIntervalDeterminerBase, com.amazon.communication.heartbeat.HeartbeatIntervalDeterminer
    public void addHeartbeatIntervalUpdatesListener(HeartbeatIntervalUpdatesListener heartbeatIntervalUpdatesListener) {
        super.addHeartbeatIntervalUpdatesListener(heartbeatIntervalUpdatesListener);
        this.mObfuscatedBssidIntervalDeterminerMapLock.lock();
        try {
            log.debug("addHeartbeatIntervalUpdatesListener", "updating all HIDs currently available", "mObfuscatedBssidToHeartbeatIntervalDeterminerMap.size()", Integer.valueOf(this.mObfuscatedBssidToHeartbeatIntervalDeterminerMap.size()));
            for (AdaptiveHeartbeatIntervalDeterminer adaptiveHeartbeatIntervalDeterminer : this.mObfuscatedBssidToHeartbeatIntervalDeterminerMap.values()) {
                adaptiveHeartbeatIntervalDeterminer.addHeartbeatIntervalUpdatesListener(heartbeatIntervalUpdatesListener);
            }
        } finally {
            this.mObfuscatedBssidIntervalDeterminerMapLock.unlock();
        }
    }

    protected AdaptiveHeartbeatIntervalDeterminer createHeartbeatIntervalDeterminer(String str, HeartbeatIntervalDeterminerState heartbeatIntervalDeterminerState) {
        long longValue = RemoteSettingManager.getOptLongValue(GeneratedOutlineSupport1.outline72("AdaptiveHeartbeat.MinIntervalMillis.WiFi.", str), -1L).longValue();
        long longValue2 = RemoteSettingManager.getOptLongValue("AdaptiveHeartbeat.MaxIntervalMillis.WiFi." + str, -1L).longValue();
        if (longValue > 0 && longValue2 > 0) {
            log.info("createHeartbeatIntervalDeterminer", "using bssid specific values for heartbeat limits for bssid", "bssid", str, "minIntervalMillis", Long.valueOf(longValue), "maxIntervalMillis", Long.valueOf(longValue2), "storedState", heartbeatIntervalDeterminerState);
            return new AdaptiveHeartbeatIntervalDeterminer(NetworkType.WIFI, longValue, longValue2, str, this.mHeartbeatIntervalDeterminerStore, heartbeatIntervalDeterminerState);
        }
        log.info("createHeartbeatIntervalDeterminer", "using default heartbeat limits as no values are defined for bssid", "bssid", str, "storedState", heartbeatIntervalDeterminerState);
        return new AdaptiveHeartbeatIntervalDeterminer(NetworkType.WIFI, str, this.mHeartbeatIntervalDeterminerStore, heartbeatIntervalDeterminerState);
    }

    @Override // com.amazon.communication.heartbeat.HeartbeatIntervalDeterminerBase, com.amazon.communication.heartbeat.HeartbeatIntervalDeterminer
    public void forceLearningMode(TriggerLearningCommand triggerLearningCommand) {
        log.info("forceLearningMode", "received command to force learning", "command", triggerLearningCommand);
        LinkedList<HeartbeatIntervalDeterminer> linkedList = new LinkedList();
        this.mObfuscatedBssidIntervalDeterminerMapLock.lock();
        try {
            if ("".equals(triggerLearningCommand.getNetworkIdentifier())) {
                log.info("forceLearningMode", "empty networkIdentifier found; forcing learning for all interval determiners", "command", triggerLearningCommand);
                linkedList.addAll(this.mObfuscatedBssidToHeartbeatIntervalDeterminerMap.values());
            } else {
                AdaptiveHeartbeatIntervalDeterminer adaptiveHeartbeatIntervalDeterminer = this.mObfuscatedBssidToHeartbeatIntervalDeterminerMap.get(triggerLearningCommand.getNetworkIdentifier());
                if (adaptiveHeartbeatIntervalDeterminer != null) {
                    linkedList.add(adaptiveHeartbeatIntervalDeterminer);
                } else {
                    log.warn("forceLearningMode", "unable to map networkIdentifier to any hid; ignoring", "command", triggerLearningCommand);
                }
            }
            this.mObfuscatedBssidIntervalDeterminerMapLock.unlock();
            for (HeartbeatIntervalDeterminer heartbeatIntervalDeterminer : linkedList) {
                heartbeatIntervalDeterminer.forceLearningMode(triggerLearningCommand);
                log.info("forceLearningMode", "forced switching the learning mode", "command", triggerLearningCommand, "hid", heartbeatIntervalDeterminer);
            }
        } catch (Throwable th) {
            this.mObfuscatedBssidIntervalDeterminerMapLock.unlock();
            throw th;
        }
    }

    @Override // com.amazon.communication.heartbeat.HeartbeatIntervalDeterminer
    public long getLastKnownGoodHeartbeatIntervalMillis() {
        return getOrCreateHeartbeatIntervalDeterminer().getLastKnownGoodHeartbeatIntervalMillis();
    }

    @Override // com.amazon.communication.heartbeat.HeartbeatIntervalDeterminer
    public long getMaximumHeartbeatIntervalMillis() {
        return getOrCreateHeartbeatIntervalDeterminer().getMaximumHeartbeatIntervalMillis();
    }

    @Override // com.amazon.communication.heartbeat.HeartbeatIntervalDeterminer
    public long getMinimumHeartbeatIntervalMillis() {
        return getOrCreateHeartbeatIntervalDeterminer().getMinimumHeartbeatIntervalMillis();
    }

    @Override // com.amazon.communication.heartbeat.HeartbeatIntervalDeterminerBase, com.amazon.communication.heartbeat.HeartbeatIntervalDeterminer
    public boolean hasLearntHeartbeatInterval() {
        return getOrCreateHeartbeatIntervalDeterminer().hasLearntHeartbeatInterval();
    }

    @Override // com.amazon.communication.heartbeat.ConnectionHealthStatisticsAggregator
    public void onHealthyConnection(long j) {
        getOrCreateHeartbeatIntervalDeterminer().onHealthyConnection(j);
    }

    @Override // com.amazon.communication.heartbeat.ConnectionHealthStatisticsAggregator
    public void onUnhealthyConnection(long j) {
        getOrCreateHeartbeatIntervalDeterminer().onUnhealthyConnection(j);
    }

    @Override // com.amazon.communication.heartbeat.HeartbeatIntervalDeterminerBase, com.amazon.communication.heartbeat.HeartbeatIntervalDeterminer
    public void shutdown() {
        for (AdaptiveHeartbeatIntervalDeterminer adaptiveHeartbeatIntervalDeterminer : this.mObfuscatedBssidToHeartbeatIntervalDeterminerMap.values()) {
            adaptiveHeartbeatIntervalDeterminer.shutdown();
        }
        this.mObfuscatedBssidToHeartbeatIntervalDeterminerMap.clear();
    }

    public String toString() {
        return getOrCreateHeartbeatIntervalDeterminer().toString();
    }

    public WifiHeartbeatIntervalDeterminer(WifiManagerWrapper wifiManagerWrapper, HeartbeatIntervalDeterminerStore heartbeatIntervalDeterminerStore, final int i) {
        this.mObfuscatedBssidIntervalDeterminerMapLock = new ReentrantLock();
        this.mWifiManagerWrapper = wifiManagerWrapper;
        this.mObfuscatedBssidToHeartbeatIntervalDeterminerMap = new LinkedHashMap<String, AdaptiveHeartbeatIntervalDeterminer>(i, 0.75f, true) { // from class: com.amazon.communication.heartbeat.WifiHeartbeatIntervalDeterminer.1
            @Override // java.util.LinkedHashMap
            protected boolean removeEldestEntry(Map.Entry<String, AdaptiveHeartbeatIntervalDeterminer> entry) {
                boolean z = size() > i;
                if (z) {
                    WifiHeartbeatIntervalDeterminer.log.debug("removeEldestEntry", "max size reached. Removing the LRU entry", "entry", entry.getKey());
                    entry.getValue().shutdown();
                }
                return z;
            }
        };
        this.mHeartbeatIntervalDeterminerStore = heartbeatIntervalDeterminerStore;
    }
}
