package com.amazonaws.mobileconnectors.remoteconfiguration.internal.model;

import com.amazonaws.mobileconnectors.remoteconfiguration.Configuration;
/* loaded from: classes13.dex */
public class RemoteConfigurationImpl implements RemoteConfiguration {
    private final String mAppConfigurationId;
    private final Configuration mConfiguration;
    private final String mEntityTag;
    private final int mOrigin;
    private final boolean mUpdate;

    public RemoteConfigurationImpl(Configuration configuration, String str, int i, String str2, boolean z) {
        if (str != null) {
            if (i != 1 && i != 2 && i != 3) {
                throw new IllegalArgumentException("Invalid configuration origin.");
            }
            this.mConfiguration = configuration;
            this.mAppConfigurationId = str;
            this.mOrigin = i;
            this.mEntityTag = str2;
            this.mUpdate = z;
            return;
        }
        throw new NullPointerException("The Application Configuration ID may not be null");
    }

    @Override // com.amazonaws.mobileconnectors.remoteconfiguration.internal.model.RemoteConfiguration
    public String getAppConfigurationId() {
        return this.mAppConfigurationId;
    }

    @Override // com.amazonaws.mobileconnectors.remoteconfiguration.internal.model.RemoteConfiguration
    public Configuration getConfiguration() {
        return this.mConfiguration;
    }

    @Override // com.amazonaws.mobileconnectors.remoteconfiguration.internal.model.RemoteConfiguration
    public String getEntityTag() {
        return this.mEntityTag;
    }

    @Override // com.amazonaws.mobileconnectors.remoteconfiguration.internal.model.RemoteConfiguration
    public int getOrigin() {
        return this.mOrigin;
    }

    @Override // com.amazonaws.mobileconnectors.remoteconfiguration.internal.model.RemoteConfiguration
    public boolean isUpdate() {
        return this.mUpdate;
    }
}
