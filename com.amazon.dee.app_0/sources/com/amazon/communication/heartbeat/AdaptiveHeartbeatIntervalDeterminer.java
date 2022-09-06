package com.amazon.communication.heartbeat;

import amazon.speech.simclient.settings.Settings;
import com.amazon.communication.NetworkType;
import com.amazon.communication.heartbeat.HeartbeatIntervalUpdatesListener;
import com.amazon.communication.heartbeat.store.HeartbeatIntervalDeterminerState;
import com.amazon.communication.heartbeat.store.HeartbeatIntervalDeterminerStore;
import com.amazon.communication.remotesetting.RemoteSettingManager;
import com.amazon.communication.remotesetting.SettingUpdateListener;
import com.amazon.communication.time.SystemTimeSource;
import com.amazon.dp.logger.DPLogger;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes12.dex */
public class AdaptiveHeartbeatIntervalDeterminer extends HeartbeatIntervalDeterminerBase implements ConnectionHealthStatisticsAggregator {
    private static final DPLogger log = new DPLogger("TComm.AdaptiveHeartbeatIntervalDeterminer");
    private volatile int mConsecutiveFailureCount;
    private volatile int mConsecutiveFailureNearLowerBoundCount;
    protected volatile long mCurrentLowerBoundInMillis;
    protected volatile long mCurrentUpperBoundInMillis;
    protected volatile long mHeartbeatIntervalMillis;
    private final HeartbeatIntervalConfidenceComputer mIntervalConfidenceComputer;
    protected volatile long mIntervalExpiryTimestamp;
    private volatile int mLearningAttemptCount;
    protected volatile boolean mLearningMode;
    private volatile long mLearntTimestamp;
    protected volatile long mMaxHeartbeatIntervalMillis;
    protected volatile long mMinHeartbeatIntervalMillis;
    private volatile NetworkType mNetworkType;
    private final SettingsCacheUpdateListener mSettingsCacheUpdateListener;
    private volatile HeartbeatIntervalDeterminerStore mStore;
    private volatile String mStoreKey;

    /* loaded from: classes12.dex */
    private final class SettingsCacheUpdateListener extends SettingUpdateListener {
        private SettingsCacheUpdateListener() {
        }

