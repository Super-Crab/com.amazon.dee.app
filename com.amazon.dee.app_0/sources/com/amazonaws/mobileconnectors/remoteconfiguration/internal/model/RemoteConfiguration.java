package com.amazonaws.mobileconnectors.remoteconfiguration.internal.model;

import com.amazonaws.mobileconnectors.remoteconfiguration.Configuration;
/* loaded from: classes13.dex */
public interface RemoteConfiguration {
    public static final int ORIGIN_DEFAULT = 1;
    public static final int ORIGIN_OVERWRITTEN = 3;
    public static final int ORIGIN_SYNCED = 2;

    String getAppConfigurationId();

    Configuration getConfiguration();

    String getEntityTag();

    int getOrigin();

    boolean isUpdate();
}
