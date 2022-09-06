package amazon.speech.tts;

import amazon.speech.simclient.metrics.MetricsClient;
/* loaded from: classes.dex */
public final class Configuration {
    private final boolean mFlagTTSAttrs;
    private final boolean mIgnoreAudioFocus;
    private final MetricsClient mMetricsClient;
    private final WakeLockManager mWakeLockManager;

    /* loaded from: classes.dex */
    public static class Builder {
        private MetricsClient mMetricsClient = null;
        private WakeLockManager mWakeLockManager = null;
        private boolean mFlagTTSAttrs = false;
        private boolean mIgnoreAudioFocus = false;

        public Configuration build() {
            return new Configuration(this.mMetricsClient, this.mWakeLockManager, this.mFlagTTSAttrs, this.mIgnoreAudioFocus);
        }

        public Builder enableCustomAudioAttributes(boolean z) {
            this.mFlagTTSAttrs = z;
            return this;
        }

        public Builder setIgnoreAudioFocusChange(boolean z) {
            this.mIgnoreAudioFocus = z;
            return this;
        }

        public Builder setMetricsClient(MetricsClient metricsClient) {
            this.mMetricsClient = metricsClient;
            return this;
        }

        public Builder setWakeLockManager(WakeLockManager wakeLockManager) {
            this.mWakeLockManager = wakeLockManager;
            return this;
        }
    }

    public boolean getFlagTTSAttributes() {
        return this.mFlagTTSAttrs;
    }

    public boolean getIgnoreAudioFocusChange() {
        return this.mIgnoreAudioFocus;
    }

    public MetricsClient getMetricsClient() {
        return this.mMetricsClient;
    }

    public WakeLockManager getWakeLockManager() {
        return this.mWakeLockManager;
    }

    private Configuration(MetricsClient metricsClient, WakeLockManager wakeLockManager, boolean z, boolean z2) {
        this.mWakeLockManager = wakeLockManager;
        this.mMetricsClient = metricsClient;
        this.mFlagTTSAttrs = z;
        this.mIgnoreAudioFocus = z2;
    }
}