        @Override // com.amazon.communication.remotesetting.SettingUpdateListener
        public void onSettingUpdated() {
            boolean z = false;
            boolean z2 = true;
            AdaptiveHeartbeatIntervalDeterminer.log.info("onCacheUpdated", "Received update notification", HeartbeatSettings.MIN_HEARTBEAT_INTERVAL_MILLIS_WIFI_KEY, HeartbeatSettings.getLongValue(HeartbeatSettings.MIN_HEARTBEAT_INTERVAL_MILLIS_WIFI_KEY), HeartbeatSettings.MIN_HEARTBEAT_INTERVAL_MILLIS_WAN_KEY, HeartbeatSettings.getLongValue(HeartbeatSettings.MIN_HEARTBEAT_INTERVAL_MILLIS_WAN_KEY), HeartbeatSettings.MAX_HEARTBEAT_INTERVAL_MILLIS_WIFI_KEY, HeartbeatSettings.getLongValue(HeartbeatSettings.MAX_HEARTBEAT_INTERVAL_MILLIS_WIFI_KEY), HeartbeatSettings.MAX_HEARTBEAT_INTERVAL_MILLIS_WAN_KEY, HeartbeatSettings.getLongValue(HeartbeatSettings.MAX_HEARTBEAT_INTERVAL_MILLIS_WAN_KEY), HeartbeatSettings.MAX_ALLOWED_CONSECUTIVE_FAILURE_COUNT_KEY, HeartbeatSettings.getIntValue(HeartbeatSettings.MAX_ALLOWED_CONSECUTIVE_FAILURE_COUNT_KEY));
            if (HeartbeatSettings.getLongValue(HeartbeatSettings.MIN_HEARTBEAT_INTERVAL_MILLIS_WIFI_KEY).longValue() < 0) {
                AdaptiveHeartbeatIntervalDeterminer.log.error("onCacheUpdated", "MIN_HEARTBEAT_INTERVAL_MILLIS_WIFI negative", new Object[0]);
                z2 = false;
            }
            if (HeartbeatSettings.getLongValue(HeartbeatSettings.MIN_HEARTBEAT_INTERVAL_MILLIS_WAN_KEY).longValue() < 0) {
                AdaptiveHeartbeatIntervalDeterminer.log.error("onCacheUpdated", "MIN_HEARTBEAT_INTERVAL_MILLIS_WAN negative", new Object[0]);
                z2 = false;
            }
            if (HeartbeatSettings.getLongValue(HeartbeatSettings.MAX_HEARTBEAT_INTERVAL_MILLIS_WIFI_KEY).longValue() <= 0) {
                AdaptiveHeartbeatIntervalDeterminer.log.error("onCacheUpdated", "MAX_HEARTBEAT_INTERVAL_MILLIS_WIFI not positive", new Object[0]);
                z2 = false;
            }
            if (HeartbeatSettings.getLongValue(HeartbeatSettings.MAX_HEARTBEAT_INTERVAL_MILLIS_WAN_KEY).longValue() <= 0) {
                AdaptiveHeartbeatIntervalDeterminer.log.error("onCacheUpdated", "MAX_HEARTBEAT_INTERVAL_MILLIS_WAN not positive", new Object[0]);
                z2 = false;
            }
            if (HeartbeatSettings.getLongValue(HeartbeatSettings.MAX_HEARTBEAT_INTERVAL_MILLIS_WIFI_KEY).longValue() < HeartbeatSettings.getLongValue(HeartbeatSettings.MIN_HEARTBEAT_INTERVAL_MILLIS_WIFI_KEY).longValue()) {
                AdaptiveHeartbeatIntervalDeterminer.log.error("onCacheUpdated", "MAX_HEARTBEAT_INTERVAL_MILLIS_WIFI smaller than MIN_HEARTBEAT_INTERVAL_MILLIS_WIFI", new Object[0]);
                z2 = false;
            }
            if (HeartbeatSettings.getLongValue(HeartbeatSettings.MAX_HEARTBEAT_INTERVAL_MILLIS_WAN_KEY).longValue() < HeartbeatSettings.getLongValue(HeartbeatSettings.MIN_HEARTBEAT_INTERVAL_MILLIS_WAN_KEY).longValue()) {
                AdaptiveHeartbeatIntervalDeterminer.log.error("onCacheUpdated", "MAX_HEARTBEAT_INTERVAL_MILLIS_WAN smaller than MIN_HEARTBEAT_INTERVAL_MILLIS_WAN", new Object[0]);
                z2 = false;
            }
            if (HeartbeatSettings.getLongValue(HeartbeatSettings.INTERVAL_VALIDITY_PERIOD_MILLIS_KEY).longValue() <= 0) {
                AdaptiveHeartbeatIntervalDeterminer.log.error("onCacheUpdated", "INTERVAL_VALIDITY_PERIOD_MILLIS not positive", new Object[0]);
            } else {
                z = z2;
            }
            if (z) {
                AdaptiveHeartbeatIntervalDeterminer.this.updateIntervalLimits();
                if (AdaptiveHeartbeatIntervalDeterminer.this.mCurrentLowerBoundInMillis > AdaptiveHeartbeatIntervalDeterminer.this.mCurrentUpperBoundInMillis) {
                    AdaptiveHeartbeatIntervalDeterminer.this.resetBounds(HeartbeatIntervalUpdatesListener.SwitchingReason.REMOTE_SETTINGS_DRASTIC_CHANGE);
                    AdaptiveHeartbeatIntervalDeterminer.this.switchToLearningMode(HeartbeatIntervalUpdatesListener.SwitchingReason.REMOTE_SETTINGS_DRASTIC_CHANGE);
                }
                AdaptiveHeartbeatIntervalDeterminer.this.persist();
            }
        }
    }

