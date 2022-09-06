package com.amazonaws.mobileconnectors.remoteconfiguration;
/* loaded from: classes13.dex */
public interface ConfigurationSyncCallback {
    void onConfigurationModified(Configuration configuration);

    void onConfigurationUnmodified(Configuration configuration);

    void onFailure(Exception exc);

    void onThrottle(long j);
}
