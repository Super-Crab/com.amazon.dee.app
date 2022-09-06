package com.amazon.whisperjoin.provisionerSDK.radios.ble;

import com.amazon.comms.ringservice.webrtc.FrostVideoEffectController;
import com.amazon.whisperjoin.common.sharedtypes.configuration.OveractiveConfiguration;
import com.amazon.whisperjoin.common.sharedtypes.utility.WJLog;
import com.amazon.whisperjoin.provisionerSDK.configuration.defaults.Config;
import com.amazon.whisperjoin.util.Clock;
import com.google.common.base.MoreObjects;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Locale;
/* loaded from: classes13.dex */
public class OveractiveBleActivityDetector {
    private static final String TAG = "OveractiveBleActivityDetector";
    private int mActiveBucketCount;
    private final LinkedList<ActivityBucket> mActivityBuckets;
    private final long mBucketCount;
    private final Clock mClock;
    private final OveractiveConfiguration mOveractiveConfiguration;

    /* loaded from: classes13.dex */
    private static class ActivityBucket {
        private boolean mActivityDetected = false;
        private final long mBucketEndTime;
        private final long mBucketStartTime;

        public ActivityBucket(long j, long j2) {
            this.mBucketStartTime = j;
            this.mBucketEndTime = j + j2;
        }

        public long getBucketEndTime() {
            return this.mBucketEndTime;
        }

        public long getBucketStartTime() {
            return this.mBucketStartTime;
        }

        public boolean isActivityDetected() {
            return this.mActivityDetected;
        }

        public boolean isTimeInBucket(long j) {
            return j >= this.mBucketStartTime && j < this.mBucketEndTime;
        }

        public void setActivityDetected(boolean z) {
            this.mActivityDetected = z;
        }

        public String toString() {
            return MoreObjects.toStringHelper(this).add("mBucketStartTime", this.mBucketStartTime).add("mBucketEndTime", this.mBucketEndTime).add("mActivityDetected", this.mActivityDetected).toString();
        }
    }

    /* loaded from: classes13.dex */
    public static class OveractivityDetected extends Exception {
    }

    public OveractiveBleActivityDetector(OveractiveConfiguration overactiveConfiguration) {
        this(new Clock(), overactiveConfiguration);
    }

    private boolean isValidOveractiveConfig(OveractiveConfiguration overactiveConfiguration) {
        return overactiveConfiguration.getThresholdPercentage() > FrostVideoEffectController.VIDEO_STRENGTH_CLEAR && overactiveConfiguration.getThresholdPercentage() < 100.0d && overactiveConfiguration.getMonitoringWindowMs() >= overactiveConfiguration.getBucketLengthMs() && overactiveConfiguration.getBucketLengthMs() > 0 && overactiveConfiguration.getMonitoringWindowMs() > 0;
    }

    public void onNewDiscoveryActivity() throws OveractivityDetected {
        ActivityBucket activityBucket;
        long elapsedRealtime = this.mClock.elapsedRealtime();
        long monitoringWindowMs = elapsedRealtime - this.mOveractiveConfiguration.getMonitoringWindowMs();
        if (this.mActivityBuckets.size() > 0 && this.mActivityBuckets.peek().getBucketStartTime() < monitoringWindowMs) {
            this.mActivityBuckets.clear();
        }
        if (this.mActivityBuckets.isEmpty()) {
            this.mActivityBuckets.add(new ActivityBucket(monitoringWindowMs, this.mOveractiveConfiguration.getBucketLengthMs()));
        }
        ListIterator<ActivityBucket> listIterator = this.mActivityBuckets.listIterator(0);
        ActivityBucket first = this.mActivityBuckets.getFirst();
        while (true) {
            activityBucket = first;
            if (activityBucket.isTimeInBucket(elapsedRealtime)) {
                break;
            }
            listIterator.add(new ActivityBucket(activityBucket.getBucketEndTime(), this.mOveractiveConfiguration.getBucketLengthMs()));
            first = listIterator.previous();
        }
        if (activityBucket.isActivityDetected()) {
            return;
        }
        activityBucket.setActivityDetected(true);
        this.mActiveBucketCount = 0;
        while (listIterator.hasNext()) {
            ActivityBucket next = listIterator.next();
            if (next.getBucketStartTime() <= monitoringWindowMs) {
                listIterator.remove();
            } else if (next.isActivityDetected()) {
                this.mActiveBucketCount++;
            }
        }
        double d = (this.mActiveBucketCount / this.mBucketCount) * 100.0d;
        WJLog.d(TAG, String.format(Locale.ENGLISH, " Discovery Activity Update: Percentage of Window with Activity: %.2f", Double.valueOf(d)));
        if (d > this.mOveractiveConfiguration.getThresholdPercentage()) {
            throw new OveractivityDetected();
        }
    }

    OveractiveBleActivityDetector(Clock clock, OveractiveConfiguration overactiveConfiguration) {
        this.mActiveBucketCount = 0;
        this.mClock = clock;
        if (overactiveConfiguration != null && isValidOveractiveConfig(overactiveConfiguration)) {
            this.mOveractiveConfiguration = overactiveConfiguration;
        } else {
            this.mOveractiveConfiguration = new OveractiveConfiguration(Config.Overactive.BUCKET_LENGTH_MS, Config.Overactive.MONITORING_WINDOW_MS, 50.0d);
        }
        this.mBucketCount = this.mOveractiveConfiguration.getMonitoringWindowMs() / this.mOveractiveConfiguration.getBucketLengthMs();
        this.mActivityBuckets = new LinkedList<>();
    }
}