    public AdaptiveHeartbeatIntervalDeterminer(NetworkType networkType) {
        this(networkType, getMinHeartbeatIntervalMillis(networkType), getMaxHeartbeatIntervalMillis(networkType));
    }

    private boolean areWeInLearningMode() {
        switchToLearningModeIfHeartbeatIsStale();
        return this.mLearningMode;
    }

    private long boundIntervalMillis(long j) {
        return boundIntervalMillis(j, this.mMinHeartbeatIntervalMillis, this.mMaxHeartbeatIntervalMillis);
    }

    private long calculateNextHeartbeatInterval() {
        if (this.mCurrentLowerBoundInMillis < this.mMinHeartbeatIntervalMillis) {
            log.warn("calculateNextHeartbeatInterval", "inconsistent state - currentLowerBound is less than the absolute min allowed for heartbeat interval; fixing it", "mCurrentLowerBoundInMillis", Long.valueOf(this.mCurrentLowerBoundInMillis), "mMinHeartbeatIntervalMillis", Long.valueOf(this.mMinHeartbeatIntervalMillis));
            this.mCurrentLowerBoundInMillis = this.mMinHeartbeatIntervalMillis;
        }
        if (this.mCurrentUpperBoundInMillis > this.mMaxHeartbeatIntervalMillis) {
            log.warn("calculateNextHeartbeatInterval", "inconsistent state - currentUpperBound is more than the absolute max allowed for heartbeat interval; fixing it", "mCurrentUpperBoundInMillis", Long.valueOf(this.mCurrentUpperBoundInMillis), "mMaxHeartbeatIntervalMillis", Long.valueOf(this.mMaxHeartbeatIntervalMillis));
            this.mCurrentUpperBoundInMillis = this.mMaxHeartbeatIntervalMillis;
        }
        long j = (this.mCurrentLowerBoundInMillis + this.mCurrentUpperBoundInMillis) / 2;
        log.debug("calculateNextHeartbeatInterval", "calculated a new heartbeat interval", "mMinHeartbeatIntervalMillis", Long.valueOf(this.mMinHeartbeatIntervalMillis), "mCurrentLowerBoundInMillis", Long.valueOf(this.mCurrentLowerBoundInMillis), "nextHeartbeatInterval", Long.valueOf(j), "mCurrentUpperBoundInMillis", Long.valueOf(this.mCurrentUpperBoundInMillis), "mMaxHeartbeatIntervalMillis", Long.valueOf(this.mMaxHeartbeatIntervalMillis));
        notifyIntervalChange(this.mNetworkType, this.mHeartbeatIntervalMillis, j);
        return j;
    }

    private static long getMaxHeartbeatIntervalMillis(NetworkType networkType) {
        if (networkType == NetworkType.WIFI) {
            return HeartbeatSettings.getLongValue(HeartbeatSettings.MAX_HEARTBEAT_INTERVAL_MILLIS_WIFI_KEY).longValue();
        }
        if (networkType == NetworkType.MOBILE) {
            return HeartbeatSettings.getLongValue(HeartbeatSettings.MAX_HEARTBEAT_INTERVAL_MILLIS_WAN_KEY).longValue();
        }
        throw new IllegalArgumentException("Unknown networkType: " + networkType);
    }

    private static long getMinHeartbeatIntervalMillis(NetworkType networkType) {
        if (networkType == NetworkType.WIFI) {
            return HeartbeatSettings.getLongValue(HeartbeatSettings.MIN_HEARTBEAT_INTERVAL_MILLIS_WIFI_KEY).longValue();
        }
        if (networkType == NetworkType.MOBILE) {
            return HeartbeatSettings.getLongValue(HeartbeatSettings.MIN_HEARTBEAT_INTERVAL_MILLIS_WAN_KEY).longValue();
        }
        throw new IllegalArgumentException("Unknown networkType: " + networkType);
    }

    private boolean isIntervalStabilized() {
        return this.mCurrentUpperBoundInMillis - this.mCurrentLowerBoundInMillis <= HeartbeatSettings.getLongValue(HeartbeatSettings.STABILIZED_INTERVAL_BOUNDS_DIFF_MILLIS_KEY).longValue();
    }

    private boolean isLessOrTriviallyMore(long j, long j2) {
        return j - j2 <= HeartbeatSettings.getLongValue(HeartbeatSettings.TRIVIAL_INTERVAL_DIFF_MILLIS_KEY).longValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void resetBounds(HeartbeatIntervalUpdatesListener.SwitchingReason switchingReason) {
        long j;
        if (switchingReason == HeartbeatIntervalUpdatesListener.SwitchingReason.INTERVAL_VALIDITY_EXPIRED) {
            j = this.mHeartbeatIntervalMillis;
        } else {
            j = this.mMinHeartbeatIntervalMillis;
        }
        resetBounds(switchingReason, j, this.mMaxHeartbeatIntervalMillis);
    }

    private void resetLowerBound() {
        log.info("resetLowerBound", "Resetting lower bound", "minHeartbeatIntervalMillis", Long.valueOf(this.mMinHeartbeatIntervalMillis));
        this.mCurrentLowerBoundInMillis = this.mMinHeartbeatIntervalMillis;
    }

    private final void restoreState(HeartbeatIntervalDeterminerState heartbeatIntervalDeterminerState) {
        if (heartbeatIntervalDeterminerState != null) {
            this.mCurrentLowerBoundInMillis = boundIntervalMillis(heartbeatIntervalDeterminerState.getCurrentLowerBoundMillis());
            this.mCurrentUpperBoundInMillis = boundIntervalMillis(heartbeatIntervalDeterminerState.getCurrentUpperBoundMillis());
            this.mHeartbeatIntervalMillis = boundIntervalMillis(heartbeatIntervalDeterminerState.getHeartbeatIntervalMillis(), this.mCurrentLowerBoundInMillis, this.mCurrentUpperBoundInMillis);
            this.mLearningMode = heartbeatIntervalDeterminerState.isLearningMode();
            this.mLearntTimestamp = heartbeatIntervalDeterminerState.getLearntTimestamp();
            this.mIntervalExpiryTimestamp = heartbeatIntervalDeterminerState.getIntervalExpiryTimestamp();
            this.mLearningAttemptCount = heartbeatIntervalDeterminerState.getLearningAttemptCount();
            this.mConsecutiveFailureCount = heartbeatIntervalDeterminerState.getConsecutiveFailureCount();
            if (this.mCurrentLowerBoundInMillis == heartbeatIntervalDeterminerState.getCurrentLowerBoundMillis() && this.mCurrentUpperBoundInMillis == heartbeatIntervalDeterminerState.getCurrentUpperBoundMillis() && this.mHeartbeatIntervalMillis == heartbeatIntervalDeterminerState.getHeartbeatIntervalMillis()) {
                return;
            }
            log.info("restoreState", "bounds and intervals had to be changed to make them within the current min and max limits", "mMinHeartbeatIntervalMillis", Long.valueOf(this.mMinHeartbeatIntervalMillis), "mCurrentLowerBoundInMillis", Long.valueOf(this.mCurrentLowerBoundInMillis), "mHeartbeatIntervalMillis", Long.valueOf(this.mHeartbeatIntervalMillis), "mCurrentUpperBoundInMillis", Long.valueOf(this.mCurrentUpperBoundInMillis), "mMaxHeartbeatIntervalMillis", Long.valueOf(this.mMaxHeartbeatIntervalMillis));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void switchToLearningMode(HeartbeatIntervalUpdatesListener.SwitchingReason switchingReason) {
        this.mLearningMode = true;
        this.mLearningAttemptCount = 0;
        this.mConsecutiveFailureCount = 0;
        notifyLearningModeChange(this.mNetworkType, this.mLearningMode, switchingReason, this.mHeartbeatIntervalMillis);
    }

    private void switchToLearntMode(HeartbeatIntervalUpdatesListener.SwitchingReason switchingReason) {
        double confidence = this.mIntervalConfidenceComputer.getConfidence(this.mHeartbeatIntervalMillis);
        this.mLearntTimestamp = SystemTimeSource.INSTANCE.currentTimeMillis();
        long longValue = HeartbeatSettings.getLongValue(HeartbeatSettings.POOR_CONNECTIVITY_INTERVAL_VALIDITY_PERIOD_MILLIS_KEY).longValue();
        long longValue2 = longValue + ((long) ((HeartbeatSettings.getLongValue(HeartbeatSettings.INTERVAL_VALIDITY_PERIOD_MILLIS_KEY).longValue() - longValue) * confidence));
        this.mIntervalExpiryTimestamp = this.mLearntTimestamp + longValue2;
        this.mLearningMode = false;
        log.info("switchToLearntMode", "switched to learnt mode", "will use heartbeat interval", Long.valueOf(this.mHeartbeatIntervalMillis), Settings.ListeningSettings.EXTRA_REASON, switchingReason, "heartbeatIntervalConfidence", Double.valueOf(confidence), "mIntervalExpiryTimestamp", Long.valueOf(this.mIntervalExpiryTimestamp), "validityDurationMillis", Long.valueOf(longValue2));
        notifyLearningModeChange(this.mNetworkType, this.mLearningMode, switchingReason, this.mHeartbeatIntervalMillis);
    }

    @Override // com.amazon.communication.heartbeat.HeartbeatIntervalDeterminerBase, com.amazon.communication.heartbeat.HeartbeatIntervalDeterminer
    public void forceLearningMode(TriggerLearningCommand triggerLearningCommand) {
        if (!this.mLearningMode) {
            HeartbeatIntervalUpdatesListener.SwitchingReason switchingReason = HeartbeatIntervalUpdatesListener.SwitchingReason.FORCED_LEARNING_MODE;
            long boundIntervalMillis = boundIntervalMillis(triggerLearningCommand.getLowerBoundMillis());
            long boundIntervalMillis2 = boundIntervalMillis(triggerLearningCommand.getUpperBoundMillis());
            if (boundIntervalMillis != triggerLearningCommand.getLowerBoundMillis() || boundIntervalMillis2 != triggerLearningCommand.getUpperBoundMillis()) {
                log.info("forceLearningMode", "using different values for the bounds than those in the command to make them fit in to the limits", "command", triggerLearningCommand, "lowerBoundMillis", Long.valueOf(boundIntervalMillis), "upperBoundMillis", Long.valueOf(boundIntervalMillis2));
            }
            resetBounds(switchingReason, boundIntervalMillis, boundIntervalMillis2);
            switchToLearningMode(switchingReason);
            persist();
            return;
        }
        log.info("forceLearningMode", "already in learning mode; ignoring command", "command", triggerLearningCommand);
    }

    @Override // com.amazon.communication.heartbeat.HeartbeatIntervalDeterminer
    public long getLastKnownGoodHeartbeatIntervalMillis() {
        switchToLearningModeIfHeartbeatIsStale();
        return this.mCurrentLowerBoundInMillis;
    }

    @Override // com.amazon.communication.heartbeat.HeartbeatIntervalDeterminer
    public long getMaximumHeartbeatIntervalMillis() {
        switchToLearningModeIfHeartbeatIsStale();
        return Math.min(HeartbeatSettings.getLongValue(HeartbeatSettings.HEARTBEAT_INTERVAL_HIKE_MILLIS_KEY).longValue() + this.mHeartbeatIntervalMillis, this.mMaxHeartbeatIntervalMillis);
    }

    @Override // com.amazon.communication.heartbeat.HeartbeatIntervalDeterminer
    public long getMinimumHeartbeatIntervalMillis() {
        switchToLearningModeIfHeartbeatIsStale();
        return Math.max(this.mHeartbeatIntervalMillis - HeartbeatSettings.getLongValue(HeartbeatSettings.HEARTBEAT_INTERVAL_RANGE_MILLIS_KEY).longValue(), this.mCurrentLowerBoundInMillis);
    }

    @Override // com.amazon.communication.heartbeat.HeartbeatIntervalDeterminerBase, com.amazon.communication.heartbeat.HeartbeatIntervalDeterminer
    public boolean hasLearntHeartbeatInterval() {
        return !areWeInLearningMode();
    }

    @Override // com.amazon.communication.heartbeat.ConnectionHealthStatisticsAggregator
    public void onHealthyConnection(long j) {
        log.debug("onHealthyConnection", "Entering method", "Current heartbeat state", toString(), "time connections were dormant", Long.valueOf(j));
        if (this.mConsecutiveFailureNearLowerBoundCount > 0 && !isLessOrTriviallyMore(j, this.mMinHeartbeatIntervalMillis)) {
            log.info("onHealthyConnection", "reseting the consecutive failure near lower bound count", "timeDormantMillis", Long.valueOf(j), "mMinHeartbeatIntervalMillis", Long.valueOf(this.mMinHeartbeatIntervalMillis));
            this.mConsecutiveFailureNearLowerBoundCount = 0;
        }
        if (j < this.mCurrentLowerBoundInMillis) {
            return;
        }
        long min = Math.min(j, this.mMaxHeartbeatIntervalMillis);
        this.mIntervalConfidenceComputer.reportInterval(min);
        if (areWeInLearningMode()) {
            log.debug("onHealthyConnection", "in learning mode. Learnt that current heartbeat interval works", "timeDormantMillis", Long.valueOf(j));
            if (this.mCurrentLowerBoundInMillis <= min) {
                log.debug("onHealthyConnection", "updating lower bound due to healthy connection", "oldLowerBoundInMillis", Long.valueOf(this.mCurrentLowerBoundInMillis), "newLowerBoundInMillis", Long.valueOf(min));
                this.mCurrentLowerBoundInMillis = min;
                this.mCurrentUpperBoundInMillis = Math.max(min, this.mCurrentUpperBoundInMillis);
                int intValue = HeartbeatSettings.getIntValue(HeartbeatSettings.MAX_LEARNING_ATTEMPT_COUNT_KEY).intValue();
                if (this.mLearningAttemptCount < intValue && !isIntervalStabilized()) {
                    this.mLearningAttemptCount++;
                    this.mHeartbeatIntervalMillis = calculateNextHeartbeatInterval();
                } else {
                    log.debug("onHealthyConnection", "updating final heartbeat interval and switching to learnt mode", "mCurrentLowerBoundInMillis", Long.valueOf(this.mCurrentLowerBoundInMillis), "mHeartbeatIntervalMillis", Long.valueOf(this.mHeartbeatIntervalMillis), "ceiledTimeDormantMillis", Long.valueOf(min), "mCurrentUpperBoundInMillis", Long.valueOf(this.mCurrentUpperBoundInMillis));
                    this.mHeartbeatIntervalMillis = Math.max(this.mHeartbeatIntervalMillis, min);
                    switchToLearntMode(this.mLearningAttemptCount >= intValue ? HeartbeatIntervalUpdatesListener.SwitchingReason.EXPECTED_LEARNING_ATTEMPTS_REACHED : HeartbeatIntervalUpdatesListener.SwitchingReason.INTERVAL_STABILIZED);
                }
            } else {
                log.debug("onHealthyConnection", "not updating any heartbeat intervals because the time dormant was the same as the existing lower bound", "ceiledTimeDormantMillis", Long.valueOf(min));
            }
        } else if (min >= this.mHeartbeatIntervalMillis) {
            log.info("onHealthyConnection", "In learnt mode. Resetting consecutiveFailureCount to 0", "value of mConsecutiveFailureCount before reset", Integer.valueOf(this.mConsecutiveFailureCount));
            this.mConsecutiveFailureCount = 0;
        }
        persist();
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x00b9  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0174  */
    @Override // com.amazon.communication.heartbeat.ConnectionHealthStatisticsAggregator
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void onUnhealthyConnection(long r17) {
        /*
            Method dump skipped, instructions count: 451
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.communication.heartbeat.AdaptiveHeartbeatIntervalDeterminer.onUnhealthyConnection(long):void");
    }

    protected void persist() {
        if (this.mStore == null || this.mStoreKey == null) {
            return;
        }
        HeartbeatIntervalDeterminerState heartbeatIntervalDeterminerState = new HeartbeatIntervalDeterminerState(this.mNetworkType, this.mCurrentLowerBoundInMillis, this.mCurrentUpperBoundInMillis, this.mHeartbeatIntervalMillis, this.mLearningMode, this.mLearntTimestamp, this.mIntervalExpiryTimestamp, this.mLearningAttemptCount, this.mConsecutiveFailureCount);
        this.mStore.store(this.mStoreKey, heartbeatIntervalDeterminerState);
        log.verbose("persist", "persisted current state", "mStoreKey", this.mStoreKey, "currentState", heartbeatIntervalDeterminerState);
    }

    @Override // com.amazon.communication.heartbeat.HeartbeatIntervalDeterminerBase, com.amazon.communication.heartbeat.HeartbeatIntervalDeterminer
    public void shutdown() {
        RemoteSettingManager.removeSettingUpdateListener(this.mSettingsCacheUpdateListener);
    }

    protected void switchToLearningModeIfHeartbeatIsStale() {
        if (this.mLearningMode || SystemTimeSource.INSTANCE.currentTimeMillis() < this.mIntervalExpiryTimestamp) {
            return;
        }
        log.info("switchToLearningModeIfHeartbeatIsStale", "Switching back to learning mode as heartbeat intervalis stale", "mLearntTimeStamp", Long.valueOf(this.mLearntTimestamp), "mIntervalExpiryTimestamp", Long.valueOf(this.mIntervalExpiryTimestamp));
        resetBounds(HeartbeatIntervalUpdatesListener.SwitchingReason.INTERVAL_VALIDITY_EXPIRED);
        switchToLearningMode(HeartbeatIntervalUpdatesListener.SwitchingReason.INTERVAL_VALIDITY_EXPIRED);
        persist();
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("AdaptiveHID: low: ");
        outline107.append(this.mCurrentLowerBoundInMillis);
        outline107.append(", current: ");
        outline107.append(this.mHeartbeatIntervalMillis);
        outline107.append(", upper: ");
        outline107.append(this.mCurrentUpperBoundInMillis);
        return outline107.toString();
    }

    public void updateIntervalLimits() {
        this.mMinHeartbeatIntervalMillis = getMinHeartbeatIntervalMillis(this.mNetworkType);
        this.mMaxHeartbeatIntervalMillis = getMaxHeartbeatIntervalMillis(this.mNetworkType);
        boolean z = false;
        boolean z2 = this.mCurrentLowerBoundInMillis < this.mMinHeartbeatIntervalMillis || this.mCurrentLowerBoundInMillis > this.mMaxHeartbeatIntervalMillis;
        if (this.mCurrentUpperBoundInMillis < this.mMinHeartbeatIntervalMillis || this.mCurrentUpperBoundInMillis > this.mMaxHeartbeatIntervalMillis) {
            z = true;
        }
        if (this.mNetworkType == NetworkType.MOBILE) {
            this.mCurrentLowerBoundInMillis = HeartbeatSettings.getMinHeartbeatIntervalMillisOverWan();
        } else if (z2) {
            this.mCurrentLowerBoundInMillis = this.mMinHeartbeatIntervalMillis;
        }
        this.mCurrentLowerBoundInMillis = Math.max(this.mMinHeartbeatIntervalMillis, this.mCurrentLowerBoundInMillis);
        if (this.mNetworkType == NetworkType.MOBILE) {
            this.mCurrentUpperBoundInMillis = HeartbeatSettings.getMaxHeartbeatIntervalMillisOverWan();
        } else if (z) {
            this.mCurrentUpperBoundInMillis = this.mMaxHeartbeatIntervalMillis;
        }
        this.mCurrentUpperBoundInMillis = Math.min(this.mCurrentUpperBoundInMillis, this.mMaxHeartbeatIntervalMillis);
        if (this.mHeartbeatIntervalMillis < this.mCurrentLowerBoundInMillis || this.mHeartbeatIntervalMillis > this.mCurrentUpperBoundInMillis) {
            this.mHeartbeatIntervalMillis = calculateNextHeartbeatInterval();
        }
    }

    public AdaptiveHeartbeatIntervalDeterminer(NetworkType networkType, long j, long j2) {
        this.mLearningMode = true;
        this.mLearningAttemptCount = 0;
        this.mConsecutiveFailureCount = 0;
        this.mConsecutiveFailureNearLowerBoundCount = 0;
        this.mNetworkType = networkType;
        this.mMinHeartbeatIntervalMillis = j;
        this.mMaxHeartbeatIntervalMillis = j2;
        this.mCurrentLowerBoundInMillis = this.mNetworkType == NetworkType.MOBILE ? HeartbeatSettings.getMinHeartbeatIntervalMillisOverWan() : this.mMinHeartbeatIntervalMillis;
        this.mCurrentUpperBoundInMillis = this.mNetworkType == NetworkType.MOBILE ? HeartbeatSettings.getMaxHeartbeatIntervalMillisOverWan() : this.mMaxHeartbeatIntervalMillis;
        this.mHeartbeatIntervalMillis = calculateNextHeartbeatInterval();
        this.mIntervalConfidenceComputer = new StaticHeartbeatIntervalConfidenceComputer(j, j2);
        this.mSettingsCacheUpdateListener = new SettingsCacheUpdateListener();
        RemoteSettingManager.addSettingUpdateListener(this.mSettingsCacheUpdateListener);
    }

    private long boundIntervalMillis(long j, long j2, long j3) {
        return j < j2 ? j2 : Math.min(j, j3);
    }

    private void resetBounds(HeartbeatIntervalUpdatesListener.SwitchingReason switchingReason, long j, long j2) {
        this.mCurrentLowerBoundInMillis = j;
        this.mCurrentUpperBoundInMillis = j2;
        this.mHeartbeatIntervalMillis = calculateNextHeartbeatInterval();
        log.info("resetBounds", "Reset bounds", Settings.ListeningSettings.EXTRA_REASON, switchingReason, "mCurrentLowerBoundInMillis", Long.valueOf(this.mCurrentLowerBoundInMillis), "mCurrentUpperBoundInMillis", Long.valueOf(this.mCurrentUpperBoundInMillis), "mHeartbeatIntervalMillis", Long.valueOf(this.mHeartbeatIntervalMillis));
    }

    public AdaptiveHeartbeatIntervalDeterminer(NetworkType networkType, String str, HeartbeatIntervalDeterminerStore heartbeatIntervalDeterminerStore, HeartbeatIntervalDeterminerState heartbeatIntervalDeterminerState) {
        this(networkType, getMinHeartbeatIntervalMillis(networkType), getMaxHeartbeatIntervalMillis(networkType), str, heartbeatIntervalDeterminerStore, heartbeatIntervalDeterminerState);
    }

    public AdaptiveHeartbeatIntervalDeterminer(NetworkType networkType, long j, long j2, String str, HeartbeatIntervalDeterminerStore heartbeatIntervalDeterminerStore, HeartbeatIntervalDeterminerState heartbeatIntervalDeterminerState) {
        this(networkType, j, j2);
        this.mStoreKey = str;
        this.mStore = heartbeatIntervalDeterminerStore;
        restoreState(heartbeatIntervalDeterminerState);
    }
}
